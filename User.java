package twitter;

import java.io.*;
import java.util.ArrayList;

public class User {
	
	public String username;
	
	private String filePath;
	private boolean isMainUser;
	
	/* Friend's List
	 * Note: Not mutual, it is directional */
	private static ArrayList<User> following = new ArrayList<User>();
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	
	public User(String userName, boolean isMain) {
		username = userName;
		isMainUser = isMain;
		
		String desktopPath = new DirectoryManager().getDesktopPath();
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
	public ArrayList<User> getFollowing() {
		
		return following;
	}
	
	
	/*
	 * Follows a specific user putting their tweets in your feed
	 * @PARAM userName: The username of the user you'd like to follow
	 */
	public void followUser(String userName) {
		
		DirectoryManager drMng = new DirectoryManager();
		
		switch (drMng.checkUserExists(userName)) {
		
		case 0: //Success
			
			try {
			FileWriter fw = new FileWriter(filePath + "/following.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			PrintWriter out = new PrintWriter(bw);
			
			out.append(userName);
			out.println();
			
			out.close();
			bw.close();
			
			following.add(new User(userName, false));	
			
			} catch (Exception e) {
				System.out.println("ERROR OCCURRED! Error : " + e.getLocalizedMessage());
			}
			
			System.out.println("User was followed!");

			break;
			
			
		default:
			System.out.println("User could not be followed - does not exist.");
		}
					

	}
	
	
	
	
	/*
	 * @RETURNS The Tweet ArrayList object
	 */
	public ArrayList<Tweet> getTweets() {
		
		return tweets;
	}
	
	
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
		
		if (isMainUser) tweets.add(newTweet);
		
		} catch (Exception e) {
			System.out.println("ERROR! Could not publish tweet. error - " + e.getLocalizedMessage());
		}
		
	}
	
	
	/*
	 * File Management
	 */
	
	private void loadFollowingFromFiles() {
		
		try {
		FileReader fr = new FileReader(filePath + "/following.txt");
		
		BufferedReader br = new BufferedReader(fr);
		DirectoryManager drMng = new DirectoryManager();
		
		String line = br.readLine();
		
		while (line != null) {
			
			if (drMng.checkUserExists(line) == 0) {
				
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
		DirectoryManager drMng = new DirectoryManager();
		
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
	
	
	
	public static Tweet[] getTwitterFeed() {
		
		Tweet[] feed = new Tweet[10];
		
		for (User user: following) {
			ArrayList<Tweet> followingTweets = user.getTweets();
			
			for (int i = 0; i<followingTweets.size(); i++) {
				feed[i] = followingTweets.get(i);
			}
		}
		
		return feed;
	}
	
	
	
	/*
	 * @RETURNS The Twitter Feed based on every followed User's Tweets sorted in date order from most recent - oldest
	 * 
	 * Work in Progress
	 */
	
//	public Tweet[] getTwitterFeed() {
//		Tweet[] feed = new Tweet[10];
//		
//		for (User user : following) {
//			
//			ArrayList<Tweet> followingTweets = user.getTweets();
//			
//			for (int i = 0; i<followingTweets.size(); i++) {
//				
//				boolean shouldContinue = false;
//				
//				for (int j = 0; j<feed.length; j++) {
//					
//					if (shouldContinue) {
//						shouldContinue = false;
//						break;
//					}
//					
//					if (feed[j] == null) feed[j] = followingTweets.get(i);
//					
//					else if (feed[j].getDate().getTime() < followingTweets.get(i).getDate().getTime()) {
//						feed[j] = followingTweets.get(i);
//						shouldContinue = true;
//						continue;
//					} 
//					
//					else continue;
//					
//				}
//				
//			}
//		}
//		
//		return feed;
//	}
	



	
	

}
