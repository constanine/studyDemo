package com.bokesoft.distro.bizlock.impl.client.struc;

public abstract class CShareModel extends CTagOption {
	public abstract String getShareModelName();
	@Override
	public boolean isShareModel() {
		return true;
	}

}
