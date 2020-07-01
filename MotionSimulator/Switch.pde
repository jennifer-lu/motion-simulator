/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Switch
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Switch class simulates switches.
 */

class Switch
{
  private String labelUntoggled;                  // Label of the switch when untoggled
  private String labelToggled;                    // Label of the switch when toggled
  private int xPosition;                          // x position of the switch
  private int yPosition;                          // y position of the switch
  private int width;                              // Width of the switch
  private int height;                             // Height of the switch
  private boolean isPressed;                      // Whether the switch is pressed
  private boolean isToggled;                      // Whether the switch is toggled
  private boolean isEnabled;                      // Whether the button is enabled
  private color colour;                           // Colour of the switch
 
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param labelUntoggled The label of the switch when untoggled
   * @param labelToggled The label of the switch when toggled
   * @param xPosition The x position of the switch
   * @param yPosition The y position of the switch
   * @param width The width of the switch
   * @param height The height of the switch
   * @param isToggled Whether the switch is toggled
   */
  public Switch(String labelUntoggled, String labelToggled, int xPosition, int yPosition, int width, int height, boolean isToggled)
  {
    this.labelUntoggled = labelUntoggled.toUpperCase();
    this.labelToggled = labelToggled.toUpperCase();
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    this.isToggled = isToggled;                   // Set switch to initially toggled 
    this.isEnabled = true;                        // Set switch to initially enabled 
  }
  
  /**
   * Returns whether the switch is toggled.
   * @return Whether the switch is toggled
   */
  public boolean getToggled()
  {
    return isToggled;
  }
  
  /**
   * Sets whether the switch is toggled to the value passed in.
   * @param isToggled Whether the switch is toggled
   */
  public void setToggled(boolean isToggled)
  {
    this.isToggled = isToggled;
  }
  
  /**
   * Sets the enabled status of the switch to the value passed in.
   * @param isEnabled Whether the switch is enabled
   */
  public void setEnabled(boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }
  
  /**
   * Draws the switch.
   */
  public void draw()
  {
    // Set colour and text style
    if (isPressed)
      colour = color(237, 188, 25);
    else if (mouseIn() && isEnabled)
      colour = color(265, 216, 53);
    else
      colour = color(255, 206, 43);
    stroke(colour);
    fill(colour);
    textSize(24);
    textAlign(CENTER, CENTER);
    
    // Draw background
    rect(xPosition, yPosition, width, height, 5);
    
    // Draw label
    fill(66, 65, 61);
    if (isToggled)
      text(labelToggled, xPosition + width/2, yPosition + height/2);
    else
      text(labelUntoggled, xPosition + width/2, yPosition + height/2);
  } // end draw
  
  /**
   * Returns whether a mouse press was within the switch.
   * @return Whether a mouse press was within the switch
   */
  private boolean mouseIn()
  {
    return (mouseX > xPosition && mouseX < (xPosition + width) && mouseY > yPosition && mouseY < (yPosition + height));
  }
  
  /**
   * Sets the switch to pressed if it is pressed by the mouse.
   */
  public void mousePressed()
  {
    isPressed = mouseIn();
  }
  
  /**
   * Toggles the switch if it is clicked by the mouse.
   */
  public void mouseReleased()
  {
     if (mouseIn() && isPressed)
     {
      isToggled = !isToggled;
      isPressed = false;
     }
  }
 
} // end class Switch
