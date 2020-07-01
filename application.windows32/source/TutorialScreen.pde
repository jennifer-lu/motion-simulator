/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: TutorialScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The TutorialScreen class displays the tutorial screen.
 */

class TutorialScreen extends SimulatorScreen
{
  private boolean finished;                       // Whether the tutorial is finished
  private int tutorialNumber;                     // The tutorial number
  private Label tutorialLabel;                    // The tutorial label
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public TutorialScreen()
  {
    super(true);
    finished = false;
    tutorialNumber = 1;
  }
  
  /**
   * Draws the tutorial screen.
   */
  @Override
  public void draw()
  {
    super.draw();
    
    // Draws the tutorial label based on the tutorial number
    switch (tutorialNumber)
    {
      case 1:
        String[] textBlock = {"Welcome to the tutorial!", "Here you will learn how to use the motion simulator."};
        tutorialLabel = new Label(textBlock, 255, 245, 580, 115);
        break;
      case 2:
        drawTutorial2();
        break;
      case 3:
        drawTutorial3();
        break;
      case 4:
        drawTutorial4();
        break;
      case 5:
        drawTutorial5();
        break;
      case 6:
        drawTutorial6();
        break;
      case 7:
        drawTutorial7();
        break;
      case 8:
        drawTutorial8();
        break;
      case 9:
        String[] textBlock2 = {"That's all there is!", "Now you can try the real deal."};
        tutorialLabel = new Label(textBlock2, 355, 233, 380, 115);
        break;
      default:
        break;
    }
    tutorialLabel.draw();
  } // end draw
  
  /**
   * Draws the second tutorial.
   */
  private void drawTutorial2()
  {
    String[] textBlock = {"This is the object.", "It will move based on the parameters that you enter."};
    tutorialLabel = new Label(textBlock, 78, 183, 580, 115);
    setColour();
    rect(315, 45, 100, 100);
  }
  
  /**
   * Draws the third tutorial.
   */
  private void drawTutorial3()
  {
    String[] textBlock = {"These are the fields in which you can enter those parameters.", 
        "You can adjust the position, velocity, and acceleration.",
        "You may enter positive and negative decimal values.",
        "For example, Position: 10, Velocity: -6, Acceleration: 1",
        "or, Position: -7, Velocity: 2.5, Acceleration: -0.2."};
    tutorialLabel = new Label(textBlock, 20, 220, 690, 220);
    setColour();
    rect(720, 180, 350, 50);
    rect(720, 310, 350, 50);
    rect(720, 440, 350, 50);
  }
  
  /**
   * Draws the fourth tutorial.
   */
  private void drawTutorial4()
  {
    String[] textBlock = {"You can also enter an equation to",
        "control how the object moves.",
        "This is the equation for the object's position."};
    tutorialLabel = new Label(textBlock, 210, 200, 500, 150);
    setColour();
    rect(720, 240, 350, 50);
  }
  
  /**
   * Draws the fifth tutorial.
   */
  private void drawTutorial5()
  {
    String[] textBlock = {"This is the timer.", "It will display the time elapsed as the object moves."};
    tutorialLabel = new Label(textBlock, 130, 20, 580, 115);
    setColour();
    rect(720, 20, 350, 50);
  }
  
  /**
   * Draws the sixth tutorial.
   */
  private void drawTutorial6()
  {
    String[] textBlock = {"These are the graphs for each parameter.",
        "They will display the position, velocity, and acceleration of the object over time."};
    tutorialLabel = new Label(textBlock, 20, 55, 870, 115);
    setColour();
    rect(20, 180, 690, 120);
    rect(20, 310, 690, 120);
    rect(20, 440, 690, 120);
  }
  
  /**
   * Draws the seventh tutorial.
   */
  private void drawTutorial7()
  {
    String[] textBlock = {"These are the equations of the",
        "velocity and acceleration graphs.",
        "They are the first and second derivatives",
        "of the position equation, respectively."};
    tutorialLabel = new Label(textBlock, 240, 365, 470, 190);
    setColour();
    rect(720, 370, 350, 50);
    rect(720, 500, 350, 50);
  }
  
  /**
   * Draws the eighth tutorial.
   */
  private void drawTutorial8()
  {
    String[] textBlock = {"These are the buttons.",
        "You can use them to play and pause the object's motion,",
        "to reset all parameters to zero, and to return to the menu."};
    tutorialLabel = new Label(textBlock, 60, 20, 650, 150);
    setColour();
    rect(720, 80, 110, 60);
    rect(840, 80, 110, 60);
    rect(960, 80, 110, 60);
  }
  
  /**
   * Sets colours.
   */
  private void setColour()
  {
    noFill();
    stroke(255, 206, 43);
  }
  
  /* Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
   if (finished)
      return ScreenType.SIMULATOR;
    return null;
  }
  
  /**
   * Ignores mouse presses.
   */
  @Override
  public void mousePressed()
  {
  }
  
  /**
   * Responds to mouse releases. Advances the tutorial number if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    tutorialNumber++;
    if (tutorialNumber == 10)
      finished = true;
  }
  
} // end class TutorialScreen
