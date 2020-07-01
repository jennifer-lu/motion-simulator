import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.text.DecimalFormat; 
import java.text.DecimalFormat; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MotionSimulator extends PApplet {

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


                
Screen screen;                                    // Current screen

/**
 * Sets the window size and starts the program with the start screen.
 */ 
public void setup()
{
  
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
  private final float GRAVITY = 0.5f;              // Gravity of the ball
  private final float SPRING = 0.1f;              // Spring of the ball
  private final float WALL_FRICTION = -0.8f;       // Friction of the ball on the wall
  private final float AIR_RESISTANCE = 0.98f;      // Air resistance of the ball

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
    int colour;
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
        float xAcceleration = (xPosition + cos(angle) * collisionDistance - balls[i].xPosition) * 0.1f;
        float yAcceleration = (yPosition + sin(angle) * collisionDistance - balls[i].yPosition) * 0.1f;
        
        // Update velocity for the other ball
        balls[i].xVelocity += xAcceleration;
        balls[i].yVelocity += yAcceleration;
      }
    }
  } // end collideBalls
  
} // end class CursorBall
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: ExitScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The ExitScreen class displays the exit screen.
 */

class ExitScreen extends Screen
{
  private int startTime;                          // Start time of the exit animation
  private int opacity;                            // Opacity of the exit animation
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public ExitScreen()
  {
    super(ScreenType.EXIT);
    startTime = millis();                         // Set the start time to the time at construction
  }
  
  /**
   * Draws the exit screen.
   */
  @Override
  public void draw()
  {
    // Set opacity to decrease over time
    opacity = 2100 - millis() + startTime;
    
    // Set colour and text
    background(30, 29, 27);
    textAlign(CENTER, CENTER);
    fill(178, 175, 172, opacity);
    textFont(loadFont("CenturyGothic-24.vlw"));
    textSize(24);
    
    // Draw exit message part 1
    text("Thank you for using the", 545, 270);
    
    // Set colour and text
    fill(255, 206, 43, opacity);
    textFont(loadFont("CenturyGothic-50.vlw"));
    textSize(50);
    
    // Draw exit message part 2
    text("Motion Simulator", 545, 320);
    
    // Stop program after animation ends
    if (millis() - startTime > 2500)
      exit();
  } // end draw
 
} // end class ExitScreen
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Button
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Graph class simulates graphs.
 */

class Graph
{
  private int yPosition;                          // y position of the graph
  private ArrayList<float[]> pointList;           // Points on the graph
  private int xBorder;                            // x border of the graph
  private int yBorder;                            // y border of the graph
 
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param yPosition The y position of the graph
   */
  public Graph(int yPosition)
  {
    this.yPosition = yPosition;
    pointList = new ArrayList<float[]>();
    xBorder = 10;                                 // Default x border value is 10
    yBorder = 5;                                  // Default y border value is 5
  }
  
  /**
   * Draws the graph.
   */
  public void draw()
  {
    drawGrid();
    
    // Draw points on graph
    fill(255, 206, 43);
    for (int i = 0; i < pointList.size(); i++)
    {
      float[] values = pointList.get(i);
      if (values[0] > xBorder)
        xBorder += 10;
      if (yBorder * -1 > values[1] || values[1] > yBorder)
        yBorder += 5;
      ellipse(80 + values[0] * 600 / xBorder, yPosition + 125 + values[1] * -30 / yBorder, 2, 2);
    }
  } // end draw
  
  /**
   * Draws a grid with a numbered scale and frame.
   */
  private void drawGrid()
  {
    // Set colours and text style
    stroke(66, 65, 61);
    fill(178, 175, 172);
    textSize(16);
    textAlign(CENTER, CENTER);
    
    // Draw grid lines
    for (int row = 1; row < 7; row++)
      line(35, yPosition + 80 + row * 15, 695, yPosition + 80 + row * 15);
    for (int col = 0; col < 45; col++)
      line(col * 15 + 35, yPosition + 80, col * 15 + 35, yPosition + 170);
      
    // Draw number scale
    for (int row = 0; row < 3; row++)
      text("" + (yBorder - row * yBorder), 50, yPosition + 95 + row * 30);
    for (int col = 0; col < 11; col++)
      text("" + col * xBorder / 10, col * 60 + 80, yPosition + 140);
    
    // Highlight the horizontal grid line along the number scale
    stroke(178, 175, 172);
    line(65, yPosition + 125, 695, yPosition + 125);
    
    // Draw a frame around the grid
    noStroke();
    rect(30, yPosition + 75, 670, 5);
    rect(30, yPosition + 170, 670, 5);
    rect(30, yPosition + 80, 5, 90);
    rect(695, yPosition + 80, 5, 90);
  } // end drawGrid
  
