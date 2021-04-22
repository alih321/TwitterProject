package application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet {

	public User user;
	public String content;
	private Date datePosted;
	private boolean isTrending;
	private ArrayList<String> accountLikes;
	private String retweetedFrom = "-";
	
	
	public Tweet(User user, String textContent) {
		
		this.user = user;
		content = textContent;
		datePosted = new Date();
		accountLikes = new ArrayList<>();
	}
	
	public Tweet(User user, String textContent, String dateString, ArrayList<String> accountLikes) {
		
		this.user = user;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		content = textContent;
		
		this.accountLikes = accountLikes;
		
		try {
		datePosted = format.parse(dateString);
		} catch (Exception e) {
			System.out.println("ERROR OCCURRED! error : " + e);
		}
		
	}
	
	public Tweet(User user, String textContent, String dateString, ArrayList<String> accountLikes, String retweetFrom) {
		
		this(user, textContent, dateString, accountLikes);
		this.retweetedFrom = retweetFrom;
		
	}
	
	public Tweet(Tweet other) {
		this.user = other.user;
		this.content = other.content;
		this.datePosted = new Date();
		this.isTrending = other.isTrending;
		this.accountLikes = other.accountLikes;
	}
	
	
	public void setTrending(boolean b) {
		this.isTrending = b;
	}
	
	
	public void setRetweet(String un) {
		this.retweetedFrom = un;
	}
	
	public void addAccountLike(User u) {
		
		if (u.username == user.username) return;
		
		if (!isUserLiked(u))
			accountLikes.add(u.username);
		else
			accountLikes.remove(u.username);
		
		user.updateTweets();
		
	}
	
	
	public int getNumberOfLikes() {
		return accountLikes.size()-1;
	}
	
	
	public boolean isUserLiked(User u) {
		return accountLikes.contains(u.username);
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
		return user.username + datePosted;
	}
	
	public String getRetweetedFrom() {
		return retweetedFrom;
	}
	
	
	public ArrayList<String> getHashtags() {
		
		ArrayList<String> hashtags = new ArrayList<>();
		
	    String regex = "#[a-zA-Z0-9]*";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(content);
	    
	    while (matcher.find()) hashtags.add(matcher.group(0));
		
		return hashtags;
	}
	
	public String toString() {
		
		String part1 = content + "\n" + getDateString() + "\n";
		String part2 = "";
		
		for (String s: accountLikes) {
			part2+=s + "\t";
		}
		
		return part1 + part2 + "\n" + retweetedFrom + "\n";
	}

	
	
	
}
