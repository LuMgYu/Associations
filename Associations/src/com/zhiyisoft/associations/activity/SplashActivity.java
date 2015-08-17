package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午11:16:31 类描述：这个类是实现
 *
 */

public class SplashActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_splash);
		Handler x = new Handler();
		x.postDelayed(new splashhandler(), 2000);

	}

	class splashhandler implements Runnable {

		public void run() {
			startActivity(new Intent(getApplication(), MainActivity.class));
			SplashActivity.this.finish();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setCenterTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
