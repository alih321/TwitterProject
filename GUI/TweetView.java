package application.GUI;

import application.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class TweetView extends VBox {
	
	public TweetView(Tweet tweet) {
		
		super(20);
		
		Text user = new Text(tweet.username);
		Text content = new Text(tweet.content);
		Text date = new Text(tweet.getDateString());
		Text trending = new Text("");

		//Button like = new Button("Like");
		
		Separator separator = new Separator();
		
		user.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		content.setFont(Font.font("Verdana", 14));
		date.setFont(Font.font("Verdana", 12));
		
		if (tweet.getIsTrending()) {
			trending.setText("TRENDING");
			trending.setFill(Color.RED);
		}
		
		this.getChildren().addAll(user, content, date, trending, separator);
		
	}
	
	
	

}
