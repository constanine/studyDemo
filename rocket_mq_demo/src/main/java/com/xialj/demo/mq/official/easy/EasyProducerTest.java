package com.xialj.demo.mq.official.easy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EasyProducerTest {
	public static void main(String[] args) throws IOException, TimeoutException {
		EasyProducer.sendMessage("easy","hello word3");
	}
}
