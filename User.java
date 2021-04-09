package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class User implements Comparable<User> {
	
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
			if (followed.username == userName) return false;
		}
		
		if (AuthManager.checkUserExists(userName)) {
			try {
			FileWriter fw = new FileWriter(filePath + "/following.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			PrintWriter out = new PrintWriter(bw);
			
			//Check if user is in following
			
			out.append(userName);
			out.println();
			
			out.close();
			bw.close();
			
			//Overwrite file with new folowing
			
			following.add(new User(userName, false));	
			
			} catch (Exception e) {
				System.out.println("ERROR OCCURRED! Error : " + e.getLocalizedMessage());
			}
			
			System.out.println("User was followed!");

			return true;
			
		}
			else {
			System.out.println("User could not be followed - does not exist.");
			return false;
		}
					

	}
	
	
	/*
	 * @TODO: Remove the user's name from the "Following.txt" file
	 */
	public void unfollowUser(User user) {
		following.remove(user);
		System.out.println("User unfollowed!");
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
		try {
			
		FileWriter fw = new FileWriter(filePath + "/tweets.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		PrintWriter out = new PrintWriter(bw);
		
		Tweet newTweet = new Tweet(username, content);
		
		out.append(content);
		out.println();
		out.append(newTweet.getDateString());
		out.println();
		
		out.close();
		bw.close();
		
		System.out.println(newTweet.getHashtags().toString());
		
		if (isMainUser) tweets.add(newTweet);
		
		} catch (Exception e) {
			System.out.println("ERROR! Could not publish tweet. error - " + e.getLocalizedMessage());
		}
		
		
		
	}
	
	
	/*
	 * @NOTE: Needs serious refactoring, O(n^3)
	 */
	public Tweet[] getTwitterFeed() {
		
		ArrayList<Tweet> tweets = new ArrayList<>();
		
		for (User user : following) {
			
			tweets.addAll(user.getTweets());
			
		}
		
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
			
			Tweet newTweet = new Tweet(username, line, tweetDate);
			
			tweets.add(newTweet);
			
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED! : " + e.getLocalizedMessage());
		}
		
	}


	@Override
	public int compareTo(User o) {
		if (o.username == this.username) return 1;
		else return 0;
	}

}
