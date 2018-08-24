package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.cells.Cell;
import model.game.Defuser;
import view.CellButton;
import view.ResourceHandler;

public class RightClickOnFlagEventHandler implements EventHandler<MouseEvent>{

	private Cell cell;
	private CellButton cellButton;

	public RightClickOnFlagEventHandler(Cell cell, CellButton cellButton) {

		this.cell = cell;
		this.cellButton = cellButton;
	}

	public void handle(MouseEvent event) {
		
		MouseButton mouseButton = event.getButton();
		
		/* Still doesn't work lmao
		AudioClip clickAudio = new AudioClip(clickSound.toString());
		clickAudio.setVolume(0.3);
		AudioClip flagAudio = new AudioClip(flagSound.toString());
		flagAudio.setVolume(0.3);
		*/
		
		// Works perfectly fine
		Media clickAudio = new Media(ResourceHandler.getInstance().getClass().getResource("sounds/meepmerp.mp3").toString());
		Media flagAudio = new Media(ResourceHandler.getInstance().getClass().getResource("sounds/flag.mp3").toString());
		
		MediaPlayer clickMediaPlayer = new MediaPlayer(clickAudio);
		clickMediaPlayer.setVolume(0.15);
		
		MediaPlayer flagMediaPlayer = new MediaPlayer(flagAudio);
		flagMediaPlayer.setVolume(0.15);
		
		
		
		if (mouseButton == MouseButton.PRIMARY) {
			//clickAudio.play();					// AUDIOCLIP
			clickMediaPlayer.play();				// MEDIAPLAYER
		}
			
		if (mouseButton == MouseButton.SECONDARY) {
			
			this.cell.toggleFlag();
			this.cellButton.toggleFlagView();
			//flagAudio.play();						// AUDIOCLIP
			flagMediaPlayer.play();					// MEDIAPLAYER
			this.cellButton.setOnMouseClicked(new CellButtonEventHandler(this.cellButton));
			Defuser.getInstance().addCurrentMines();
			
		}
		
		Defuser.getInstance().update();
		
	}

}
