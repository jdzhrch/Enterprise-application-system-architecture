

public class Orderitem
{
  private int id;
  private int orderid;
  private int bookid;
  private int amount;
  private int orderitemPrice;
  private Order order;
  
  public Orderitem() {}
  
  public Orderitem(int orderid, int bookid, int amount, int orderitemPrice)
  {
    this.orderid = orderid;
    this.bookid = bookid;
    this.amount = amount;
    this.orderitemPrice = orderitemPrice;
  }
  
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
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public void setOrder(Order order)
  {
    this.order = order;
  }
}
