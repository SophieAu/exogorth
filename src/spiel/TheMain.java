package spiel;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
		
public class TheMain {
	public static int mode=1;//1:Men�; 2:Spiel; 3: Highscoreint mode=1;//1:Men�; 2:Spiel; 3: Highscore
	public static MainMenu app;
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
		
		// Thread f�r Spieler
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
		// Thread f�r Bullet
			//	Thread bulletThread = new Thread() {
				//	@Override
					//public void run() {
						//for (int i = 0; i < bullet.size(); i++) {
							//bullet.get(i).update();
						//}
					//}
				//};

		
		spielerThread.start();
		EventQueue.invokeLater(new Runnable() {
			@Override public void run() {
				app = new MainMenu();
			}
		});
		while (true){
		while (mode==1){
			app.setVisible(true);}
		app.setVisible(false);
		while (mode==2) {
			// Start
			double zeit = System.currentTimeMillis();

			// Objekte update
			// spieler update
			spielerThread.run();
			hg.update();
			ablauf.drawAgain();
			//Kugeln ftw.
		//	bulletThread.start();
			for (int i = 0; i < bullet.size(); i++) {
				bullet.get(i).update();
			}
			while (zeit + 10 > System.currentTimeMillis())
				Thread.sleep(1);
		}
	}
}
}
