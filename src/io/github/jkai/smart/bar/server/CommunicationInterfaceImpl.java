package io.github.jkai.smart.bar.server;

import java.rmi.RemoteException;

import io.github.jkai.smart.bar.server.GpioControl;

public class CommunicationInterfaceImpl implements CommunicationInterface {

	public CommunicationInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void pinOn(int pinNo) throws RemoteException {
		GpioControl gpioController = new GpioControl();
		switch (pinNo) {
		case 1:
			pinNo = 1;
			gpioController.pump1On();
			break;
		case 2:
			pinNo = 2;
			gpioController.pump2On();
			break;
		case 3:
			pinNo = 3;
			gpioController.pump3On();
			break;
		case 4:
			pinNo = 4;
			gpioController.valveOn();
			break;
		default:
			gpioController.shutOff();
			break;
		}

	}

	@Override
	public void pinOff(int pinNo) throws RemoteException {
		GpioControl gpioController = new GpioControl();
		switch (pinNo) {
		case 1:
			pinNo = 1;
			gpioController.pump1Off();
			break;
		case 2:
			pinNo = 2;
			gpioController.pump2Off();
			break;
		case 3:
			pinNo = 3;
			gpioController.pump3Off();
			break;
		case 4:
			pinNo = 4;
			gpioController.valveOff();
			break;
		default:
			gpioController.shutOff();
			break;
		}

	}

	@Override
	public void pinToggle(int pinNo) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendPulse(int pinNo) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutOff() throws RemoteException {
		GpioControl gpioController = new GpioControl();
		gpioController.shutOff();
	}

	@Override
	public double getFlowrate() throws RemoteException {
		GpioControl gpioController = new GpioControl();
		return gpioController.flowRate();
	}

}
