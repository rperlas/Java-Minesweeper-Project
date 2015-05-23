/*
   Name: Ronald Perlas
   Teacher: Ms. Krasteva
   Date: 02/04/13
   Assignment: This program will create s star animation.
*/

// Import Statements
import java.awt.*; // Imports Java Graphics
import hsa.Console; // Imports Console Class
import java.lang.*; // Imports Thread Class

// Stars Class - Inherits Thread Class
public class Stars extends Thread
{
    // Declaration Section
    private Console c; // Reference Variable To Console CLass
    int x, y; // For Loop Variables
    private int height = 0; // Overloaded Variable - Determines Height

    // Koi Fish Drawing Method
    public void stars ()
    {
	// Animation Loop
	for (x = -100 ; x <= 640 ; x++)
	{
	    
	    c.setColor (Color.black);
	    c.fillRect (x - 1, 0 + height, 30, 30);
	    c.setColor (Color.yellow);
	    c.fillStar (x, 0 + height, 30, 30);
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
    public Stars (Console con)
    {
	c = con;
    }
    
    // Overloaded Class Constructor
    public Stars (Console con, int vertical)
    {
	c = con;
	height = vertical;
    }

    
    // Run Method - Executes Stars Method
    public void run ()
    {
	stars ();
    }
}

