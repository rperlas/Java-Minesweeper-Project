/*  Name:       Ronald Perlas
    Teacher:    Ms. Krasteva
    Date:       07/06/13
    Assignment: This program creates a full Minesweeer game conisting of three levels, animations and high scores.
		The user is presented with various game options and the program runs until he/she decides to quit.
*/

// Import Statements
import hsa.*;           // Imports HSA Console Superclass
import java.io.*;       // Imports Java Input/Output
import java.awt.*;      // Imports Java Graphics Class
import java.awt.Font;   // Imports Java Fonts Subclass

// Minesweeper Class
public class Minesweeper
{
    // Declaration Section
    Console c;                  // Reference Variable to Console Class
    int x = 0, y = 0;           // For Loop Variables
    static int choice;          // Menu Choice Variable
    double score;               // Game Score
    boolean game = false;       // Game Three Level Loop Boolean Variable
    String theme = "";          // Initial Default Theme

    // Font Chart
    Font monospaced = new Font ("Monospaced", Font.PLAIN, 20);      // Large-Scale Font
    Font serif = new Font ("Serif", Font.PLAIN, 13);                // Basic Font
    Font roman = new Font ("Roman_Baseline", Font.BOLD, 15);        // Small-Scale Font

    // Class Constructor
    public Minesweeper ()
    {
	c = new Console ("Minesweeper");
    }


    // Program Title - Clears Screen and Draws Title
    private void title ()
    {
	// Radiance Theme
	if (theme.equals ("Radiance"))
	{
	    for (x = 0 ; x <= 500 ; x++)
	    {
		Color radiance = new Color (60, 200 - x / 3, 200 - x / 20);
		c.setColor (radiance);
		c.drawLine (0, x, 640, x);
	    }
	}
	//  Slime Theme
	else if (theme.equals ("Slime"))
	{
	    for (x = 0 ; x <= 500 ; x++)
	    {
		Color slime = new Color (0, 200 - x / 3, x / 5);
		c.setColor (slime);
		c.drawLine (0, x, 640, x);
	    }
	}
	// Crimsanity Theme
	else if (theme.equals ("Crimsanity"))
	{
	    for (x = 0 ; x <= 500 ; x++)
	    {
		Color crimsanity = new Color (200 - x / 3, 0, 0);
		c.setColor (crimsanity);
		c.drawLine (0, x, 640, x);
	    }
	}
	// Solaris Theme
	else if (theme.equals ("Solaris"))
	{
	    for (x = 0 ; x <= 500 ; x++)
	    {
		Color solaris = new Color (200 - x / 3, 200, x / 5);
		c.setColor (solaris);
		c.drawLine (0, x, 640, x);
	    }
	}
	// Default Theme
	else
	{
	    Color spaceBlue = new Color (1, 4, 81);
	    c.setColor (spaceBlue);
	    c.fillRect (0, 0, 640, 500);
	}
	c.setColor (Color.white);
	c.setFont (roman);
	c.drawString ("Minesweeper", 300, 20);
    }


    // Pause Program - Receives input from user to continue
    private void pauseProgram ()
    {
	c.setColor (Color.white);
	c.setFont (monospaced);
	c.drawString ("Press any key to continue . . .", 180, 400);
	c.getChar ();
    }


