package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.GuideAdapter;
import com.zhiyisoft.associations.config.Config;

/**
 * author：qiuchunjia time：上午11:16:31 类描述：这个类是实现
 *
 */

public class SplashActivity extends BaseActivity {

	private ViewPager mViewpager;
	private GuideAdapter mAdapter;
	int[] ImageRes = new int[] { R.drawable.guide_01, R.drawable.guide_02,
			R.drawable.guide_03 };
	List<View> mViews = new ArrayList<View>();
	boolean mNotGuide = true;

	@Override
	public boolean checkTheUser() {
		return true;
	}

	class splashhandler implements Runnable {

		public void run() {
			startActivity(new Intent(getApplication(), MainActivity.class));
			SplashActivity.this.finish();
		}
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return null;
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_splash;
	}

	@Override
	public void initView() {
		mNotGuide = getIsGuide();
		if (mNotGuide) {
			mViewpager = (ViewPager) findViewById(R.id.SplashViewpager);
			for (int i = 0; i < ImageRes.length; i++) {
				ImageView view = new ImageView(this);
				view.setLayoutParams(new ViewGroup.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				view.setImageResource(ImageRes[i]);
				if (i == 2) {
					view.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							mApp.startActivity(SplashActivity.this,
									MainActivity.class, null,
									Intent.FLAG_ACTIVITY_CLEAR_TOP);
							saveToSharePreference(false);
							SplashActivity.this.finish();

						}
					});
				}
				mViews.add(view);
			}
			mAdapter = new GuideAdapter(mViews);
			mViewpager.setAdapter(mAdapter);
		} else {
			Handler x = new Handler();
			x.postDelayed(new splashhandler(), 2000);
		}
	}

	/**
	 * 保存到
	 * 
	 * @param notguide
	 */
	private void saveToSharePreference(boolean notguide) {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(Config.ISNOT_GUIDE, notguide);
		editor.commit();
	};

	/**
	 * 获取是否导航过
	 * 
	 * @return
	 */
	private boolean getIsGuide() {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, MODE_PRIVATE);
		boolean flag = preferences.getBoolean(Config.ISNOT_GUIDE, true);
		return flag;

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
