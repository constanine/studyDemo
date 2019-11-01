package com.xialj.demo.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {
	public static void main(String[] args) throws IOException, TimeoutException {
        Consumer.receMessage();
    }
}
