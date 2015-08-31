package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.LocalPhotoHelper.adapter.AlbumGridViewAdapter;
import com.zhiyisoft.associations.util.localImageHelper.LocalImage;

/**
 * author：qiuchunjia time：下午4:17:40 类描述：这个类是实现
 *
 */

public class LocalAlbumActivity extends BaseActivity {
	private GridView gridView;
	private ArrayList<String> dataList = new ArrayList<String>();
	private HashMap<String, ImageView> hashMap = new HashMap<String, ImageView>();
	private ArrayList<String> selectedDataList = new ArrayList<String>();
	private String bucketId = "";
	private ProgressBar progressBar;
	private AlbumGridViewAdapter gridImageAdapter;
	private LinearLayout selectedImageLayout;
	private Context mContext;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "本地相册";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_local_album;
	}

	@Override
	public void initView() {
		mContext = this;
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		bucketId = bundle.getString("bucketId");
		init();
		initListener();
	}

	private void init() {

		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		progressBar.setVisibility(View.GONE);
		gridView = (GridView) findViewById(R.id.myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
				selectedDataList);
		gridView.setAdapter(gridImageAdapter);
		refreshData();
	}

	@Override
	public void initListener() {
		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, final String path, boolean isChecked) {

						if (selectedDataList.size() >= 8) {
							toggleButton.setChecked(false);
							if (!removePath(path)) {
								ToastUtils.showToast("只能选择8张");
							}
							return;
						} else {
							removePath(path);
						}

					}
				});

	}

	private boolean removePath(String path) {
		if (hashMap.containsKey(path)) {
			selectedImageLayout.removeView(hashMap.get(path));
			hashMap.remove(path);
			removeOneData(selectedDataList, path);
			return true;
		} else {
			return false;
		}
	}

	private void removeOneData(ArrayList<String> arrayList, String s) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).equals(s)) {
				arrayList.remove(i);
				return;
			}
		}
	}

	private void refreshData() {

		new AsyncTask<Void, Void, ArrayList<String>>() {

			@Override
			protected void onPreExecute() {
				progressBar.setVisibility(View.VISIBLE);
				super.onPreExecute();
			}

			@Override
			protected ArrayList<String> doInBackground(Void... params) {
				ArrayList<String> tmpList = new ArrayList<String>();
				tmpList = (ArrayList<String>) LocalImage.getPhotoFileNameById(
						mContext, bucketId);
				for (String str : tmpList) {

				}
				return tmpList;
			}

			protected void onPostExecute(ArrayList<String> tmpList) {

				if (LocalAlbumActivity.this == null
						|| LocalAlbumActivity.this.isFinishing()) {
					return;
				}
				progressBar.setVisibility(View.GONE);
				dataList.clear();
				dataList.addAll(tmpList);
				gridImageAdapter.notifyDataSetChanged();
				return;

			};

		}.execute();

	}
}
