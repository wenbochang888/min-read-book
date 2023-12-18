package com.tianya.entity;

import com.google.common.collect.Lists;
import com.tianya.entity.bo.ArticleBaseBo;

import java.util.List;

/**
 * @author changwenbo
 * @date 2023/11/29 19:50
 */
public final class ArticleConstant {

	public static final List<ArticleBaseBo> ARTICLE_ALL_LIST = Lists.newArrayList();

	public static final String ARTICLE_ALL_LIST_KEY = "article_all_list_key";

	public static final String DEFAULT_ARTICLE_ID = "1001";

	public static final Integer KK_PAGE_SIZE = 1084 / 3 + 1;
	public static final Integer KK_CURRENT = 3;

	public static final Integer BEIJING_PAGE_SIZE = 1759 / 3 + 1;
	public static final Integer BEIJING_CURRENT = 3;


	static {
		ArticleBaseBo kkHouse = new ArticleBaseBo();
		kkHouse.setArticleId("1001");
		kkHouse.setAuthorName("kk");
		kkHouse.setArticleName("2010年的房地产调控，我们收获了什么？写在房价暴涨前");
		kkHouse.setTotalPageSize(KK_PAGE_SIZE);
		kkHouse.setCurrent(KK_CURRENT);
		kkHouse.setArticleImage("/article/redis/get/image?name=1001");
		ARTICLE_ALL_LIST.add(kkHouse);


		ArticleBaseBo beijing = new ArticleBaseBo();
		beijing.setArticleId("1002");
		beijing.setAuthorName("子房");
		beijing.setArticleName("北京十年房子故事");
		beijing.setTotalPageSize(BEIJING_PAGE_SIZE);
		beijing.setCurrent(BEIJING_CURRENT);
		beijing.setArticleImage("/article/redis/get/image?name=1002");
		ARTICLE_ALL_LIST.add(beijing);

		ArticleBaseBo china = new ArticleBaseBo();
		china.setArticleId("1003");
		china.setAuthorName("陈雨露 杨忠恕");
		china.setArticleName("中国是部金融史");
		china.setTotalPageSize(0);
		china.setCurrent(0);
		china.setArticleImage("/article/redis/get/image?name=1003");
		china.setShortLink("#小程序://微信读书/33akYgjm3DYcysw");
		ARTICLE_ALL_LIST.add(china);
	}
}