  /**
   * Updates the graph.
   */
  public void update(float x, float y)
  {
    float[] point = {x, y};
    pointList.add(point);
  }
  
  /**
   * Resets all values of the graph.
   */
  public void reset()
  {
    pointList.clear();
    xBorder = 10;
    yBorder = 5;
  }
  
} // end class Graph
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
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: MenuScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The MenuScreen class displays the menu screen.
 */

class MenuScreen extends Screen
{ 
  private Button simulatorButton;                 // Simulator button
  private Button tutorialButton;                  // Tutorial button
  private Button aboutButton;                     // About button
  private Button exitButton;                      // Exit button
  private Ball[] balls;                           // Array of balls in the background
  private CursorBall cursorBall;                  // Cursor-following ball
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public MenuScreen()
  {
    super(ScreenType.MENU);
    setup();
  }
  
  /**
   * Sets up the menu. Initializes buttons and balls.
   */ 
  private void setup()
  {
    simulatorButton = new Button("Motion Simulator", 410, 215, 270, 40);
    tutorialButton = new Button("Tutorial", 470, 275, 150, 40);
    aboutButton = new Button("About", 485, 335, 120, 40);
    exitButton = new Button("Exit", 505, 395, 80, 40);
    
    // Initialize balls for background display
    balls = new Ball[10];
    for (int i = 0; i < 10; i++)
      balls[i] = new Ball(random(width), random(height), random(60, 200), i + 1, balls);
    cursorBall = new CursorBall(mouseX, mouseY, balls);
  } // end setup

  /**
   * Draws the menu screen.
   */
  @Override
  public void draw()
  {
    // Draw background and balls
    background(30, 29, 27);
    noStroke();
    fill(66, 65, 61);
    for (Ball ball : balls)
      ball.draw();
    
    // Set colour and text
    fill(178, 175, 172);
    textAlign(CENTER, CENTER);
    textFont(loadFont("CenturyGothic-50.vlw"));
    textSize(50);
    
    // Draw header and buttons
    text("Menu", 545, 145);
    simulatorButton.draw();
    tutorialButton.draw();
    aboutButton.draw();
    exitButton.draw();
    
    // Draw cursor ball
    cursorBall.draw();
  } // end draw
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
    if (simulatorButton.getClicked())
      return ScreenType.SIMULATOR;
    else if (tutorialButton.getClicked())
      return ScreenType.TUTORIAL;
    else if (aboutButton.getClicked())
      return ScreenType.ABOUT;
    else if (exitButton.getClicked())
      return ScreenType.EXIT;
    return null;
  }
  
  /**
   * Responds to mouse presses. Updates the buttons if the mouse is pressed.
   */
  @Override
  public void mousePressed()
  {
    simulatorButton.mousePressed();
    tutorialButton.mousePressed();
    aboutButton.mousePressed();
    exitButton.mousePressed();
  }
  
  /**
   * Responds to mouse releases. Updates the buttons if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    simulatorButton.mouseReleased();
    tutorialButton.mouseReleased();
    aboutButton.mouseReleased();
    exitButton.mouseReleased();
  }
 
} // end class MenuScreen
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
  private int colour;                           // Colour of the number field
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
    position.setValue(position.getInitialValue() + (velocity.getValue() + velocity.getInitialValue()) * 0.5f * time);
    
    // If the object has collided, stop the object and update its collision status
    if (position.getValue() < -10.0000f)
    {
      position.setValue(-10.0000f);
      collided = true;
    }
    else if (position.getValue() > 10.0000f)
    {
      position.setValue(10.0000f);
      collided = true;
    }
  } // end updateMotion

} // end class Object
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Screen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Screen class displays a screen.
 */

