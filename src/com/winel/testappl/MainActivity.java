package com.winel.testappl;

import java.io.InputStream;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private WeaHandler weahandler;
	private Spinner cityselect;
	private ImageView imageday0;
	private ImageView imagenight0;
	private ImageView imageday1;
	private ImageView imagenight1;
	private ImageView imageday2;
	private ImageView imagenight2;
	private ImageView imageday3;
	private ImageView imagenight3;
	private TextView textcityname;
	private TextView texttempinstant;
	private TextView textpm25view;
	private TextView textday0;
	private TextView textday1;
	private TextView textday2;
	private TextView textday3;
	private TextView texttemp0;
	private TextView texttemp1;
	private TextView texttemp2;
	private TextView texttemp3;
	private TextView textweather0;
	private TextView textweather1;
	private TextView textweather2;
	private TextView textweather3;
	private TextView textwind0;
	private TextView textwind1;
	private TextView textwind2;
	private TextView textwind3;
	private RelativeLayout layoutinstant;
	private String instantActString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cityselect = (Spinner)findViewById(R.id.cityselect);
		cityselect.setOnItemSelectedListener(new WeaItemSelect());
		textcityname = (TextView)findViewById(R.id.cityname);
		texttempinstant = (TextView)findViewById(R.id.tempinstant);
		textpm25view = (TextView)findViewById(R.id.pm25view);
		imageday0 = (ImageView)findViewById(R.id.imageday0);
		imageday1 = (ImageView)findViewById(R.id.imageday1);
		imageday2 = (ImageView)findViewById(R.id.imageday2);
		imageday3 = (ImageView)findViewById(R.id.imageday3);
		imagenight0 = (ImageView)findViewById(R.id.imagenight0);
		imagenight1 = (ImageView)findViewById(R.id.imagenight1);
		imagenight2 = (ImageView)findViewById(R.id.imagenight2);
		imagenight3 = (ImageView)findViewById(R.id.imagenight3);
		textday0 = (TextView)findViewById(R.id.textday0);
		textday1 = (TextView)findViewById(R.id.textday1);
		textday2 = (TextView)findViewById(R.id.textday2);
		textday3 = (TextView)findViewById(R.id.textday3);
		texttemp0 = (TextView)findViewById(R.id.texttemp0);
		texttemp1 = (TextView)findViewById(R.id.texttemp1);
		texttemp2 = (TextView)findViewById(R.id.texttemp2);
		texttemp3 = (TextView)findViewById(R.id.texttemp3);
		textweather0 = (TextView)findViewById(R.id.textweather0);
		textweather1 = (TextView)findViewById(R.id.textweather1);
		textweather2 = (TextView)findViewById(R.id.textweather2);
		textweather3 = (TextView)findViewById(R.id.textweather3);
		textwind0 = (TextView)findViewById(R.id.textwind0);
		textwind1 = (TextView)findViewById(R.id.textwind1);
		textwind2 = (TextView)findViewById(R.id.textwind2);
		textwind3 = (TextView)findViewById(R.id.textwind3);
		layoutinstant = (RelativeLayout)findViewById(R.id.layoutinstant);
		layoutinstant.setOnClickListener(new InstantClickListener());
		weahandler = new WeaHandler();
		/*weabutton = (Button)findViewById(R.id.weabutton);
		weatext = (TextView)findViewById(R.id.weatext);
		weabutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WeatherThread weathread = new WeatherThread("杭州", "json", weahandler);
				weathread.start();
			}
		});*/
	}
	
	class InstantClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent instIntent = new Intent();
			if(instantActString.isEmpty() == false){
				instIntent.putExtra("Instant", instantActString);
				instIntent.setClass(MainActivity.this, InstantActivity.class);
				startActivity(instIntent);
			}
		}
		
	}
	
	class WeaItemSelect implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			//arg2-position
			String sInfo = arg0.getItemAtPosition(arg2).toString();  
            Toast.makeText(getApplicationContext(), sInfo, Toast.LENGTH_LONG).show(); 
            WeatherThread weathread = new WeatherThread(sInfo, "json", weahandler);
			weathread.start();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class WeaHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String resultString;
			//InputStream resultStream;去除转换，放在消息传递之前转换。
			Bitmap resultbmp;
			switch (msg.what) {
			case 1:
				resultString = (String)msg.obj;
				textcityname.setText(resultString);
				break;
			case 2:
				resultString = (String)msg.obj;
				texttempinstant.setText(resultString);				
				break;
			case 3:
				resultString = (String)msg.obj;
				textpm25view.setText(resultString);
				break;
			case 4:
				resultbmp = (Bitmap)msg.obj; 
				imageday0.setImageBitmap(resultbmp);
				System.out.println(4);
				break;
			case 5:
				resultbmp = (Bitmap)msg.obj;
				imageday1.setImageBitmap(resultbmp);
				System.out.println(5);
				break;
			case 6:
				resultbmp = (Bitmap)msg.obj;
				imageday2.setImageBitmap(resultbmp);
				System.out.println(6);
				break;
			case 7:
				resultbmp = (Bitmap)msg.obj; 
				imageday3.setImageBitmap(resultbmp);	
				System.out.println(7);
				break;
			case 8:
				resultbmp = (Bitmap)msg.obj;
				imagenight0.setImageBitmap(resultbmp);
				System.out.println(8);
				break;
			case 9:
				resultbmp = (Bitmap)msg.obj;
				imagenight1.setImageBitmap(resultbmp);
				System.out.println(9);
				break;
			case 10:
				resultbmp = (Bitmap)msg.obj; 
				imagenight2.setImageBitmap(resultbmp);	
				System.out.println(10);
				break;
			case 11:
				resultbmp = (Bitmap)msg.obj;
				imagenight3.setImageBitmap(resultbmp);
				System.out.println(11);
				break;
			case 12:
				resultString = (String)msg.obj;
				textday0.setText(resultString);
				break;
			case 13:
				resultString = (String)msg.obj;
				textday1.setText(resultString);	
				break;
			case 14:
				resultString = (String)msg.obj;
				textday2.setText(resultString);
				break;
			case 15:
				resultString = (String)msg.obj;
				textday3.setText(resultString);
				break;
			case 16:
				resultString = (String)msg.obj;
				texttemp0.setText(resultString);	
				break;
			case 17:
				resultString = (String)msg.obj;
				texttemp1.setText(resultString);	
				break;
			case 18:
				resultString = (String)msg.obj;
				texttemp2.setText(resultString);		
				break;
			case 19:
				resultString = (String)msg.obj;
				texttemp3.setText(resultString);	
				break;
			case 20:
				resultString = (String)msg.obj;
				textweather0.setText(resultString);	
				break;
			case 21:
				resultString = (String)msg.obj;
				textweather1.setText(resultString);		
				break;
			case 22:
				resultString = (String)msg.obj;
				textweather2.setText(resultString);	
				break;
			case 23:
				resultString = (String)msg.obj;
				textweather3.setText(resultString);		
				break;
			case 24:
				resultString = (String)msg.obj;
				textwind0.setText(resultString);	
				break;
			case 25:
				resultString = (String)msg.obj;
				textwind1.setText(resultString);	
				break;
			case 26:
				resultString = (String)msg.obj;
				textwind2.setText(resultString);		
				break;
			case 27:
				resultString = (String)msg.obj;
				textwind3.setText(resultString);	
				break;
			case 28:
				resultString = (String)msg.obj;
				instantActString = resultString.toString();
				break;
			default:
				break;
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
