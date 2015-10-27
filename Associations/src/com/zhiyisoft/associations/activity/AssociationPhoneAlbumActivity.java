package com.zhiyisoft.associations.activity;

import java.io.FileNotFoundException;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationPhoneAlbumActivity extends BaseActivity {
	private GridView phone_album_gv;
	private Button next;
	private int[] resArray = new int[] { R.drawable.girl, R.drawable.girl,
			R.drawable.ic_launcher, R.drawable.apk, R.drawable.doc,
			R.drawable.default_image_small, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl,
			R.drawable.girl, R.drawable.girl };
	private BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("手机相册", null, null);
		getPhonePhoto();
	}

	/**
	 * 获取手机上面所有的图片
	 */
	private void getPhonePhoto() {
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("uri", uri.toString());
			ContentResolver cr = this.getContentResolver();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(cr
						.openInputStream(uri));
			} catch (FileNotFoundException e) {
				Log.e("Exception", e.getMessage(), e);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public String setCenterTitle() {
		return "最近照片";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_phone_album;
	}

	@Override
	public void initView() {
		phone_album_gv = (GridView) findViewById(R.id.phone_album_gv);
		next = (Button) findViewById(R.id.next);
//		mAdapter = new MyPhotoGridViewAdapter(resArray, this);
//		phone_album_gv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		phone_album_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle data = new Bundle();
				data.putIntArray("photolist", resArray);
				mApp.startActivity(AssociationPhoneAlbumActivity.this,
						AssociationPhotoDisplayActivity.class, data);

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
