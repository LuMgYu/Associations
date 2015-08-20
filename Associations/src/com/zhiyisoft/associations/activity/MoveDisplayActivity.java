package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MoveAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现展示所点选项的社团
 *
 */

public class MoveDisplayActivity extends BaseActivity {
	private BaseListView move_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private String mName;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("", mName, "");
	}

	@Override
	public void initIntent() {
		mBundle = getIntent().getExtras();
		if (mBundle != null) {
			mName = mBundle.getString(Config.HOTCATEGORY);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_display;
	}

	@Override
	public void initView() {
		move_lv = (MoveListview) findViewById(R.id.move_lv);
		mAdapter = new MoveAdapter(this, mlist);
		move_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {

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

	@Override
	public String setCenterTitle() {
		return "";
	}
}
