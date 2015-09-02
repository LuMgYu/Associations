package com.zhiyisoft.associations.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youku.uploader.YouKuGetCodeActivity;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MeSettingDataActivity;
import com.zhiyisoft.associations.activity.MeSettingSignatureActivity;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.listview.MeAssociationListview;
import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMe extends BaseFragment {
	private RelativeLayout me_rl_find;
	private RoundImageView me_iv_icon;
	private ImageView me_iv_photo;
	private TextView me_tv_nick;
	private Button me_btn_update;
	private RelativeLayout me_rl_signature;
	private TextView me_tv_signature;
	private ImageView me_iv_default;
	private BaseListView me_lv_association;

	private Bitmap mBitmap;

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_me;
	}

	@Override
	public void initView() {
		me_rl_find = (RelativeLayout) findViewById(R.id.me_rl_find);
		me_iv_icon = (RoundImageView) findViewById(R.id.me_iv_icon);
		me_iv_photo = (ImageView) findViewById(R.id.me_iv_photo);
		me_tv_nick = (TextView) findViewById(R.id.me_tv_nick);
		me_btn_update = (Button) findViewById(R.id.me_btn_update);
		me_rl_signature = (RelativeLayout) findViewById(R.id.me_rl_signature);
		me_tv_signature = (TextView) findViewById(R.id.me_tv_signature);
		me_iv_default = (ImageView) findViewById(R.id.me_iv_default);
		me_lv_association = (MeAssociationListview) findViewById(R.id.me_lv_association);
	}

	@Override
	public void initListener() {
		me_rl_find.setOnClickListener(this);
		me_iv_icon.setOnClickListener(this);
		me_iv_photo.setOnClickListener(this);
		me_btn_update.setOnClickListener(this);
		me_rl_signature.setOnClickListener(this);

	}

	@Override
	public void initData() {

	}

	@Override
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		me_iv_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.me_rl_find:
			Bundle data = new Bundle();
			mApp.startActivity(getActivity(), MeSettingDataActivity.class, data);
			break;

		case R.id.me_iv_icon:
			mApp.startActivity(getActivity(), YouKuGetCodeActivity.class, null);
			// openTheGalley();
			break;
		case R.id.me_iv_photo:
			openTheGalley();
			break;
		case R.id.me_btn_update:
			Bundle data1 = new Bundle();
			mApp.startActivity(getActivity(), MeSettingDataActivity.class,
					data1);
			break;
		case R.id.me_rl_signature:
			Bundle data2 = new Bundle();
			mApp.startActivity(getActivity(), MeSettingSignatureActivity.class,
					data2);
			break;
		}

	}

}
