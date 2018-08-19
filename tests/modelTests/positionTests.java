package modelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.game.Position;

public class positionTests {
	
	@Test
	public void testPositionsWithEqualCoordinatesAreEqual() {
		
		Position onePosition = new Position(1,2);
		Position otherPosition = new Position(1,2);
		
		assertTrue(onePosition.isEqualTo(otherPosition));
	}

}
