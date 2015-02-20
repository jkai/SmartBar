package io.github.jkai.smart.bar.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommunicationInterface extends Remote {
	
	public void test() throws RemoteException;

}
