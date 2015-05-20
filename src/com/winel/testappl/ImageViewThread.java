package com.winel.testappl;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageViewThread extends Thread{
	ImageView imagetemp;
	String urlString;
	Handler imgHandler;
	public ImageViewThread(ImageView imagetemp, String urlString, Handler imgHandler){
		this.imagetemp = imagetemp;
		this.urlString = urlString;
		this.imgHandler = imgHandler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpGet imgHttpGet = new HttpGet(urlString);
		HttpClient imgHttpClient = new DefaultHttpClient();
		try {
			HttpResponse imgHttpResponse = imgHttpClient.execute(imgHttpGet);
			if(imgHttpResponse.getStatusLine().getStatusCode() == 200){				
				Message msg = imgHandler.obtainMessage();
				msg.what = 2;
				msg.obj = imgHttpResponse.getEntity().getContent();
				imgHandler.sendMessage(msg);
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
