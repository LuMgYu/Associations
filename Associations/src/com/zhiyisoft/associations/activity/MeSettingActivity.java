package com.zhiyisoft.associations.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingActivity extends BaseActivity {
	private ImageView iv_openMessage;
	private RelativeLayout setting_rl_cache;
	private TextView setting_tv_cache;
	private RelativeLayout setting_rl_article;
	private RelativeLayout setting_rl_edition;
	private TextView setting_tv_edition;
	private Button btn_setting_quit;

	@Override
	public String setCenterTitle() {
		return "软件设置";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting;
	}

	@Override
	public void initView() {
		iv_openMessage = (ImageView) findViewById(R.id.iv_openMessage);
		setting_rl_cache = (RelativeLayout) findViewById(R.id.setting_rl_cache);
		setting_tv_cache = (TextView) findViewById(R.id.setting_tv_cache);
		setting_rl_article = (RelativeLayout) findViewById(R.id.setting_rl_article);
		setting_rl_edition = (RelativeLayout) findViewById(R.id.setting_rl_edition);
		setting_tv_edition = (TextView) findViewById(R.id.setting_tv_edition);
		btn_setting_quit = (Button) findViewById(R.id.btn_setting_quit);
		setting_tv_cache.setText(mApp.getUsedCache() + "M");
		if (isOpenedMessage()) {
			iv_openMessage.setImageResource(R.drawable.on);
		} else {
			iv_openMessage.setImageResource(R.drawable.off);
		}
	}

	@Override
	public void initListener() {
		iv_openMessage.setOnClickListener(this);
		setting_rl_cache.setOnClickListener(this);
		setting_rl_article.setOnClickListener(this);
		setting_rl_edition.setOnClickListener(this);
		btn_setting_quit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_openMessage:
			if (isOpenedMessage()) {
				iv_openMessage.setImageResource(R.drawable.off);
				saveToSharePreference(false);
			} else {
				iv_openMessage.setImageResource(R.drawable.on);
				saveToSharePreference(true);
			}
			break;
		case R.id.setting_rl_cache:
			if (mApp.cleanCache()) {
				ToastUtils.showToast("清除缓存成功！");
				setting_tv_cache.setText("0M");
			}
			break;
		case R.id.setting_rl_article:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, MeUseAgreementActivity.class, data2);
			break;
		case R.id.setting_rl_edition:
			ToastUtils.showToast("当前为最新版本");
			break;
		case R.id.btn_setting_quit:
			quitLogin();
			onBackPressed();
			break;
		}

	}

	/**
	 * 是否关闭了message
	 * 
	 * @return
	 */
	private boolean isOpenedMessage() {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, Activity.MODE_PRIVATE);
		return preferences.getBoolean(Config.OPEN_MESSAGE, true);
	}

	/**
	 * @param user
	 */
	private void saveToSharePreference(boolean openMessage) {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(Config.OPEN_MESSAGE, openMessage);
		editor.commit();
	}
}