static class Screen
{ 
  private ScreenType screenType;                  // Screen type of the Screen
  
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param screenType The screen type of the Screen
   */
  public Screen(ScreenType screenType)
  {
    this.screenType = screenType;
  }
  
  /**
   * Returns the screen type of the screen.
   * @return The screen type of the screen.
   */
  public ScreenType getScreenType()
  {
    return screenType;
  }
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  public ScreenType getScreenChange()
  {
    return null;
  }
  
  public void draw() {}
  
  public void mouseReleased() {}
  
  public void mousePressed() {}
  
  public void keyPressed() {}
  
} // end class Screen
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: ScreenType
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: ScreenType enumerated data type.
 */
 
enum ScreenType {START, MENU, SIMULATOR, TUTORIAL, ABOUT, EXIT}
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: SimulatorScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The SimulatorScreen class displays the motion simulator screen.
 */



class SimulatorScreen extends Screen
{
  private DecimalFormat defaultDecimalFormat;     // Default decimal format
  private DecimalFormat shortDecimalFormat;       // Shortened decimal format
  private Object object;                          // Object being simulated
  private Timer timer;                            // Timer
  private Label timeLabel;                        // Label for the time
  private Label positionLabel;                    // Label for the position
  private Label velocityLabel;                    // Label for the velocity
  private Label accelerationLabel;                // Label for the acceleration
  private Switch pauseSwitch;                     // Pause switch
  private Button resetButton;                     // Reset button
  private Button menuButton;                      // Menu button
  private NumberField positionField;              // Number field for the object's position
  private NumberField velocityField;              // Number field for the object's velocity
  private NumberField accelerationField;          // Number field for the object's acceleration
  private NumberField positionEquationField;      // Number field for the object's position in the equation
  private NumberField velocityEquationField;      // Number field for the object's velocity in the equation
  private NumberField accelerationEquationField;  // Number field for the object's acceleration in the equation
  private Graph positionGraph;                    // Graph for the object's position
  private Graph velocityGraph;                    // Graph for the object's velocity
  private Graph accelerationGraph;                // Graph for the object's acceleration
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public SimulatorScreen()
  {
    super(ScreenType.SIMULATOR);
    setup();
  }
  
  /**
   * Constructor that sets the values of all fields to the arguments passed in.
   * @param isTutorial Whether the screen is in tutorial mode
   */
  public SimulatorScreen(boolean isTutorial)
  {
    super(ScreenType.TUTORIAL);
    setup();
  }
  
  /**
   * Sets up the simulation.
   * Initializes the: object, timer, label, number fields, switch, and buttons.
   */ 
  private void setup()
  {
    textFont(loadFont("CenturyGothic-24.vlw"));   // Default font
    defaultDecimalFormat = new DecimalFormat("0.0000");             // Default decimal format
    shortDecimalFormat = new DecimalFormat("0.0");                  // Shortened decimal format
    
    // Initialize the object being simulated and the timer
    object = new Object();
    timer = new Timer();
      
    // Initialize labels
    timeLabel = new Label("Time: " + defaultDecimalFormat.format(timer.getTime()), 730, 30, 330, 30);
    positionLabel = new Label("d(t) =             t  +             t +", 730, 250, 330, 30);
    velocityLabel = new Label("d'(t) = " + shortDecimalFormat.format(object.velocity.getValue()), 730, 380, 330, 30);
    accelerationLabel = new Label("d\"(t) = " + shortDecimalFormat.format(object.acceleration.getValue()), 730, 510, 330, 30);
    
    // Initialize number fields
    positionField = new NumberField("Position", defaultDecimalFormat.format(object.position.getValue()), 730, 190, 330, 30);
    velocityField = new NumberField("Velocity", defaultDecimalFormat.format(object.velocity.getValue()), 730, 320, 330, 30);
    accelerationField = new NumberField("Acceleration", defaultDecimalFormat.format(object.acceleration.getValue()), 730, 450, 330, 30);
    positionEquationField = new NumberField("" + shortDecimalFormat.format(object.position.getValue()), 993, 250, 60, 30);
    velocityEquationField = new NumberField("" + shortDecimalFormat.format(object.velocity.getValue()), 900, 250, 60, 30);
    accelerationEquationField = new NumberField("" + shortDecimalFormat.format(object.acceleration.getValue() / 2), 805, 250, 60, 30);
    
    // Initialize graphs
    positionGraph = new Graph(115);
    velocityGraph = new Graph(245);
    accelerationGraph = new Graph(375);
      
    // Initialize switches and buttons
    pauseSwitch = new Switch("pause", "play", 730, 90, 90, 40, true);
    resetButton = new Button("reset", 850, 90, 90, 40);
    menuButton = new Button("menu", 970, 90, 90, 40);
    
    // If in tutorial mode, disable buttons, switches, and fields
    if (super.getScreenType() == ScreenType.TUTORIAL)
    {
      positionField.setEnabled(false);
      velocityField.setEnabled(false);
      accelerationField.setEnabled(false);
      positionEquationField.setEnabled(false);
      velocityEquationField.setEnabled(false);
      accelerationEquationField.setEnabled(false);
      pauseSwitch.setEnabled(false);
      resetButton.setEnabled(false);
      menuButton.setEnabled(false);
    }
  } // end setup

