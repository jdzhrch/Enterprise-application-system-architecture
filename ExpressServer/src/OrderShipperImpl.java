import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class OrderShipperImpl extends UnicastRemoteObject implements OrderShipper
{
   public OrderShipperImpl() throws RemoteException
   {
   }

   public String shipOrder(Order order)
   {     
      return "Processing";
   }
    
}
