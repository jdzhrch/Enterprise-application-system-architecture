package dao.impl;

import dao.BookDao;
import java.util.List;
import model.Book;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRES_NEW)
public class BookDaoImpl
  extends HibernateDaoSupport
  implements BookDao
{
  public Integer save(Book book)
  {
    return (Integer)getHibernateTemplate().save(book);
  }
  
  public void delete(Book book)
  {
    getHibernateTemplate().delete(book);
  }
  
  public void update(Book book)
  {
    getHibernateTemplate().merge(book);
  }
  
  public Book getBookById(int id)
  {
    List<Book> books = getHibernateTemplate().find(
      "from Book as b where b.id=?", Integer.valueOf(id));
    Book book = books.size() > 0 ? (Book)books.get(0) : null;
    return book;
  }
  
  public List<Book> getAllBooks()
  {
    List<Book> books = getHibernateTemplate()
      .find("from Book");
    return books;
  }
  
  public List<Book> searchBookByBookname(String bookname)
  {
    List<Book> books = getHibernateTemplate()
      .find("from Book as b where b.title like '%" + bookname + "%'");
    return books;
  }
}