  /**
   * Draws the simulation screen.
   */
  @Override
  public void draw()
  {
    // Update object motion and labels
    update();
      
    // Set background to dark gray
    background(30, 29, 27);
      
    // Draw grid with scale and frame
    drawGrid();
      
    // Draw object, labels, number fields, switch, and buttons
    object.draw();
    timeLabel.draw();
    positionLabel.draw();
    velocityLabel.draw();
    accelerationLabel.draw();
    positionField.draw();
    velocityField.draw();
    accelerationField.draw();
    positionEquationField.draw();
    velocityEquationField.draw();
    accelerationEquationField.draw();
    pauseSwitch.draw();
    resetButton.draw();
    menuButton.draw();
    positionGraph.draw();
    velocityGraph.draw();
    accelerationGraph.draw();
    
    if(positionEquationField.getVisible())
    {
      fill(178, 175, 172);
      textSize(12);
      text("2", 880, 257);
    }
  }

  /**
   * Draws a grid with a numbered scale and frame.
   */
  private void drawGrid()
  {
    // Set colours and text style
    stroke(66, 65, 61);
    fill(178, 175, 172);
    textSize(16);
    textAlign(CENTER, CENTER);
    
    // Draw vertical grid lines and a number scale
    for (int col = 0; col < 21; col++)
    {
      line(col * 30 + 65, 35, col * 30 + 65, 155);
      text("" + (-10 + col), col * 30 + 65, 140);
    }
    
    // Draw horizontal grid lines
    line(30, 65, 695, 65);
    line(30, 95, 695, 95);
    stroke(178, 175, 172);
    line(30, 125, 695, 125);
    
    // Draw a frame around the grid
    noStroke();
    rect(30, 30, 670, 5);
    rect(30, 155, 670, 5);
    rect(30, 35, 5, 125);
    rect(695, 35, 5, 125);
  } // end drawGrid

  /**
   * Updates the timer, object, and labels.
   */
  private void update()
  { 
    // Update the timer
    timer.updateTime();
    
    // If the timer is running, update object, number fields, and graphs
    if (!timer.getPaused())
    {
      object.updateMotion(timer.getLapTime());
      updateNumberFields();
      positionGraph.update(timer.getTime(), object.position.getValue());
      velocityGraph.update(timer.getTime(), object.velocity.getValue());
      accelerationGraph.update(timer.getTime(), object.acceleration.getValue());
    }
    
    // Update the number fields if they have been deselected
    if (!positionField.getSelected() && !velocityField.getSelected() && !accelerationField.getSelected())
      updateNumberFields();
    
    // Stop the object's motion and pause the simulation if it has collided with a frame border
    if (object.didCollide())
    {
      object.stop();
      timer.setPaused(true);
      pauseSwitch.setToggled(true);
      updateNumberFields();
    }
    
    // Update time label
    timeLabel.setText("Time: " + defaultDecimalFormat.format(timer.getTime()));
  } // end update
  
