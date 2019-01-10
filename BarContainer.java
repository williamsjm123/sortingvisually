/**
 * The BarContainer class represents the dynamic drawing area of the program.
 * It contains the data for and displays ten Bar objects corresponding to integers within a sorting algorithm.
 * It also contains the data for and displays 2 Counter objects.
 * Additionally, it displays a key that explains the colours of the Bar objects on screen.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BarContainer extends JPanel{
	
	// to contain the Bar objects
	private ArrayList<Bar> arrayOfBars;
	// to contain an initial copy of the Bar objects in their initial positions (used to reset the animation)
	private ArrayList<Bar> arrayOfBarsCopy;
	// outer loop counter
	private Counter jCounter;
	//inner loop counter
	private Counter kCounter;
	
	/**
	 * Constructor of a BarContainer object.
	 * Sets up initial arrays of Bars and counters.
	 */
	public BarContainer(){
		
		this.arrayOfBars = new ArrayList<Bar>();
		this.arrayOfBarsCopy = new ArrayList<Bar>();
		
		for(int i = 0; i < 10; i++){
			Bar bar = new Bar((140 + i*2*Bar.BAR_WIDTH), Bar.BAR_WIDTH, i+1 , Color.ORANGE);
			arrayOfBars.add(bar);
			
			Bar barCopy = new Bar((140 + i*2*Bar.BAR_WIDTH), Bar.BAR_WIDTH, i+1 , Color.ORANGE);
			arrayOfBarsCopy.add(barCopy);
		}
		
		this.jCounter = new Counter(140, 10, Color.RED, "Outer Loop");
		this.kCounter = new Counter(140, 50, Color.PINK, "Inner Loop");
		
	}
	
	/**
	 * Paints all objects contained within the BarContainer onto the screen.
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        paintBarContainer(g);
        paintCounters(g);
        paintKey(g);
    }
	
	/**
	 * Paints the contents of the array of Bar objects onto the screen.
	 */
	public void paintBarContainer(Graphics g) {
		
		for(Bar b: arrayOfBars){
			b.paintBar(g);
		}
        
    }
	
	/**
	 * Paints the 2 Counter objects onto the screen.
	 */
	public void paintCounters(Graphics g) {
		
		this.jCounter.paintCounter(g);
		this.kCounter.paintCounter(g);
        
    }
	
	/**
	 * Paints the key onto the screen.
	 */
	public void paintKey(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(1150, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT +20, 105, 160);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("KEY", 1165, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 40);
        
        g.setColor(Color.BLUE);
        g.fillRect(1160, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 60, 85, 30);
        g.setColor(Color.WHITE);
        g.drawString("Unsorted", 1165, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 80);
        
        g.setColor(Color.ORANGE);
        g.fillRect(1160, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 100, 85, 30);
        g.setColor(Color.WHITE);
        g.drawString("Sorted", 1165, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 120);
        
        g.setColor(Color.BLACK);
        g.fillRect(1160, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 140, 85, 30);
        g.setColor(Color.WHITE);
        g.drawString("Swap", 1165, Bar.OFFSET_FROM_TOP + Bar.MAX_BAR_HEIGHT + 160);
        
		
	}

	/**
	 * Randomizes the order in which the Bar objects are stored in the array Of Bars and its copy.
	 * It does this by swapping the x coordinate and array position of 2 randomly selected Bars in the array 100 times.
	 * After randomization, the colour of the bars is changed to blue to reflect their unsorted status.
	 */
	public void randomizeArrayOfBars(){
		int numberOfSwaps = 100;
		
		Random randomGen = new Random();
		
		for(int i = 0; i < numberOfSwaps; i++){
			int firstIndex = randomGen.nextInt(arrayOfBars.size());
			int secondIndex = randomGen.nextInt(arrayOfBars.size());
			
			if(firstIndex == secondIndex){
				
			}
			else{
				Bar bar1 = arrayOfBars.get(firstIndex);
				Bar bar2 = arrayOfBars.get(secondIndex);
				
				Bar bar1Copy = arrayOfBarsCopy.get(firstIndex);
				Bar bar2Copy = arrayOfBarsCopy.get(secondIndex);
				
				int x1temp = bar1.getXCoordinate();
				bar1.setXCoordinate(bar2.getXCoordinate());
				bar2.setXCoordinate(x1temp);
				
				Bar tempBar = bar1;
				arrayOfBars.set(firstIndex, bar2);
				arrayOfBars.set(secondIndex, tempBar);
				
				int x1tempCopy = bar1Copy.getXCoordinate();
				bar1Copy.setXCoordinate(bar2Copy.getXCoordinate());
				bar2Copy.setXCoordinate(x1tempCopy);
				
				Bar tempBarCopy = bar1Copy;
				arrayOfBarsCopy.set(firstIndex, bar2Copy);
				arrayOfBarsCopy.set(secondIndex, tempBarCopy);
			}	
		}
		for(Bar bar: arrayOfBars){
			bar.setColor(Color.BLUE);
		}
		for(Bar bar: arrayOfBarsCopy){
			bar.setColor(Color.BLUE);
		}
		repaint();
	}
	
	/**
	 * Gets the array of Bars.
	 * @return The array of Bars.
	 */
	public ArrayList<Bar> getArrayOfBars(){
		return this.arrayOfBars;
	}
	/**
	 * Sets the array Of Bars.
	 * @param arrayOfBars The new array of Bars.
	 */
	public void setArrayOfBars(ArrayList<Bar> arrayOfBars){
		this.arrayOfBars = arrayOfBars;
	}
	
	/**
	 * Gets the original copy made of the array of Bars.
	 * @return The copy of the array of Bars.
	 */
	public ArrayList<Bar> getArrayOfBarsCopy(){
		return this.arrayOfBarsCopy;
	}
	
	/**
	 * Gets the outer loop Counter J.
	 * @return The outer loop Counter J.
	 */
	public Counter getJCounter(){
		return this.jCounter;
	}
	
	/**
	 * Gets the inner loop Counter K.
	 * @return The inner loop Counter K.
	 */
	public Counter getKCounter(){
		return this.kCounter;
	}
}
