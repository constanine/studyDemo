package com.xialj.demo.mq.kryo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.xialj.demo.mq.struc.GoodsItem;
import com.xialj.demo.mq.struc.Order;

public class KryoTest {
	private static String[] nameSeq = { "笔记本", "鼠标", "内存", "硬盘", "机箱" };
	private static BigDecimal[] priceReq = { new BigDecimal("9889"), new BigDecimal("98"), new BigDecimal("889"),
			new BigDecimal("589"), new BigDecimal("89") };

	public static Order buildOrder() {
		Order order = new Order();
		order.setAmout(new BigDecimal("12000.50"));
		order.setBillNo("T20191022");
		List<GoodsItem> items = new ArrayList<GoodsItem>();
		for (int i = 0; i < 5; i++) {
			GoodsItem item = new GoodsItem();
			item.setCode("G000" + i);
			item.setName(nameSeq[i]);
			item.setPrice(priceReq[i]);
			item.setQty(i);
			items.add(item);
		}
		order.setItems(items);
		return order;
	}
}
