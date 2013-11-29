package UI;

import java.awt.EventQueue;


import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * InfoPage provides a quick and easy way to instantiate multiple information dialog.
 * The usages are : About page , Instruction page, Game Score page.
 * Information will be displayed in HTML, allowing the displayed information to be flexible.
 * 
 * note : this is not a singleton as other UIs in this game. 
 * 
 * @author yuechuan
 *
 */
public class InfoPage extends UIElement implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoPage window = new InfoPage("helloworld","<h1><center>hello</center></h1> <p><center>world</center></p>", "ok");
					InfoPage window2 = new InfoPage("helloworld2","<h1>hello2</h1> <p><center>sdasadas</center></p>", "ok");
					window.setVisible(true);
					window2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


/**
 * construct the dialog 
 * @param title is the title of the dialog 
 * @param HTMLTextBody text displayed in the dialog formated as html
 * @param buttonMsg the message displayed on the button.
 */
	public InfoPage(String title,String HTMLTextBody , String buttonMsg) {
		
		 JScrollPane scrollPane = new JScrollPane();
		 JEditorPane editorPane = new JEditorPane();
		 JButton btnNewButton = new JButton("New button");
		
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[grow][]"));
		getContentPane().add(scrollPane, "cell 1 0,grow,span 1 1");
		scrollPane.setViewportView(editorPane);
		getContentPane().add(btnNewButton, "cell 1 1,alignx center,growy");
		
		//set title 
		this.setTitle(title);
		//set body text
		editorPane.setContentType("text/html");
		editorPane.setText(HTMLTextBody);
		editorPane.setEditable(false);
		//set button text		
		btnNewButton.setText(buttonMsg);
		btnNewButton.addActionListener(this);
	}
	
	/**
	 * button action
	 */
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}



}
