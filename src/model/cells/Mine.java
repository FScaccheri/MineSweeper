package model.cells;

import model.game.Defuser;

public class Mine{
	
	private Position position;
	
	public Mine(Position position) {
		
		this.position = position;
	}

	public void explode() {
		Defuser.getInstance().endGame(this.position);
	}

}
