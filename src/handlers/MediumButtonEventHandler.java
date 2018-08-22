package handlers;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.game.Defuser;
import view.MainMenu;

public class MediumButtonEventHandler implements EventHandler<ActionEvent> {

	private Stage stage;
	private MainMenu mainMenu;
	private ArrayList<AudioClip> audioClips;

	public MediumButtonEventHandler(Stage stage, MainMenu mainMenu, ArrayList<AudioClip> audioClips) {
		
		this.stage = stage;
		this.mainMenu = mainMenu;
		this.audioClips = audioClips;
	}
	
	
	public void handle(ActionEvent event) {
		
		Defuser.getInstance().setDifficulty(2);
		Scene scene = mainMenu.creteGameScene();
		
		Random random = new Random();
		AudioClip audio = this.audioClips.get(random.nextInt(this.audioClips.size()));
		audio.setVolume(0.3);
		audio.play();
		
		this.stage.setScene(scene);
	}

}
