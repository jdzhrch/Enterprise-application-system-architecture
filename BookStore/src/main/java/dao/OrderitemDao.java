package dao;

import java.util.List;
import model.Orderitem;

public abstract interface OrderitemDao
{
  public abstract Integer save(Orderitem paramOrderitem);
  
  public abstract void delete(Orderitem paramOrderitem);
  
  public abstract void update(Orderitem paramOrderitem);
  
  public abstract Orderitem getOrderitemById(int paramInt);
  
  public abstract List<Orderitem> getOrderitemByOrderid(int paramInt);
  
  public abstract Orderitem getOrderitemByOrderidBookid(int paramInt1, int paramInt2);
  
  public abstract List<Orderitem> getAllOrderitems();
}
