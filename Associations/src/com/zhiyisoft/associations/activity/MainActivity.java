package com.zhiyisoft.associations.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.FragmentAssociation;
import com.zhiyisoft.associations.fragment.FragmentHome;
import com.zhiyisoft.associations.fragment.FragmentLogin;
import com.zhiyisoft.associations.fragment.FragmentMe;
import com.zhiyisoft.associations.fragment.FragmentMove;
import com.zhiyisoft.associations.fragment.FragmentNotify;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.util.UIUtils;

public class MainActivity extends BaseActivity {
	private LinearLayout ll_home;
	private LinearLayout ll_move;
	private LinearLayout ll_association;
	private LinearLayout ll_notify;
	private LinearLayout ll_me;
	// -----------------------------------
	private ImageView iv_home;
	private ImageView iv_move;
	private ImageView iv_association;
	private ImageView iv_notify;
	private ImageView iv_me;
	// --------------------------------------
	private TextView tv_home;
	private TextView tv_move;
	private TextView tv_association;
	private TextView tv_notify;
	private TextView tv_me;

	private FragmentHome mHomeFragment;
	private FragmentMove mMoveFragment;
	private FragmentAssociation mAssociationFragment;
	private FragmentNotify mNotifyFragment;
	private FragmentMe mMeFragment;
	private FragmentLogin mLoginFragment;

	public static int HOME = 0;
	public static int MOVE = 1;
	public static int ASSOCIATION = 2;
	public static int NOTIFY = 3;
	public static int ME = 4;
	public static int mCurrentState = 0;

	@Override
	public String setCenterTitle() {
		return "线团";
	}

	@Override
	public int getBottomLayoutId() {
		return R.layout.bottom_layout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyisoft.associations.activity.base.BaseActivity#checkTheUser()
	 */
	@Override
	public boolean checkTheUser() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyisoft.associations.activity.base.BaseActivity#initIntent()
	 */
	@Override
	public void initIntent() {
		mBundle = getIntent().getExtras();
		if (mBundle != null) {
			mCurrentState = mBundle.getInt(Config.MAIN_ACTIVITY, 0);
		}
	}

	@Override
	public int setTitleLeftImageId() {
		return super.setTitleLeftImageId();
	}

	@Override
	public int getLayoutId() {
		return 0;
	}

	@Override
	public void initView() {

		ll_home = (LinearLayout) findViewById(R.id.ll_home);
		ll_move = (LinearLayout) findViewById(R.id.ll_move);
		ll_association = (LinearLayout) findViewById(R.id.ll_association);
		ll_notify = (LinearLayout) findViewById(R.id.ll_notify);
		ll_me = (LinearLayout) findViewById(R.id.ll_me);

		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_move = (ImageView) findViewById(R.id.iv_move);
		iv_association = (ImageView) findViewById(R.id.iv_association);
		iv_notify = (ImageView) findViewById(R.id.iv_notify);
		iv_me = (ImageView) findViewById(R.id.iv_me);

		tv_home = (TextView) findViewById(R.id.tv_home);
		tv_move = (TextView) findViewById(R.id.tv_move);
		tv_association = (TextView) findViewById(R.id.tv_association);
		tv_notify = (TextView) findViewById(R.id.tv_notify);
		tv_me = (TextView) findViewById(R.id.tv_me);
		iv_title_left.setVisibility(View.GONE);
		initPopWindow();
		chooseTheState(mCurrentState);

	}

	@Override
	public void initListener() {
		ll_home.setOnClickListener(this);
		ll_move.setOnClickListener(this);
		ll_association.setOnClickListener(this);
		ll_notify.setOnClickListener(this);
		ll_me.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		if (mCurrentState == ME) {
			initFragmentMe();
		}
		if (mCurrentState == ASSOCIATION) {
			mAssociationFragment = new FragmentAssociation();
			replaceFragment(mAssociationFragment);
			changeTheTitle("社团");
			changeTheColor(iv_association, tv_association,
					R.drawable.corporation_c);
		}
		super.onResume();
	}

	/**
	 * 选择当前的当前的状态
	 * 
	 * @param state
	 */
	private void chooseTheState(int state) {
		switch (state) {
		case 0:
			mCurrentState = HOME;
			initFragmentHome();
			break;

		case 1:
			mCurrentState = MOVE;
			setAllImagetitle(0, 0, 0, R.drawable.more);
			iv_title_right3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showPop(iv_title_right3, 0, 20);
				}
			});
			initFragmentMove();
			break;
		case 2:
			mCurrentState = ASSOCIATION;
			setAlltitle(null, null, "创建");
			tv_title_right.setVisibility(View.VISIBLE);
			initFragmentAssociation();

