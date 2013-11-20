package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * need to be done 
 * @author yuechuan
 *
 */



public class About extends UIElement {

	private JPanel contentPane;
	private static About about = new About();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}
	public static About getInstance (){
		return about;
	}
	
	/**
	 * Create the frame.
	 */
	public About() {
		this.setTitle("About us");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
