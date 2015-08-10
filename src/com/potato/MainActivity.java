package com.potato;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
	private SurfaceView sfv;
	private GLSurfaceView GLsfv;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams param;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
//        initialize(new MyGame(), cfg);
        
        param = new WindowManager.LayoutParams();
		param.type = WindowManager.LayoutParams.TYPE_PHONE; // 系统提示类型,重要
		param.format = PixelFormat.RGBA_8888;
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_FULLSCREEN;
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制
		param.flags = param.flags | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON; // 唤醒者
		param.alpha = 0.0f;
		param.gravity = Gravity.LEFT | Gravity.TOP;
		param.x = 0;
		param.y = 0;
		param.width = WindowManager.LayoutParams.MATCH_PARENT;
		param.height = WindowManager.LayoutParams.MATCH_PARENT;
		
        sfv = (SurfaceView) initializeForView(new MyGame(), cfg);
        sfv.getHolder().setFormat(PixelFormat.RGBA_8888);
		
        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		if(mWindowManager != null)
		{
			Gdx.app.log("WindowManger", "addView");
			//类似setContentView(),需要权限<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
			mWindowManager.addView(sfv, param);
		}
    }
	@Override
	protected void onPause() {
		if(mWindowManager != null){
			Gdx.app.log("MainActivity", "onPause");
			mWindowManager.removeView(sfv);
		}
		super.onPause();
	}
}