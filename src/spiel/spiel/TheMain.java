package spiel;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class TheMain {
	public static void main(String[] args) throws InterruptedException,
			IOException {
		int speedPlayer = 5;
		int worldY = 800;
		int worldX = 600;
		ArrayList<Bullets> bullet=new ArrayList<Bullets>();
		Player player = new Player(speedPlayer, 250, 500, worldY,
				worldX, bullet);
		bullet.add(new Bullets(-speedPlayer+3,300, 1000,4,bullet));
		bullet.add(new Bullets(-speedPlayer+3,200, 1500,4,bullet));
		bullet.add(new Bullets(-speedPlayer+3,100, 2000,4,bullet));
		bullet.add(new Bullets(-speedPlayer+3,500, 2500,4,bullet));
		Background hg = new Background(speedPlayer);
		Draws ablauf = new Draws(player, hg,bullet);
		ablauf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ablauf.setSize(800, 600);
		ablauf.setLocationRelativeTo(null);
		ablauf.setVisible(true);
		ablauf.buffer();
		ablauf.setResizable(false);
		
		// Thread für Spieler
		Thread spielerThread = new Thread() {
			@Override
			public void run() {
				try {
					player.update();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};		
		// Threads starten
		spielerThread.start();
		while (true) {
			// Start
			double zeit = System.currentTimeMillis();

			// Objekte update
			// spieler update
			spielerThread.run();
			hg.update(player.endOfWorld);
			ablauf.drawAgain();
			//Kugeln ftw.
			for (int i = 0; i < bullet.size(); i++) {
				bullet.get(i).update();
				//i--;
			}
			while (zeit + 10 > System.currentTimeMillis())
				Thread.sleep(1);
		}
	}
}