    // SplashScreen Animation - Adds Stars, Rocket Classes
    public void splashScreen ()
    {
	// Creates Overloaded KoiFish Threads
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	Stars m = new Stars (c);
	Stars n = new Stars (c, 100);
	Stars o = new Stars (c, 200);
	Stars p = new Stars (c, 300);
	Stars q = new Stars (c, 400);
	Rocket r = new Rocket (c);
	Rocket s = new Rocket (c, 200);
	Rocket t = new Rocket (c, 400);
	Fireworks f = new Fireworks (c, 100, Color.RED);
	Fireworks g = new Fireworks (c, -100, Color.RED);
	Fireworks h = new Fireworks (c, -300, Color.RED);
	// Starts KoiFish Threads
	m.start ();
	n.start ();
	o.start ();
	p.start ();
	q.start ();
	r.start ();
	s.start ();
	t.start ();
	f.start ();
	g.start ();
	h.start ();
	// Joins Existing Threads
	try
	{
	    m.join ();
	    n.join ();
	    o.join ();
	    p.join ();
	    q.join ();
	    r.join ();
	    s.join ();
	    t.join ();
	    f.join ();
	    g.join ();
	    h.join ();
	    InputStream is = this.getClass ().getResourceAsStream ("bloktilt.ttf");
	    Font deltaBase = Font.createFont (Font.TRUETYPE_FONT, is);
	    Font deltaReal = deltaBase.deriveFont (Font.TRUETYPE_FONT, 75);
	    c.setFont (deltaReal);
	    c.drawString ("MINESWEEPER", 70, 250);
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
    }


    // Main Menu
    public void mainMenu ()
    {
	String input;       // Basic User Input Variable
	// Choice Errortrap Loop
	while (true)
	{
	    title ();
	    c.setFont (serif);
	    c.setColor (Color.white);
	    c.drawString ("1. Play Game", 200, 100);
	    c.drawString ("2. Instructions", 200, 130);
	    c.drawString ("3. Options", 200, 160);
	    c.drawString ("4. High Scores", 200, 190);
	    c.drawString ("5. Exit", 200, 220);
	    c.drawString ("Enter your choice: ", 190, 255);
	    c.fillRect (295, 240, 60, 20);
	    c.setCursor (13, 38);
	    input = c.readLine ();
	    try
	    {
		choice = Integer.parseInt (input);
		if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5)
		    break;
	    }
	    catch (NumberFormatException e)
	    {
		new Message ("Please enter a valid option!");
	    }
	}
    }


    // TileCount Return Method - Counts the number of surrounding mines.
    public String tileCount (int num)
    {
	return (Integer.toString (num));
    }


