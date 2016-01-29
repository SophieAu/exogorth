package exogorth.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import exogorth.STATE;
import exogorth.Window;
import exogorth.level.Level;
import exogorth.TheMain;

public class MouseInput implements MouseListener {

	@Override
	public void mousePressed(MouseEvent e) {
		if (TheMain.State == STATE.GAME)
			return;

		if (e.getX() >= MenuScreen.buttonXLeft && e.getX() <= MenuScreen.buttonXRight) {
			if (TheMain.State == STATE.MAINMENU)
				mousePressedMainMenu(e, e.getY());
			else
				mousePressedOtherScreens(e, e.getY());
		}
	}

	private void mousePressedMainMenu(MouseEvent e, int mouseY) {
		if (mouseY >= MainMenu.playBoxY && mouseY <= (MainMenu.playBoxY + Window.BOXHEIGHT)) {
			if (!TheMain.isFirstStart)
				TheMain.level = new Level();
			TheMain.State = STATE.GAME;
			TheMain.currentScreen = TheMain.level;
			TheMain.isFirstStart = false;
			return;
		}

		if (mouseY >= MainMenu.helpBoxY && mouseY <= (MainMenu.helpBoxY + Window.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new HelpScreen();
			return;
		}

		if (mouseY >= MainMenu.highscoreBoxY && mouseY <= (MainMenu.highscoreBoxY + Window.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new HighscoreScreen();
			return;
		}

		if (mouseY >= MainMenu.creditsBoxY && mouseY <= (MainMenu.creditsBoxY + Window.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new CreditsScreen();
			return;
		}

		if (mouseY >= MenuScreen.exitBoxY && mouseY <= (MenuScreen.exitBoxY + Window.BOXHEIGHT))
			System.exit(0);
	}

	private void mousePressedOtherScreens(MouseEvent e, int mouseY) {
		if (mouseY >= MenuScreen.exitBoxY && mouseY <= (MenuScreen.exitBoxY + Window.BOXHEIGHT)) {
			TheMain.State = STATE.MAINMENU;
			TheMain.currentScreen = new MainMenu();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
