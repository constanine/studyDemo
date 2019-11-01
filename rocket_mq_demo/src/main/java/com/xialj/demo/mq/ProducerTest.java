package com.xialj.demo.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerTest {
	public static void main(String[] args) throws IOException, TimeoutException {
		Producer.sendMessage();
	}
}
