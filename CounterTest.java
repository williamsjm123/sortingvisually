/**
 * Tests the Counter class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CounterTest {

	Counter testCounter;
	
	@Before
	public void setUp() throws Exception {
		this.testCounter = new Counter(10, 20 , Color.BLACK, "Test");
		
	}

	@Test
	public void testGetXCoordinate() {
		assertEquals(10, testCounter.getXCoordinate());
	}
	
	@Test
	public void testSetXCoordinate() {
		this.testCounter.setXCoordinate(50);
		assertEquals(50, testCounter.getXCoordinate());
	}

}
