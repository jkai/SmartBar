package io.github.jkai.smart.bar;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server extends Thread {

	private static Server instance = null;
	private Service service = null;

	private Server() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException {
		Server server = Server.getInstance();
		server.createServer();
	}

	public static Server getInstance() {
		if (instance == null) {
			try {
				instance = new Server();
				Runtime.getRuntime().addShutdownHook(instance);
			} catch (RemoteException ex) {
				System.err.println("Server:getInstance() - " + ex.getMessage());
			}
		}
		return instance;
	}

	public void createServer() {

		if (System.getSecurityManager() == null) {
			System.out.println("Creating new security manager");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			service = new Service(CommunicationInterface.ServicePort);
			Registry r;
			try {
				r = LocateRegistry
						.createRegistry(CommunicationInterface.RMIRegistryPort);
			} catch (java.rmi.server.ExportException ex) {
				r = LocateRegistry
						.getRegistry(CommunicationInterface.RMIRegistryPort);
			}

			System.out.println("Service attempts rebind of registry");

			r.rebind(CommunicationInterface.SMART_BAR_SERVEICE, service);
			System.out.println("Server bound in registry");
			System.out.println("Press Ctrl-C to exit.");
		} catch (java.rmi.server.ExportException ee) {
			System.err
					.println("Server  cannot use RMI port "
							+ CommunicationInterface.RMIRegistryPort
							+ " as it is already in use, check that you are not running another instance of the server.");
			ee.printStackTrace(System.err);
			System.exit(-1);
		} catch (Exception e) {
			System.err.println("Server err: " + e.getMessage());
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		System.out.println("Server Shutting Down.");
	}

}
