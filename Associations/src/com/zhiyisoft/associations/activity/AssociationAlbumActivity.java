package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationAlbumAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.listview.AssociationAlbumListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationAlbumActivity extends BaseActivity {
	private BaseListView album_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "创建相册");
	}

	@Override
	public String setCenterTitle() {
		return "社团相册";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_album;
	}

	@Override
	public void initView() {
		album_lv = (AssociationAlbumListview) findViewById(R.id.album_lv);
		mAdapter = new AssociationAlbumAdapter(this, mlist);
		album_lv.setAdapter(mAdapter);
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
