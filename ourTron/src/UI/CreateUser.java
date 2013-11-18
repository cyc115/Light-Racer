package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Backend.User;
import Backend.UserAuth;
import Backend.UserDataBase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser extends UIElement {

	private JPanel contentPane;

	// only instance of the UI
	private static CreateUser cuInstance = new CreateUser();

	// things that need to be reset by the reset method
	private JTextField txtUserName;
	private JPasswordField jpfPassNew = new JPasswordField();
	private JPasswordField jpfPassAgain = new JPasswordField();

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		txtUserName.setText("user name");
		jpfPassNew.setText("");
		jpfPassAgain.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @bug throws an exception when the UserDataBase.data does not exist in the
	 *      working directory. Creates it however.
	 */
	public CreateUser() {
		setTitle("Create user dialog");
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]20[grow][][][]",
				"[]20[]10[]10[]10[grow]30"));

		// title
		JLabel lblCreateUserAccount = new JLabel("create user account");
		JPanel panel = new JPanel();
		panel.add(lblCreateUserAccount);
		contentPane.add(panel, "cell 0 0 5 1,alignx center");

		// user name lable and field
		JLabel lblNewUserName = new JLabel("New User Name:");
		contentPane.add(lblNewUserName, "cell 0 1");
		txtUserName = new JTextField();
		txtUserName.setText("user name");
		contentPane.add(txtUserName, "cell 1 1 4 1,grow");
		txtUserName.setColumns(10);

		// password label and fields
		JLabel lblNewPassWord = new JLabel("New Password:");
		JLabel lblNewPassWordAgain = new JLabel("Enter Password Again:");

		contentPane.add(lblNewPassWord, "cell 0 2");
		contentPane.add(jpfPassNew, "cell 1 2 4 1,grow");
		contentPane.add(lblNewPassWordAgain, "cell 0 3");
		contentPane.add(jpfPassAgain, "cell 1 3 4 1,grow");

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.getInstance().setVisible(true);
				CreateUser.getInstance().setVisible(false);
			}
		});
		contentPane.add(btnNewButton, "cell 0 4,alignx center,aligny bottom");

		JButton btnSubmitButton = new JButton("Submit");
		contentPane.add(btnSubmitButton, "cell 2 4,aligny bottom");
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = passwordToString(jpfPassNew.getPassword());
				String passAgain = passwordToString(jpfPassAgain.getPassword());

				// TODO fix bug where same dialog is shown when the same
				// username is added twice. Weird problem. Possibly with
				// JOptionPane since the user is not doubly created. Works if
				// you close and reopen the program though.
				if (UserAuth.isRegistered(txtUserName.getText())) {
					JOptionPane.showMessageDialog(contentPane,
							"User already exists in database", "ERROR",
							JOptionPane.OK_OPTION);
					reset();
				} else if (pass.equals(passAgain)) {
					UserDataBase userDB = new UserDataBase();
					userDB.addUser(new User(txtUserName.getText(), pass));
					JOptionPane.showMessageDialog(contentPane, "User created!",
							"Success", JOptionPane.INFORMATION_MESSAGE);
					reset();
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"The password's don't match", "ERROR",
							JOptionPane.OK_OPTION);
					reset();
				}
			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		contentPane.add(btnClear, "cell 4 4,aligny bottom");

	}

	public static CreateUser getInstance() {
		return cuInstance;
	}

	private static String passwordToString(char[] password) {
		String out = "";
		for (char c : password)
			out += c;
		return out;
	}

}
