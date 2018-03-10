import java.rmi.*;
import warehouse.server.*;

import javax.naming.*;

public class WarehouseClient {
	public static void main(String[] args) throws NamingException, RemoteException {
		WarehouseService service = new WarehouseService();
		Warehouse port = service.getPort(Warehouse.class);
		String descr = "ZapXpress Microwave Oven";
		double price = port.getPrice(descr);
		System.out.println(descr + ": " + price);
	}
}