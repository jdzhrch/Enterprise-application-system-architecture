import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WarehouseServer {
	public static void main(String[] args) throws RemoteException, NamingException {
		System.out.println("Constructing server implementation...");
		WarehouseImpl centralWarehouse = new WarehouseImpl();
		System.out.println("Binding server implementation to registry...");
		Context namingContext = new InitialContext();
		namingContext.bind("rmi:central_warehouse", centralWarehouse);//要将classdir加入classpath，这是rmi用于找要bind的对象的路径，否则bind的时候就会找不到 出错
		System.out.println("Waiting for invocations from clients...");
	}
}