package UI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.UIElement;
/**
 * GameFrame hosts the GamePanel 
 * @author yuechuan
 *
 */

//TODO make this single ton 
// TODO check and implement the missing methods 

public class GameFrame extends UIElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//I've changed this to Canvas because Jpanel doesn't work well with BufferStrategy
	private static Canvas canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					// I've moved everything here because otherwise canvas wasn't getting added to the frame
					canvas = (GamePanel.getInstance());
					frame.add(canvas);
					frame.pack();
					((GamePanel) canvas).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		this.setSize(1500,1500);
		
		//contentPane = GamePanel.getInstance();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		
		
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
