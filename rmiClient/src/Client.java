import java.rmi.*;
import java.util.*;
import javax.naming.*;

public class Client
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      Context namingContext = new InitialContext();
      
      System.out.print("RMI registry bindings: ");
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      String url = "rmi://localhost:1099/order_shipper";      
      OrderShipper orderShipper = (OrderShipper) namingContext.lookup(url);
      
      System.out.print("Enter keywords: ");
      Order order = new Order();
      String str = orderShipper.shipOrder(order);
      
      System.out.println(str + ": ");
   }
}
