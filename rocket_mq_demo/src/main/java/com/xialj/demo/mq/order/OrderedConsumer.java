package com.xialj.demo.mq.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

public class OrderedConsumer {
	private static DateFormat  df = SimpleDateFormat.getDateTimeInstance();

	public static void main(String[] args) throws Exception {
		System.out.println(new Date(1572597101105L));
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
		consumer.setNamesrvAddr("localhost:9876");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		consumer.subscribe("TopicTest", "TagA || TagC || TagD");

		consumer.registerMessageListener(new MessageListenerOrderly() {

			AtomicLong consumeTimes = new AtomicLong(0);

			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
				context.setAutoCommit(false);
				for (MessageExt msg : msgs) {
					System.out.println("Receive New Message { " + "msgBornHost:" + msg.getBornHostNameString()
							+ ",msgKey:" + msg.getKeys() + ",msgTopic:" + msg.getTopic() + ",msgTag:" + msg.getTags()
							+ ",msgBornTime:" + df.format(new Date(msg.getBornTimestamp())) + ",msgContent:"
							+ new String(msg.getBody()) + " }");
				}
//				this.consumeTimes.incrementAndGet();
//				if ((this.consumeTimes.get() % 2) == 0) {
//					return ConsumeOrderlyStatus.SUCCESS;
//				} else if ((this.consumeTimes.get() % 3) == 0) {
//					return ConsumeOrderlyStatus.ROLLBACK;
//				} else if ((this.consumeTimes.get() % 4) == 0) {
//					return ConsumeOrderlyStatus.COMMIT;
//				} else if ((this.consumeTimes.get() % 5) == 0) {
//					context.setSuspendCurrentQueueTimeMillis(3000);
//					return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//				}
				return ConsumeOrderlyStatus.SUCCESS;

			}
		});

		consumer.start();

		System.out.printf("Consumer Started.%n");
	}
}