    // User Input & Display (Output & Processing)
    public void askData ()
    {
	// Local Variables
	int xCoord = 220, yCoord = 100, xAmount = 9, yAmount = 9, mineLimit = 10;                   // Coordinate Variables / Mine Amounts / Mine Limits
	int[] [] square = new int [20] [26];                                                        // Squares 2D Array
	int ROW = 1, COL = 1, win = 71, opens = 0, counter = 0, num = 0;                            // Array Modifiers / Win Condition / Squares Counter / For Loop Variable / Mine Counter
	int mineX, mineY, xLimit = 10, yLimit = 10, xGrid = 180, yGrid = 180, xStart = 220;         // Mine Values / Grid System Limits / Grid Size Limits / Starting Position
	char move = 0;                                                                              // User Entered Key
	int cheatX, cheatY;
	double time = System.currentTimeMillis ();

	// Three Level Loop
	for (int level = 1 ; level <= 3 ; level++)
	{
	    // Clear Screen
	    title ();

	    // Outer Square Setting
	    for (y = 0 ; y < 20 ; y++)
	    {
		for (x = 0 ; x < 26 ; x++)
		{
		    square [y] [x] = 2;
		}
	    }

	    // Available Square Setting
	    for (y = 1 ; y < yLimit ; y++)
	    {
		for (x = 1 ; x < xLimit ; x++)
		{
		    square [y] [x] = 0;
		}
	    }

	    // Mine Maker
	    try
	    {
		// Random Values File
		PrintWriter pw = new PrintWriter (new FileWriter ("Mines.txt"));
		for (x = 0 ; x < mineLimit ; x++)
		{
		    mineX = (int) Math.ceil (xAmount * Math.random ());
		    mineY = (int) Math.ceil (yAmount * Math.random ());
		    pw.println (mineX);
		    pw.println (mineY);
		}
		pw.close ();
		// File Reading
		BufferedReader br = new BufferedReader (new FileReader ("Mines.txt"));
		for (x = 0 ; x < mineLimit ; x++)
		{
		    mineX = Integer.parseInt (br.readLine ());
		    mineY = Integer.parseInt (br.readLine ());
		    square [mineY] [mineX] = 1;
		}
	    }
	    // Catches File Errors
	    catch (IOException e)
	    {
	    }
	    // Catches Conversion Errors
	    catch (NumberFormatException e)
	    {
	    }

	    // Initial Grid
	    c.setColor (Color.cyan);
	    c.fillRect (xStart, 100, xGrid, yGrid);
	    for (x = xStart ; x < xStart + xGrid ; x += 20)
	    {
		for (y = 100 ; y < 100 + yGrid ; y += 20)
		{
		    c.setColor (Color.black);
		    c.drawRect (x - 1, y - 1, 22, 22);
		    c.drawRect (x + 1, y + 1, 18, 18);
		    c.drawRect (x, y, 20, 20);
		}
	    }

	    // Movement and Reveal Loop
	    while (true)
	    {
		// Draws Grid
		for (x = xStart ; x < xStart + xGrid ; x += 20)
		{
		    for (y = 100 ; y < 100 + yGrid ; y += 20)
		    {
			c.setColor (Color.black);
			c.drawRect (x - 1, y - 1, 22, 22);
			c.drawRect (x, y, 20, 20);
			c.drawRect (x + 1, y + 1, 18, 18);
		    }
		}
		// Control Commands
		c.setColor (Color.red);
		// Up (W)
		if (move == 119 && ROW != 1)
		{
		    ROW -= 1;
		    yCoord -= 20;
		}
		// Left (A)
		else if (move == 97 && COL != 1)
		{
		    COL -= 1;
		    xCoord -= 20;
		}
		// Down (S)
		else if (move == 115 && ROW != yLimit - 1)
		{
		    ROW += 1;
		    yCoord += 20;
		}
		// Right (D)
		else if (move == 100 && COL != xLimit - 1)
		{
		    COL += 1;
		    xCoord += 20;
		}
		// Flag (Q)
		else if (move == 113 && square [ROW] [COL] != 2)
		{
		    c.setColor (Color.orange);
		    c.fillRect (xCoord + 1, yCoord + 1, 18, 18);
		}
		// Unflag (E)
		else if (move == 101 && square [ROW] [COL] != 2)
		{
		    c.setColor (Color.cyan);
		    c.fillRect (xCoord + 1, yCoord + 1, 18, 18);
		}
		// Cheat Code (0)
		else if (move == 48)
		{
		    cheatX = xStart;
		    cheatY = 100;
		    for (y = 1; y < 20; y++)
		    {
			cheatX = xStart;
			for (x = 1; x < 26; x++)
			{
			    if (square [y][x] == 1)
				c.fillRect (cheatX + 1, cheatY + 1, 18, 18);
			    cheatX += 20;
			}
			cheatY += 20;
		    }
		}
		// Click (SPACE)
		else if (move == 32)
		{
		    // Mine Hit - Processing Loss
		    if (square [ROW] [COL] == 1)
		    {
			c.setColor (Color.magenta);
			c.fillRect (xCoord + 1, yCoord + 1, 18, 18);
			game = false;
			level = 3;
			try
			{
			    Thread.sleep (2000);
			}
			catch (InterruptedException e)
			{
			}
			break;
		    }
		    // Empty Square - Flood Fill Opening
		    else
		    {
			c.setColor (Color.gray);
			c.fillRect (xCoord + 1, yCoord + 1, 18, 18);
			square [ROW] [COL] = 2;

			// South-East Opening
			for (y = ROW ; y < yLimit ; y++)
			{
			    counter = 0;
			    if (square [y + 1] [COL] == 1)
				break;

			    for (x = COL ; x < xLimit ; x++)
			    {
				if (square [y] [x] == 1)
				{
				    counter = 0;
				    break;
				}
				else if (square [y + 1] [x] == 1 || square [y - 1] [x] == 1)
				{
				    c.fillRect (xCoord + (counter * 20) + 1, yCoord + (y - ROW) * 20 + 1, 18, 18);
				    square [y] [x] = 2;
				    break;
				}
				else
				{
				    c.fillRect (xCoord + (counter * 20) + 1, yCoord + (y - ROW) * 20 + 1, 18, 18);
				    counter += 1;
				    square [y] [x] = 2;
				}
			    }
			}

			// North-East Opening
			for (y = ROW ; y > 0 ; y--)
			{
			    counter = 0;
			    if (square [y - 1] [COL] == 1)
				break;
			    for (x = COL ; x < xLimit ; x++)
			    {
				if (square [y] [x] == 1)
				{
				    counter = 0;
				    break;
				}
				else if (square [y + 1] [x] == 1 || square [y - 1] [x] == 1)
				{
				    c.fillRect (xCoord + (counter * 20) + 1, yCoord - (ROW - y) * 20 + 1, 18, 18);
				    square [y] [x] = 2;
				    break;
				}
				else
				{
				    c.fillRect (xCoord + (counter * 20) + 1, yCoord - (ROW - y) * 20 + 1, 18, 18);
				    counter += 1;
				    square [y] [x] = 2;
				}
			    }
			}

			// South-West Opening
			for (y = ROW ; y < yLimit ; y++)
			{
			    counter = 0;
			    if (square [y + 1] [COL] == 1)
				break;
			    for (x = COL ; x > 0 ; x--)
			    {
				if (square [y] [x] == 1)
				{
				    counter = 0;
				    break;
				}
				else if (square [y + 1] [x] == 1 || square [y - 1] [x] == 1)
				{
				    c.fillRect (xCoord - (counter * 20) + 1, yCoord + (y - ROW) * 20 + 1, 18, 18);
				    square [y] [x] = 2;
				    break;
				}
				else
				{
				    c.fillRect (xCoord - (counter * 20) + 1, yCoord + (y - ROW) * 20 + 1, 18, 18);
				    counter += 1;
				    square [y] [x] = 2;
				}
			    }
			}


			// North West Opening
			for (y = ROW ; y > 0 ; y--)
			{
			    counter = 0;
			    if (square [y - 1] [COL] == 1)
				break;
			    for (x = COL ; x > 0 ; x--)
			    {
				if (square [y] [x] == 1 || square [y + 1] [x] == 1 || square [y - 1] [x] == 1)
				{
				    counter = 0;
				    break;
				}
				else if (square [y + 1] [x] == 1 || square [y - 1] [x] == 1)
				{
				    c.fillRect (xCoord - (counter * 20) + 1, yCoord - (ROW - y) * 20 + 1, 18, 18);
				    square [y] [x] = 2;
				    break;
				}
				else
				{
				    c.fillRect (xCoord - (counter * 20) + 1, yCoord - (ROW - y) * 20 + 1, 18, 18);
				    counter += 1;
				    square [y] [x] = 2;
				}
			    }
			}

			// Numeric System Output - Nested For Loop (Vertical And Horizontal)
			c.setColor (Color.red);
			for (y = 1 ; y < yLimit ; y++)
			{
			    for (x = 1 ; x < xLimit ; x++)
			    {
				if (square [y] [x + 1] == 1)
				    num += 1;
				if (square [y] [x - 1] == 1)
				    num += 1;
				if (square [y + 1] [x] == 1)
				    num += 1;
				if (square [y - 1] [x] == 1)
				    num += 1;
				if (square [y + 1] [x + 1] == 1)
				    num += 1;
				if (square [y + 1] [x - 1] == 1)
				    num += 1;
				if (square [y - 1] [x + 1] == 1)
				    num += 1;
				if (square [y - 1] [x - 1] == 1)
				    num += 1;
				if (square [y] [x] == 2)
				    c.drawString (tileCount (num), xStart + (x - 1) * 20 + 7, 100 + (y - 1) * 20 + 15);
				num = 0;
			    }
			}
		    }
		}

		// Information Bars
		c.setColor (Color.gray);
		c.fillRect (85, 60, 420, 20);
		c.setColor (Color.white);
		c.drawString ("Opened Squares: " + Integer.toString (opens), 90, 75);
		c.drawString ("Total Mines: " + Integer.toString (mineLimit), 300, 75);

		// Victory Condition
		opens = 0;
		for (y = 1 ; y < yLimit ; y++)
		{
		    for (x = 1 ; x < xLimit ; x++)
		    {
			if (square [y] [x] == 2)
			    opens++;
		    }
		}
		if (opens >= win)
		{
		    game = true;
		    try
		    {
			Thread.sleep (2000);
		    }
		    catch (InterruptedException e)
		    {
		    }
		    break;
		}

		// Square Position
		c.setColor (Color.red);
		c.drawRect (xCoord - 1, yCoord - 1, 22, 22);
		c.drawRect (xCoord + 1, yCoord + 1, 18, 18);
		c.drawRect (xCoord, yCoord, 20, 20);

		// User Input
		move = c.getChar ();
	    }
	    // Level Two Settings
	    if (level == 1)
	    {
		c.clear ();
		xCoord = 175;
		yCoord = 100;
		ROW = 1;
		COL = 1;
		win = 216;
		opens = 0;
		move = 0;
		xLimit = 17;
		yLimit = 17;
		xGrid = 320;
		yGrid = 320;
		xStart = 175;
		xAmount = 16;
		yAmount = 16;
		mineLimit = 40;
	    }
	    // Level Three Settings
	    else if (level == 2)
	    {
		c.clear ();
		xCoord = 100;
		yCoord = 100;
		ROW = 1;
		COL = 1;
		win = 333;
		opens = 0;
		move = 0;
		xLimit = 25;
		yLimit = 19;
		xGrid = 480;
		yGrid = 360;
		xStart = 100;
		xAmount = 24;
		yAmount = 18;
		mineLimit = 99;
	    }

	}
	// User Score
	score = (System.currentTimeMillis () - time) / 1000;
	endGame ();
    }


