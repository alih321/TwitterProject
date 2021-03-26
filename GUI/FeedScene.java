package application.GUI;

import application.Main;
import application.Tweet;
import application.User;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class FeedScene {
	
	public static Scene getFeedScene(Main main, User user) {
		
		ScrollPane scroll = new ScrollPane();
				
		VBox layout = new VBox(20);
		
		layout.setAlignment(Pos.TOP_CENTER);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBar(main, user));
		
		
		Tweet[] tweets = user.getTwitterFeed();
		
		for (Tweet tweet : tweets) {
			
			TweetView tweetView = new TweetView(tweet);
			Line line = new Line();
			line.setStroke(Color.BLACK);
			
			layout.getChildren().addAll(tweetView, line);
			
		}
		
		scroll.setContent(layout);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		
		Scene feedScene = new Scene(scroll, 500, 500);
		
		
		
		
		return feedScene;
		
	}

}
