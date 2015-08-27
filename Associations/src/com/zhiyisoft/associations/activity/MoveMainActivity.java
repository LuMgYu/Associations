package com.zhiyisoft.associations.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.img.RoundImageView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveMainActivity extends BaseActivity {
	private ImageView returnBack;
	private ImageView move_icon;
	private Button move_btn_online;
	private TextView move_btn_event;
	private TextView tv_team_name;
	private TextView move_tv_deadline;
	private TextView move_tv_allmove;
	private TextView move_tv_onlineMove;
	private TextView tv_reference_des;
	private TextView tv_main_handle_school;
	private TextView tv_association_member;
	private RelativeLayout rl_member;
	private RoundImageView iv_member4;
	private RoundImageView iv_member3;
	private RoundImageView iv_member2;
	private RoundImageView iv_member1;
	private TextView tv_association_name;
	private RelativeLayout rl_association;
	private RelativeLayout rl_works_display;
	private TextView tv_album;
	private ImageView iv_album1;
	private ImageView iv_album2;
	private ImageView iv_album3;
	private RelativeLayout rl_move_status;
	private TextView tv_move_status;
	private LinearLayout main_ll_share;
	private LinearLayout main_ll_watch;
	private LinearLayout main_ll_join;
	private FrameLayout move_fl_bg;

	private RelativeLayout association_rl_name;

	@Override
	public String setCenterTitle() {
		return null;
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_main;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyisoft.associations.activity.base.BaseActivity#initView()
	 */
	@Override
	public void initView() {
		returnBack = (ImageView) findViewById(R.id.returnBack);
		move_icon = (ImageView) findViewById(R.id.move_icon);
		move_btn_online = (Button) findViewById(R.id.move_btn_online);
		move_btn_event = (Button) findViewById(R.id.move_btn_event);
		tv_team_name = (TextView) findViewById(R.id.tv_team_name);
		move_tv_deadline = (TextView) findViewById(R.id.move_tv_deadline);

		move_tv_allmove = (TextView) findViewById(R.id.move_tv_allmove);
		move_tv_onlineMove = (TextView) findViewById(R.id.move_tv_onlineMove);
		tv_reference_des = (TextView) findViewById(R.id.tv_reference_des);
		tv_main_handle_school = (TextView) findViewById(R.id.tv_main_handle_school);
		tv_association_member = (TextView) findViewById(R.id.tv_association_member);
		rl_member = (RelativeLayout) findViewById(R.id.rl_member);
		iv_member4 = (RoundImageView) findViewById(R.id.iv_member4);
		iv_member3 = (RoundImageView) findViewById(R.id.iv_member3);
		iv_member2 = (RoundImageView) findViewById(R.id.iv_member2);
		iv_member1 = (RoundImageView) findViewById(R.id.iv_member1);
		tv_association_name = (TextView) findViewById(R.id.tv_association_name);
		rl_association = (RelativeLayout) findViewById(R.id.rl_association);
		tv_association_member = (TextView) findViewById(R.id.tv_association_member);
		rl_works_display = (RelativeLayout) findViewById(R.id.rl_works_display);
		tv_album = (TextView) findViewById(R.id.tv_album);
		iv_album1 = (ImageView) findViewById(R.id.iv_album1);
		iv_album2 = (ImageView) findViewById(R.id.iv_album2);
		iv_album3 = (ImageView) findViewById(R.id.iv_album3);
		rl_move_status = (RelativeLayout) findViewById(R.id.rl_move_status);
		tv_move_status = (TextView) findViewById(R.id.tv_move_status);
		main_ll_watch = (LinearLayout) findViewById(R.id.main_ll_watch);
		main_ll_share = (LinearLayout) findViewById(R.id.main_ll_share);
		main_ll_join = (LinearLayout) findViewById(R.id.main_ll_join);
		move_fl_bg = (FrameLayout) findViewById(R.id.move_fl_bg);
		association_rl_name = (RelativeLayout) findViewById(R.id.association_rl_name);
		setTopBg(move_fl_bg);
	}

	/**
	 * 设置活动上面的背景
	 * 
	 * @param view
	 */
	@SuppressLint("NewApi")
	private void setTopBg(View view) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.move_icon);
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		Bitmap targetBp = Bitmap.createBitmap(bitmap, 0, 0, width, height / 3);
		BitmapDrawable background = new BitmapDrawable(targetBp);
		view.setBackground(background.getCurrent());
	}

	@Override
	public void initListener() {
		rl_member.setOnClickListener(this);
		rl_association.setOnClickListener(this);
		rl_works_display.setOnClickListener(this);
		rl_move_status.setOnClickListener(this);
		main_ll_share.setOnClickListener(this);
		main_ll_watch.setOnClickListener(this);
		main_ll_join.setOnClickListener(this);
		returnBack.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_association:
			Bundle data = new Bundle();
			mApp.startActivity(this, AssociationInformationActivity.class, data);
			break;
		case R.id.rl_works_display:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, MoveWorksDisplayActivity.class, data1);
			break;
		case R.id.rl_move_status:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.main_ll_share:
			preformShare();
			break;
		case R.id.main_ll_watch:
			// Bundle data4 = new Bundle();
			// mApp.startActivity(this, AssociationAlbumActivity.class, data4);
			break;
		case R.id.main_ll_join:
			// Bundle data3 = new Bundle();
			// mApp.startActivity(this, AssociationInformationActivity.class,
			// data3);
			break;
		case R.id.rl_member:
			Bundle data5 = new Bundle();
			// mApp.startActivity(this, AssociationWordActivity.class, data5);
			break;
		case R.id.returnBack:
			onBackPressed();
			break;
		}

	}
}