  /**
   * Updates the position, velocity, and acceleration labels.
   */
  private void updateObjectLabels()
  {
    // Equations are unavailable for complex (piecewise) functions
    if (timer.getTime() != 0)
    {
      positionLabel.setText("No equation.");
      velocityLabel.setText("No equation.");
      accelerationLabel.setText("No equation.");
      positionEquationField.setVisible(false);
      velocityEquationField.setVisible(false);
      accelerationEquationField.setVisible(false);
    }
    else
    {
      // Update fields
      positionEquationField.setVisible(true);
      velocityEquationField.setVisible(true);
      accelerationEquationField.setVisible(true);
      if(!positionEquationField.getSelected())
        positionEquationField.setField("" + shortDecimalFormat.format(object.position.getValue()));
      if(!velocityEquationField.getSelected())
        velocityEquationField.setField("" + shortDecimalFormat.format(object.velocity.getValue()));
      if(!accelerationEquationField.getSelected())
        accelerationEquationField.setField("" + shortDecimalFormat.format(object.acceleration.getValue() / 2));
      
      // Update labels
      positionLabel.setText("d(t) =             t  +             t +");
      if (object.acceleration.getValue() == 0)
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.velocity.getValue()));
      else if (object.velocity.getValue() == 0)
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.acceleration.getValue()) + "t");
      else
        velocityLabel.setText("d'(t) = " + shortDecimalFormat.format(object.acceleration.getValue()) + "t + " + shortDecimalFormat.format(object.velocity.getValue()));
      accelerationLabel.setText("d\"(t) = " + shortDecimalFormat.format(object.acceleration.getValue()));
    }
  } // end updateObjectLabels
  
  /**
   * Updates the number fields.
   */
  private void updateNumberFields()
  {
    positionField.setField(defaultDecimalFormat.format(object.position.getValue()));
    velocityField.setField(defaultDecimalFormat.format(object.velocity.getValue()));
    accelerationField.setField(defaultDecimalFormat.format(object.acceleration.getValue()));
  }
  
  /**
   * Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
    if (menuButton.getClicked())
      return ScreenType.MENU;
     return null;
  }
  
  /**
   * Updates the position of the object being simulated.
   */
  private void updatePosition(NumberField updatedField)
  {
    object.position.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
  }
  
  /**
   * Updates the velocity of the object being simulated.
   */
  private void updateVelocity(NumberField updatedField)
  {
    object.velocity.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
    object.velocity.updateInitialValue();
  }
  
  /**
   * Updates the acceleration of the object being simulated.
   */
  private void updateAcceleration(NumberField updatedField)
  {
    object.acceleration.setValue(Float.parseFloat(updatedField.getField()));
    object.position.updateInitialValue();
    object.velocity.updateInitialValue();
    object.acceleration.updateInitialValue();
  }
  
  /**
   * Resets the simulation.
   */
  private void reset()
  {
    pauseSwitch.setToggled(true);
    timer.reset();
    object.stop();
    object.position.reset();
    updateNumberFields();
    positionGraph.reset();
    velocityGraph.reset();
    accelerationGraph.reset();
    updateObjectLabels();
  }
    
  /**
   * Responds to mouse presses. Updates the buttons, switches, and number fields if the mouse is pressed.
   */
  @Override
  public void mousePressed()
  {
    pauseSwitch.mousePressed();
    resetButton.mousePressed();
    menuButton.mousePressed();
    
    // Only update number fields if the simulation is paused
    if (timer.getPaused())
    {
      positionField.mousePressed();
      velocityField.mousePressed();
      accelerationField.mousePressed();
      positionEquationField.mousePressed();
      velocityEquationField.mousePressed();
      accelerationEquationField.mousePressed();
    }
    
    // If number fields were edited, update the object's motion and reset the timer lap
    if (positionField.getEdited())
      updatePosition(positionField);
    else if (positionEquationField.getEdited())
      updatePosition(positionEquationField);
    else if (velocityField.getEdited())
      updateVelocity(velocityField);
    else if (velocityEquationField.getEdited())
      updateVelocity(velocityEquationField);
    else if (accelerationField.getEdited())
      updateAcceleration(accelerationField);
    else if (accelerationEquationField.getEdited())
    {
      accelerationEquationField.setField("" + Float.parseFloat(accelerationEquationField.getField()) * 2);
      updateAcceleration(accelerationEquationField);
    }
    if (positionField.getEdited() || velocityField.getEdited() || accelerationField.getEdited() || positionEquationField.getEdited() || velocityEquationField.getEdited() || accelerationEquationField.getEdited())
    {
      positionField.setEdited(false);
      velocityField.setEdited(false);
      accelerationField.setEdited(false);
      positionEquationField.setEdited(false);
      velocityEquationField.setEdited(false);
      accelerationEquationField.setEdited(false);
      updateObjectLabels();
      timer.resetLap();
    }
  } // end mousePressed
  
  /**
   * Responds to mouse releases. Updates the buttons if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    pauseSwitch.mouseReleased();
    resetButton.mouseReleased();
    menuButton.mouseReleased();
    
    // If the pause switch is toggled, pause the simulation
    timer.setPaused(pauseSwitch.getToggled());
    
    // If the reset button was clicked, reset the simulation
    if (resetButton.getClicked())
    {
      resetButton.setClicked(false);
      reset();
    }
  } // end mouseReleased
  
  /**
   * Responds to key presses. Updates text fields if a key was pressed.
   */
  @Override
  public void keyPressed()
  {
    positionField.keyPressed();
    velocityField.keyPressed();
    accelerationField.keyPressed();
    positionEquationField.keyPressed();
    velocityEquationField.keyPressed();
    accelerationEquationField.keyPressed();
    
    // If the value entered for position is invalid, set it to 0
    if (positionField.getField().length() >= 2 && (Float.parseFloat(positionField.getField()) > 10 || Float.parseFloat(positionField.getField()) < -10))
      positionField.setField("0.0000");
    if (positionEquationField.getField().length() >= 2 && (Float.parseFloat(positionEquationField.getField()) > 10 || Float.parseFloat(positionEquationField.getField()) < -10))
      positionEquationField.setField("0.0");
  } // end keyPressed
 
} // end class SimulatorScreen
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: StartScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The StartScreen class displays the start screen.
 */

