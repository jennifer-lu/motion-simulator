/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: MotionSimulator
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: This program simulates an object in one-dimensional motion.
 *              It displays graphs of the object’s position, velocity, and acceleration
 *              and allows the user to pause, play, alter, and reset the object’s motion.
 */

import java.text.DecimalFormat;
                
Screen screen;                                    // Current screen

/**
 * Sets the window size and starts the program with the start screen.
 */ 
public void setup()
{
  size(1090, 580);
  screen = new StartScreen();
}

/**
 * Draws the current screen.
 */
public void draw()
{
  screen.draw();
  
  // After the start screen animation, direct the user to the menu
  if (screen.getScreenType() == ScreenType.START)
    if (millis() > 5500)
      screen = new MenuScreen();
}

/**
 * Updates the screen if the mouse is pressed.
 */
public void mousePressed()
{
  screen.mousePressed();
}

/**
 * Updates the screen if the mouse is released.
 */
public void mouseReleased()
{
  screen.mouseReleased();
  
  // Change the screen if it should be changed
  if (screen.getScreenChange() != null)
    switch (screen.getScreenChange())
    {
      case MENU:
        screen = new MenuScreen();
        break;
      case SIMULATOR:
        screen = new SimulatorScreen();
        break;
      case TUTORIAL:
        screen = new TutorialScreen();
        break;
      case ABOUT:
        screen = new AboutScreen();
        break;
      case EXIT:
        screen = new ExitScreen();
        break;
      default:
        break;
    }
} // end mouseReleased

/**
 * Updates the screen if a key is pressed.
 */
public void keyPressed()
{
  screen.keyPressed();
}
