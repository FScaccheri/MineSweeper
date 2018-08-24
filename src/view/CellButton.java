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
			
			Image flagImg = new Image (ResourceHandler.getInstance().getClass().getResource("images/redflag.png").toString());
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
		
		String fontSize = "-fx-font-size: " + size + "%; ";
		String textFill = null;
		
		switch (mines) {
		
		case 1: textFill = "-fx-text-fill: #2661e8";
				break;
		
		case 2: textFill = "-fx-text-fill: #006600";
				break;
		
		case 3: textFill = "-fx-text-fill: #E50000";
				break;
				
		case 4: textFill = "-fx-text-fill: #00003D";
				break;
				
		case 5: textFill = "-fx-text-fill: #990000";
				break;
				
		case 6: textFill = "-fx-text-fill: #005151";
				break;
				
		case 7: textFill = "-fx-text-fill: #191919";
				break;
				
		case 8: textFill = "-fx-text-fill: #757575";
				break;
		
		
		}
		if (this.mines != 0) {
			
			this.setText(Integer.toString(this.mines));
			this.setStyle(fontSize + textFill);
		}
		this.setDisable(true);
	}

}
