package UI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * This is the about page, message is stored in the string ABOUT_MESSAGE.
 * Only the static object 'about' of the about class should be called through getInstance() method 
 * @author Yuechuan Chen
 *
 */

public class About extends UIElement {

	private JPanel contentPane;
	private static About about = new About();
	//TODO possibly make this read from a external html file 
	private final static String ABOUT_MESSAGE = "<h1><center> about us</center> </h1> <p> this is  a new paragraph </p>";
		
	/**
	 * returns a static instance of About
	 * @return About object
	 */
	public static About getInstance (){
		return about;
	}
	
	
	/**
	 * Instantiate a new object.
	 * @deprecated
	 * 
	 */
	public About() {
		this.setTitle("About us");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JEditorPane dtrpnAboutUsThis = new JEditorPane();
		dtrpnAboutUsThis.setEditable(false);
		dtrpnAboutUsThis.setContentType("text/html");
		dtrpnAboutUsThis.setText(ABOUT_MESSAGE);
		scrollPane.setViewportView(dtrpnAboutUsThis);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getInstance().setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

	@Override
	/**
	 * resets the About panel to its original text
	 * @deprecated 
	 */
	public void reset() {
		
	}

}
