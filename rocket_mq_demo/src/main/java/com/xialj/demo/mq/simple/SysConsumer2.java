package com.xialj.demo.mq.simple;

import org.apache.rocketmq.client.exception.MQClientException;

public class SysConsumer2 {
	public static void main(String[] args) throws InterruptedException, MQClientException {

        // Instantiate with specified consumer group name.
        ConsumerUtil.listenerMqMsg("sysdemo","sysdemo2");
    }
}
