package model.cells;

import java.util.Collection;
import java.util.LinkedList;

public class Cell {
	
	private int closeMines;
	private Collection<Cell> closeCells;
	private boolean visible;
	private boolean flagged;
	private Mine mine;
	private Position position;

	
	public Cell(Position position) {
		
		this.position = position;
		this.visible = false;
		this.flagged = false;
		this.closeCells = new LinkedList<Cell>();
		this.closeMines = 0;
	}

	public void reveal() {
		
		if(!this.visible) {
			visible = true;

			if(this.hasMine())
				this.mine.explode();
		
			if ( this.countCloseMines() == 0 && !this.hasMine() ){
			
				for(Cell closeCell: closeCells)
					closeCell.reveal();
			}
		}
	}
	
	public int countCloseMines() {

		this.closeMines = 0;
		
		for (Cell closeCell : closeCells) {
			
			if (closeCell.hasMine())
				this.closeMines++;
		}
		
		return this.closeMines;
	}

	public void addMine() {
		
		this.mine = new Mine(this.position);
	}
	
	public boolean hasMine() {
		return (this.mine != null);
	}

	public boolean isFlagged() {
		return this.flagged;
	}

	public int getCloseMines() {
		return this.closeMines;
	}

	public Position getPosition() {
		return this.position;
	}
	
	public Collection<Cell> getCloseCells() {
		return this.closeCells;
	}

	public boolean visible() {
		
		return this.visible;
	}

	public void toggleFlag() {

		this.flagged = !this.flagged;
		
	}

	public void removeMine() {

		this.mine = null;
		
	}

}
