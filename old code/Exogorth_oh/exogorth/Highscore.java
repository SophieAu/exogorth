package exogorth;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Highscore extends JFrame {
	private static SortedMap<Integer, String> highscores;
	static int currentScore;
	static boolean nameEntered;
	static boolean doneSaving;
	static boolean newHighscore;
	final static Integer holder = 0;;

	public Highscore() {
		fetchOldHighscores();
		currentScore = 0;
	}

	public static void finalScore(int livesLeft) {
		currentScore *= (livesLeft + 1);

		if (highscores.size() >= 1 && (currentScore > highscores.firstKey() && highscores.size() >= 10))
			highscores.remove(highscores.firstKey());

		addNewHighScore();
		saveNewHighscores();
		doneSaving = true;
		reset();
	}

	private static void reset() {
		newHighscore = true;
		currentScore = 0;
	}

	private static void addNewHighScore() {
		String name = enterName();
		if (highscores.containsKey(currentScore))
			highscores.put(currentScore, highscores.get(currentScore) + ", " + name);
		else
			highscores.put(currentScore, name);
	}

	private static String enterName() {
		HighscoreEnterName nameInput = new HighscoreEnterName();
		nameInput.setVisible(true);
		while (!nameEntered)
			System.out.println("Waiting for user input");
		return nameInput.getPlayerName();
	}

	@SuppressWarnings("unchecked")
	private static void fetchOldHighscores() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscore.dat"));
			highscores = (SortedMap<Integer, String>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			highscores = new TreeMap<Integer, String>();
			System.out.println("Ich war's nicht!");
			e.printStackTrace();
		}
	}

	private static void saveNewHighscores() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscore.dat"));
			oos.writeObject(highscores);
			oos.close();
			System.out.println(highscores);
		} catch (IOException e) {
			System.out.println("Irgendwas ist beim Speichern schief gelaufen");
			e.printStackTrace();
		}
	}

	public static SortedMap<Integer, String> getHighscoreMap() {
		fetchOldHighscores();
		return highscores;
	}

}