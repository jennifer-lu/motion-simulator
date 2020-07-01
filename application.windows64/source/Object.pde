/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Object
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Object class simulates objects in motion.
 */

class Object
{
  private Vector position;                        // Position of the object
  private Vector velocity;                        // Velocity of the object
  private Vector acceleration;                    // Acceleration of the object
  private boolean collided;                       // Whether the object has collided
  
  /**
   * Constructor that sets the values of all fields to default values.
   */
  public Object()
  {
    // Set object to have no motion
    position = new Vector(0);
    velocity = new Vector(0);
    acceleration = new Vector(0);
    collided = false;
  }
  
  /**
   * Draws the object.
   */
  public void draw()
  {
    noStroke();
    fill(255, 206, 43);
    ellipse(position.getValue() * 30 + 365, 95, 60, 60);
  }
  
  /**
   * Returns whether the object has collided.
   * @return Whether the object has collided
   */
  public boolean didCollide()
  {
    return collided;
  }
  
  /**
   * Stops the object's motion.
   */
  public void stop()
  {
    // Maintains the object's position
    position.setValue((int)position.getValue());
    position.updateInitialValue();
    collided = false;
    
    // Set the values of the object's motion vectors to zero
    velocity.reset();
    acceleration.reset();
  }
  
  /**
   * Updates the values of the object's motion vectors based on the time elapsed.
   * @param time The time elapsed
   */
  public void updateMotion(float time)
  {
    velocity.setValue(velocity.getInitialValue() + acceleration.getValue() * time);
    position.setValue(position.getInitialValue() + (velocity.getValue() + velocity.getInitialValue()) * 0.5 * time);
    
    // If the object has collided, stop the object and update its collision status
    if (position.getValue() < -10.0000)
    {
      position.setValue(-10.0000);
      collided = true;
    }
    else if (position.getValue() > 10.0000)
    {
      position.setValue(10.0000);
      collided = true;
    }
  } // end updateMotion

} // end class Object
