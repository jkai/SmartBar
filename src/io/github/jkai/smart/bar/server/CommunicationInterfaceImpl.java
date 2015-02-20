package io.github.jkai.smart.bar.server;

import java.rmi.RemoteException;

import io.github.jkai.smart.bar.server.GpioControl;


public class CommunicationInterfaceImpl implements CommunicationInterface{
	
	public CommunicationInterfaceImpl() throws RemoteException {
		
	}

	@Override
	public void test() throws RemoteException {
		
		GpioControl gpioControl = new GpioControl ();
		gpioControl.connected();
		
	}
	
	

}
