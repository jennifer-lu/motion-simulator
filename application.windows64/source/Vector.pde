/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Vector
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Vector class represents vectors.
 */

class Vector
{
  private float value;                            // Current value of the vector
  private float initialValue;                     // Initial value of the vector
  
  /**
   * Constructor that sets the values of all fields to the argument passed in.
   * @param value The value of the vector
   */
  public Vector(float value)
  {
    this.value = value;
    this.initialValue = value;                    // Set the initial value of the vector its value at construction
  }
  
  /**
   * Returns the current value of the vector.
   * @return The current value of the vector
   */
  public float getValue()
  {
    return value;
  }
  
  /**
   * Returns the initial value of the vector.
   * @return The initial value of the vector
   */
  public float getInitialValue()
  {
    return initialValue;
  }
  
  /**
   * Sets the current value of the vector to the value passed in.
   * @param value The value of the vector
   */
  public void setValue(float value)
  {
    this.value = value;
  }
  
  /**
   * Updates the initial value of the vector to the current value of the vector.
   */
  public void updateInitialValue()
  {
    initialValue = value;
  }
  
  /**
   * Resets all values of the vector to 0.
   */
  public void reset()
  {
    this.value = 0;
    this.initialValue = 0;
  }
  
} // end class Vector
