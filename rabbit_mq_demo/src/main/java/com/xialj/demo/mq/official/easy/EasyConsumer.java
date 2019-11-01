package com.xialj.demo.mq.official.easy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.xialj.demo.mq.kryo.KryoUtil;
import com.xialj.demo.mq.struc.Order;

public class EasyConsumer {
	public static void receMessage(String QUEUE_NAME) throws IOException, TimeoutException {
		 ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();

	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            Order order = (Order) KryoUtil.deserializationObject(Order.class, delivery.getBody());
	            System.out.println(" [x] Received '" + JSON.toJSONString(order) + "'");
	        };
	        channel.basicConsume(QUEUE_NAME, true, deliverCallback, (consumerTag) -> {
	        	System.out.println(" [x] canceled '" + consumerTag + "'");
	        });
    }
}
