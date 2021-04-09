package application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet {

	public String username;
	public String content;
	private Date datePosted;
	private boolean isTrending;
	
	
	public Tweet(String un, String textContent) {
		
		username = un;
		content = textContent;
		datePosted = new Date();
	}
	
	public Tweet(String un, String textContent, String dateString) {
		
		username = un;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		content = textContent;
		
		try {
		datePosted = format.parse(dateString);
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED! error : " + e);
		}
	}
	
	
	public void setTrending(boolean b) {
		this.isTrending = b;
	}
	
	public boolean getIsTrending() {
		
		return isTrending;
	}
	
	
	public String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(datePosted);
	}
	
	
	public Date getDate() {
		return datePosted;
	}
	
	public String getID() { 
		return username + datePosted;
	}
	
	public ArrayList<String> getHashtags() {
		
		ArrayList<String> hashtags = new ArrayList<>();
		
	    String regex = "#[a-zA-Z0-9]*";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(content);
	    
	    while (matcher.find()) hashtags.add(matcher.group(0));
		
		return hashtags;
	}
	

	
	
	
}
