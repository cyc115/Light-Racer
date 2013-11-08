package UI;

<<<<<<< HEAD
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainMenu extends UIElement {

	private JPanel contentPane;

=======
import java.awt.EventQueue;

import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends UIElement {

	public static MainMenu menuInstance = new MainMenu();

	public void reset() {
		menuInstance = new MainMenu();
		
	}
	
	
>>>>>>> MikeGui
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
<<<<<<< HEAD
					MainMenu frame = new MainMenu();
=======
					MainMenu frame = menuInstance;
>>>>>>> MikeGui
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
<<<<<<< HEAD
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		JButton btnNewButton_2 = new JButton("Start Game");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.ipady = 50;
		gbc_btnNewButton_2.ipadx = 50;
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 1;
		gbc_btnNewButton_2.gridheight = 1;
		gbc_btnNewButton_2.gridwidth = 3;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JButton btnNewButton_1 = new JButton("Options");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.ipady = 50;
		gbc_btnNewButton_1.ipadx = 50;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		gbc_btnNewButton_1.gridheight = 1;
		gbc_btnNewButton_1.gridwidth = 3;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Exit Game");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.ipady = 50;
		gbc_btnNewButton.ipadx = 50;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		gbc_btnNewButton.gridheight = 1;
		gbc_btnNewButton.gridwidth = 3;
		
		contentPane.add(btnNewButton, gbc_btnNewButton);
=======
		setTitle("Menu");
		getContentPane().setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][][][][][][]"));
		
		JLabel lblLightRacer = new JLabel("Light Racer @TODO add logo");
		getContentPane().add(lblLightRacer, "cell 1 0,alignx center");
		
		//start game bottom
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginFrame = Login.getInstance();
				loginFrame.setVisible(true);
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
				MainMenu.getInstance().setVisible(false);
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
>>>>>>> MikeGui
	}

}
