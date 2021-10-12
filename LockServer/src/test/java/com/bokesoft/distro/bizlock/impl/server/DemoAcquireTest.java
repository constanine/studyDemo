package com.bokesoft.distro.bizlock.impl.server;

public class DemoAcquireTest {
	private static String lockType1 = "test1";
	private static String lockType2 = "test2";
	private static int[] lockType1_pkg = { 1, 2, 3 };
	private static int[] lockType2_pkg = { 0, 2, 3 };
	private static int[] lockType1_sg = { 4 };
	private static int[] lockType2_sg = { 1 };
	private static int[] lockType1_rg = { 0 };
	private static int[] lockType2_rg = { 4, 5 };

	public static void main(String[] args) throws InterruptedException {
		String[] t1Value = { "OPERATOR1", "公司A", "工厂A", "物料A", "采购" };
		String[] t2Value = { "OPERATOR2", "公司A", "工厂A", "物料A", "采购" };
		String[] t3Value = { "OPERATOR1", "公司A", "工厂A", "*", "采购" };
		String[] t4Value = { "OPERATOR2", "公司A", "工厂B", "物料A", "采购" };
		String[] t5Value = { "OPERATOR2", "公司A", "工厂A", "物料B", "采购" };
		Thread t1 = new DemoThread("T1-1", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value);
		t1.start();
		Thread t2 = new DemoThread("T1-2", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value);
		t2.start();
		Thread t3 = new DemoThread("T1-3", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t1Value);
		t3.start();
		Thread t4 = new DemoThread("T1-4", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t2Value);
		t4.start();
		Thread t5 = new DemoThread("T1-5", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t3Value);
		t5.start();
		Thread t6 = new DemoThread("T1-6", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t4Value);
		t6.start();
		Thread t7 = new DemoThread("T1-7", lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, false, t5Value);
		t7.start();

		String[] t21Value = { "公司A", "采购", "工厂A", "物料A", "OPERATOR1", "FORM1" };
		String[] t22Value = { "公司A", "采购", "工厂A", "物料A", "OPERATOR1", "FORM2" };
		String[] t23Value = { "公司A", "采购", "工厂A", "*", "OPERATOR1", "FORM1" };
		String[] t24Value = { "公司A", "采购", "工厂B", "物料A", "采购", "OPERATOR2", "FORM1" };
		String[] t25Value = { "公司A", "采购", "工厂A", "物料B", "采购", "OPERATOR2", "FORM1" };

		Thread t21 = new DemoThread("T2-1", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, true, t21Value);
		t21.start();
		Thread t22 = new DemoThread("T2-2", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, true, t21Value);
		t22.start();
		Thread t23 = new DemoThread("T2-3", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, false, t21Value);
		t23.start();
		Thread t24 = new DemoThread("T2-4", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, true, t22Value);
		t24.start();
		Thread t25 = new DemoThread("T2-5", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, true, t23Value);
		t25.start();
		Thread t26 = new DemoThread("T2-6", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, false, t24Value);
		t26.start();
		Thread t27 = new DemoThread("T2-7", lockType2, lockType2_pkg, lockType2_sg, lockType2_rg, false, t25Value);
		t27.start();

	}
}
