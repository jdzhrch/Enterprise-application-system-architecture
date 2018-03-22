package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Book;
import model.Order;
import model.Orderitem;
import service.AppService;

public class OrderitemAction
  extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int id;
  private int orderid;
  private int bookid;
  private int amount;
  private int orderitemPrice;
  private AppService appService;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public int getOrderid()
  {
    return this.orderid;
  }
  
  public void setOrderid(int orderid)
  {
    this.orderid = orderid;
  }
  
  public int getBookid()
  {
    return this.bookid;
  }
  
  public void setBookid(int bookid)
  {
    this.bookid = bookid;
  }
  
  public int getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(int amount)
  {
    this.amount = amount;
  }
  
  public int getOrderitemPrice()
  {
    return this.orderitemPrice;
  }
  
  public void setOrderitemPrice(int orderitemPrice)
  {
    this.orderitemPrice = orderitemPrice;
  }
  
  public void setAppService(AppService appService)
  {
    this.appService = appService;
  }
  
  public String add()
    throws Exception
  {
    Order order = this.appService.getOrderById(this.orderid);
    Book book = this.appService.getBookById(this.bookid);
    if (this.amount <= book.getStock()) {
      if (order.getDate() == null)
      {
        Orderitem orderitem_exist = this.appService.getOrderitemByOrderidBookid(this.orderid, this.bookid);
        if (orderitem_exist != null)
        {
          int amount_sum = orderitem_exist.getAmount() + this.amount;
          if (amount_sum <= book.getStock())
          {
            orderitem_exist.setOrderitemPrice(orderitem_exist.getOrderitemPrice() / 
              orderitem_exist.getAmount() * amount_sum);
            orderitem_exist.setAmount(amount_sum);
            this.appService.updateOrderitem(orderitem_exist);
          }
        }
        else
        {
          this.orderitemPrice = (book.getPrice() * this.amount);
          Orderitem orderitem = new Orderitem(this.orderid, this.bookid, this.amount, this.orderitemPrice);
          this.appService.addOrderitem(orderitem);
        }
      }
    }
    return "success";
  }
  
  public String all()
    throws Exception
  {
    List<Orderitem> orderitems = this.appService.getAllOrderitems();
    request().setAttribute("orderitems", orderitems);
    
    List<Order> orders = this.appService.getAllOrders();
    request().setAttribute("orders", orders);
    
    List<Book> books = this.appService.getAllBooks();
    request().setAttribute("books", books);
    
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    Orderitem orderitem = this.appService.getOrderitemById(this.id);
    this.appService.deleteOrderitem(orderitem);
    
    return "success";
  }
  
  public String update()
    throws Exception
  {
    Book book = this.appService.getBookById(this.bookid);
    if (this.amount <= book.getStock())
    {
      Orderitem orderitem = this.appService.getOrderitemById(this.id);
      orderitem.setOrderid(this.orderid);
      orderitem.setBookid(this.bookid);
      orderitem.setAmount(this.amount);
      this.appService.updateOrderitem(orderitem);
    }
    return "success";
  }
}
