/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: SimulatorScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The SimulatorScreen class displays the motion simulator screen.
 */

import java.text.DecimalFormat;

class SimulatorScreen extends Screen
{
  private DecimalFormat defaultDecimalFormat;     // Default decimal format
  private DecimalFormat shortDecimalFormat;       // Shortened decimal format
  private Object object;                          // Object being simulated
  private Timer timer;                            // Timer
  private Label timeLabel;                        // Label for the time
  private Label positionLabel;                    // Label for the position
  private Label velocityLabel;                    // Label for the velocity
  private Label accelerationLabel;                // Label for the acceleration
  private Switch pauseSwitch;                     // Pause switch
  private Button resetButton;                     // Reset button
  private Button menuButton;                      // Menu button
  private NumberField positionField;              // Number field for the object's position
  private NumberField velocityField;              // Number field for the object's velocity
  private NumberField accelerationField;          // Number field for the object's acceleration
  private NumberField positionEquationField;      // Number field for the object's position in the equation
  private NumberField velocityEquationField;      // Number field for the object's velocity in the equation
  private NumberField accelerationEquationField;  // Number field for the object's acceleration in the equation
  private Graph positionGraph;                    // Graph for the object's position
  private Graph velocityGraph;                    // Graph for the object's velocity
  private Graph accelerationGraph;                // Graph for the object's acceleration
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public SimulatorScreen()
  {
    super(ScreenType.SIMULATOR);
    setup();
  }
  
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param isTutorial Whether the screen is in tutorial mode
   */
  public SimulatorScreen(boolean isTutorial)
  {
    super(ScreenType.TUTORIAL);
    setup();
  }
  
  /**
   * Sets up the simulation.
   * Initializes the: object, timer, label, number fields, switch, and buttons.
   */ 
  private void setup()
  {
    textFont(loadFont("CenturyGothic-24.vlw"));   // Default font
    defaultDecimalFormat = new DecimalFormat("0.0000");             // Default decimal format
    shortDecimalFormat = new DecimalFormat("0.0");                  // Shortened decimal format
    
    // Initialize the object being simulated and the timer
    object = new Object();
    timer = new Timer();
      
    // Initialize labels
    timeLabel = new Label("Time: " + defaultDecimalFormat.format(timer.getTime()), 730, 30, 330, 30);
    positionLabel = new Label("d(t) =             t  +             t +", 730, 250, 330, 30);
    velocityLabel = new Label("d'(t) = " + shortDecimalFormat.format(object.velocity.getValue()), 730, 380, 330, 30);
    accelerationLabel = new Label("d\"(t) = " + shortDecimalFormat.format(object.acceleration.getValue()), 730, 510, 330, 30);
    
    // Initialize number fields
    positionField = new NumberField("Position", defaultDecimalFormat.format(object.position.getValue()), 730, 190, 330, 30);
    velocityField = new NumberField("Velocity", defaultDecimalFormat.format(object.velocity.getValue()), 730, 320, 330, 30);
    accelerationField = new NumberField("Acceleration", defaultDecimalFormat.format(object.acceleration.getValue()), 730, 450, 330, 30);
    positionEquationField = new NumberField("" + shortDecimalFormat.format(object.position.getValue()), 993, 250, 60, 30);
    velocityEquationField = new NumberField("" + shortDecimalFormat.format(object.velocity.getValue()), 900, 250, 60, 30);
    accelerationEquationField = new NumberField("" + shortDecimalFormat.format(object.acceleration.getValue() / 2), 805, 250, 60, 30);
    
    // Initialize graphs
    positionGraph = new Graph(115);
    velocityGraph = new Graph(245);
    accelerationGraph = new Graph(375);
      
    // Initialize switches and buttons
    pauseSwitch = new Switch("pause", "play", 730, 90, 90, 40, true);
    resetButton = new Button("reset", 850, 90, 90, 40);
    menuButton = new Button("menu", 970, 90, 90, 40);
    
    // If in tutorial mode, disable buttons, switches, and fields
    if (super.getScreenType() == ScreenType.TUTORIAL)
    {
      positionField.setEnabled(false);
      velocityField.setEnabled(false);
      accelerationField.setEnabled(false);
      positionEquationField.setEnabled(false);
      velocityEquationField.setEnabled(false);
      accelerationEquationField.setEnabled(false);
      pauseSwitch.setEnabled(false);
      resetButton.setEnabled(false);
      menuButton.setEnabled(false);
    }
  } // end setup

