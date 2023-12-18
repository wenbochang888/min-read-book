package com.tianya.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author changwenbo
 * @date 2023/10/17 19:39
 */
public class HouseMinProgramUtils {

	public static void main(String[] args) {
		process();
	}

	public static void process() {
		try (LineIterator it = FileUtils.lineIterator(new File("/Users/apple/b.txt"))) {
			int pos = 0;
			while (it.hasNext()) {
				String line = it.nextLine();
				if (StringUtils.isBlank(line) || line.length() <= 1) {
					continue;
				}
				if (pos == 689) {
					System.out.println("line = " + line);
				}

				String[] split = line.split("楼:");

				String key = StrUtil.format("kk:1001:{}", pos++);
				String val = split.length <= 1 ? "" : split[1];

				System.out.println(key + " ===  " + val);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static void processAToB() {
		StringBuilder sb = new StringBuilder();
		List<StringBuilder> list = new ArrayList<>();
		try (LineIterator it = FileUtils.lineIterator(new File("/Users/apple/a.txt"))) {
			while (it.hasNext()) {
				String line = it.nextLine();
				String regex = "\\d+楼";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					list.add(sb);
					sb = new StringBuilder();
					sb.append(line);
				} else {
					if (StringUtils.isBlank(line) || line.length() <= 1) {
						continue;
					}
					sb.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int number = 0;
		List<String> res = new ArrayList<>();
		StringBuilder temp = new StringBuilder();


		int pos = 1;
		for (StringBuilder s : list) {
			temp.append(s + "\n");
			number += s.length();
			if (number >= 2000) {
				res.add(temp.toString());
				res.add("\n");
				temp = new StringBuilder();
				number = 0;
				pos++;
			}
		}
		res.add( temp.toString());



		try {
			// 底层用了try-with-resources，会自动关闭资源
			FileUtils.writeLines(new File("/Users/apple/b.txt"), "utf-8", res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
