package dao;

import java.util.List;
import model.Book;

public abstract interface BookDao
{
  public abstract Integer save(Book paramBook);
  
  public abstract void delete(Book paramBook);
  
  public abstract void update(Book paramBook);
  
  public abstract Book getBookById(int paramInt);
  
  public abstract List<Book> getAllBooks();
  
  public abstract List<Book> searchBookByBookname(String paramString);
}
