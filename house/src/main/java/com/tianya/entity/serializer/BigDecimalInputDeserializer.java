package com.tianya.entity.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author changwenbo
 * @date 2023/6/13 21:32
 */
public class BigDecimalInputDeserializer extends JsonDeserializer<BigDecimal> {

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {




		String text = p.getText();
		if (StringUtils.isNotBlank(text)) {
			BigDecimal result = new BigDecimal(text).multiply(BigDecimal.valueOf(100));
			if (result.compareTo(BigDecimal.ZERO) < 0) {

			}
			// 添加兜底防止前端浮点错误，直接截取分以后
			return result.setScale(0, RoundingMode.DOWN);
		}
		return null;
	}

}