class StartScreen extends Screen
{ 
  private float opacity;                          // Opacity of the start animation
  private PVector position;                       // Position of shape
  private PVector velocity;                       // Velocity of shape
  private PVector gravity;                        // Gravity acting on shape
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public StartScreen()
  {
    super(ScreenType.START);
    setup();
  }
  
  /**
   * Sets up the start screen.
   */
  private void setup()
  {
    position = new PVector(-20, -5);
    velocity = new PVector(2.2f, 0);
    gravity = new PVector(0, 0.4f);
    textFont(loadFont("CenturyGothic-50.vlw"));
  }
  
  /**
   * Draws the start screen.
   */
  @Override
  public void draw()
  {
    // Set opacity to fade in and out
    if (millis() < 3500)
      opacity = (millis() - 700) / 10;
    else if (millis() > 4000)
      opacity = 255 - ((millis() - 4000) / 4);
    else
      opacity = 255;
    
    // Set colour and text style
    background(30, 29, 27);
    fill(255, 206, 43, opacity);
    textAlign(CENTER, CENTER);
    textSize(50);
    
    // Draw animated 'o' and title
    drawAnimation();
    text("M   tion Simulator", 545, 280);
    
    // Set colour and text style
    fill(178, 175, 172, opacity);
    textSize(24);
    
    // Draw credit
    text("By: Jennifer Lu", 545, 330);
  } // end draw
  
  /**
   * Animates the 'o'.
   */
  private void drawAnimation()
  {
    // Update position and velocity
    position.add(velocity);
    velocity.add(gravity);
    
    // Bounce at lower bound
    if (position.y > 280)
    {
      velocity.y = velocity.y * -0.55f; 
      position.y = 280;
    }
    
    // Stop moving horizontally at final position
    if (position.x > 409)
      position.x = 409;
    
    // Stop bouncing (moving vertically) near final position
    if (position.x > 387)
      position.y = 280;
    
    // Draw the 'o'
    text("o", position.x, position.y);
  } // end drawAnimation
 
} // end class StartScreen
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
  private int colour;                           // Colour of the switch
 
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
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: Timer
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The Timer class simulates stopwatch-style timers.
 */

