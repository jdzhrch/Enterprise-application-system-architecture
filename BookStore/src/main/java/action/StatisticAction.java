package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Statistic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;

public class StatisticAction 
  extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private AppService appService;
  
  public AppService getAppService() {
	  return appService;
  }
	
  public void setAppService(AppService appService) {
	  this.appService = appService;
  }
  public void allByBook() throws IOException{
	  List<Statistic> statistics = this.appService.allByBook();
	  //request().setAttribute("statistics", statistics);
	  //return "success";
	    JSONArray responseJSONArray = JSONArray.fromObject(statistics);
	    response().getWriter().append(responseJSONArray.toString());
  }
  
  public void allByUser() throws IOException{
	  List<Statistic> statistics = this.appService.allByUser();
	//request().setAttribute("statistics", statistics);
	  //return "success";
	    JSONArray responseJSONArray = JSONArray.fromObject(statistics);
	    response().getWriter().append(responseJSONArray.toString());
  }
  
  public void allByStartDate() throws IOException{
	  List<Statistic> statistics = this.appService.allByStartDate();
	//request().setAttribute("statistics", statistics);
	  //return "success";
	    JSONArray responseJSONArray = JSONArray.fromObject(statistics);
	    response().getWriter().append(responseJSONArray.toString());
  }
  
  public void allByEndDate() throws IOException{
	  List<Statistic> statistics = this.appService.allByEndDate();
	//request().setAttribute("statistics", statistics);
	  //return "success";
	    JSONArray responseJSONArray = JSONArray.fromObject(statistics);
	    response().getWriter().append(responseJSONArray.toString());
  }
  
  public void allByCategory() throws IOException{
	  List<Statistic> statistics = this.appService.allByCategory();
	//request().setAttribute("statistics", statistics);
	  //return "success";
	    JSONArray responseJSONArray = JSONArray.fromObject(statistics);
	    response().getWriter().append(responseJSONArray.toString());
  }

  public String allByBookTotal() throws IOException{
	  List<Statistic> statistics = this.appService.allByBook();
	  request().setAttribute("statistics", statistics);
	  return "success";
  }
}