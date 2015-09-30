package com.zhiyisoft.associations.activity;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.ModelLeagueMember;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

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
	private RoundImageView iv_member2;
	private RoundImageView iv_member3;
	private RoundImageView iv_member4;
	private SmartImageView iv_album;
	private SmartImageView iv_album2;
	private SmartImageView iv_album3;
	private SmartImageView iv_file_share;
	private SmartImageView iv_file_share2;
	private SmartImageView iv_file_share3;
	private RelativeLayout rl_new;
	private RelativeLayout rl_activity;
	private TextView tv_activity;
	private TextView tv_activity_count;
	private LinearLayout main_ll_share;
	private LinearLayout main_ll_join;

	private RelativeLayout rl_album;
	private RelativeLayout rl_file_share;

	ModelLeague mLeague; // 从activity传过来的信息

	private static final int SUCCESS = 1;
	private static final int SUCCESS_JOIN = 2;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS:
				ModelLeague league = (ModelLeague) msg.obj;
				if (league != null) {
					ToastUtils.showToast("获取资料成功");
					bindleDataToView(league);
				} else {
					ToastUtils.showToast("获取资料失败");
				}
				break;
			case SUCCESS_JOIN:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					ToastUtils.showToast("您已成功加入此社团,请等待圈主审核！");
					onBackPressed();
				} else {
					ToastUtils.showToast("加入社团失败");
				}
				break;
			}

		}

	};

	@Override
	public String setCenterTitle() {
		return "北京大学轮滑社";
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
		tv_activity_count = (TextView) findViewById(R.id.tv_activity_count);
		main_ll_share = (LinearLayout) findViewById(R.id.main_ll_share);
		main_ll_join = (LinearLayout) findViewById(R.id.main_ll_join);
		rl_album = (RelativeLayout) findViewById(R.id.rl_album);
		rl_file_share = (RelativeLayout) findViewById(R.id.rl_file_share);

		iv_member2 = (RoundImageView) findViewById(R.id.iv_member2);
		iv_member3 = (RoundImageView) findViewById(R.id.iv_member3);
		iv_member4 = (RoundImageView) findViewById(R.id.iv_member4);
		iv_album = (SmartImageView) findViewById(R.id.iv_album);
		iv_album2 = (SmartImageView) findViewById(R.id.iv_album2);
		iv_album3 = (SmartImageView) findViewById(R.id.iv_album3);
		iv_file_share = (SmartImageView) findViewById(R.id.iv_file_share);
		iv_file_share2 = (SmartImageView) findViewById(R.id.iv_file_share2);
		iv_file_share3 = (SmartImageView) findViewById(R.id.iv_file_share3);
		getInformationFromNet(mLeague);
	}

	/**
	 * 获取信息从网络上 通过activity传过来的必要的参数
	 * 
	 * @param mLeague2
	 */
	private void getInformationFromNet(final ModelLeague league) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				ModelLeague result = (ModelLeague) leagueImpl.view(league);
				Message message = Message.obtain();
				message.what = SUCCESS;
				message.obj = result;
				mHandle.sendMessage(message);
			}
		});
	}

	private void applyJoinAssociation(final ModelLeague league) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				boolean isSuccess = leagueImpl.join(league);
				Message message = Message.obtain();
				message.what = SUCCESS_JOIN;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
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
			data.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationMemberActivity.class, data);
			break;
		case R.id.rl_new:
			Bundle data1 = new Bundle();
			data1.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationNewActivity.class, data1);
			break;
		case R.id.rl_activity:
			Bundle moveData = new Bundle();
			moveData.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationMoveActivity.class, moveData);
			break;
		case R.id.main_ll_share:
			preformShare();
			break;
		case R.id.main_ll_join:
			applyJoinAssociation(mLeague);
			break;
		case R.id.iv_title:
			break;
		case R.id.rl_album:
			Bundle albumdata = new Bundle();
			albumdata.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationAlbumActivity.class, albumdata);
			break;
		case R.id.rl_file_share:
			Bundle data3 = new Bundle();
			mApp.startActivity(this, AssociationWordActivity.class, data3);
			break;
		case R.id.rl_phone:
			break;
		}

	}

	/**
	 * 绑定资料到界面上
	 * 
	 * @param league
	 */
	private void bindleDataToView(ModelLeague league) {
		iv_title.setImageUrl(league.getLogourl());
		tv_association_name.setText(league.getName() + "");
		setAlltitle(null, league.getName() + "", null);
		tv_association_data_content.setText(league.getDescription() + "");
		tv_association_data_xiehui.setText(league.getCategoryName());
		tv_association_data_school.setText(league.getSchoolName());
		tv_activity_count.setText(league.getEvent_count() + "个");
		List<Model> members = league.getMemberlist();
		if (members != null) {
			for (int i = 0; i < members.size(); i++) {
				ModelLeagueMember member = (ModelLeagueMember) members.get(i);
				if (i == 0) {
					iv_member1.setVisibility(View.VISIBLE);
					mApp.displayImage(member.getFaceurl(), iv_member1);
				} else if (i == 1) {
					iv_member2.setVisibility(View.VISIBLE);
					mApp.displayImage(member.getFaceurl(), iv_member2);
				} else if (i == 2) {
					iv_member3.setVisibility(View.VISIBLE);
					mApp.displayImage(member.getFaceurl(), iv_member3);
				} else if (i == 3) {
					iv_member4.setVisibility(View.VISIBLE);
					mApp.displayImage(member.getFaceurl(), iv_member4);
				}
			}
		}
		List<Model> albums = league.getAlbums();
		if (albums != null) {
			for (int i = 0; i < albums.size(); i++) {
				ModelLeagueAlbum album = (ModelLeagueAlbum) albums.get(i);
				if (i == 0) {
					iv_album.setVisibility(View.VISIBLE);
					mApp.displayImage(album.getImgsrcL(), iv_album);

				} else if (i == 1) {
					iv_album2.setVisibility(View.VISIBLE);
					mApp.displayImage(album.getImgsrcL(), iv_album2);
				} else if (i == 2) {
					iv_album3.setVisibility(View.VISIBLE);
					mApp.displayImage(album.getImgsrcL(), iv_album3);
				}
			}
		}
	};
}
