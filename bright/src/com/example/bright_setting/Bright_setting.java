package com.example.bright_setting;



import android.Manifest.permission;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Bright_setting extends Activity {

	private static final String TAG = "BrightnessActivity";
    private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bright_setting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bright_setting, menu);
		return true;
	}

	public void onclick(View v) {
        try {
            // 取得螢幕亮度
            int brightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            String msg = "目前亮度 - " + brightness;
            //Log.d(BrightnessActivity.TAG, msg);
            Log.d("bright",msg);
            this.insert2Tv(msg);
            switch (v.getId()) {
            case R.id.plus:
                brightness += 30;
                break;
            case R.id.minus:
                brightness -= 30;
                break;
            }
            // 亮度範圍為 0 - 255
            brightness = Math.min(brightness, 255);
            brightness = Math.max(brightness, 0);
            // 設定螢幕亮度
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, brightness);
            msg = "調整後亮度 - " + brightness;
            //Log.d(BrightnessActivity.TAG, msg);
            Log.d("bright",msg);
            this.insert2Tv(msg);
        }
        catch (SettingNotFoundException e) {
            //Log.e(BrightnessActivity.TAG, e.getMessage(), e);
        	Log.e("bright", e.getMessage(), e);
            this.insert2Tv(e.getMessage());
        }
    }

    private void insert2Tv(String msg) {
        if (this.tv == null) {
            this.tv = (TextView) this.findViewById(R.id.tv);
        }
        this.tv.setText(msg + "\n" + this.tv.getText().toString());
    }
    
}

