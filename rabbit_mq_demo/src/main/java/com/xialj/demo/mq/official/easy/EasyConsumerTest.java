package com.xialj.demo.mq.official.easy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EasyConsumerTest {
	public static void main(String[] args) throws IOException, TimeoutException {
        EasyConsumer.receMessage("easy");
    }
}
