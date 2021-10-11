package com.bokesoft.distro.bizlock.impl.server;

public class DemoTest {
	private static String lockType1 = "test1";
	private static String lockType2 = "test2";
	private static int[] lockType1_pkg = { 1, 2, 3 };
	private static int[] lockType2_pkg = { 0, 2, 3, 4 };
	private static int[] lockType1_sg = { 4 };
	private static int[] lockType2_sg = {};
	private static int[] lockType1_rg = { 1 };
	private static int[] lockType2_rg = { 5 };

	public static void main(String[] args) {
		final String[] t1Value = { "OPERATOR1","公司A", "工厂A", "物料A", "采购" };
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					LockServerCore.acquire(lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value, 300000);
				} catch (InterruptedException e) {
					System.err.print(e);
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					LockServerCore.acquire(lockType1, lockType1_pkg, lockType1_sg, lockType1_rg, true, t1Value, 300000);
				} catch (InterruptedException e) {
					System.err.print(e);
				}
			}
		});
		t2.start();
	}
}
