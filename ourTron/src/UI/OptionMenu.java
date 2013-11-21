package UI;


import java.awt.EventQueue;


import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class OptionMenu extends UIElement {

	private static OptionMenu omInstance = new OptionMenu();

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
	 */
	public OptionMenu() {
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][][][grow]"));
		
		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton, "cell 1 4,alignx center,aligny bottom");
	}

	@Override
	public void reset() {
		// TODO implement the reset()
		
	}

}
