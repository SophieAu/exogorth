package spiel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	private static boolean[] keys = new boolean[1024];

	public static boolean knopfgedrueckt(int keycode) {
		if (keycode >= 0 && keycode < keys.length)
			return keys[keycode];
		return false;
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
