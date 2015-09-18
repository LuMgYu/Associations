package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class RegisterFillInformationActivity extends BaseActivity {
	private FrameLayout fill_fl_icon;
	private RoundImageView fill_iv_login_icon;
	private ImageView fill_iv_photo;
	private EditText fill_et_nick;
	private RelativeLayout fill_rl_school;
	private TextView fill_tv_school;
	private ImageView fill_iv_gender_boy;
	private ImageView fill_iv_gender_girl;
	private ImageView fill_iv_gender_boy_yes;
	private ImageView fill_iv_gender_girl_yes;

	private String mPhotoid;
	private String mSchool_id;
	private String mSex;
	private String mUname;

	private Button bt_done;
	private Bitmap mBitmap;
	private ModelUser mUser;
	private static final int SUCCESS = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				ModelUser user = (ModelUser) msg.obj;
				if (user != null) {
					ToastUtils.showToast("完善资料成功");
				} else {
					ToastUtils.showToast("完善资料失败");
				}
				break;
			}

		};

	};

	@Override
	public String setCenterTitle() {
		return "填写资料";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mUser = (ModelUser) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_fill_information;
	}

	@Override
	public void initView() {
		fill_fl_icon = (FrameLayout) findViewById(R.id.fill_fl_icon);
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_photo = (ImageView) findViewById(R.id.fill_iv_photo);
		fill_et_nick = (EditText) findViewById(R.id.fill_et_nick);
		fill_rl_school = (RelativeLayout) findViewById(R.id.fill_rl_school);
		fill_tv_school = (TextView) findViewById(R.id.fill_tv_school);
		fill_iv_gender_boy = (ImageView) findViewById(R.id.fill_iv_gender_boy);
		fill_iv_gender_girl = (ImageView) findViewById(R.id.fill_iv_gender_girl);
		fill_iv_gender_boy_yes = (ImageView) findViewById(R.id.fill_iv_gender_boy_yes);
		fill_iv_gender_girl_yes = (ImageView) findViewById(R.id.fill_iv_gender_girl_yes);
		bt_done = (Button) findViewById(R.id.bt_done);
		setIconImageByUrl(mUser.getFaceurl());
	}

	/**
	 * 设置头像
	 * 
	 * @param faceurl
	 *            头像的url
	 */
	private void setIconImageByUrl(String faceurl) {
		if (faceurl != null) {
			fill_iv_login_icon.setImageUrl(faceurl);
		}
	}

	@Override
	public void initListener() {
		fill_fl_icon.setOnClickListener(this);
		fill_rl_school.setOnClickListener(this);
		fill_iv_gender_boy.setOnClickListener(this);
		fill_iv_gender_girl.setOnClickListener(this);
		bt_done.setOnClickListener(this);
	}

	@Override
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		fill_iv_login_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public Bitmap compressOutStream2Bitmap(Bitmap bitmap, OutputStream stream) {
		mBitmap = super.compressOutStream2Bitmap(bitmap, stream);
		fill_iv_login_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public File getFile(String path) {
		File file = super.getFile(path);
		mUser.setUploadFile(file);
		uploadIcon(mUser);
		return file;
	}

	private File uploadIcon(ModelUser user) {
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
								if (data.has("id")) {
									mPhotoid = data.getString("id");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
		return file;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fill_fl_icon:
			initCameraPopWindow();
			showCameraPop(fill_fl_icon, 0, 0);
			break;
		case R.id.fill_rl_school:
			mApp.startActivityForResult(this, MeSettingProvinceActivity.class,
					null);
			break;
		case R.id.fill_iv_gender_boy:
			resetChoose();
			fill_iv_gender_boy_yes.setVisibility(View.VISIBLE);
			mSex = "0";
			break;

		case R.id.fill_iv_gender_girl:
			resetChoose();
			fill_iv_gender_girl_yes.setVisibility(View.VISIBLE);
			mSex = "1";
			break;
		case R.id.bt_done:
			mUname = fill_et_nick.getText().toString();
			if (checkTheUploadMessage(mUname, mSchool_id, mSex)) {
				final LoginIm loginIm2 = mApp.getLoginIm();

				mUser.setUname(mUname);
				mUser.setschool_id(mSchool_id);
				mUser.setSex(mSex);
				mUser.setUploadPhotoId(mPhotoid);
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						Model model = loginIm2.updateProfile(mUser);
						Message message = Message.obtain();
						message.what = SUCCESS;
						message.obj = model;
						mHandle.sendMessage(message);
					}
				});
			}

			break;
		}
	}

	/**
	 * 检验上传的信息是否合格，就是检验这些不为空
	 * 
	 * @param uname
	 * @param school
	 * @param sex
	 * @return
	 */
	private boolean checkTheUploadMessage(String uname, String school,
			String sex) {
		if (uname == null | uname.length() < 1) {
			return false;
		}
		if (school == null | school.length() < 1) {
			return false;
		}
		if (sex == null | sex.length() < 1) {
			return false;
		}
		return true;

	}

	/**
	 * 重置选择对象
	 */
	private void resetChoose() {
		fill_iv_gender_boy_yes.setVisibility(View.GONE);
		fill_iv_gender_girl_yes.setVisibility(View.GONE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ModelSchool modelSchool = (ModelSchool) bundle
					.get(Config.GET_ACTIVITY_DATA);
			if (modelSchool != null) {
				mSchool_id = modelSchool.getId();
				fill_tv_school.setText(modelSchool.getName() + "");
			}
		}
	}
}
