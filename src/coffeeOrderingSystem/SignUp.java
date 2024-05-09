package coffeeOrderingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SignUp extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<User> users;
	private JPasswordField passwordField;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldConfirm;
	private JLabel lblConfirmPassword;
	private JButton btnSignUp;
	
	public SignUp(ArrayList<User> users) {
		this.users = users;
		setTitle("Sign Up");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 415, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 240, 190));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEnterYourUsername = new JLabel("Enter your username:");
		lblEnterYourUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEnterYourUsername.setBounds(17, 71, 151, 16);
		contentPanel.add(lblEnterYourUsername);
		
		JLabel lblPassword = new JLabel("Enter your password:");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setBounds(17, 112, 151, 16);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordField.setBounds(190, 105, 184, 29);
		contentPanel.add(passwordField);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(190, 64, 184, 29);
		contentPanel.add(textFieldUsername);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordFieldConfirm.setBounds(190, 147, 184, 29);
		contentPanel.add(passwordFieldConfirm);
		
		lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblConfirmPassword.setBounds(17, 154, 151, 16);
		contentPanel.add(lblConfirmPassword);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBackground(new Color(185, 185, 255));
		btnSignUp.addActionListener(this);
		btnSignUp.setBounds(115, 200, 151, 37);
		contentPanel.add(btnSignUp);
		
		JLabel lblAddNewUser = new JLabel("Add New User");
		lblAddNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewUser.setFont(new Font("High Tower Text", Font.BOLD, 26));
		lblAddNewUser.setBounds(12, 25, 375, 26);
		contentPanel.add(lblAddNewUser);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSignUp) {
			if (textFieldUsername.getText().trim().isEmpty() || new String(passwordField.getPassword()).trim().isEmpty()) {
				JOptionPane.showMessageDialog(contentPanel, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (new String(passwordField.getPassword()).equals(new String(passwordFieldConfirm.getPassword()))) {
				users.add(new User(textFieldUsername.getText(), new String(passwordField.getPassword())));
				JOptionPane.showMessageDialog(contentPanel, "Sign Up successful!", "Message", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(contentPanel, "Passwords does not match!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
