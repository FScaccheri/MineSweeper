package model.game;

import java.util.Random;


public class Terrorist {
	
	//Singleton Class
	
	private Random random;
	private int diffFactor;
	private static Terrorist instance;
		
	public static Terrorist getInstance() {
			
		if(instance == null)
			instance = new Terrorist();
		
		return instance;
	}

	public void addMines() {
		
		this.diffFactor = 5;
		
		random = new Random();
		
		int range = CellsBoard.getInstance().range();
		
		int minesAmount = Defuser.getInstance().difficulty() * this.diffFactor;
		System.out.println("Mines: " + minesAmount);

		// I add only 1 mine for testing
		for (int i = 0; i < minesAmount; ) {
		
			int x = random.nextInt(range);
			int y = random.nextInt(range);
			
			Position position = new Position (x, y);
			
			if (!CellsBoard.getInstance().getCell(position).hasMine()) {
				System.out.println(x + ", " + y);
				CellsBoard.getInstance().getCell(position).addMine();
				i++;
			} else {System.out.println("Repetido");} // DELETE LATER
		}
	}
	
	public int totalMines() {
		
		return this.diffFactor * Defuser.getInstance().difficulty();
	}

}
