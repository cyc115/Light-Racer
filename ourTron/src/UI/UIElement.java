package src.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("set Menu to visible");
				MainMenu.getInstance().setVisible(true);
			}
		});

		mnGame.add(mntmNewGame);
		
		JMenuItem mntmEndGame = new JMenuItem("End Game");
		mntmEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnGame.add(mntmEndGame);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmOption = new JMenuItem("Option");
		mntmOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionMenu.getInstance().setVisible(true);
			}
		});
		mnOptions.add(mntmOption);
		
		JMenuItem mntmSelectMap = new JMenuItem("Select Map");
		mntmSelectMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapSelect.getInstance().setVisible(true);
			}
		});
		mnOptions.add(mntmSelectMap);
		
		JMenu mnOther = new JMenu("Other");
		menuBar.add(mnOther);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mntmAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About.getInstance().setVisible(true);
			}
		});
		mnOther.add(mntmAboutUs);
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
