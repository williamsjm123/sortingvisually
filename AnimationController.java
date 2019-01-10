/**
 * The AnimationController class contains methods called by events that the user triggers from components in the AnimatorGUI class.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import java.util.ArrayList;

public class AnimationController {

	private BarContainer barContainer;
	private AnimatorGUI animatorGUI;
	
	// used to set up a new thread
	private Runnable runnable;
	private Thread thread;
	
	// boolean value to indicate if the animation is paused
	private volatile boolean paused;
	// boolean value to indicate if the animation is currently animating (has been started)
	private volatile boolean animating;
	// boolean value to indicate if the animation is currently being reset
	private volatile boolean terminated;
	
	// integers used by the AnimatorGUI class when user selects an algorithm type
	public static final int SELECTION_SORT = 0;
	public static final int INSERTION_SORT = 1;
	public static final int BUBBLE_SORT = 2;
	
	// integers used by the AnimatorGUI class when user selects an animation speed
	public static final double SLOW = 1.5;
	public static final double NORMAL = 1;
	public static final double FAST = 0.5;
	
	// integer representing the currently selected algorithm type
	private int currentlySelectedAlgorithm;
	// integer representing the currently selected animation speed
	private double currentSpeedMultiplier;
	
	/**
	 * Constructor for an AnimationController object.
	 * @param barContainer The BarContainer object created by the AnimatorGUI class.
	 * @param animatorGUI The AnimatorGUI object connected to the AnimationController.
	 */
	public AnimationController(BarContainer barContainer, AnimatorGUI animatorGUI){
		this.barContainer = barContainer;
		this.animatorGUI = animatorGUI;
		this.currentlySelectedAlgorithm = SELECTION_SORT;
		this.currentSpeedMultiplier = NORMAL;
		this.paused = true;
		this.animating = false;
		this.terminated = false;
	}
	
	/**
	 * Starts the animation when the user presses the play button if the animation has not yet started.
	 * Restarts the animation if it is paused.
	 */
	public void play(){
			
			if(!paused){
				return;
			}
			setPaused(false);
			
			if(!animating){
			startSort();
			}
		}
	
	/**
	 * Sets up a new thread to play an animation with the currently selected options given by the user (Algorithm type and speed).
	 */
	public void startSort(){
		setAnimating(true);
		this.runnable = new SortThread(barContainer, this, currentlySelectedAlgorithm, this.currentSpeedMultiplier, this.animatorGUI);
		this.thread = new Thread(runnable);
		thread.start();
	}
	
	/**
	 * Pauses the animation.
	 */
	public void pause(){
		setPaused(true);
	}
	
	/**
	 * Resets the animation back to its original state if it is currently animating and hasn't already been reset.
	 * Checks terminated boolean first to check if currently in the process of being reset.
	 * The original array of unsorted Bars is displayed alongside the reset loop counters.
	 * This uses the copy of the original array inside the BarContainer object. 
	 */
	public void reset(){
		if(this.terminated){
			return;
		}
		
		this.terminated = true;
		setPaused(false);
		if(animating){
			while(this.thread.isAlive()){
				try{
				Thread.sleep(100);
				}
				catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
		this.thread = null;
		this.runnable = null;
		setPaused(true);
		setAnimating(false);
		setTerminated(false);
		
		
		ArrayList<Bar> returnToOriginalState = new ArrayList<Bar>();
		for(Bar bar: barContainer.getArrayOfBarsCopy()){
			Bar barCopy = new Bar(bar.getXCoordinate(), bar.getWidth(), bar.getValue(), bar.getColor());
			returnToOriginalState.add(barCopy);
		}
		this.barContainer.setArrayOfBars(returnToOriginalState);
		this.barContainer.getJCounter().setXCoordinate(140);
		this.barContainer.getKCounter().setXCoordinate(140);
		
		this.barContainer.repaint();
		
		
	}
	
	/**
	 * Sets the currently selected algorithm type (Selection, Insertion or Bubble) represented by an integer instance variable.
	 * @param currentlySelectedAlgorithm The new currently selected algorithm.
	 */
	public void setCurrentlySelectedAlgorithm(int currentlySelectedAlgorithm){
		this.currentlySelectedAlgorithm = currentlySelectedAlgorithm;
	}
	
	/**
	 * Gets the currently selected algorithm type type (Selection, Insertion or Bubble) represented by an integer instance variable.
	 * @return The currently selected algorithm.
	 */
	public int getCurrentlySelectedAlgorithm(){
		return this.currentlySelectedAlgorithm;
	}
	
	/**
	 * Sets the current speed multiplier used to control the speed of animation.
	 * @param currentSpeedMultiplier The new current speed multiplier.
	 */
	public void setCurrentSpeedMultiplier(double currentSpeedMultiplier){
		this.currentSpeedMultiplier = currentSpeedMultiplier;
	}
	
	/**
	 * Gets the current speed multiplier used to control the speed of animation.
	 * @return The current speed multiplier.
	 */
	public double getCurrentSpeedMultiplier(){
		return this.currentSpeedMultiplier;
	}
	
	/**
	 * Gets the boolean value representing whether the animation is paused or not.
	 * @return true if paused, false if not paused.
	 */
	public boolean isPaused(){
		return this.paused;
	}
	
	/**
	 * Sets the boolean value representing whether the animation is paused or not.
	 * Synchronized to avoid concurrency problems.
	 * @param paused The new value of the paused boolean. True if paused, false if not.
	 */
	public synchronized void setPaused(boolean paused){
		this.paused = paused;
	}
	
	/**
	 * Gets the boolean value representing whether the animation is in progress.
	 * This is whether the animation has previously been started by pressing play or not.
	 * Synchronized to avoid concurrency problems.
	 * @return true if in progress, false if not.
	 */
	public synchronized boolean isAnimating(){
		return this.animating;
	}
	
	/**
	 * Sets the boolean value representing whether the animation is in progress.
	 * This is whether the animation has previously been started by pressing play or not.
	 * Synchronized to avoid concurrency problems.
	 * @param animating true if in progress, false if not.
	 */
	public synchronized void setAnimating(boolean animating){
		this.animating = animating;
	}
	
	/**
	 * Sets the boolean value representing whether the animation is currently being reset.
	 * @param terminated true if the animation is being reset, false if not.
	 */
	public synchronized void setTerminated(boolean terminated){
		this.terminated = terminated;
	}
	
	/**
	 * Gets the boolean value representing whether the animation is currently being reset.
	 * @return true if the animation is being reset, false if not.
	 */
	public synchronized boolean isTerminated(){
		return this.terminated;
	}
	
}
