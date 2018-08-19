package view;

import java.util.ArrayList;
import java.util.Collection;

import handlers.EasyButtonEventHandler;
import handlers.HardButtonEventHandler;
import handlers.MediumButtonEventHandler;
import handlers.StartButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends VBox{
	
	private HBox buttonContainer;
	private Button startButton;
	private Button easyDiffButton;
	private Button medDiffButton;
	private Button hardDiffButton;

	public MainMenu(Stage stage) {
		
		this.buttonContainer = new HBox();
		this.buttonContainer.setSpacing(20);
		this.buttonContainer.setAlignment(Pos.CENTER);
		this.getChildren().add(buttonContainer);
		this.setButtons();
		this.setHandlers(stage);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.buttonContainer.setPadding(new Insets(30));
		
		Image logo = new Image("file:src/view/images/minesweeper.png");
		BackgroundImage background = new BackgroundImage(logo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(background));
		
		/*
		AudioClip mainMenuAudio = new AudioClip("file:src/view/sounds/mainmenu2.mp3");
		mainMenuAudio.setVolume(0.2);
		mainMenuAudio.setCycleCount(AudioClip.INDEFINITE);
		mainMenuAudio.play();
		*/
		// DOESNT LOOP, MAKE IT LOOP
	}
	
	private void setButtons() {

		this.startButton = new Button("Start!");
		this.startButton.setPrefSize(100, 50);
		this.startButton.setFont(Font.font("Arial", FontWeight.BOLD , 15));
		this.buttonContainer.getChildren().add(startButton);

		
		this.easyDiffButton = new Button("Facil");
		this.easyDiffButton.setPrefSize(80, 40);
		this.easyDiffButton.setFont(Font.font("Arial", FontWeight.BOLD , 12));
		
		this.medDiffButton = new Button ("Medio");
		this.medDiffButton.setPrefSize(80, 40);
		this.medDiffButton.setFont(Font.font("Arial", FontWeight.BOLD , 12));
		
		this.hardDiffButton = new Button ("Dificil");
		this.hardDiffButton.setPrefSize(80, 40);
		this.hardDiffButton.setFont(Font.font("Arial", FontWeight.BOLD , 12));
		
		
	}

	private ArrayList<AudioClip> setupAudioClips() {
		
		ArrayList collection = new ArrayList<AudioClip>();
		collection.add(new AudioClip("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/sounds/setup1.mp3"));
		collection.add(new AudioClip("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/sounds/setup2.mp3"));
		collection.add(new AudioClip("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/sounds/setup3.mp3"));
		
		return collection;
	}

	private void setHandlers(Stage stage) {
		
		
		StartButtonEventHandler startEventHandler = new StartButtonEventHandler (this);
		this.startButton.setOnAction(startEventHandler);
		
		ArrayList<AudioClip> audioClips = this.setupAudioClips();
		
		EasyButtonEventHandler easyEventHandler = new EasyButtonEventHandler(stage, this, this.easyDiffButton, audioClips);
		this.easyDiffButton.setOnAction(easyEventHandler);
		
		MediumButtonEventHandler medEventHandler = new MediumButtonEventHandler(stage, this, this.medDiffButton, audioClips);
		this.medDiffButton.setOnAction(medEventHandler);
		
		HardButtonEventHandler hardEventHandler = new HardButtonEventHandler(stage, this, this.hardDiffButton, audioClips);
		this.hardDiffButton.setOnAction(hardEventHandler);
		
	}
	
	public void changeButtons() {
		
		this.buttonContainer.getChildren().remove(0);
		
		this.buttonContainer.getChildren().addAll(easyDiffButton, medDiffButton, hardDiffButton);
		
	}

	public Scene creteGameScene() {
		
		Scene gameScene = new Scene(new GamePane(), 1200, 800);
		return gameScene;
	}
	
	
}
