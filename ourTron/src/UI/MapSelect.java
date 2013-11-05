package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapSelect extends UIElement {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapSelect frame = new MapSelect();
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
	public MapSelect() {
		setTitle("Select map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]30[grow]40", "[]30[][][][][][]"));
		
		JLabel lblWelcomePAnd = new JLabel("welcome P1 and P2 @TODO access player name");
		contentPane.add(lblWelcomePAnd, "cell 0 0 2 1,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("Match 1");
		contentPane.add(lblNewLabel, "cell 0 1,alignx left");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 1 1,growx");
		
		JLabel lblMatch = new JLabel("Match 2");
		contentPane.add(lblMatch, "cell 0 2,alignx left");
		
		JComboBox comboBox_1 = new JComboBox();
		contentPane.add(comboBox_1, "cell 1 2,growx");
		
		
		
		JLabel lblMatch_1 = new JLabel("Match 3");
		contentPane.add(lblMatch_1, "cell 0 3,alignx left");
		
		JComboBox comboBox_2 = new JComboBox();
		contentPane.add(comboBox_2, "cell 1 3,growx");
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 1 6,alignx right");
		
		JButton btnNewButton_1 = new JButton("Clear");
		contentPane.add(btnNewButton_1, "cell 1 6,alignx right");
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_2, "cell 1 6");
		
	}

}
