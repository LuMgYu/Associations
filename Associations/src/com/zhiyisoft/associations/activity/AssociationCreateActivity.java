package com.zhiyisoft.associations.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.LeagueIm;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationCreateActivity extends BaseActivity {
	private RoundImageView association_icon;
	private EditText association_et_name;
	private TextView association_tv_school_name;
	private RelativeLayout association_rl_school;
	private RelativeLayout association_rl_welfare;
	private TextView association_tv_welfare_name;
	private ImageView association_iv_welfare;
	private EditText association_et_about;
	private ImageView association_iv_yes;
	private ImageView association_iv_no;
	private RelativeLayout association_rl_main;
	private EditText association_et_contact;
	private EditText association_et_contact_way;
	private ImageView association_iv_commit_yes;

	private TextView association_tv_commit_yes;
	private Button association_btn_commit;
	private Bitmap mBitmap;
	public static final int SUCCESS = 1;

	private Handler mHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SUCCESS:

				break;

			default:
				break;
			}
		};
	};

	@Override
	public String setCenterTitle() {
		return "申请社团";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_create;
	}

	@Override
	public void initView() {
		association_icon = (RoundImageView) findViewById(R.id.association_icon);
		association_et_name = (EditText) findViewById(R.id.association_et_name);
		association_tv_school_name = (TextView) findViewById(R.id.association_tv_school_name);
		association_rl_school = (RelativeLayout) findViewById(R.id.association_rl_school);
		association_rl_welfare = (RelativeLayout) findViewById(R.id.association_rl_welfare);
		association_tv_welfare_name = (TextView) findViewById(R.id.association_tv_welfare_name);

		association_iv_welfare = (ImageView) findViewById(R.id.association_iv_welfare);
		association_et_about = (EditText) findViewById(R.id.association_et_about);
		association_iv_yes = (ImageView) findViewById(R.id.association_iv_yes);
		association_iv_no = (ImageView) findViewById(R.id.association_iv_no);
		association_rl_main = (RelativeLayout) findViewById(R.id.association_rl_main);
		association_et_contact = (EditText) findViewById(R.id.association_et_contact);
		association_et_contact_way = (EditText) findViewById(R.id.association_et_contact_way);
		association_iv_commit_yes = (ImageView) findViewById(R.id.association_iv_commit_yes);
		association_tv_commit_yes = (TextView) findViewById(R.id.association_tv_commit_yes);
		association_btn_commit = (Button) findViewById(R.id.association_btn_commit);
	}

	@Override
	public void initListener() {
		association_icon.setOnClickListener(this);
		association_rl_school.setOnClickListener(this);
		association_iv_welfare.setOnClickListener(this);
		association_iv_yes.setOnClickListener(this);
		association_iv_no.setOnClickListener(this);
		association_rl_main.setOnClickListener(this);
		association_btn_commit.setOnClickListener(this);

	}

	@Override
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		association_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.association_icon:
			super.openTheGalley();
			break;
		case R.id.association_rl_school:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, MeSettingProvinceActivity.class, data1);
			break;
		case R.id.association_iv_welfare:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.association_iv_yes:
			resetChoose();
			association_iv_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.association_iv_no:
			resetChoose();
			association_iv_no.setImageResource(R.drawable.yes);
			break;
		case R.id.association_rl_main:
			Bundle data3 = new Bundle();
			mApp.startActivity(this, AssociationWordActivity.class, data3);
			break;
		case R.id.association_btn_commit:
			final ModelLeague league = new ModelLeague();
			// name:社团名(45字符，约11个字)
			// categoryid：分类
			// logo:logoID
			// description:描述
			// school:学校ID
			// private:是否够开
			// tags: 标签(选填)
			// contact:联系人电话
			league.setName(association_et_name.getText().toString() + "");
			// league.setCategoryid(categoryid);
			// league.setLogo(name);
			league.setDescription(association_et_about.getText().toString()
					+ "");
			league.setSchool(association_tv_school_name.getText().toString()
					+ "");
			// league.setmPrivate(association_et_name.getText().toString() +
			// "");
			// league.setTags(association_et_name.getText().toString() + "");
			league.setContact(association_et_contact_way.getText().toString()
					+ "");
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					LeagueIm leagueIm = mApp.getLeagueIm();
					leagueIm.createLeague(league);
				}
			});
			break;
		}

	}

	/**
	 * 重置选项
	 */
	private void resetChoose() {
		association_iv_yes.setImageResource(R.drawable.no);
		association_iv_no.setImageResource(R.drawable.no);

	}
}
