/**
 * Tests the BarContainer class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BarContainerTest {
	
	BarContainer test;

	@Before
	public void setUp() throws Exception {
		test = new BarContainer();
	}

	@Test
	public void testGetArrayOfBars() {
		assertEquals(1, test.getArrayOfBars().get(0).getValue());
		assertEquals(2, test.getArrayOfBars().get(1).getValue());
		assertEquals(3, test.getArrayOfBars().get(2).getValue());
		assertEquals(4, test.getArrayOfBars().get(3).getValue());
		assertEquals(5, test.getArrayOfBars().get(4).getValue());
		assertEquals(6, test.getArrayOfBars().get(5).getValue());
		assertEquals(7, test.getArrayOfBars().get(6).getValue());
		assertEquals(8, test.getArrayOfBars().get(7).getValue());
		assertEquals(9, test.getArrayOfBars().get(8).getValue());
		assertEquals(10, test.getArrayOfBars().get(9).getValue());
		
		assertEquals(140, test.getArrayOfBars().get(0).getXCoordinate());
		assertEquals(240, test.getArrayOfBars().get(1).getXCoordinate());
		assertEquals(340, test.getArrayOfBars().get(2).getXCoordinate());
		assertEquals(440, test.getArrayOfBars().get(3).getXCoordinate());
		assertEquals(540, test.getArrayOfBars().get(4).getXCoordinate());
		assertEquals(640, test.getArrayOfBars().get(5).getXCoordinate());
		assertEquals(740, test.getArrayOfBars().get(6).getXCoordinate());
		assertEquals(840, test.getArrayOfBars().get(7).getXCoordinate());
		assertEquals(940, test.getArrayOfBars().get(8).getXCoordinate());
		assertEquals(1040, test.getArrayOfBars().get(9).getXCoordinate());
		
	}
	
	@Test
	public void testSetArrayOfBars() {
		ArrayList<Bar> array = new ArrayList<Bar>();
		Bar testBar = new Bar(100, 60, 9, Color.black);
		array.add(testBar);
		
		test.setArrayOfBars(array);
		
		assertEquals(100, test.getArrayOfBars().get(0).getXCoordinate());
		assertEquals(60, test.getArrayOfBars().get(0).getWidth());
		assertEquals(9, test.getArrayOfBars().get(0).getValue());
		assertEquals(Color.black, test.getArrayOfBars().get(0).getColor());
		
	}
	
	@Test
	public void testGetArrayOfBarsCopy() {
		assertEquals(1, test.getArrayOfBarsCopy().get(0).getValue());
		assertEquals(2, test.getArrayOfBarsCopy().get(1).getValue());
		assertEquals(3, test.getArrayOfBarsCopy().get(2).getValue());
		assertEquals(4, test.getArrayOfBarsCopy().get(3).getValue());
		assertEquals(5, test.getArrayOfBarsCopy().get(4).getValue());
		assertEquals(6, test.getArrayOfBarsCopy().get(5).getValue());
		assertEquals(7, test.getArrayOfBarsCopy().get(6).getValue());
		assertEquals(8, test.getArrayOfBarsCopy().get(7).getValue());
		assertEquals(9, test.getArrayOfBarsCopy().get(8).getValue());
		assertEquals(10, test.getArrayOfBarsCopy().get(9).getValue());
		
		assertEquals(140, test.getArrayOfBarsCopy().get(0).getXCoordinate());
		assertEquals(240, test.getArrayOfBarsCopy().get(1).getXCoordinate());
		assertEquals(340, test.getArrayOfBarsCopy().get(2).getXCoordinate());
		assertEquals(440, test.getArrayOfBarsCopy().get(3).getXCoordinate());
		assertEquals(540, test.getArrayOfBarsCopy().get(4).getXCoordinate());
		assertEquals(640, test.getArrayOfBarsCopy().get(5).getXCoordinate());
		assertEquals(740, test.getArrayOfBarsCopy().get(6).getXCoordinate());
		assertEquals(840, test.getArrayOfBarsCopy().get(7).getXCoordinate());
		assertEquals(940, test.getArrayOfBarsCopy().get(8).getXCoordinate());
		assertEquals(1040, test.getArrayOfBarsCopy().get(9).getXCoordinate());
		
	}
	
	@Test
	public void testGetJCounter() {
		assertEquals(140, test.getJCounter().getXCoordinate());
	}
	
	@Test
	public void testGetKCounter() {
		assertEquals(140, test.getKCounter().getXCoordinate());
	}
	
	@Test
	public void testRandomizeArrayOfBars() {
		ArrayList<Bar> array = test.getArrayOfBars();
		
		ArrayList<Bar> array2 = new ArrayList<Bar>();
		
		for(int i = 0; i < 10; i++){
			Bar bar = new Bar((140 + i*2*Bar.BAR_WIDTH), Bar.BAR_WIDTH, i+1 , Color.ORANGE);
			array2.add(bar);
		}
		
		for(int i = 0; i < 10; i++){
			assertTrue(array.get(i).equals(array2.get(i)));
		}
		
		test.randomizeArrayOfBars();
		
		for(int i = 0; i < 10; i++){
			assertFalse(array.get(i).equals(array2.get(i)));
		}
		
		
	}
	

}