  /**
   * Draws the simulation screen.
   */
  @Override
  public void draw()
  {
    // Update object motion and labels
    update();
      
    // Set background to dark gray
    background(30, 29, 27);
      
    // Draw grid with scale and frame
    drawGrid();
      
    // Draw object, labels, number fields, switch, and buttons
    object.draw();
    timeLabel.draw();
    positionLabel.draw();
    velocityLabel.draw();
    accelerationLabel.draw();
    positionField.draw();
    velocityField.draw();
    accelerationField.draw();
    positionEquationField.draw();
    velocityEquationField.draw();
    accelerationEquationField.draw();
    pauseSwitch.draw();
    resetButton.draw();
    menuButton.draw();
    positionGraph.draw();
    velocityGraph.draw();
    accelerationGraph.draw();
    
    if(positionEquationField.getVisible())
    {
      fill(178, 175, 172);
      textSize(12);
      text("2", 880, 257);
    }
  }

  /**
   * Draws a grid with a numbered scale and frame.
   */
  private void drawGrid()
  {
    // Set colours and text style
    stroke(66, 65, 61);
    fill(178, 175, 172);
    textSize(16);
    textAlign(CENTER, CENTER);
    
    // Draw vertical grid lines and a number scale
    for (int col = 0; col < 21; col++)
    {
      line(col * 30 + 65, 35, col * 30 + 65, 155);
      text("" + (-10 + col), col * 30 + 65, 140);
    }
    
    // Draw horizontal grid lines
    line(30, 65, 695, 65);
    line(30, 95, 695, 95);
    stroke(178, 175, 172);
    line(30, 125, 695, 125);
    
    // Draw a frame around the grid
    noStroke();
    rect(30, 30, 670, 5);
    rect(30, 155, 670, 5);
    rect(30, 35, 5, 125);
    rect(695, 35, 5, 125);
  } // end drawGrid

  /**
   * Updates the timer, object, and labels.
   */
  private void update()
  { 
    // Update the timer
    timer.updateTime();
    
    // If the timer is running, update object, number fields, and graphs
    if (!timer.getPaused())
    {
      object.updateMotion(timer.getLapTime());
      updateNumberFields();
      positionGraph.update(timer.getTime(), object.position.getValue());
      velocityGraph.update(timer.getTime(), object.velocity.getValue());
      accelerationGraph.update(timer.getTime(), object.acceleration.getValue());
    }
    
    // Update the number fields if they have been deselected
    if (!positionField.getSelected() && !velocityField.getSelected() && !accelerationField.getSelected())
      updateNumberFields();
    
    // Stop the object's motion and pause the simulation if it has collided with a frame border
    if (object.didCollide())
    {
      object.stop();
      timer.setPaused(true);
      pauseSwitch.setToggled(true);
      updateNumberFields();
    }
    
    // Update time label
    timeLabel.setText("Time: " + defaultDecimalFormat.format(timer.getTime()));
  } // end update
  
