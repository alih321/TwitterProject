package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import application.GUI.AlertBox;

public class User {
	
	public String username;
	
	private String filePath;
	private boolean isMainUser;
	
	/* Friend's List
	 * Note: Not mutual, it is directional */
	private ArrayList<User> following = new ArrayList<User>();
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	
	public User(String userName, boolean isMain) {
		username = userName;
		isMainUser = isMain;
		
		String desktopPath = DirectoryManager.getDesktopPath();
		filePath = desktopPath + "/TwitterProject/Users/" + username;
		
		loadProfile();
	}
	
	
	/*
	 * Calls the methods necessary to initialize the entire object
	 */
	public void loadProfile() {
		
		if (isMainUser) loadFollowingFromFiles();
		loadTweetsFromFiles();
		
	}
	
	/*
	 * @RETURNS The Following ArrayList object
	 */
	public ArrayList<User> getFollowing() { return following; }
	
	
	/*
	 * Follows a specific user putting their tweets in your feed
	 * @PARAM userName: The username of the user you'd like to follow
	 */
	public boolean followUser(String userName) {
		
		for (User followed : following) {
			if (followed.username.equals(userName)) return false;
		}
		
		if (AuthManager.checkUserExists(userName)) {
			
			for (User users: following) {
				if (users.username == userName) {
					System.out.println("User is already followed");
					return false;
				}
			}
			
			try {
				File tweetFile = new File(filePath + "/following.txt");
				
				PrintWriter out = new PrintWriter(tweetFile);
				
				User toFollow = new User(userName, false);
				following.add(toFollow);
				
				String newFollowingFileContent = "";
				
				for (User follow: following) {
					newFollowingFileContent += follow.username + "\n";
				}
				
				System.out.println(newFollowingFileContent);
				
				out.write(newFollowingFileContent);
				out.flush();
				out.close();
				
			} catch (Exception e) {
				System.out.println("A problem occurred: " + e.getMessage());
			}
			
			System.out.println("User was followed!");

			return true;
			
		}
			else {
			System.out.println("User could not be followed - does not exist or is already followed.");
			return false;
		}
					

	}
	

	public void unfollowUser(User user) {

		try {
			File tweetFile = new File(filePath + "/following.txt");
			
			PrintWriter out = new PrintWriter(tweetFile);
			
			following.remove(user);
			System.out.println("User unfollowed!");
			
			String newFollowingFileContent = "";
			
			for (User follow: following) {
				newFollowingFileContent += follow.username + "\n";
			}
			
			System.out.println(newFollowingFileContent);
			
			out.write(newFollowingFileContent);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			System.out.println("A problem occurred: " + e.getMessage());
		}
		
		AlertBox.display("Notification", "User was unfollowed");
	}
	
	
	
	/*
	 * @RETURNS The Tweet ArrayList object
	 */
	public ArrayList<Tweet> getTweets() { return tweets; }
	
	
	
	/*
	 * Publishes a tweet if the method is called from a Main User class
	 * @PARAM content: The tweet's text content
	 */
	public void publishTweet(String content) {
		
		Tweet newTweet = new Tweet(this, content);
		tweets.add(newTweet);
		
		updateTweets();
		
		
		
	}
	
	public void retweetTweet(Tweet t) {
		
		if (t.user.username == this.username) 
			return;
		
		
		Tweet retweet = new Tweet(t, this);
		retweet.setRetweet(t.user.username);
		AlertBox.display("Success!", "You have retweeted this tweet.");
		
		tweets.add(retweet);
		updateTweets();
		
	}
	
	public void updateTweets() {
		
		try {
			File tweetFile = new File(filePath + "/tweets.txt");
			
			PrintWriter out = new PrintWriter(tweetFile);
			
			String newTweetFileContent = "";
			
			for (Tweet tweet: tweets) {
				newTweetFileContent += tweet.toString();
			}
			
			System.out.println(newTweetFileContent);
			
			out.write(newTweetFileContent);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			System.out.println("A problem occurred: " + e.getMessage());
		}
		
	}
	
	

	public Tweet[] getTwitterFeed() {
		
		ArrayList<Tweet> tweets = new ArrayList<>();
		
		for (User user : following) {
			
			tweets.addAll(user.getTweets());
			
		}
		
		Collections.sort(tweets, new SortByDate());
		
		Tweet[] tweetArray = new Tweet[tweets.size()];
		
		
		for (int i = 0; i<tweets.size(); i++) {
			tweetArray[i] = tweets.get(i);
		}
		
		String trendingTag = FeedAnalyzer.getTrendingTagsFromFeed(tweetArray);
		
		for (int i = 0; i<tweetArray.length; i++)
			if (tweetArray[i].content.contains(trendingTag))
				tweetArray[i].setTrending(true);

		
		return tweetArray;
	}
	
	
	
	/*
	 * File Management
	 */
	
	private void loadFollowingFromFiles() {
		
		try {
		FileReader fr = new FileReader(filePath + "/following.txt");
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		
		while (line != null) {
			
			if (AuthManager.checkUserExists(line)) {
				
				User newUser = new User(line, false);
				
				following.add(newUser);
				
			}
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED! : " + e.getLocalizedMessage());
		}
		

	}
	
	private void loadTweetsFromFiles() {
		//Load Tweets from Files
		
		try {
		FileReader fr = new FileReader(filePath + "/tweets.txt");
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		
		while (line != null) {
			
			String tweetDate = br.readLine();
			
			String accountLikes = br.readLine();
			
			String[] accountSep = accountLikes.split("\t");
			ArrayList<String> allAccounts = new ArrayList<>();
			for (String s : accountSep) allAccounts.add(s);
			
			String retweetedFrom = br.readLine();
			
			Tweet newTweet;
			
			if (retweetedFrom.equals("-"))
				newTweet = new Tweet(this, line, tweetDate, allAccounts);
			else
				newTweet = new Tweet(this, line, tweetDate, allAccounts, retweetedFrom);
				
			
			tweets.add(newTweet);
			
			System.out.println(newTweet);
			
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED! : " + e.getLocalizedMessage());
		}
		
	}


}
