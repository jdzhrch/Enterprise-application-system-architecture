package com.sjtu.se.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class KafkaProducerExample {
	public static void main(String[] args) {
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("acks", "all");
	    props.put("retries", 0);
	    props.put("batch.size", 16384);
	    props.put("linger.ms", 1);
	    props.put("buffer.memory", 33554432);
	    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    Producer<String, String> producer = new KafkaProducer<String, String>(props);
	    Orderitem oo = new Orderitem();
	    producer.send(new ProducerRecord<String, String>("topic1", Integer.toString(oo.getOrderid()),Integer.toString(oo.getOrderid())+"/"+Integer.toString(oo.getBookid())+"/"+Integer.toString(oo.getAmount())+"/"+Integer.toString(oo.getOrderitemPrice())));
  		producer.close();
	  }
}
