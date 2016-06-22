package exogorth.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import exogorth.STATE;
import exogorth.Settings;
import exogorth.level.Level;
import exogorth.TheMain;

public class MouseInput extends MouseAdapter {

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
		if (mouseY >= MainMenu.playBoxY && mouseY <= (MainMenu.playBoxY + Settings.BOXHEIGHT)) {
			if (!TheMain.isFirstStart)
				TheMain.level = new Level(1);
			TheMain.State = STATE.GAME;
			TheMain.currentScreen = TheMain.level;
			TheMain.isFirstStart = false;
			return;
		}

		if (mouseY >= MainMenu.helpBoxY && mouseY <= (MainMenu.helpBoxY + Settings.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new HelpScreen();
			return;
		}

		if (mouseY >= MainMenu.highscoreBoxY && mouseY <= (MainMenu.highscoreBoxY + Settings.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new HighscoreScreen();
			return;
		}

		if (mouseY >= MainMenu.creditsBoxY && mouseY <= (MainMenu.creditsBoxY + Settings.BOXHEIGHT)) {
			TheMain.State = STATE.MENUSCREENS;
			TheMain.currentScreen = new CreditsScreen();
			return;
		}

		if (mouseY >= MenuScreen.exitBoxY && mouseY <= (MenuScreen.exitBoxY + Settings.BOXHEIGHT))
			System.exit(0);
	}

	private void mousePressedOtherScreens(MouseEvent e, int mouseY) {
		if (mouseY >= MenuScreen.exitBoxY && mouseY <= (MenuScreen.exitBoxY + Settings.BOXHEIGHT)) {
			TheMain.State = STATE.MAINMENU;
			TheMain.currentScreen = new MainMenu();
		}
	}
}
