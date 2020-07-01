/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Button
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The CursorBall class simulates a cursor-following ball.
 */

class CursorBall extends Ball
{
  private float xPosition;                        // x position of the ball
  private float yPosition;                        // y position of the ball
  private int number;                             // Identification number of the ball
  private Ball[] balls;                           // Array of other balls

  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param xPosition The x position of the ball
   * @param yPosition The y position of the ball
   * @param balls An array of the other balls
   */
  CursorBall(float xPosition, float yPosition, Ball[] balls)
  {
    super(xPosition, yPosition, 60, 0, balls);
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.balls = balls;
    setup();
  }
  
  /**
   * Draws the ball.
   */
  @Override
  public void draw()
  {
    update();
    noStroke();
    fill(255, 206, 43);
    ellipse(xPosition, yPosition, 60, 60);
  }
  
  /**
   * Updates the movement and collisions of the ball.
   */
  @Override
  public void update()
  {
    move();
    collideBalls();
  }
  
  /**
   * Moves the ball to follow the cursor.
   */
  @Override
  protected void move()
  {
    xPosition = (float) mouseX;
    yPosition = (float) mouseY;
  }
  
  /**
   * Calculates collisions with other balls.
   */
  @Override
  protected void collideBalls()
  {
    // Calculate collisions with each remaining ball in the array of other balls
    for (int i = number; i < 10; i++)
    {
      // Determine the distance between the balls
      float xDistance = balls[i].xPosition - xPosition;
      float yDistance = balls[i].yPosition - yPosition;
      float distance = sqrt(xDistance*xDistance + yDistance*yDistance);
      
      // Determine the distance needed for the balls to collide
      float collisionDistance = 30 + balls[i].diameter / 2;
      
      // If the balls are close enough to collide, bounce the other ball
      if (distance <= collisionDistance)
      {
        // Determine the magnitude and direction in which the other ball will accelerate
        float angle = atan2(yDistance, xDistance);
        float xAcceleration = (xPosition + cos(angle) * collisionDistance - balls[i].xPosition) * 0.1;
        float yAcceleration = (yPosition + sin(angle) * collisionDistance - balls[i].yPosition) * 0.1;
        
        // Update velocity for the other ball
        balls[i].xVelocity += xAcceleration;
        balls[i].yVelocity += yAcceleration;
      }
    }
  } // end collideBalls
  
} // end class CursorBall
