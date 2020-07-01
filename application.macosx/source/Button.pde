/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Button
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Button class simulates buttons.
 */

class Button
{
  private String label;                           // Label of the button
  private int xPosition;                          // x position of the button
  private int yPosition;                          // y position of the button
  private int width;                              // Width of the button
  private int height;                             // Height of the button
  private boolean isPressed;                      // Whether the button is pressed
  private boolean isClicked;                      // Whether the button is clicked
  private boolean isEnabled;                      // Whether the button is enabled
 
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param label The label of the button
   * @param xPosition The x position of the button
   * @param yPosition The y position of the button
   * @param width The width of the button
   * @param height The height of the button
   */
  public Button(String label, int xPosition, int yPosition, int width, int height)
  {
    this.label = label.toUpperCase();             // Set label to uppercase
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    isPressed = false;                            // Set button to initially unpressed 
    isEnabled = true;                             // Set button to initially enabled
  }
  
  /**
   * Returns whether the button is pressed.
   * @return Whether the button is pressed
   */
  public boolean getPressed()
  {
    return isPressed;
  }
  
  /**
   * Returns whether the button is clicked.
   * @return Whether the button is clicked
   */
  public boolean getClicked()
  {
    return isClicked;
  }
  
  /**
   * Sets whether the button is pressed to the value passed in.
   * @param isPressed Whether the button is pressed
   */
  public void setPressed(boolean isPressed)
  {
    this.isPressed = isPressed;
  }
  
  /**
   * Sets whether the button is clicked to the value passed in.
   * @param isClicked Whether the button is clicked
   */
  public void setClicked(boolean isClicked)
  {
    this.isClicked = isClicked;
  }
  
  /**
   * Sets whether the button is enabled to the value passed in.
   * @param isEnabled Whether the button is enabled
   */
  public void setEnabled(boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }
  
  /**
   * Draws the button.
   */
  public void draw()
  {
    // Set colour and text style
    color colour;
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
    
    // Draw background and label
    rect(xPosition, yPosition, width, height, 5);
    fill(66, 65, 61);
    text(label, xPosition + width/2, yPosition + height/2);
  } // end draw
  
  /**
   * Returns whether a mouse press was within the button.
   * @return Whether a mouse press was within the button
   */
  private boolean mouseIn()
  {
    return (mouseX > xPosition && mouseX < (xPosition + width) && mouseY > yPosition && mouseY < (yPosition + height));
  }
  
  /**
   * Sets the button to pressed if it is pressed by the mouse.
   */
  public void mousePressed()
  {
    isPressed = mouseIn();
  }
  
  /**
   * Sets the button to clicked if it is clicked by the mouse.
   */
  public void mouseReleased()
  {
    isClicked = (mouseIn() && isPressed);
    isPressed = false;
  }
 
} // end class Button
