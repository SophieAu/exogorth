package spiel;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
		
public class TheMain {
	public static int mode=1;//1:Menü; 2:Spiel; 3: Highscoreint mode=1;//1:Menü; 2:Spiel; 3: Highscore
	public static MainMenu app;
	public static Draws ablauf = null;
	
	
	public static void main(String[] args) throws InterruptedException,
			IOException {
		boolean firstTimeStarted=true;
		
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
		// Thread für Bullet
			//	Thread bulletThread = new Thread() {
				//	@Override
					//public void run() {
						//for (int i = 0; i < bullet.size(); i++) {
							//bullet.get(i).update();
						//}
					//}
				//};

	//	app = new MainMenu();
		spielerThread.start();
		if(firstTimeStarted){
			ablauf = new Draws(player, hg,bullet);
			ablauf.setVisible(true);
			ablauf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ablauf.setSize(800, 600);
			ablauf.setLocationRelativeTo(null);	
			ablauf.buffer();
			ablauf.setResizable(false);
			ablauf.setVisible(false);
			firstTimeStarted=false;
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override public void run() {
			    MainMenu app = new MainMenu();
				app.setVisible(true);
			}
		});
		while (true){
	//	while (mode==1){
		//	app.setVisible(true);
		//	}
		//app.setVisible(false);
	//	while (mode==2) {
			//Wenn erstart, dann baue das Fenster auf;
			// Start
			double zeit = System.currentTimeMillis();
			if(mode==2){
			spielerThread.run();
			hg.update();
			ablauf.drawAgain();
			
			//Kugeln ftw.
		//	bulletThread.start();
			for (int i = 0; i < bullet.size(); i++) {
				bullet.get(i).update();
			}
			}
			while (zeit + 10 > System.currentTimeMillis())
				Thread.sleep(1);
		}
	//}
}
}
