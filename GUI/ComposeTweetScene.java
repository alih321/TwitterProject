package application.GUI;


import application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class ComposeTweetScene {
	
	public static Scene getComposeTweetScene(Main main, User user) {
		
		VBox layout = new VBox(50);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBar(main, user));
		
		layout.setAlignment(Pos.TOP_CENTER);
		
		Label title = new Label("Compose Tweet");
		title.setFont(Font.font("Verdana", 20));
		TextArea tweetContent = new TextArea();
		Button postTweet = new Button("Post Tweet");
		
		postTweet.setOnAction(e -> {
			if (tweetContent.getText().length() > 10) {
				user.publishTweet(tweetContent.getText());
				AlertBox.display("Success!", "Your tweet has been posted.");
				tweetContent.setText("");
			}
			else AlertBox.display("Notice", "Your Tweet is too short.");
		});
		
		
		layout.getChildren().addAll(title, tweetContent, postTweet);
		
		Scene scene = new Scene(layout, 500, 500);
		
		return scene;
		
	}

}
