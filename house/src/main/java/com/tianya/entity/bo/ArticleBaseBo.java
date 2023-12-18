package com.tianya.entity.bo;

import lombok.Data;

/**
 * @author changwenbo
 * @date 2023/11/28 15:33
 */
@Data
public class ArticleBaseBo {

	// 唯一ID
	private String articleId;

	private String articleName;

	private String articleImage;

	private String authorName;

	// 跳转小程序页面
	private String shortLink;

	private Integer current = 0;

	private Integer totalPageSize = 0;
}
