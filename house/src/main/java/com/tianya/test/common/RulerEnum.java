package com.tianya.test.common;

/**
 * @author changwenbo
 * @date 2023/7/28 10:36
 */
public enum RulerEnum {

	EXAMPLE_RULER("0", "exampleRuler", "所有活动的一些规则"),

	LOAN_CREDIT_EXPIRE_DATE("1", "loanCreditExpireDate", "授信有效期内"),

	CREDIT_PASS_NOT_LOAN_DATE("2", "creditPassNotLoanDate", "授信通过但未用信"),

	;

	private String code;

	private String name;

	private String desc;

	RulerEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
