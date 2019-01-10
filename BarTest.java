/**
 * Tests the Bar class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class BarTest {
	
	Bar testBar;

	@Before
	public void setUp() throws Exception {
		this.testBar = new Bar(100, 60, 9, Color.black);
	}

	@Test
	public void testGetXCoordinate() {
		assertEquals(100, testBar.getXCoordinate());
	}

	@Test
	public void testGetYCoordinate() {
		assertEquals(110, testBar.getYCoordinate());
	}
	
	@Test
	public void testGetWidth() {
		assertEquals(60, testBar.getWidth());
	}
	
	@Test
	public void testGetValue() {
		assertEquals(9, testBar.getValue());
	}
	
	@Test
	public void testGetColor() {
		assertEquals(Color.black, testBar.getColor());
	}
	
	@Test
	public void testSetXCoordinate() {
		testBar.setXCoordinate(200);
		assertEquals(200, testBar.getXCoordinate());
	}
	
	@Test
	public void testSetYCoordinate() {
		testBar.setYCoordinate(200);
		assertEquals(200, testBar.getYCoordinate());
	}
	
	@Test
	public void testSetColor() {
		testBar.setColor(Color.pink);
		assertEquals(Color.pink, testBar.getColor());
	}
	
	@Test
	public void testMoveBarDown() {
		testBar.moveBarDown();
		assertEquals(120, testBar.getYCoordinate());
	}
	
	@Test
	public void testMoveBarUp() {
		testBar.moveBarUp();
		assertEquals(100, testBar.getYCoordinate());
	}
	
	@Test
	public void testMoveBarLeft() {
		testBar.moveBarLeft();
		assertEquals(90, testBar.getXCoordinate());
	}
	
	@Test
	public void testMoveBarRight() {
		testBar.moveBarRight();
		assertEquals(110, testBar.getXCoordinate());
	}
	
	@Test
	public void testEquals() {
		Bar equalBar = new Bar(100, 60, 9, Color.black);
		assertTrue(testBar.equals(equalBar));
		
		Bar notEqualBar = new Bar(10, 60, 9, Color.black);
		assertFalse(testBar.equals(notEqualBar));
		
		Bar notEqualBar2 = new Bar(100, 60, 1, Color.black);
		assertFalse(testBar.equals(notEqualBar2));
	}
}
