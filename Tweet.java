package application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.GUI.AlertBox;

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
		
		if (this.accountLikes.contains(""))
			this.accountLikes.remove("");
		
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
	
	public Tweet(Tweet other, User retweetedBy) {
		this.user = retweetedBy;
		this.content = other.content;
		this.datePosted = new Date();
		this.isTrending = other.isTrending;
		this.accountLikes = new ArrayList<>();
	}
	
	
	public void setTrending(boolean b) {
		this.isTrending = b;
	}
	
	
	public void setRetweet(String un) {
		this.retweetedFrom = un;
	}
	
	public void addAccountLike(User u) {
		
		if (u.username == user.username) {
			AlertBox.display("Notice", "You cannot like your own tweet.");
			return;
		}
		
		if (!isUserLiked(u)) {
			accountLikes.add(u.username);
			AlertBox.display("Success!", "You have liked this tweet.");
		}
		else {
			accountLikes.remove(u.username);
			AlertBox.display("Notice", "You have unliked this tweet.");
		}
		
		user.updateTweets();
		
	}
	
	
	public int getNumberOfLikes() {
		
		return accountLikes.size();
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


class SortByDate implements Comparator<Tweet> {

	@Override
	public int compare(Tweet o1, Tweet o2) {
		return -1*(o1.getDate().compareTo(o2.getDate()));
	}
	
}
