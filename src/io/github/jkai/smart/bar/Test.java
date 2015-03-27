package io.github.jkai.smart.bar;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

public class Test {

	private JFrame frame;
	private JTextArea statusBar;
	private JButton orderButton;
	private JButton shutButton;
	private JRadioButton pickDrink1;
	private JRadioButton pickDrink2;
	private JRadioButton pickDrink3;
	private JRadioButton pickDrink4;
	private JRadioButton pickDrink5;
	private JRadioButton pickDrink6;
	private JRadioButton pickDrink7;
	private JRadioButton pickDrink8;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 342, 388, 24);
		frame.getContentPane().add(scrollPane);

		statusBar = new JTextArea();
		statusBar.setEditable(false);
		scrollPane.setRowHeaderView(statusBar);

		DefaultCaret caret = (DefaultCaret) statusBar.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JLabel title = new JLabel("The Smart Bar");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Wide Latin", Font.ITALIC, 16));
		title.setBounds(6, 6, 388, 28);
		frame.getContentPane().add(title);

		orderButton = new JButton("Order!");
		orderButton.setFont(new Font("Courier", Font.PLAIN, 14));
		orderButton.setBounds(6, 314, 191, 24);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOrder();
			}

			private void makeOrder() {
				// TODO Auto-generated method stub
				
			}

		});
		frame.getContentPane().add(orderButton);

		shutButton = new JButton("Stop!");
		shutButton.setFont(new Font("Courier", Font.PLAIN, 14));
		shutButton.setBounds(198, 314, 196, 24);
		shutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopOrder();
			}

			private void stopOrder() {
				// TODO Auto-generated method stub
				
			}

		});
		frame.getContentPane().add(shutButton);

		JLabel lblPick = new JLabel("Pick your drink:");
		lblPick.setBounds(16, 46, 378, 16);
		frame.getContentPane().add(lblPick);

		pickDrink1 = new JRadioButton("Sprite");
		pickDrink1.setToolTipText("Lalala");
		pickDrink1.setBounds(16, 87, 141, 23);
		frame.getContentPane().add(pickDrink1);

		pickDrink2 = new JRadioButton("Fizzy Orange");
		pickDrink2.setBounds(188, 87, 141, 23);
		frame.getContentPane().add(pickDrink2);

		pickDrink3 = new JRadioButton("Fizzy Lemon");
		pickDrink3.setBounds(16, 122, 141, 23);
		frame.getContentPane().add(pickDrink3);

		pickDrink4 = new JRadioButton("Fizzy Fruit");
		pickDrink4.setBounds(188, 122, 141, 23);
		frame.getContentPane().add(pickDrink4);

		pickDrink5 = new JRadioButton("Sour Orange");
		pickDrink5.setBounds(16, 157, 141, 23);
		frame.getContentPane().add(pickDrink5);

		pickDrink6 = new JRadioButton("Fruity Orange");
		pickDrink6.setBounds(188, 157, 141, 23);
		frame.getContentPane().add(pickDrink6);

		pickDrink7 = new JRadioButton("Sour Fruit");
		pickDrink7.setBounds(16, 192, 141, 23);
		frame.getContentPane().add(pickDrink7);

		pickDrink8 = new JRadioButton("All-Star");
		pickDrink8.setBounds(188, 192, 141, 23);
		frame.getContentPane().add(pickDrink8);

		ButtonGroup drinkGroup = new ButtonGroup();
		drinkGroup.add(pickDrink1);
		drinkGroup.add(pickDrink2);
		drinkGroup.add(pickDrink3);
		
	
	}
}
