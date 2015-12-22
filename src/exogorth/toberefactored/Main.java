//package exogorth.toberefactored;
//
//import java.awt.Canvas;
//import java.awt.Dimension;
//
//import javax.swing.JFrame;
//
//import exogorth.Window;
//
//public class Main extends Canvas implements Runnable {
//	private static final long serialVersionUID = 1L;
//	public final String TITLE = "Exogorth";
//	
//	private boolean running = false;
//	private Thread thread;
//	
//		private synchronized void start(){
//		if(running)
//			return;
//		
//		running = true;
//		thread = new Thread(this);
//		thread.start();
//	}
//	
//	private synchronized void stop(){
//		if(!running)
//			return;
//		running = false;
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			System.out.println("Threads couldn't be joined.");
//			e.printStackTrace();
//		}
//		System.exit(1);
//	}
//	
//	@Override
//	public void run() {
//		while(running){
//			System.out.println("WORKING");
//		}
//		// TODO Auto-generated method stub
//		stop();
//	}
//
//	
//	
//	
//	
//	
//	
//	public static void main(String args[]){
//		Main main = new Main();
//		
//		main.setPreferredSize(new Dimension(Window.width, Window.height));
//		
//		JFrame frame = new JFrame(main.TITLE);
//		frame.add(main);
//		frame.pack();
//		frame.setResizable(false);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		main.start();
//	}
//	
//
//}
