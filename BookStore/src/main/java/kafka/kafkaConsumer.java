package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Orderitem;
import service.AppService;

import java.util.Arrays;
import java.util.Properties;

public class kafkaConsumer {
	private static AppService appService;

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("topic1"));

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动类
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "zhrch0376");// （url数据库的IP地址，user数据库用户名，password数据库密码）
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(conn);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
				String[] ss = record.value().split("/");
				int orderid = Integer.parseInt(ss[0]);
				int bookid = Integer.parseInt(ss[1]);
				int amount = Integer.parseInt(ss[2]);
				int orderitemprice = Integer.parseInt(ss[3]);
				Orderitem oi = new Orderitem(orderid, bookid, amount, orderitemprice);

				PreparedStatement ps = null;
				String sql = "insert into orderitems(orderid,bookid,amount,orderitemPrice)" + "values('" + ss[0]
						+ "','" + ss[1] + "','" + ss[2] + "','" + ss[3] + "')";
				System.out.println(sql);
				try {
					ps = conn.prepareStatement(sql);// 把写好的sql语句传递到数据库，让数据库知道我们要干什么
					int a = ps.executeUpdate();// 这个方法用于改变数据库数据，a代表改变数据库的条数
					if (a > 0) {
						System.out.println("添加成功");
					} else {
						System.out.println("添加失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}