package coffeeOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnSignUp;
	private JCheckBox chckbxShowPass;

	public Login(ArrayList<User> users) {
		this.users = users;
		setResizable(false);
		setTitle("Coffee Ordering System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 343);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 220, 110));
		panel.setBounds(282, 12, 326, 274);
		contentPane.add(panel);
		panel.setLayout(null);
		
		chckbxShowPass = new JCheckBox("Show Password");
		chckbxShowPass.addActionListener(this);
		chckbxShowPass.setBackground(new Color(255, 220, 110));
		chckbxShowPass.setBounds(185, 147, 120, 24);
		panel.add(chckbxShowPass);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldUsername.setBounds(119, 77, 184, 29);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(this);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordField.setBounds(119, 118, 184, 29);
		panel.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(26, 185, 277, 30);
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		btnLogin.setBackground(new Color(185, 185, 255));
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(this);
		btnSignUp.setBounds(26, 225, 277, 30);
		panel.add(btnSignUp);
		btnSignUp.setBackground(new Color(255, 255, 128));
		
		JLabel lblNewLabelTitle = new JLabel("Coffee Ordering System");
		lblNewLabelTitle.setBounds(12, 21, 302, 34);
		panel.add(lblNewLabelTitle);
		lblNewLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelTitle.setFont(new Font("High Tower Text", Font.BOLD, 24));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(24, 84, 93, 16);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(24, 125, 93, 16);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblLogoTitle = new JLabel();
		lblLogoTitle.setBounds(12, 12, 252, 274);
		contentPane.add(lblLogoTitle);
		lblLogoTitle.setIcon(new ImageIcon(Login.class.getResource("/res/JavaCoffee.png")));
		setLocationRelativeTo(null);
	}
	
	public void login(ArrayList<User> users) {
		if (textFieldUsername.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Username empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		User currentUser = null;
		for	(User user : users) {
			if (user.getUsername().equals(textFieldUsername.getText())) {
				currentUser = user;
			}
		}
		if (currentUser == null) {
			JOptionPane.showMessageDialog(contentPane, "Account does not exists!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (currentUser.getPassword().equals(new String(passwordField.getPassword()))) {
			dispose();
			MainCoffeeOrderingSystem frame = new MainCoffeeOrderingSystem(users, currentUser);
			frame.setVisible(true);
		} else {
			passwordField.setText("");
			JOptionPane.showMessageDialog(contentPane, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chckbxShowPass) {
			if (chckbxShowPass.isSelected()) {
            	passwordField.setEchoChar((char) 0);
            } else {
            	passwordField.setEchoChar('•');
            }
		}
		if (e.getSource() == passwordField || e.getSource() == btnLogin) {
			login(users);
		}
		if (e.getSource() == btnSignUp) {
			textFieldUsername.setText("");
			passwordField.setText("");
			SignUp dialog = new SignUp(users);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
}
