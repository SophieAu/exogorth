package exogorth.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import exogorth.STATE;
import exogorth.Window;
import exogorth.toberefactored.TheMain;

public class MouseInput implements MouseListener {

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (TheMain.State != STATE.GAME) {
			if (e.getX() >= ((Window.width - Window.boxWidth) / 2) && e.getX() <= ((Window.width + Window.boxWidth) / 2)) {
				if (TheMain.State == STATE.MAINMENU) {
					mousePressedMainMenu(e, e.getY());
				} else {
					mousePressedOtherScreens(e, e.getY());
				}
			}
		}
	}

	private void mousePressedMainMenu(MouseEvent e, int mouseY) {
		// Spiel starten
		if (mouseY >= MainMenu.playBoxY && mouseY <= (MainMenu.playBoxY + Window.boxHeight)) {
			TheMain.canvas.setVisible(false);
			TheMain.State = STATE.GAME;
			TheMain.ablauf.setVisible(true);
		}

		// Hilfe
		if (mouseY >= MainMenu.helpBoxY && mouseY <= (MainMenu.helpBoxY + Window.boxHeight)) {
			TheMain.canvas.setVisible(false);
			TheMain.State = STATE.HELP;
			TheMain.canvas = new HelpScreen();
			TheMain.canvas.setVisible(true);
		}

		// Highscore
		if (mouseY >= MainMenu.highscoreBoxY && mouseY <= (MainMenu.highscoreBoxY + Window.boxHeight)) {
			TheMain.canvas.setVisible(false);
			TheMain.State = STATE.HIGHSCORE;
			TheMain.canvas = new HighscoreScreen();
			TheMain.canvas.setVisible(true);
		}

		// Credits
		if (mouseY >= MainMenu.creditsBoxY && mouseY <= (MainMenu.creditsBoxY + Window.boxHeight)) {
			TheMain.canvas.setVisible(false);
			TheMain.State = STATE.CREDITS;
			TheMain.canvas = new CreditsScreen();
			TheMain.canvas.setVisible(true);
		}

		// Spiel verlassen
		if (mouseY >= GenericScreen.exitBoxY && mouseY <= (GenericScreen.exitBoxY + Window.boxHeight))
			System.exit(0);

	}

	private void mousePressedOtherScreens(MouseEvent e, int mouseY) {
		// Y COORDINATES TBD
		if (mouseY >= GenericScreen.exitBoxY && mouseY <= (GenericScreen.exitBoxY + Window.boxHeight)) {
			TheMain.canvas.setVisible(false);
			TheMain.State = STATE.MAINMENU;
			TheMain.canvas = new MainMenu();
			TheMain.canvas.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
