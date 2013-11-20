package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Instruction extends JFrame {

	private JPanel contentPane;
	private static Instruction instructPanel = new Instruction();
	private final static String INSTRUCTION_STR = 
			"<h1><center>this is a sample instruction page</center></h1> \n" + 
			"<h2><center>Goal of game</center></h2>\n" +

			"<p>sadkjsajfdsa'j fdsalmsdal;fmsdal;kdmfsa\n" +
			"dsakdflsajfdsa\n"+
			"';fdk\n"+
			"spadklfsdla\n" + 
			"sfadlkdfsal,sdmfakdasjfd\n" +
			";asdkl\n"+
			"sksad;ljfkl;dsaf\n"+
			"sdsfkjfdsa;ladskfsdfa</p>\n"+

			"<h2><center>Control</center></h2>\n"+

			"<p>sasjakdsa'dfasadfs\n"+
			"sad\n"+
			"dfsa\n"+
			"dfa\n"+
			"fdsasa</p>\n"+

			"<h2><center>About use</center></h2>\n";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static Instruction getInstance(){
		return instructPanel;
	}

	/**
	 * depreciated, use getInstance instead.
	 */
	public Instruction() {
		setTitle("Instruction");
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
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setText(INSTRUCTION_STR);
		scrollPane.setViewportView(editorPane);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInstance().setVisible(false);
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 1;
		contentPane.add(btnOk, gbc_btnOk);
		
	
	}

	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
