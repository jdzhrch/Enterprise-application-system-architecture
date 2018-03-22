package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import model.Book;
import model.Order;
import model.Orderitem;
import model.Statistic;
import model.User;

public abstract interface AppService
{
  public abstract Integer addBook(Book paramBook);
  
  public abstract void deleteBook(Book paramBook);
  
  public abstract void updateBook(Book paramBook);
  
  public abstract Book getBookById(int paramInt);
  
  public abstract List<Book> getAllBooks();
  
  public abstract List<Book> searchBookByBookname(String paramString);
  
  public abstract Integer addOrder(Order paramOrder);
  
  public abstract void deleteOrder(Order paramOrder);
  
  public abstract void updateOrder(Order paramOrder);
  
  public abstract Order getOrderById(int paramInt);
  
  public abstract List<Order> getOrderByUserid(int paramInt);
  
  public abstract List<Order> getAllOrders();
  
  public abstract Integer addOrderitem(Orderitem paramOrderitem);
  
  public abstract void deleteOrderitem(Orderitem paramOrderitem);
  
  public abstract void updateOrderitem(Orderitem paramOrderitem);
  
  public abstract Orderitem getOrderitemById(int paramInt);
  
  public abstract List<Orderitem> getOrderitemByOrderid(int paramInt);
  
  public abstract Orderitem getOrderitemByOrderidBookid(int paramInt1, int paramInt2);
  
  public abstract List<Orderitem> getAllOrderitems();
  
  public abstract List<Order> getCartByUserid(int paramInt);
  
  public abstract Integer addUser(User paramUser);
  
  public abstract void deleteUser(User paramUser);
  
  public abstract void updateUser(User paramUser);
  
  public abstract User getUserById(int paramInt);
  
  public abstract User getUserByUsername(String paramString);
  
  public abstract User getUserByUsernamePassword(String paramString1, String paramString2);
  
  public abstract List<User> getAllUsers();
  
  public abstract boolean checkUsername(String paramString);
  
  public abstract List<Statistic> allByBook();
  
  public abstract List<Statistic> allByStartDate();
  
  public abstract List<Statistic> allByEndDate();
  
  public abstract List<Statistic> allByUser();
  
  public abstract List<Statistic> allByCategory();
	
	public void savePicture(String fileName,File imageFile)throws IOException;
	
	public void deletePicture(String fileName);
	
	public GridFSDBFile getPicture(String fileName);
}