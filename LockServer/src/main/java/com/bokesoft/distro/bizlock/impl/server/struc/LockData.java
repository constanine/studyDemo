package com.bokesoft.distro.bizlock.impl.server.struc;

public class LockData {
	private String key;
	private String[] tagValues;
	private long outTime;
	private boolean shared;
	private String lockType;
	private long count = 0;

	public LockData(String key, boolean shared, String[] tagValues, long outTime, String lockType) {
		this.key = key;
		this.shared = shared;
		this.tagValues = tagValues;
		this.outTime = outTime;
		this.lockType = lockType;
	}

	public String getKey() {
		return key;
	}

	public boolean isShared() {
		return shared;
	}

	public String[] getTagValues() {
		return tagValues;
	}

	public long getOutTime() {
		return outTime;
	}

	public String getLockType() {
		return lockType;
	}

	public long getCount() {
		return count;
	}

	public LockData relock(long duration) {
		this.count++;
		this.outTime = System.currentTimeMillis()+duration;
		return this;
	}
	
	public LockData renew(long duration) {
		this.outTime = System.currentTimeMillis()+duration;
		return this;
	}

	public LockData release() {
		this.count--;
		return this;
	}

}
