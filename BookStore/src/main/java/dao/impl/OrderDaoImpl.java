package dao.impl;

import dao.OrderDao;
import java.util.List;
import model.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer save(Order order) {
		return (Integer) getHibernateTemplate().save(order);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		List<Order> orders = getHibernateTemplate().find("from Order as o where o.id=?", Integer.valueOf(id));
		Order order = orders.size() > 0 ? (Order) orders.get(0) : null;
		return order;
	}

	public List<Order> getOrderByUserid(int userid) {
		List<Order> orders = getHibernateTemplate().find("from Order as o where o.userid=?", Integer.valueOf(userid));
		return orders;
	}

	public List<Order> getAllOrders() {
		List<Order> orders = getHibernateTemplate().find("from Order");
		return orders;
	}

	public List<Order> getCartByUserid(int userid) {
		List<Order> orders = getHibernateTemplate().find("from Order as o where o.date=null and o.userid=?",
				Integer.valueOf(userid));
		return orders;
	}
}
