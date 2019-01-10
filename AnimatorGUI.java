/**
 * The AnimatorGUI class is responsible for setting up the graphical user interface and initialising the program.
 * It creates the frame and adds all graphical components to it.
 * It then adds event listeners to the graphical components, and sets up an AnimationController to handle these events.
 * @author Jeremy Williams
 * @version 1.0
 */
package uk.ac.ncl.animator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AnimatorGUI {
	
	// top level container
	private JFrame frame;
	
	// panels to place components in
	private JPanel panel, topControls, bottomControls, bottomButtonPanel, pseudoCodePanel, pseudoPanel1, pseudoPanel2, pseudoPanel3;
	
	// handles drawing of animation - placed in centre of GUI
	protected BarContainer barContainer;
	
	// labels for top user controls
	private JLabel title, selectLabel, speedLabel;
	
	// labels for pseudocode
	protected JLabel pseudoCode1, pseudoCode2, pseudoCode3;
	
	// user control for selection of algorithm
	private JComboBox<String> animationSelector;
	
	// user control of animation speed
	private JSlider animationSpeed;
	
	// user controls for animation
	private JButton reset, play, pause, randomize;
	
	// used to connect events generated to actions performed
	protected AnimationController animationController;
	
	/**
	 * Constructor for the AnimatorGUI object.
	 * It creates the frame and adds all graphical components to it.
	 * It then adds event listeners to the graphical components, and sets up an AnimationController to handle these events.
	 */
	public AnimatorGUI()
	{
		frame = new JFrame("Sorting Algorithm Animation"); 
		panel = new JPanel(new BorderLayout());
		
		addComponents();
		
		this.animationController = new AnimationController(this.barContainer, this);
		
		addListeners();
		
	  	frame.setContentPane(panel);
	  	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	frame.setSize(1280, 800);;
	  	frame.setVisible(true);
	
	}

	/**
	 * Adds all the graphical components to the content pane of the program.
	 */
	private void addComponents() {
		
		// top section of GUI
		topControls = new JPanel();
		topControls.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(topControls, BorderLayout.PAGE_START);
		
		title = new JLabel("Sorting Visually");
		title.setFont(new Font("Serif", Font.BOLD, 40));
		topControls.add(title);
		
		selectLabel = new JLabel("Select Algorithm:");
		selectLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		topControls.add(selectLabel);
		
		String[] selection = {"Selection Sort", "Insertion Sort", "Bubble Sort"};
		animationSelector = new JComboBox<String>(selection);
		animationSelector.setSelectedIndex(0);
		animationSelector.setPreferredSize(new Dimension(200, 50));
		topControls.add(animationSelector);
		
		speedLabel = new JLabel("Set Animation Speed:");
		speedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		topControls.add(speedLabel);
		
		animationSpeed = new JSlider(JSlider.HORIZONTAL, 1, 3, 2);
		animationSpeed.setPreferredSize(new Dimension(200, 50));
		animationSpeed.setPaintTicks(true);
		animationSpeed.setSnapToTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 1 ), new JLabel("SLOW") );
		labelTable.put( new Integer( 2 ), new JLabel("NORMAL") );
		labelTable.put( new Integer( 3 ), new JLabel("FAST") );
		animationSpeed.setLabelTable( labelTable );
		animationSpeed.setPaintLabels(true);
		animationSpeed.setMajorTickSpacing(1);
		topControls.add(animationSpeed);
		
		randomize = new JButton("Randomize");
		topControls.add(randomize);
		
		
		// centre of GUI where animation occurs
		barContainer = new BarContainer();
		barContainer.setBackground(Color.WHITE);
		barContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(barContainer, BorderLayout.CENTER);
		
		
		// bottom section of GUI
		GridLayout bottomLayout = new GridLayout(0,2);
		bottomControls = new JPanel();
		bottomControls.setLayout(bottomLayout);
		bottomControls.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomControls.setPreferredSize(new Dimension(1200, 180));
		panel.add(bottomControls, BorderLayout.PAGE_END);
		
		// user control buttons for animation
		bottomButtonPanel = new JPanel();
		bottomButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomControls.add(bottomButtonPanel);
		
		// pseudocode section
		pseudoCodePanel = new JPanel(new BorderLayout());
		pseudoCodePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomControls.add(pseudoCodePanel);
		
		
		play = new JButton("Play");
		bottomButtonPanel.add(play);
		
		pause = new JButton("Pause");
		bottomButtonPanel.add(pause);
		
		reset = new JButton("Reset");
		bottomButtonPanel.add(reset);
		
		pseudoPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pseudoPanel1.setPreferredSize(new Dimension(600, 75));
		pseudoPanel1.setBackground(Color.LIGHT_GRAY);
		pseudoCodePanel.add(pseudoPanel1, BorderLayout.PAGE_START);
		pseudoCode1 = new JLabel("<html>SELECTION SORT PSEUDOCODE<br><br>"
				+ "for (array length - 1 times)"
				+ "<br> &emsp {set the minimum as first unsorted element</html>");
		pseudoPanel1.add(pseudoCode1);
		
		pseudoPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pseudoPanel2.setPreferredSize(new Dimension(600, 75));
		pseudoPanel2.setBackground(Color.LIGHT_GRAY);
		pseudoCodePanel.add(pseudoPanel2, BorderLayout.CENTER);
		pseudoCode2 = new JLabel("<html> &emsp for (all unsorted elements)"
				+ "<br> &emsp &emsp {if (next element &lt current minimum)"
				+ "<br> &emsp &emsp &emsp set this element as the new current minimum}</html>");
		pseudoPanel2.add(pseudoCode2);
		
		pseudoPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pseudoPanel3.setPreferredSize(new Dimension(600, 30));
		pseudoPanel3.setBackground(Color.LIGHT_GRAY);
		pseudoCodePanel.add(pseudoPanel3, BorderLayout.PAGE_END);
		pseudoCode3 = new JLabel("<html> &emsp swap the first unsorted position with current minimum}");
		pseudoPanel3.add(pseudoCode3);
		
	}
	
	/**
	 * Adds event listeners to the user controls components in order to handle events.
	 */
	public void addListeners()
	{
		// randomize button
		randomize.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				if(animationController.isPaused() && !animationController.isAnimating()){
				barContainer.randomizeArrayOfBars();
				}
				
			}
			
		});
		
		// aniamtion selector menu
		animationSelector.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				int currentSelection = animationSelector.getSelectedIndex();
				
				switch(currentSelection){
					
					case 0: animationController.setCurrentlySelectedAlgorithm(AnimationController.SELECTION_SORT);
							
							break;
					case 1: animationController.setCurrentlySelectedAlgorithm(AnimationController.INSERTION_SORT);
							
							break;
					case 2: animationController.setCurrentlySelectedAlgorithm(AnimationController.BUBBLE_SORT);
							
							break; 		
					default: break;
				}
				
			}
			
		});
		
		// play button
		play.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				animationController.play();
				
			}
			
		});
		
		// pause button 
		pause.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				animationController.pause();
				
			}
			
		});
		
		// speed control
		animationSpeed.addChangeListener(new ChangeListener(){
			
			public void stateChanged(ChangeEvent e) {
				
				int currentSelection = animationSpeed.getValue();
				
				switch(currentSelection){
					
					case 1: animationController.setCurrentSpeedMultiplier(AnimationController.SLOW);
							break;
					case 2: animationController.setCurrentSpeedMultiplier(AnimationController.NORMAL);
							break;
					case 3: animationController.setCurrentSpeedMultiplier(AnimationController.FAST);
					break;		
					default: break;
				}
				
			}
			
		});
		
		// reset button
		reset.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				animationController.reset();
				
			}
			
		});
		
	}
	
	/**
	 * Gets the first (top) JPanel that the pseudocode JLabel is placed in.
	 * @return The first (top) JPanel that the pseudocode JLabel is placed in.
	 */
	public JPanel getPseudoPanel1(){
		return this.pseudoPanel1;
	}
	
	/**
	 * Gets the second (middle) JPanel that the pseudocode JLabel is placed in.
	 * @return The second (middle) JPanel that the pseudocode JLabel is placed in.
	 */
	public JPanel getPseudoPanel2(){
		return this.pseudoPanel2;
	}
	
	/**
	 * Gets the third (bottom) JPanel that the pseudocode JLabel is placed in.
	 * @return The third (bottom) JPanel that the pseudocode JLabel is placed in.
	 */
	public JPanel getPseudoPanel3(){
		return this.pseudoPanel3;
	}
	
	/**
	 * Gets the top JLabel that shows the first part of the pseudocode.
	 * @return The top JLabel that shows the first part of the pseudocode.
	 */
	public JLabel getPseudoCode1(){
		return this.pseudoCode1;
	}
	
	/**
	 * Gets the middle JLabel that shows the second part of the pseudocode.
	 * @return The middle JLabel that shows the second part of the pseudocode.
	 */
	public JLabel getPseudoCode2(){
		return this.pseudoCode2;
	}
	
	/**
	 * Gets the bottom JLabel that shows the third part of the pseudocode.
	 * @return The bottom JLabel that shows the third part of the pseudocode.
	 */
	public JLabel getPseudoCode3(){
		return this.pseudoCode3;
	}

}
