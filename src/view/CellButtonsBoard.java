package view;


import model.cells.CellsBoard;
import model.game.Defuser;
import model.game.Position;

public class CellButtonsBoard{
	
	private static CellButtonsBoard instance;
	private CellButton[][] cellButtons;
	private int rangeX;
	private int rangeY;

	public static CellButtonsBoard getInstance() {
		
		if(instance == null) 
			instance = new CellButtonsBoard();
		return instance;
	}


	public CellButton getCellButton(Position position) {
		int x = position.getX();
		int y = position.getY();
		
		return this.cellButtons[x][y];
	}

	public void addCellButton(CellButton cellView) {
		
		int x = cellView.getPosition().getX();
		int y = cellView.getPosition().getY();
		
		this.cellButtons[x][y] = cellView;
		
	}

	public void setRange() {

		//int range = Defuser.getInstance().boardRange();
		this.rangeX = Defuser.getInstance().boardRangeX();
		this.rangeY = Defuser.getInstance().boardRangeY();
		this.cellButtons = new CellButton[rangeX][rangeY];
		
	}

	
	
}
