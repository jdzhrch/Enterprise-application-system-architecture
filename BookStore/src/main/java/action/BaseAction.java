package action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class BaseAction
  extends ActionSupport
{
  private static final long serialVersionUID = 1L;
  
  public HttpServletRequest request()
  {
    return ServletActionContext.getRequest();
  }
  
  public HttpSession session()
  {
    return ServletActionContext.getRequest().getSession();
  }
  
  public ServletContext application()
  {
    return ServletActionContext.getServletContext();
  }
  
  public HttpServletResponse response()
  {
    return ServletActionContext.getResponse();
  }
  
  public String execute()
    throws Exception
  {
    return null;
  }
}
