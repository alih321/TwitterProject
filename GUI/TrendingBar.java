package application.GUI;

import application.User;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class TrendingBar {
	
	private static Text trendingTags = new Text("No trending tags");
	
	public static HBox getTrendingBar(User user) {
		
		HBox trending = new HBox(20);
		Text trendingLabel = new Text("Trending Tags:");
		
		trendingTags.setFont(Font.font("Verdana", 14));
		trendingTags.setFill(Color.RED);
		trendingLabel.setFont(Font.font("Verdana", 14));

		
		trending.getChildren().addAll(trendingLabel, trendingTags, new Line());
		
		return trending;
	}
	
	public void setTrendingTag(String tag) {
		trendingTags.setText(tag);
	}

}
