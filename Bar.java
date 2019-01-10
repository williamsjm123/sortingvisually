/**
 * The Bar class represents a bar chart style bar corresponding to an individual integer within a sorting algorithm.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import java.awt.*;

public class Bar{
	
	// constants used to define the size and location of the Bar objects on screen
	public static final int BAR_WIDTH = 50;
	public static final int MAX_BAR_HEIGHT = 200;
	public static final int OFFSET_FROM_TOP = 90;
	public static final int HEIGHT_PER_VALUE = MAX_BAR_HEIGHT/10;
	public static final int MOVE_SPEED = 10;

	// individual properties of each Bar
	private int xCoordinate;
	private int yCoordinate;
	private int width;
	private int value;
	private Color color;
	
	/**
	 * Constructor for a Bar object.
	 * @param xCoordinate The x coordinate of the Bar.
	 * @param width The width of the Bar in pixels.
	 * @param value The integer value that the Bar represents.
	 * @param color The colour of the Bar.
	 */
	public Bar(int xCoordinate, int width, int value, Color color){
		this.xCoordinate = xCoordinate;
		this.yCoordinate = MAX_BAR_HEIGHT + OFFSET_FROM_TOP - (value*HEIGHT_PER_VALUE);
		this.width = width;
		this.value = value;
		this.color = color;
	}
	
	/**
	 * Paints the Bar object onto the screen.
	 * @param g Graphics object used to display the Bar.
	 */
	public void paintBar(Graphics g) {
        
		g.setColor(this.color);
        g.fillRect(xCoordinate, yCoordinate, width, value*HEIGHT_PER_VALUE);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString(String.valueOf(value), (xCoordinate + (width/3)), (yCoordinate + (5 +(value*HEIGHT_PER_VALUE)/2)));
    }
	
	/**
	 * Sets the x coordinate of the Bar.
	 * @param xCoordinate The new x coordinate of the Bar.
	 */
	public void setXCoordinate(int xCoordinate){
		this.xCoordinate = xCoordinate;
	}
	
	/**
	 * Gets the x coordinate of the Bar.
	 * @return The x coordinate of the Bar.
	 */
	public int getXCoordinate(){
		return this.xCoordinate;
	}
	
	/**
	 * Sets the y coordinate of the Bar.
	 * @param yCoordinate The new y coordinate of the Bar.
	 */
	public void setYCoordinate(int yCoordinate){
		this.yCoordinate = yCoordinate;
	}
	
	/**
	 * Gets the y coordinate of the Bar.
	 * @return The y coordinate of the Bar.
	 */
	public int getYCoordinate(){
		return this.yCoordinate;
	}
	
	/**
	 * Gets the width of the Bar.
	 * @return The width of the Bar.
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Gets the integer value of the Bar.
	 * @return The integer value of the Bar.
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Sets the colour of the Bar.
	 * @param color The new colour of the Bar.
	 */
	public void setColor(Color color){
		this.color = color;
	}
	
	/**
	 * Gets the colour of the Bar.
	 * @return The colour of the Bar.
	 */
	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Moves the Bar down.
	 */
	public void moveBarDown(){
		setYCoordinate(getYCoordinate() + MOVE_SPEED);
	}
	
	/**
	 * Moves the Bar up.
	 */
	public void moveBarUp(){
		setYCoordinate(getYCoordinate() - MOVE_SPEED);
	}
	
	/**
	 * Moves the Bar left.
	 */
	public void moveBarLeft(){
		setXCoordinate(getXCoordinate() - MOVE_SPEED);
	}
	
	/**
	 * Moves the Bar right.
	 */
	public void moveBarRight(){
		setXCoordinate(getXCoordinate() + MOVE_SPEED);
	}
	
	/**
	 * Determines whether this Bar object is equal to another using its x coordinate and value.
	 * Only used in JUnit tests.
	 * @param otherBar the other Bar
	 * @return true if equal or false if not
	 */
	public boolean equals(Object otherBar){
		if (this == otherBar){
			return true;
			}
		
		if (!(otherBar instanceof Bar)){
			return false;
		}
		
		Bar bar = (Bar) otherBar;
		
		if(this.value == bar.getValue() && this.xCoordinate == bar.getXCoordinate())
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Override of the hashcode method as equals has been overridden.
	 */
	public int hashCode() {
		  int hc = 23;
		  int multiplier = 41;
		   hc = multiplier * hc + this.xCoordinate;
		   hc = multiplier * hc + this.value;
		   return hc;
		}
}
