package com.tianya.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changwenbo
 * @date 2023/5/10 20:55
 */
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private Long id;
	private String name;
	private Integer age;
	private String email;

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}