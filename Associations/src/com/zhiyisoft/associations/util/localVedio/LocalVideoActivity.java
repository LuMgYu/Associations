package com.zhiyisoft.associations.util.localVedio;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Video.Thumbnails;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

public class LocalVideoActivity extends BaseActivity {

	public LocalVideoActivity instance = null;
	ListView mLocalVideoListView;
	LocalVideoListViewAdapter mLocalVideoListViewAdapter;
	List<LocalVideo> listVideos;
	int videoSize;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "本地视频";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.vedio_listview;
	}

	@Override
	public void initView() {
		initData();
		loadImages();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	private void initData() {
		instance = this;
		VideoProvider provider = new VideoProvider(instance);
		listVideos = provider.getListVedio();
		videoSize = listVideos.size();
		mLocalVideoListViewAdapter = new LocalVideoListViewAdapter(this,
				listVideos);
		mLocalVideoListView = (ListView) findViewById(R.id.Localvideolistfile);
		mLocalVideoListView.setAdapter(mLocalVideoListViewAdapter);
		mLocalVideoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				// intent.setClass(LocalVideo.this, LocalVideoPlayer.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("video", listVideos.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	/**
	 * Load images.
	 */
	private void loadImages() {
		final Object data = getLastNonConfigurationInstance();
		if (data == null) {
			new LoadImagesFromSDCard().execute();
		} else {
			final LoadedImage[] photos = (LoadedImage[]) data;
			if (photos.length == 0) {
				new LoadImagesFromSDCard().execute();
			}
			for (LoadedImage photo : photos) {
				addImage(photo);
			}
		}
	}

	private void addImage(LoadedImage... value) {
		for (LoadedImage image : value) {
			mLocalVideoListViewAdapter.addPhoto(image);
			mLocalVideoListViewAdapter.notifyDataSetChanged();
		}
	}

	// @Override
	// public Object onRetainNonConfigurationInstance() {
	// final ListView grid = mLocalVideoListView;
	// final int count = grid.getChildCount();
	// final LoadedImage[] list = new LoadedImage[count];
	//
	// for (int i = 0; i < count; i++) {
	// final ImageView v = (ImageView) grid.getChildAt(i);
	// list[i] = new LoadedImage(
	// ((BitmapDrawable) v.getDrawable()).getBitmap());
	// }
	//
	// return list;
	// }

	/**
	 * 获取视频缩略图
	 * 
	 * @param videoPath
	 * @param width
	 * @param height
	 * @param kind
	 * @return
	 */
	private Bitmap getVideoThumbnail(String videoPath, int width, int height,
			int kind) {
		Bitmap bitmap = null;
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	class LoadImagesFromSDCard extends AsyncTask<Object, LoadedImage, Object> {
		@Override
		protected Object doInBackground(Object... params) {
			Bitmap bitmap = null;
			for (int i = 0; i < videoSize; i++) {
				bitmap = getVideoThumbnail(listVideos.get(i).getPath(), 120,
						120, Thumbnails.MINI_KIND);
				if (bitmap != null) {
					publishProgress(new LoadedImage(bitmap));
				}
			}
			return null;
		}

		@Override
		public void onProgressUpdate(LoadedImage... value) {
			addImage(value);
		}
	}

}