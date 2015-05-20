package com.winel.testappl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.security.auth.PrivateCredentialPermission;








import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class WeatherThread extends Thread{
	String baseUrl = "http://api.map.baidu.com/telematics/v3/weather";
	String modeType;
	String city;
	Handler weaHandler;
	public WeatherThread(String city, String modeType, Handler weaHandler){
		try {
			this.city = URLEncoder.encode(city.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.modeType = modeType;
		this.weaHandler = weaHandler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String strUrl = baseUrl + "?location=" + city + "&output=" + modeType + "&ak=S8jhXHo4cIXp0ytua1rQbcf3";
		HttpGet weaHttpGet = new HttpGet(strUrl);
		HttpClient weaHttpClient = new DefaultHttpClient();
		try {
			HttpResponse weaHttpRes = weaHttpClient.execute(weaHttpGet);
			if(weaHttpRes.getStatusLine().getStatusCode() == 200){
				String result = EntityUtils.toString(weaHttpRes.getEntity());
				//
				try {
					JSONObject weaJson = new JSONObject(result);
					String status = weaJson.getString("status");
					if(status.equalsIgnoreCase("success")){
						JSONObject weaJsonObject  = weaJson.getJSONArray("results").getJSONObject(0);
						String cityname = weaJsonObject.getString("currentCity");
						String pm25 = weaJsonObject.getString("pm25");
						ImgStringSend(cityname, 1);
						ImgStringSend(pm25, 3);
						//
						JSONArray indexJsonArray = weaJsonObject.getJSONArray("index");
						ImgStringSend(indexJsonArray.toString(), 28);
						//
						JSONArray dataJsonArray = weaJsonObject.getJSONArray("weather_data");
						for(int i = 0; i < dataJsonArray.length();i++){
							//日期
							String dayString = dataJsonArray.getJSONObject(i).getString("date");
							if(i == 0){
								int startidx = dayString.indexOf("：");
								int endidx = dayString.indexOf(")"); 
								//当天实时温度在天气列表中
								String tempinstantstr = dayString.substring(startidx + 1,endidx);
								dayString = dayString.substring(0, 2);
								ImgStringSend(tempinstantstr, 2);
							}
							ImgStringSend(dayString, 12 + i);
							//温度
							String tempString = dataJsonArray.getJSONObject(i).getString("temperature");
							ImgStringSend(tempString, 16 + i);
							//天气
							String weaString = dataJsonArray.getJSONObject(i).getString("weather");
							ImgStringSend(weaString, 20 + i);
							//风
							String windString = dataJsonArray.getJSONObject(i).getString("wind");
							ImgStringSend(windString, 24 + i);
							//天气图片
							String dayimgurl = dataJsonArray.getJSONObject(i).getString("dayPictureUrl");
							String nightimgurl = dataJsonArray.getJSONObject(i).getString("nightPictureUrl");
							ImgStreamSend(dayimgurl, 4 + i);
							ImgStreamSend(nightimgurl, 8 + i);
						}

						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//
				
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void ImgStringSend(String resultstr, Integer argidx){
		Message msg = weaHandler.obtainMessage();
		msg.what = argidx;
		msg.obj = resultstr;
		weaHandler.sendMessage(msg);
	}
	private void ImgStreamSend(String urlstr, Integer argidx){
		HttpGet imgGet = new HttpGet(urlstr);
		HttpClient imgClient = new DefaultHttpClient();
		try {
			HttpResponse imgResponse = imgClient.execute(imgGet);
			if(imgResponse.getStatusLine().getStatusCode() == 200){
				InputStream imgStream = imgResponse.getEntity().getContent();
				Message imgmsg = weaHandler.obtainMessage();
				imgmsg.what = argidx;
				//imgmsg.obj = imgStream;修改此处，将流转换好再传递消息，否则多消息，流转换错误
				imgmsg.obj = BitmapFactory.decodeStream(imgStream);
				weaHandler.sendMessage(imgmsg);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}