package view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.cells.CellsBoard;
import model.game.Defuser;
import model.game.Position;

public class GamePane extends BorderPane{
	
	private Defuser defuser;
	private CellButtonsBoard buttonsBoard;
	private VBox bigContainer;
	private HBox topContainer;

	public GamePane(){
		
		this.defuser = Defuser.getInstance();
		defuser.generateBoard();
		
		this.buttonsBoard = CellButtonsBoard.getInstance();
		
		this.setPrefSize(500, 500);
		
		this.bigContainer = new VBox();
		this.bigContainer.setAlignment(Pos.CENTER);
		this.setCenter(bigContainer);

		this.topContainer = new HBox();
		this.topContainer.setAlignment(Pos.CENTER);
		
		this.setBoardView();
		
		
		//Test Button (use for reference)
		/*
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);
		Button button = new Button("Test");
		button.setPrefSize(80, 40);
		button.setFont(new Font("Arial", 12));
		
		container.getChildren().add(button);
		
		this.setCenter(container);		
		*/
	}

	public void setBoardView() {

		this.buttonsBoard.setRange();
		
		for (int y = 0; y < defuser.boardRange(); y++) {
			
			HBox smallContainer = new HBox();
			smallContainer.setAlignment(Pos.CENTER);
			this.bigContainer.getChildren().add(smallContainer);
			
			for (int x = 0; x < defuser.boardRange(); x++) {
				
				Position position = new Position(x,y);
				int mines = CellsBoard.getInstance().getCell(position).getCloseMines();
				
				CellButton cellView = new CellButton(position, mines);
				
				this.buttonsBoard.addCellButton(cellView);
				smallContainer.getChildren().add(cellView);
				
			}
		}
		
	}

	
}
