package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.NotifyNfyAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.NotifyNfyListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentNotify extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_notify;
	}

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public void initView() {
		Log.i("notify", "-----------------------调用了initview");
		if (mListView == null) {
			mListView = (NotifyNfyListview) findViewById(R.id.notify_lv);
			mAdapter = new NotifyNfyAdapter(this, mlist);
			mListView.setAdapter(mAdapter);
			mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					TextView tv = (TextView) view.findViewById(R.id.tv_del);
					tv.setVisibility(View.VISIBLE);
					return true;
				}
			});
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

	//
	// @Override
	// public void onDetach() {
	// super.onDetach();
	//
	// try {
	// Field childFragmentManager = Fragment.class
	// .getDeclaredField("mChildFragmentManager");
	// childFragmentManager.setAccessible(true);
	// childFragmentManager.set(this, null);
	//
	// } catch (NoSuchFieldException e) {
	// throw new RuntimeException(e);
	// } catch (IllegalAccessException e) {
	// throw new RuntimeException(e);
	// }
	// }

}
