import java.rmi.*;
import java.util.*;

public interface OrderShipper extends Remote
{  
   String shipOrder(Order order) throws RemoteException;
}
