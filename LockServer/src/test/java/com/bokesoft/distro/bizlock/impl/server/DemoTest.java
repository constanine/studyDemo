package com.bokesoft.distro.bizlock.impl.server;

public class DemoTest {
	private static String lockType1 = "test1";
	private static String lockType2 = "test2";
	private static int[] lockType1_pkg = { 1, 2, 3 };
	private static int[] lockType2_pkg = { 0, 2, 3, 4 };
	private static int[] lockType1_sg = { 4 };
	private static int[] lockType2_sg = {};
	private static int[] lockType1_rg = { 0 };
	private static int[] lockType2_rg = { 5 };

	public static void main(String[] args) throws InterruptedException {
		String[] t1Value = { "OPERATOR1","公司A", "工厂A", "物料A", "采购" };
		String[] t2Value = { "OPERATOR2","公司A", "工厂A", "物料A", "采购" };
		String[] t3Value = { "OPERATOR1","公司A", "工厂A", "*", "采购" };		
		String[] t4Value = { "OPERATOR2","公司A", "工厂B", "物料A", "采购" };
		String[] t5Value = { "OPERATOR2","公司A", "工厂A", "物料B", "采购" };
		Thread t1 = new DemoThread("T1",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value);
		t1.start();
		Thread t2 = new DemoThread("T2",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value);
		t2.start();
		Thread t3 = new DemoThread("T3",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t1Value);
		t3.start();
		Thread t4 = new DemoThread("T4",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t2Value);
		t4.start();
		Thread t5 = new DemoThread("T5",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t3Value);
		t5.start();
		Thread t6 = new DemoThread("T6",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t4Value);
		t6.start();
		Thread t7 = new DemoThread("T7",lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t5Value);
		t7.start();
	}
}
