package com.tianya.controller.house;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.google.gson.reflect.TypeToken;
import com.tianya.entity.ArticleConstant;
import com.tianya.entity.DownloadType;
import com.tianya.entity.bo.ArticleBaseBo;
import com.tianya.entity.bo.ArticleContentResp;
import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RequestMapping("/article/redis")
@RestController
@Slf4j
public class ArticleController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 获取文章内容
	 * @param articleId
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/get/content")
	public ArticleContentResp getContent(String articleId, Integer pageSize) {
		log.info("articleId = {}, pageSize = {}", articleId, pageSize);
		try {
			Map<String, ArticleBaseBo> map = getAllArticleFromRedis();
			ArticleBaseBo articleBaseBo = map.get(articleId);
			if (articleBaseBo == null) {
				articleBaseBo = map.get(ArticleConstant.DEFAULT_ARTICLE_ID);
			}
			log.info("articleBaseBo = {}", GsonUtils.toJson(articleBaseBo));

			String content = getContent(articleBaseBo, pageSize);
			ArticleContentResp resp = BeanUtil.copyProperties(articleBaseBo, ArticleContentResp.class);
			resp.setContent(content);

			return resp;
		} catch (Exception e) {
			log.info("e = {}", e.getMessage(), e);
			return null;
		}
	}


	/**
	 * 获取所有文章信息
	 * @return
	 */
	@RequestMapping("/get/all")
	public List<ArticleBaseBo> getAllArticle() {
		Map<String, ArticleBaseBo> map = getAllArticleFromRedis();
		log.info("map = {}", GsonUtils.toJson(map));

		List<ArticleBaseBo> list = new ArrayList<>();
		for (Map.Entry<String, ArticleBaseBo> mp : map.entrySet()) {
			list.add(mp.getValue());
		}

		log.info("list = {}", GsonUtils.toJson(list));
		return list;
	}

	@RequestMapping("/get/download")
	public Object getDownLoad() {
		List<DownloadType> list = new ArrayList<>();
		String text1 = "\n本人对经济一些比较感兴趣，也比较经常关注新闻，逛逛论坛什么的。\n";
		text1 += "18年在知乎看到一个问题: 房价真的会就这样一直涨下去吗？ 感慨甚多里面提到了一篇2010年天涯社区的一篇帖子 《2010年的房地产调控，我们收获了什么？写在房价暴涨前这篇天涯的帖子》\n";
		list.add(DownloadType.of("text", text1));

		String text2 = "\n上面是2018年我当时写下的一段话，真的非常感慨。同时至今我仍然觉得这篇帖子对现实乃至现在都有很大的参考意义。\n";
		text2 += "kk这篇文章我已经阅读了不下三遍，非常的经典，每次阅读完都会有新的感受。尤其是其对历史的研究以及见解。我非常想把这篇文章分享给大家，因此开发了此小程序可以在线阅读\n";
		list.add(DownloadType.of("text", text2));

		String text3 = "\n如果您在阅读中有什么好的建议想与我分享，欢迎评论留言。\n";
		text3 += "小程序前端、后端源码都在github上开源。所有文章的PDF版本在github以及公众号都有下载，欢迎关注我的公众号：\"程序员博博\"，与我交流\n";
		list.add(DownloadType.of("text", text3));

		list.add(DownloadType.of("img", "/article/redis/get/image?name=gongzhonghao"));
//		list.add(DownloadType.of("img", "/article/redis/get/image?name=30cm"));
//		list.add(DownloadType.of("img", "/article/redis/get/image?name=1cm"));
//		list.add(DownloadType.of("img", "/article/redis/get/image?name=xiaochengxu"));

		log.info("list = {}", GsonUtils.toJson(list));
		return GsonUtils.toJson(list);
	}


	/**
	 * 获取文章封面首页
	 * @param name
	 * @return
	 * @throws IOException
	 */
	private Map<String, ResponseEntity<byte[]>> imageMap = new HashMap<>();
	@GetMapping(value = "/get/image", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<byte[]> getImage(String name) throws IOException {
		if (imageMap.containsKey(name)) {
			log.info("imageMap containsKey = {}", name);
			return imageMap.get(name);
		}

		Resource imgFile = new ClassPathResource("img/" + name + ".jpg");
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

		ResponseEntity<byte[]> body = ResponseEntity
				.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(bytes);
		imageMap.put(name, body);

		return body;
	}

	public Map<String, ArticleBaseBo> getAllArticleFromRedis() {
		String key = ArticleConstant.ARTICLE_ALL_LIST_KEY;
		String val = stringRedisTemplate.opsForValue().get(key);
		Map<String, ArticleBaseBo> map = GsonUtils.fromJson(val, new TypeToken<Map<String, ArticleBaseBo>>() {});
		return CollUtil.isEmpty(map) ? Collections.EMPTY_MAP : map;
	}


	@PostConstruct
	public void init() {
		Map<String, ArticleBaseBo> map = new LinkedHashMap<>();
		for (ArticleBaseBo articleBaseBo : ArticleConstant.ARTICLE_ALL_LIST) {
			map.put(articleBaseBo.getArticleId(), articleBaseBo);
		}


		String key = ArticleConstant.ARTICLE_ALL_LIST_KEY;
		String val = GsonUtils.toJson(map);
		log.info("kk init key = {}, val = {}", key, val);
		stringRedisTemplate.opsForValue().set(key, val);
	}


	public static String getRedisKey(String authorName, String articleId, Integer pageSize) {
		if (pageSize == null) {
			pageSize = 0;
		}
		return authorName + ":" + articleId + ":" + pageSize;
	}

	private String getContent(ArticleBaseBo articleBaseBo, Integer pageSize) {
		int current = articleBaseBo.getCurrent();
		int end = pageSize * current;
		int start = end - current + 1 < 0 ? 0 : end - current + 1;

		StringBuilder sb = new StringBuilder();
		for (int i = start; i <= end; i++) {
			String key = getRedisKey(articleBaseBo.getAuthorName(), articleBaseBo.getArticleId(), i);
			String val = stringRedisTemplate.opsForValue().get(key);
			if (StringUtils.isEmpty(val)) {
				return sb.toString();
			}
			sb.append("      " + val + "\n"); // 6个空格
		}


		return sb.toString();
	}
}


/**
 *
 *
 * 备案：https://qcloudimg.tencent-cloud.cn/raw/ac4a2fb526f6998d88b624402b4332e6/%E5%B9%BF%E4%B8%9C%E7%9C%81%E4%B8%AA%E4%BA%BA%E7%BD%91%E7%AB%99%E5%A4%87%E6%A1%88%E6%89%BF%E8%AF%BA%E4%B9%A6%EF%BC%88%E6%A8%A1%E6%9D%BF%EF%BC%89.pdf
 *
 */


















