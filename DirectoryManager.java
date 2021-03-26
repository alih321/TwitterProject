package application;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryManager {

	static String PATH = getDesktopPath() + "/TwitterProject";
	
	
	public static boolean initMainDirectory() {
		
		File directory = new File(PATH);
		
		if (! directory.exists()) {
			
			try {
				
			Files.createDirectories(Paths.get(PATH + "/Users"));
			File authorizationFile = new File(PATH + "/authorization.txt");
			authorizationFile.createNewFile();
			
			} catch (IOException E){
				
				System.exit(-1);
				
			}
			
				
		}
		
		return directory.exists();

	}
	

	
	
	
	public static String getDesktopPath() {
		
		try {
			
			  String desktopPath = System.getProperty("user.home") + "/Desktop";
			  desktopPath.replace("\\", "/");
			  
				return desktopPath;

			  } catch (Exception e) {
				  System.out.println("Exception caught ="+e.getMessage());
				  return null;
			  }
		
	}
	
	


}
