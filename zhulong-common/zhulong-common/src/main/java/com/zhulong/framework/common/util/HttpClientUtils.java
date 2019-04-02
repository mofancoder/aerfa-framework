package com.zhulong.framework.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * 
 * @author dengfl
 * 
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {
	
	/**
	 * 用户名
	 */
	private static final String USERNAME_PARAM = "username";
	
	/**
	 * 参数
	 */
	private static final String PASSWORD_PARAM = "password";
	
	/**
	 * 默认HTTP头信息
	 */
	private static Map<String, String> defaultHeaders = new HashMap<String, String>();
	
	/**
	 * 初始化默认HTTP头信息
	 */
	static {
		defaultHeaders.put("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		defaultHeaders.put("Content-Type", "application/x-www-form-urlencoded");
	}
	
	/**
	 * 登录后提交
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String loginAndPost(String url, String username, String password, String postUrl, Map<String, Object> params) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		Map<String, Object> loginParams = new HashMap<String, Object>();
		loginParams.put(USERNAME_PARAM, username);
		loginParams.put(PASSWORD_PARAM, password);
		post(url, loginParams, httpClient);
		CookieStore cookieStore = httpClient.getCookieStore();
		httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(cookieStore);
		return post(postUrl, params, httpClient);
	}

	public static String post(String url) throws Exception {
		return post(url, null, null);
	}
	
	public static String post(String url, DefaultHttpClient httpClient) throws Exception {
		return post(url, null, httpClient);
	}
	
	public static String post(String url, Map<String, Object> params) throws Exception {
		return post(url, params, null);
	}
	
	public static String post(String url, Map<String, Object> params, DefaultHttpClient httpClient) throws Exception {
		return post(url, defaultHeaders, params, httpClient, null);
	}
	
	/**** 自定义超时时间 *****/
	public static String postForChaoShi(String url, Integer time) throws Exception {
		return postForChaoShi(url, null, null, time);
	}
	
	public static String postForChaoShi(String url, DefaultHttpClient httpClient, Integer time) throws Exception {
		return postForChaoShi(url, null, httpClient, time);
	}
	
	public static String postForChaoShi(String url, Map<String, Object> params, Integer time) throws Exception {
		return postForChaoShi(url, params, null, time);
	}
	
	public static String postForChaoShi(String url, Map<String, Object> params, DefaultHttpClient httpClient, Integer time) throws Exception {
		return post(url, defaultHeaders, params, httpClient, time);
	}
	
	/********/

	public static String post(String url, Map<String, String> headers, Map<String, Object> params,
                              DefaultHttpClient httpClient, Integer time) throws Exception {
		if(httpClient == null){
			httpClient = new DefaultHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
		try {
			if (headers != null) {
				setHeaders(httpPost, headers);
			} else {
				setHeaders(httpPost, defaultHeaders);
			}
			List<NameValuePair> nvps = setParams(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			setTimeouts(httpClient, httpPost, time);
			return readResponseBody(httpClient, httpPost);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static String postFile(String url, String pathname) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		Map<String, String> headers = new HashMap<String, String>();
		headers.putAll(defaultHeaders);
		headers.remove("Content-Type");
		setHeaders(httpPost, headers);
		//设置HttpMultipartMode为浏览器兼容模式，字符集为UTF-8，防止中文文件名乱码
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
		entity.addPart("file", new FileBody(new File(pathname)));
		httpPost.setEntity(entity);

		try {
			return readResponseBody(httpClient, httpPost);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}


	private static void setHeaders(HttpPost httpPost,
			Map<String, String> headers) {
		for (Entry<String, String> entry : headers.entrySet()) {
			httpPost.setHeader(entry.getKey(), entry.getValue());
		}
	}

	private static List<NameValuePair> setParams(Map<String, Object> params) {
		StringBuilder paramsLogBuilder = new StringBuilder();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params == null) {
			return nvps;
		}
		for (Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				nvps.add(new BasicNameValuePair(key, ""));
				paramsLogBuilder.append("[" + key + "-]");
				continue;
			}
			if (String[].class.isInstance(value)) {
				String[] strArray = (String[]) value;
				for (String str : strArray) {
					nvps.add(new BasicNameValuePair(key, str));
				}
				paramsLogBuilder.append("[" + key + "- "
						+ Arrays.asList(strArray) + "]");
				continue;
			}
			nvps.add(new BasicNameValuePair(key, value.toString()));
		}
		return nvps;
	}

	private static void setTimeouts(DefaultHttpClient httpclient,
			HttpPost httpPost, Integer time) {
		if(time == null || time <= 0){
			time = 10000;
		}
		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), time);
		HttpConnectionParams.setSoTimeout(httpPost.getParams(), time);
	}

	private static String readResponseBody(DefaultHttpClient httpclient,
                                           HttpPost httpPost) throws Exception {
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			byte[] bytes = EntityUtils.toByteArray(entity);
			return new String(bytes, "UTF-8");
		}
		return "";
	}

    public static void main(String[] args) {
        String url = "http://localhost:8805/file/uploadFile";
        String path = "D:\\share\\UltraEdit_SC.zip";
        try {
            Long currentTime = System.currentTimeMillis();
            System.out.println(postFile(url, path));
            System.out.println("---------"+(System.currentTimeMillis()-currentTime));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