public class Timer
{
  private float time;                             // Time elapsed while the timer was running (in milliseconds)
  private float timePaused;                       // Time elapsed while the timer was paused (in milliseconds)
  private float lapTime;                          // Time elapsed in the current lap while the timer was running  (in milliseconds)
  private float lapTimePaused;                    // Time elapsed in the current lap while the timer was paused  (in milliseconds)
  private boolean isPaused;                       // Whether the timer is paused
   
  /**
   * Constructor that sets the values of all fields to their default values.
   */
  public Timer()
  {
    // Start with the time at 0 and paused
    time = 0;
    timePaused = 0;
    lapTime = 0;
    lapTimePaused = 0;
    isPaused = true;
  }
  
  /**
   * Returns the time elapsed while the Timer was running (in seconds).
   * @return The time elapsed while the Timer was running (in seconds)
   */
  public float getTime()
  {
    return time / 1000;
  }
  
  /**
   * Returns the time elapsed in the current lap while the Timer was running (in seconds).
   * @return The time elapsed in the current lap while the Timer was running (in seconds)
   */
  public float getLapTime()
  {
    return lapTime / 1000;
  }
  
  /**
   * Returns whether the Timer is paused.
   * @return Whether the Timer is paused
   */
  public boolean getPaused()
  {
    return isPaused;
  }
  
  /**
   * Sets whether the Timer is paused to the value passed in.
   * @param isPaused Whether the Timer is paused
   */
  public void setPaused(boolean isPaused)
  {
    this.isPaused = isPaused;
  }
  
  /**
   * Updates the time elapsed.
   */
  public void updateTime()
  {
    // If the timer is paused, update the time elapsed while paused
    if (isPaused)
    {
      timePaused = millis() - time;
      lapTimePaused = millis() - lapTime;
    }
    // If not, update the time elapsed while running
    else
    {
      time = millis() - timePaused;
      lapTime = millis() - lapTimePaused;
    }
  } // end updateTime
  
  /**
   * Resets the lap time.
   */
  public void resetLap()
  {
    lapTime = 0;
    lapTimePaused = millis();
  }
  
  /**
   * Resets all values of the Timer.
   */
  public void reset()
  {
    time = 0;
    timePaused = millis();
    lapTime = 0;
    lapTimePaused = millis();
    isPaused = true;
  }
  
} // end class Timer
/**
 *     Project: ICS4U1 Final Project
 *      Prompt: Motion simulator for Tony (application for a client).
 *        File: TutorialScreen
 *     Version: 1.0
 *      Author: Jennifer Lu
 * Last Edited: May 30, 2020
 * Description: The TutorialScreen class displays the tutorial screen.
 */

class TutorialScreen extends SimulatorScreen
{
  private boolean finished;                       // Whether the tutorial is finished
  private int tutorialNumber;                     // The tutorial number
  private Label tutorialLabel;                    // The tutorial label
  
  /**
   * Constructor that sets the default values of all fields.
   */
  public TutorialScreen()
  {
    super(true);
    finished = false;
    tutorialNumber = 1;
  }
  
  /**
   * Draws the tutorial screen.
   */
  @Override
  public void draw()
  {
    super.draw();
    
    // Draws the tutorial label based on the tutorial number
    switch (tutorialNumber)
    {
      case 1:
        String[] textBlock = {"Welcome to the tutorial!", "Here you will learn how to use the motion simulator."};
        tutorialLabel = new Label(textBlock, 255, 245, 580, 115);
        break;
      case 2:
        drawTutorial2();
        break;
      case 3:
        drawTutorial3();
        break;
      case 4:
        drawTutorial4();
        break;
      case 5:
        drawTutorial5();
        break;
      case 6:
        drawTutorial6();
        break;
      case 7:
        drawTutorial7();
        break;
      case 8:
        drawTutorial8();
        break;
      case 9:
        String[] textBlock2 = {"That's all there is!", "Now you can try the real deal."};
        tutorialLabel = new Label(textBlock2, 355, 233, 380, 115);
        break;
      default:
        break;
    }
    tutorialLabel.draw();
  } // end draw
  
