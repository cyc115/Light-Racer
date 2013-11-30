package UI;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.filechooser.FileFilter;

import GameCore.GameLogic;
import GameCore.Map;
/**
 * Map selection GUI 
 */
public class MapSelect extends UIElement implements Reinitializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MapSelect msInstance = new MapSelect();
	private JFileChooser fileChooser = new JFileChooser();
	private boolean[] mapSet ={false,false,false};
	//get the user name of user 1 and user 2
	String welcomeMsg = "welcome " +
			 GameLogic.getUser(1).getUsername() + 
			" and " + GameLogic.getUser(2).getUsername();
	
	//title label 
	JLabel lblWelcomePAnd = new JLabel(welcomeMsg);
	
	//labels to be updated by map file chooser 
	private JLabel lblNewLabel_1 = new JLabel("please select a map file");
	private JLabel lblPleaseSelectA_1 = new JLabel("please select a map file");
	private JLabel lblPleaseSelectA = new JLabel("please select a map file");
	
	final int MAX_CHAR_SIZE = 30 ; // maximum char displayed in JLabel 
	
	@Override
	public void reset() {
		 lblNewLabel_1 = new JLabel("please select a map file");
		 lblPleaseSelectA_1 = new JLabel("please select a map file");
		 lblPleaseSelectA = new JLabel("please select a map file");
		 welcomeMsg = null;
	}
	
	
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
	private MapSelect() {
		
		//for the JFrame 
		setTitle("Select map");
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]30[250px:250px:250px,grow][]", "[]30[][][][][][]"));
		
		
		//config FileChooser 
		fileChooser.setFileFilter(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
               return file.getName().toUpperCase().endsWith(".MAP");
            }

            @Override
            public String getDescription()
            {
               return ".map file. Map file for light racer game.";
            }
         });
		
		//stuff on the Panel

		contentPane.add(lblWelcomePAnd, "cell 0 0 2 1,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("Match 1");
		contentPane.add(lblNewLabel, "cell 0 1,alignx left");
		
		//label to show path of map file 
		contentPane.add(lblNewLabel_1, "cell 1 1,growx");
		//select button 1
		JButton btnNewButton_3 = new JButton("Select");
		btnNewButton_3.addActionListener(selectButtonListener1);
		
		contentPane.add(btnNewButton_3, "cell 2 1,alignx right");
		
		JLabel lblMatch = new JLabel("Match 2");
		contentPane.add(lblMatch, "cell 0 2,alignx left");
		
		//label to show path of map file 
		contentPane.add(lblPleaseSelectA, "flowx,cell 1 2,growx");
		
		//select button 2
		JButton btnNewButton_4 = new JButton("Select");
		btnNewButton_4.addActionListener(selectButtonListener2);
		contentPane.add(btnNewButton_4, "cell 2 2,alignx right");
		
		
		
		JLabel lblMatch_1 = new JLabel("Match 3");
		contentPane.add(lblMatch_1, "cell 0 3,alignx left");
		
		JButton btnNewButton = new JButton("Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.getInstance().setVisible(true);
				setVisible(false);
			}
		});
		
		//label to show path of map file 
		contentPane.add(lblPleaseSelectA_1, "flowx,cell 1 3,growx");
		
		//select button 3
		JButton btnNewButton_5 = new JButton("Select");
		btnNewButton_5.addActionListener(selectButtonListener3);
		contentPane.add(btnNewButton_5, "cell 2 3,alignx right");
		contentPane.add(btnNewButton, "flowx,cell 1 6,alignx right");
		
		JButton btnNewButton_1 = new JButton("Clear");
		contentPane.add(btnNewButton_1, "cell 1 6,alignx right");
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapSet[0] && mapSet[1] && mapSet[2]) {
					try {
						GameFrame.getInstance().init();
						GameFrame.getInstance().setVisible(true);
						
						
					} catch (Exception excep) {
						excep.printStackTrace();
					}

					setVisible(false);
				}
			}
		});
		contentPane.add(btnNewButton_2, "cell 1 6");
		
	}

	/**
	 * action listener for the select file button
	 */

	ActionListener selectButtonListener1 = new ActionListener(){
		@Override 
		public void actionPerformed(ActionEvent ae){
			switch (fileChooser.showOpenDialog(MapSelect.this)) {
				case JFileChooser.APPROVE_OPTION:
					lblNewLabel_1.setText(shortenStr("" + fileChooser.getSelectedFile(), MAX_CHAR_SIZE)) ;
					Map map = new Map();
					map.loadMapFromFile(fileChooser.getSelectedFile().getAbsolutePath());
					GameLogic.allMaps[0] = map;
					mapSet[0] = true;
	                break;
	
	             case JFileChooser.CANCEL_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Cancelled",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
	                break;
	          
	             case JFileChooser.ERROR_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Error",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
                
			}
		}

	};
	/**
	 * action listener for the select file button2
	 */
	ActionListener selectButtonListener2 = new ActionListener(){
		@Override 
		public void actionPerformed(ActionEvent ae){
			switch (fileChooser.showOpenDialog(MapSelect.this)) {
				case JFileChooser.APPROVE_OPTION:
					lblPleaseSelectA.setText(shortenStr("" + fileChooser.getSelectedFile(), MAX_CHAR_SIZE));
					Map map = new Map();
					map.loadMapFromFile(fileChooser.getSelectedFile().getAbsolutePath());
					GameLogic.allMaps[1] = map;
					mapSet[1] = true;
	                break;
	
	             case JFileChooser.CANCEL_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Cancelled",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
	                break;
	          
	             case JFileChooser.ERROR_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Error",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
                
			}
		}
	};
	
	/**
	 * action listener for the select file button3
	 */
	ActionListener selectButtonListener3 = new ActionListener(){
		@Override 
		public void actionPerformed(ActionEvent ae){
			switch (fileChooser.showOpenDialog(MapSelect.this)) {
				case JFileChooser.APPROVE_OPTION:
					lblPleaseSelectA_1.setText(shortenStr("" + fileChooser.getSelectedFile(), MAX_CHAR_SIZE));
					Map map = new Map();
					map.loadMapFromFile(fileChooser.getSelectedFile().getAbsolutePath());
					GameLogic.allMaps[2] = map;
					mapSet[2] = true;
	                break;
	
	             case JFileChooser.CANCEL_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Cancelled",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
	                break;
	          
	             case JFileChooser.ERROR_OPTION:
	                JOptionPane.showMessageDialog(MapSelect.this, "Error",
	                                              "MapFileChooser",
	                                              JOptionPane.OK_OPTION);
                
			}
		}
	};
	
	
	
	/**
	 * obtain a static instance of Map Selection menu 
	 * @return a static instance of Map Selection menu 
	 */
	public static MapSelect getInstance(){
		return msInstance;
	}
	
	/**
	 * shortens the given str if needed
	 * @param string string to be shortened 
	 * @param maxsize maximum string length 
	 * @return the shortened string 
	 */
	private String shortenStr(String string,int maxsize) {
		String result = "@Error: string not found.";
		int strlen = string.length(); //string len
		
		if (strlen <= maxsize){
			result = string;
		}
		else {
			result = "..." + string.substring(strlen - maxsize);
		}
		return result;
	}

}
