/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: MenuScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The MenuScreen class displays the menu screen.
 */

class MenuScreen extends Screen
{ 
  private Button simulatorButton;                 // Simulator button
  private Button tutorialButton;                  // Tutorial button
  private Button aboutButton;                     // About button
  private Button exitButton;                      // Exit button
  private Ball[] balls;                           // Array of balls in the background
  private CursorBall cursorBall;                  // Cursor-following ball
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public MenuScreen()
  {
    super(ScreenType.MENU);
    setup();
  }
  
  /**
   * Sets up the menu. Initializes buttons and balls.
   */ 
  private void setup()
  {
    simulatorButton = new Button("Motion Simulator", 410, 215, 270, 40);
    tutorialButton = new Button("Tutorial", 470, 275, 150, 40);
    aboutButton = new Button("About", 485, 335, 120, 40);
    exitButton = new Button("Exit", 505, 395, 80, 40);
    
    // Initialize balls for background display
    balls = new Ball[10];
    for (int i = 0; i < 10; i++)
      balls[i] = new Ball(random(width), random(height), random(60, 200), i + 1, balls);
    cursorBall = new CursorBall(mouseX, mouseY, balls);
  } // end setup

  /**
   * Draws the menu screen.
   */
  @Override
  public void draw()
  {
    // Draw background and balls
    background(30, 29, 27);
    noStroke();
    fill(66, 65, 61);
    for (Ball ball : balls)
      ball.draw();
    
    // Set colour and text
    fill(178, 175, 172);
    textAlign(CENTER, CENTER);
    textFont(loadFont("CenturyGothic-50.vlw"));
    textSize(50);
    
    // Draw header and buttons
    text("Menu", 545, 145);
    simulatorButton.draw();
    tutorialButton.draw();
    aboutButton.draw();
    exitButton.draw();
    
    // Draw cursor ball
    cursorBall.draw();
  } // end draw
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
    if (simulatorButton.getClicked())
      return ScreenType.SIMULATOR;
    else if (tutorialButton.getClicked())
      return ScreenType.TUTORIAL;
    else if (aboutButton.getClicked())
      return ScreenType.ABOUT;
    else if (exitButton.getClicked())
      return ScreenType.EXIT;
    return null;
  }
  
  /**
   * Responds to mouse presses. Updates the buttons if the mouse is pressed.
   */
  @Override
  public void mousePressed()
  {
    simulatorButton.mousePressed();
    tutorialButton.mousePressed();
    aboutButton.mousePressed();
    exitButton.mousePressed();
  }
  
  /**
   * Responds to mouse releases. Updates the buttons if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    simulatorButton.mouseReleased();
    tutorialButton.mouseReleased();
    aboutButton.mouseReleased();
    exitButton.mouseReleased();
  }
 
} // end class MenuScreen
