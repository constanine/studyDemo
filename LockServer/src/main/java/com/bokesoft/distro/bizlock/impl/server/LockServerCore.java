package com.bokesoft.distro.bizlock.impl.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import com.bokesoft.distro.bizlock.impl.server.struc.LockData;
import com.bokesoft.distro.bizlock.impl.server.struc.LockTypeData;

public class LockServerCore {
	/**
	 * 将lockType对应的锁以及下标集合缓存起来
	 */
	private static Map<String, LockTypeData> TAG_GROUP_POOL = new ConcurrentHashMap<String, LockTypeData>();
	/**
	 * 所有锁存在的锁池缓存
	 */
	private static Map<String, LockData> LOCK_POOL = new ConcurrentHashMap<String, LockData>();
	/**
	 * 所有Tag形成的索引池, 按Tag下标为,作为集合的下标位,值一个二级缓存 二级缓存的key:tag值,value:对应锁key
	 */
	private static List<Map<String, List<String>>> TAG_INDEX_POOL = new ArrayList<Map<String, List<String>>>();
	/**
	 * 针对LockType缓存每个locktype下 主键tag数组,用于快速比较
	 */
	private static Map<String, List<String[]>> LOCKTYPE_PK_POOL = new ConcurrentHashMap<String, List<String[]>>();

	private static ReentrantLock lock = new ReentrantLock();

	private static void registerLockType(String lockType, int[] pkGroups, int[] searchIndexGourps, int[] reetrantGroups)
			throws InterruptedException {
		if (null == TAG_GROUP_POOL.get(lockType)) {
			if (lock.tryLock(1, TimeUnit.SECONDS)) {
				TAG_GROUP_POOL.put(lockType,
						new LockTypeData(megreIndexTagPos(pkGroups, searchIndexGourps, reetrantGroups), pkGroups));
				init_TAG_INDEX_POOL(megreIndexTagPos(pkGroups, searchIndexGourps, reetrantGroups));
				lock.unlock();
			}
		}
	}

