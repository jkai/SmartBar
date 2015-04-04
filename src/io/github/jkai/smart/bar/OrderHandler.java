package io.github.jkai.smart.bar;

import java.rmi.RemoteException;

public class OrderHandler extends Thread {

	private static CommunicationInterface ci = null;
	private static final long shotTime = 30000;
	private static final double volume_in_L = 1;
	private static int orderNumber = 0;

	public OrderHandler(CommunicationInterface ciPar) {
		OrderHandler.ci = ciPar;
	}

	public OrderHandler() {
	}

	@Override
	public void run() {
		try {
			proceedOrder(orderNumber);
		} catch (InterruptedException | RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setOrderNumber(int parOrder) {
		orderNumber = parOrder;
		System.out.println(orderNumber);
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
		case 9:
			stopOrder();
		default:
			break;
		}

	}

	private void orderDrink1() throws RemoteException, InterruptedException {
		orderSprite();
	}

	private void orderSprite() throws RemoteException, InterruptedException {
		// ci.pinOn(4);
		// waitFor((long) ci.getFlowTime(volume_in_L) - 500);
		// ci.pinOff(4);

		ci.pinOn(4);
		Thread.sleep(500);
		ci.pinOff(4);

	}

	private void orderDrink2() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		orderSprite();
	}

	private void orderDrink3() throws RemoteException, InterruptedException {
		ci.pinOn(2);
		Thread.sleep(shotTime);
		ci.pinOff(2);
		orderSprite();
	}

	private void orderDrink4() throws RemoteException, InterruptedException {
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(3);
		orderSprite();
	}

	private void orderDrink5() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(2);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(2);
		orderSprite();
	}

	private void orderDrink6() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(3);
		orderSprite();
	}

	private void orderDrink7() throws RemoteException, InterruptedException {
		ci.pinOn(2);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(3);
		ci.pinOff(3);
		orderSprite();
	}

	private void orderDrink8() throws RemoteException, InterruptedException {
		ci.pinOn(1);
		ci.pinOn(2);
		ci.pinOn(3);
		Thread.sleep(shotTime);
		ci.pinOff(1);
		ci.pinOff(2);
		ci.pinOff(3);
		orderSprite();
	}

	private void stopOrder() throws RemoteException {
		ci.pinOff(1);
		ci.pinOff(2);
		ci.pinOff(3);
		ci.pinOff(4);
	}

}
