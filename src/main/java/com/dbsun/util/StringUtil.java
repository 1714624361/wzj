package com.dbsun.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

/**
 * 字串工具类
 * @author Allen
 *
 */
@SuppressWarnings("rawtypes")
public abstract class StringUtil {
	
	/**
	 * 判断是否为整数
	 * @param integer 被判断的字符串
	 * @return true: 是整数; false:不为整数或数字
	 */
	public static boolean isNumber(String integer){
		return isNumber(integer, false);
	}
	
	/**
	 * 判断是否为整数
	 * @param integer 被判断的字符串
	 * @param isUnsign 是否为正数
	 * @return true: 是整数; false:不为整数或数字
	 */
	public static boolean isNumber(String integer, boolean isUnsign){
		if(integer == null) return false;
		return integer.matches("^"+(isUnsign?"":"[-+]?")+"[0-9]+$");
	}


	/**
	 * 判断是否浮点数或数字
	 * @param decimal 被检查的字符串
	 * @param type "0+":非负浮点数; "+":正浮点数; "-0":非正浮点数; "-":负浮点数; "":浮点数;
	 * @return true: 是浮点数或数字; false: 不为浮点数或数字;
	 */
	public static boolean isDecimal(String decimal, String type) {
		if(decimal == null) return false;
		String eL = "";
		if (type.equals("0+")) eL = "^\\d+(\\.\\d+)?$";// 非负浮点数
		else if (type.equals("+")) eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";// 正浮点数
		else if (type.equals("-0")) eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";// 非正浮点数
		else if (type.equals("-")) eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";// 负浮点数
		else eL = "^(-?\\d+)(\\.\\d+)?$";// 浮点数
		return decimal.matches(eL);
	}
	
	/**
	 * 判断是否浮点数或数字
	 * @param decimal
	 * @return true: 是浮点数或数字; false: 不为浮点数或数字;
	 */
	public static boolean isDecimal(String decimal) {
		return isDecimal(decimal, "");
	}

	/**
	 * 判断是否为布尔型
	 * @param bool
	 * @return
	 */
	public static boolean isBoolean(String bool){
		if(bool == null) return false;
		return bool.matches("^(true|false)$");
	}
	

	/**
	 * MD5加密方法
	 * @param str 要被加密的字符串
	 * @returnn 32位的字符串
	 */
	public static String md5(String str) {
		return md5(str, true);
	}
	
