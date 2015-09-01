package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.localImageHelper.LocalImage;
import com.zhiyisoft.associations.util.localImageHelper.PhotoDirInfo;
import com.zhiyisoft.associations.util.localImageHelper.adapter.ImageListAdapter;

/**
 * author：qiuchunjia time：下午4:01:50 类描述：这个类是实现
 *
 */

public class LocalImagListActivity extends BaseActivity {

	private ListView imageListView;
	private ArrayList<PhotoDirInfo> mDirInfos;
	private ImageListAdapter adapter;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "相册";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_localimagelist_layout;
	}

	@Override
	public void initView() {
		imageListView = (ListView) findViewById(R.id.imageListView);
		mDirInfos = LocalImage.getImageDir(getApplicationContext());
		adapter = new ImageListAdapter(this, mDirInfos);
		imageListView.setAdapter(adapter);
		imageListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PhotoDirInfo photoDirInfo = mDirInfos.get(position);
				Intent intent = new Intent(getApplicationContext(),
						LocalAlbumActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				Bundle bundle = new Bundle();
				bundle.putString("bucketId", photoDirInfo.getDirId());
				intent.putExtras(bundle);
				startActivityForResult(intent, GET_DATA_FROM_ACTIVITY);
			}
		});

	}

	@Override
	public void initListener() {

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ArrayList<String> list = (ArrayList<String>) bundle
					.get(Config.GET_ACTIVITY_DATA);
			if (list != null) {
				onReturnResult(list);
				onBackPressed();
			}
		}
	}
}
