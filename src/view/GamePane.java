package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.cells.CellsBoard;
import model.game.Defuser;
import model.game.Position;

public class GamePane extends BorderPane{
	
	private Defuser defuser;
	private CellButtonsBoard buttonsBoard;
	private VBox bigContainer;
	private Canvas canvas;

	public GamePane(){
		
		this.defuser = Defuser.getInstance();
		defuser.setGamePane(this);
		defuser.generateBoard();
		
		this.buttonsBoard = CellButtonsBoard.getInstance();
		
		this.setPrefSize(500, 500);
		
		this.setCanvas();
		this.setBoardView();
		
	}

	private void setCanvas() {
		
		this.canvas = new Canvas(2000, 2000);
		this.canvas.getGraphicsContext2D().setFill(Color.web("#002448"));
		this.canvas.getGraphicsContext2D().setFill(Color.web("#000A15"));
		this.canvas.getGraphicsContext2D().setFill(Color.web("#323232"));
		this.canvas.getGraphicsContext2D().fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		
		this.getChildren().add(this.canvas);
		
	}

	private void setMinesBox() {
		
		StackPane pointsPane = new StackPane();
		pointsPane.setAlignment(Pos.CENTER);
		pointsPane.setPadding(new Insets(0, 0, 30, 0));
		Rectangle rect = new Rectangle(200, 60, Color.ANTIQUEWHITE);
		int mines = Defuser.getInstance().currentMines();
		Text text = new Text("Mines: " + Integer.toString(mines));
		
		// All JavaFX Fonts
		//System.out.println(javafx.scene.text.Font.getFamilies().toString());
		
		text.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, null, 25));
		pointsPane.getChildren().addAll(rect, text);
		
		this.bigContainer.getChildren().add(pointsPane);
		
	}

	public void setBoardView() {
		
		
		this.bigContainer = new VBox();
		this.bigContainer.setAlignment(Pos.CENTER);		
		this.setCenter(bigContainer);
		
		this.setMinesBox();

		this.buttonsBoard.setRange();
		
		for (int y = 0; y < defuser.boardRangeY(); y++) {
			
			HBox smallContainer = new HBox();
			smallContainer.setAlignment(Pos.CENTER);
			this.bigContainer.getChildren().add(smallContainer);
			
			for (int x = 0; x < defuser.boardRangeX(); x++) {
				
				Position position = new Position(x,y);
				int mines = CellsBoard.getInstance().getCell(position).getCloseMines();
				
				CellButton cellView = new CellButton(position, mines);
				
				this.buttonsBoard.addCellButton(cellView);
				smallContainer.getChildren().add(cellView);
				
			}
		}
		
	}
	
	public void updateMines() {
		
		StackPane pointsPane = (StackPane)this.bigContainer.getChildren().get(0);
		pointsPane.setAlignment(Pos.CENTER);
		pointsPane.setPadding(new Insets(0, 0, 30, 0));
		Rectangle rect = new Rectangle(200, 60, Color.ANTIQUEWHITE);
		int mines = Defuser.getInstance().currentMines();
		Text text = new Text("Mines: " + Integer.toString(mines));
		text.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, null, 25));
		
		pointsPane.getChildren().addAll(rect, text);
		
		
		
	}

	
}
