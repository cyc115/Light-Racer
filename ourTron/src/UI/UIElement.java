package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public abstract class UIElement extends JFrame {

	private JPanel contentPane;

	
	//abstract methods
	
	public abstract void reset();

	/**
	 * Create the frame.
	 */
	public UIElement() {
		setTitle("Tron");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnGame.add(mntmNewGame);
		
		JMenuItem mntmEndGame = new JMenuItem("End Game");
		mnGame.add(mntmEndGame);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmOption = new JMenuItem("Option");
		mnOptions.add(mntmOption);
		
		JMenuItem mntmSelectMap = new JMenuItem("Select Map");
		mnOptions.add(mntmSelectMap);
		
		JMenu mnOther = new JMenu("Other");
		menuBar.add(mnOther);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mnOther.add(mntmAboutUs);
		
		JMenuItem mntmAboutThisGame = new JMenuItem("About This Game");
		mnOther.add(mntmAboutThisGame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(contentPane.getWidth(), 16));
		this.setResizable(false);
	}
	

	

}
