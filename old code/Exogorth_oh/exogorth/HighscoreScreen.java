package exogorth;

import java.awt.BorderLayout;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class HighscoreScreen extends JFrame {

	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;

	public HighscoreScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(SCREEN_WIDTH, SCREEN_HEIGHT);

		setTitle("Exogorth - Highscore");
		setLayout(null);
		setBounds(def.getX(), def.getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);

		JButton backButton = def.backToMenu((JFrame) this);
		getContentPane().add(backButton);

		JTable highscoreTable = displayHighscores();
		setLayout(new BorderLayout());
		add(highscoreTable.getTableHeader(), BorderLayout.PAGE_START);
		add(highscoreTable, BorderLayout.CENTER);
	}

	private JTable displayHighscores() {
		SortedMap<Integer, String> highscores = Highscore.getHighscoreMap();
		int arraySize = highscores.size();
		System.out.println(highscores);
		String columnNames[] = { "Name", "Score" };

		Object[][] scoreNamePairings = new Object[arraySize][2];

		int i = 1;
		for (Entry<Integer, String> entry : highscores.entrySet()) {
			scoreNamePairings[arraySize-i][0] = entry.getValue();
			scoreNamePairings[arraySize-i][1] = entry.getKey();
			i++;
		}
		return new JTable(scoreNamePairings, columnNames);
	}
}
