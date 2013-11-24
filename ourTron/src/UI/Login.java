package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Backend.User;
import Backend.UserAuth;
import Backend.UserDataBase;
import GameCore.GameLogic;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JFrame that displays the login dialog.
 * @author yuechuan
 *
 */
public class Login extends UIElement implements reinitializable {
	
	//following are fields that need to be reset during reset() call 
	private JTextField jtfUser = new JTextField();
	private JTextField jtfUser2 = new JTextField();
	private JPasswordField pwdPass;
	private JPasswordField passwordField;
	
	//unique instance
	private static Login loginInstance = new Login(); 
	
	/**
	 * reinitialize the login dialog.
	 */
	public void reset(){
		jtfUser.setText("");
		jtfUser2.setText("");
		pwdPass.setText("");
		passwordField.setText("");
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * use this to obtain a unique instance of the login dialog
	 * @return static instance of the Login dialog
	 */
	public static Login getInstance(){
		return loginInstance;
	}
	

	

	/**
	 * initialize login
	 * @deprecated call getInstance() instead 
	 */
	
	public Login() {
		setTitle("Log in ");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][grow][][][]", "[][][][][][][][][grow]10"));
		this.setAlwaysOnTop(true);
		
		JLabel jl1 = new JLabel("User1"); //title1
		JLabel jl0 = new JLabel("User2");
	
		
		JLabel jl2 = new JLabel("User Name :"); // user name password
		JLabel jl3 = new JLabel("Password :");

		
		JLabel jl4 = new JLabel("User Name :"); // user name password
		JLabel jl5 = new JLabel("Password :");

		
		JPanel panel = new JPanel();  //add title 
		panel.add(jl1);
		getContentPane().add(panel, "cell 0 0 5 1,grow");
		
		getContentPane().add(jl2, "cell 0 1,alignx left"); // user name label and field
		getContentPane().add(jtfUser,"cell 1 1 4 1,growx");
		
		getContentPane().add(jl3, "cell 0 2,alignx trailing");
		
		passwordField = new JPasswordField();
		passwordField.setText("");
		getContentPane().add(passwordField, "cell 1 2,growx,span");
		
		JPanel panel2 = new JPanel();  // title for user 2
		panel2.add(jl0);
		getContentPane().add(panel2, "cell 0 3 5 1,grow");
		getContentPane().add(jl4, "cell 0 4,alignx left"); // user name label and field
		getContentPane().add(jtfUser2,"cell 1 4 4 1,growx");
		
		getContentPane().add(jl5, "cell 0 5,alignx trailing");
		
		pwdPass = new JPasswordField();
		pwdPass.setText("");
		getContentPane().add(pwdPass, "cell 1 5 4 1,growx,span");
		
		JButton b1 = new JButton("Back");  //buttons 
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainMenu menu = MainMenu.getInstance();
				menu.setVisible(true);
			}
		});
		
		
		getContentPane().add(b1, "cell 1 7,aligny bottom"); //add the 3 buttons 
		JButton b2 = new JButton("Submit");
		getContentPane().add(b2, "cell 3 7,aligny bottom");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check the user info.
				if (!UserAuth.isRegistered(jtfUser.getText())
						|| !UserAuth.isRegistered(jtfUser2.getText())) {
					String message = "One of the users do not exist in the database.";
					JOptionPane.showMessageDialog(Login.this, message, "Error",
							JOptionPane.OK_OPTION);
				} else if (jtfUser.getText().equals(jtfUser2.getText())) {
					String message = "You cannot log in the same user twice!";
					JOptionPane.showMessageDialog(Login.this, message, "Error",
							JOptionPane.OK_OPTION);
				} else {
					String u1Password = passwordToString(passwordField
							.getPassword());
					String u2Password = passwordToString(pwdPass.getPassword());

					User user1 = UserAuth.isValidInput(jtfUser.getText(),
							u1Password);
					User user2 = UserAuth.isValidInput(jtfUser2.getText(),
							u2Password);

					if (user1 == null || user2 == null) {
						String message = "A password was entered incorrectly.";
						JOptionPane.showMessageDialog(Login.this, message,
								"Error", JOptionPane.OK_OPTION);
					} else {
						GameLogic.setUser(1,user1);
						GameLogic.setUser(2, user2);
						
						setVisible(false);
						// TODO change the title
						MapSelect choose = MapSelect.getInstance();
						choose.setVisible(true);
					}
				}
			}
		});
		
		//clear button
		JButton b3 = new JButton("Clear");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginInstance.reset();
			}
		});
		getContentPane().add(b3, "cell 4 7,aligny bottom");
	}
	
	//static helper method.
	/**
	 * convert a char array of password to string 
	 * @param pass 
	 * @return String.valueOf(pass) 
	 */
	private static String passwordToString(char[] pass) {
		return String.valueOf(pass);
	}
}
