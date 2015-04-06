package io.github.jkai.smart.bar;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommunicationInterface extends Remote {
	
	public static final String SMART_BAR_SERVEICE = "SmartBarServer";
	public static final int RMIRegistryPort = 2024;
	public static final int ServicePort = 2025;
	
	public void pinOn(int pinNo) throws RemoteException;
	
	public void pinOff(int pinNo) throws RemoteException;
	
	public void shutOff() throws RemoteException;
	
	public double getFlowTime(double volume) throws RemoteException;
	
	public void calibration() throws RemoteException;
	
	public boolean cupIsPresent() throws RemoteException;
	
}
