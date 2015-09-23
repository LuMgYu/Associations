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
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLeague;

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
	private ModelLeague mLeague;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "上传");
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
		tv_title_right.setOnClickListener(this);
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
		case R.id.tv_title_right:
			Bundle data = new Bundle();
			data.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationUploadPhotoActivity.class, data);
			break;
		}

	}
}
