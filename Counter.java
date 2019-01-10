/**
 * The Counter class represents a loop counter within a sorting algorithm, which it visually shows.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Counter {
	
	// properties of the individual Counter
	private int xCoordinate;
	private int yCoordinate;
	private Color color;
	private String key;
	
	/**
	 * Constructor for a Counter object.
	 * @param xCoordinate The x coordinate of the counter.
	 * @param yCoordinate The y coordinate of the counter.
	 * @param color The colour of the counter.
	 * @param key The label to display alongside the counter to describe it.
	 */
	public Counter(int xCoordinate, int yCoordinate, Color color, String key){
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.color = color;
		this.key = key;
	}
	
	/**
	 * Paints the Counter object onto the screen.
	 * @param g Graphics object used to display the counter.
	 */
	public void paintCounter(Graphics g) {
        
		int[] xCoordinates = {this.xCoordinate, this.xCoordinate + Bar.BAR_WIDTH, this.xCoordinate + (Bar.BAR_WIDTH/2)};
		int[] yCoordinates = {this.yCoordinate, this.yCoordinate, this.yCoordinate + 30};
		Polygon triangle = new Polygon(xCoordinates, yCoordinates, 3);
		
		g.setColor(this.color);
        g.fillPolygon(triangle);
        
        g.drawString(this.key, 10, this.yCoordinate + 10);
    }
	
	/**
	 * Sets the x coordinate of the Counter.
	 * @param xCoordinate The new x coordinate of the counter.
	 */
	public void setXCoordinate(int xCoordinate){
		this.xCoordinate = xCoordinate;
	}
	
	/**
	 * Gets the x coordinate of the Counter.
	 * @return The x coordinate of the Counter.
	 */
	public int getXCoordinate(){
		return this.xCoordinate;
	}

}
