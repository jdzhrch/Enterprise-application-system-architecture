package dao.impl;

import dao.BookDao;
import java.util.List;
import model.Book;
import redis.RedisJava;
import redis.clients.jedis.Jedis;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BookDaoImpl extends HibernateDaoSupport implements BookDao {
	public Integer save(Book book) {
		return (Integer) getHibernateTemplate().save(book);
	}

	public void delete(Book book) {
		Jedis jedis = new Jedis("localhost");
		String key = "User:id:" + book.getId();
		jedis.del(key.getBytes());
		System.out.println("delete redis");
		getHibernateTemplate().delete(book);
	}

	public void update(Book book) {
		Jedis jedis = new Jedis("localhost");
		String key = "User:id:" + book.getId();
		jedis.set(key.getBytes(), RedisJava.serialize(book));
		System.out.println("update redis");
		getHibernateTemplate().merge(book);
	}

	public Book getBookById(int id) {
		Book book;
		Jedis jedis = new Jedis("localhost");
		String key = "User:id:" + id;
		byte[] byt = jedis.get(key.getBytes());
		if (byt == null) {
			List<Book> books = getHibernateTemplate().find("from Book as b where b.id=?", Integer.valueOf(id));
			book = books.size() > 0 ? (Book) books.get(0) : null;
			jedis.set(key.getBytes(), RedisJava.serialize(book));
			System.out.println("get from DB");
			return book;
		} else {
			Object obj = RedisJava.unserialize(byt);
			book = (Book) obj;
			System.out.println("get from redis" + book.getTitle());
			return book;
		}
	}

	public List<Book> getAllBooks() {
		List<Book> books = getHibernateTemplate().find("from Book");
		return books;
	}

	public List<Book> searchBookByBookname(String bookname) {
		List<Book> books = getHibernateTemplate().find("from Book as b where b.title like '%" + bookname + "%'");
		return books;
	}
}
