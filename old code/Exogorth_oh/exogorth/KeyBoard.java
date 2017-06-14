package exogorth;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter {
	private static boolean[] keys = new boolean[1024];

	public static boolean knopfgedrueckt(int keycode) {
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

	public static void reset() {
		keys = new boolean[1024];		
	}

}
