package com.bokesoft.distro.bizlock.impl.server.struc;

import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class LockTypeData {

	private Set<Integer> needBuildIndexesPosSet;
	private int[] pkGroups;
	private ReentrantLock lock = new ReentrantLock();

	public Set<Integer> getNeedBuildIndexesPosSet() {
		return needBuildIndexesPosSet;
	}

	public int[] getPkGroups() {
		return pkGroups;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public LockTypeData(Set<Integer> needBuildIndexesPosSet, int[] pkGroups) {
		this.needBuildIndexesPosSet = needBuildIndexesPosSet;
		this.pkGroups = pkGroups;
	}
}
