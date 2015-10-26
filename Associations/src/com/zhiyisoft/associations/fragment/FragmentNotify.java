package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

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

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现系统通知消息的
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
		if (mListView == null) {
			mListView = (NotifyNfyListview) findViewById(R.id.notify_lv);
			mAdapter = new NotifyNfyAdapter(this, mlist);
			mListView.setAdapter(mAdapter);
			mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					return true;
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
		// TODO Auto-generated method stub

	}

}
