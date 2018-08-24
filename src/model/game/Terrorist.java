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
		
		
		//this.diffFactor = 4.9;
		
		random = new Random();
		
		//int range = CellsBoard.getInstance().range();
		int rangeX = Defuser.getInstance().boardRangeX();
		int rangeY = Defuser.getInstance().boardRangeY();


		for (int i = 0; i < minesAmount; ) {
		
			int x = random.nextInt(rangeX);
			int y = random.nextInt(rangeY);
			
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

	public void setTotalMines(int difficulty) {

		switch (difficulty) {
		
		case 1: minesAmount = 10;
				break;
		
		case 2: minesAmount = 40;
				break;
		
		case 3: minesAmount = 99;
				break;
		}
		
	}

	public void replaceMineAt(Position position) {
		
		
		Position newPosition = new Position(position.getX(), position.getY());
		CellsBoard.getInstance().getCell(position).removeMine();
		Random random = new Random();
		
		while (newPosition.isEqualTo(position) || CellsBoard.getInstance().getCell(newPosition).hasMine()) {
			
			int randomX = random.nextInt(CellsBoard.getInstance().rangeX());
			int randomY = random.nextInt(CellsBoard.getInstance().rangeY());
			newPosition.setXY(randomX, randomY);
		
		}
		CellsBoard.getInstance().getCell(newPosition).addMine();
	}

}
