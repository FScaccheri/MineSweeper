package model.game;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.cells.Cell;
import model.cells.Position;
import view.CellButtonsBoard;
import view.GamePane;
import view.MainMenu;
import view.ResourceHandler;

public class Defuser {
	
	//Singleton class
	
	private static Defuser instance;
	private int difficulty;
	private Stage stage;
	private int currentMines;
	private GamePane gamePane;
	private boolean firstClick;
	
	public static Defuser getInstance() {
		if(instance == null)
			instance = new Defuser();
		return instance;
	}
	
	public void generateBoard() {
		
		this.firstClick = true;
		Terrorist.getInstance().setTotalMines(difficulty);
		CellsBoard.getInstance().generate(difficulty);
		this.currentMines = Terrorist.getInstance().totalMines();
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
	
	public int boardRangeX() {
		
		return CellsBoard.getInstance().rangeX();
	}
	
	public int boardRangeY() {
		
		return CellsBoard.getInstance().rangeY();
	}
	
	public void endGame(Position position) {
		
		CellButtonsBoard.getInstance().getCellButton(position).setMineImage();
		
		ArrayList<AudioClip> explosionAudioClips = new ArrayList<AudioClip>();
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate1.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate2.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate3.mp3").toString()));
		explosionAudioClips.add(new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/detonate4.mp3").toString()));
		
		Random random = new Random();
		AudioClip explosion = explosionAudioClips.get(random.nextInt(explosionAudioClips.size()));
		explosion.setVolume(0.5);
		explosion.play();
		
		Alert lossAlert = new Alert(AlertType.CONFIRMATION);
		lossAlert.setTitle("Defeat...");
		lossAlert.setHeaderText("You lost!");
		lossAlert.setContentText("Retry?");
		
		lossAlert.getButtonTypes().remove(ButtonType.OK);
		lossAlert.getButtonTypes().remove(ButtonType.CANCEL);
		ButtonType retryButton = new ButtonType("Retry", ButtonBar.ButtonData.OK_DONE);
		lossAlert.getButtonTypes().add(retryButton);
		lossAlert.getButtonTypes().add(ButtonType.CLOSE);
		
		
		
		Optional<ButtonType> result = lossAlert.showAndWait();
		if(result.isPresent() && result.get() == retryButton) {
			
			this.stage.setScene(new Scene(new GamePane(), 1200, 700));
		}
		
		if(result.isPresent() && result.get() == ButtonType.CLOSE) {
			
			System.exit(0);
		}		
		
	}

	public void update() {

		CellButtonsBoard buttonsBoard = CellButtonsBoard.getInstance();
		CellsBoard cellsBoard = CellsBoard.getInstance();
		
		for (int y = 0; y < cellsBoard.rangeY(); y++) {
			for(int x = 0; x < cellsBoard.rangeX(); x++) {
				
				Position position = new Position(x,y);
				
				Cell cell = cellsBoard.getCell(position);
				
				CellButtonsBoard.getInstance().getCellButton(position).setMines(cell.countCloseMines());
				
				if (cell.visible()) 
					
					buttonsBoard.getCellButton(position).reveal();
				
			}
		}
		
		this.gamePane.updateMines();
		
		if (cellsBoard.verifyWinCondition()) {
			
			AudioClip win = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/win.mp3").toString());
			win.setVolume(0.2);
			AudioClip victory = new AudioClip(ResourceHandler.getInstance().getClass().getResource("sounds/victory1.mp3").toString());
			victory.setVolume(0.2);
			
			win.play();
			victory.play();
			
			Alert winAlert = new Alert(AlertType.CONFIRMATION);
			winAlert.setTitle("Victory!");
			winAlert.setHeaderText("You won!");
			winAlert.setContentText("Thanks for playing");
			
			winAlert.getButtonTypes().remove(ButtonType.OK);
			winAlert.getButtonTypes().remove(ButtonType.CANCEL);
			ButtonType retryButton = new ButtonType("Retry", ButtonBar.ButtonData.OK_DONE);
			winAlert.getButtonTypes().add(retryButton);
			winAlert.getButtonTypes().add(ButtonType.CLOSE);
			
			Optional<ButtonType> result = winAlert.showAndWait();
			if(result.isPresent() && result.get() == retryButton) {
				
				this.stage.setScene(new Scene(new GamePane(), 1500, 900));
			}
			
			if(result.isPresent() && result.get() == ButtonType.CLOSE) {
				
				System.exit(0);
			}
			
			
			
		}
			
		
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
	
	public void addCurrentMines() {

		this.currentMines++;
		
	}

	public void substractCurrentMines() {
		
		this.currentMines--;
		
	}

	public int currentMines() {
		
		return this.currentMines;
	}

	public void setGamePane(GamePane gamePane) {

		this.gamePane = gamePane;
		
	}
	

	public boolean isFirstClick() {
		
		return this.firstClick;
	}
	
	public void firstClickDone() {
		
		this.firstClick = false;
	}

	
}
