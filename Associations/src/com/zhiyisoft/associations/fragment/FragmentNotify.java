package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.MyViewPagerAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentNotify extends BaseFragment implements OnClickListener {
	// private testListview mListView;
	// private BAdapter mAdapter;
	// private List<Model> mlist = new ArrayList<Model>();
	private TextView notify_tv_msg;
	private TextView notify_tv_notify;
	private TextView mTextBottemLine;
	private ViewPager mViewPager;
	private ArrayList<BaseFragment> mFragments = new ArrayList<BaseFragment>();

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_notify;
	}

	@Override
	public void initView() {
		// mListView = (testListview) findViewById(R.id.notify_lv);
		// mAdapter = new testAdapter(this, mlist);
		// mListView.setAdapter(mAdapter);
		// private TextView notify_tv_msg;
		// private TextView notify_tv_notify;
		// private TextView notify_tv_progress;
		// private ViewPager mViewPager;
		notify_tv_msg = (TextView) findViewById(R.id.notify_tv_msg);
		notify_tv_notify = (TextView) findViewById(R.id.notify_tv_notify);
		mTextBottemLine = (TextView) findViewById(R.id.notify_tv_progress);
		initViewPager();

	}

	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.notify_vp_content);
		mFragments.add(new FragmentNotifyMsg());
		mFragments.add(new FragmentNotifyNotify());
		mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(),
				mFragments));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
				mViewPager.setCurrentItem(pos);
				Animation animation = null;
				switch (pos) {
				case 0:
					animation = new TranslateAnimation(150, 0, 0, 0);
					break;
				case 1:
					animation = new TranslateAnimation(0, 150, 0, 0);
					break;
				}
				animation.setFillAfter(true);
				animation.setDuration(300);
				mTextBottemLine.startAnimation(animation);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void initListener() {

	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notify_tv_msg:
			mViewPager.setCurrentItem(0);
			break;

		case R.id.notify_tv_notify:
			mViewPager.setCurrentItem(1);
			break;
		}
	}

}
