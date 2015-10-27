package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.NotifyIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.util.DateUtil;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class NotifyDetailActivity extends BaseActivity {
	private ImageView iv_notify;
	private TextView tv_notify_date;
	private TextView tv_notify_content;
	private TextView tv_notify_detail;
	private ModelNotify mNotify;

	@Override
	public String setCenterTitle() {
		return "详情";
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
		return R.layout.activity_notify_detail;
	}

	@Override
	public void initView() {
		iv_notify = (ImageView) findViewById(R.id.iv_notify);
		tv_notify_date = (TextView) findViewById(R.id.tv_notify_date);
		tv_notify_content = (TextView) findViewById(R.id.tv_notify_content);
		tv_notify_detail = (TextView) findViewById(R.id.tv_notify_detail);
		setIsRread(mNotify);
		initData();
	}

	private void initData() {
		if (mNotify != null) {
			tv_notify_date.setText(DateUtil.strTodate(mNotify.getcTime()));
			tv_notify_content.setText(mNotify.getContent());
		}
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
			data.putSerializable(Config.SEND_ACTIVITY_DATA, mNotify);
			mApp.startActivity(this, NotifyDetailContentActivity.class, data);
			break;
		}

	}

	/**
	 * 设置已经读了
	 * 
	 * @param msg
	 */
	private void setIsRread(final ModelNotify notify) {
		final NotifyIm notifyIm = mApp.getNotifyIm();
		if (notify != null) {
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					notifyIm.setRead(notify);
				}
			});
		}
	}
}
