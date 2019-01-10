/**
 * Tests the AnimationController class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnimationControllerTest {

	AnimatorGUI animatorGUI;
	AnimationController aC;
	
	@Before
	public void setUp() throws Exception {
		animatorGUI = new AnimatorGUI();
		aC = animatorGUI.animationController;
	}

	@Test
	public void testPlay() {
		assertFalse(aC.isAnimating());
		assertTrue(aC.isPaused());
		aC.play();
		assertTrue(aC.isAnimating());
		assertFalse(aC.isPaused());
	}
	
	@Test
	public void testPause() {
		assertFalse(aC.isAnimating());
		assertTrue(aC.isPaused());
		aC.play();
		assertTrue(aC.isAnimating());
		assertFalse(aC.isPaused());
		aC.pause();
		assertTrue(aC.isAnimating());
		assertTrue(aC.isPaused());
	}
	
	@Test
	public void testReset() {
		assertFalse(aC.isAnimating());
		assertTrue(aC.isPaused());
		animatorGUI.barContainer.randomizeArrayOfBars();
		aC.play();
		assertTrue(aC.isAnimating());
		assertFalse(aC.isPaused());
		aC.reset();
		assertFalse(aC.isAnimating());
		assertTrue(aC.isPaused());
		assertFalse(aC.isTerminated());
		
		for(int i = 0; i < 10; i++){
			assertTrue(animatorGUI.barContainer.getArrayOfBarsCopy().get(i).equals(animatorGUI.barContainer.getArrayOfBars().get(i)));
		}
	}
	
	@Test
	public void testGetCurrentlySelectedAlgorithm() {
		assertEquals(0, aC.getCurrentlySelectedAlgorithm());
	}
	
	@Test
	public void testSetCurrentlySelectedAlgorithm() {
		aC.setCurrentlySelectedAlgorithm(1);
		assertEquals(1, aC.getCurrentlySelectedAlgorithm());
	}
	
	@Test
	public void testGetCurrentSpeedMultiplier() {
		assertTrue(Double.compare(1.0, aC.getCurrentSpeedMultiplier()) == 0);
	}
	
	@Test
	public void testSetCurrentSpeedMultiplier() {
		aC.setCurrentSpeedMultiplier(1.5);
		assertTrue(Double.compare(1.5, aC.getCurrentSpeedMultiplier()) == 0);
	}
	
	@Test
	public void testIsPaused() {
		assertTrue(aC.isPaused());
		aC.play();
		assertFalse(aC.isPaused());
	}
	
	@Test
	public void testSetPaused() {
		assertTrue(aC.isPaused());
		aC.setPaused(false);
		assertFalse(aC.isPaused());
	}
	
	@Test
	public void testSetAnimating() {
		assertFalse(aC.isAnimating());
		aC.setAnimating(true);
		assertTrue(aC.isAnimating());
	}
	
	@Test
	public void testIsTerminated() {
		assertFalse(aC.isTerminated());
	}
	
	@Test
	public void testSetTerminated() {
		assertFalse(aC.isTerminated());
		aC.setTerminated(true);
		assertTrue(aC.isTerminated());
	}
	
	
	

}
