package com.yiye.utils;

public class StringEscapeUtils {

	/**
	 * Escape string to protected against SQL Injection
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}

		if (str.replaceAll(
				"[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]", "")
				.length() < 1) {
			return str;
		}

		String clean_string = str;
		clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\");
		clean_string = clean_string.replaceAll("\\n", "\\\\n");
		clean_string = clean_string.replaceAll("\\r", "\\\\r");
		clean_string = clean_string.replaceAll("\\t", "\\\\t");
		clean_string = clean_string.replaceAll("\\00", "\\\\0");
		clean_string = clean_string.replaceAll("'", "\\\\'");
		clean_string = clean_string.replaceAll("\\\"", "\\\\\"");

		return clean_string;
	}

	public static void main(String[] args) {
		System.out.println(escapeSql("o'%;1-~real"));
	}

}
