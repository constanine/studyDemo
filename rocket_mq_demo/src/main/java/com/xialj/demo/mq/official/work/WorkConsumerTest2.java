package com.xialj.demo.mq.official.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkConsumerTest2 {
	public static void main(String[] args) throws IOException, TimeoutException {
        WorkConsumer.receMessage("work");
    }
}
