package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationInformationActivity extends BaseActivity {
	private TextView association_tv_name1;
	private ImageView iv_v;
	private TextView association_tv_school_name;
	private TextView association_tv_welfare_name;
	private TextView association_tv_about2;
	private TextView association_tv_theme_name;
	private TextView association_tv_contact2;
	private TextView association_tv_contact_way2;

	private ModelLeague mLeague;
	private static final int SUCCESS = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				ModelLeague league = (ModelLeague) msg.obj;
				if (league != null) {
					ToastUtils.showToast("获取社团信息成功");
					bindDataToView(league);
				} else {
					ToastUtils.showToast("获取社团信息失败");
				}
				break;
			}

		}

	};

	@Override
	public String setCenterTitle() {
		return "社团信息";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_information;
	}

	@Override
	public void initView() {
		association_tv_name1 = (TextView) findViewById(R.id.association_tv_name1);
		iv_v = (ImageView) findViewById(R.id.iv_v);
		association_tv_school_name = (TextView) findViewById(R.id.association_tv_school_name);
		association_tv_welfare_name = (TextView) findViewById(R.id.association_tv_welfare_name);
		association_tv_about2 = (TextView) findViewById(R.id.association_tv_about2);
		association_tv_theme_name = (TextView) findViewById(R.id.association_tv_theme_name);
		association_tv_contact2 = (TextView) findViewById(R.id.association_tv_contact2);
		association_tv_contact_way2 = (TextView) findViewById(R.id.association_tv_contact_way2);
		getInformatonFromNet(mLeague);
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

	/**
	 * 获取社团信息
	 * 
	 * @param league
	 */
	private void getInformatonFromNet(ModelLeague league) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = leagueImpl.getGroupBaseInfo(mLeague);
				Message message = Message.obtain();
				message.what = SUCCESS;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 绑定信息到界面上
	 * 
	 * @param league
	 */
	private void bindDataToView(ModelLeague league) {
		association_tv_name1.setText(league.getName());
		association_tv_school_name.setText(league.getSchoolName());
		association_tv_welfare_name.setText(league.getCategoryName());
		association_tv_about2.setText(league.getDescription());

		// association_tv_theme_name
		association_tv_contact2.setText(league.getOpenerName());
		association_tv_contact_way2.setText(league.getContact());
	};

}
