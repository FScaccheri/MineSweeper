package view;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application{
	
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Buscaminas!");

		stage.setMaximized(false);
		
		//GamePane gamePane = new GamePane();
		//Scene gameScene = new Scene(1200, 800);
		
		MainMenu mainMenu = new MainMenu(stage);
		Scene mainScene = new Scene(mainMenu, 1200, 800);
		
		stage.setScene(mainScene);
		
		stage.show();
		
	}

}
