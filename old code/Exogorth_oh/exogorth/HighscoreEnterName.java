package exogorth;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HighscoreEnterName extends JFrame {
	String playerName = "";

	public HighscoreEnterName() {
		setTitle("Title");
		setLocationRelativeTo(null);
		setResizable(false);

		JTextField nameInput = new JTextField(10);
		JButton okButton = new JButton("OK");

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
					playerName = nameInput.getText();
					Highscore.nameEntered = true;
					tester();
					setVisible(false);
				dispose();
			}

			private void tester() {
				System.out.println(playerName);
				System.out.println("NameEntered?: " + Highscore.nameEntered);
			}
		});

		getContentPane().add(nameInput, BorderLayout.CENTER);
		getContentPane().add(okButton, BorderLayout.SOUTH);
		pack();
	}

	public String getPlayerName() {
		return playerName;
	}
}
