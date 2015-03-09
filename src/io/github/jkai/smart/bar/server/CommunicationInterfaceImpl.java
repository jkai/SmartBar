package io.github.jkai.smart.bar.server;

import java.rmi.RemoteException;

import io.github.jkai.smart.bar.server.GpioControl;


public class CommunicationInterfaceImpl implements CommunicationInterface{
	
	public CommunicationInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void pinOn(int pinNo) throws RemoteException {
		GpioControl gpioController = new GpioControl();
		gpioController.pinOn(pinNo);
	}

	@Override
	public void pinOff(int pinNo) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pinToggle(int pinNo) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPulse(int pinNo) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	

}
