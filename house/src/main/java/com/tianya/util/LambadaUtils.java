package com.tianya.util;

import com.alibaba.fastjson.JSON;
import com.tianya.entity.Person;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author changwenbo
 * @date 2023/7/7 16:00
 */
public class LambadaUtils {

	public static void main(String[] args) {
		List<Person> list = new LinkedList<>();

		// 名字 + 年龄。注意所有人的年龄有相同的
		list.add(new Person("张三", 21));
		list.add(new Person("张四", 21));

		list.add(new Person("李三", 28));
		list.add(new Person("李四", 28));

		list.add(new Person("赵三", 23));
		list.add(new Person("赵四", 23));


		// key是age，value是list
		Map<Integer, List<Person>> map1 = list.stream().collect(Collectors.groupingBy(person -> person.getAge()));
		System.out.println(map1);


		// key是age，value是name的list
		Map<Integer, List<String>> map2 = list.stream().collect(
				Collectors.groupingBy(person -> person.getAge(), Collectors.mapping(person -> person.getName(), Collectors.toList())));
		System.out.println(map2);

		// key是age，value是list, 且是有序的
		Map<Integer, List<Person>> map3 = list.stream().collect(
				Collectors.groupingBy(Person::getAge, () -> new LinkedHashMap<>(), Collectors.toList()));
		System.out.println(map3);

		// key是age，value是name的list, 且是有序的
		Map<Integer, List<String>> map4 = list.stream().collect(
				Collectors.groupingBy(Person::getAge, LinkedHashMap::new, Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println(map4);
	}

	public static void testReduce(List<Person> list) {
		// 相当于 sum = a + b + c + d + ....
		int sum = list.stream().map(Person::getAge).reduce((first, second) -> {
			return first + second;
		}).get();
		System.out.println("sum = " + sum);
		System.out.println("================================");

		// 相当于 sum = init + a + b + c + d + ....
		int init = 0;
		int sum2 = list.stream().map(Person::getAge).reduce(init, (first, second) -> {
			//log.info("first = {}, second = {}", first, second);
			return first + second;
		});
		System.out.println("sum2 = " + sum2);
		System.out.println("================================");

		int sum3 = list.stream().mapToInt(Person::getAge).sum();
		System.out.println("sum3 = " + sum3);
		System.out.println("============testReduce==================");
	}

	public static void testCollectMap(List<Person> list) {
		// 报错 因为age是一个是重复的
//		Map<Integer, Person> collect = list.stream().collect(Collectors.toMap(Person::getAge, Function.identity()));

		// 如果重复key，选择oldValue
		Map<Integer, Person> map1 = list.stream().collect(Collectors.toMap(Person::getAge, Function.identity(), (oldValue, newValue) -> oldValue));
		System.out.println("map1 = " + JSON.toJSONString(map1));
		System.out.println("================================");

		// 返回的map是有序的，linkedHashMap
		Map<Integer, Person> map2 = list.stream().collect(Collectors.toMap(Person::getAge,
				Function.identity(), (k1, k2) -> k1, LinkedHashMap::new));
		System.out.println("map2 = " + JSON.toJSONString(map2));
		System.out.println("================================");

		// 拿到所有的age list集合
		List<Integer> map3 = list.stream().map(Person::getAge).collect(Collectors.toList());
		System.out.println("collect = " + JSON.toJSONString(map3));

		System.out.println("============testCollectMap====================");
	}

	public static void testGrouping(List<Person> list) {
		// 按照age进行分类, 默认value是原来的list
		Map<Integer, List<Person>> groupMap = list.stream().collect(Collectors.groupingBy((person) -> person.getAge()));
		System.out.println("groupMap = " + GsonUtils.toJson(groupMap));
		System.out.println("================================");


		// 按照age进行分类, value是第二个参数
		Map<Integer, List<String>> groupMap2 =
				list.stream().collect(Collectors.groupingBy(Person::getAge,
						Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println(GsonUtils.toJson(groupMap2));
	}
}

























































