package com.zhiyisoft.associations.fragment;

import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MeSettingDataActivity;
import com.zhiyisoft.associations.activity.MeSettingSignatureActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
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
		initPopWindow();
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
	public Bitmap compressOutStream2Bitmap(Bitmap bitmap, OutputStream stream) {
		mBitmap = super.compressOutStream2Bitmap(bitmap, stream);
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
			// mApp.startActivity(getActivity(), YouKuGetCodeActivity.class,
			// null);
			// // openTheGalley();
			showPop(me_iv_icon, 0, 0);
			break;
		case R.id.me_iv_photo:
			showPop(me_iv_icon, 0, 0);
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

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private TextView btn_openTheCamera;
	private TextView btn_openTheGallery;
	private TextView btn_cancle;

	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.upload_icon_item, null);
			mPopupWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			mPopupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					setWindowAlpha(1.0f);

				}
			});
			// 设置popwindow出现和消失动画
			initPopWidge(popView);
			setPopListener();
		}
	}

	/**
	 * 设置屏幕的透明度
	 * 
	 * @param alpha
	 *            需要设置透明度
	 */
	private void setWindowAlpha(float alpha) {
		WindowManager.LayoutParams params = getActivity().getWindow()
				.getAttributes();
		params.alpha = alpha;
		getActivity().getWindow().setAttributes(params);
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		PopWindowItemListener listener = new PopWindowItemListener();
		btn_openTheCamera.setOnClickListener(listener);
		btn_openTheGallery.setOnClickListener(listener);
		btn_cancle.setOnClickListener(listener);
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		btn_openTheCamera = (TextView) popView
				.findViewById(R.id.btn_openTheCamera);
		btn_openTheGallery = (TextView) popView
				.findViewById(R.id.btn_openTheGallery);
		btn_cancle = (TextView) popView.findViewById(R.id.btn_cancle);

	}

	private class PopWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_openTheCamera:
				openTheCamera();
				mPopupWindow.dismiss();
				break;

			case R.id.btn_openTheGallery:
				mPopupWindow.dismiss();
				openTheGalley();
				break;
			case R.id.btn_cancle:
				mPopupWindow.dismiss();
				break;

			}
		}

	}

	/**
	 * 显示popWindow
	 * */
	@SuppressLint("NewApi")
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
		mPopupWindow.update();
		setWindowAlpha(0.7f);
	}
}
