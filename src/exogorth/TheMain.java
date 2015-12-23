package exogorth;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import exogorth.level.Keyboard;
import exogorth.level.Level;
import exogorth.menu.MainMenu;
import exogorth.menu.MouseInput;

@SuppressWarnings("serial")
public class TheMain extends Canvas implements Runnable {
	public static STATE State = STATE.MAINMENU;

	private boolean running = false;
	private Thread thread;
	private BufferStrategy bufferStrategy;
	public static Level level;
	public static JFrame currentScreen;

	private void init() {
		level = new Level();
		currentScreen = new MainMenu();
	}

	private void render() {
		bufferStrategy = this.getBufferStrategy();

		if (bufferStrategy == null) {
			createBufferStrategy(3); // TrippleBuffering (MainScreen -> Buffer -> Buffer)
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		if (State == STATE.GAME)
			level.render(g);
		else
			currentScreen.paint(g);

		g.dispose();
		bufferStrategy.show();
	}

	private void update() {
		if (State == STATE.GAME)
			level.update();
	}

	public static void main(String args[]) {
		TheMain game = new TheMain();

		JFrame frame = new JFrame("Exogorth");
		frame.add(game);
		frame.pack();
		frame.setSize(Window.WIDTH, Window.HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocusInWindow();

		game.addKeyListener(new Keyboard());
		game.addMouseListener(new MouseInput());

		game.start();
	}

	/**
	 * Der Game-Loop. Updates laufen auf 60FPS, das Rendering hängt ganz davon ab, bei mir aber
	 * momentan ca. 200FPS
	 */
	private synchronized void start() {
		if (running)
			return;

		running = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double ns = 100000000 / 6;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " UpdatesPS, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println("Exception when trying to join the Threads.");
			e.printStackTrace();
		}
		System.exit(1);
	}
}
