package UI;


import java.awt.Canvas;
import java.awt.EventQueue;

/**
 * GameFrame contains GamePanel.this is the window displaying the actual game that user plays.
 * @author yuechuan
 *
 */

public class GameFrame extends UIElement {
		private static GameFrame gameFrameInstance = new GameFrame();
		
	
        private static final long serialVersionUID = 1L;
        //I've changed this to Canvas because Jpanel doesn't work well with BufferStrategy
        private static Canvas canvas;
        
    
        /**
         * 
         * @return a static instance of the GameFrame class
         */
        public static GameFrame getInstance(){
        	return gameFrameInstance;
        }
        /**
         * initialize the GameFrame Object for gaming.
         */
        public static void init() {
        	EventQueue.invokeLater(new Runnable() {
                public void run() {
                        try {
                        		gameFrameInstance.setSize(1000,1000);
                                canvas = (GamePanel.getInstance());
                                GamePanel.getInstance().setSize(600, 400);
                    			GamePanel.getInstance().addKeyListener(GamePanel.getInstance());
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
		/**
		 * @deprecated not implemented , use init() instead to reinitialize 
		 */
		public void reset() {
		}
}
