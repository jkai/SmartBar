package io.github.jkai.smart.bar;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final long shotTime = 30000;
	private static CommunicationInterface ci = null;

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

	public Client(CommunicationInterface ciPar) {
		super("The Smart Bar Ordering System");
		initialize();
		ci = ciPar;
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String hostName;
					InputStreamReader is = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(is);
					System.out.println("Enter the Server IP here:");
					hostName = br.readLine();
					ci = connectToServer(hostName);

					Client application = new Client(ci);
					application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					application.getFrame().setVisible(true);
					application.getFrame().toFront();
					application.getFrame().repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static CommunicationInterface connectToServer(String host)
			throws MalformedURLException, RemoteException, NotBoundException {
		if (System.getSecurityManager() == null) {
			System.out.println("Creating new security manager");
			System.setSecurityManager(new SecurityManager());
		}
		try {
			ci = (CommunicationInterface) Naming.lookup("rmi://" + host + ":"
					+ CommunicationInterface.RMIRegistryPort + "/"
					+ CommunicationInterface.SMART_BAR_SERVEICE);
			System.out.println("Connected to " + host + ":"
					+ CommunicationInterface.RMIRegistryPort + "/"
					+ CommunicationInterface.SMART_BAR_SERVEICE);
		} catch (UnmarshalException ue) {
			System.err
					.println("PiInfoClient:connectToServer() - UnmarshalException - Check that the server can access it's configuration / policy files");
			ue.printStackTrace(System.err);
			throw ue;
		}
		return ci;

	}

	public JFrame getFrame() {
		return this;
	}

	private void initialize() {
		getFrame().setResizable(false);
		getFrame().setBounds(100, 100, 400, 400);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 342, 388, 24);
		this.getContentPane().add(scrollPane);

		statusBar = new JTextArea();
		statusBar.setEditable(false);
		scrollPane.setRowHeaderView(statusBar);

		DefaultCaret caret = (DefaultCaret) statusBar.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JLabel title = new JLabel("The Smart Bar");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Wide Latin", Font.ITALIC, 16));
		title.setBounds(6, 6, 388, 28);
		this.getContentPane().add(title);

		orderButton = new JButton("Order!");
		orderButton.setFont(new Font("Courier", Font.PLAIN, 14));
		orderButton.setBounds(80, 303, 117, 35);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					makeOrder();
				} catch (RemoteException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}

		});
		this.getContentPane().add(orderButton);

		shutButton = new JButton("Stop!");
		shutButton.setFont(new Font("Courier", Font.PLAIN, 14));
		shutButton.setBounds(220, 303, 117, 35);
		shutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stopOrder();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}

		});
		this.getContentPane().add(shutButton);

		JLabel lblPick = new JLabel("Pick your drink:");
		lblPick.setBounds(16, 46, 378, 16);
		this.getContentPane().add(lblPick);

		pickDrink1 = new JRadioButton("1.Sprite");
		pickDrink1.setBounds(16, 87, 141, 23);
		this.getContentPane().add(pickDrink1);

		pickDrink2 = new JRadioButton("2.Fizzy Orange");
		pickDrink2.setBounds(188, 87, 141, 23);
		this.getContentPane().add(pickDrink2);

		pickDrink3 = new JRadioButton("3.Fizzy Lemon");
		pickDrink3.setBounds(16, 122, 141, 23);
		this.getContentPane().add(pickDrink3);

		pickDrink4 = new JRadioButton("4.Fizzy Fruit");
		pickDrink4.setBounds(188, 122, 141, 23);
		this.getContentPane().add(pickDrink4);

		pickDrink5 = new JRadioButton("5.Sour Orange");
		pickDrink5.setBounds(16, 157, 141, 23);
		this.getContentPane().add(pickDrink5);

		pickDrink6 = new JRadioButton("6.Fruity Orange");
		pickDrink6.setBounds(188, 157, 141, 23);
		this.getContentPane().add(pickDrink6);

		pickDrink7 = new JRadioButton("7.Sour Fruit");
		pickDrink7.setBounds(16, 192, 141, 23);
		this.getContentPane().add(pickDrink7);

		pickDrink8 = new JRadioButton("8.All-Star");
		pickDrink8.setBounds(188, 192, 141, 23);
		this.getContentPane().add(pickDrink8);

		ButtonGroup drinkGroup = new ButtonGroup();
		drinkGroup.add(pickDrink1);
		drinkGroup.add(pickDrink2);
		drinkGroup.add(pickDrink3);
		drinkGroup.add(pickDrink4);
		drinkGroup.add(pickDrink5);
		drinkGroup.add(pickDrink6);
		drinkGroup.add(pickDrink7);
		drinkGroup.add(pickDrink8);
	}

	public void makeOrder() throws RemoteException, InterruptedException {
		if (ci.cupIsPresent()) {
			checkOrder();
		} else {
			updateStatus("No cup detected!");
		}
	}

	private void checkOrder() throws RemoteException, InterruptedException {
		if (pickDrink1.isSelected()) {
			updateStatus("Drink 1 is selected");
			proceedOrder(1);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 2 is selected");
			proceedOrder(2);
		} else if (pickDrink3.isSelected()) {
			updateStatus("Drink 3 is selected");
			proceedOrder(3);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 4 is selected");
			proceedOrder(4);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 5 is selected");
			proceedOrder(5);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 6 is selected");
			proceedOrder(6);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 7 is selected");
			proceedOrder(7);
		} else if (pickDrink2.isSelected()) {
			updateStatus("Drink 8 is selected");
			proceedOrder(8);
		} else {
			updateStatus("Please select one drink!");
		}
	}

	private void proceedOrder(int i) throws RemoteException,
			InterruptedException {
		switch (i) {
		case 1:
			orderDrink1();
			break;
		case 2:
			orderDrink2();
			break;
		case 3:
			orderDrink3();
			break;
		case 4:
			orderDrink4();
			break;
		case 5:
			orderDrink5();
			break;
		case 6:
			orderDrink6();
			break;
		case 7:
			orderDrink7();
			break;
		case 8:
			orderDrink8();
			break;
		default:
			break;
		}

	}

	private void orderDrink1() throws RemoteException, InterruptedException {
		ci.pinOn(4);
		Thread.sleep(2 * shotTime);
		ci.pinOff(4);
	}

	private void orderDrink2() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(2);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(2);
	}

	private void orderDrink3() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(3);
	}

	private void orderDrink4() throws RemoteException, InterruptedException {
		ci.pinOn(2);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(2);
		ci.pinOff(3);
	}

	private void orderDrink5() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		Thread.sleep(shotTime);
		ci.pinOff(1);
	}

	private void orderDrink6() throws RemoteException, InterruptedException {
		ci.pinOn(2);
		Thread.sleep(shotTime);
		ci.pinOff(2);
	}

	private void orderDrink7() throws RemoteException, InterruptedException {
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(3);
	}

	private void orderDrink8() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(2);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(2);
		ci.pinOff(3);
	}

	private void stopOrder() throws RemoteException {
		ci.pinOff(1);
		ci.pinOff(2);
		ci.pinOff(3);
		ci.pinOff(4);
	}

	private void updateStatus(String text) {
		statusBar.setText(text);
		this.repaint();
	}

}
