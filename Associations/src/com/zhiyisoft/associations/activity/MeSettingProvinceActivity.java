package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyGridViewAdapter;
import com.zhiyisoft.associations.config.Config;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */
/**
 * 
 * 23个省：河北省（冀）、山东省（齐）、辽宁省（辽）、黑龙江省（黑）、吉林省（吉）、甘肃省（陇）、青海省（青）、河南省（豫）、 江苏省（苏）
 * 、湖北省（鄂）、湖南省（湘）、江西省（赣）、浙江省（浙）、广东省（粤）、
 * 云南省（滇）、福建省（福）、台湾省（台）、海南省（琼）、山西省（晋）、四川省（川）、 陕西省（陕）、 贵州省（黔）、安徽省（皖）
 * 
 * 
 * */
public class MeSettingProvinceActivity extends BaseActivity {
	private GridView province_gv;
	private MyGridViewAdapter mAdapter;
	private String mStr[] = new String[] { "北京", "上海", "天津", "重庆", "浙江", "广东",
			"江苏", "山东", "福建", "安徽", "四川", "湖北", "河北", "云南", "黑龙江", "吉林", "辽宁",
			"海南", "湖南", "河南", "贵州", "江西", "广西", "陕西", "山西", "青海", "宁夏", "甘肃",
			"西藏", "内蒙古", "新疆", "台湾", "香港", "澳门", "国外" };

	@Override
	public String setCenterTitle() {
		return "选择省份";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_province;
	}

	@Override
	public void initView() {
		province_gv = (GridView) findViewById(R.id.province_gv);
		mAdapter = new MyGridViewAdapter(mStr, this);
		province_gv.setAdapter(mAdapter);

	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		province_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle data = new Bundle();
				data.putString(Config.PROVINCE, mStr[position]);
				mApp.startActivity(MeSettingProvinceActivity.this,
						MeSettingSchoolActivity.class, data);
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

}