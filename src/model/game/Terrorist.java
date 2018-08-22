package model.game;

import java.util.Random;

import model.cells.CellsBoard;


public class Terrorist {
	
	//Singleton Class
	
	private Random random;
	private double diffFactor;
	private int minesAmount;
	private static Terrorist instance;
		
	public static Terrorist getInstance() {
			
		if(instance == null)
			instance = new Terrorist();
		
		return instance;
	}

	public void addMines() {
		
		this.diffFactor = 4.9;
		
		random = new Random();
		
		int range = CellsBoard.getInstance().range();
		
		this.minesAmount = (int)((Math.pow(Defuser.getInstance().boardRange(), 2)) / this.diffFactor);

		// I add only 1 mine for testing
		for (int i = 0; i < minesAmount; ) {
		
			int x = random.nextInt(range);
			int y = random.nextInt(range);
			
			Position position = new Position (x, y);
			
			if (!CellsBoard.getInstance().getCell(position).hasMine()) {
				
				CellsBoard.getInstance().getCell(position).addMine();
				i++;
			}
		}
	}
	
	public int totalMines() {
		
		return this.minesAmount;
	}

}
