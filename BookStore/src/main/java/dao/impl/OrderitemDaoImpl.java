package dao.impl;

import dao.OrderitemDao;
import java.util.List;
import model.Orderitem;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderitemDaoImpl extends HibernateDaoSupport implements OrderitemDao {
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer save(Orderitem orderitem) {
		return (Integer) getHibernateTemplate().save(orderitem);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Orderitem orderitem) {
		getHibernateTemplate().delete(orderitem);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Orderitem orderitem) {
		getHibernateTemplate().merge(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		List<Orderitem> orderitems = getHibernateTemplate().find("from Orderitem as oi where oi.id=?",
				Integer.valueOf(id));
		Orderitem orderitem = orderitems.size() > 0 ? (Orderitem) orderitems.get(0) : null;
		return orderitem;
	}

	public List<Orderitem> getOrderitemByOrderid(int orderid) {
		List<Orderitem> orderitems = getHibernateTemplate().find("from Orderitem as oi where oi.orderid=?",
				Integer.valueOf(orderid));
		return orderitems;
	}

	public Orderitem getOrderitemByOrderidBookid(int orderid, int bookid) {
		List<Orderitem> orderitems = getHibernateTemplate().find(
				"from Orderitem as oi where oi.orderid=? and oi.bookid=?",
				new Object[] { Integer.valueOf(orderid), Integer.valueOf(bookid) });
		Orderitem orderitem = orderitems.size() > 0 ? (Orderitem) orderitems.get(0) : null;
		return orderitem;
	}

	public List<Orderitem> getAllOrderitems() {
		List<Orderitem> orderitems = getHibernateTemplate().find("from Orderitem");
		return orderitems;
	}
}