			break;
		case 3:
			mCurrentState = NOTIFY;
			initFragmentNotify();
			break;
		case 4:
			mCurrentState = ME;
			iv_title_right2.setVisibility(View.VISIBLE);
			initFragmentMe();

			break;

		}
	}

	@Override
	public void onClick(View v) {
		iv_title_right2.setVisibility(View.GONE);
		iv_title_right3.setVisibility(View.GONE);
		setAlltitle(null, null, "");
		resetTheColor();
		switch (v.getId()) {
		case R.id.ll_home:
			mCurrentState = HOME;
			initFragmentHome();
			break;

		case R.id.ll_move:
			mCurrentState = MOVE;
			setAllImagetitle(0, 0, 0, R.drawable.more);
			iv_title_right3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					showPop(iv_title_right3, 0, 40);
				}
			});
			initFragmentMove();
			break;
		case R.id.ll_association:
			mCurrentState = ASSOCIATION;
			setAlltitle(null, null, "创建");
			tv_title_right.setVisibility(View.VISIBLE);
			tv_title_right.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (IsLogin()) {
						mApp.startActivity(MainActivity.this,
								AssociationCreateActivity.class, null);
					} else {
						addLogin();
					}
				}
			});
			initFragmentAssociation();

			break;
		case R.id.ll_notify:
			mCurrentState = NOTIFY;
			initFragmentNotify();
			break;
		case R.id.ll_me:
			mCurrentState = ME;
			iv_title_right2.setVisibility(View.VISIBLE);
			initFragmentMe();

			break;
		// case R.id.tv_title_right:
		// tv_title_right.setVisibility(View.VISIBLE);
		//
		// switch (mCurrentState) {
		// case 1:
		// mApp.startActivity(this, MoveCreateActivity.class, null);
		// break;
		// case 2:
		// mApp.startActivity(this, AssociationCreateActivity.class, null);
		// break;
		// }
		// break;
		}
	}

	/**
	 * 初始化FragmentMe()
	 */
	public void initFragmentMe() {
		changeTheColor(iv_me, tv_me, R.drawable.personal_c);
		if (IsLogin()) {
			if (mMeFragment == null) {
				mMeFragment = new FragmentMe();
			}
			replaceFragment(mMeFragment);
			changeTheTitle("我的");
			iv_title_right2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mApp.startActivity(MainActivity.this,
							MeSettingActivity.class, null);
				}
			});
		} else {
			addLogin();
		}
	}

	/**
	 * 添加登陆
	 */
	private void addLogin() {
		if (mLoginFragment == null) {
			mLoginFragment = new FragmentLogin();
		}
		replaceFragment(mLoginFragment);
		setAlltitle("", "登录", "忘记密码？");
		tv_title_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mApp.startActivity(MainActivity.this,
						ForgetPwdPhoneActivity.class, null);
			}
		});
		iv_title_right2.setVisibility(View.GONE);
	}

	/**
	 * 是否登登录
	 * 
	 * @return
	 */
	public boolean IsLogin() {
		ModelUser user = mApp.getUser();
		if (user != null && user.getMobile() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 初始化FragmentNotify()
	 */

	private void initFragmentNotify() {
		if (mNotifyFragment == null) {
			mNotifyFragment = new FragmentNotify();
		}
		replaceFragment(mNotifyFragment);
		changeTheTitle("通知");
		changeTheColor(iv_notify, tv_notify, R.drawable.inform_);
	}

	/**
	 * 初始化FragmentAssociation()
	 */
	private void initFragmentAssociation() {
		if (mAssociationFragment == null) {
			mAssociationFragment = new FragmentAssociation();
		}
		replaceFragment(mAssociationFragment);
		changeTheTitle("社团");
		changeTheColor(iv_association, tv_association, R.drawable.corporation_c);
	}

	/**
	 * 初始化FragmentMove()
	 */
	private void initFragmentMove() {
		if (mMoveFragment == null) {
			mMoveFragment = new FragmentMove();
		}
		replaceFragment(mMoveFragment);
		changeTheTitle("活动");
		changeTheColor(iv_move, tv_move, R.drawable.activity_c);
	}

	/**
	 * 初始化FragmentHome()
	 */
	private void initFragmentHome() {
		if (mHomeFragment == null) {
			mHomeFragment = new FragmentHome();
		}
		replaceFragment(mHomeFragment);
		changeTheTitle("线团");
		changeTheColor(iv_home, tv_home, R.drawable.home_c);
	}

	private void replaceFragment(BaseFragment fragment) {
		if (!fragment.isAdded()) {
			FragmentTransaction mFTransaction = mFManager.beginTransaction();
			mFTransaction.replace(R.id.ll_content, fragment).commit();
		}
	}

	/**
	 * 改变这两个控件颜色
	 * 
	 * @param imageView
	 *            需要改变的imageview控件
	 * @param textView
	 *            需要改變的textview控件
	 */
	private void changeTheColor(ImageView imageView, TextView textView,
			int imageid) {
		if (imageView != null && textView != null) {
			imageView.setImageDrawable(getResources().getDrawable(imageid));
			textView.setTextColor(getResources().getColor(R.color.main_color));
		}
	}

	/**
	 * 把这两个控件设置颜色重置
	 */
	private void resetTheColor() {
		iv_home.setImageDrawable(getResources().getDrawable(R.drawable.home));
		iv_move.setImageDrawable(getResources()
				.getDrawable(R.drawable.activity));
		iv_association.setImageDrawable(getResources().getDrawable(
				R.drawable.corporation));
		iv_notify.setImageDrawable(getResources()
				.getDrawable(R.drawable.inform));
		iv_me.setImageDrawable(getResources().getDrawable(R.drawable.personal));
		tv_home.setTextColor(getResources().getColor(R.color.text_gray));
		tv_move.setTextColor(getResources().getColor(R.color.text_gray));
		tv_association.setTextColor(getResources().getColor(R.color.text_gray));
		tv_notify.setTextColor(getResources().getColor(R.color.text_gray));
		tv_me.setTextColor(getResources().getColor(R.color.text_gray));
	}

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private TextView me_join;
	private TextView me_create;
	private TextView me_watch;
	private TextView create_online_move;
	private TextView create_notOnline_move;

	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.move_menu, null);
			mPopupWindow = new PopupWindow(popView,
					UIUtils.getWindowWidth(getApplicationContext()) / 10 * 4,
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
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.alpha = alpha;
		getWindow().setAttributes(params);
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		PopWindowItemListener listener = new PopWindowItemListener();
		me_join.setOnClickListener(listener);
		me_create.setOnClickListener(listener);
		me_watch.setOnClickListener(listener);
		create_online_move.setOnClickListener(listener);
		create_notOnline_move.setOnClickListener(listener);
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		me_join = (TextView) popView.findViewById(R.id.me_join);
		me_create = (TextView) popView.findViewById(R.id.me_create);
		me_watch = (TextView) popView.findViewById(R.id.me_watch);
		create_online_move = (TextView) popView
				.findViewById(R.id.create_online_move);
		create_notOnline_move = (TextView) popView
				.findViewById(R.id.create_notOnline_move);
	}

	private class PopWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.me_join:
				mPopupWindow.dismiss();
				Bundle bundle = new Bundle();
				bundle.putInt(Config.SEND_ACTIVITY_DATA, MoveMyActivity.MY_JOIN);
				mApp.startActivity(MainActivity.this, MoveMyActivity.class,
						bundle);
				break;

			case R.id.me_create:
				mPopupWindow.dismiss();
				Bundle bundle2 = new Bundle();
				bundle2.putInt(Config.SEND_ACTIVITY_DATA,
						MoveMyActivity.MY_CREATE);
				mApp.startActivity(MainActivity.this, MoveMyActivity.class,
						bundle2);
				break;
			case R.id.me_watch:
				mPopupWindow.dismiss();
				Bundle bundle3 = new Bundle();
				bundle3.putInt(Config.SEND_ACTIVITY_DATA,
						MoveMyActivity.MY_WATCH);
				mApp.startActivity(MainActivity.this, MoveMyActivity.class,
						bundle3);
				break;
			case R.id.create_online_move:
				mPopupWindow.dismiss();
				Bundle online = new Bundle();
				online.putInt(Config.SEND_ACTIVITY_DATA,
						MoveCreateActivity.ONLINE);
				mApp.startActivity(MainActivity.this, MoveCreateActivity.class,
						online);
				break;
			case R.id.create_notOnline_move:
				mPopupWindow.dismiss();
				Bundle notonline = new Bundle();
				notonline.putInt(Config.SEND_ACTIVITY_DATA,
						MoveCreateActivity.NOT_ONLINE);
				mApp.startActivity(MainActivity.this, MoveCreateActivity.class,
						notonline);
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
		mPopupWindow.showAsDropDown(parent, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
		setWindowAlpha(0.7f);
	}
}
