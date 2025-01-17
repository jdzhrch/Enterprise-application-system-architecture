package dao.impl;

import dao.UserDao;
import java.util.List;
import model.User;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UserDaoImpl
  extends HibernateDaoSupport
  implements UserDao
{
	@Transactional(propagation=Propagation.REQUIRED)
  public Integer save(User user)
  {
    return (Integer)getHibernateTemplate().save(user);
  }

	@Transactional(propagation=Propagation.REQUIRED)
  public void delete(User user)
  {
    getHibernateTemplate().delete(user);
  }

	@Transactional(propagation=Propagation.REQUIRED)
  public void update(User user)
  {
    getHibernateTemplate().merge(user);
  }
  
  public User getUserById(int id)
  {
    List<User> users = getHibernateTemplate().find(
      "from User as u where u.id=?", Integer.valueOf(id));
    User user = users.size() > 0 ? (User)users.get(0) : null;
    return user;
  }
  
  public User getUserByUsername(String username)
  {
    List<User> users = getHibernateTemplate().find(
      "from User as u where u.username=?", username);
    User user = users.size() > 0 ? (User)users.get(0) : null;
    return user;
  }
  
  public User getUserByUsernamePassword(String username, String password)
  {
    List<User> users = getHibernateTemplate().find(
      "from User as u where u.username=? and u.password=?", new Object[] { username, password });
    User user = users.size() > 0 ? (User)users.get(0) : null;
    return user;
  }
  
  public List<User> getAllUsers()
  {
    List<User> users = getHibernateTemplate()
      .find("from User");
    return users;
  }
  
  public boolean checkUsername(String username)
  {
    List<User> users = getHibernateTemplate().find(
      "from User as u where u.username=?", username);
    return users.size() > 0;
  }
}
