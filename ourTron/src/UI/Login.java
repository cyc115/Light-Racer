package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class Login extends UIElement {
	private JPasswordField pwdPass;
	private JPasswordField passwordField;

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
	 * Create the frame.
	 */
	public Login() {
		setTitle("Log in ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][grow][][][]", "[][][][][][][][][grow]10"));
		
		JLabel jl1 = new JLabel("User1"); //title1
		JLabel jl0 = new JLabel("User2");
	
		
		JLabel jl2 = new JLabel("User Name :"); // user name password
		JLabel jl3 = new JLabel("Password :");

		JTextField jtfUser = new JTextField();
		
		JLabel jl4 = new JLabel("User Name :"); // user name password
		JLabel jl5 = new JLabel("Password :");
		JTextField jtfUser2 = new JTextField();
		
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
		
		

		
		getContentPane().add(b1, "cell 1 7,aligny bottom"); //add the 3 buttons 
		JButton b2 = new JButton("Submit");
		getContentPane().add(b2, "cell 3 7,aligny bottom");
		JButton b3 = new JButton("Clear");
		getContentPane().add(b3, "cell 4 7,aligny bottom");
	}
}
