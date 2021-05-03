package application.GUI;

import application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class LoginScene {

	User user;
	
	public static Scene getLoginScene(Main main) {
		
		VBox layout = new VBox(20);
		
		layout.setStyle("-fx-background-color: lightblue");
		
		Label title = new Label("Welcome to Twitter");
		
		title.setFont(Font.font("Economica", 25));
		TextField username = new TextField("Username");
		TextField password = new TextField("Password");
		username.setMaxWidth(250);
		password.setMaxWidth(250);
		
		Button login = new Button("Login / Register");
		
		login.setOnAction(e -> {
			String usernameAttempt = username.getText();
			String passwordAttempt = password.getText();
			
			if (AuthManager.attemptLogin(usernameAttempt,passwordAttempt)) {
				User user = new User(usernameAttempt, true);
				AlertBox.display("Notice", "User successfully logged in!");
				main.setScene(FeedScene.getFeedScene(main, user, FeedFilters.NONE));
			} else {
				AlertBox.display("Notice", "Could not find register user...");
			}
		});
		

		
		layout.getChildren().addAll(title, username, password, login);
		layout.setAlignment(Pos.BASELINE_CENTER);

		
		Scene scene = new Scene(layout, 500, 300);
		
		return scene;
	}




	

}
