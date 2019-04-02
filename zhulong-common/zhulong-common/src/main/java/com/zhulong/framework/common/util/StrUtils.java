package com.zhulong.framework.common.util;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;

import java.util.regex.Pattern;

/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 * 
 * @author dengfl
 * 
 */
public class StrUtils {
	/**
	 * 禁止实例化
	 */
	private StrUtils() { }

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://")
				|| url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	/**
	 * 分割并且去除空格
	 * 
	 * @param str
	 *            待分割字符串
	 * @param sep
	 *            分割符
	 * @param sep2
	 *            第二个分隔符
	 * @return 如果str为空，则返回null。
	 */
	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);
		// trim
		for (int i = 0, len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			StringBuilder sb = new StringBuilder(html.length());
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
	 * 
	 * @param str
	 * @param search
	 * @return
	 */
	public static boolean contains(String str, String search) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}
	
	/**
	 * 判断字符串是否为空或者null，空或者null返回true
	 * @param str
	 * @return
	 * @author dangrenning
	 * @date 2015年4月20日 下午2:57:38
	 */
	public static boolean isBlank(String str){
		if( StringUtils.isBlank(str) || "null".equals(str) || "undefined".equals(str) ){
			return true;
		}

		return false;
	}

	public static boolean isNotBlank(String input){
		return !isBlank(input);
	}
    
	/**
	 * 数字转中文
	 * @param number
	 * @return
	 * @author zhengkai
	 * @date 2015年7月1日 上午10:22:26
	 */
	public static String numberToChinese(Integer number){
		    // 单位数组
		    String[] units = new String[] {"十", "百", "千", "万", "十", "百", "千", "亿"};
		    
		    // 中文大写数字数组
		    String[] numeric = new String[] {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

		    String strNumber=number.toString();
	        String res = "";

	            // 遍历一行中所有数字
	            for (int k = -1; strNumber.length() > 0; k++)
	            {
	                // 解析最后一位
	                int j = Integer.parseInt(strNumber.substring(strNumber.length() - 1, strNumber.length()));
	                String rtemp = numeric[j];
	                
	                // 数值不是0且不是个位 或者是万位或者是亿位 则去取单位
	                if (j != 0 && k != -1 || k % 8 == 3 || k % 8 == 7)
	                {
	                    rtemp += units[k % 8];
	                }
	                
	                // 拼在之前的前面
	                res = rtemp + res;
	                
	                // 去除最后一位
	                strNumber = strNumber.substring(0, strNumber.length() - 1);
	            }
	            
	            // 去除后面连续的零零..
	            while (res.endsWith(numeric[0]))
	            {
	                res = res.substring(0, res.lastIndexOf(numeric[0]));
	            }
	            
	            // 将零零替换成零
	            while (res.indexOf(numeric[0] + numeric[0]) != -1)
	            {
	                res = res.replaceAll(numeric[0] + numeric[0], numeric[0]);
	            }
	            
	            // 将 零+某个单位 这样的窜替换成 该单位 去掉单位前面的零
	            for (int m = 1; m < units.length; m++)
	            {
	                res = res.replaceAll(numeric[0] + units[m], units[m]);
	            }
	            	            
	            return res;
	}

	public static String parseString(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

}
