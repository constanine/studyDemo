package com.bokesoft.distro.bizlock.impl.client.struc;

import java.util.LinkedList;
import java.util.List;

public class CLockOption {
	List<CTagOption> taglist = new LinkedList<CTagOption>();

	public List<CTagOption> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<CTagOption> taglist) {
		this.taglist = taglist;
	}
}
