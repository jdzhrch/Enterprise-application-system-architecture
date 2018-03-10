import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map; 

public class WarehouseImpl extends Activatable implements Warehouse {
	private Map<String, Double> prices;

	public WarehouseImpl(ActivationID id, MarshalledObject<Map<String, Double>> param)
			throws RemoteException, ClassNotFoundException, IOException {
		super(id, 0);
		prices = param.get();
		System.out.println("Warehouse implementation constructed.");
	}

	public double getPrice(String description) throws RemoteException {
		Double price = prices.get(description);
		return price == null ? 0 : price;
	} 
}