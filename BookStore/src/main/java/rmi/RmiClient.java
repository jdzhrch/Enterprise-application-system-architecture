package rmi;
import java.rmi.*;
import java.util.*;
import javax.naming.*;

public class RmiClient
{
   public String runShipOrder(RmiOrder order) throws NamingException, RemoteException
   {
      Context namingContext = new InitialContext();
      
      System.out.print("RMI registry bindings: ");
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      String url = "rmi://localhost:1099/order_shipper";      
      OrderShipper orderShipper = (OrderShipper) namingContext.lookup(url);
      
      String str = orderShipper.shipOrder(order);
      
      System.out.println(str);
      return str;
   }
}
