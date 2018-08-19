package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import model.cells.Cell;
import view.CellButton;

public class RightClickOnFlagEventHandler implements EventHandler<MouseEvent>{

	private Cell cell;
	private CellButton cellButton;

	public RightClickOnFlagEventHandler(Cell cell, CellButton cellButton) {

		this.cell = cell;
		this.cellButton = cellButton;
	}

	public void handle(MouseEvent event) {
		
		MouseButton mouseButton = event.getButton();
		AudioClip clickAudio = new AudioClip("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/sounds/meepmerp.mp3");
		clickAudio.setVolume(0.3);
		AudioClip flagAudio = new AudioClip("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/sounds/flag.mp3");
		flagAudio.setVolume(0.3);
		
		if (mouseButton == MouseButton.PRIMARY) {
			clickAudio.play();
		}
			
		if (mouseButton == MouseButton.SECONDARY) {
			
			this.cell.toggleFlag();
			this.cellButton.toggleFlagView();
			flagAudio.play();
			this.cellButton.setOnMouseClicked(new CellButtonEventHandler(this.cellButton));
			
		}
		
	}

}