    // Score Saving Method - Uses Parameter Passes to Save / Organize High Scores
    private void scoreSaver (double score)
    {
	// Local Variables
	final int topTen = 11;                      // Final Array Range Variable
	double[] points = new double [topTen];      // High Score Array
	String[] names = new String [topTen];       // High Score Names Array
	double tempNum;                             // Temporary Score Variable (Bubble Sort)
	String tempName, userName;                  // Temporary Name Variable (Bubble Sort)
	char save;                                  // Save Toggle Input Variable

	// User Input
	title ();
	c.setColor (Color.white);
	c.fillRect (0, 50, 640, 200);
	c.setCursor (4, 8);
	c.print ("Your score is: " + score);
	c.println ("\nDo you want to save your score? (Y/N)");
	// Save Input Errortrap Loop
	while (true)
	{
	    save = c.getChar ();
	    // Saves User High Score
	    if (save == 'Y' || save == 'y')
	    {
		c.setCursor (8, 8);
		c.print ("Please enter your user name: ");
		userName = c.readLine ();
		points [10] = score;
		names [10] = userName;
		// Accesses High Score File
		try
		{
		    BufferedReader br = new BufferedReader (new FileReader ("HighScore.txt"));
		    // Reads And Stores Top Ten High Scores
		    for (x = 0 ; x < 10 ; x++)
		    {
			names [x] = br.readLine ();
			points [x] = Double.parseDouble (br.readLine ());
		    }
		    // Bubble Sort - Arranges The High Scores In Order
		    for (y = 0 ; y < 11 ; y++)
		    {
			for (x = 1 ; x < 11 ; x++)
			{
			    if (points [x] > points [x - 1])
			    {
				tempNum = points [x];
				points [x] = points [x - 1];
				points [x - 1] = tempNum;
				tempName = names [x];
				names [x] = names [x - 1];
				names [x - 1] = tempName;
			    }
			}
		    }
		    // Rewrites High Score File Based On New Order
		    PrintWriter pw = new PrintWriter (new FileWriter ("HighScore.txt"));
		    for (x = 10 ; x > 0 ; x--)
		    {
			pw.println (names [x]);
			pw.println (points [x]);
		    }
		    pw.close ();
		}
		catch (IOException e)
		{
		}
		break;
	    }
	    // Invalid Input Variable
	    else if (save != 'Y' && save != 'y' && save != 'N' && save != 'n')
		new Message ("Please strike 'Y' or 'N' only!", "Error");
	    // Breaks Loop
	    else
		break;
	}
	pauseProgram ();
    }


