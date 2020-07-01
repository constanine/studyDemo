package com.xialj.demo.mq.order;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OrderedProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
		producer.setNamesrvAddr("localhost:9876");
		// Launch the instance.
		producer.start();
		String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
		final CountDownLatch countDownLatch = new CountDownLatch(10);// 定义条件
		for (int i = 0; i < 20; i++) {
			int orderId = i % 10;
			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i,
					("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					Integer id = (Integer) arg;
					int index = id % mqs.size();
					countDownLatch.countDown();
					return mqs.get(index);
				}
			}, orderId);
			System.out.printf("%s%n", sendResult);
		}
		System.out.println("发出消息总数:" + countDownLatch.getCount());
		countDownLatch.await(5, TimeUnit.SECONDS);// 达到100关闭生产（发送者）

		// server shutdown
		producer.shutdown();
	}
}
