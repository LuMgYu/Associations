package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyPhotoGridViewAdapter;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationPhotoAlbumActivity extends BaseActivity {
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
		mAdapter = new MyPhotoGridViewAdapter(resArray, this);
		phone_album_gv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		phone_album_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle data = new Bundle();
				data.putIntArray("photolist", resArray);
				mApp.startActivity(AssociationPhotoAlbumActivity.this,
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
