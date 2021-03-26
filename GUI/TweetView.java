package application.GUI;

import application.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class TweetView extends VBox {
	
	public TweetView(Tweet tweet) {
		
		super(20);
		
		Text user = new Text(tweet.username);
		Text content = new Text(tweet.content);
		Text date = new Text(tweet.getDateString());
		
		Separator separator = new Separator();
		
		user.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		content.setFont(Font.font("Verdana", 14));
		date.setFont(Font.font("Verdana", 12));
	
		
		
		this.getChildren().addAll(user, content, date, separator);
		
	}
	
	

}
