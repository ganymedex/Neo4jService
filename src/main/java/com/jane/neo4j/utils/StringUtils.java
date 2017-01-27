package com.jane.neo4j.utils;

import java.util.UUID;

public class StringUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
