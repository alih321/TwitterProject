package application.GUI;

import application.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TestFeedScene {
	
	public static Scene getTestFeedScene() {
		
		
		Button button1 = new Button("Test 1"), button2 = new Button("Test 2"), button3 = new Button("Test 3");
		
		Separator sep = new Separator();
		
		button1.setBackground(Background.EMPTY);
		button2.setBackground(Background.EMPTY);
		button3.setBackground(Background.EMPTY);
		
		
		VBox sideBar = new VBox(20);
		
		VBox regularView = new VBox(20);
		
		ScrollPane scroll = new ScrollPane();
		
		
		sideBar.getChildren().addAll(sep, button1,button2,button3);
		
		//regularView.getChildren().add(new TweetView(new Tweet("Test 1", "Hello")));
		
		sideBar.setStyle("-fx-background-color: lightblue");
		
		regularView.setFillWidth(true);

		HBox layout = new HBox();
		
		scroll.setContent(regularView);
		sideBar.setMinWidth(120);
		scroll.setMinWidth(380);

		layout.getChildren().addAll(sideBar, scroll);
		
		Scene scene = new Scene(layout,500,500);
		
	
		
		return scene;
	}

}
