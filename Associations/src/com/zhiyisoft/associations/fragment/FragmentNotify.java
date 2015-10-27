package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.NotifyDetailActivity;
import com.zhiyisoft.associations.adapter.NotifyNfyAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.NotifyIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenu;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenuListView;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现系统通知消息的
 *
 */

public class FragmentNotify extends BaseFragment {
	private SwipeMenuListView mListView;
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
		if (mListView == null) {
			mListView = (SwipeMenuListView) findViewById(R.id.notify_lv);
			mAdapter = new NotifyNfyAdapter(this, mlist);
			mListView.setAdapter(mAdapter);
			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Model model = (Model) parent.getItemAtPosition(position);
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, model);
					mApp.startActivity(mApp.getActivity(),
							NotifyDetailActivity.class, bundle);
				}
			});
			mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(int position, SwipeMenu menu,
						int index) {
					switch (index) {
					case 0:
						ModelNotify notify = (ModelNotify) mAdapter.mList
								.get(position);
						deleNotify(notify);
						mAdapter.mList.remove(position);
						mAdapter.notifyDataSetChanged();
						break;
					}
					return false;
				}
			});
		}
	}

	@Override
	public void initListener() {
	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {

	}

	private void deleNotify(final ModelNotify notify) {
		final NotifyIm notifyIm = mApp.getNotifyIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				notifyIm.delNotify(notify);
			}
		});
	}

}
