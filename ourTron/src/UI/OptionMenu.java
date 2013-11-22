package UI;


import java.awt.EventQueue;


import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

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
public class OptionMenu extends UIElement {

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
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][][][grow]"));
		
		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton, "cell 1 4,alignx center,aligny bottom");
	}
	

	@Override
	/**
	 * unimplemented due to time constraints.
	 * @see UI.UIElement#reset()
	 */
	public void reset() {		
	}

}
