package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.util.Anim;

/**
 * author：qiuchunjia time：下午2:11:02 类描述：这个类是实现
 *
 */

public class AssociationAgreementActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAllImagetitle(R.drawable.x_agreement, 0, 0, 0);
		iv_title_left.setVisibility(View.VISIBLE);
	}
	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "协议";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Anim.startActivityFromTop(this);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_agreement;
	}

	@Override
	public void initView() {
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
