package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationCreateAlbumActivity extends BaseActivity {
	private EditText et_album;
	private EditText et_album_des;
	private ImageView iv_visable;
	private ImageView iv_gone;

	public static final int PUB = 0; // 公开
	public static final int PRI = 1; // 不公开
	public static int mCurrentState = 0; // 不公开

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "创建");
	}

	@Override
	public String setCenterTitle() {
		return "创建相册";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_create_album;
	}

	@Override
	public void initView() {
		et_album = (EditText) findViewById(R.id.et_album);
		et_album_des = (EditText) findViewById(R.id.et_album_des);
		iv_visable = (ImageView) findViewById(R.id.iv_visable);
		iv_gone = (ImageView) findViewById(R.id.iv_gone);
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		iv_visable.setOnClickListener(this);
		iv_gone.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_visable:
			resetImage();
			iv_visable.setImageResource(R.drawable.yes);
			mCurrentState = PUB;
			break;
		case R.id.iv_gone:
			resetImage();
			iv_gone.setImageResource(R.drawable.yes);
			mCurrentState = PRI;
			break;
		case R.id.rl_school:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, MeSettingProvinceActivity.class, data2);
			break;
		case R.id.tv_title_right:
			ToastUtils.showToast("妹子，你创建的了相册哈！");
			break;
		}

	}

	private void resetImage() {
		iv_visable.setImageResource(R.drawable.no);
		iv_gone.setImageResource(R.drawable.no);
	}
}
