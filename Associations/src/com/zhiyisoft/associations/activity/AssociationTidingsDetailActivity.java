package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelTiding;
import com.zhiyisoft.associations.util.DateUtil;

/**
 * author：qiuchunjia time：下午3:05:26 类描述：这个类是实现
 *
 */

public class AssociationTidingsDetailActivity extends BaseActivity {
	private TextView tv_tinding_title;
	private TextView tv_name;
	private TextView tv_date;
	private TextView tv_content;
	private ModelTiding mTinds;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "新闻详情";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mTinds = (ModelTiding) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_tidings_detail;
	}

	@Override
	public void initView() {
		tv_tinding_title = (TextView) findViewById(R.id.tv_tinding_title);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_content = (TextView) findViewById(R.id.tv_content);
		initData();

	}

	private void initData() {
		if (mTinds != null) {
			tv_tinding_title.setText(mTinds.getTitle());
			tv_name.setText(mTinds.getUser().getUname());
			tv_date.setText(DateUtil.stamp2humanDate(mTinds.getCtime()));
			tv_content.setText(mTinds.getContent());
		}

	}

	@Override
	public void initListener() {

	}

}
