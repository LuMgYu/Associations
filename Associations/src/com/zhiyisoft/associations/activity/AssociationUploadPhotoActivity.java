package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.UploadPhotoGridViewAdapter;
import com.zhiyisoft.associations.config.Config;

/**
 * author：qiuchunjia time：上午10:49:20 类描述：这个类是实现
 *
 */

public class AssociationUploadPhotoActivity extends BaseActivity {
	private EditText photo_detail;
	private RelativeLayout rl_album_location;
	private TextView tv_album_location;
	private GridView gv_fill_photo;
	public final static String ADDPHOTO = "addphoto";
	private UploadPhotoGridViewAdapter mAdapter;

	private List<String> mPhotos = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "上传");
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		// TODO Auto-generated method stub
		return "上传照片";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_uploadphoto;
	}

	@Override
	public void initView() {
		mPhotos.add(ADDPHOTO);
		photo_detail = (EditText) findViewById(R.id.photo_detail);
		rl_album_location = (RelativeLayout) findViewById(R.id.rl_album_location);
		tv_album_location = (TextView) findViewById(R.id.tv_album_location);
		gv_fill_photo = (GridView) findViewById(R.id.gv_fill_photo);
		mAdapter = new UploadPhotoGridViewAdapter(mPhotos, this);
		gv_fill_photo.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		gv_fill_photo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String str = (String) view.getTag();
				if (str.equals(ADDPHOTO)) {
					mApp.startActivityForResult(
							AssociationUploadPhotoActivity.this,
							LocalImagListActivity.class, null);
				}

			}
		});
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
				// TODO 这里的list路径，以后还要靠这个来上传图片呢，接口写出来后就调用这里
				mPhotos.addAll(list);
				replaceLocation(ADDPHOTO, mPhotos);
			}
		}
	}

	/**
	 * 把添加图片的按钮设置放置到最后
	 * 
	 * @param path
	 * @param list
	 */
	private void replaceLocation(String path, List<String> list) {
		String addphoto = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(ADDPHOTO)) {
				addphoto = list.remove(i);
			}
		}
		list.add(addphoto);
		mAdapter = new UploadPhotoGridViewAdapter(list, this);
		gv_fill_photo.setAdapter(mAdapter);
	}
}
