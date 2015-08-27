package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.img.RoundImageView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationMainActivity extends BaseActivity {
	private RelativeLayout rl_title;
	private RoundImageView iv_title;
	private ImageView iv_v;
	private TextView tv_association_name;
	private TextView tv_association_data_content;
	private TextView tv_association_data_xiehui;
	private TextView tv_association_data_school;
	private RelativeLayout rl_member;
	private RoundImageView iv_member1;
	private RelativeLayout rl_new;
	private RelativeLayout rl_activity;
	private TextView tv_activity;
	private LinearLayout main_ll_share;
	private LinearLayout main_ll_join;

	private RelativeLayout rl_album;
	private RelativeLayout rl_file_share;

	@Override
	public String setCenterTitle() {
		return "北京大学轮滑社";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_main;
	}

	@Override
	public void initView() {
		rl_title = (RelativeLayout) findViewById(R.id.rl_title);
		iv_title = (RoundImageView) findViewById(R.id.iv_title);
		iv_v = (ImageView) findViewById(R.id.iv_v);
		tv_association_name = (TextView) findViewById(R.id.tv_association_name);
		tv_association_data_content = (TextView) findViewById(R.id.tv_association_data_content);
		tv_association_data_xiehui = (TextView) findViewById(R.id.tv_association_data_xiehui);

		tv_association_data_school = (TextView) findViewById(R.id.tv_association_data_school);
		rl_member = (RelativeLayout) findViewById(R.id.rl_member);
		iv_member1 = (RoundImageView) findViewById(R.id.iv_member1);
		rl_new = (RelativeLayout) findViewById(R.id.rl_new);
		rl_activity = (RelativeLayout) findViewById(R.id.rl_activity);
		tv_activity = (TextView) findViewById(R.id.tv_activity);
		main_ll_share = (LinearLayout) findViewById(R.id.main_ll_share);
		main_ll_join = (LinearLayout) findViewById(R.id.main_ll_join);
		rl_album = (RelativeLayout) findViewById(R.id.rl_album);
		rl_file_share = (RelativeLayout) findViewById(R.id.rl_file_share);
	}

	@Override
	public void initListener() {
		rl_member.setOnClickListener(this);
		rl_new.setOnClickListener(this);
		rl_activity.setOnClickListener(this);
		main_ll_share.setOnClickListener(this);
		main_ll_join.setOnClickListener(this);
		iv_title.setOnClickListener(this);
		rl_album.setOnClickListener(this);
		rl_file_share.setOnClickListener(this);

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
		case R.id.main_ll_share:
			preformShare();
			break;
		case R.id.main_ll_join:
			// Bundle data2 = new Bundle();
			// mApp.startActivity(this, AssociationMoveActivity.class, data2);
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
		case R.id.rl_phone:
			break;
		}

	}
}