  /**
   * Draws the second tutorial.
   */
  private void drawTutorial2()
  {
    String[] textBlock = {"This is the object.", "It will move based on the parameters that you enter."};
    tutorialLabel = new Label(textBlock, 78, 183, 580, 115);
    setColour();
    rect(315, 45, 100, 100);
  }
  
  /**
   * Draws the third tutorial.
   */
  private void drawTutorial3()
  {
    String[] textBlock = {"These are the fields in which you can enter those parameters.", 
        "You can adjust the position, velocity, and acceleration.",
        "You may enter positive and negative decimal values.",
        "For example, Position: 10, Velocity: -6, Acceleration: 1",
        "or, Position: -7, Velocity: 2.5, Acceleration: -0.2."};
    tutorialLabel = new Label(textBlock, 20, 220, 690, 220);
    setColour();
    rect(720, 180, 350, 50);
    rect(720, 310, 350, 50);
    rect(720, 440, 350, 50);
  }
  
  /**
   * Draws the fourth tutorial.
   */
  private void drawTutorial4()
  {
    String[] textBlock = {"You can also enter an equation to",
        "control how the object moves.",
        "This is the equation for the object's position."};
    tutorialLabel = new Label(textBlock, 210, 200, 500, 150);
    setColour();
    rect(720, 240, 350, 50);
  }
  
  /**
   * Draws the fifth tutorial.
   */
  private void drawTutorial5()
  {
    String[] textBlock = {"This is the timer.", "It will display the time elapsed as the object moves."};
    tutorialLabel = new Label(textBlock, 130, 20, 580, 115);
    setColour();
    rect(720, 20, 350, 50);
  }
  
  /**
   * Draws the sixth tutorial.
   */
  private void drawTutorial6()
  {
    String[] textBlock = {"These are the graphs for each parameter.",
        "They will display the position, velocity, and acceleration of the object over time."};
    tutorialLabel = new Label(textBlock, 20, 55, 870, 115);
    setColour();
    rect(20, 180, 690, 120);
    rect(20, 310, 690, 120);
    rect(20, 440, 690, 120);
  }
  
  /**
   * Draws the seventh tutorial.
   */
  private void drawTutorial7()
  {
    String[] textBlock = {"These are the equations of the",
        "velocity and acceleration graphs.",
        "They are the first and second derivatives",
        "of the position equation, respectively."};
    tutorialLabel = new Label(textBlock, 240, 365, 470, 190);
    setColour();
    rect(720, 370, 350, 50);
    rect(720, 500, 350, 50);
  }
  
  /**
   * Draws the eighth tutorial.
   */
  private void drawTutorial8()
  {
    String[] textBlock = {"These are the buttons.",
        "You can use them to play and pause the object's motion,",
        "to reset all parameters to zero, and to return to the menu."};
    tutorialLabel = new Label(textBlock, 60, 20, 650, 150);
    setColour();
    rect(720, 80, 110, 60);
    rect(840, 80, 110, 60);
    rect(960, 80, 110, 60);
  }
  
  /**
   * Sets colours.
   */
  private void setColour()
  {
    noFill();
    stroke(255, 206, 43);
  }
  
  /* Returns the screen type that the user should be directed to.
   * @return The screen type that the user should be directed to
   */
  @Override
  public ScreenType getScreenChange()
  {
   if (finished)
      return ScreenType.SIMULATOR;
    return null;
  }
  
  /**
   * Ignores mouse presses.
   */
  @Override
  public void mousePressed()
  {
  }
  
  /**
   * Responds to mouse releases. Advances the tutorial number if the mouse is released.
   */
  @Override
  public void mouseReleased()
  {
    tutorialNumber++;
    if (tutorialNumber == 10)
      finished = true;
  }
  
} // end class TutorialScreen
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
  public void settings() {  size(1090, 580); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MotionSimulator" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
