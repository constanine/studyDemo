package com.xialj.demo.mq.struc;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsItem implements  Serializable {
	private static final long serialVersionUID = 201910220002L;
	private String code;
	private String name;
	private BigDecimal price;
	private int qty;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
