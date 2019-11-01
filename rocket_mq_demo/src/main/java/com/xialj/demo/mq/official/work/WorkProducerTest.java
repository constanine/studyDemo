package com.xialj.demo.mq.official.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkProducerTest {
	public static void main(String[] args) throws IOException, TimeoutException {
		WorkProducer.sendMessage("work","hello word1");
		WorkProducer.sendMessage("work","hello word2");
		WorkProducer.sendMessage("work","hello word3");
		WorkProducer.sendMessage("work","hello word4");
		WorkProducer.sendMessage("work","hello word5");
		WorkProducer.sendMessage("work","hello word6");
	}
}
