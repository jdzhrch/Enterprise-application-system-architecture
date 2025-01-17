package com.sjtu.se.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerExample {
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
	    while (true) {
	      ConsumerRecords<String, String> records = consumer.poll(100);
	      for (ConsumerRecord<String, String> record : records)
	        System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
	    }
	  }/*
		 * String[] ss = new String[4]; ss = record.value().split("/"); int
		 * orderid = Integer.parseInt(ss[0]); int bookid =
		 * Integer.parseInt(ss[1]); int amount = Integer.parseInt(ss[2]); int
		 * orderitemprice = Integer.parseInt(ss[3]);
		 */

}