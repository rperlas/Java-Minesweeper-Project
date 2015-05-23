/*
   Name: Ronald Perlas
   Teacher: Ms. Krasteva
   Date: 02/04/13
   Assignment: This program will create a rocket animation.
*/

// Import Statements
import java.awt.*;  // Imports Java Graphics
import hsa.Console; // Imports Console Class
import java.lang.*; // Imports Thread Class

// Stars Class - Inherits Thread Class
public class Rocket extends Thread
{
    // Declaration Section
    private Console c;       // Reference Variable To Console CLass
    int x, y;                // For Loop Variables
    private int depth = 0;   // Overloaded Variable - Determines Height

    // Koi Fish Drawing Method
    public void rocket ()
    {
	// Animation Loop
	for (x = 600 ; x >= -100 ; x--)
	{
	    c.setColor (Color.black);
	    c.fillRect (50 + depth, x + 1, 100, 90);
	    c.setColor (Color.red);
	    int [] rocketX = {100 + depth, 130 + depth, 115 + depth};
	    int [] rocketY = {x, x, x - 20};
	    c.fillPolygon (rocketX, rocketY, 3);
	    int [] tailX = {80 + depth, 150 + depth, 115 + depth};
	    int [] tailY = {x + 60, x + 60, x + 30};
	    c.fillPolygon (tailX, tailY, 3);
	    c.setColor (Color.white);
	    c.fillRect (100 + depth, x, 30, 60);
	    // Animation Delay
	    try
	    {
		Thread.sleep (8);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }

    // Class Constructor
    public Rocket (Console con)
    {
	c = con;
    }
    
    // Overloaded Class Constructor
    public Rocket (Console con, int horizontal)
    {
	c = con;
	depth = horizontal;
    }
    
    // Run Method - Executes Stars Method
    public void run ()
    {
	rocket ();
    }
}
