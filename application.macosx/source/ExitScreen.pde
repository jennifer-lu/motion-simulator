/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: ExitScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The ExitScreen class displays the exit screen.
 */

class ExitScreen extends Screen
{
  private int startTime;                          // Start time of the exit animation
  private int opacity;                            // Opacity of the exit animation
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public ExitScreen()
  {
    super(ScreenType.EXIT);
    startTime = millis();                         // Set the start time to the time at construction
  }
  
  /**
   * Draws the exit screen.
   */
  @Override
  public void draw()
  {
    // Set opacity to decrease over time
    opacity = 2100 - millis() + startTime;
    
    // Set colour and text
    background(30, 29, 27);
    textAlign(CENTER, CENTER);
    fill(178, 175, 172, opacity);
    textFont(loadFont("CenturyGothic-24.vlw"));
    textSize(24);
    
    // Draw exit message part 1
    text("Thank you for using the", 545, 270);
    
    // Set colour and text
    fill(255, 206, 43, opacity);
    textFont(loadFont("CenturyGothic-50.vlw"));
    textSize(50);
    
    // Draw exit message part 2
    text("Motion Simulator", 545, 320);
    
    // Stop program after animation ends
    if (millis() - startTime > 2500)
      exit();
  } // end draw
 
} // end class ExitScreen
