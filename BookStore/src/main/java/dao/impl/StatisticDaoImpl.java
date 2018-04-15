package dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.StatisticDao;
import model.Book;
import model.Statistic;

@Transactional(propagation=Propagation.NOT_SUPPORTED)//因为管理员只有一个，且在这里数据只是显示，本身就随时间变化很大。
public class StatisticDaoImpl 
	extends HibernateDaoSupport
	implements StatisticDao
{

	public List<Statistic> allByBook() {
		String hql = "select oi.bookid as criteria,sum(oi.amount) as number,TRUNCATE(sum(oi.orderitemPrice)/100,2) as amount "
				+ "from (select id as orderid from orders where enddate!=\"null\") o "
				+ "natural join (select orderid, bookid, amount,orderitemPrice from orderitems) oi group by oi.bookid";
		SQLQuery query = getSession().createSQLQuery(hql); 
		query.addScalar("criteria",StandardBasicTypes.STRING);
		query.addScalar("number",StandardBasicTypes.INTEGER);
		query.addScalar("amount",StandardBasicTypes.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(Statistic.class));  
		return query.list();
	}

	public List<Statistic> allByStartDate() {
		String hql = "select o.date as criteria,sum(oi.amount) as number,TRUNCATE(sum(oi.orderitemPrice)/100,2) as amount "
				+ "from (select id as orderid, date from orders where enddate!=\"null\") o "
				+ "natural join (select orderid, bookid, amount,orderitemPrice from orderitems) oi group by o.date";
		SQLQuery query = getSession().createSQLQuery(hql); 
		query.addScalar("criteria",StandardBasicTypes.STRING);
		query.addScalar("number",StandardBasicTypes.INTEGER);
		query.addScalar("amount",StandardBasicTypes.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(Statistic.class));  
		return query.list();
	}

	public List<Statistic> allByEndDate() {
		String hql = "select o.enddate as criteria,sum(oi.amount) as number,TRUNCATE(sum(oi.orderitemPrice)/100,2) as amount "
				+ "from (select id as orderid, enddate from orders where enddate!=\"null\") o "
				+ "natural join (select orderid, bookid, amount,orderitemPrice from orderitems) oi group by o.enddate";
		SQLQuery query = getSession().createSQLQuery(hql); 
		query.addScalar("criteria",StandardBasicTypes.STRING);
		query.addScalar("number",StandardBasicTypes.INTEGER);
		query.addScalar("amount",StandardBasicTypes.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(Statistic.class));  
		return query.list();
	}

	public List<Statistic> allByUser() {
		String hql = "select o.userid as criteria,sum(oi.amount) as number,TRUNCATE(sum(oi.orderitemPrice)/100,2) as amount "
				+ "from (select id as orderid, userid from orders where enddate!=\"null\") o "
				+ "natural join (select orderid, bookid, amount,orderitemPrice from orderitems) oi group by o.userid";
		SQLQuery query = getSession().createSQLQuery(hql); 
		query.addScalar("criteria",StandardBasicTypes.STRING);
		query.addScalar("number",StandardBasicTypes.INTEGER);
		query.addScalar("amount",StandardBasicTypes.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(Statistic.class));  
		return query.list();
	}

	public List<Statistic> allByCategory() {
		String hql = "select b.category as criteria,sum(oi.amount) as number,TRUNCATE(sum(oi.orderitemPrice)/100,2) as amount "
				+ "from (select id as orderid from orders where enddate!=\"null\") o "
				+ "natural join (select orderid, bookid, amount,orderitemPrice from orderitems) oi natural join books b group by b.category";
		SQLQuery query = getSession().createSQLQuery(hql); 
		query.addScalar("criteria",StandardBasicTypes.STRING);
		query.addScalar("number",StandardBasicTypes.INTEGER);
		query.addScalar("amount",StandardBasicTypes.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(Statistic.class));  
		return query.list();
	}

}