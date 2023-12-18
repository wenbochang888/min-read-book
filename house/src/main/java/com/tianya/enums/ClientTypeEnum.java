package com.tianya.enums;

/**
 * @author changwenbo
 * @date 2023/5/12 16:34
 */
public enum ClientTypeEnum implements EnumType<String> {

	DEFAULT("0", 1, "微信小程序"),

	MINI_PROGRAM("1", 4, "微信小程序");

	private String code;

	private int terminal;

	private String desc;

	ClientTypeEnum(String code, int terminal, String desc) {
		this.code = code;
		this.terminal = terminal;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	public String getDesc(){
		return desc;
	}


}
