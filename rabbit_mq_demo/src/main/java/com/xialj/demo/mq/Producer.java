package com.xialj.demo.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public static void sendMessage() throws IOException, TimeoutException {
		// 鍒涘缓杩炴帴宸ュ巶
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		// 璁剧疆 RabbitMQ 鍦板潃
		factory.setHost("localhost");
		// 寤虹珛鍒颁唬鐞嗘湇鍔″櫒鍒拌繛鎺�
		Connection conn = factory.newConnection();
		// 鑾峰緱淇￠亾
		Channel channel = conn.createChannel();
		// 澹版槑浜ゆ崲鍣�
		String exchangeName = "hello-exchange";
		channel.exchangeDeclare(exchangeName, "direct", true);

		String routingKey = "hola";
		// 鍙戝竷娑堟伅
		byte[] messageBodyBytes = "quit".getBytes();
		channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

		channel.close();
		conn.close();
	}
}
