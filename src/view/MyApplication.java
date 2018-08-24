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
		
		stage.setTitle("MineSweeper!");

		stage.setMaximized(false);
		
		//GamePane gamePane = new GamePane();
		//Scene gameScene = new Scene(1200, 800);
		
		MainMenu mainMenu = new MainMenu(stage);
		Scene mainScene = new Scene(mainMenu, 1500, 900);
		
		stage.setScene(mainScene);
		
		stage.show();
		
		
	}
	
	

}
