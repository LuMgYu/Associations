package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyPhotoGridViewAdapter;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationAlbumSingleActivity extends BaseActivity {
	private GridView album_gv;
	private int[] resArray = new int[] { R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small, R.drawable.default_image_small,
			R.drawable.default_image_small };
	private BaseAdapter mAdapter;

	@Override
	public String setCenterTitle() {
		return "社团相册";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_album_single;
	}

	@Override
	public void initView() {
		album_gv = (GridView) findViewById(R.id.album_gv);
		mAdapter = new MyPhotoGridViewAdapter(resArray, this);
		album_gv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		album_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle data = new Bundle();
				data.putIntArray("photolist", resArray);
				mApp.startActivity(AssociationAlbumSingleActivity.this,
						AssociationTopicDetailActivity.class, data);

			}
		});

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
