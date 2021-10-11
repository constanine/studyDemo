package com.bokesoft.distro.bizlock.impl.client.struc;

public class CTagDataOption extends CTagOption {
	private boolean primaryKey;
	private boolean serachable;
	private boolean reentrantMask;

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isSerachable() {
		return serachable;
	}

	public void setSerachable(boolean serachable) {
		this.serachable = serachable;
	}

	public boolean isReentrantMask() {
		return reentrantMask;
	}

	public void setReentrantMask(boolean reentrantMask) {
		this.reentrantMask = reentrantMask;
	}

	@Override
	public boolean isShareModel() {
		return false;
	}

}
