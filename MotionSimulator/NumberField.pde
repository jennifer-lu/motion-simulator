/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: NumberField
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The NumberField class simulates editable number fields.
 */

class NumberField
{
  private String label;                           // Label displayed by the number field
  private String field;                           // Field displayed by the number field
  private int xPosition;                          // x position of the number field
  private int yPosition;                          // y position of the number field
  private int width;                              // Width of the number field
  private int height;                             // Height of the number field
  private color colour;                           // Colour of the number field
  private boolean isSelected;                     // Whether the number field is selected
  private boolean isEdited;                       // Whether the number field is edited
  private boolean isEnabled;                      // Whether the number field is enabled
  private boolean isVisible;                      // Whether the number field is visible
  
  /**
   * Constructor that sets the values of all fields to the argument passed in.
   * @param label The label of the number field
   * @param field The field of the number field
   * @param xPosition The x position of the number field
   * @param yPosition The y position of the number field
   * @param width The width of the number field
   * @param height The height of the number field
   */
  public NumberField(String label, String field, int xPosition, int yPosition, int width, int height)
  {
    this.label = label + ": ";
    this.field = field;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    isSelected = false;                           // Set number field to initially unselected
    isEdited = false;                             // Set number field to initially unedited
    isEnabled = true;                             // Set number field to initially enabled 
    isVisible = true;                             // Set number field to initially visible 
  }
  
  /**
   * Constructor that sets the values of all fields to the argument passed in.
   * @param field The field of the number field
   * @param xPosition The x position of the number field
   * @param yPosition The y position of the number field
   * @param width The width of the number field
   * @param height The height of the number field
   */
  public NumberField(String field, int xPosition, int yPosition, int width, int height)
  {
    label = "";                                   // No label
    this.field = field;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.width = width;
    this.height = height;
    isSelected = false;                           // Field is initially unselected
    isEdited = false;                             // Field is initially unedited
    isEnabled = true;                             // Field is initially enabled
    isVisible = true;                             // Set number field to initially visible 
  }
  
  /**
   * Returns the field of the number field.
   * @return The field of the number field
   */
  public String getField()
  {
    return field;
  }
  
  /**
   * Returns whether number field is edited.
   * @return Whether number field is edited
   */
  public boolean getEdited()
  {
    return isEdited;
  }
  
  /**
   * Returns whether number field is selected.
   * @return Whether number field is selected
   */
  public boolean getSelected()
  {
    return isSelected;
  }
  
  /**
   * Returns whether number field is visible.
   * @return Whether number field is visible
   */
  public boolean getVisible()
  {
    return isVisible;
  }
  
  /**
   * Sets the field of the number field to the String passed in.
   * @param field The field to be displayed by the number field
   */
  public void setField(String field)
  {
    this.field = field;
  }
  
  /**
   * Sets the edited status of the number field to the value passed in.
   * @param isEdited Whether the number field is edited
   */
  public void setEdited(boolean isEdited)
  {
    this.isEdited = isEdited;
  }
  
  /**
   * Sets the enabled status of the field to the value passed in.
   * @param isEnabled Whether the field is enabled
   */
  public void setEnabled(boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }
  
  /**
   * Sets the visible status of the field to the value passed in.
   * @param isVisible Whether the field is visible
   */
  public void setVisible(boolean isVisible)
  {
    this.isVisible = isVisible;
  }
  
  /**
   * Draws the number field.
   */
  public void draw()
  {
    if(isVisible)
    {
      // Set colour
      if (isSelected)
        colour = color(51, 50, 46);
      else if (mouseIn() && isEnabled)
        colour = color(76, 75, 71);
      else
        colour = color(66, 65, 61);
      stroke(colour);
      fill(colour);
      
      // Draw background
      rect(xPosition, yPosition, width, height, 2);
      
      // Set text style
      if (isEnabled && (mouseIn() || isSelected))
        fill(250, 250, 250);
      else
        fill(178, 175, 172);
      textSize(24);
      
      // Draw label and field
      if (label.equals(""))
      {
        textAlign(CENTER, CENTER);
        text(field, xPosition + width / 2, yPosition + height / 2);
      }
      else
      {
        textAlign(LEFT, CENTER);
        text(field, xPosition + textWidth(label) + 10, yPosition + height / 2);
        fill(178, 175, 172);
        text(label, xPosition + 10, yPosition + height / 2);
      }
    }
  } // end draw
  
  /**
   * Returns whether a mouse press was within the number field.
   * @return Whether a mouse press was within the number field
   */
  private boolean mouseIn()
  {
    return (isVisible && mouseX > xPosition && mouseX < xPosition + width && mouseY > yPosition && mouseY < yPosition + height);
  }
  
  /**
   * Toggles whether the number field is selected if it is pressed by the mouse.
   */
  public void mousePressed()
  {
    // Empties the field if it contians a defualt value
    if(mouseIn() && (field.equals("0.0000") || field.equals("0.0")))
        field = "";
        
    if (isSelected && !mouseIn())
    {
      isEdited = true;
      
      // If the field does not contain a non-zero integer, set it to 0.0000
      if (field.length() == 0 || field.equals(".") || field.equals("-"))
        field = "0.0000";
    }
    isSelected = mouseIn();
  }
  
  /**
   * Returns whether an input is valid.
   * @return Whether the input is valid
   */
  private boolean isValid()
  {
    // Input is valid if it is a digit
    boolean isValid = keyCode >= 48 && keyCode <= 57;
    
    // Input is valid if it is a '-' as the first character
    if (keyCode == 45 && field.length() == 0)
      isValid = true;
     
    // Input is valid if it is a '.' and the field has no pre-existing '.' characters and does not only contain a '-'
    if (keyCode == 46 && !field.contains(".") && !field.equals("-"))
      isValid = true;
    
    return isValid;
  } // end isValid
  
  /**
   * Changes the contents of the number field based on the user's keyboard input (if it is selected).
   */
  public void keyPressed()
  {
    if (isSelected)
    {
      if (keyCode == BACKSPACE)
      {
        if (field.length() > 0)
          field = field.substring(0, field.length() - 1);
      }
      else if (isValid())
        field += key;
    }
  } // end keyPressed

} // end class NumberField
