package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthManager {
	
	static String PATH = DirectoryManager.PATH;
	
	
	public static boolean attemptLogin(String username, String password) {
		
		switch (checkUserCredentials(username, password)) {
		case SUCCESS:
			System.out.println("User exists and password is correct!");
			return true;
		case INCORRECT_PASSWORD:
			System.out.println("User exists but password was incorrect!");
			return false;
		default:
			System.out.println("User does not exist. The user was registered.");
			registerUser(username, password);
			return true;
		}
	}
	
	
	
	public static boolean checkUserExists(String username) {
		
		File file = new File(PATH + "/" + "authorization.txt");
		try {
			FileReader fr = new FileReader(file.getAbsoluteFile());
			
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while (line != null) {
				
				if (line.contentEquals(username)) {
	
					return true; //SUCCESS
				}
				
				
				line = br.readLine();
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} 
		
		
		return false; //User does not exist
		
	}
	


	
	private static UserAuthResults checkUserCredentials(String username, String password) {
		
		File file = new File(PATH + "/" + "authorization.txt");
		try {
			FileReader fr = new FileReader(file.getAbsoluteFile());
			
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while (line != null) {
				
				if (line.contentEquals(username)) {
	
					String pw = br.readLine();
					if (pw.equals(password)) { 
						br.close();
						return UserAuthResults.SUCCESS;
					}
					
	
					else {
						br.close();
						return UserAuthResults.INCORRECT_PASSWORD;
					}
				}
				
				
				line = br.readLine();
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
				
		return UserAuthResults.USER_DOES_NOT_EXIST;
		
	}
	

	
	private static boolean registerUser(String username, String password) {
		
		File file = new File(PATH + "/" + "authorization.txt");
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			PrintWriter out = new PrintWriter(bw);
			
			out.append(username);
			out.println();
			out.append(password);
			out.println();
			
			out.close();
			bw.close();
			
			Files.createDirectories(Paths.get(PATH + "/Users/" + username));
			
			File tweetFile = new File(PATH + "/Users/" + username + "/tweets.txt");
			tweetFile.createNewFile();
			
			File followingFile = new File(PATH + "/Users/" + username + "/following.txt");
			followingFile.createNewFile();
						
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		return true;
	}
	
	


}


