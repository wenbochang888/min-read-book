package com.tianya.entity.serializer;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author changwenbo
 * @date 2023/6/13 21:33
 */
public class TimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {


		if (null != value) {
			String num = LocalDateTimeUtil.formatNormal(value);
			gen.writeString(num);
		} else {
			gen.writeString("");
		}
	}
}
