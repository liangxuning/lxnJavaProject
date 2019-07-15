package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String sendPutHttp(String url, String json, Header... headers) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url);
		String response = "";
		try {
			StringEntity s = new StringEntity(json);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			put.setEntity(s);
			logger.info("请求url：{}", url);
			logger.info("请求参数：{}", json);
			if (headers != null && headers.length > 0) {
				put.setHeaders(headers);
			}
			HttpResponse res = httpclient.execute(put);
			if (res.getStatusLine().getStatusCode() >= 200 && res.getStatusLine().getStatusCode() <= 299) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			} else {
				throw new RuntimeException("response status code not 2xx," + res);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
		} finally {
			if (put != null) {
				put.abort();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

	public static String sendPutHttpText(String url, String json) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url);
		String response = "";
		try {
			StringEntity s = new StringEntity(json);
			s.setContentEncoding("UTF-8");
			s.setContentType("text/plain");
			put.setEntity(s);
			logger.debug("请求url：{}", url);
			logger.debug("请求参数：{}", json);
			HttpResponse res = httpclient.execute(put);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
			throw new Exception(e);
		}
		return response;
	}

	public static String sendPostHttp(String url, String json, Map<String, String> headers) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = "";
		try {
			StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
			s.setContentType("application/json;charset=UTF-8");
			post.setHeader("Content-Type", "application/json;");
			if (headers != null && !headers.isEmpty()) {
				for (String key : headers.keySet()) {
					post.setHeader(key, headers.get(key));
				}
			}
			post.setEntity(s);
			logger.info("请求url：{}", url);
			logger.info("请求参数：{}", json);
			HttpResponse res = httpclient.execute(post);
			// System.out.println(EntityUtils.toString(res.getEntity(),"utf-8"));
			response = EntityUtils.toString(res.getEntity(), "utf-8");
			System.out.println(response);
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
			throw new Exception(e);
		} finally {
			if (post != null) {
				post.abort();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

	public static String sendPostHttp(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = "";
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			logger.debug("请求url：{}", url);
			logger.debug("请求参数：{}", params);
			HttpResponse res = httpclient.execute(post);
			response = EntityUtils.toString(res.getEntity(), "utf-8");
			logger.debug(response);
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
			throw new Exception(e);
		} finally {
			if (post != null) {
				post.abort();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}
	
	public static String sendPutHttp(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url);
		String response = "";
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
			}
			put.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			logger.debug("请求url：{}", url);
			logger.debug("请求参数：{}", params);
			HttpResponse res = httpclient.execute(put);
			response = EntityUtils.toString(res.getEntity(), "utf-8");
			logger.debug(response);
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
			throw new Exception(e);
		} finally {
			if (put != null) {
				put.abort();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

	public static String post(String url, String json, Header... headers) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = "";
		try {
			StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
			post.setEntity(s);
			logger.info("请求url：{}", url);
			logger.info("请求参数：{}", json);
			if (headers != null && headers.length > 0) {
				post.setHeaders(headers);
			}
			HttpResponse res = httpclient.execute(post);
			if (res.getStatusLine().getStatusCode() >= 200 && res.getStatusLine().getStatusCode() <= 299) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			} else {
				throw new RuntimeException("response status code not 2xx," + res);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

	public static String patch(String url, String json, Header... headers) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPatch post = new HttpPatch(url);
		String response = "";
		try {
			StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
			post.setEntity(s);
			logger.info("请求url：{}", url);
			logger.info("请求参数：{}", json);
			if (headers != null && headers.length > 0) {
				post.setHeaders(headers);
			}
			HttpResponse res = httpclient.execute(post);
			if (res.getStatusLine().getStatusCode() >= 200 && res.getStatusLine().getStatusCode() <= 299) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			} else {
				throw new RuntimeException("response status code not 2xx," + res);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}
	
	public static String delete(String url, Header... headers) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpDelete delete = new HttpDelete(url);
		String response = "";
		try {
			logger.info("请求url：{}", url);
			if (headers != null && headers.length > 0) {
				delete.setHeaders(headers);
			}
			HttpResponse res = httpclient.execute(delete);
			if (res.getStatusLine().getStatusCode() >= 200 && res.getStatusLine().getStatusCode() <= 299) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			} else {
				throw new RuntimeException("response status code not 2xx," + res);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

	public static String sendPostHttp(String url, File file) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = "";
		try {
			post.setEntity(new FileEntity(file));
			HttpResponse res = httpclient.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				response = EntityUtils.toString(res.getEntity(), "utf-8");
				logger.debug(response);
			}
		} catch (Exception e) {
			logger.error("请求发生异常：", e);
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return response;
	}

//	public static String sendPostHttp(String url, MultipartFile file, String fileName) throws Exception {
//		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url);
//		String response = "";
//		try {
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//			builder.addBinaryBody(fileName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());// 文件流
//			post.setEntity(builder.build());
//			HttpResponse res = httpclient.execute(post);
//			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				response = EntityUtils.toString(res.getEntity(), "utf-8");
//				logger.debug(response);
//			}
//		} catch (Exception e) {
//			logger.error("请求发生异常：", e);
//		} finally {
//			if (httpclient != null) {
//				httpclient.close();
//			}
//		}
//		return response;
//	}

	public static String sendGetHttp(String url, Map<String, String> headers) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		String response = "";
		logger.debug("请求url：{}", url);
//		if (headers != null && headers.length > 0) {
//			get.setHeaders(headers);
//		}
		if (headers != null && !headers.isEmpty()) {
			for (String key : headers.keySet()) {
				get.setHeader(key, headers.get(key));
			}
		}
		HttpResponse res = httpclient.execute(get);
		response = EntityUtils.toString(res.getEntity(), "utf-8");
		logger.debug(response);
		return response;
	}
}
