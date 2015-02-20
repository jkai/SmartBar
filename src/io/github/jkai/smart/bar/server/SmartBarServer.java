package io.github.jkai.smart.bar.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class SmartBarServer {

	public static void main(String[] args) throws RemoteException {
		
		CommunicationInterfaceImpl communicationInterfaceImpl = new CommunicationInterfaceImpl ();
		CommunicationInterface communicationI = (CommunicationInterface)UnicastRemoteObject.exportObject(communicationInterfaceImpl, 0);
		Registry registry = LocateRegistry.createRegistry(2001);
		registry.rebind("communicationInterfaceImpl", communicationI);
		System.out.println("Waiting for object setup...\n");
		communicationInterfaceImpl.test();
		
	}

}
