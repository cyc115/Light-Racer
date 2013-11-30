package UI;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import Backend.PlayerStatistics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeadToHead extends UIElement implements Reinitializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private static HeadToHead hthInstance = new HeadToHead();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadToHead frame = new HeadToHead();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static HeadToHead getInstance(){
		return hthInstance;
	}
	/**
	 * Create the frame.
	 */
	public HeadToHead() {
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][][][][][]"));
		
		JLabel lblHeadToHead = new JLabel("Head to Head");
		contentPane.add(lblHeadToHead, "cell 1 0,alignx center");
		
		JLabel lblEnterTheTwo = new JLabel(" enter the two player's user name to see thir score");
		contentPane.add(lblEnterTheTwo, "cell 0 1 2 1,growx,alignx center");
		
		JLabel lblUser = new JLabel("User :");
		contentPane.add(lblUser, "cell 0 3");
		
		JLabel lblUser_1 = new JLabel("User :");
		contentPane.add(lblUser_1, "cell 0 4");
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoPage head2Head = new InfoPage("Head to Head",PlayerStatistics.user1VsUser2Wins(textField.getText(), textField_1.getText()),"Back");
				head2Head.setVisible(true);
				getInstance().setVisible(false);  // close the window and reset its values 
				reset();
			}
		});
		contentPane.add(btnAccept, "cell 1 5,alignx left");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 3,growx");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 1 4,growx");
		textField_1.setColumns(10);
	}

	@Override
	/**
	 * resets the gui. extends from reinitializable interface.
	 */
	public void reset() {
		textField.setText("");
		textField_1.setText("");

	}

}
