package handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.game.Defuser;
import view.MainMenu;

public class EasyButtonEventHandler implements EventHandler<ActionEvent> {

	private Stage stage;
	private MainMenu mainMenu;
	private ArrayList<AudioClip> audioClips;
	private Button easyDiffButton;

	public EasyButtonEventHandler(Stage stage, MainMenu mainMenu, Button easyDiffButton, ArrayList<AudioClip> audioClips) {
		
		this.stage = stage;
		this.mainMenu = mainMenu;
		this.easyDiffButton = easyDiffButton;
		this.audioClips = audioClips;
	}
	
	
	public void handle(ActionEvent arg0) {
		
		Defuser.getInstance().setDifficulty(1);
		Scene scene = mainMenu.creteGameScene();
		
		Random random = new Random();
		AudioClip audio = this.audioClips.get(random.nextInt(this.audioClips.size()));
		audio.setVolume(0.3);
		audio.play();
		
		this.stage.setScene(scene);
	}

}
