package application.GUI;

import application.Main;
import application.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddUserScene {
	
	public static Scene getAddUserScene(Main main, User user) {
		
		VBox layout = new VBox(50);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBar(main, user));
		
		layout.setAlignment(Pos.TOP_CENTER);
		
		Label title = new Label("Search for User");
		title.setFont(Font.font("Verdana", 20));
		TextField userName = new TextField();
		Button addUser = new Button("Follow User");
		
		addUser.setOnAction(e -> {
			if (user.followUser(userName.getText()) ) {
				AlertBox.display("Success!", "The user was followed");
				userName.setText("");
			} else {
				AlertBox.display("Error", "User could not be found or you are already following them.");
			}
		});
		
		
		layout.getChildren().addAll(title, userName, addUser);
		
		Scene scene = new Scene(layout, 500, 500);
		
		return scene;
		
	}

}
