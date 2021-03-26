package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import application.GUI.*;
import javafx.scene.Scene;



public class Main extends Application {
	
	private Stage primStage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primStage = primaryStage;
		
		primStage.setTitle("Twitter");
		
		primaryStage.setScene(LoginScene.getLoginScene(this));
		primaryStage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
		
		DirectoryManager.initMainDirectory();
	}
	
	
	public void setScene(Scene scene) {
		primStage.setScene(scene);
	}




	
	

	
}
