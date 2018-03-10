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
		namingContext.bind("rmi:central_warehouse", centralWarehouse);//Ҫ��classdir����classpath������rmi������Ҫbind�Ķ����·��������bind��ʱ��ͻ��Ҳ��� ����
		System.out.println("Waiting for invocations from clients...");
	}
}