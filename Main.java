package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import application.GUI.*;
import javafx.scene.Scene;



public class Main extends Application {
	
	private Stage primStage;
	private Scene currentScene;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primStage = primaryStage;
		
		primStage.setTitle("Twitter");
		
		primaryStage.setScene(LoginScene.getLoginScene(this));
		primaryStage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		
		DirectoryManager.initMainDirectory();

		Application.launch(args);
		
	}
	
	
	public void setScene(Scene scene) {
		primStage.setScene(scene);
	}
	
	
}
