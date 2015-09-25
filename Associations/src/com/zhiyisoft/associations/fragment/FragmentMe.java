package com.zhiyisoft.associations.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MeSettingDataActivity;
import com.zhiyisoft.associations.activity.MeSettingSignatureActivity;
import com.zhiyisoft.associations.adapter.AssociationJoinAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.listview.MeAssociationListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

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

	private ModelUser mUser;
	private static final int SUCCESS = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				List<Model> list = (List<Model>) msg.obj;
				if (list != null) {
					ToastUtils.showToast("获取社团信息成功");
					bindDataToView(list);
				} else {
					ToastUtils.showToast("获取社团信息失败");
				}
				break;
			}

		}

	};

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
		getJoinedAssocitionFromNet(); // 获取已加入社团的列表
		mUser = mApp.getUser();
		initUser(mUser);
		me_tv_nick.setText(mUser.getUname() + "");
		if (mUser.getAutograph() != null) {
			me_tv_signature.setText(mUser.getAutograph() + "");
		}
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param mUser2
	 */
	private void initUser(ModelUser user) {
		setimageIcon(user);
		// TODO 以后这里还需要设置 个性签名，以及以及加入的社团

	}

	/**
	 * 设置头像
	 * 
	 * @param user
	 */
	private void setimageIcon(ModelUser user) {
		String faceUrl = user.getFaceurl();
		if (faceUrl != null && faceUrl.length() > 0) {
			me_iv_icon.setImageUrl(faceUrl);
		}
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
	public File getFile(String path) {
		File file = super.getFile(path);
		mUser.setUploadFile(file);
		uploadIcon(mUser);
		return file;
	}

	private void uploadIcon(ModelUser user) {
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, user.getOauth_token());
		params.put(Api.oauth_token_secret, user.getOauth_token_secret());
		File file = user.getUploadFile();
		if (file != null) {
			try {
				params.put("file", user.getUploadFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=facepic",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						try {
							JSONObject jsonObject = new JSONObject(arg1);
							if (jsonObject.has("data")) {
								JSONObject data = jsonObject
										.getJSONObject("data");
								if (data.has("url")) {
									ToastUtils.showToast("头像更新成功");
									mUser.setFaceurl(data.getString("url")); // 传了数据上去就要更新本地的user
									mApp.saveUser(mUser);

								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
	}

	/**
	 * 获取社团信息
	 * 
	 * @param league
	 */
	private void getJoinedAssocitionFromNet() {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				List<Model> list = leagueImpl.joinedGroup();
				Message message = Message.obtain();
				message.what = SUCCESS;
				message.obj = list;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 绑定数据到界面
	 * 
	 * @param leaguelist
	 */
	private void bindDataToView(List<Model> leaguelist) {
		if (leaguelist != null) {
			AssociationJoinAdapter joinadapter = new AssociationJoinAdapter(
					mActivity, leaguelist);
			me_lv_association.setAdapter(joinadapter);
			me_iv_default.setVisibility(View.GONE);
		}
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
