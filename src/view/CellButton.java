package view;

import handlers.CellButtonEventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.cells.Cell;
import model.cells.CellsBoard;
import model.game.Defuser;
import model.game.Position;

public class CellButton extends Button{
	
	private Position position;
	private int mines;
	private int cellSize;

	public CellButton(Position position, int mines) {

		//super(position.getX() + ", " + position.getY());
		//super(Integer.toString(mines));
		
		this.mines = mines;
		this.position = position;
		this.cellSize = 40 + 10 * (3 - Defuser.getInstance().difficulty());
		this.setPrefSize(cellSize, cellSize);
		
		this.setOnMouseClicked(new CellButtonEventHandler(this));
	}
	
	public Position getPosition() {
		
		return this.position;
	}

	public void toggleFlagView() {

		Cell cell = CellsBoard.getInstance().getCell(this.position);
		if (cell.isFlagged()) {	
			
			Image flagImg = new Image ("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/images/redflag.png");
			ImageView flagImgView = new ImageView(flagImg);
			
			int imgSize = 20 + 5 * (3 - Defuser.getInstance().difficulty());
			
			flagImgView.setFitHeight(imgSize);
			flagImgView.setFitWidth(imgSize);
			this.setGraphic(flagImgView);
			
		}
		
		else this.setGraphic(null);
		
	}

	public void reveal() {
		
		int size = 175 +  35 * (3 - Defuser.getInstance().difficulty());
		
		if (this.mines != 0) {
			
			this.setText(Integer.toString(this.mines));
			this.setStyle("-fx-font-size: " + size + "%; -fx-text-fill: #2661e8");
		}
		this.setDisable(true);
	}

}
