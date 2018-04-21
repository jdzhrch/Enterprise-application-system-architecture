package redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import model.Book;

import redis.clients.jedis.Jedis;

public class RedisJava {
	public static void main(String[] args) {
    	int id = 33;
    	Jedis jedis = new Jedis("localhost");
        String key = "User:id:" + id;
        byte[] byt=jedis.get(key.getBytes());
        if(byt == null){
        	Book book = new Book("hhh","jjj",10, "publisher",null,"image", 2, "category");
        	jedis.set(key.getBytes(), serialize(book));
        }
        byte[] byt2=jedis.get(key.getBytes());
        Object obj=unserialize(byt2);
        Book b2 = (Book)obj;
        System.out.println(b2.getTitle());
        
    }
	//序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserialize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return null;
    }
}
