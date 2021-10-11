package com.bokesoft.distro.bizlock.impl.client.struc;

public class CLockData {
	private String key;
	private CLockOption lockOption;
	public String getKey() {
		return key;
	}
	public CLockOption getLockOption() {
		return lockOption;
	}
	public CLockData(String key, CLockOption lockOption) {
		super();
		this.key = key;
		this.lockOption = lockOption;
	}
}
