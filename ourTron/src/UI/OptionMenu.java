package UI;


import java.awt.EventQueue;


import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JSlider;
import javax.swing.JLabel;

/**
 * Option menu is a menu that can be used to adjust Game Volume (unimplemented) 
 * and Game speed (unimplemented) and Game Power ups (unimplemented). 
 * Option menu is unimplemented. It is unnecessary for the Final project. 
 * This is only something we can do for extra experience, hence the option menu will
 * be disabled in the deliverable if there are not enough time to fully implement its 
 * functionality.
 * @author yuechuan
 *
 */
public class OptionMenu extends UIElement implements reinitializable {

	private static OptionMenu omInstance = new OptionMenu();
	
	/**
	 * 
	 * @return returns a static instance of OptionMenu 
	 */
	static OptionMenu getInstance(){
		return omInstance;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionMenu frame = new OptionMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @deprecated use getInstance() instead to obtains a static instance of OptionMenu
	 */
	public OptionMenu() {
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[]10px[]20px[]10px[]20px[]10px[][grow][]"));
		
		JLabel lblVolume = new JLabel("Volume");
		getContentPane().add(lblVolume, "flowx,cell 1 0,alignx center");
		
		JSlider slider = new JSlider();
		getContentPane().add(slider, "cell 1 1,growx");
		
		JLabel lblPowerUps = new JLabel("Power ups");
		getContentPane().add(lblPowerUps, "cell 1 2,alignx center");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Power up 1");
		rdbtnNewRadioButton_1.setEnabled(false);
		getContentPane().add(rdbtnNewRadioButton_1, "flowx,cell 1 3,alignx center");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Power up 2");
		rdbtnNewRadioButton.setEnabled(false);
		getContentPane().add(rdbtnNewRadioButton, "cell 1 3,alignx center");
		
		JRadioButton rdbtnPowerUp = new JRadioButton("Power up 3");
		rdbtnPowerUp.setEnabled(false);
		getContentPane().add(rdbtnPowerUp, "cell 1 3,alignx center");
		
		JButton btnNewButton = new JButton("Save Settings");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblGameSpeed = new JLabel("Game Speed");
		getContentPane().add(lblGameSpeed, "flowx,cell 1 4,alignx center");
		
		JSlider slider_1 = new JSlider();
		slider_1.setPaintLabels(true);
		slider_1.setPaintTicks(true);
		slider_1.setMajorTickSpacing(25);
		slider_1.setSnapToTicks(true);
		slider_1.setMinorTickSpacing(25);
		slider_1.setValue(100);
		slider_1.setMaximum(250);
		slider_1.setMinimum(50);
		getContentPane().add(slider_1, "cell 1 5,growx");
		getContentPane().add(btnNewButton, "cell 1 7,alignx center,aligny bottom");
	}
	

	@Override
	/**
	 * unimplemented due to time constraints.
	 * @see UI.UIElement#reset()
	 */
	public void reset() {		
	}

}