    // High Score Method - Displays Top Ten High Score
    public void highScore ()
    {
	title ();
	// Accesses And Reads High Score FIle
	try
	{
	    BufferedReader br = new BufferedReader (new FileReader ("HighScore.txt"));
	    // Sets Font And Colors
	    c.setColor (Color.white);
	    // Creates and Derives Flashback Font
	    InputStream is = this.getClass ().getResourceAsStream ("Flashback.ttf");
	    Font gammaBase = Font.createFont (Font.TRUETYPE_FONT, is);
	    Font gammaReal = gammaBase.deriveFont (Font.TRUETYPE_FONT, 15);
	    c.setFont (gammaReal);
	    // Outputs Top Ten High Scores
	    for (x = 0 ; x < 10 ; x++)
	    {
		c.drawString (br.readLine ().toUpperCase (), 100, 80 + 24 * x);
		c.drawString (br.readLine ().toUpperCase (), 300, 80 + 24 * x);
	    }
	}
	catch (Exception e)
	{
	}
	pauseProgram ();
    }


    // End Screens
    private void endGame ()
    {
	title ();
	// Game Over
	if (game == false)
	{
	    // Sets Color And Font
	    c.setColor (Color.red);
	    try
	    {
		// Creates and Derives Chang Font
		InputStream is = this.getClass ().getResourceAsStream ("ChangChang.ttf");
		Font omegaBase = Font.createFont (Font.TRUETYPE_FONT, is);
		Font omegaReal = omegaBase.deriveFont (Font.TRUETYPE_FONT, 90);
		c.setFont (omegaReal);
		c.drawString ("GAME OVER", 100, 240);
		Thread.sleep (200);
	    }
	    catch (Exception e)
	    {
	    }
	    pauseProgram ();
	}
	
	// Victory Screen
	else
	{
	    // Sets Color And Font
	    c.setColor (Color.white);
	    try
	    {
		// Creates and Derives Halo Font
		InputStream is = this.getClass ().getResourceAsStream ("Halov2.ttf");
		Font alphaBase = Font.createFont (Font.TRUETYPE_FONT, is);
		Font alphaReal = alphaBase.deriveFont (Font.TRUETYPE_FONT, 90);
		c.setFont (alphaReal);
		c.drawString ("VICTORY", 120, 240);
		Thread.sleep (200);
	    }
	    catch (Exception e)
	    {
	    }
	    // Outputs Score
	    c.setFont (monospaced);
	    c.drawString ("SCORE: " + Double.toString (score), 180, 450);
	    pauseProgram ();
	    scoreSaver (score);
	}
    }


