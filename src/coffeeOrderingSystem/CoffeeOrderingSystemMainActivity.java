package coffeeOrderingSystem;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CoffeeOrderingSystemMainActivity {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
					ArrayList<User> users = new ArrayList<User>();
					users.add(new User("admin", "admin"));
					Login frame = new Login(users);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JPanel(), e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}