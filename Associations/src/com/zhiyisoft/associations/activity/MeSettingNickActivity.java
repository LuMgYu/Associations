package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api.LoginImpl;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingNickActivity extends BaseActivity {
	private EditText et_nick;

	private static final int SUCCESS = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				ModelUser user = (ModelUser) msg.obj;
				if (user != null) {
					ToastUtils.showToast("更新昵称成功！");
					// 保存更新的数据
					ModelUser mainUser = mApp.getUser();
					mainUser.setUname(user.getUname());
					mApp.saveUser(mainUser);
					onBackPressed();
				} else {
					ToastUtils.showToast("更新昵称失败");
				}
				break;
			}

		};

	};

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "保存");
	}

	@Override
	public String setCenterTitle() {
		return "输入昵称";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_nick;
	}

	@Override
	public void initView() {
		et_nick = (EditText) findViewById(R.id.et_nick);

	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_title_right) {
			String content = et_nick.getText().toString();
			if (checkTheContent(content)) {
				ModelUser user = new ModelUser();
				user.setUname((content));
				updateAutoSinature(user);
			}
		}

	}

	private boolean checkTheContent(String content) {
		if (content == null || content.equals("")) {
			ToastUtils.showToast("输入不能为空！");
			return false;
		}
		return true;
	}

	private void updateAutoSinature(final ModelUser user) {
		final LoginImpl loginImpl = mApp.getLoginIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = loginImpl.updateProfile(user);
				Message message = Message.obtain();
				message.what = SUCCESS;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}
}