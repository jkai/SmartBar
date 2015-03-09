package io.github.jkai.smart.bar.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommunicationInterface extends Remote {
	
	public void pinOn(int pinNo) throws RemoteException;
	
	public void pinOff(int pinNo) throws RemoteException;
	
	public void pinToggle(int pinNo) throws RemoteException;
	
	public void sendPulse(int pinNo) throws RemoteException;
	
}
