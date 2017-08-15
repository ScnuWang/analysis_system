package cn.geekview.analysisSystem.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.geekview.analysisSystem.entity.model.TdreamProduct;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpUtil {

	@SuppressWarnings("rawtypes")
	public static HttpResponse post(String url, Map<String, Object> mapParams) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		// 这里只能转换成String类型的键值对
		for (Map.Entry map : mapParams.entrySet()) {
			formparams.add(new BasicNameValuePair(map.getKey().toString(), map.getValue().toString()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		System.out.println(entity);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		return response;
	}

	/**
	 * 由于微信客户端不支持自动为图片的地址添加http通信协议，为了统一，将没有通信协议的图片地址添加http协议
	 */
	public static void addHttpProtocol(List<TdreamProduct> list){
		if(list!=null&&list.size()>0){
			for (TdreamProduct tdreamProduct : list) {
				String imageurl = tdreamProduct.getProductImage();
				if(!imageurl.startsWith("http")){
					tdreamProduct.setProductImage("http:"+imageurl);
				}
			}
		}
		
	}
	
	public static void addHttpProtocol(TdreamProduct tdreamProduct){
		if(tdreamProduct!=null){
			String imageurl = tdreamProduct.getProductImage();
			if(!imageurl.startsWith("http")){
				tdreamProduct.setProductImage("http:"+imageurl);
			}
		}
	}
}
