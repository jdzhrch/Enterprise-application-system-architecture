package dao;

import java.util.List;
import model.Order;

public abstract interface OrderDao
{
  public abstract Integer save(Order paramOrder);
  
  public abstract void delete(Order paramOrder);
  
  public abstract void update(Order paramOrder);
  
  public abstract Order getOrderById(int paramInt);
  
  public abstract List<Order> getOrderByUserid(int paramInt);
  
  public abstract List<Order> getAllOrders();
  
  public abstract List<Order> getCartByUserid(int paramInt);
}
