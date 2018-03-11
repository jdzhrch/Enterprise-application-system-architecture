import java.rmi.*;
import javax.naming.*;

public class Server
{
   public static void main(String[] args) throws RemoteException, NamingException
   {
      System.out.println("Constructing server implementation...");
      OrderShipperImpl orderShipper = new OrderShipperImpl();

      System.out.println("Binding server implementation to registry...");
      Context namingContext = new InitialContext();
      namingContext.bind("rmi:order_shipper", orderShipper);

      System.out.println("Waiting for invocations from clients...");
   }
}
