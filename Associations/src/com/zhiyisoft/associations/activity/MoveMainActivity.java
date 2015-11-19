package com.zhiyisoft.associations.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.img.WebImageCache;
import com.zhiyisoft.associations.model.ModelError;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueMember;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveMainActivity extends BaseActivity {
	private ImageView returnBack;
	private SmartImageView move_icon;
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
	private RoundImageView association_icon;
	private RelativeLayout association_rl_name;
	private WebImageCache imageCache;
	private TextView move_tv_watch;
	private TextView move_tv_join;
	ModelEvent mEventResult;

	private boolean isWatch = false;
	private boolean isJoin = false;
	private static final int SUCCESS_DETAIL = 1;
	private static final int SUCCESS_WATCH = 2;
	private static final int SUCCESS_CANCLE_WATCH = 4;
	private static final int SUCCESS_JOIN = 3;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS_DETAIL:
				mEventResult = (ModelEvent) msg.obj;
				if (mEventResult != null) {
					bindDataToView(mEventResult);
				} else {
					ToastUtils.showToast("获取资料失败");
				}
				break;
			case SUCCESS_WATCH:
				boolean isWatch = (Boolean) msg.obj;
				if (isWatch) {
					ToastUtils.showToast("关注成功");
					isWatch(1);
				} else {
					ToastUtils.showToast("关注失败");
				}
				break;
			case SUCCESS_CANCLE_WATCH:
				boolean flag = (Boolean) msg.obj;
				if (flag) {
					ToastUtils.showToast("取消关注成功");
					isWatch(0);
				} else {
					ToastUtils.showToast("取消关注失败");
				}
				break;
			case SUCCESS_JOIN:
				ModelError model = (ModelError) msg.obj;
				if (model != null) {
					if (model.getStatus() == 1) {
						ToastUtils.showToast("加入成功");
						isJoin(1);
					} else {
						ToastUtils.showToast(model.getMsg());
					}
				} else {
					ToastUtils.showToast("加入失败");
				}
				break;
			}

		}

	};
	private ModelEvent mEvent; // 事件

	@Override
	public String setCenterTitle() {
		return null;
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mEvent = (ModelEvent) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	/**
	 * 绑定数据到界面
	 * 
	 * @param event
	 */
	private void bindDataToView(ModelEvent event) {
		if (event != null) {
			Log.i("event", event.toString());
			move_icon.setImageUrl(event.getLogourl());
			tv_team_name.setText(event.getTitle());
			move_btn_online.setText(event.getOnline());
			move_btn_event.setText(event.getTypeName());
			move_tv_deadline.setText(DateUtil.strTodate(event.getsTime()) + "-"
					+ DateUtil.strTodate(event.geteTime()));
			move_tv_allmove.setText(event.getJoinCount() + "参加");
			String online = event.getOnline();
			if (online.equals("1")) {
				move_btn_online.setText("线上");
				move_tv_onlineMove.setText(event.getAddress());
			} else {
				move_btn_online.setText("线下");
				move_tv_onlineMove.setText(event.getAddress());
			}
			String typeName = event.getTypeName();
			if (typeName != null) {
				move_btn_event.setText(typeName);
			}
			tv_reference_des.setText(event.getExplain());
			tv_main_handle_school.setText(event.getHost());
			tv_association_member.setText("参与者（" + event.getJoinCount() + "）");
			tv_association_name.setText(event.getGname());
			association_icon.setImageUrl(event.getGlogo());
			int isin = event.getIsin();
			int issub = event.getIssub();
			isJoin(isin);
			isWatch(issub);
			// TODO 这里纯在bug
			setTopBg(move_fl_bg, event.getLogourl());
			List<Model> members = event.getMembers();
			if (members != null) {
				for (int i = 0; i < members.size(); i++) {
					ModelLeagueMember member = (ModelLeagueMember) members
							.get(i);
					if (i == 0) {
						iv_member1.setVisibility(View.VISIBLE);
						mApp.displayImage(member.getFaceurl(), iv_member1);
					}
					if (i == 1) {
						iv_member2.setVisibility(View.VISIBLE);
						mApp.displayImage(member.getFaceurl(), iv_member2);
					}

					if (i == 2) {
						iv_member3.setVisibility(View.VISIBLE);
						mApp.displayImage(member.getFaceurl(), iv_member3);
					}

					if (i == 3) {
						iv_member4.setVisibility(View.VISIBLE);
						mApp.displayImage(member.getFaceurl(), iv_member4);
					}

				}
			}
			List<Model> works = event.getWorks();
			if (works != null) {
				for (int i = 0; i < works.size(); i++) {
					ModelEventWorks work = (ModelEventWorks) works.get(i);
					if (i == 0) {
						iv_album1.setVisibility(View.VISIBLE);
						mApp.displayImage(work.getFaceurl(), iv_album1);
					}
					if (i == 1) {
						iv_album2.setVisibility(View.VISIBLE);
						mApp.displayImage(work.getFaceurl(), iv_album2);
					}

					if (i == 2) {
						iv_album3.setVisibility(View.VISIBLE);
						mApp.displayImage(work.getFaceurl(), iv_album3);
					}

				}
			}

		}
	}

	private void isWatch(int issub) {
		if (issub == 1) {
			move_tv_watch.setText("已关注");
			isWatch = true;
		} else {
			move_tv_watch.setText("关注");
			isWatch = false;
		}
	}

	private void isJoin(int isin) {
		if (isin == 1) {
			move_tv_join.setText("已报名");
			isJoin = true;
		} else {
			isJoin = false;
			move_tv_watch.setText("报名");
		}
	};

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_main;
	}

	@Override
	public void initView() {
		returnBack = (ImageView) findViewById(R.id.returnBack);
		move_icon = (SmartImageView) findViewById(R.id.move_icon);
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
		association_icon = (RoundImageView) findViewById(R.id.association_icon);
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
		move_tv_watch = (TextView) findViewById(R.id.move_tv_watch);
		move_tv_join = (TextView) findViewById(R.id.move_tv_join);
		imageCache = new WebImageCache(getApplicationContext());
		getMoveInforFromNet(mEvent);
	}

	/**
	 * 设置活动上面的背景
	 * 
	 * @param view
	 */
	@SuppressLint("NewApi")
	private void setTopBg(final View bgView, String url) {
		ImageSize mImageSize = new ImageSize(100, 100);
		mApp.initImageLoader().loadImage(url, mImageSize,
				new SimpleImageLoadingListener() {

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						if (loadedImage != null) {
							int height = loadedImage.getHeight();
							int width = loadedImage.getWidth();
							Bitmap targetBp = Bitmap.createBitmap(loadedImage,
									0, 0, width, height / 3);
							BitmapDrawable background = new BitmapDrawable(
									targetBp);
							bgView.setBackground(background);
						}
					}

				});
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
			Bundle assoData = new Bundle();
			ModelLeague league = new ModelLeague();
			if (mEventResult != null) {
				league.setGid(mEventResult.getGid());
			}
			assoData.putSerializable(Config.SEND_ACTIVITY_DATA, league);
			mApp.startActivity(this, AssociationInformationActivity.class,
					assoData);
			break;
		case R.id.rl_works_display:
			Bundle workdata = new Bundle();
			workdata.putSerializable(Config.SEND_ACTIVITY_DATA, mEventResult);
			mApp.startActivity(this, MoveWorksDisplayActivity.class, workdata);
			break;
		case R.id.rl_move_status:

			break;
		case R.id.main_ll_share:
			preformShare();
			break;
		case R.id.main_ll_watch:
			if (!isWatch && checkTheUser()) {
				mEvent.setStub(0);
				WatchMove(mEvent, SUCCESS_WATCH);
			}
			if (isWatch && checkTheUser()) {
				if (mEvent != null) {
					mEvent.setStub(1);
					WatchMove(mEvent, SUCCESS_CANCLE_WATCH);
				}
			}
			break;
		case R.id.main_ll_join:
			if (!isJoin && checkTheUser()) {
				JoinMove(mEvent);
			}

			break;
		case R.id.rl_member:
			Bundle moveMember = new Bundle();
			moveMember.putSerializable(Config.SEND_ACTIVITY_DATA, mEvent);
			mApp.startActivity(this, MoveMemberActivity.class, moveMember);
			break;
		case R.id.returnBack:
			onBackPressed();
			break;
		}

	}

	/**
	 * 获取活动详情
	 * 
	 * @param event
	 */
	private void getMoveInforFromNet(final ModelEvent event) {
		final EventImpl eventImpl = mApp.getEventFIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = eventImpl.eventView(event);
				Message message = Message.obtain();
				message.what = SUCCESS_DETAIL;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 加入活动
	 * 
	 * @param event
	 */
	private void JoinMove(final ModelEvent event) {
		final EventImpl eventImpl = mApp.getEventFIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = eventImpl.join(event);
				Message message = Message.obtain();
				message.what = SUCCESS_JOIN;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 关注活动
	 * 
	 * @param event
	 */
	private void WatchMove(final ModelEvent event, final int Type) {
		final EventImpl eventImpl = mApp.getEventFIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Boolean isSuccess = eventImpl.sub(event);
				Message message = Message.obtain();
				message.what = Type;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == FILE_CODE) {
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					ToastUtils.showToast(uri.toString());
				}
			}
		}
	}
}
