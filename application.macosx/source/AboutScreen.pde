/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: AboutScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The AboutScreen class displays the about screen.
 */

class AboutScreen extends Screen
{ 
  private Button menuButton;                      // Menu button
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public AboutScreen()
  {
    super(ScreenType.ABOUT);
    menuButton = new Button("Menu", 495, 465, 100, 40);
  }
  
  /**
   * Draws the about screen.
   */
  @Override
  public void draw()
  {
    // Set colour and text style
    background(30, 29, 27);
    fill(178, 175, 172);
    textAlign(CENTER, CENTER);
    textFont(loadFont("CenturyGothic-50.vlw"));
    textSize(24);
    
    // Draw text
    text("Program:  Motion Simulator", 545, 180);
    text("Version:  1.0", 545, 230);
    text("Released:  June 15, 2020", 545, 280);
    text("Author:  Jennifer Lu", 545, 330);
    text("Client:  Tony", 545, 380);
    
    // Draw header
    textSize(50);
    text("About", 545, 90);
    
    // Draw button
    menuButton.draw();
  } // end draw
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
    // Redirect the user to the menu if the menu button has been clicked
    if (menuButton.getClicked())
      return ScreenType.MENU;
    return null;
  }
  
  /**
   * Responds to mouse presses. Updates the button if the mouse is pressed.
   */
  @Override
  public void mousePressed()
  {
    menuButton.mousePressed();
  }
  
  /**
   * Responds to mouse releases. Updates the button if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    menuButton.mouseReleased();
  }

} // end class AboutScreen
