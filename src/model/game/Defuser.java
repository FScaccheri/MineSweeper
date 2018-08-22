package model.game;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import model.cells.Cell;
import model.cells.CellsBoard;
import view.CellButtonsBoard;
import view.ResourceHandler;

public class Defuser {
	
	//Singleton class
	
	private static Defuser instance;
	private int difficulty;
	
	public static Defuser getInstance() {
		if(instance == null)
			instance = new Defuser();
		return instance;
	}
	
	public void generateBoard() {
		
		CellsBoard.getInstance().generate(difficulty);
	}

	public void setDifficulty(int difficulty) {
		
		this.difficulty = difficulty;
		
	}
	
	public int difficulty() {
		
		return this.difficulty;
	}
	
	
	public int boardRange() {
		
		return CellsBoard.getInstance().range();
	}
	
	public void endGame(Position position) {
		
		Image mineImg = new Image("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/images/mine.png");
		ImageView mineImgView = new ImageView(mineImg);
		
		int imgSize = 20 + 5 * (3 - this.difficulty);
				
		mineImgView.setFitHeight(imgSize);
		mineImgView.setFitWidth(imgSize);
		CellButtonsBoard.getInstance().getCellButton(position).setGraphic(mineImgView);
		
		ArrayList<AudioClip> explosionAudioClips = new ArrayList<AudioClip>();
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate1.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate2.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate3.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate4.mp3").toString()));
		
		Random random = new Random();
		AudioClip explosion = explosionAudioClips.get(random.nextInt(explosionAudioClips.size()));
		explosion.setVolume(0.5);
		explosion.play();
		
		Alert lossAlert = new Alert(AlertType.WARNING);
		lossAlert.setTitle("BOOM");
		lossAlert.setHeaderText("Perdiste!");
		lossAlert.setContentText("Gracias por jugar");
		lossAlert.showAndWait();
		
		System.exit(0);
		
		
	}

	public void update() {

		CellButtonsBoard buttonsBoard = CellButtonsBoard.getInstance();
		CellsBoard cellsBoard = CellsBoard.getInstance();
		
		for (int y = 0; y < cellsBoard.range(); y++) {
			for(int x = 0; x < cellsBoard.range(); x++) {
				
				Position position = new Position(x,y);
				
				Cell cell = cellsBoard.getCell(position);
				
				if(cell.visible()) 
					
					buttonsBoard.getCellButton(position).reveal();
				
			}
		}
		
		if (cellsBoard.verifyWinCondition()) {
			
			AudioClip win = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/win.mp3").toString());
			win.setVolume(0.3);
			AudioClip victory = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/victory1.mp3").toString());
			victory.setVolume(0.3);
			
			win.play();
			victory.play();
			
			Alert winAlert = new Alert(AlertType.WARNING);
			winAlert.setTitle("Victory");
			winAlert.setHeaderText("Ganaste!");
			winAlert.setContentText("Gracias por jugar");
			winAlert.showAndWait();
			
			System.exit(0);
			
		}
			
		
	}
}
