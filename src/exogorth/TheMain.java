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
public class TheMain extends Canvas {
	public static STATE State = STATE.MAINMENU;
	public static Level level;
	public static JFrame currentScreen;
	public static boolean running = false;
	private BufferStrategy bufferStrategy;
	private Thread renderer;
	private Thread updater;

	public static void main(String args[]) {
		TheMain game = new TheMain();
		createFrame(game);
		game.addKeyListener(new Keyboard());
		game.addMouseListener(new MouseInput());
		game.start();
	}

	private static void createFrame(TheMain game) {
		JFrame frame = new JFrame("Exogorth");
		frame.add(game);
		frame.pack();
		frame.setSize(Window.WIDTH, Window.HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocusInWindow();
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		createRenderer();
		createUpdater();
		startGameLoop();
	}

	private void createRenderer() {
		renderer = new Thread(new Runnable() {
			@Override
			public void run() {
				renderRunner();
			}
		});
	}

	private void renderRunner() {
		while (running)
			render();
		stop(renderer);
	}

	private void createUpdater() {
		updater = new Thread(new Runnable() {
			@Override
			public void run() {
				updateRunner();
			}
		});
	}

	private void updateRunner() {
		long lastTime = System.nanoTime();
		double nanoSecondsPerFrame = 1000000000 / 60;
		double delta = 0;
		long currentTime;
		while (running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / nanoSecondsPerFrame;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				delta--;
			}
		}
		stop(updater);
	}

	private void startGameLoop() {
		init();
		renderer.start();
		updater.start();
	}

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

	private synchronized void stop(Thread thread) {
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