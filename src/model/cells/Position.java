package model.cells;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int newX, int newY) {
		
		this.x = newX;
		this.y = newY;
	}

	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public void setX(int value) {
		
		this.x = value;
	}

	public void setY(int value) {
		
		this.y = value;
	}
	
	public void setXY(int newX, int newY) {
		
		this.x = newX;
		this.y = newY;
	}

	public boolean isEqualTo(Position otherPosition) {
		
		return (this.x == otherPosition.getX() && this.y == otherPosition.getY());
	}
}
