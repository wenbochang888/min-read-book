package com.tianya.entity.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author changwenbo
 * @date 2023/10/8 18:00
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value != null) {
			gen.writeString(value.divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN).toString());
		}
	}
}