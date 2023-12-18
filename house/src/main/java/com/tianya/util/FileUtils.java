package com.tianya.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author changwenbo
 * @date 2019/10/18 16:58
 */
@Slf4j
public class FileUtils {

	public static void main(String[] args) {
		try {
			String url = "https://bme-lala-finance.oss-cn-shenzhen.aliyuncs.com/loan/user/order/2467186444618252288.jpeg?Expires=1687937165&OSSAccessKeyId=LTAI4FzAizzUCB5UbYRqMDEx&Signature=c8KgNjD%2BceGrWmopr5b1PmWLaJU%3D";
			org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), new File("/Users/apple/1.jpeg"));
		} catch (Exception e) {}

	}

	private static final String PREFIX = "### ==**";
	private static final String SUFFIX = "楼: **==" + "\n";

	/** 写入文件中，转化为PDF文档 */
	public static String writeFile(List<String> res, String uuid) {
		String path = "D://" + uuid + ".md";
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)))) {
			int cnt = 1;
			log.info("开始写入磁盘....uuid = {}", uuid);
			for (String s : res) {
				// markdown格式
				String ss = PREFIX + cnt + SUFFIX + "\n";
				bos.write(ss.getBytes());
				// 真正数据
				bos.write(s.getBytes());
				cnt++;
			}
			log.info("写入磁盘成功...uuid = {}", uuid);
		} catch (Exception e) {
			log.error("写入文件出错：" + e.getMessage());
		}
		return path;
	}

	public static byte[] downLoadPic(String imgUrl){
		try {
			//定义一个URL对象，就是你想下载的图片的URL地址
			URL url = new URL(imgUrl);
			//打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//设置请求方式为"GET"
			conn.setRequestMethod("GET");
			//超时响应时间为10秒
			conn.setConnectTimeout(1000);
			//通过输入流获取图片数据
			InputStream is = conn.getInputStream();
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
			return readInputStream(is);
		} catch (Exception e) {
			log.warn("=======图片下载异常========",e);
		}
		return null;
	}


	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[6024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len;
		//使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
