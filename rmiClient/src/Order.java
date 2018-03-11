import java.io.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Order implements Serializable
{
	private int id;
	  private int userid;
	  private Date date;
	  private Date enddate;
	  private String address;
	  private String phone;
	  
	  public Order() {}
	  
	  public Order(int userid, Date date, Date enddate, String address, String phone)
	  {
	    this.userid = userid;
	    this.date = date;
	    this.enddate = enddate;
	    this.address = address;
	    this.phone = phone;
	  }
	  
	  public int getId()
	  {
	    return this.id;
	  }
	  
	  public void setId(int id)
	  {
	    this.id = id;
	  }
	  
	  public int getUserid()
	  {
	    return this.userid;
	  }
	  
	  public void setUserid(int userid)
	  {
	    this.userid = userid;
	  }
	  
	  public Date getDate()
	  {
	    return this.date;
	  }
	  
	  public void setDate(Date date)
	  {
	    this.date = date;
	  }
	  
	  public Date getEnddate()
	  {
	    return this.enddate;
	  }
	  
	  public void setEnddate(Date enddate)
	  {
	    this.enddate = enddate;
	  }
	  
	  public String getAddress()
	  {
	    return this.address;
	  }
	  
	  public void setAddress(String address)
	  {
	    this.address = address;
	  }
	  
	  public String getPhone()
	  {
	    return this.phone;
	  }
	  
	  public void setPhone(String phone)
	  {
	    this.phone = phone;
	  }
	  
	  private Set<Orderitem> orderitems = new HashSet();
	  
	  public Set<Orderitem> getOrderitems()
	  {
	    return this.orderitems;
	  }
	  
	  public void setOrderitems(Set<Orderitem> orderitems)
	  {
	    this.orderitems = orderitems;
	  }
}
