package com.tianya.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author changwenbo
 * @date 2023/5/18 15:00
 */
@Slf4j
public final class EnumUtil {

	private EnumUtil() {

	}
	/**
	 * 根据code 转枚举
	 * @param code 值
	 * @param enumType 类型
	 * @param fieldStr 字段名
	 * @param <T> 返回枚举
	 * @return 返回枚举
	 */
	public static <T extends Enum<T>> Optional<T> convertEnumByCode(Integer code, Class<T> enumType, String fieldStr) {
		try {
			Enum<T>[] enumConstants = enumType.getEnumConstants();
			for (Enum<T> enumConstant : enumConstants) {
				Class<T> aClass = enumConstant.getDeclaringClass();
				Field field = aClass.getDeclaredField(fieldStr);
				field.setAccessible(true);
				Integer codeValue = Convert.toInt(field.get(enumConstant));
				if (ObjectUtil.equal(codeValue, code)) {
					return Optional.of((T) enumConstant);
				}
			}
		} catch (Exception e) {
			log.error("code 转枚举失败 错误原因 ", e);
		}
		return Optional.empty();
	}


	/**
	 * 根据code 转枚举  枚举字段值名称一定要为code！
	 * @param code 值
	 * @param enumType 类型
	 * @param <T> 返回枚举
	 * @return 返回枚举
	 */
	public static <T extends Enum<T>> Optional<T> convertEnumByCode(Integer code, Class<T> enumType) {
		return convertEnumByCode(code, enumType, "code");
	}

	public static <T extends Enum<T>> Optional<T> convertEnumByCode(Byte code, Class<T> enumType) {
		return convertEnumByCode(code.intValue(), enumType, "code");
	}

	public static <T extends Enum<T>> Optional<T> convertEnumByCode(String code, Class<T> enumType) {
		return convertEnumByCode(Convert.toInt(code), enumType, "code");
	}


	public static <T extends Enum<T>> Optional<T> convertEnumByCode(Byte code, Class<T> enumType, String fieldName) {
		return convertEnumByCode(code.intValue(), enumType, fieldName);
	}

}

