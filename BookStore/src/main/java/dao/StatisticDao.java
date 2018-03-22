package dao;

import java.util.List;

import model.Statistic;;

public interface StatisticDao {

	  public abstract List<Statistic> allByBook();
	  
	  public abstract List<Statistic> allByStartDate();
	  
	  public abstract List<Statistic> allByEndDate();
	  
	  public abstract List<Statistic> allByUser();
	  
	  public abstract List<Statistic> allByCategory();
}