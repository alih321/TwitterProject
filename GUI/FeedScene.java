package application.GUI;

import application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class FeedScene {
	
	public static Scene getFeedScene(Main main, User user, FeedFilters filter) {
		
		ScrollPane scroll = new ScrollPane();
				
		VBox layout = new VBox(20);
								
		layout.setAlignment(Pos.TOP_CENTER);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBarWithFilters(main, user));
		
		
		Tweet[] tweets = user.getTwitterFeed();
		
		
		TrendingBar trendingBar = new TrendingBar();
		trendingBar.setTrendingTag(FeedAnalyzer.getTrendingTagsFromFeed(tweets));
		HBox trending = trendingBar.getTrendingBar(user);
		

		layout.getChildren().add(trending);

		for (Tweet tweet : tweets) {
			
			TweetView tweetView = new TweetView(tweet);
			Line line = new Line();
			line.setStroke(Color.BLACK);
			
			switch (filter) {
			
			case NONE:
				layout.getChildren().addAll(tweetView, line);
				break;
				
			case TRENDING_ONLY:
				if (tweet.getIsTrending()) 
					layout.getChildren().addAll(tweetView, line);
				break;
				
			default:
				layout.getChildren().addAll(tweetView, line);
			
			}
			
		}
		
		scroll.setContent(layout);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		
		Scene feedScene = new Scene(scroll, 500, 500);
		
		
		
		
		return feedScene;
		
	}

}
