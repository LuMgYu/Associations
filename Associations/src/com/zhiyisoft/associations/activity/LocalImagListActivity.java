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
import com.zhiyisoft.associations.util.LocalPhotoHelper.adapter.ImageListAdapter;
import com.zhiyisoft.associations.util.localImageHelper.LocalImage;
import com.zhiyisoft.associations.util.localImageHelper.PhotoDirInfo;

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
		return "本地相册";
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
				startActivityForResult(intent, 0);
			}
		});

	}

	@Override
	public void initListener() {

	}

}
