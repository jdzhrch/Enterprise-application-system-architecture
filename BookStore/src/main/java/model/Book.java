package model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable
{
  private int id;
  private String title;
  private String author;
  private int price;
  private String publisher;
  private Date date;
  private String image;
  private int stock;
  private String category;
  
  public Book() {}
  
  public Book(String title, String author, int price, String publisher, Date date, String image, int stock, String category)
  {
    this.title = title;
    this.author = author;
    this.price = price;
    this.publisher = publisher;
    this.date = date;
    this.image = image;
    this.stock = stock;
    this.category = category;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getAuthor()
  {
    return this.author;
  }
  
  public void setAuthor(String author)
  {
    this.author = author;
  }
  
  public int getPrice()
  {
    return this.price;
  }
  
  public void setPrice(int price)
  {
    this.price = price;
  }
  
  public String getPublisher()
  {
    return this.publisher;
  }
  
  public void setPublisher(String publisher)
  {
    this.publisher = publisher;
  }
  
  public Date getDate()
  {
    return this.date;
  }
  
  public void setDate(Date date)
  {
    this.date = date;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public void setImage(String image)
  {
    this.image = image;
  }
  
  public int getStock()
  {
    return this.stock;
  }
  
  public void setStock(int stock)
  {
    this.stock = stock;
  }
  
  public String getCategory()
  {
    return this.category;
  }
  
  public void setCategory(String category)
  {
    this.category = category;
  }
}
