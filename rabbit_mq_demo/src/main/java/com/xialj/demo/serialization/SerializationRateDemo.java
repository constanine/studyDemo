package com.xialj.demo.serialization;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xialj.demo.mq.bson.BsonUtilTest;
import com.xialj.demo.mq.kryo.KryoUtilTest;
import com.xialj.demo.mq.struc.Order;

public class SerializationRateDemo {
	public static void main(String[] args) throws Exception {
		Order order = DemoBeanCreator.buildOrder();
		
		Object serObj1 = showSerializationSpeed(order,new JSONSerializationHandler());		
		Object serObj2 = showSerializationSpeed(order,new BSONSerializationHandler());		
		Object serObj3 = showSerializationSpeed(order,new KryoSerializationHandler());
		showDeserializationSpeed(serObj1,new JSONSerializationHandler());
		showDeserializationSpeed(serObj2,new BSONSerializationHandler());
		showDeserializationSpeed(serObj3,new KryoSerializationHandler());
	}

	private static Object showSerializationSpeed(Order order,SerializationHandler handler) throws Exception {
		long times1 = System.currentTimeMillis();
		Object serObj = handler.serialization(order);
		long timee1 = System.currentTimeMillis();
		System.out.println(handler.getName()+" serialization is cost "+(timee1-times1)+" ms");
		return serObj;
	}
	
	private static void showDeserializationSpeed(Object obj,SerializationHandler handler) throws Exception {
		long times1 = System.currentTimeMillis();
		Order order = handler.deserialization(obj);
		long timee1 = System.currentTimeMillis();
		System.out.println(handler.getName()+" serialization is cost "+(timee1-times1)+" ms");
		System.out.println("Deserialization result:"+JSON.toJSONString(order));
	}
	
	private static class JSONSerializationHandler implements SerializationHandler{
		@Override
		public Object serialization(Order order) {
			return JSON.toJSONString(order);
		}
		@Override
		public String getName() {
			return "fastjson";
		}
		@Override
		public Order deserialization(Object obj) {
			return JSONObject.parseObject(obj.toString(),Order.class);
		}
	}
	
	private static class BSONSerializationHandler implements SerializationHandler{
		@Override
		public Object serialization(Order order) throws Exception {
			return BsonUtilTest.serializationObjectbyObjectMapper(order);
		}
		@Override
		public String getName() {
			return "bson";
		}
		@Override
		public Order deserialization(Object obj) throws JsonParseException, JsonMappingException, IOException {
			byte[] bytearray = (byte[]) obj;
			return BsonUtilTest.deserializationObject(bytearray, Order.class);
		}
	}
	
	private static class KryoSerializationHandler implements SerializationHandler{
		@Override
		public Object serialization(Order order) throws FileNotFoundException {
			return KryoUtilTest.serializationObject(Order.class, order);
		}
		@Override
		public String getName() {
			return "kryo";
		}
		@Override
		public Order deserialization(Object obj) {
			byte[] byteData = (byte[]) obj;
			return (Order) KryoUtilTest.deserializationObject(byteData,Order.class);
		}
	}
}
