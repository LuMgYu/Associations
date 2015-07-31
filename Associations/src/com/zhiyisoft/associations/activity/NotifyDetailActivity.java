package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class NotifyDetailActivity extends BaseActivity {
	private ImageView iv_notify;
	private TextView tv_notify_date;
	private TextView tv_notify_content;
	private TextView tv_notify_detail;

	@Override
	public String setCenterTitle() {
		return "详情";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_notify_detail;
	}

	// private ImageView iv_notify;
	// private TextView tv_notify_date;
	// private TextView tv_notify_content;
	// private TextView tv_notify_detail;

	@Override
	public void initView() {
		iv_notify = (ImageView) findViewById(R.id.iv_notify);
		tv_notify_date = (TextView) findViewById(R.id.tv_notify_date);
		tv_notify_content = (TextView) findViewById(R.id.tv_notify_content);
		tv_notify_detail = (TextView) findViewById(R.id.tv_notify_detail);
	}

	@Override
	public void initListener() {
		tv_notify_detail.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_notify_detail:
			Bundle data = new Bundle();
			mApp.startActivity(this, NotifyDetailContentActivity.class, data);
			break;
		}

	}
}
