package io.github.jkai.smart.bar.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SmartBarServer {

	public static void main(String[] args) throws RemoteException {
		// Initialize an implemented CommunicationInterface instance
		CommunicationInterfaceImpl communicationInterfaceImpl = new CommunicationInterfaceImpl();
		// Initialize the skeleton of RMI server
		CommunicationInterface communicationI = (CommunicationInterface) UnicastRemoteObject
				.exportObject(communicationInterfaceImpl, 0);
		// Initialize the registry
		Registry registry = LocateRegistry.createRegistry(2001);
		// Rebind registry to communicationInterfaceImpl
		registry.rebind("communicationInterfaceImpl", communicationI);
		System.out.println("Waiting for client...\n");
	}
}
