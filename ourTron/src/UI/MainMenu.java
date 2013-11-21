package UI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends UIElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainMenu menuInstance = new MainMenu();

	//stub
	public void reset() {
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainMenu.getInstance().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Menu");
		getContentPane().setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][][][][][][]"));

		JLabel lblLightRacer = new JLabel();
		lblLightRacer.setIcon(new ImageIcon(MainMenu.class.getResource("/Res/tron.png")));//location of the icon 
		lblLightRacer.setVisible(true);
		getContentPane().add(lblLightRacer, "cell 1 0,alignx center");
		
		//start game bottom
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.getInstance().setVisible(true);
				MainMenu.getInstance().setVisible(false);
			}
		});
		getContentPane().add(btnNewButton, "cell 1 1,alignx center");
		
		//create account button 
		
		JButton button = new JButton("Create Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser createUserFrame = CreateUser.getInstance();
				createUserFrame.setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().add(button, "cell 1 3,alignx center");
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		getContentPane().add(button_1, "cell 1 5,alignx center");
		
	}

	public static MainMenu getInstance() {
		// TODO Auto-generated method stub
		return menuInstance;
	}

}
