package action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Order;
import model.Orderitem;
import model.User;
import service.AppService;

public class UserAction
  extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int id;
  private String username;
  private String password;
  private String role;
  private AppService appService;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getRole()
  {
    return this.role;
  }
  
  public void setRole(String role)
  {
    this.role = role;
  }
  
  public void setAppService(AppService appService)
  {
    this.appService = appService;
  }
  
  public String add()
    throws Exception
  {
    User user = new User(this.username, this.password, this.role);
    this.appService.addUser(user);
    return "success";
  }
  
  public String all()
    throws Exception
  {
    List<User> users = this.appService.getAllUsers();
    request().setAttribute("users", users);
    
    List<ArrayList<String>> orderStrs = new ArrayList();
    for (int i = 0; i < users.size(); i++)
    {
      User user = (User)users.get(i);
      List<Order> orders = this.appService.getOrderByUserid(user.getId());
      ArrayList<String> orderStr = new ArrayList();
      
      Iterator iterator = orders.iterator();
      while (iterator.hasNext())
      {
        Order order = (Order)iterator.next();
        orderStr.add(""+order.getId());
      }
      orderStrs.add(orderStr);
    }
    request().setAttribute("orderStrs", orderStrs);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    User user = this.appService.getUserById(this.id);
    this.appService.deleteUser(user);
    
    return "success";
  }
  
  public String update()
    throws Exception
  {
    User user = this.appService.getUserById(this.id);
    user.setUsername(this.username);
    user.setPassword(this.password);
    user.setRole(this.role);
    this.appService.updateUser(user);
    
    return "success";
  }
  
  public String register()
    throws Exception
  {
    User user_exist = this.appService.getUserByUsername(this.username);
    if (user_exist != null)
    {
      request().setAttribute("errorMsg", "Register Error:this user already exists");
      return "register error";
    }
    User user = new User(this.username, this.password, this.role);
    this.appService.addUser(user);
    
    List<Book> books = this.appService.getAllBooks();
    request().setAttribute("books", books);
    return "register success";
  }
  
  public String login()
    throws Exception
  {
    User user_exist = this.appService.getUserByUsername(this.username);
    if (user_exist == null)
    {
      request().setAttribute("errorMsg", "Login Error:this user does not exist");
      return "login error";
    }
    User pwd_right = this.appService.getUserByUsernamePassword(this.username, this.password);
    if (pwd_right == null)
    {
      request().setAttribute("errorMsg", "Login Error:wrong password");
      return "login error";
    }
    List<Book> books = this.appService.getAllBooks();
    request().setAttribute("books", books);
    session().setAttribute("account", user_exist);
    
    List<Order> orders = this.appService.getOrderByUserid(user_exist.getId());
    int i;
    for (i = 0; i < orders.size(); i++) {
      if (((Order)orders.get(i)).getDate() == null)
      {
        session().setAttribute("cart", orders.get(i));
        break;
      }
    }
    if (i == orders.size())
    {
      Date date = null;
      Order cart = new Order(user_exist.getId(), date, null, null, null);
      this.appService.addOrder(cart);
      cart = (Order)this.appService.getCartByUserid(user_exist.getId()).get(0);
      session().setAttribute("cart", cart);
    }
    return "login success";
  }
  
  public String logout()
    throws Exception
  {
    List<Book> books = this.appService.getAllBooks();
    request().setAttribute("books", books);
    session().removeAttribute("account");
    session().removeAttribute("cart");
    return "logout success";
  }
  
  public String cart()
    throws Exception
  {
    List<Order> orders = this.appService.getOrderByUserid(this.id);
    request().setAttribute("orders", orders);
    
    List<List<Orderitem>> orderitemses = new ArrayList();
    List<List<Book>> bookses = new ArrayList();
    for (int i = 0; i < orders.size(); i++)
    {
      if (((Order)orders.get(i)).getDate() == null) {
        request().setAttribute("cart_index", Integer.valueOf(i));
      }
      List<Orderitem> orderitems = this.appService.getOrderitemByOrderid(((Order)orders.get(i)).getId());
      List<Book> books = new ArrayList();
      for (int j = 0; j < orderitems.size(); j++)
      {
        Book book = this.appService.getBookById(((Orderitem)orderitems.get(j)).getBookid());
        books.add(book);
      }
      orderitemses.add(orderitems);
      bookses.add(books);
    }
    request().setAttribute("orderitemses", orderitemses);
    request().setAttribute("bookses", bookses);
    return "cart";
  }
  
  public void checkUsername()
    throws Exception
  {
    if (this.appService.checkUsername(this.username)) {
      response().getWriter().write("this user already exists");
    }
  }
}
