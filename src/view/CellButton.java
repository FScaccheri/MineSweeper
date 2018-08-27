package view;

import handlers.CellButtonEventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.cells.Cell;
import model.cells.Position;
import model.game.CellsBoard;
import model.game.Defuser;

public class CellButton extends Button{
	
	private Position position;
	private int mines;
	private int cellSize;
	private int fontSize;
	private int imgSize;

	public CellButton(Position position, int mines) {

		//super(position.getX() + ", " + position.getY());
		//super(Integer.toString(mines));
		
		this.mines = mines;
		this.position = position;		
		int difficulty = Defuser.getInstance().difficulty();
		
		switch (difficulty) {
				
				case 1: this.cellSize = 60;
						this.fontSize = 245;
						this.imgSize = 30;
						break;
						
				case 2: this.cellSize = 35;
						this.fontSize = 150;
						this.imgSize = 18;
						break;
						
				case 3: this.cellSize = 35;
						this.fontSize = 150;
						this.imgSize = 18;
						break;
		}
		
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
			
			flagImgView.setFitHeight(this.imgSize);
			flagImgView.setFitWidth(this.imgSize);
			this.setGraphic(flagImgView);
			
		}
		
		else this.setGraphic(null);
		
	}

	public void reveal() {
		
		String fontSize = "-fx-font-size: " + this.fontSize + "%; ";
		String textFill = null;
		
		switch (mines) {
		
		case 1: textFill = "-fx-text-fill: #2661e8;";
				break;
		
		case 2: textFill = "-fx-text-fill: #006600;";
				break;
		
		case 3: textFill = "-fx-text-fill: #E50000;";
				break;
				
		case 4: textFill = "-fx-text-fill: #00003D;";
				break;
				
		case 5: textFill = "-fx-text-fill: #990000;";
				break;
				
		case 6: textFill = "-fx-text-fill: #005151;";
				break;
				
		case 7: textFill = "-fx-text-fill: #191919;";
				break;
				
		case 8: textFill = "-fx-text-fill: #757575;";
				break;
		
		
		}
		if (this.mines != 0) {
			
			this.setText(Integer.toString(this.mines));
			this.setStyle(fontSize + textFill);
		}
		this.setDisable(true);
	}

	public void setMineImage() {
		
		Image mineImg = new Image(ResourceHandler.getInstance().getClass().getResource("images/mine.png").toString());
		ImageView mineImgView = new ImageView(mineImg);
		
		mineImgView.setFitHeight(this.imgSize);
		mineImgView.setFitWidth(this.imgSize);
		
		this.setGraphic(mineImgView);
		
	}
	
	public void setMines(int mines) {
		
		this.mines = mines;
	}

}
