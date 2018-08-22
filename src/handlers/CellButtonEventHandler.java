package handlers;



import javafx.event.EventHandler;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import model.cells.Cell;
import model.cells.CellsBoard;
import model.game.Defuser;
import model.game.Position;
import view.CellButton;
import view.ResourceHandler;

public class CellButtonEventHandler implements EventHandler<MouseEvent>{

	private CellButton cellButton;
	
	public CellButtonEventHandler(CellButton cellButton) {
		
		this.cellButton = cellButton;
		
	}
	
	
	public void handle(MouseEvent event) {

		Position position = cellButton.getPosition();
		Cell cell = CellsBoard.getInstance().getCell(position);
		MouseButton mouseButton = event.getButton();

		AudioClip clickAudio = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/click.mp3").toString());
		clickAudio.setVolume(0.3);
		AudioClip flagAudio = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/flag.mp3").toString());
		flagAudio.setVolume(0.3);
		
		if (mouseButton == MouseButton.PRIMARY) {
	
			this.cellButton.setDisable(true);	
			clickAudio.play();
			cell.reveal();
			Defuser.getInstance().update();	
			
		}
		
		if (mouseButton == MouseButton.SECONDARY) {
			
			cell.toggleFlag();
			this.cellButton.toggleFlagView();
			flagAudio.play();
			this.cellButton.setOnMouseClicked(new RightClickOnFlagEventHandler(cell, this.cellButton));
		}	

		
	}

		
}

