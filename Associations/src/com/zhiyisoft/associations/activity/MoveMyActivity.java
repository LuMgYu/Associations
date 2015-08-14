package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyViewPagerAdapter;
import com.zhiyisoft.associations.fragment.FragmentMoveMyCreate;
import com.zhiyisoft.associations.fragment.FragmentMoveMyJoin;
import com.zhiyisoft.associations.fragment.FragmentMoveMyWatch;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveMyActivity extends BaseActivity {
	private TextView tv_my_join;
	private TextView tv_my_watch;
	private TextView tv_my_create;
	private TextView mTextBottemLine;
	private ViewPager mViewPager;
	private List<BaseFragment> mFragments = new ArrayList<BaseFragment>();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAllImagetitle(0, 0, 0, R.drawable.more);
	}

	@Override
	public String setCenterTitle() {
		return "我的活动";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_my;
	}

	@Override
	public void initView() {
		tv_my_join = (TextView) findViewById(R.id.tv_my_join);
		tv_my_watch = (TextView) findViewById(R.id.tv_my_watch);
		tv_my_create = (TextView) findViewById(R.id.tv_my_create);
		mTextBottemLine = (TextView) findViewById(R.id.bottom_line);
		initViewPager();
	}

	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.move_my_viewpager);
		mFragments.add(new FragmentMoveMyJoin());
		mFragments.add(new FragmentMoveMyWatch());
		mFragments.add(new FragmentMoveMyCreate());
		mViewPager.setAdapter(new MyViewPagerAdapter(mFManager, mFragments));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			private float mCurrentDes = 0;
			private float moveWidth = UIUtils
					.getWindowWidth(getApplicationContext()) / 3;

			@Override
			public void onPageSelected(int pos) {
				mViewPager.setCurrentItem(pos);
				Animation animation = null;
				switch (pos) {
				case 0:
					animation = new TranslateAnimation(mCurrentDes, 0, 0, 0);
					mCurrentDes = 0 * moveWidth;
					break;
				case 1:
					animation = new TranslateAnimation(mCurrentDes, moveWidth,
							0, 0);
					mCurrentDes = 1 * moveWidth;
					break;
				case 2:
					animation = new TranslateAnimation(mCurrentDes,
							moveWidth * 2, 0, 0);
					mCurrentDes = 2 * moveWidth;
					break;
				}
				animation.setFillAfter(true);
				animation.setDuration(300);
				mTextBottemLine.startAnimation(animation);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		tv_my_join.setOnClickListener(this);
		tv_my_watch.setOnClickListener(this);
		tv_my_create.setOnClickListener(this);
		iv_title_right3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_right:
			initPopWindow();
			showPop(tv_title_right, 0, 10);
			break;
		// --------------------------PopupWindow的界面控件监听器------------------
		case R.id.ll_essay:
			Bundle data = new Bundle();
			mApp.startActivity(this, MoveSendEssayActivity.class, data);
			break;
		case R.id.ll_pic:
			mApp.startActivity(this, MoveSendPhotoActivity.class, null);
			break;
		case R.id.ll_music:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, MoveSendMusicActivity.class, data2);
			break;
		case R.id.ll_vedio:
			Bundle data3 = new Bundle();
			mApp.startActivity(this, MoveSendVedioActivity.class, data3);
			break;
		case R.id.tv_my_join:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.tv_my_watch:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.tv_my_create:
			mViewPager.setCurrentItem(2);
			break;
		case R.id.iv_title_right3:
			initPopWindow();
			showPop(iv_title_right3, 0, 10);
			break;
		// --------------------------PopWindow控件监听器--------------------------------------
		case R.id.me_join:
			mApp.startActivity(this, MoveMyActivity.class, null);
			break;
		case R.id.me_create:
			mApp.startActivity(this, MoveMyActivity.class, null);
			break;
		case R.id.me_watch:
			mApp.startActivity(this, MoveMyActivity.class, null);
			;
			break;
		case R.id.create_online_move:
			mApp.startActivity(this, MoveCreateActivity.class, null);
			break;
		case R.id.create_notOnline_move:
			mApp.startActivity(this, MoveCreateActivity.class, null);
			break;

		}

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
			//需要加载的布局
			View popView = mInflater.inflate(R.layout.move_menu, null);
			mPopupWindow = new PopupWindow(popView, 330,
					LayoutParams.WRAP_CONTENT);
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			// 设置popwindow出现和消失动画
			initPopWidge(popView);
			setPopListener();
		}
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		me_join.setOnClickListener(this);
		me_create.setOnClickListener(this);
		me_watch.setOnClickListener(this);
		create_online_move.setOnClickListener(this);
		create_notOnline_move.setOnClickListener(this);
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

	/**
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAsDropDown(parent, x, y);
//		mPopupWindow.showAtLocation(parent, gravity, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
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

}
