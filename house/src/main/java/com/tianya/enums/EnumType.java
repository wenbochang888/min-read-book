package com.tianya.enums;

import java.util.Arrays;

/**
 * @author changwenbo
 * @date 2023/5/12 16:33
 */
public interface EnumType<T> {
	/**
	 * Get the code of Enum.
	 *
	 * @return code
	 */
	T getCode();

	/**
	 * 判断code是否与当前枚举相同
	 *
	 * @param code
	 * @return
	 */
	default boolean isCode(T code) {
		if (code == null) {
			return false;
		}

		return code.equals(getCode());
	}

	/**
	 * 判断是否存在与当前枚举相同的code
	 *
	 * @param codes
	 * @return
	 */
	default boolean anyMatch(T...codes) {
		if (codes == null || codes.length == 0) {
			return false;
		}

		return Arrays.stream(codes).anyMatch(this::isCode);
	}

	default <E extends EnumType<T>> boolean anyMatch(E...enums) {
		if (enums == null || enums.length == 0) {
			return false;
		}

		for (E anEnum : enums) {
			if (this == anEnum) {
				return true;
			}
		}

		return false;
	}
}
