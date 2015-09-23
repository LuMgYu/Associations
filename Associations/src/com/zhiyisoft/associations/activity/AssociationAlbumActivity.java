package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationAlbumAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.AssociationAlbumListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationAlbumActivity extends BaseActivity {
	private BaseListView album_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelLeague mLeague;

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
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_album;
	}

	@Override
	public void initView() {
		album_lv = (AssociationAlbumListview) findViewById(R.id.album_lv);
		mAdapter = new AssociationAlbumAdapter(this, mlist, mLeague);
		album_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
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
		case R.id.tv_title_right:
			Bundle albumdata = new Bundle();
			albumdata.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationCreateAlbumActivity.class,
					albumdata);
			break;
		case R.id.rl_phone:
			break;
		}

	}
}
