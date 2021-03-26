package application.GUI;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.*;


public class AlertBox {
	
	public static void display(String title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		window.setTitle(title);
		
		Label label = new Label();
		label.setText(message);
		
		Button closeButton = new Button("Okay");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(20, label, closeButton);
		layout.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
	
		
		window.showAndWait();
		
	}

}
