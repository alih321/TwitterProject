package application;
import java.util.*;
public class Runner {
	
	static User user;
	static Scanner scan = new Scanner(System.in);


	public static void main(String[] args) {		
		
		DirectoryManager drMng = new DirectoryManager();
		AuthManager authMng = new AuthManager();
		
		System.out.println("Does Directory Exist?: " + drMng.initMainDirectory() + "\n\n");
		
		String username;
		String password;
		
		while(true) {
		
		System.out.println();
		System.out.println("---LOGIN---");
		System.out.println("Username: ");
		 username = scan.next();
		System.out.println("Password: ");
		 password = scan.next();
		 
		
		if (authMng.attemptLogin(username, password) == true) break;
		
		
		}
		
		 System.out.println("-----------");
		
		user = new User(username, true);
		
		
		runOption();
				

	}
	

	static void printMenu() {
		System.out.println("\n\n-----Choose Option-----");
		System.out.println("1. Add Friend");
		System.out.println("2. View Your Tweets");
		System.out.println("3. View Your Friend's List");
		System.out.println("4. Write New Tweet");
		System.out.println("5. See FEED");
		System.out.println("6. End\n");
	}
	
	
	static void runOption() {
		
		Scanner scan2 = new Scanner(System.in);

		
		printMenu();
		
		System.out.println("What is your choice: ");
		int option = scan.nextInt();
			
		if (option == 1) {
			
			System.out.println("\n\n----Add Friend----\n\nWhat is your friend's name?: ");
			
			String friendName = scan2.next();
			
			user.followUser(friendName);
			
			System.out.println("\n\n------------------\n\n");
		} 
		
		else if (option == 2) {
			ArrayList<Tweet> userTweets = user.getTweets();
			
			System.out.println("---- Your Tweets ----");
			
			for (int i = 0; i<userTweets.size(); i++) {
				Tweet tweet = userTweets.get(i);
				
				System.out.println("\n----");
				System.out.println("USER: " + tweet.user.username);
				System.out.println("--");
				System.out.println(tweet.content);
				System.out.println("\n");
				System.out.println(tweet.getDateString());
				System.out.println("----\n\n");
				
			}
			
			System.out.println("------------------\n\n");

		}
		
		else if (option == 3) {
			ArrayList<User> userFriends = user.getFollowing();
			for (int i = 0; i<userFriends.size(); i++) {
				
				System.out.println("\n\n----Your Friend's List----");
				
				System.out.println(userFriends.get(i).username);
				
				System.out.println("------------------\n\n");
			}
		} 
		
		else if (option == 4) {
			System.out.println("\n\n----New Tweet----");
			
			System.out.println("What is the content of the tweet: ");
			String newTweetContent = scan2.nextLine();
			user.publishTweet(newTweetContent);
			
			System.out.println("Tweet posted! \n\n------------------\n\n");
			
		} else if (option == 5) {
			Tweet[] tweets = user.getTwitterFeed();
			
			for (Tweet tweet : tweets) {
				
				if (tweet == null) break;
				
				System.out.println("\n----");
				System.out.println("USER: " + tweet.user.username);
				System.out.println("--");
				System.out.println(tweet.content);
				System.out.println("\n");
				System.out.println(tweet.getDateString());
				System.out.println("----\n\n");
			}
		}
		
		else if (option == 6) {
			return;
		}
		
		runOption();
			
	}
	

	

}
