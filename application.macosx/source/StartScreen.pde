/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: StartScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The StartScreen class displays the start screen.
 */

class StartScreen extends Screen
{ 
  private float opacity;                          // Opacity of the start animation
  private PVector position;                       // Position of shape
  private PVector velocity;                       // Velocity of shape
  private PVector gravity;                        // Gravity acting on shape
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public StartScreen()
  {
    super(ScreenType.START);
    setup();
  }
  
  /**
   * Sets up the start screen.
   */
  private void setup()
  {
    position = new PVector(-20, -5);
    velocity = new PVector(2.2, 0);
    gravity = new PVector(0, 0.4);
    textFont(loadFont("CenturyGothic-50.vlw"));
  }
  
  /**
   * Draws the start screen.
   */
  @Override
  public void draw()
  {
    // Set opacity to fade in and out
    if (millis() < 3500)
      opacity = (millis() - 700) / 10;
    else if (millis() > 4000)
      opacity = 255 - ((millis() - 4000) / 4);
    else
      opacity = 255;
    
    // Set colour and text style
    background(30, 29, 27);
    fill(255, 206, 43, opacity);
    textAlign(CENTER, CENTER);
    textSize(50);
    
    // Draw animated 'o' and title
    drawAnimation();
    text("M   tion Simulator", 545, 280);
    
    // Set colour and text style
    fill(178, 175, 172, opacity);
    textSize(24);
    
    // Draw credit
    text("By: Jennifer Lu", 545, 330);
  } // end draw
  
  /**
   * Animates the 'o'.
   */
  private void drawAnimation()
  {
    // Update position and velocity
    position.add(velocity);
    velocity.add(gravity);
    
    // Bounce at lower bound
    if (position.y > 280)
    {
      velocity.y = velocity.y * -0.55; 
      position.y = 280;
    }
    
    // Stop moving horizontally at final position
    if (position.x > 409)
      position.x = 409;
    
    // Stop bouncing (moving vertically) near final position
    if (position.x > 387)
      position.y = 280;
    
    // Draw the 'o'
    text("o", position.x, position.y);
  } // end drawAnimation
 
} // end class StartScreen
