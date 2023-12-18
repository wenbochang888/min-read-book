package com.tianya.entity;

import com.tianya.enums.EnumType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author changwenbo
 * @date 2022/6/27 15:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
	private String name;

	private Integer age;

	public Person(String name, int age, String creditNo) {
		this.name = name;
		this.age = age;
	}

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}


	public enum ClientTypeEnum implements EnumType<String> {
		MINI_PROGRAM("1", "微信小程序");

		private String code;

		private String desc;

		ClientTypeEnum(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		@Override
		public String getCode() {
			return this.code;
		}

		public String getDesc(){
			return desc;
		}


		public static ClientTypeEnum getSmsCodeSceneEnum(String code) {
			for (ClientTypeEnum smsCodeSceneEnum : ClientTypeEnum.values()) {
				if (smsCodeSceneEnum.getCode().equals(code)) {
					return smsCodeSceneEnum;
				}
			}
			return null;
		}
	}

}
