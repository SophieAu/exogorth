package exogorth.gamelogic;

//Aufruf KeyBoard.knopfgedrueckt(KeyEvent.VK_SPACE))
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private static boolean[] keys = new boolean[1024];

	public static boolean pressedKey(int keycode) {
		return keys[keycode];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		keys[keyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		keys[keyCode] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
