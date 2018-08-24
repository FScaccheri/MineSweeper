package model.cells;

import model.game.Position;
import model.game.Terrorist;

public class CellsBoard{

	//Singleton Class
	
	private static CellsBoard instance;
	private Cell[][] cells;
	private int rangeFactor;
	private int range;
	private int rangeX;
	private int rangeY;
	
	public static CellsBoard getInstance() {
		
		if(instance == null)
			instance = new CellsBoard();
		return instance;
	}
	
	public void generate(int difficulty) {
		
		this.rangeFactor = 7; // I set it up here because I can't make a constructor
		this.range = difficulty * this.rangeFactor;
		
		switch (difficulty) {
			
		case 1: rangeX = 9;
				rangeY = 9;
				break;
				
		case 2: rangeX = 16;
				rangeY = 16;
				break;
				
		case 3: rangeX = 30;
				rangeY = 16;
				break;
		
		}
		
		// Initialize this.cells
		
		//this.cells = new Cell[this.range][this.range];
		this.cells = new Cell[this.rangeX][this.rangeY];
		
		//Generate empty board
		
		for (int y = 0; y < this.rangeY; y++) {
			
			for(int x = 0; x < this.rangeX; x++) {
				
				Position position = new Position(x, y);
				
				this.cells[x][y] = new Cell(position);
				
			}
		}
		
		//Link Cells close to each other
		
		for (int y = 0; y < this.rangeY; y++) {
			
			for (int x = 0; x < this.rangeX; x++) {
				
				for (int j = (y-1); j < (y+2); j++) {
					
					for (int i = (x-1); i < (x+2); i++) {
						
						if ((x != i || y != j) && (j >= 0) && (j < this.rangeY) && (i >= 0) && (i < this.rangeX))
						
							cells[x][y].getCloseCells().add(cells[i][j]);
					}
				}
			}
			
			
		}
		
		//Add mines
		
		Terrorist.getInstance().addMines();
		
		//Count close mines for each Cell
		
		for (int y = 0; y < this.rangeY; y++) {
					
			for (int x = 0; x < this.rangeX; x++) {
						
				this.cells[x][y].countCloseMines();
			}
		}
	}


	public Cell getCell(Position position) {
		
		
		int x = position.getX();
		int y = position.getY();
		
		return (this.cells[x][y]);

	}
	
	
	public int range() {

		return this.range;
	}
	
	public int rangeX() {
		
		return this.rangeX;
	}
	
	public int rangeY() {
		
		return this.rangeY;
	}

	public boolean verifyWinCondition() {
		
		int hiddenCellsCounter = 0;

		for (int y = 0; y < this.rangeY; y++) {
			for (int x = 0; x < this.rangeX; x++) {
				
				if (!this.cells[x][y].visible())
					hiddenCellsCounter++;
				
			}
		}
		
		return (hiddenCellsCounter == Terrorist.getInstance().totalMines());
		
	}

	
}