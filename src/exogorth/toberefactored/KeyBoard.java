package exogorth.toberefactored;

//Aufruf KeyBoard.knopfgedrueckt(KeyEvent.VK_SPACE))
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	public static boolean knopfgedrueckt(int keycode) {
		if (keycode >= 0 && keycode < keys.length)
			return keys[keycode];
		return false;
	}

	private static boolean[] keys = new boolean[1024];

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
