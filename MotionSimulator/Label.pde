/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Label
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Label class simulates labels.
 */

class Label
{
  private String text;                            // Text displayed by the label
  private String[] textBlock;                     // Text block displayed by the label
  private int xPosition;                          // x position of the label
  private int yPosition;                          // y position of the label
  private int width;                              // Width of the label
  private int height;                             // Height of the label
  private boolean isTransparent;                  // Whether the label is transparent
 
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param text The text displayed by the label
   * @param xPosition The x position of the label
   * @param yPosition The y position of the label
   * @param width The width of the label
   * @param height The height of the label
   */
  public Label(String text, int xPosition, int yPosition, int width, int height)
  {
    this.text = text;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    this.isTransparent = false;
  }
  
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param textBlock The text block displayed by the label
   * @param xPosition The x position of the label
   * @param yPosition The y position of the label
   * @param width The width of the label
   * @param height The height of the label
   */
  public Label(String[] textBlock, int xPosition, int yPosition, int width, int height)
  {
    this.textBlock = textBlock;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    this.isTransparent = true;
  }
  
  /**
   * Sets the text of the label to the String passed in.
   * @param text The text to be displayed by the label
   */
  public void setText(String text)
  {
    this.text = text;
  }
  
  /**
   * Draws the label.
   */
  public void draw()
  {
    // Draws transparent version
    if (isTransparent)
    {
      // Set colour and text style
      noStroke();
      fill(0, 0, 0, 200);
      textSize(22);
      textAlign(CENTER, CENTER);
      
      // Draw background and text
      rect(xPosition, yPosition, width, height, 5);
      fill(178, 175, 172);
      for (int i = 0; i < textBlock.length; i++)
        text(textBlock[i], xPosition  + width / 2, yPosition + i * 34 + 24);
      fill(255, 206, 43);
      textSize(18);
      text("Click anywhere to continue.", xPosition + width / 2, yPosition + textBlock.length * 34 + 24);
    }
    
    // Draws opaque version
    else
    {
      // Set colour and text style
      stroke(66, 65, 61);
      fill(66, 65, 61);
      textSize(24);
      textAlign(LEFT, CENTER);
      
      // Draw background and text
      rect(xPosition, yPosition, width, height, 2);
      fill(178, 175, 172);
      text(text, xPosition + 10, yPosition + height / 2);
    }
  } // end draw
 
} // end class Label
