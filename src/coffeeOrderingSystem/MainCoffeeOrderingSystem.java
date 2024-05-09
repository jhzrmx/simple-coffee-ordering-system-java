package coffeeOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class MainCoffeeOrderingSystem extends JFrame implements ActionListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;
	private User user;
	private ArrayList<Coffee> coffees;
	private ArrayList<Order> orders;
	private JPanel contentPane;
	private JTextField textFieldMacchiatoCount;
	private JTextField textFieldEspressoCount;
	private JTextField textFieldCappuccinoCount;
	private JTextField textFieldLatteCount;
	private JTextField textFieldMochaCount;
	private JButton btnCheckout;
	private JMenuItem mntmNewMenuItem;
	private JButton btnMacchiatoDecre;
	private JButton btnMacchiatoIncre;
	private JButton btnEspressoDecre;
	private JButton btnEspressoIncre;
	private JButton btnCappuccinoDecre;
	private JButton btnCappuccinoIncre;
	private JButton btnLatteDecre;
	private JButton btnLatteIncre;
	private JButton btnMochaDecre;
	private JButton btnMochaIncre;
	
	private void initializeCoffees() {
		coffees = new ArrayList<Coffee>();
		coffees.add(new Coffee("Macchiato", 75f));
		coffees.add(new Coffee("Espresso", 65f));
		coffees.add(new Coffee("Cappuccino", 90f));
		coffees.add(new Coffee("Latte", 85f));
		coffees.add(new Coffee("Mocha", 95f));
	}

	public MainCoffeeOrderingSystem(ArrayList<User> users, User user) {
		this.users = users;
		this.user = user;
		initializeCoffees();
		setTitle("Coffee Ordering System (Current User: " + user.getUsername() + ")");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 660);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Logout");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(255, 220, 110));
		panelTitle.setBounds(35, 22, 570, 50);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblSelectYourOrder = new JLabel("Select Order");
		lblSelectYourOrder.setVerticalAlignment(SwingConstants.TOP);
		lblSelectYourOrder.setBounds(12, 12, 546, 30);
		lblSelectYourOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectYourOrder.setFont(new Font("High Tower Text", Font.BOLD, 32));
		panelTitle.add(lblSelectYourOrder);
		
		JPanel panelMacchiato = new JPanel();
		panelMacchiato.setBackground(new Color(255, 220, 110));
		panelMacchiato.setBounds(35, 95, 170, 230);
		contentPane.add(panelMacchiato);
		panelMacchiato.setLayout(null);
		
		JPanel panelMacchiatoPrice = new JPanel();
		panelMacchiatoPrice.setBackground(new Color(255, 240, 190));
		panelMacchiatoPrice.setBounds(118, 129, 40, 30);
		panelMacchiato.add(panelMacchiatoPrice);
		
		JLabel lblMaPrice = new JLabel(String.format("₱ %.0f", coffees.get(0).getCoffeePrice()));
		lblMaPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		panelMacchiatoPrice.add(lblMaPrice);
		
		JLabel lblImageMacchiato = new JLabel();
		lblImageMacchiato.setBounds(10, 11, 150, 150);
		panelMacchiato.add(lblImageMacchiato);
		lblImageMacchiato.setIcon(new ImageIcon(MainCoffeeOrderingSystem.class.getResource("/res/macchiato.jpg")));
		
		JLabel lblMachiato = new JLabel(coffees.get(0).getCoffeeName());
		lblMachiato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMachiato.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMachiato.setBounds(10, 170, 150, 20);
		panelMacchiato.add(lblMachiato);
		
		btnMacchiatoDecre = new JButton("-");
		btnMacchiatoDecre.setBackground(new Color(255, 240, 190));
		btnMacchiatoDecre.addActionListener(this);
		btnMacchiatoDecre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMacchiatoDecre.setBounds(10, 197, 46, 23);
		panelMacchiato.add(btnMacchiatoDecre);
		
		textFieldMacchiatoCount = new JTextField();
		textFieldMacchiatoCount.addMouseWheelListener(this);
		textFieldMacchiatoCount.setBackground(new Color(255, 240, 190));
		textFieldMacchiatoCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldMacchiatoCount.setEditable(false);
		textFieldMacchiatoCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMacchiatoCount.setText("0");
		textFieldMacchiatoCount.setBounds(62, 197, 46, 24);
		panelMacchiato.add(textFieldMacchiatoCount);
		textFieldMacchiatoCount.setColumns(10);
		
		btnMacchiatoIncre = new JButton("+");
		btnMacchiatoIncre.setBackground(new Color(255, 240, 190));
		btnMacchiatoIncre.addActionListener(this);
		btnMacchiatoIncre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMacchiatoIncre.setBounds(113, 197, 46, 23);
		panelMacchiato.add(btnMacchiatoIncre);
		
		JPanel panelEspresso = new JPanel();
		panelEspresso.setBackground(new Color(255, 220, 110));
		panelEspresso.setLayout(null);
		panelEspresso.setBounds(235, 95, 170, 230);
		contentPane.add(panelEspresso);
		
		JPanel panelEspressoPrice = new JPanel();
		panelEspressoPrice.setBackground(new Color(255, 240, 190));
		panelEspressoPrice.setBounds(118, 129, 40, 30);
		panelEspresso.add(panelEspressoPrice);
		
		JLabel lblEPrice = new JLabel(String.format("₱ %.0f", coffees.get(1).getCoffeePrice()));
		lblEPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		panelEspressoPrice.add(lblEPrice);
		
		JLabel lblImageEspresso = new JLabel();
		lblImageEspresso.setIcon(new ImageIcon(MainCoffeeOrderingSystem.class.getResource("/res/espresso.jpg")));
		lblImageEspresso.setBounds(10, 11, 150, 150);
		panelEspresso.add(lblImageEspresso);
		
		JLabel lblEspresso = new JLabel(coffees.get(1).getCoffeeName());
		lblEspresso.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspresso.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEspresso.setBounds(10, 170, 150, 20);
		panelEspresso.add(lblEspresso);
		
		btnEspressoDecre = new JButton("-");
		btnEspressoDecre.setBackground(new Color(255, 240, 190));
		btnEspressoDecre.addActionListener(this);
		btnEspressoDecre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEspressoDecre.setBounds(10, 197, 46, 23);
		panelEspresso.add(btnEspressoDecre);
		
		textFieldEspressoCount = new JTextField();
		textFieldEspressoCount.addMouseWheelListener(this);
		textFieldEspressoCount.setBackground(new Color(255, 240, 190));
		textFieldEspressoCount.setText("0");
		textFieldEspressoCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEspressoCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldEspressoCount.setEditable(false);
		textFieldEspressoCount.setColumns(10);
		textFieldEspressoCount.setBounds(62, 197, 46, 24);
		panelEspresso.add(textFieldEspressoCount);
		
		btnEspressoIncre = new JButton("+");
		btnEspressoIncre.setBackground(new Color(255, 240, 190));
		btnEspressoIncre.addActionListener(this);
		btnEspressoIncre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEspressoIncre.setBounds(113, 197, 46, 23);
		panelEspresso.add(btnEspressoIncre);
		
		JPanel panelCappuccino = new JPanel();
		panelCappuccino.setBackground(new Color(255, 220, 110));
		panelCappuccino.setLayout(null);
		panelCappuccino.setBounds(435, 95, 170, 230);
		contentPane.add(panelCappuccino);
		
		JPanel panelCappuccinoPrice = new JPanel();
		panelCappuccinoPrice.setBackground(new Color(255, 240, 190));
		panelCappuccinoPrice.setBounds(118, 129, 40, 30);
		panelCappuccino.add(panelCappuccinoPrice);
		
		JLabel lblCPrice = new JLabel(String.format("₱ %.0f", coffees.get(2).getCoffeePrice()));
		lblCPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		panelCappuccinoPrice.add(lblCPrice);
		
		JLabel lblImageCappuccino = new JLabel();
		lblImageCappuccino.setIcon(new ImageIcon(MainCoffeeOrderingSystem.class.getResource("/res/cappuccino.jpg")));
		lblImageCappuccino.setBounds(10, 11, 150, 150);
		panelCappuccino.add(lblImageCappuccino);
		
		JLabel lblCappuccino = new JLabel(coffees.get(2).getCoffeeName());
		lblCappuccino.setHorizontalAlignment(SwingConstants.CENTER);
		lblCappuccino.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCappuccino.setBounds(10, 170, 150, 20);
		panelCappuccino.add(lblCappuccino);
		
		btnCappuccinoDecre = new JButton("-");
		btnCappuccinoDecre.setBackground(new Color(255, 240, 190));
		btnCappuccinoDecre.addActionListener(this);
		btnCappuccinoDecre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCappuccinoDecre.setBounds(10, 197, 46, 23);
		panelCappuccino.add(btnCappuccinoDecre);
		
		textFieldCappuccinoCount = new JTextField();
		textFieldCappuccinoCount.addMouseWheelListener(this);
		textFieldCappuccinoCount.setBackground(new Color(255, 240, 190));
		textFieldCappuccinoCount.setText("0");
		textFieldCappuccinoCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCappuccinoCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldCappuccinoCount.setEditable(false);
		textFieldCappuccinoCount.setColumns(10);
		textFieldCappuccinoCount.setBounds(62, 197, 46, 24);
		panelCappuccino.add(textFieldCappuccinoCount);
		
		btnCappuccinoIncre = new JButton("+");
		btnCappuccinoIncre.setBackground(new Color(255, 240, 190));
		btnCappuccinoIncre.addActionListener(this);
		btnCappuccinoIncre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCappuccinoIncre.setBounds(113, 197, 46, 23);
		panelCappuccino.add(btnCappuccinoIncre);
		
		JPanel panelLatte = new JPanel();
		panelLatte.setBackground(new Color(255, 220, 110));
		panelLatte.setLayout(null);
		panelLatte.setBounds(35, 345, 170, 230);
		contentPane.add(panelLatte);
		
		JPanel panelLattePrice = new JPanel();
		panelLattePrice.setBackground(new Color(255, 240, 190));
		panelLattePrice.setBounds(118, 129, 40, 30);
		panelLatte.add(panelLattePrice);
		
		JLabel lblLPrice = new JLabel(String.format("₱ %.0f", coffees.get(3).getCoffeePrice()));
		lblLPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		panelLattePrice.add(lblLPrice);
		
		JLabel lblImageLatte = new JLabel();
		lblImageLatte.setIcon(new ImageIcon(MainCoffeeOrderingSystem.class.getResource("/res/latte.jpg")));
		lblImageLatte.setBounds(10, 11, 150, 150);
		panelLatte.add(lblImageLatte);
		
		JLabel lblLatte = new JLabel(coffees.get(3).getCoffeeName());
		lblLatte.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatte.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLatte.setBounds(10, 170, 150, 20);
		panelLatte.add(lblLatte);
		
		btnLatteDecre = new JButton("-");
		btnLatteDecre.setBackground(new Color(255, 240, 190));
		btnLatteDecre.addActionListener(this);
		btnLatteDecre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLatteDecre.setBounds(10, 197, 46, 23);
		panelLatte.add(btnLatteDecre);
		
		textFieldLatteCount = new JTextField();
		textFieldLatteCount.addMouseWheelListener(this);
		textFieldLatteCount.setBackground(new Color(255, 240, 190));
		textFieldLatteCount.setText("0");
		textFieldLatteCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLatteCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldLatteCount.setEditable(false);
		textFieldLatteCount.setColumns(10);
		textFieldLatteCount.setBounds(62, 197, 46, 24);
		panelLatte.add(textFieldLatteCount);
		
		btnLatteIncre = new JButton("+");
		btnLatteIncre.setBackground(new Color(255, 240, 190));
		btnLatteIncre.addActionListener(this);
		btnLatteIncre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLatteIncre.setBounds(113, 197, 46, 23);
		panelLatte.add(btnLatteIncre);
		
		JPanel panelMocha = new JPanel();
		panelMocha.setLayout(null);
		panelMocha.setBackground(new Color(255, 220, 110));
		panelMocha.setBounds(235, 345, 170, 230);
		contentPane.add(panelMocha);
		
		JPanel panelMochaPrice = new JPanel();
		panelMochaPrice.setBackground(new Color(255, 240, 190));
		panelMochaPrice.setBounds(118, 129, 40, 30);
		panelMocha.add(panelMochaPrice);
		
		JLabel lblMoPrice = new JLabel(String.format("₱ %.0f", coffees.get(4).getCoffeePrice()));
		lblMoPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		panelMochaPrice.add(lblMoPrice);
		
		JLabel lblImageMocha = new JLabel();
		lblImageMocha.setIcon(new ImageIcon(MainCoffeeOrderingSystem.class.getResource("/res/mocha.jpg")));
		lblImageMocha.setBounds(10, 11, 150, 150);
		panelMocha.add(lblImageMocha);
		
		JLabel lblMocha = new JLabel(coffees.get(4).getCoffeeName());
		lblMocha.setHorizontalAlignment(SwingConstants.CENTER);
		lblMocha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMocha.setBounds(10, 170, 150, 20);
		panelMocha.add(lblMocha);
		
		btnMochaDecre = new JButton("-");
		btnMochaDecre.setBackground(new Color(255, 240, 190));
		btnMochaDecre.addActionListener(this);
		btnMochaDecre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMochaDecre.setBounds(10, 197, 46, 23);
		panelMocha.add(btnMochaDecre);
		
		textFieldMochaCount = new JTextField();
		textFieldMochaCount.addMouseWheelListener(this);
		textFieldMochaCount.setBackground(new Color(255, 240, 190));
		textFieldMochaCount.setText("0");
		textFieldMochaCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMochaCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldMochaCount.setEditable(false);
		textFieldMochaCount.setColumns(10);
		textFieldMochaCount.setBounds(62, 197, 46, 24);
		panelMocha.add(textFieldMochaCount);
		
		btnMochaIncre = new JButton("+");
		btnMochaIncre.setBackground(new Color(255, 240, 190));
		btnMochaIncre.addActionListener(this);
		btnMochaIncre.setFont(new Font("Dialog", Font.BOLD, 16));
		btnMochaIncre.setBounds(113, 197, 46, 23);
		panelMocha.add(btnMochaIncre);
		
		JPanel panelOperations = new JPanel();
		panelOperations.setBackground(new Color(255, 220, 110));
		panelOperations.setBounds(435, 345, 170, 230);
		contentPane.add(panelOperations);
		panelOperations.setLayout(null);
		
		btnCheckout = new JButton("Check Out");
		btnCheckout.setEnabled(false);
		btnCheckout.addActionListener(this);
		btnCheckout.setBounds(25, 88, 120, 40);
		btnCheckout.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCheckout.setBackground(new Color(128, 255, 128));
		panelOperations.add(btnCheckout);
		setLocationRelativeTo(null);
	}
	
	private void increaseCount(JTextField tf, JButton btnDecre) {
		int numCount = Integer.parseInt(tf.getText());
		int limit = 10;
		if (numCount >= limit) {
			JOptionPane.showMessageDialog(contentPane, limit + " limit only!", "Message", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (numCount >= 0) {
			btnDecre.setEnabled(true);
		}
		numCount++;
		tf.setText(numCount + "");
		if (isValidCheckout()) {
			btnCheckout.setEnabled(true);
		} else {
			btnCheckout.setEnabled(false);
		}
	}
	
	private void decreaseCount(JTextField tf, JButton btnDecre) {
		int numCount = Integer.parseInt(tf.getText());
		if (numCount <= 0) {
			btnDecre.setEnabled(false);
			return;
		}
		numCount--;
		tf.setText(numCount + "");
		if (isValidCheckout()) {
			btnCheckout.setEnabled(true);
		} else {
			btnCheckout.setEnabled(false);
		}
	}
	
	private boolean isValidCheckout() {
		if (textFieldMacchiatoCount.getText().trim().equals("0") && textFieldEspressoCount.getText().trim().equals("0") && textFieldCappuccinoCount.getText().trim().equals("0") && textFieldLatteCount.getText().trim().equals("0") && textFieldMochaCount.getText().trim().equals("0")) {
			return false;
		}
		return true;
	}
	
	private void increaseDecreaseViaMouseWheel(MouseWheelEvent e, JTextField tf, JButton btnDecre) {
		if (e.getWheelRotation() > 0) {
			decreaseCount(tf, btnDecre);
		} else {
			increaseCount(tf, btnDecre);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getSource() == textFieldMacchiatoCount) {
			increaseDecreaseViaMouseWheel(e, textFieldMacchiatoCount, btnMacchiatoDecre);
		}
		if (e.getSource() == textFieldEspressoCount) {
			increaseDecreaseViaMouseWheel(e, textFieldEspressoCount, btnEspressoDecre);
		}
		if (e.getSource() == textFieldCappuccinoCount) {
			increaseDecreaseViaMouseWheel(e, textFieldCappuccinoCount, btnCappuccinoDecre);
		}
		if (e.getSource() == textFieldLatteCount) {
			increaseDecreaseViaMouseWheel(e, textFieldLatteCount, btnLatteDecre);
		}
		if (e.getSource() == textFieldMochaCount) {
			increaseDecreaseViaMouseWheel(e, textFieldMochaCount, btnMochaDecre);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheckout) {
			orders = new ArrayList<Order>();
			ArrayList<JTextField> tfs = new ArrayList<JTextField>();
			tfs.add(textFieldMacchiatoCount);
			tfs.add(textFieldEspressoCount);
			tfs.add(textFieldCappuccinoCount);
			tfs.add(textFieldLatteCount);
			tfs.add(textFieldMochaCount);
			StringBuilder sb = new StringBuilder();
			float subtotal = 0f;
			for (int i=0; i<tfs.size(); i++) {
				int quantity = Integer.parseInt(tfs.get(i).getText().trim());
				if (quantity > 0) {
					orders.add(new Order(coffees.get(i), quantity));
					sb.append(quantity + " " + coffees.get(i).getCoffeeName() + " (" + String.format("₱ %.2f", coffees.get(i).getCoffeePrice()*quantity) + ")\n");
					subtotal += coffees.get(i).getCoffeePrice()*quantity;
				}
			}
			sb.append(String.format("%n-------------------------------------%nSUBTOTAL: ₱ %.2f%n", subtotal));
			tfs.clear();
			if (JOptionPane.showConfirmDialog(contentPane, "Order List:\n\n" + sb.toString() + "\nProceed to payment?", "Messsage", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				dispose();
				Checkout frame = new Checkout(users, user, orders);
				frame.setVisible(true);
			}
		}
		if (e.getSource() == mntmNewMenuItem) {
			if (JOptionPane.showConfirmDialog(contentPane, "Are you sure to logout?", "Messsage", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				dispose();
				Login frame = new Login(users);
				frame.setVisible(true);
			}
		}
		if (e.getSource() == btnMacchiatoDecre) {
			decreaseCount(textFieldMacchiatoCount, btnMacchiatoDecre);
		}
		if (e.getSource() == btnMacchiatoIncre) {
			increaseCount(textFieldMacchiatoCount, btnMacchiatoDecre);
		}
		if (e.getSource() == btnEspressoDecre) {
			decreaseCount(textFieldEspressoCount, btnEspressoDecre);
		}
		if (e.getSource() == btnEspressoIncre) {
			increaseCount(textFieldEspressoCount, btnEspressoDecre);
		}
		if (e.getSource() == btnCappuccinoDecre) {
			decreaseCount(textFieldCappuccinoCount, btnCappuccinoDecre);
		}
		if (e.getSource() == btnCappuccinoIncre) {
			increaseCount(textFieldCappuccinoCount, btnCappuccinoDecre);
		}
		if (e.getSource() == btnLatteDecre) {
			decreaseCount(textFieldLatteCount, btnLatteDecre);
		}
		if (e.getSource() == btnLatteIncre) {
			increaseCount(textFieldLatteCount, btnLatteDecre);
		}
		if (e.getSource() == btnMochaDecre) {
			decreaseCount(textFieldMochaCount, btnMochaDecre);
		}
		if (e.getSource() == btnMochaIncre) {
			increaseCount(textFieldMochaCount, btnMochaDecre);
		}
	}
}
