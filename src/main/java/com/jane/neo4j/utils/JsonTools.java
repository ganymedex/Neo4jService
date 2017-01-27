package com.jane.neo4j.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonTools {

	// public static final Object parse(String text); //
	// 把JSON文本parse为JSONObject或者JSONArray
	// public static final JSONObject parseObject(String text)； //
	// 把JSON文本parse成JSONObject
	// public static final <T> T parseObject(String text, Class<T> clazz); //
	// 把JSON文本parse为JavaBean
	// public static final JSONArray parseArray(String text); //
	// 把JSON文本parse成JSONArray
	// public static final <T> List<T> parseArray(String text, Class<T> clazz);
	// //把JSON文本parse成JavaBean集合
	// public static final String toJSONString(Object object); //
	// 将JavaBean序列化为JSON文本
	// public static final String toJSONString(Object object, boolean
	// prettyFormat); // 将JavaBean序列化为带格式的JSON文本
	// public static final Object toJSON(Object javaObject);
	// 将JavaBean转换为JSONObject或者JSONArray。

	public static <T> Object parserStrToBean(String jsonStr, Class<T> t) {
		// TODO 参数检测 异常处理
		return JSON.parseObject(jsonStr, t);
	}

	public static <T> List<T> parserStrToListBean(String jsonStr, Class<T> t) {
		return JSON.parseArray(jsonStr, t);
	}

	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> List<T> getBeanList(String jsonData, Class<T> clazz) {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<Map<String, Object>> getBeanMapList(String jsonData) {
		return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
		});
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的javaMap
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static Map<String, Object> getBeanMap(String jsonData) {
		return JSON.parseObject(jsonData, new TypeReference<Map<String, Object>>() {
		});
	}

	// public static void main(String[] args) {
	// Entrust bean = new Entrust();
	// bean.setApplId(StringUtils.getUUID());
	//
	// String s=toJson(bean);
	// System.out.println(s);
	//
	// Entrust str=(Entrust) parserStrToBean(toJson(bean), Entrust.class);
	// System.out.println(str);
	// }

	// public static <T> List<T> getBeanList(String jsonStr,Class<T> t){
	// List<Map<String, Object>> listMap = JSON.parseObject(jsonStr, new
	// TypeReference<List<Map<String,Object>>>(){});
	// return JSON.parseArray(jsonStr, t);
	// }
}
