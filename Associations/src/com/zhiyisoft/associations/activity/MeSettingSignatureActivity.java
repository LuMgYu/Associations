package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingSignatureActivity extends BaseActivity {
	private EditText et_signature;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "保存");
	}

	@Override
	public String setCenterTitle() {
		return "个性签名";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_signature;
	}

	@Override
	public void initView() {
		et_signature = (EditText) findViewById(R.id.et_signature);

	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

	}

}