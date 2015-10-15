package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.util.DateUtil;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class NotifyDetailContentActivity extends BaseActivity {
	private TextView tv_content_title;
	private TextView tv_content_date;
	private TextView tv_content_master;
	private TextView tv_content;
	private ModelNotify mNotify;

	@Override
	public String setCenterTitle() {
		return "查看通知";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mNotify = (ModelNotify) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_notify_detail_content;
	}

	@Override
	public void initView() {
		tv_content_title = (TextView) findViewById(R.id.tv_content_title);
		tv_content_date = (TextView) findViewById(R.id.tv_content_date);
		tv_content_master = (TextView) findViewById(R.id.tv_content_master);
		tv_content = (TextView) findViewById(R.id.tv_content);
		initData();
	}

	private void initData() {
		if (mNotify != null) {
			tv_content_title.setText("系统通知");
			tv_content_date.setText(DateUtil.strTodate(mNotify.getcTime()));
			tv_content_master.setText("系统管理员");
			tv_content.setText(mNotify.getContent());
		}
	}

	@Override
	public void initListener() {
		// tv_notify_detail.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_notify_detail:
			break;
		}

	}
}
