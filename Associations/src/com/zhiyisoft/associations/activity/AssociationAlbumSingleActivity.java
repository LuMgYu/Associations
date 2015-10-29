package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyPhotoGridViewAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationAlbumSingleActivity extends BaseActivity {
	private GridView album_gv;

	private BaseAdapter mAdapter;

	private ModelLeagueAlbum mAlbum;
	private static final int SUCCESS = 1;
	private List<ModelLeagueAlbum> mResultLeagueAlbum;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				mResultLeagueAlbum = (List<ModelLeagueAlbum>) msg.obj;
				if (mResultLeagueAlbum != null) {
					ToastUtils.showToast("获取照片成功，正在加载");
					List<String> photoUrllist = new ArrayList<String>();
					for (ModelLeagueAlbum album : mResultLeagueAlbum) {
						photoUrllist.add(album.getPhotoUrl());
					}
					mAdapter = new MyPhotoGridViewAdapter(
							AssociationAlbumSingleActivity.this, photoUrllist);
					album_gv.setAdapter(mAdapter);
				} else {
					ToastUtils.showToast("获取照片失败");
				}
				break;
			}

		};

	};

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
			mAlbum = (ModelLeagueAlbum) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_album_single;
	}

	@Override
	public void initView() {
		album_gv = (GridView) findViewById(R.id.album_gv);
		getPhotosFromNet(mAlbum);
	}

	/**
	 * 从网络中获取照片
	 * 
	 * @param album
	 */
	private void getPhotosFromNet(final ModelLeagueAlbum album) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				List<Model> model = leagueImpl.photoList(album);
				Message message = Message.obtain();
				message.what = SUCCESS;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		album_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mResultLeagueAlbum != null) {
					ModelLeagueAlbum photoModel = mResultLeagueAlbum
							.get(position);
					Bundle data = new Bundle();
					data.putSerializable(Config.SEND_ACTIVITY_DATA, photoModel);
					mApp.startActivity(AssociationAlbumSingleActivity.this,
							AssociationTopicDetailActivity.class, data);
				}

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mResultLeagueAlbum != null) {
			getPhotosFromNet(mAlbum);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_right:
			Bundle data = new Bundle();
			data.putSerializable(Config.SEND_ACTIVITY_DATA, mAlbum);
			mApp.startActivity(this, AssociationUploadPhotoActivity.class, data);
			break;
		}

	}
}
