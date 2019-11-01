package com.xialj.demo.mq.struc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements  Serializable {
	private static final long serialVersionUID = 201910220001L;
	private String billNo;
	private BigDecimal amout;
	private List<GoodsItem> items;
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public BigDecimal getAmout() {
		return amout;
	}
	public void setAmout(BigDecimal amout) {
		this.amout = amout;
	}
	public List<GoodsItem> getItems() {
		return items;
	}
	public void setItems(List<GoodsItem> items) {
		this.items = items;
	}
	
}
