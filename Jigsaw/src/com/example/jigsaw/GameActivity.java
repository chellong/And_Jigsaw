package com.example.jigsaw;

import com.example.jigsaw.view.GameView;

import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	/**
	 * 游戏进度数据要传给GameView
	 */
	private GameView mView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * setContentView(R.layout.activity_main);
		 */
		
		/*
		 *	隐藏状态栏 
		 */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		/*
		 *  隐去程序标题栏
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		/*
		 * 加载游戏进度给gameview
		 */
		
		mView = new GameView(this);
		loadGameProgress();
		setContentView(mView);
	}

	private void loadGameProgress() {
		
	}

}
