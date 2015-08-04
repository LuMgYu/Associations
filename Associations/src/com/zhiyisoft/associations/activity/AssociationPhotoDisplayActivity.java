package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationAlbumAdapter;
import com.zhiyisoft.associations.listview.AssociationAlbumListview;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationPhotoDisplayActivity extends BaseActivity {
	private HorizontalScrollView photo_hsv;
	private LinearLayout photo_ll;
	private int[] resArray;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "创建相册");
		addImage(resArray);
	}

	@Override
	public String setCenterTitle() {
		return "1/32";
	}

	@Override
	public void initIntent() {
		getIntent();
		Bundle bundle = getIntent().getExtras();
		resArray = bundle.getIntArray("photolist");
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_photo_display;
	}

	@Override
	public void initView() {
		photo_hsv = (HorizontalScrollView) findViewById(R.id.photo_hsv);
		photo_ll = (LinearLayout) findViewById(R.id.photo_ll);
	}

	@Override
	public void initListener() {
		// rl_nick.setOnClickListener(this);
		// rl_gender.setOnClickListener(this);
		// rl_school.setOnClickListener(this);
		// rl_homeland.setOnClickListener(this);
		// rl_email.setOnClickListener(this);
		// rl_phone.setOnClickListener(this);

	}

	/**
	 * 添加热热门分类
	 */
	private void addImage(int[] imageArray) {
		for (int i = 0; i < imageArray.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(UIUtils
					.getWindowWidth(this), LayoutParams.MATCH_PARENT));
			imageView.setImageResource(imageArray[i]);
			photo_ll.addView(imageView);
		}
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
