import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

public class WarehouseClient {
	public static void main(String[] args) throws NamingException, RemoteException {
		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
		while (e.hasMoreElements())
			System.out.println(e.nextElement().getName());
		String url = "rmi://localhost/central_warehouse";
		Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
		String descr = "ZapXpress Microwave Oven";// "Blackwell Toaster";
		double price = centralWarehouse.getPrice(descr);
		System.out.println(descr + ": " + price);
	}
} 