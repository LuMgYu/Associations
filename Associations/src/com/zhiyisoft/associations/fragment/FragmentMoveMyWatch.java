package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.MoveAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMoveMyWatch extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_move_my_watch;
	}

	@Override
	public void initView() {
		if (mListView == null) {
			Log.i("notify",
					"-----------------------调用了initview——mListView == null");
			mListView = (MoveListview) findViewById(R.id.my_watch_lv);
			mAdapter = new MoveAdapter(this, mlist);
			mListView.setAdapter(mAdapter);
		}
	}

	// private void initViewPager() {
	// mViewPager = (ViewPager) findViewById(R.id.notify_vp_content);
	// mFragments.add(new FragmentNotifyMsg());
	// // mFragments.add(new FragmentNotifyNotify()); //存在bug 需要修改
	// mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(),
	// mFragments));
	// mViewPager.setCurrentItem(0);
	// mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
	//
	// @Override
	// public void onPageSelected(int pos) {
	// mViewPager.setCurrentItem(pos);
	// Animation animation = null;
	// switch (pos) {
	// case 0:
	// animation = new TranslateAnimation(150, 0, 0, 0);
	// break;
	// case 1:
	// animation = new TranslateAnimation(0, 150, 0, 0);
	// break;
	// }
	// animation.setFillAfter(true);
	// animation.setDuration(300);
	// mTextBottemLine.startAnimation(animation);
	// }
	//
	// @Override
	// public void onPageScrolled(int arg0, float arg1, int arg2) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onPageScrollStateChanged(int arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	// }

	@Override
	public void initListener() {
	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
