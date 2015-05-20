package com.winel.testappl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InstantActivity extends Activity{
	private TextView title0;
	private TextView title1;
	private TextView title2;
	private TextView title3;
	private TextView title4;
	private TextView title5;
	private TextView zs0;
	private TextView zs1;
	private TextView zs2;
	private TextView zs3;
	private TextView zs4;
	private TextView zs5;
	private TextView tipt0;
	private TextView tipt1;
	private TextView tipt2;
	private TextView tipt3;
	private TextView tipt4;
	private TextView tipt5;
	private TextView des0;
	private TextView des1;
	private TextView des2;
	private TextView des3;
	private TextView des4;
	private TextView des5;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instant);
		
		title0 = (TextView)findViewById(R.id.title0);
		title1 = (TextView)findViewById(R.id.title1);
		title2 = (TextView)findViewById(R.id.title2);
		title3 = (TextView)findViewById(R.id.title3);
		title4 = (TextView)findViewById(R.id.title4);
		title5 = (TextView)findViewById(R.id.title5);
		zs0 = (TextView)findViewById(R.id.zs0);
		zs1 = (TextView)findViewById(R.id.zs1);
		zs2 = (TextView)findViewById(R.id.zs2);
		zs3 = (TextView)findViewById(R.id.zs3);
		zs4 = (TextView)findViewById(R.id.zs4);
		zs5 = (TextView)findViewById(R.id.zs5);
		tipt0 = (TextView)findViewById(R.id.tipt0);
		tipt1 = (TextView)findViewById(R.id.tipt1);
		tipt2 = (TextView)findViewById(R.id.tipt2);
		tipt3 = (TextView)findViewById(R.id.tipt3);
		tipt4 = (TextView)findViewById(R.id.tipt4);
		tipt5 = (TextView)findViewById(R.id.tipt5);
		des0 = (TextView)findViewById(R.id.des0);
		des1 = (TextView)findViewById(R.id.des1);
		des2 = (TextView)findViewById(R.id.des2);
		des3 = (TextView)findViewById(R.id.des3);
		des4 = (TextView)findViewById(R.id.des4);
		des5 = (TextView)findViewById(R.id.des5);
		
		Intent selfIntent = getIntent();
		String jsonString = selfIntent.getStringExtra("Instant");
		try {
			JSONArray instantArray = new JSONArray(jsonString);
			for(int i = 0;i < instantArray.length(); i++){
				String titleString = instantArray.getJSONObject(i).getString("title");
				String zsString = instantArray.getJSONObject(i).getString("zs");
				String tiptString = instantArray.getJSONObject(i).getString("tipt");
				String desString = instantArray.getJSONObject(i).getString("des");
				switch (i) {
				case 0:
					title0.setText(titleString);
					zs0.setText(zsString);
					tipt0.setText(tiptString);
					des0.setText(desString);
					break;
				case 1:
					title1.setText(titleString);
					zs1.setText(zsString);
					tipt1.setText(tiptString);
					des1.setText(desString);
					break;
				case 2:
					title2.setText(titleString);
					zs2.setText(zsString);
					tipt2.setText(tiptString);
					des2.setText(desString);
					break;
				case 3:
					title3.setText(titleString);
					zs3.setText(zsString);
					tipt3.setText(tiptString);
					des3.setText(desString);
					break;
				case 4:
					title4.setText(titleString);
					zs4.setText(zsString);
					tipt4.setText(tiptString);
					des4.setText(desString);
					break;
				case 5:
					title5.setText(titleString);
					zs5.setText(zsString);
					tipt5.setText(tiptString);
					des5.setText(desString);
					break;
				default:
					break;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