    // Game Instructions - Outputs Rules And Regulations of Minesweeper
    public void instructions ()
    {
	title ();
	c.setColor (Color.white);
	c.fillRect (0, 50, 640, 400);
	c.setCursor (4, 1);
	c.println ("Welcome, Mine Excavation Specialists!\n");
	c.println ("Your current mission is Project Minesweeper where you (Codename: PLAYER) are    tasked with uncovering the tiles of a mysterious grid, an ancient relic of the  mythical Macrosoft city.");
	c.println ("\nThe objective of your mission is to successfully uncover all the tiles of the   grid system while avoiding the mines scattered across the grid.");
	c.println ("\nThere exist three different levels of the grid, each rising with difficulty     . . . and mines. (9x9) [10 Mines]|(16x16)|(24x18)");
	c.println ("\nPress any key to continue . . .");
	pauseProgram ();
	title ();
	c.setColor (Color.white);
	c.fillRect (0, 50, 640, 400);
	c.setCursor (4, 1);
	c.println ("Fortunately, your old friend Dr. Keyboard has developed several experimental    tools for your mission:\n");
	c.println ("W     = Move Up");
	c.println ("S     = Move Down");
	c.println ("A     = Move Left");
	c.println ("D     = Move Right");
	c.println ("Q/E   = Toggle Flags");
	c.println ("Q/E   = Toggle Flags");
	c.println ("SPACE = Uncover Tile");
	c.println ("\nPress any key to continue . . .");
	pauseProgram ();
	title ();
	c.setColor (Color.white);
	c.fillRect (0, 50, 640, 400);
	c.setCursor (4, 1);
	c.println ("To assist you on your task, Mr. Java has kindly coloured and number-coded the   various squares of the grid :\n");
	c.println ("MAGENTA = Mines (Levels : 10 | 40 | 99)");
	c.println ("GRAY    = Safely Opened Squares");
	c.println ("CYAN    = Unopened Squares");
	c.println ("ORANGE  = Flagged Squares (Indicators)");
	c.println ("NUMBERS = Amount Of Mines In The Surrounding Eight Squares");
	c.println ("\nMission Briefing Complete. Press any key to continue . . .");
	pauseProgram ();
    }


