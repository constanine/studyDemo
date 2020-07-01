package com.xialj.demo.mq.simple;

import org.apache.rocketmq.client.exception.MQClientException;

public class AsyConsumer {
	public static void main(String[] args) throws InterruptedException, MQClientException {
		ConsumerUtil.listenerMqMsg("asydemo","asydemo1");
    }
}
