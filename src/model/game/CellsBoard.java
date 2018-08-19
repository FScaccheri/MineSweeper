package model.game;

import model.cells.Cell;

public class CellsBoard{

	//Singleton Class
	
	private static CellsBoard instance;
	private Cell[][] cells;
	private int rangeFactor;
	private int range;
	
	public static CellsBoard getInstance() {
		
		if(instance == null)
			instance = new CellsBoard();
		return instance;
	}
	
	public void generate(int difficulty) {
		
		this.rangeFactor = 6;
		this.range = difficulty * this.rangeFactor;
		System.out.println("Range is " + this.range);
		
		// Initialize this.cells
		
		this.cells = new Cell[this.range][this.range];
		
		//Generate empty board
		
		for (int y = 0; y < this.range; y++) {
			
			for(int x = 0; x < this.range; x++) {
				
				Position position = new Position(x, y);
				
				this.cells[x][y] = new Cell(position);
				
			}
		}
		
		/*VERIFICATION ONLY
		boolean OK = true;
		for (int y = 0; y < range; y++) {
			for( int x = 0; x < range; x++) {
				if(cells[x][y] == null)
					OK = false;
			}
		}
		if (OK)
			System.out.println("Board generation: OK");
		*///VERIFICATION ONLY
		
		//Link Cells close to each other
		
		for (int y = 0; y < this.range; y++) {
			
			for (int x = 0; x < this.range; x++) {
				
				for (int j = (y-1); j < (y+2); j++) {
					
					for (int i = (x-1); i < (x+2); i++) {
						
						if ((x != i || y != j) && (j >= 0) && (j < this.range) && (i >= 0) && (i < this.range))
						
							cells[x][y].getCloseCells().add(cells[i][j]);
					}
				}
			}
			
			
		}
		
		//Add mines
		
		Terrorist.getInstance().addMines();
		
		//Count close mines for each Cell
		
		for (int y = 0; y < this.range; y++) {
					
			for (int x = 0; x < this.range; x++) {
						
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

	public boolean verifyWinCondition() {
		
		int hiddenCellsCounter = 0;

		for (int y = 0; y < this.range; y++) {
			for (int x = 0; x < this.range; x++) {
				
				if (!this.cells[x][y].visible())
					hiddenCellsCounter++;
				
			}
		}
		
		return (hiddenCellsCounter == Terrorist.getInstance().totalMines());
		
	}

	
}