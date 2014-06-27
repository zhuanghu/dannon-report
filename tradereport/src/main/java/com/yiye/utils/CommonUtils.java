package com.yiye.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 常用工具类
 * 
 * @author tony
 * 
 */
public class CommonUtils {

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			str.concat(Integer.toHexString((int) s.charAt(i)));
		}
		return str;
	}

	public static String parseHexString(String hexString, String encoding) {
		if (StringUtils.isEmpty(hexString))
			return null;
		int count = hexString.length() / 2;
		byte[] b = new byte[count];
		for (int i = 0; i < count; i++) {
			String s = hexString.substring(i * 2, i * 2 + 2);
			b[i] = (byte) Integer.parseInt(s, 16);
		}
		try {
			return new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			return hexString;
		}
	}

	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}

	public static byte[] md5(byte[] b) {
		return DigestUtils.md5(b);
	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
				| (b[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF),
				(byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}

	/**
	 * hexString -> byte[], like f7dbeb735b7a07f1cfca79cc1dfe4fa4 -> { 0xf7,
	 * 0xdb, 0xeb, 0x73, 0x5b, 0x7a, 0x07, 0xf1, 0xcf, 0xca, 0x79, 0xcc, 0x1d,
	 * 0xfe, 0x4f, 0xa4 }
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] toByteArrayFromHexString(String hexString) {
		if (StringUtils.isEmpty(hexString))
			return null;

		byte[] b = new byte[hexString.length() / 2];
		int pos = 0;
		while (pos < hexString.length()) {
			int k = Integer.parseInt(hexString.substring(pos, pos + 2), 16);
			b[pos / 2] = (byte) k;
			pos += 2;
		}
		return b;
	}

	public static String toUnicodeCharacter(String str) {
		return StringEscapeUtils.escapeJava(str);
	}

	public static String escapeHtmlEntities(String str) {
		if (StringUtils.isEmpty(str))
			return str;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (!StringUtils.isAsciiPrintable(s)) {
				sb.append("&#x").append(Integer.toHexString(s.charAt(0)))
						.append(";");
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	/**
	 * %u54E6 (javascript) -> \u54E6 (java)
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeJavaScriptUnicode(String str) {
		Pattern p = Pattern.compile("%u([a-zA-Z0-9]{4})");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb,
					String.valueOf((char) Integer.parseInt(m.group(1), 16)));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static List<String> convert2StringList(List<Integer> list) {
		if (list == null)
			return null;

		List<String> result = new ArrayList<String>();
		for (Integer i : list) {
			result.add(String.valueOf(i));
		}
		return result;
	}

	public static <T> List<T> setToList(Set<T> set) {
		if (set == null)
			return null;
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}

	public static <T> Set<T> listToSet(List<T> list) {
		if (list == null)
			return null;
		Set<T> set = new HashSet<T>();
		set.addAll(list);
		return set;
	}

}