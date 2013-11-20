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
	private static GameFrame gameFrameInstance = new GameFrame();
	
        private static final long serialVersionUID = 1L;
        //I've changed this to Canvas because Jpanel doesn't work well with BufferStrategy
        private static Canvas canvas;
        /**
         * Launch the application.
         */

        /**
         * Create the frame.
         */
        public GameFrame() {
                this.setSize(1500,1500);
        }
        
        public static GameFrame getInstance(){
        	return gameFrameInstance;
        }
        
        public static void init() {
        	EventQueue.invokeLater(new Runnable() {
                public void run() {
                        try {
                                canvas = (GamePanel.getInstance());
                                canvas.setVisible(true);
                                gameFrameInstance.add(canvas);
                                gameFrameInstance.pack();
                                ((GamePanel) canvas).start();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        });
        }

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
}
