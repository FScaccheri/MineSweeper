package handlers;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.game.Defuser;
import view.GamePane;
import view.MainMenu;

public class EasyButtonEventHandler implements EventHandler<ActionEvent> {

	private Stage stage;
	private ArrayList<AudioClip> audioClips;

	public EasyButtonEventHandler(Stage stage, ArrayList<AudioClip> audioClips) {
		
		this.stage = stage;
		this.audioClips = audioClips;
	}
	
	
	public void handle(ActionEvent arg0) {
		
		Defuser.getInstance().setDifficulty(1);
		Scene gameScene = new Scene(new GamePane(), 1500, 900);
		
		Random random = new Random();
		AudioClip audio = this.audioClips.get(random.nextInt(this.audioClips.size()));
		audio.setVolume(0.3);
		audio.play();
		
		this.stage.setScene(gameScene);
	}

}
