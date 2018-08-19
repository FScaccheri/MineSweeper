package view;

import java.util.Collection;

import handlers.CellButtonEventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.cells.Cell;
import model.game.CellsBoard;
import model.game.Position;

public class CellButton extends Button{
	
	private Position position;
	private Image flagImage;
	private int mines;

	public CellButton(Position position, int mines) {

		//super(position.getX() + ", " + position.getY());
		//super(Integer.toString(mines));
		
		this.mines = mines;
		this.position = position;
		this.setPrefSize(50, 50);
		
		this.flagImage = new Image ("file:///c:/Users/Franco/proyectos/MineSweeper/src/view/images/flag.png");
		
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
			flagImgView.setFitHeight(30);
			flagImgView.setFitWidth(30);
			this.setGraphic(flagImgView);
			
		}
		
		else this.setGraphic(null);
		
	}

	public void reveal() {
		
		if (this.mines != 0) {
			
			this.setText(Integer.toString(this.mines));
			this.setStyle("-fx-font-size: 200%; -fx-text-fill: #2661e8");
		}
		this.setDisable(true);
	}

}
