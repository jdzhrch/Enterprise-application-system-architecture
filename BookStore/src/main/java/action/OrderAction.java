package action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import model.Book;
import model.Order;
import model.User;
import rmi.RmiClient;
import rmi.RmiOrder;
import service.AppService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Date;
import java.util.Properties;

public class OrderAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int id;
	private int userid;
	private Date date;
	private Date enddate;
	private String address;
	private String phone;
	private AppService appService;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public String add() throws Exception {
		Order order = new Order(this.userid, this.date, this.enddate,
		this.address, this.phone);
		this.appService.addOrder(order);
		return "success";
	}

	public String all() throws Exception {
		List<Order> orders = this.appService.getAllOrders();
		request().setAttribute("orders", orders);

		List<User> users = this.appService.getAllUsers();
		request().setAttribute("users", users);

		List<Book> books = this.appService.getAllBooks();
		request().setAttribute("books", books);

		return "success";
	}

	public String delete() throws Exception {
		Order order = this.appService.getOrderById(this.id);
		this.appService.deleteOrder(order);

		return "success";
	}

	public String update() throws Exception {
		Order order = this.appService.getOrderById(this.id);
		order.setUserid(this.userid);
		order.setDate(this.date);
		order.setEnddate(this.enddate);
		order.setAddress(this.address);
		order.setPhone(this.phone);
		this.appService.updateOrder(order);

		return "success";
	}

	public String deal() throws Exception {
		Order order = this.appService.getOrderById(this.id);
		order.setUserid(this.userid);
		order.setDate(this.date);
		order.setAddress(this.address);
		order.setPhone(this.phone);
		this.appService.updateOrder(order);

		RmiOrder rmiOrder = new RmiOrder(this.userid, this.date, null, this.address, this.phone);
		RmiClient rmiClient = new RmiClient();
		rmiClient.runShipOrder(rmiOrder);

		List<Book> books = this.appService.getAllBooks();
		request().setAttribute("books", books);

		session().removeAttribute("cart");
		Date date = null;
		Order cart = new Order(this.userid, date, null, null, null);
		this.appService.addOrder(cart);
		cart = (Order) this.appService.getCartByUserid(this.userid).get(0);
		session().setAttribute("cart", cart);
		return "deal";
	}

	public void confirmReceipt() throws Exception {
		Order order = this.appService.getOrderById(this.id);
		order.setEnddate(this.enddate);
		this.appService.updateOrder(order);
	}

	public String showStatistics() throws Exception {
		return "statistics";
	}
}