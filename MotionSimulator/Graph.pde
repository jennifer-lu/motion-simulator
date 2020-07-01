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
