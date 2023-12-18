package com.tianya.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2023/4/14 11:36
 */
@Slf4j
public class SyncTest {
	public static final Object LOCK = new Object();

	public void sync1_1() {
		// 锁住lock对象
		synchronized (LOCK) {
			sleep("sync1_1");
		}
	}

	public void sync1_2() {
		// 锁住lock对象
		synchronized (LOCK) {
			sleep("sync1_2");
		}
	}

	public void sync2_1() {
		// 锁住SyncTask new的那个对象
		// 会和其他方法互斥
		synchronized (this) {
			sleep("sync2_1");
		}
	}
	public void sync2_2() {
		// 锁住SyncTask new的那个对象
		// 会和其他方法互斥
		synchronized (this) {
			sleep("sync2_2");
		}
	}


	// 锁住SyncTask new的那个对象
	// 会和其他方法互斥
	public synchronized void sync3_1() {
		sleep("sync3_1");
	}

	// 锁住SyncTask new的那个对象
	// 会和其他方法互斥
	public synchronized void sync3_2() {
		sleep("sync3_2");
	}

	// 锁住SyncTask这个类 类锁
	// 会和其他方法互斥
	public static synchronized void sync4_1() {
		sleep("sync4_1");
	}

	public static synchronized void sync4_2() {
		sleep("sync4_2");
	}

	// 锁住Main类，会和其他方法互斥
	public void sync5_1() {
		synchronized (SyncTest.class) {
			sleep("sync5_1");
		}
	}

	public void sync5_2() {
		synchronized (SyncTest.class) {
			sleep("sync5_2");
		}
	}

	public void noSync() {
		sleep("noSync");
	}

	public static void sleep(String name) {
		try {
			log.info(name + " get lock");
			TimeUnit.SECONDS.sleep(1);
			log.info(name + " release lock");
		} catch (Exception e) {}
	}
}
