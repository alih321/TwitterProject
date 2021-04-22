package application.GUI;

import java.util.ArrayList;

import application.Main;
import application.Tweet;
import application.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MyProfileScene {
	
	public static Scene getMyProfileScene(Main main, User user) {
		
		ScrollPane scroll = new ScrollPane();
				
		VBox layout = new VBox(20);
		
		layout.setAlignment(Pos.TOP_CENTER);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBar(main, user));
		
		
		ArrayList<Tweet> tweets = user.getTweets();
		
		for (Tweet tweet : tweets) {
			
			TweetView tweetView = new TweetView(tweet, user);
			Line line = new Line();
			line.setStroke(Color.BLACK);
			
			layout.getChildren().addAll(tweetView, line);
			
		}
		
		scroll.setContent(layout);
		scroll.setFitToWidth(true);
		
		Scene ProfileScene = new Scene(scroll, 500, 500);
		
		
		return ProfileScene;
		
	}

}
