package com.xialj.demo.serialization;

import com.xialj.demo.mq.struc.Order;

public interface SerializationHandler {
	public Object serialization(Order order) throws Exception;

	public String getName();

	public Order deserialization(Object obj) throws Exception;
}
