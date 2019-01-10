/**
 * The SortThread class represents a thread that is set up when a new animation is started.
 * This thread runs to avoid blocking the event dispatch thread.
 * It contains functionality to display Selection, Insertion and Bubble sort animations.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SortThread implements Runnable{
	
	// objects to be used by the newly set up thread
	private BarContainer barContainer;
	private AnimationController animationController;
	private AnimatorGUI animatorGUI;
	
	// algorithm animation type to be run
	private int currentlySelectedAlgorithm;
	
	// speed at which to run the animation
	private double currentSpeedMultiplier;
	
	/**
	 * Constructor for a SortThread object.
	 * @param barContainer The BarContainer object set up by the AnimatorGUI class.
	 * @param animationController The AnimationController object set up by the AnimatorGUI class.
	 * @param currentlySelectedAlgorithm The currently selected algorithm chosen by the user.
	 * @param currentSpeedMultiplier The currently selected animation speed chosen by the user.
	 * @param animatorGUI The AnimatorGUI object itself.
	 */
	public SortThread(BarContainer barContainer, AnimationController animationController, int currentlySelectedAlgorithm, double currentSpeedMultiplier, AnimatorGUI animatorGUI){
		this.barContainer = barContainer;
		this.animationController = animationController;
		this.currentlySelectedAlgorithm = currentlySelectedAlgorithm;
		this.currentSpeedMultiplier = currentSpeedMultiplier;
		this.animatorGUI = animatorGUI;
	}

	/**
	 * The method called when the thread is started.
	 * Selects which pseudocode to display and animation to run based on user selection of algorithm.
	 */
	@Override
	public void run() {
		
		switch(this.currentlySelectedAlgorithm){
		
		case 0: this.animatorGUI.getPseudoCode1().setText("<html>SELECTION SORT PSEUDOCODE<br><br>"
				+ "for (array length - 1 times)"
				+ "<br> &emsp { set the minimum as first unsorted element</html>");
				this.animatorGUI.getPseudoCode2().setText("<html> &emsp for (all unsorted elements)"
				+ "<br> &emsp &emsp { if (next element &lt current minimum)"
				+ "<br> &emsp &emsp &emsp set this element as the new current minimum }</html>");
				this.animatorGUI.getPseudoCode3().setText("<html> &emsp swap the first unsorted position with current minimum }");
				
				selectionSort(barContainer.getArrayOfBars());
				
				break;
		
		case 1: this.animatorGUI.getPseudoCode1().setText("<html>INSERTION SORT PSEUDOCODE<br><br>"
				+ "first element is considered sorted"
				+ "<br>for (all unsorted elements) </html>");
				this.animatorGUI.getPseudoCode2().setText("<html>{ select first unsorted element j"
				+ "<br> &emsp for(k = rightmost sorted element; k > 0; k--)"
				+ "<br> &emsp &emsp { if(sorted element k > selected unsorted element j)"
				+ "<br> &emsp &emsp &emsp { move sorted element k right } }</html>");
				this.animatorGUI.getPseudoCode3().setText("<html>insert selected element j into its sorted place }</html>");
				
				insertionSort(barContainer.getArrayOfBars());
				
				break;
		
		case 2: this.animatorGUI.getPseudoCode1().setText("<html>BUBBLE SORT PSEUDOCODE<br><br>"
				+ "for(array length - 1 times)</html>");
				this.animatorGUI.getPseudoCode2().setText("<html> &emsp { for (rightmost element k; k > 0; k--)"
				+ "<br> &emsp &emsp { if(element k &lt element k - 1)</html>");
				this.animatorGUI.getPseudoCode3().setText("<html> &emsp &emsp &emsp { swap element k with element k - 1 } } }</html>");
				
				bubbleSort(barContainer.getArrayOfBars());
				
				break;
		default:break;
		}
		animationController.setAnimating(false);
		animationController.setPaused(true);
	}
	
	/**
	 * Performs a bubble sort animation, which is displayed on screen.
	 * Pseudocode is highlighted and unhighlighted as appropriate as the sorting process occurs.
	 * Colours of the Bar objects are appropriately changed as the sorting process occurs.
	 * @param arrayOfBars The array of Bar objects to sort.
	 * @return The sorted array Of Bars.
	 */
	public ArrayList<Bar> bubbleSort(ArrayList<Bar> arrayOfBars) {
		for (int i = 0; i < arrayOfBars.size(); i++)
		{
			// stop animation if it is reset (used throughout the rest of this class)
			if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}
			
			highlightPanel(this.animatorGUI.getPseudoPanel1());
			barContainer.getJCounter().setXCoordinate(140+ (i*2*Bar.BAR_WIDTH));
			barContainer.repaint();
			startSleep(300);
			
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			
			
			for (int j = arrayOfBars.size()-1; j > i; j--)
			{
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}
				
				highlightPanel(this.animatorGUI.getPseudoPanel2());
				
				barContainer.getKCounter().setXCoordinate(140+ (j*2*Bar.BAR_WIDTH));
				barContainer.repaint();
				startSleep(300);
				
				Bar selectedBar = arrayOfBars.get(j);
				Bar jMinusOne = arrayOfBars.get(j-1);
				
				if(selectedBar.getValue() < jMinusOne.getValue())
				{
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					highlightPanel(this.animatorGUI.getPseudoPanel3());
					
					exchangeBarsBubble(j, j-1);
					
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					
					arrayOfBars.set(j, jMinusOne);
					arrayOfBars.set(j-1, selectedBar);
				}
			}
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}
			
			Bar sortedBar = arrayOfBars.get(i);
			sortedBar.setColor(Color.ORANGE);
			barContainer.repaint();
			startSleep(300);
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}
			
		}
		
		startSleep(300);
		
		this.barContainer.getJCounter().setXCoordinate(140);
		this.barContainer.getKCounter().setXCoordinate(140);
		barContainer.repaint();
		
		return arrayOfBars;
	}
	
	/**
	 * Performs an animation of two swapping Bar objects in the Bubble sort animation.
	 * @param firstIndex The index of the Bar to the right.
	 * @param secondIndex The index of the Bar to the left.
	 */
	public void exchangeBarsBubble(int firstIndex, int secondIndex){
		
		Bar firstBar = barContainer.getArrayOfBars().get(firstIndex);
		Bar secondBar = barContainer.getArrayOfBars().get(secondIndex);
		
		int originalFirstBarX = firstBar.getXCoordinate();
		int originalSecondBarX = secondBar.getXCoordinate();
		
		int difference = originalFirstBarX - originalSecondBarX;

		firstBar.setColor(Color.BLACK);
		secondBar.setColor(Color.BLACK);
		
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return;
		}

		for(int i = 0; i < difference/Bar.MOVE_SPEED; i++){
			firstBar.moveBarLeft();
			secondBar.moveBarRight();
			barContainer.repaint();
			
			startSleep(100);
			while(this.animationController.isPaused()){
				startSleep(500);
			}
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return;
			}
		}
		firstBar.setColor(Color.BLUE);
		secondBar.setColor(Color.BLUE);
		barContainer.repaint();

		startSleep(500);
	}

	/**
	 * Performs an Insertion sort animation, which is displayed on screen.
	 * Pseudocode is highlighted and unhighlighted as appropriate as the sorting process occurs.
	 * Colours of the Bar objects are appropriately changed as the sorting process occurs.
	 * @param arrayOfBars The array of Bar objects to sort.
	 * @return The sorted array Of Bars.
	 */
	public ArrayList<Bar> insertionSort(ArrayList<Bar> arrayOfBars) {
		
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return arrayOfBars;
		}
		
		highlightPanel(this.animatorGUI.getPseudoPanel1());
		Bar initiallySortedBar = arrayOfBars.get(0);
		initiallySortedBar.setColor(Color.ORANGE);
		startSleep(100);
		unHighlightPanel(this.animatorGUI.getPseudoPanel1());

		
		for (int i = 1; i < arrayOfBars.size(); i++)
		{
			highlightPanel(this.animatorGUI.getPseudoPanel2());
			
			barContainer.getJCounter().setXCoordinate(140+ (i*2*Bar.BAR_WIDTH));
			barContainer.repaint();
			startSleep(300);
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}

			Bar selectedBar = arrayOfBars.get(i);
			for(int moveIndex = 0; moveIndex < (Bar.MAX_BAR_HEIGHT+5)/Bar.MOVE_SPEED; moveIndex++){
				selectedBar.moveBarDown();
				barContainer.repaint();

				startSleep(100);
				
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}

				while(this.animationController.isPaused()){
					startSleep(500);
				}
			}

			int j;
			for (j = i; j > 0; j--)
			{
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}
				
				barContainer.getKCounter().setXCoordinate(140+ (j*2*Bar.BAR_WIDTH));
				barContainer.repaint();
				startSleep(300);
				
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}
				
				if (arrayOfBars.get(j-1).getValue() < selectedBar.getValue())
				{
					break; 
				}
				else {
					
					
					exchangeBarsInsertion(j, j-1);

					Bar jMinusOne = arrayOfBars.get(j-1);
					arrayOfBars.set(j, jMinusOne);
					arrayOfBars.set(j-1, selectedBar);
				}
			}
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			
			highlightPanel(this.animatorGUI.getPseudoPanel3());
			for(int moveIndex = 0; moveIndex < (Bar.MAX_BAR_HEIGHT+5)/Bar.MOVE_SPEED; moveIndex++){
				selectedBar.moveBarUp();
				barContainer.repaint();
				startSleep(100);
				
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}

				while(this.animationController.isPaused()){
					startSleep(500);
				}
				
				selectedBar.setColor(Color.ORANGE);
				barContainer.repaint();
			}
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());

		}
		startSleep(300);
		
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return arrayOfBars;
		}
		
		this.barContainer.getJCounter().setXCoordinate(140);
		this.barContainer.getKCounter().setXCoordinate(140);
		barContainer.repaint();

		return arrayOfBars;
		
	}
	
	/**
	 * Performs an animation of two swapping Bar objects in the Insertion sort animation.
	 * @param firstIndex The index of the Bar to the right.
	 * @param secondIndex The index of the Bar to the left.
	 */
	public void exchangeBarsInsertion(int firstIndex, int secondIndex){
		
		Bar firstBar = barContainer.getArrayOfBars().get(firstIndex);
		Bar secondBar = barContainer.getArrayOfBars().get(secondIndex);

		int originalFirstBarX = firstBar.getXCoordinate();
		int originalSecondBarX = secondBar.getXCoordinate();

		int difference = originalFirstBarX - originalSecondBarX;
		
		Color color1 = firstBar.getColor();
		Color color2 = secondBar.getColor();

		firstBar.setColor(Color.BLACK);
		secondBar.setColor(Color.BLACK);

		for(int i = 0; i < difference/Bar.MOVE_SPEED; i++){
			firstBar.moveBarLeft();
			secondBar.moveBarRight();
			barContainer.repaint();
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return;
			}
			startSleep(100);
			
			while(this.animationController.isPaused()){
				startSleep(500);
			}
		}
		firstBar.setColor(color1);
		secondBar.setColor(color2);
		barContainer.repaint();
		
		startSleep(500);
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return;
		}

	}

	/**
	 * Performs a Selection sort animation, which is displayed on screen.
	 * Pseudocode is highlighted and unhighlighted as appropriate as the sorting process occurs.
	 * Colours of the Bar objects are appropriately changed as the sorting process occurs.
	 * @param arrayOfBars The array of Bar objects to sort.
	 * @return The sorted array Of Bars.
	 */
	public ArrayList<Bar> selectionSort(ArrayList<Bar> arrayOfBars){
		
		Bar temp;
		int minIndex;
		
		for (int j=0; j<arrayOfBars.size()-1; j++)
		{	
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}
			
			highlightPanel(this.animatorGUI.getPseudoPanel1());
			
			Bar jBar = arrayOfBars.get(j);
			jBar.setColor(Color.BLACK);

			barContainer.getJCounter().setXCoordinate(140+ (j*2*Bar.BAR_WIDTH));
			barContainer.repaint();
			startSleep(300);
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}
			
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());

			minIndex = j;

			highlightPanel(this.animatorGUI.getPseudoPanel2());
			for (int k=j+1; k<arrayOfBars.size(); k++)
			{
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}
				
				barContainer.getKCounter().setXCoordinate(140+ (k*2*Bar.BAR_WIDTH));
				barContainer.repaint();
				while(this.animationController.isPaused()){
					startSleep(500);
		        }
				startSleep(300);
				
				if (this.animationController.isTerminated()) {
					unHighlightPanel(this.animatorGUI.getPseudoPanel1());
					unHighlightPanel(this.animatorGUI.getPseudoPanel2());
					unHighlightPanel(this.animatorGUI.getPseudoPanel3());
					return arrayOfBars;
				}

				if (arrayOfBars.get(k).getValue() < arrayOfBars.get(minIndex).getValue())
				{   
					int oldMinIndex = minIndex;
					if(oldMinIndex != j){
						Bar oldMinBar = arrayOfBars.get(oldMinIndex);
						oldMinBar.setColor(Color.BLUE);
					}

					minIndex = k;

					Bar kBar = arrayOfBars.get(k);
					kBar.setColor(Color.BLACK);
					barContainer.repaint();
					
					while(this.animationController.isPaused()){
						startSleep(500);
			        }
					startSleep(300);
				}
			}
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return arrayOfBars;
			}
			
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			
			exchangeBarsSelection(minIndex, j);

			temp = arrayOfBars.get(minIndex);
			arrayOfBars.set(minIndex, arrayOfBars.get(j));
			arrayOfBars.set(j, temp);
		}
		
		Bar lastBar = arrayOfBars.get(arrayOfBars.size()-1);
		lastBar.setColor(Color.ORANGE);
		barContainer.repaint();
		startSleep(300);
		
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return arrayOfBars;
		}
		
		this.barContainer.getJCounter().setXCoordinate(140);
		this.barContainer.getKCounter().setXCoordinate(140);
		barContainer.repaint();
		return arrayOfBars;
}
	
	
	/**
	 * Performs an animation of two swapping Bar objects in the Selection sort animation.
	 * @param firstIndex The index of the Bar to the right.
	 * @param secondIndex The index of the Bar to the left.
	 */
	public void exchangeBarsSelection(int firstIndex, int secondIndex){
		
		Bar firstBar = barContainer.getArrayOfBars().get(firstIndex);
		Bar secondBar = barContainer.getArrayOfBars().get(secondIndex);
		
		int originalFirstBarX = firstBar.getXCoordinate();
		int originalSecondBarX = secondBar.getXCoordinate();
		
		if(firstIndex == secondIndex){
			firstBar.setColor(Color.BLACK);
			barContainer.repaint();
			startSleep(100);
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return;
			}
			
			while(this.animationController.isPaused()){
				startSleep(500);
	        }
			firstBar.setColor(Color.BLUE);
			barContainer.repaint();
			firstBar.setColor(Color.ORANGE);
			barContainer.repaint();
			return;
		}
		
		int difference = originalFirstBarX - originalSecondBarX;

		firstBar.setColor(Color.BLACK);
		secondBar.setColor(Color.BLACK);
		
		highlightPanel(this.animatorGUI.getPseudoPanel3());

		for(int i = 0; i < difference/Bar.MOVE_SPEED; i++){
			firstBar.moveBarLeft();
			secondBar.moveBarRight();
			barContainer.repaint();
			startSleep(100);
			
			if (this.animationController.isTerminated()) {
				unHighlightPanel(this.animatorGUI.getPseudoPanel1());
				unHighlightPanel(this.animatorGUI.getPseudoPanel2());
				unHighlightPanel(this.animatorGUI.getPseudoPanel3());
				return;
			}
			
			while(this.animationController.isPaused()){
				startSleep(500);
			}
		}
		unHighlightPanel(this.animatorGUI.getPseudoPanel3());
		
		firstBar.setColor(Color.BLUE);
		secondBar.setColor(Color.BLUE);
		barContainer.repaint();
		startSleep(100);
		
		if (this.animationController.isTerminated()) {
			unHighlightPanel(this.animatorGUI.getPseudoPanel1());
			unHighlightPanel(this.animatorGUI.getPseudoPanel2());
			unHighlightPanel(this.animatorGUI.getPseudoPanel3());
			return;
		}
		firstBar.setColor(Color.ORANGE);
		barContainer.repaint();
		startSleep(500);
	}
	
	/**
	 * Makes the SortThread sleep for a certain duration in milliseconds in order to make the animations work.
	 * This sleep time is then influenced by the current speed multiplier previously selected by the user in order to provide fast, normal and slow animations.
	 * @param sleepTimeMillis The sleep duration in milliseconds.
	 */
	public void startSleep(int sleepTimeMillis){
		try{
			Thread.sleep((int)(sleepTimeMillis*this.currentSpeedMultiplier));
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
	/**
	 * Highlights a section of pseudocode yellow to correspond with the current animation state.
	 * @param panel The JPanel to highlight yellow.
	 */
	public void highlightPanel(JPanel panel){
		panel.setBackground(Color.YELLOW);
	}
	
	/**
	 * Unhighlights a section of pseudocode back to its original colour to correspond with the current animation state.
	 * @param panel The JPanel to unhighlight back to gray.
	 */
	public void unHighlightPanel(JPanel panel){
		panel.setBackground(Color.LIGHT_GRAY);
	}

}
	

		
		
	

