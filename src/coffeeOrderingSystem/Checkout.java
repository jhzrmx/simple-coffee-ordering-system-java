package coffeeOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Checkout extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;
	private ArrayList<Order> orders;
	private User user;
	private float subtotal = 0f;
	private JPanel contentPane;
	private JTextField textFieldSubtotal;
	private JTextField textFieldPayment;
	private JTextField textFieldChange;
	private JButton btnPlaceOrder;
	private JButton btnNewOrder;
	private JMenuItem mntmCancelOrder;
	private JTextArea txtReceipt;

	public Checkout(ArrayList<User> users, User user, ArrayList<Order> orders) {
		this.users = users;
		this.orders = orders;
		this.user = user;
		setTitle("Coffee Ordering System (Current User: " + user.getUsername() + ")");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 485);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		mntmCancelOrder = new JMenuItem("Cancel order");
		mntmCancelOrder.addActionListener(this);
		mnOptions.add(mntmCancelOrder);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 240, 190));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtReceipt = new JTextArea();
		txtReceipt.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtReceipt.setText("\n  Receipt goes here...");
		txtReceipt.setEditable(false);
		txtReceipt.setBounds(269, 24, 270, 377);
		contentPane.add(txtReceipt);
		
		JLabel lblPayment = new JLabel("Payment: ₱ ");
		lblPayment.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPayment.setBounds(24, 244, 103, 24);
		contentPane.add(lblPayment);
		
		for(Order order : orders) {
			subtotal += order.getCoffee().getCoffeePrice()*order.getQuantity();
		}
		textFieldSubtotal = new JTextField(String.format("%.2f", subtotal));
		textFieldSubtotal.setEditable(false);
		textFieldSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSubtotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldSubtotal.setColumns(10);
		textFieldSubtotal.setBounds(121, 206, 121, 29);
		contentPane.add(textFieldSubtotal);
		
		JList<String> listOrder = new JList<String>();
		listOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listOrder.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			public int getSize() {
				return orders.size();
			}
			public String getElementAt(int index) {
				return orders.get(index).getQuantity() + " " + orders.get(index).getCoffee().getCoffeeName() + " (" + String.format("₱ %.2f", orders.get(index).getCoffee().getCoffeePrice()*orders.get(index).getQuantity()) + ")";
			}
		});
		listOrder.setBounds(23, 64, 219, 118);
		contentPane.add(listOrder);
		
		JLabel lblSubtotal = new JLabel("Subtotal:  ₱ ");
		lblSubtotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSubtotal.setBounds(24, 209, 103, 24);
		contentPane.add(lblSubtotal);
		
		btnNewOrder = new JButton("New Order");
		btnNewOrder.setVisible(false);
		btnNewOrder.addActionListener(this);
		btnNewOrder.setBackground(new Color(255, 255, 128));
		btnNewOrder.setBounds(81, 375, 98, 26);
		contentPane.add(btnNewOrder);
		
		btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(this);
		btnPlaceOrder.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPlaceOrder.setBackground(new Color(255, 220, 110));
		btnPlaceOrder.setBounds(24, 323, 218, 40);
		contentPane.add(btnPlaceOrder);
		
		textFieldPayment = new JTextField();
		textFieldPayment.addActionListener(this);
		textFieldPayment.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPayment.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldPayment.setColumns(10);
		textFieldPayment.setBounds(121, 242, 121, 29);
		contentPane.add(textFieldPayment);
		
		JLabel lblChange = new JLabel("Change:   ₱ ");
		lblChange.setFont(new Font("Dialog", Font.BOLD, 16));
		lblChange.setBounds(24, 279, 103, 24);
		contentPane.add(lblChange);
		
		textFieldChange = new JTextField();
		textFieldChange.setEditable(false);
		textFieldChange.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldChange.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldChange.setColumns(10);
		textFieldChange.setBounds(121, 277, 121, 29);
		contentPane.add(textFieldChange);
		
		JPanel panelOrderList = new JPanel();
		panelOrderList.setBackground(new Color(255, 230, 150));
		panelOrderList.setBounds(23, 24, 219, 31);
		contentPane.add(panelOrderList);
		
		JLabel lblOrderList = new JLabel("Order List:");
		lblOrderList.setFont(new Font("Dialog", Font.BOLD, 16));
		panelOrderList.add(lblOrderList);
		setLocationRelativeTo(null);
	}
	
	private void placeOrder() {
		if (!isValidPayment()) {
			return;
		}
		btnNewOrder.setEnabled(true);
		btnPlaceOrder.setEnabled(false);
		textFieldPayment.setText(String.format("%.2f", Float.parseFloat(textFieldPayment.getText())));
		textFieldPayment.setEditable(false);
		btnNewOrder.setVisible(true);
		btnPlaceOrder.setText("Order Placed!");
		textFieldChange.setText(String.format("%.2f", Float.parseFloat(textFieldPayment.getText())-subtotal));
		mntmCancelOrder.setEnabled(false);
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------------------\n");
		sb.append("           OFFICIAL RECEIPT           \n");
		sb.append("        COFFEE ORDERING SYSTEM        \n");
		sb.append("--------------------------------------\n");
		sb.append("  Date: " + new SimpleDateFormat("MM-dd-YYYY hh:mm:ss a").format(new Date()) + "\n");
		sb.append("  Address: Sipocot, Camarines Sur     \n");
		sb.append("  Transaction No. : " + new Random().nextInt(1000000, 9999999) + "\n");
		sb.append("  Cashier: " + user.getUsername() +  "\n");
		sb.append("--------------------------------------\n");
		sb.append("  Order List:                         \n");
		for (Order order : orders) {
			sb.append("    " + String.format("%-15s ₱ %.2f", order.getQuantity() + " " + order.getCoffee().getCoffeeName(), + order.getCoffee().getCoffeePrice()*order.getQuantity()) + "\n");
		}
		sb.append("--------------------------------------\n");
		sb.append("  Subtotal: ₱ " + String.format("%.2f", subtotal) + "\n");
		sb.append("  Payment : ₱ " + String.format("%.2f", Float.parseFloat(textFieldPayment.getText())) + "\n");
		sb.append("  Change  : ₱ " + String.format("%.2f", Float.parseFloat(textFieldPayment.getText())-subtotal) + "\n");
		sb.append("--------------------------------------\n");
		sb.append("        Thank you, come again!        \n");
		sb.append("--------------------------------------");
		txtReceipt.setText(sb.toString());
		try {
			FileWriter fw = new FileWriter("Receipt-" + new SimpleDateFormat("MM-dd-YYYY-hh-mm-ss-a").format(new Date()) + ".txt");
			fw.write(sb.toString());
			fw.close();
			JOptionPane.showMessageDialog(contentPane, "Order has been placed!\nReceipt file has been created.", "Message", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(contentPane, "Error generating receipt file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isValidPayment() {
		float payment = 0f;
		boolean status = true;
		try {
			payment = Float.parseFloat(textFieldPayment.getText().trim());
			if (payment < Float.parseFloat(textFieldSubtotal.getText().trim())) {
				JOptionPane.showMessageDialog(contentPane, "Insufficient Payment!", "Error", JOptionPane.ERROR_MESSAGE);
				status = false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPane, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
			status = false;
		}
		return status;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmCancelOrder) {
			if (JOptionPane.showConfirmDialog(contentPane, "Cancel this current order?", "Action", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				dispose();
				MainCoffeeOrderingSystem frame = new MainCoffeeOrderingSystem(users, user);
				frame.setVisible(true);
			}
		}
		if (e.getSource() == btnPlaceOrder || e.getSource() == textFieldPayment) {
			placeOrder();
		}
		if (e.getSource() == btnNewOrder) {
			dispose();
			MainCoffeeOrderingSystem frame = new MainCoffeeOrderingSystem(users, user);
			frame.setVisible(true);
		}
	}
}