    // Goodbye Screen & Exit
    public void goodBye ()
    {
	title ();
	c.setColor (Color.white);
	c.setFont (monospaced);
	c.drawString ("Thank you for playing!", 210, 200);
	c.drawString ("By: Ronald Perlas", 220, 300);
	// Delays Commands
	try
	{
	    Thread.sleep (2000);
	}
	catch (InterruptedException e)
	{
	}
	// Closes Console Window
	c.close ();
    }


    // Theme Options - Allows USer To Alter Background Gradient
    public void options ()
    {
	title ();
	c.fillRect (0, 50, 640, 400);
	c.setCursor (4, 1);
	c.println ("Please choose a theme:\n");
	c.println ("Default");
	c.println ("Radiance");
	c.println ("Slime");
	c.println ("Crimsanity");
	c.println ("Solaris");
	c.print ("\nType in your choice: ");
	// Theme Variable Errortrap
	while (true)
	{
	    c.setCursor (12, 22);
	    c.println ();
	    c.setCursor (12, 22);
	    theme = c.readLine ();
	    if (theme.equals ("Default") || theme.equals ("Radiance") || theme.equals ("Slime") || theme.equals ("Crimsanity") || theme.equals ("Solaris"))
		break;
	    new Message ("Please enter the name of a valid theme! Case sensitive!", "Error");
	}
    }


    // Main Method
    public static void main (String[] args)
    {
	Minesweeper t = new Minesweeper ();
	t.splashScreen ();
	// Loops Program Until User Exits
	while (true)
	{
	    t.mainMenu ();
	    if (choice == 1)
		t.askData ();
	    else if (choice == 2)
		t.instructions ();
	    else if (choice == 3)
		t.options ();
	    else if (choice == 4)
		t.highScore ();
	    else if (choice == 5)
		break;
	}
	t.goodBye ();
    }
}