	/**
	 * MD5加密
	 * @param str 要被加密的字符串
	 * @param zero 是否用0进行补位
	 * @return 32位的字符串
	 */
	public static String md5(String str, boolean zero) {
		if(str == null) return null;
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		byte[] resultByte = messageDigest.digest(str.getBytes());
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < resultByte.length; ++i) {
			int v = 0xFF & resultByte[i];
			if (v < 16 && zero) result.append("0");
			result.append(Integer.toHexString(v));
		}
		return result.toString();
	}


	/**
	 * 验证两个字符串是否相等且不能为空
	 * @param str1
	 * @param str2
	 * @return true: 相等;
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
			return false;
		}
		return str1.equals(str2);
	}
	
	/**
	 * 判断字符串是否为空, 为空则返回默认值
	 * @param str1 被判断字符串
	 * @return true:有效; false: 无效
	 */
	public static boolean isEmpty(String str1){
		if(str1 == null || str1 == ""){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断字符串是否为空, 为空则返回
	 * @param string 被判断字符串
	 * @param defaultValue 补位字符串
	 * @return 判断后的字符串
	 */
	public static String toString(String string, String defaultValue){
		defaultValue = defaultValue == null ? "" : defaultValue;
		if(string == null) return defaultValue;
		return string;
	}
	
	/**
	 * 过滤字符串所有非字母或数字的字符
	 * @param wrods 要被过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String toWords(String words){
		if(words == null) return null;
		return words.replaceAll("[^a-zA-Z0-9]", "");
	}

	/**
	 * 过滤字符串所有非数字或小数点或+-号的字符
	 * @param wrods 要被过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String toNumberString(String number){
		if(number == null) return null;
		number = number.replaceAll("[^\\-\\+\\d\\.]", "");
		if(!isDecimal(number)) return null;
		return number;
	}

	/**
	 * 将一个字串转为int，如果无空，则返回默认值
	 * @param integer 要转换的数字字符串
	 * @param defaultValue 当转换出错时默认值
	 * @return Integer
	 */
	public static Integer toInt(String integer, Integer defaultValue) {
		if(isNumber(toNumberString(integer)))
			try {
				defaultValue = Integer.parseInt(integer);
			} catch (Exception ex) {}
		return defaultValue;
	}


	/**
	 * 转换为double, 如果转换失败则用defaultValue替代
	 * @param decimal 要转换的数字字符串
	 * @param defaultValue 当转换出错时默认值
	 * @return Double
	 */
	public static Double toDouble(String decimal, Double defaultValue) {
		if (toNumberString(decimal) != null)
			try {
				defaultValue = Double.valueOf(decimal);
			} catch (Exception ex) {}
		return defaultValue;
	}


	/**
	 * 转换为boolean, 如果转换失败则用defaultValue替代
	 * @param bool 要转换的字符串
	 * @param defaultValue 当转换出错时默认值
	 * @return Boolean
	 */
	public static Boolean toBoolean(String bool, Boolean defaultValue){
		if(isBoolean(bool))
			try{
				defaultValue = Boolean.valueOf(bool);
			}catch(Exception e){}
		return defaultValue;
	}


	/**
	 * 转换为long, 如果转换失败则用defaultValue替代
	 * @param l 要转换的数字字符串
	 * @param defaultValue 当转换出错时默认值
	 * @return Long
	 */
	public static Long toLong(String l, Long defaultValue){
		if(isNumber(l))
			try{
				defaultValue = Long.valueOf(l);
			}catch(Exception e){}
		return defaultValue;
	}
	/**
	 * 将计量单位字节转换为相应单位
	 * @param size
	 * @return
	 */
	public static String getFileSize(long filesize){
		String temp = "";
		Double fileSize = (double)filesize;
		DecimalFormat df = new DecimalFormat("0.00");
		if (fileSize >= 1024) {
			if (fileSize >= 1048576) {
				if (fileSize >= 1073741824) {
					temp = df.format(fileSize / 1024 / 1024 / 1024) + " GB";
				} else {
					temp = df.format(fileSize / 1024 / 1024) + " MB";
				}
			} else {
				temp = df.format(fileSize / 1024) + " KB";
			}
		} else {
			temp = df.format(fileSize / 1024) + " KB";
		}
		return temp;
	}

	/**
	 * 得到一个32位随机字符
	 * 
	 * @return
	 */
	public static String getEntry() {
		Random random = new Random(100);
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(new String(
				"yyyyMMddHHmmssS"));
		return md5(formatter.format(now) + random.nextDouble());
	}

	/**
	 * 将字符串由本地编码转换至给定的编码格式
	 * @param str 被转换的字符串
	 * @param charset 转换至的编码格式
	 * @return
	 */
	public static String to(String str, String charset) {
		return to(str, System.getProperty("file.encoding"), charset);
	}
	
	/**
	 * 将str给的编码转换至给定的编码格式
	 * @param str 被转换的字符串
	 * @param srtCharset str的编码格式
	 * @param charset 转换至的编码格式
	 * @return
	 */
	public static String to(String str, String srtCharset, String charset) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes(srtCharset), charset);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	/**
	 * clear trim to String
	 * @return
	 */
	public static String toTrim(String strtrim) {
		if (null != strtrim && !strtrim.equals("")) {
			return strtrim.trim();
		}
		return "";
	}
	public static String toString(Integer i) {
		if (i != null) {
			return String.valueOf(i);
		}
		return "";
	}

	public static String toString(Object i) {
		if (i != null) {
			return String.valueOf(i);
		}
		return "";
	}

	public static String toString(Long i) {
		if (i != null) {
			return String.valueOf(i);
		}
		return "";
	}

	public static String toString(Double d) {
		if (null != d) {
			return String.valueOf(d);
		}
		return "";
	}

	public static String getRandom() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++)
			result = result * 10 + array[i];

		return "" + result;
	}


	/**
	 * 进行解析
	 * @param regex
	 * @param rpstr
	 * @param source
	 * @return
	 */
	public static String doFilter(String regex, String rpstr, String source) {
		Pattern p = Pattern.compile(regex, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(source);
		return m.replaceAll(rpstr);
	}

	/**
	 * 脚本过滤
	 * @param source
	 * @return
	 */
	public static String formatScript(String source) {
		source = source.replaceAll("javascript", "&#106avascript");
		source = source.replaceAll("jscript:", "&#106script:");
		source = source.replaceAll("js:", "&#106s:");
		source = source.replaceAll("value", "&#118alue");
		source = source.replaceAll("about:", "about&#58");
		source = source.replaceAll("file:", "file&#58");
		source = source.replaceAll("document.cookie", "documents&#46cookie");
		source = source.replaceAll("vbscript:", "&#118bscript:");
		source = source.replaceAll("vbs:", "&#118bs:");
		source = doFilter("(on(mouse|exit|error|click|key))", "&#111n$2",
				source);
		return source;
	}


	/**
	 * 动态添加表前缀，对没有前缀的表增加前缀
	 * @param table
	 * @param prefix
	 * @return
	 */
	public static String addPrefix(String table, String prefix) {
		String result = "";
		if (table.length() > prefix.length()) {
			if (table.substring(0, prefix.length()).toLowerCase()
					.equals(prefix.toLowerCase()))
				result = table;
			else
				result = prefix + table;
		} else
			result = prefix + table;

		return result;
	}

    private static final char SEPARATOR = '_';

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