	public static void acquire(String lockType, int[] pkGroups, int[] searchIndexGourps, int[] reetrantGroups,
			boolean beShared, String[] tagValues, long expireTimeMs) throws InterruptedException {
		registerLockType(lockType, pkGroups, searchIndexGourps, reetrantGroups);
		String[] pkValues = extractPKValues(pkGroups, tagValues);
		String[] reetrantValues = extractReetrantValues(reetrantGroups, tagValues);
		String lockKey = buildPKString(pkValues);
		LockTypeData lockTypeData = TAG_GROUP_POOL.get(lockType);
		try {
			if (lockTypeData.getLock().tryLock(1, TimeUnit.SECONDS)) {
				try {
					List<String[]> matchPkGroups = getPKMatchGroups(lockType, pkGroups, pkValues);
					if (matchPkGroups.size() == 0) {
						LOCK_POOL.put(lockKey, new LockData(lockKey, beShared, tagValues,
								System.currentTimeMillis() + expireTimeMs, lockType));
						buildTagIndex(lockType, lockKey, tagValues);
					} else {
						if (!beShared) {
							throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:"
									+ beShared + ",tags:" + tagValues
									+ " can't locked,beacuse of already has other locks:" + matchPkGroups);
						} else {
							boolean canReentrant = true;
							pkM: for (String[] matchPkGroup : matchPkGroups) {
								if (!LOCK_POOL.get(buildPKString(matchPkGroup)).isShared()) {
									String[] curReetrantValues = extractReetrantValues(reetrantGroups,
											LOCK_POOL.get(lockKey).getTagValues());
									for (int pos = 0; pos < reetrantValues.length; pos++) {
										if (!reetrantValues[pos].equals(curReetrantValues[pos])) {
											canReentrant = false;
											break pkM;
										}
									}
								} else {
									canReentrant = false;
									break;
								}
							}
							if (canReentrant) {
								LOCK_POOL.put(lockKey, LOCK_POOL.get(lockKey).relock(expireTimeMs));
							} else {
								throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:"
										+ beShared + ",tags:" + tagValues
										+ " can't locked,beacuse of already has other locks:" + matchPkGroups);
							}
						}
					}
				} finally {
					lockTypeData.getLock().unlock();
				}
			}
		} catch (InterruptedException e) {
			throw new InterruptedException("lock执行等待超时");
		}
	}

	public void renew(String lockType, int[] pkGroups, int[] searchIndexGourps, int[] reetrantGroups, boolean beShared,
			String[] tagValues, long expireTimeMs) {
		String[] pkValues = extractPKValues(pkGroups, tagValues);
		String[] reetrantValues = extractReetrantValues(reetrantGroups, tagValues);
		String lockKey = buildPKString(pkValues);
		LockTypeData lockTypeData = TAG_GROUP_POOL.get(lockType);
		if (lockTypeData.getLock().tryLock()) {
			if (LOCK_POOL.get(lockKey).isShared() | beShared) {
				String[] curReetrantValues = extractReetrantValues(reetrantGroups,
						LOCK_POOL.get(lockKey).getTagValues());
				boolean canRelease = true;
				for (int pos = 0; pos < reetrantValues.length; pos++) {
					if (!reetrantValues[pos].equals(curReetrantValues[pos])) {
						canRelease = false;
						break;
					}
				}
				if (canRelease) {
					LOCK_POOL.get(lockKey).renew(expireTimeMs);
				} else {
					throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:" + beShared
							+ ",tags:" + tagValues + " can't renew,beacuse of already lock not match");
				}
			} else {
				throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:" + beShared
						+ ",tags:" + tagValues + " can't renew,beacuse of already lock not match");
			}
		}
	}

	public void release(String lockType, int[] pkGroups, int[] searchIndexGourps, int[] reetrantGroups,
			boolean beShared, String[] tagValues) {
		String[] pkValues = extractPKValues(pkGroups, tagValues);
		String[] reetrantValues = extractReetrantValues(reetrantGroups, tagValues);
		String lockKey = buildPKString(pkValues);
		LockTypeData lockTypeData = TAG_GROUP_POOL.get(lockType);
		if (lockTypeData.getLock().tryLock()) {
			if (LOCK_POOL.get(lockKey).isShared() | beShared) {
				String[] curReetrantValues = extractReetrantValues(reetrantGroups,
						LOCK_POOL.get(lockKey).getTagValues());
				boolean canRelease = true;
				for (int pos = 0; pos < reetrantValues.length; pos++) {
					if (!reetrantValues[pos].equals(curReetrantValues[pos])) {
						canRelease = false;
						break;
					}
				}
				if (canRelease) {
					LOCK_POOL.get(lockKey).release();
					if (LOCK_POOL.get(lockKey).getCount() == 0) {
						LOCK_POOL.remove(lockKey);
						releaseTagIndex(lockType, lockKey, tagValues);
					}
				} else {
					throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:" + beShared
							+ ",tags:" + tagValues + " can't release,beacuse of already lock not match");
				}
			} else {
				throw new RuntimeException("LockType:" + lockType + ",LockKey:" + lockKey + ",isShared:" + beShared
						+ ",tags:" + tagValues + " can't release,beacuse of already lock not match");
			}
		}
	}

	public static List<String> findByTags(String lockType, String[][] tagConditions) {
		List<String> result = new LinkedList<String>();
		for (int pos = 0; pos < tagConditions.length; pos++) {
			String[] tagCondition = tagConditions[pos];
			if (null != tagCondition && tagCondition.length > 0) {
				Set<String> tagMatchSet = new HashSet<String>();
				for (String tagValue : tagCondition) {
					for (String lockKey : TAG_INDEX_POOL.get(pos).get(tagValue)) {
						tagMatchSet.add(lockKey);
					}
				}
				if (result.isEmpty()) {
					result.addAll(tagMatchSet);
				} else {
					for (String lockKey : tagMatchSet) {
						if (!result.contains(lockKey)) {
							result.remove(lockKey);
						}
					}
					for (String lockKey : result) {
						if (!tagMatchSet.contains(lockKey)) {
							result.remove(lockKey);
						}
					}
				}
			}
		}
		return result;
	}

	public static void releaseByTags(String lockType, String[][] tagConditions) {
		List<String> result = findByTags(lockType, tagConditions);
		for (String lockKey : result) {
			LOCK_POOL.get(lockKey).release();
			if (LOCK_POOL.get(lockKey).getCount() == 0) {
				LOCK_POOL.remove(lockKey);
				releaseTagIndex(lockType, lockKey, LOCK_POOL.get(lockKey).getTagValues());
			}
		}
	}

	private static String buildPKString(String[] pkValues) {
		return StringUtils.join(pkValues, "-");
	}

	private static void buildTagIndex(String lockType, String lockKey, String[] tagValues) {
		for (Integer idx : TAG_GROUP_POOL.get(lockType).getNeedBuildIndexesPosSet()) {
			Map<String, List<String>> tagIndex = TAG_INDEX_POOL.get(idx);
			if (null == tagIndex) {
				tagIndex = new HashMap<String, List<String>>();
				TAG_INDEX_POOL.add(idx, tagIndex);
			}
			String tagValue = tagValues[idx];
			if (null == tagValue || "*".equals(tagValue)) {
				tagValue = lockType;
			}
			List<String> tagIndexList = tagIndex.get(tagValue);
			if (null == tagIndexList) {
				tagIndexList = new LinkedList<String>();
				tagIndex.put(tagValue, tagIndexList);
			}
			tagIndexList.add(lockKey);
		}
		List<String[]> pkGroupIndex = LOCKTYPE_PK_POOL.get(lockType);
		if (null == pkGroupIndex) {
			pkGroupIndex = new LinkedList<String[]>();
			LOCKTYPE_PK_POOL.put(lockType, pkGroupIndex);
		}
		String[] pkValues = extractPKValues(TAG_GROUP_POOL.get(lockType).getPkGroups(), tagValues);
		pkGroupIndex.add(pkValues);
	}

	private static void init_TAG_INDEX_POOL(Set<Integer> tags) {
		int maxPos = 0;
		for (int pos : tags) {
			if (pos > maxPos) {
				maxPos = pos;
			}
		}
		for (int start = TAG_INDEX_POOL.size() - 1; start < maxPos; start++) {
			TAG_INDEX_POOL.add(null);
		}
	}

	private static void releaseTagIndex(String lockType, String lockKey, String[] tagValues) {
		for (Integer idx : TAG_GROUP_POOL.get(lockType).getNeedBuildIndexesPosSet()) {
			String tagValue = tagValues[idx];
			TAG_INDEX_POOL.get(idx).get(tagValue).remove(lockKey);
		}
		List<String[]> pkGroupIndex = LOCKTYPE_PK_POOL.get(lockType);
		String[] pkValues = extractPKValues(TAG_GROUP_POOL.get(lockType).getPkGroups(), tagValues);
		pkGroupIndex.remove(pkValues);
	}

	private static Set<Integer> megreIndexTagPos(int[] pkGroups, int[] searchIndexGourps, int[] reetrantGroups) {
		Set<Integer> needBuildIndexesPosSet = new HashSet<Integer>();
		for (int pkPos : pkGroups) {
			needBuildIndexesPosSet.add(pkPos);
		}
		for (int sPos : searchIndexGourps) {
			needBuildIndexesPosSet.add(sPos);
		}
		for (int rPos : reetrantGroups) {
			needBuildIndexesPosSet.add(rPos);
		}
		return needBuildIndexesPosSet;
	}

	private static String[] extractPKValues(int[] pkGroups, String[] tagValues) {
		String[] result = new String[pkGroups.length];
		for (int pos = 0; pos < pkGroups.length; pos++) {
			result[pos] = tagValues[pkGroups[pos]];
		}
		return result;
	}

	private static String[] extractReetrantValues(int[] reetrantGroups, String[] tagValues) {
		String[] result = new String[reetrantGroups.length];
		for (int pos = 0; pos < reetrantGroups.length; pos++) {
			result[pos] = tagValues[reetrantGroups[pos]];
		}
		return result;
	}

	private static List<String[]> getPKMatchGroups(String lockType, int[] pkGroups, String[] pkValues) {
		List<String[]> matchPkGroups = new LinkedList<String[]>();
		List<String[]> pkGroupIndex = LOCKTYPE_PK_POOL.get(lockType);
		if (null != pkGroupIndex) {
			for (String[] pkGroupIndexCell : pkGroupIndex) {
				boolean match = true;
				for (int pos = 0; pos < pkGroupIndexCell.length; pos++) {
					String pkValue = pkValues[pos];
					String pkGValue = pkGroupIndexCell[pos];
					if (null == pkGValue) {
						continue;
					}
					if (null == pkValue) {
						continue;
					}
					if (!pkValue.equals(pkGValue)) {
						match = false;
						break;
					}
				}
				if (match) {
					matchPkGroups.add(pkValues);
				}
			}
		}
		return matchPkGroups;
	}
}
