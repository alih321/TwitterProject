package application.GUI;


import application.*;
import javafx.scene.control.*;

public class MainMenuBar {
	
	public static MenuBar getMainMenuBar(Main main, User user) {
		
		MenuBar bar = new MenuBar();
		
		bar.setStyle("-fx-background-color: lightblue");
		
		MenuItem MyFeed = new MenuItem("My Feed"), MyProfile = new MenuItem("My Profile"), SearchUsers = new MenuItem("Add Users"), newTweet = new MenuItem("New Tweet"),
				signOut = new MenuItem("Sign Out");

		MyFeed.setStyle("-fx-font-size: 20");
		MyProfile.setStyle("-fx-font-size: 20");
		SearchUsers.setStyle("-fx-font-size: 20");
		newTweet.setStyle("-fx-font-size: 20");
		signOut.setStyle("-fx-font-size: 20");
		
		bar.setStyle("-fx-font-size: 20");

		
		Menu menu = new Menu("Options");
		
		
		MyFeed.setOnAction(e -> {
			main.setScene(FeedScene.getFeedScene(main, user));
		});
		
		newTweet.setOnAction(e -> {
			main.setScene(ComposeTweetScene.getComposeTweetScene(main, user));
		});
		
		SearchUsers.setOnAction(e -> {
			main.setScene(AddUserScene.getAddUserScene(main, user));
		});
		
		MyProfile.setOnAction(e -> {
			main.setScene(MyProfileScene.getMyProfileScene(main, user));
		});
		
		signOut.setOnAction(e -> {
			main.setScene(LoginScene.getLoginScene(main));
		});

		menu.getItems().addAll(MyFeed, MyProfile, SearchUsers, newTweet, signOut);

		
		bar.getMenus().add(menu);
				
		return bar;
	}

}
