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
  void resetLap()
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
