package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.ViewPagerAdapter;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationPhotoDisplayActivity extends BaseActivity {
	private ViewPager viewpager;
	private int[] resArray;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "创建相册");
		ViewPagerAdapter adapter = new ViewPagerAdapter(this, resArray);
		viewpager.setAdapter(adapter);
	}

	@Override
	public String setCenterTitle() {
		return "1/32";
	}

	@Override
	public void initIntent() {
		getIntent();
		Bundle bundle = getIntent().getExtras();
		resArray = bundle.getIntArray("photolist");
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_photo_display;
	}

	@Override
	public void initView() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
	}

	@Override
	public void initListener() {
		// rl_nick.setOnClickListener(this);
		// rl_gender.setOnClickListener(this);
		// rl_school.setOnClickListener(this);
		// rl_homeland.setOnClickListener(this);
		// rl_email.setOnClickListener(this);
		// rl_phone.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_nick:
			Bundle data = new Bundle();
			mApp.startActivity(this, MeSettingNickActivity.class, data);
			break;
		case R.id.rl_gender:
			break;
		case R.id.rl_school:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, MeSettingProvinceActivity.class, data2);
			break;
		case R.id.rl_homeland:
			break;
		case R.id.rl_email:
			break;
		case R.id.rl_phone:
			break;
		}

	}
}
