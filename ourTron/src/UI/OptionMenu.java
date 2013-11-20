package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class OptionMenu extends UIElement {

	private static OptionMenu omInstance = new OptionMenu();

	
	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblOptionMenuStub = DefaultComponentFactory.getInstance().createLabel("option menu stub");
		contentPane.add(lblOptionMenuStub, BorderLayout.CENTER);
	}

	@Override
	public void reset() {
		// TODO implement the reset()
		
	}

}
