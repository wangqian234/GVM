package com.dt.util;
/**
 * 字符串处理
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * 字符串字符转义
	 * @return
	 */
	public static String replaceStr(String queryWord) {
		queryWord = queryWord.replaceAll("'","\'");// 防止查询中带'报错
		queryWord = queryWord.replaceAll("\r","\\r");// 防止查询中带\r报错
		queryWord = queryWord.replaceAll("\n","\\n");// 防止查询中带\n报错
		queryWord = queryWord.replaceAll("\t","\\t");// 防止查询中带\t报错
		queryWord = queryWord.replaceAll(":","\\:");// 防止查询中带:报错
		//queryWord = queryWord.replaceAll("\\\\","\\\\");// 防止查询中带\\报错
		return queryWord.trim();
	}
}
