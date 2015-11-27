package exogorth;

import java.awt.EventQueue;

public class Execute {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override public void run() {
				MainMenu app = new MainMenu();
				app.setVisible(true);
			}
		});
	}

}