  /**
   * Updates the position, velocity, and acceleration labels.
   */
  private void updateObjectLabels()
  {
    // Equations are unavailable for complex (piecewise) functions
    if (timer.getTime() != 0)
    {
      positionLabel.setText("No equation.");
      velocityLabel.setText("No equation.");
      accelerationLabel.setText("No equation.");
      positionEquationField.setVisible(false);
      velocityEquationField.setVisible(false);
      accelerationEquationField.setVisible(false);
    }
    else
    {
      // Update fields
      positionEquationField.setVisible(true);
      velocityEquationField.setVisible(true);
      accelerationEquationField.setVisible(true);
      if(!positionEquationField.getSelected())
        positionEquationField.setField("" + shortDecimalFormat.format(object.position.getValue()));
      if(!velocityEquationField.getSelected())
        velocityEquationField.setField("" + shortDecimalFormat.format(object.velocity.getValue()));
      if(!accelerationEquationField.getSelected())
        accelerationEquationField.setField("" + shortDecimalFormat.format(object.acceleration.getValue() / 2));
      
      // Update labels
      positionLabel.setText("d(t) =             t  +             t +");
      if (object.acceleration.getValue() == 0)
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.velocity.getValue()));
      else if (object.velocity.getValue() == 0)
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.acceleration.getValue()) + "t");
      else
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.acceleration.getValue()) + "t + " + shortDecimalFormat.format(object.velocity.getValue()));
      accelerationLabel.setText("d\"(t) = " + shortDecimalFormat.format(object.acceleration.getValue()));
    }
  } // end updateObjectLabels
  
  /**
   * Updates the number fields.
   */
  private void updateNumberFields()
  {
    positionField.setField(defaultDecimalFormat.format(object.position.getValue()));
    velocityField.setField(defaultDecimalFormat.format(object.velocity.getValue()));
    accelerationField.setField(defaultDecimalFormat.format(object.acceleration.getValue()));
  }
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
    if (menuButton.getClicked())
      return ScreenType.MENU;
     return null;
  }
  
  /**
   * Updates the position of the object being simulated.
   */
  private void updatePosition(NumberField updatedField)
  {
    object.position.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
  }
  
  /**
   * Updates the velocity of the object being simulated.
   */
  private void updateVelocity(NumberField updatedField)
  {
    object.velocity.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
    object.velocity.updateInitialValue();
  }
  
  /**
   * Updates the acceleration of the object being simulated.
   */
  private void updateAcceleration(NumberField updatedField)
  {
    object.acceleration.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
    object.velocity.updateInitialValue();
    object.acceleration.updateInitialValue();
  }
  
  /**
   * Resets the simulation.
   */
  private void reset()
  {
    pauseSwitch.setToggled(true);
    timer.reset();
    object.stop();
    object.position.reset();
    updateNumberFields();
    positionGraph.reset();
    velocityGraph.reset();
    accelerationGraph.reset();
    updateObjectLabels();
  }
    
  /**
   * Responds to mouse presses. Updates the buttons, switches, and number fields if the mouse is pressed.
   */
  @Override
  public void mousePressed()
  {
    pauseSwitch.mousePressed();
    resetButton.mousePressed();
    menuButton.mousePressed();
    
    // Only update number fields if the simulation is paused
    if (timer.getPaused())
    {
      positionField.mousePressed();
      velocityField.mousePressed();
      accelerationField.mousePressed();
      positionEquationField.mousePressed();
      velocityEquationField.mousePressed();
      accelerationEquationField.mousePressed();
    }
    
    // If number fields were edited, update the object's motion and reset the timer lap
    if (positionField.getEdited())
      updatePosition(positionField);
    else if (positionEquationField.getEdited())
      updatePosition(positionEquationField);
    else if (velocityField.getEdited())
      updateVelocity(velocityField);
    else if (velocityEquationField.getEdited())
      updateVelocity(velocityEquationField);
    else if (accelerationField.getEdited())
      updateAcceleration(accelerationField);
    else if (accelerationEquationField.getEdited())
    {
      accelerationEquationField.setField("" + Float.parseFloat(accelerationEquationField.getField()) * 2);
      updateAcceleration(accelerationEquationField);
    }
    if (positionField.getEdited() || velocityField.getEdited() || accelerationField.getEdited() || positionEquationField.getEdited() || velocityEquationField.getEdited() || accelerationEquationField.getEdited())
    {
      positionField.setEdited(false);
      velocityField.setEdited(false);
      accelerationField.setEdited(false);
      positionEquationField.setEdited(false);
      velocityEquationField.setEdited(false);
      accelerationEquationField.setEdited(false);
      updateObjectLabels();
      timer.resetLap();
    }
  } // end mousePressed
  
  /**
   * Responds to mouse releases. Updates the buttons if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    pauseSwitch.mouseReleased();
    resetButton.mouseReleased();
    menuButton.mouseReleased();
    
    // If the pause switch is toggled, pause the simulation
    timer.setPaused(pauseSwitch.getToggled());
    
    // If the reset button was clicked, reset the simulation
    if (resetButton.getClicked())
    {
      resetButton.setClicked(false);
      reset();
    }
  } // end mouseReleased
  
  /**
   * Responds to key presses. Updates text fields if a key was pressed.
   */
  @Override
  public void keyPressed()
  {
    positionField.keyPressed();
    velocityField.keyPressed();
    accelerationField.keyPressed();
    positionEquationField.keyPressed();
    velocityEquationField.keyPressed();
    accelerationEquationField.keyPressed();
    
    // If the value entered for position is invalid, set it to 0
    if (positionField.getField().length() >= 2 && (Float.parseFloat(positionField.getField()) > 10 || Float.parseFloat(positionField.getField()) < -10))
      positionField.setField("0.0000");
    if (positionEquationField.getField().length() >= 2 && (Float.parseFloat(positionEquationField.getField()) > 10 || Float.parseFloat(positionEquationField.getField()) < -10))
      positionEquationField.setField("0.0");
  } // end keyPressed
 
} // end class SimulatorScreen
