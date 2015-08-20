package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.img.RoundImageView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveCreateActivity extends BaseActivity {
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

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "提交");
	}

	@Override
	public String setCenterTitle() {
		return "创建活动";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_create;
	}

	// private RelativeLayout association_icon;
	// private RoundImageView association_et_name;
	// private ImageView association_tv_school_name;
	// private TextView association_rl_school;
	// private TextView association_rl_welfare;
	// private TextView association_tv_welfare_name;
	// private TextView association_iv_welfare;
	// private RelativeLayout association_et_about;
	// private RoundImageView association_iv_yes;
	// private RelativeLayout association_iv_no;
	// private RelativeLayout association_rl_main;
	// private TextView association_et_contact;
	// private LinearLayout association_et_contact_way;
	// private LinearLayout association_iv_commit_yes;
	//
	// private RelativeLayout association_tv_commit_yes;
	// private RelativeLayout association_btn_commit;

	@Override
	public void initView() {
		// association_icon = (RoundImageView)
		// findViewById(R.id.association_icon);
		// association_et_name = (EditText)
		// findViewById(R.id.association_et_name);
		// association_tv_school_name = (TextView)
		// findViewById(R.id.association_tv_school_name);
		// association_rl_school = (RelativeLayout)
		// findViewById(R.id.association_rl_school);
		// association_rl_welfare = (RelativeLayout)
		// findViewById(R.id.association_rl_welfare);
		// association_tv_welfare_name = (TextView)
		// findViewById(R.id.association_tv_welfare_name);
		//
		// association_iv_welfare = (ImageView)
		// findViewById(R.id.association_iv_welfare);
		// association_et_about = (EditText)
		// findViewById(R.id.association_et_about);
		// association_iv_yes = (ImageView)
		// findViewById(R.id.association_iv_yes);
		// association_iv_no = (ImageView) findViewById(R.id.association_iv_no);
		// association_rl_main = (RelativeLayout)
		// findViewById(R.id.association_rl_main);
		// association_et_contact = (EditText)
		// findViewById(R.id.association_et_contact);
		// association_et_contact_way = (EditText)
		// findViewById(R.id.association_et_contact_way);
		// association_iv_commit_yes = (ImageView)
		// findViewById(R.id.association_iv_commit_yes);
		// association_tv_commit_yes = (TextView)
		// findViewById(R.id.association_tv_commit_yes);
		// association_btn_commit = (Button)
		// findViewById(R.id.association_btn_commit);
	}

	@Override
	public void initListener() {
		// association_icon.setOnClickListener(this);
		// association_rl_school.setOnClickListener(this);
		// association_iv_welfare.setOnClickListener(this);
		// association_iv_yes.setOnClickListener(this);
		// association_iv_no.setOnClickListener(this);
		// association_rl_main.setOnClickListener(this);
		// association_btn_commit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_member:
			Bundle data = new Bundle();
			mApp.startActivity(this, AssociationMemberActivity.class, data);
			break;
		case R.id.rl_new:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, AssociationNewActivity.class, data1);
			break;
		case R.id.rl_activity:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.iv_title:
			break;
		case R.id.rl_album:
			Bundle data4 = new Bundle();
			mApp.startActivity(this, AssociationAlbumActivity.class, data4);
			break;
		case R.id.rl_file_share:
			Bundle data3 = new Bundle();
			mApp.startActivity(this, AssociationWordActivity.class, data3);
			break;
		case R.id.main_ll_join:
			break;
		case R.id.rl_phone:
			break;
		}

	}
}
