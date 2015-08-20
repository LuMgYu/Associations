package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MeSettingSchoolAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.MeSettingSchoolListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingSchoolActivity extends BaseActivity {
	private BaseListView school_lv;

	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private String mProvince;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("选择省份", mProvince, null);
	}

	@Override
	public String setCenterTitle() {
		return "";
	}

	@Override
	public void initIntent() {
		mProvince = getIntent().getExtras().getString(Config.PROVINCE);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_school;
	}

	@Override
	public void initView() {
		school_lv = (MeSettingSchoolListview) findViewById(R.id.school_lv);
		ModelSchool modelSchool = new ModelSchool();
		modelSchool.setArea(mProvince);
		mAdapter = new MeSettingSchoolAdapter(this, mlist, modelSchool);
		school_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {

	}
}
