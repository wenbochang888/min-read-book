package com.tianya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changwenbo
 * @date 2023/12/5 20:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DownloadType {
	private String type;
	private String content;


	public static DownloadType of(String type, String content) {
		return new DownloadType(type, content);
	}
}
