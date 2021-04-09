package application.GUI;


import application.*;
import javafx.scene.control.*;

public class MainMenuBar {
	
	public static MenuBar getMainMenuBar(Main main, User user) {
		
		MenuBar bar = new MenuBar();
		
		bar.setStyle("-fx-background-color: lightblue");
		
		MenuItem MyFeed = new MenuItem("My Feed"), MyProfile = new MenuItem("My Profile"), SearchUsers = new MenuItem("Add Users"), newTweet = new MenuItem("New Tweet"),
				signOut = new MenuItem("Sign Out");
		
		bar.setStyle("-fx-font-size: 16");

		
		Menu menu = new Menu("Options");
		
		
		MyFeed.setOnAction(e -> {
			main.setScene(FeedScene.getFeedScene(main, user, FeedFilters.NONE));
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
	
	public static MenuBar getMainMenuBarWithFilters(Main main, User user) {
		
		MenuBar bar = getMainMenuBar(main, user);
		
		Menu filters = new Menu("Filters");
		
		MenuItem oldNew = new MenuItem("Old-New"), newOld = new MenuItem("New-Old"), trending = new MenuItem("Trending"), last10 = new MenuItem("Last 10 Tweets");
		
		trending.setOnAction(e -> {
			main.setScene(FeedScene.getFeedScene(main, user, FeedFilters.TRENDING_ONLY));
		});
		
		newOld.setOnAction(e -> {
			main.setScene(FeedScene.getFeedScene(main, user, FeedFilters.NONE));
		});
		
		filters.getItems().addAll(oldNew,newOld,trending,last10);
		
		bar.getMenus().add(filters);
		
		return bar;
		
		
	}

}
