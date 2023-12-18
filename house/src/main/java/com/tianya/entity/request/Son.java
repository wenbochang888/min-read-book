package com.tianya.entity.request;

import com.tianya.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changwenbo
 * @date 2023/6/6 20:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Son extends Parent {

	private String son;


	private Person person;

	private String name;
}

