package com.zhiyisoft.associations.activity;

import java.io.File;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：下午3:22:28 类描述：这个类是实现
 *
 */

public class AssociationTopicDetailPhotoActivity extends BaseActivity {
	private ImageView iv_backpress;
	private TextView tv_number;
	private ImageView iv_more;
	private SmartImageView iv_photo;
	private Button btn_save_to_phone;
	private Button btn_cancle;
	private LinearLayout ll_fill_twobutton;

	private String mPhotoUrl;

	@Override
	public String setCenterTitle() {
		return null;
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mPhotoUrl = (String) bundle.get(Config.PHOTOURL);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_topic_photo_detail;
	}

	@Override
	public void initView() {
		iv_backpress = (ImageView) findViewById(R.id.iv_backpress);
		tv_number = (TextView) findViewById(R.id.tv_number);
		iv_more = (ImageView) findViewById(R.id.iv_more);
		iv_photo = (SmartImageView) findViewById(R.id.iv_photo);
		btn_save_to_phone = (Button) findViewById(R.id.btn_save_to_phone);
		btn_cancle = (Button) findViewById(R.id.btn_cancle);
		ll_fill_twobutton = (LinearLayout) findViewById(R.id.ll_fill_twobutton);
		ll_fill_twobutton.setVisibility(View.GONE);
		mApp.displayImage(mPhotoUrl, iv_photo);
	}

	@Override
	public void initListener() {
		iv_backpress.setOnClickListener(this);
		iv_more.setOnClickListener(this);
		btn_save_to_phone.setOnClickListener(this);
		btn_cancle.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_backpress:
			onBackPressed();
			break;

		case R.id.iv_more:
			changeViewStatus(ll_fill_twobutton);
			break;
		case R.id.btn_save_to_phone:
			// TODO
			File file = mApp.getFilesDir();
			if (file != null) {
				ToastUtils.showToast("保存成功到" + file.toString());
			}
			break;
		case R.id.btn_cancle:
			changeViewStatus(ll_fill_twobutton);
			break;
		}

	}

	private void changeViewStatus(View view) {
		if (view.getVisibility() == View.VISIBLE) {
			view.setVisibility(View.GONE);
			Animation animation = new TranslateAnimation(0, 0, 0, 150);
			animation.setDuration(500);
			view.setAnimation(animation);
			return;
		}
		view.setVisibility(View.VISIBLE);
		Animation animation = new TranslateAnimation(0, 0, 150, 0);
		animation.setDuration(500);
		view.setAnimation(animation);
	}
}
