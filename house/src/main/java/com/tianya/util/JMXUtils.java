package com.tianya.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changwenbo
 * @date 2023/3/23 11:40
 */
@Slf4j
public class JMXUtils {

	public void process() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		String osName = operatingSystemMXBean.getName();
		String osVersion = operatingSystemMXBean.getVersion();
		int processors = operatingSystemMXBean.getAvailableProcessors();
		System.out.println(String.format("操作系统：%s，版本：%s，处理器：%d 个", osName, osVersion, processors));

		CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
		String compilationMXBeanName = compilationMXBean.getName();
		System.out.println("编译系统：" + compilationMXBeanName);

		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
		long max = heapMemoryUsage.getMax();
		long used = heapMemoryUsage.getUsed();
		System.out.println(String.format("使用内存：%dMB/%dMB", used / 1024 / 1024, max / 1024 / 1024));

		List<GarbageCollectorMXBean> gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		String gcNames = gcMXBeans.stream()
				.map(MemoryManagerMXBean::getName)
				.collect(Collectors.joining(","));
		System.out.println("垃圾收集器：" + gcNames);


		MemoryMXBean memoryMXBean2 = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage2 = memoryMXBean2.getHeapMemoryUsage();
		MemoryUsage nonHeapMemoryUsage2 = memoryMXBean2.getNonHeapMemoryUsage();
		log.info("heapMemoryUsage2 = {}", GsonUtils.toJson(heapMemoryUsage2));
		log.info("nonHeapMemoryUsage2 = {}", GsonUtils.toJson(nonHeapMemoryUsage2));
	}

}
