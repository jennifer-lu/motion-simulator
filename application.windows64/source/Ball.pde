/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Button
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Ball class simulates balls.
 */

class Ball
{
  private float xPosition;                        // x position of the ball
  private float yPosition;                        // y position of the ball
  private float diameter;                         // Diameter of the ball
  private int number;                             // Identification number of the ball
  private Ball[] balls;                           // Array of other balls
  private float xVelocity;                        // x velocity of the ball
  private float yVelocity;                        // y velocity of the ball
  private final float GRAVITY = 0.5;              // Gravity of the ball
  private final float SPRING = 0.1;              // Spring of the ball
  private final float WALL_FRICTION = -0.8;       // Friction of the ball on the wall
  private final float AIR_RESISTANCE = 0.98;      // Air resistance of the ball

  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param xPosition The x position of the ball
   * @param yPosition The y position of the ball
   * @param diameter The diameter of the ball
   * @param number The identification number of the ball
   * @param balls An array of the other balls
   */
  Ball(float xPosition, float yPosition, float diameter, int number, Ball[] balls)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.diameter = diameter;
    this.number = number;
    this.balls = balls;
    xVelocity = 0;                                // Ball initially has no velocity
    yVelocity = 0;                                // Ball initially has no velocity
  }
  
  /**
   * Returns the x position of the ball.
   * @return The x position of the ball
   */
  public float getXPosition()
  {
    return xPosition;
  }
  
  /**
   * Returns the y position of the ball.
   * @return The y position of the ball
   */
  public float getYPosition()
  {
    return yPosition;
  }
  
  /**
   * Returns the diameter of the ball.
   * @return The diameter of the ball
   */
  public float getDiameter()
  {
    return diameter;
  }
  
  /**
   * Updates the x velocity of the ball according to the x acceleration value passed in.
   * @param xAcceleration The x acceleration of the ball
   */
  public void updateXVelocity(float xAcceleration)
  {
    xVelocity += xAcceleration;
  }
  
  /**
   * Updates the y velocity of the ball according to the y acceleration value passed in.
   * @param yAcceleration The y acceleration of the ball
   */
  public void updateYVelocity(float yAcceleration)
  {
    yVelocity += yAcceleration;
  }
  
  /**
   * Draws the ball.
   */
  public void draw()
  {
    update();
    ellipse(xPosition, yPosition, diameter, diameter);
  }
  
  /**
   * Updates the movement and collisions of the ball.
   */
  public void update()
  {
    move();
    collideBalls();
    collideWalls();
  }
  
  /**
   * Calculates the movement of the ball.
   */
  protected void move()
  {
    // Determine position and velocity
    xVelocity *= AIR_RESISTANCE;
    yVelocity *= AIR_RESISTANCE;
    yVelocity += GRAVITY;
    xPosition += xVelocity;
    yPosition += yVelocity;
  }
  
  /**
   * Calculates collisions with other balls.
   */
  protected void collideBalls()
  {
    // Calculate collisions with each ball in the array of other balls
    for (int i = number; i < 10; i++)
    
      // If the balls are close enough to collide, calculate their new velocity and acceleration after the collision
      if (willCollide(balls[i]))
      {
        // Determine the magnitude and direction in which both balls will accelerate
        float angle = atan2(getYDistance(balls[i]), getXDistance(balls[i]));
        float xAcceleration = (xPosition + cos(angle) * getCollisionDistance(balls[i]) - balls[i].getXPosition()) * SPRING;
        float yAcceleration = (yPosition + sin(angle) * getCollisionDistance(balls[i]) - balls[i].getYPosition()) * SPRING;
        
        // Update velocities of both balls
        xVelocity -= xAcceleration;
        yVelocity -= yAcceleration;
        balls[i].updateXVelocity(xAcceleration);
        balls[i].updateYVelocity(yAcceleration);
      }
  } // end collideBalls
  
  /**
   * Returns the x distance to the recieved ball.
   * @param ball The other ball
   * @return The x distance to the recieved ball
   */
  private float getXDistance(Ball ball)
  {
    return ball.getXPosition() - xPosition;
  }
  
  /**
   * Returns the y distance to the recieved ball.
   * @param ball The other ball
   * @return The y distance to the recieved ball
   */
  private float getYDistance(Ball ball)
  {
    return ball.getYPosition() - yPosition;
  }
  
  /**
   * Returns the distance needed to collide with the recieved ball.
   * @param ball The other ball
   * @return The distance needed to collide with the recieved ball
   */
  private float getCollisionDistance(Ball ball)
  {
    return diameter / 2 + ball.getDiameter() / 2;
  }
  
  /**
   * Returns whether the ball will collide with the recieved ball.
   * @param ball The other ball
   * @return Whether the ball will collide with the recieved ball
   */
  private boolean willCollide(Ball ball)
  {
    // Determine the distance between the balls
    float distance = sqrt(getXDistance(ball) * getXDistance(ball) + getYDistance(ball) * getYDistance(ball));
    return distance <= getCollisionDistance(ball);
  }
  
  /**
   * Calculates collisions with the walls.
   */
  private void collideWalls()
  {
    // If the ball is close enough to collide with a wall, calculate its new position velocity after the collision
    if (xPosition + diameter / 2 > width)
    {
      xPosition = width - diameter / 2;
      xVelocity *= WALL_FRICTION;
    }
    else if (xPosition - diameter / 2 < 0)
    {
      xPosition = diameter / 2;
      xVelocity *= WALL_FRICTION;
    }
    if (yPosition + diameter / 2 > height)
    {
      yPosition = height - diameter / 2;
      yVelocity *= WALL_FRICTION; 
    } 
    else if (yPosition - diameter / 2 < 0)
    {
      yPosition = diameter/2;
      yVelocity *= WALL_FRICTION;
    }
  } // end collideWalls
  
} // end class Ball
