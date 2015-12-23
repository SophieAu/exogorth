package exogorth;

/**
 * The screen dimensions we use for the game. They're a global variable and will not be changed
 * anywhere.
 * 
 * @author Sophie
 *
 */
public class Window {
	public static final int WIDTH = 800, HEIGHT = 600, REALHEIGHT = HEIGHT-29;

	// Menu-specific constants
	public static final int BOXWIDTH = 350, BOXHEIGHT = 50;
	public static final int BOXX = (Window.WIDTH - BOXWIDTH) / 2;
	public static final int BOXYSPACE = 80;
}
