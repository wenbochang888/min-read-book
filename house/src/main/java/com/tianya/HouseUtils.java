package com.tianya;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author changwenbo
 * @date 2023/12/10 17:30
 */
public class HouseUtils {
	public static void main(String[] args) {

		String key0 = StrUtil.format("子房:1002:0");
		String val0 = "前言: 本人对经济一些比较感兴趣，也比较经常关注新闻，逛逛论坛什么的。刚在天涯看了一篇帖子 《北京十年房子故事》。这篇写于2013年的帖子，现在2020年，完全预测了中国的房价，并且已经入手了8套，实现了财富自由秉着对大神的憧憬，本人又是学计算机的，既然帖子内容这么好。那爬下来了，说动手就动手。欢迎关注公众号：程序员博博，小程序：博博的小玩意";


		try (LineIterator it = FileUtils.lineIterator(new File("/Users/apple/a.txt"))) {
			int pos = 1;
			StringBuilder sb = new StringBuilder();
			while (it.hasNext()) {
				String line = it.nextLine();
				String key = StrUtil.format("子房:1002:{}", pos);

				if (StringUtils.isNotEmpty(line) && line.contains("楼:")) {
					// 处理
					System.out.println(sb.toString());
					sb = new StringBuilder();
					pos++;
				} else {
					sb.append(line);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
