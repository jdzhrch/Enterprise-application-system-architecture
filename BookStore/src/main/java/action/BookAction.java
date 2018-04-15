package action;

import java.io.File;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.gridfs.GridFSDBFile;

import model.Book;
import net.sf.json.JSONObject;
import service.AppService;

public class BookAction
  extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int id;
  private String title;
  private String author;
  private int price;
  private String publisher;
  private Date date;
  private String image;
  private int stock;
  private String category;
  private File imageFile;
  private AppService appService;
  
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
  
  public File getImageFile()
  {
    return this.imageFile;
  }
  
  public void setImageFile(File imageFile)
  {
    this.imageFile = imageFile;
  }
  
  public void setAppService(AppService appService)
  {
    this.appService = appService;
  }
  
  public String add()
    throws Exception
  {
    Book book = new Book(this.title, this.author, this.price, this.publisher, this.date, this.image, this.stock, this.category);
    this.appService.addBook(book);
    //this.appService.savePicture(title, imageFile);
    
    return "back";
  }
  
  public String all()
    throws Exception
  {
    List<Book> books = this.appService.getAllBooks();
    request().setAttribute("books", books);
    
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    Book book = this.appService.getBookById(this.id);
    this.appService.deleteBook(book);
    this.appService.deletePicture(book.getTitle());
    
    return "success";
  }
  
  public String update()
    throws Exception
  {
    Book book = this.appService.getBookById(this.id);
    book.setTitle(this.title);
    book.setAuthor(this.author);
    book.setPrice(this.price);
    book.setPublisher(this.publisher);
    book.setDate(this.date);
    book.setImage(this.image);
    book.setStock(this.stock);
    book.setCategory(this.category);
    this.appService.updateBook(book);
    //this.appService.savePicture(title, imageFile);
    return "back";
  }
  
  public String deal()
    throws Exception
  {
    Book book = this.appService.getBookById(this.id);
    book.setStock(this.stock);
    this.appService.updateBook(book);
    
    return "deal";
  }
  
  public String search()
    throws Exception
  {
    List<Book> books = this.appService.searchBookByBookname(this.title);
    request().setAttribute("books", books);
    request().setAttribute("issearch", Integer.valueOf(1));
    return "search success";
  }
  
  public void showDetails()
    throws Exception
  {
    Book book = this.appService.getBookById(this.id);
    response().setCharacterEncoding("UTF-8");
    response().setContentType("application/json; charset=utf-8");
    JSONObject responseJSONObject = JSONObject.fromObject(book);
    response().getWriter().append(responseJSONObject.toString());
  }
  
  public void getPicture()throws IOException{
		GridFSDBFile gridFSDBFile=appService.getPicture(title);
		OutputStream sos =response().getOutputStream();
		response().setContentType("image/png");
		gridFSDBFile.writeTo(sos); 
		sos.flush();
		sos.close();
  }
  
  public String savePicture()throws IOException{
	  this.appService.savePicture(title, imageFile);
	  return "back";
  }
}
