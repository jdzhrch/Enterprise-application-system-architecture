package rmi;

import java.rmi.*;
import java.util.*;


public interface OrderShipper extends Remote
{  
   String shipOrder(RmiOrder order) throws RemoteException;
}
 