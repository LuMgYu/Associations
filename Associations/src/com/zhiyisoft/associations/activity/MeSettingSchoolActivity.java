package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MeSettingSchoolAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.listview.MeSettingSchoolListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingSchoolActivity extends BaseActivity {
	private ImageView school_iv_zoom;
	private EditText school_et_zoom;
	private ImageView school_iv_x;
	private BaseListView school_lv;

	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public String setCenterTitle() {
		return "选择学校";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_school;
	}

	@Override
	public void initView() {
		school_iv_zoom = (ImageView) findViewById(R.id.school_iv_zoom);
		school_et_zoom = (EditText) findViewById(R.id.school_et_zoom);
		school_iv_x = (ImageView) findViewById(R.id.school_iv_x);
		school_lv = (MeSettingSchoolListview) findViewById(R.id.school_lv);
		mAdapter = new MeSettingSchoolAdapter(this, mlist);
		school_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		school_iv_zoom.setOnClickListener(this);
		school_iv_x.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.school_iv_zoom:
			break;
		case R.id.school_iv_x:
			break;
		}

	}
}
