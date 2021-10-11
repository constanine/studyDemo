package com.bokesoft.distro.bizlock.impl.server.struc;

import java.util.concurrent.locks.ReentrantLock;

public class LockTypeData {
	
	private int[] tagGourps;
	private ReentrantLock lock = new ReentrantLock();
	public int[] getTagGourps() {
		return tagGourps;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public LockTypeData(int[] tagGourps) {
		this.tagGourps = tagGourps;
	}
}
