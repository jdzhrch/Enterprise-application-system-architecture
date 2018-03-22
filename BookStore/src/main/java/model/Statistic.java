package model;


public class Statistic {
	private String criteria;
	private int number;
	private double amount;
	
	public Statistic(){}
	
	public Statistic(String criteria, int number, double amount) {
		super();
		this.criteria = criteria;
		this.number = number;
		this.amount = amount;
	}
	
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}