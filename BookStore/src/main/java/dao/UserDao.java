package dao;

import java.util.List;
import model.User;

public abstract interface UserDao
{
  public abstract Integer save(User paramUser);
  
  public abstract void delete(User paramUser);
  
  public abstract void update(User paramUser);
  
  public abstract User getUserById(int paramInt);
  
  public abstract User getUserByUsername(String paramString);
  
  public abstract User getUserByUsernamePassword(String paramString1, String paramString2);
  
  public abstract List<User> getAllUsers();
  
  public abstract boolean checkUsername(String paramString);
}
