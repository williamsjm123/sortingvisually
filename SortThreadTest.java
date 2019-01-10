/**
 * Tests the SortThread class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class SortThreadTest {
	
	AnimatorGUI a;
	SortThread s;

	@Before
	public void setUp() throws Exception {
		a = new AnimatorGUI();
		s = new SortThread(a.barContainer, a.animationController, AnimationController.SELECTION_SORT, AnimationController.NORMAL, a);
	}

	@Test
	public void testHighlightPanel() {
		JPanel p = a.getPseudoPanel1();
		assertNotEquals(Color.YELLOW, p.getBackground());
		
		s.highlightPanel(p);
		assertEquals(Color.YELLOW, p.getBackground());
	}
	
	@Test
	public void testUnHighlightPanel() {
		JPanel p = a.getPseudoPanel1();
		assertNotEquals(Color.YELLOW, p.getBackground());
		
		s.highlightPanel(p);
		assertEquals(Color.YELLOW, p.getBackground());
		
		s.unHighlightPanel(p);
		assertEquals(Color.LIGHT_GRAY, p.getBackground());
	}

}
