package io.github.jkai.smart.bar;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextPane;

public class GuiTest {

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
	private Thread order;
	private OrderHandler oh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest window = new GuiTest();
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
	public GuiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 276, 388, 24);
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
		orderButton.setBounds(2, 249, 104, 24);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOrder();
			}

		});
		frame.getContentPane().add(orderButton);

		shutButton = new JButton("Stop!");
		shutButton.setFont(new Font("Courier", Font.PLAIN, 14));
		shutButton.setBounds(99, 250, 104, 24);
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
		lblPick.setBounds(16, 46, 129, 16);
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
		pickDrink8.setToolTipText("A mix of two is just not enough...");
		pickDrink8.setBounds(188, 192, 141, 23);
		frame.getContentPane().add(pickDrink8);

		ButtonGroup drinkGroup = new ButtonGroup();
		drinkGroup.add(pickDrink1);
		drinkGroup.add(pickDrink2);
		drinkGroup.add(pickDrink3);

		JButton btnCalibration = new JButton("Calibration");
		btnCalibration.setFont(new Font("Courier", Font.PLAIN, 14));
		btnCalibration.setBounds(198, 251, 194, 24);
		frame.getContentPane().add(btnCalibration);

		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("Courier", Font.ITALIC, 12));
		lblAbout.setToolTipText("[ELEC3907] Winter 2015 - Group Charlie, All Rights Reserved.");
		lblAbout.setBounds(357, 28, 38, 16);
		frame.getContentPane().add(lblAbout);

	}

	private void makeOrder() {
		System.out.println("111");
		oh = new OrderHandler();
		oh.start();
		System.out.println("222");
	}
}
