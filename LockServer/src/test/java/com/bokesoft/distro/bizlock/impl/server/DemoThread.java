package com.bokesoft.distro.bizlock.impl.server;

public class DemoThread extends Thread {
	private String lockType;
	private int[] lockType_pkg;
	private int[] lockType_sg;
	private int[] lockType_rg;
	private String[] tagValues;
	private boolean isShared;

	DemoThread(String name, String lockType, int[] lockType_pkg, int[] lockType_sg, int[] lockType_rg, boolean isShared,
			String[] tagValues) {
		super(name);
		this.lockType = lockType;
		this.lockType_pkg = lockType_pkg;
		this.lockType_sg = lockType_sg;
		this.lockType_rg = lockType_rg;
		this.isShared = isShared;
		this.tagValues = tagValues;
	}

	public void run() {
		try {
			LockServerCore.acquire(lockType, lockType_pkg, lockType_sg, lockType_rg, isShared, tagValues, 300000);
			System.out.println("线程:"+this.getName()+",获取锁成功");
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println("线程:"+this.getName()+",获取锁失败:"+e.getMessage());
		}
	}
}
