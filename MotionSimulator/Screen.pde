/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Screen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Screen class displays a screen.
 */

static class Screen
{ 
  private ScreenType screenType;                  // Screen type of the Screen
  
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param screenType The screen type of the Screen
   */
  public Screen(ScreenType screenType)
  {
    this.screenType = screenType;
  }
  
  /**
   * Returns the screen type of the screen.
   * @return The screen type of the screen.
   */
  public ScreenType getScreenType()
  {
    return screenType;
  }
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  public ScreenType getScreenChange()
  {
    return null;
  }
  
  public void draw() {}
  
  public void mouseReleased() {}
  
  public void mousePressed() {}
  
  public void keyPressed() {}
  
} // end class Screen
