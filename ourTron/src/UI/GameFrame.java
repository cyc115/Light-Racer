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
                         
                                GamePanel.getInstance().setSize(600, 400);
                    			GamePanel.getInstance().addKeyListener(GamePanel.getInstance());
                    			System.out.println("gamePanel initialized");
                               
                                
                                (GamePanel.getInstance()).setVisible(true);
                                gameFrameInstance.add(GamePanel.getInstance());
                                gameFrameInstance.pack();
                                //Create a triple buffering BufferStrategy
                                (GamePanel.getInstance()).createBufferStrategy(3);
                                (GamePanel.getInstance()).start();
                                System.out.println("game has started");
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        });
        }

		@Override
		
		/**
		 * resets the Game frame and set visibility to false 
		 */
		public void reset() {
			init();
			getInstance().setVisible(false);
		}
}
