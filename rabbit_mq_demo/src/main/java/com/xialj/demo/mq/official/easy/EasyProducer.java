package com.xialj.demo.mq.official.easy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xialj.demo.mq.kryo.KryoUtil;
import com.xialj.demo.mq.struc.Order;
import com.xialj.demo.serialization.DemoBeanCreator;

public class EasyProducer {

	public static void sendMessage(String QUEUE_NAME, String message) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); 
				) {
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			Order order = DemoBeanCreator.buildOrder();
			channel.basicPublish("", QUEUE_NAME, null, KryoUtil.serializationObject(Order.class, order));
			System.out.println(" [x] Sent '" + JSON.toJSONString(order) + "'");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
