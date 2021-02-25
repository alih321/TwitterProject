package twitter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {

	public String username;
	public String content;
	private Date datePosted;
	
	
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
	
	
	
	public String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(datePosted);
	}
	
	
	public Date getDate() {
		return datePosted;
	}
	
	
	
}
