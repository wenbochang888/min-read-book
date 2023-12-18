package com.tianya.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changwenbo
 * @date 2023/1/16 16:56
 */
public class RedisLockUtils {
	private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();


	public static boolean lock(String key, String val) {
		String flag = map.putIfAbsent(key, val);
		return flag == null ? true : false;
	}

	public static boolean unlock(String key, String val) {
		return map.remove(key, val);
	}

}
