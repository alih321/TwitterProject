package application.GUI;

import application.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class TweetView extends VBox {
	
	public TweetView(Tweet tweet, User viewer) {
		
		super(20);
		
		
		Text user = new Text((tweet.getRetweetedFrom().equals("-")) ? tweet.user.username : (tweet.user.username + " retweeted from " + tweet.getRetweetedFrom()));
		Text content = new Text(tweet.content);
		Text date = new Text(tweet.getDateString());
		Text trending = new Text("");

		HBox actions = getActionView(tweet, viewer);
		
		Separator separator = new Separator();
		
		user.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		content.setFont(Font.font("Verdana", 14));
		date.setFont(Font.font("Verdana", 12));
		
		if (tweet.getIsTrending()) {
			trending.setText("TRENDING");
			trending.setFill(Color.RED);
		}
		
		this.getChildren().addAll(user, content, date, actions, trending, separator);
		
	}
	
	
	private HBox getActionView(Tweet tweet, User viewer) {
		HBox actions = new HBox(30);
		Text numOfLikes = new Text("0 Likes");
		Button like = new Button("Like");
	
		if (tweet.isUserLiked(viewer))
			like.setText("Unlike");
		else
			like.setText("Like");
		
		like.setOnAction(e -> {
			tweet.addAccountLike(viewer);
		});
		
		numOfLikes.setText(tweet.getNumberOfLikes() + " Likes");
		
		Button retweet = new Button("Retweet");
		
		retweet.setOnAction(e -> {
			viewer.retweetTweet(tweet);
		});
		
		
		actions.getChildren().addAll(numOfLikes, like, retweet);
		
		actions.setAlignment(Pos.CENTER_LEFT);
		return actions;
	}
	
	
	

}
