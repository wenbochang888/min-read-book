package com.tianya.test.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changwenbo
 * @date 2023/9/27 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDTO extends DTO {
	private String min;
	private String max;
}
