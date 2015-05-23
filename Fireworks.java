/*
   Name: Ronald Perlas
   Teacher: Ms. Krasteva
   Date: 02/04/13
   Assignment: This program will create a fireworks animation using the Thread 
	       class and java fill commands.
*/

// Import Statements
import java.awt.*; // Imports Java Graphics
import hsa.Console; // Imports Console Class
import java.lang.*; // Imports Thread Class

// Fireworks Class - Inherits Thread Class
public class Fireworks extends Thread
{
    //  Declaration Section
    private Console c;        // Reference Variable To Console Class
    int x, y;                 // For Loop Variables
    private int vertical = 0; // Firework Location Varible

    // Color Section
    Color fireCol = new Color (0, 0, 0); // Used For Firework Colors

    // Fireworks Drawing Method
    public void fireworks ()
    {
	// Draws Fireworks Launch
	for (x = 145 ; x >= 80 ; x--)
	{
	    // Erases Launching Flare
	    c.setColor (Color.black);
	    c.fillOval (400 + vertical, x + 1, 5, 5);
	    // Draws Launching Flare
	    c.setColor (fireCol);
	    c.fillOval (400 + vertical, x, 5, 5);
	    
	    // Animation Delay
	    try
	    {
		Thread.sleep (20);
	    }
	    catch (Exception e)
	    {
	    }
	}
	
	// Draws Fireworks Burst
	for (x = 0 ; x <= 10 ; x++)
	{
	
	    // Draws Parabola Shape
	    int one = (int) Math.round (0.3 * Math.pow (x, 2));
	    c.setColor (fireCol);
	    c.fillStar (400 + vertical + x, 80 + one, 5, 5);
	    c.fillStar (400 + vertical + x, 80 - one, 5, 5);
	    c.fillStar (400 + vertical - x, 80 + one, 5, 5);
	    c.fillStar (400 + vertical - x, 80 - one, 5, 5);
	    c.fillStar (400 + vertical + one, 80 + x, 5, 5);
	    c.fillStar (400 + vertical - one, 80 + x, 5, 5);
	    c.fillStar (400 + vertical + one, 80 - x, 5, 5);
	    c.fillStar (400 + vertical - one, 80 - x, 5, 5);
	    
	    // Animation Delay
	    try
	    {
		Thread.sleep (50);
	    }
	    catch (Exception e)
	    {
	    }
	}

	// Erases Fireworks
	for (x = 0 ; x <= 40 ; x++)
	{
	    // Erases From Center
	    c.setColor (Color.black);
	    c.fillOval (402 + vertical - x, 82 - x, 2 * x, 2 * x);
	    
	    // Animation Delay
	    try
	    {
		Thread.sleep (40);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }

    // Class Constructor - Changeable Parameters
    public Fireworks (Console con, int v, Color paint)
    {
	fireCol = paint; // Determines Color
	vertical = v; // Determines Vertical Location
	c = con;
    }

    // Run Method - Executes Fireworks Method
    public void run ()
    {
	fireworks ();
    }
}

