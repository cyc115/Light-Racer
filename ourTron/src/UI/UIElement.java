package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Backend.Info;
import Backend.PlayerStatistics;
import GameCore.GameLogic;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * UIElement is the parent class for almost all of the classes in UI package.
 * it defines the size, closing actions of the JFrame as well as hosting the 
 * JMenuBar and all of its options.This class is Abstract so it does not provide
 *  ways to instantiate concrete Objects. Please provide a subclass for instantiation.
 * 
 * @author yuechuan
 *
 */
public abstract class UIElement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public UIElement() {
		setTitle("Tron");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 200, 450, 400);
		
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
		
		JMenuItem mntmHellOfFame = new JMenuItem("Hall Of Fame");
		mntmHellOfFame.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				InfoPage hellOfFame = new InfoPage("Hall Of Fame ", PlayerStatistics.top10Users(), "Back");	//TODO get johanna's class 
				hellOfFame.setVisible(true);
				
			}
		});
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				//redisplay 
				MainMenu.getInstance().setVisible(true);
				CreateUser.getInstance().setVisible(false);
				GameFrame.getInstance().setVisible(false);
				GamePanel.getInstance().setVisible(false);
				Login.getInstance().setVisible(false);
				MapSelect.getInstance().setVisible(false);
				
				
				CreateUser.getInstance().reset();
				GameFrame.getInstance().reset();
				Login.getInstance().reset();
				MapSelect.getInstance().reset();
			}
		});
		mnGame.add(mntmLogOut);
		mnGame.add(mntmHellOfFame);
		
		JMenuItem mntmInstruction = new JMenuItem("Instruction");
		mntmInstruction.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				InfoPage instructionPage = new InfoPage("Instruction", Info.getInstruction(), "Back");   	//TODO get johanna's class
				instructionPage.setVisible(true);
				
			}
		});
		
		JMenuItem mntmHeadToHead = new JMenuItem("Head to Head search");
		
		mntmHeadToHead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeadToHead.getInstance().setVisible(true);
			}
		});
		
		mnGame.add(mntmHeadToHead);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Head to Head current user");
		mntmNewMenuItem.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				InfoPage head2HeadCurrentUser = new InfoPage("Head to Head",
						PlayerStatistics.user1VsUser2Wins(
								GameLogic.getUser(1).getUsername(),
								GameLogic.getUser(2).getUsername() )
						,"Back");
				head2HeadCurrentUser.setVisible(true);
			}
		});
		
		
		mnGame.add(mntmNewMenuItem);
		
		mnGame.add(mntmInstruction);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmOption = new JMenuItem("Option");
		mntmOption.setEnabled(false);
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
		mntmAboutUs.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				InfoPage aboutUsPage = new InfoPage("About Us", Info.getAboutUs(), "Back");  	//TODO get johanna's class
				aboutUsPage.setVisible(true);
				
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
