package application.GUI;

import application.Main;
import application.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AddUserScene {
	
	public static Scene getAddUserScene(Main main, User user) {
		
		VBox layout = new VBox(50);
		VBox manageUsersLayout = new VBox(10);
		HBox allFollowing = new HBox(5);
		
		layout.getChildren().add(MainMenuBar.getMainMenuBar(main, user));
		
		layout.setAlignment(Pos.TOP_CENTER);
		manageUsersLayout.setAlignment(Pos.TOP_CENTER);
		
		Label title = new Label("Search for User");
		title.setFont(Font.font("Verdana", 20));
		
		TextField userName = new TextField();
		userName.setMaxWidth(250);
		Button addUser = new Button("Follow User");
		
		Text followedUsers = new Text("");
		followedUsers.setTextAlignment(TextAlignment.CENTER);
		followedUsers.setFont(Font.font("Verdana", 16));
		
		Label followLabel = new Label("Followed Users");
		Label note = new Label("To unfollow a user, press on the user's name.");
		followLabel.setFont(Font.font("Verdana", 20));
		
		addUser.setOnAction(e -> {
			if (user.followUser(userName.getText()) ) {
				AlertBox.display("Success!", "The user was followed");
				userName.setText("");
				main.setScene(AddUserScene.getAddUserScene(main, user));
			} else {
				AlertBox.display("Error", "User could not be found or you are already following them.");
			}
		});
			
		for (User friend: user.getFollowing()) {
			Button followingUser = new Button(friend.username);
			
			followingUser.setOnAction(e -> {
				user.unfollowUser(friend);
				AlertBox.display("Notice", "The user has been unfollowed");
				main.setScene(AddUserScene.getAddUserScene(main, user));
			});
			
			allFollowing.getChildren().add(followingUser);
		}
		
		manageUsersLayout.getChildren().addAll(followLabel, note, allFollowing);
		
		layout.getChildren().addAll(title, userName, addUser, manageUsersLayout);

		
		Scene scene = new Scene(layout, 500, 500);
		
		return scene;
		
	}

}
