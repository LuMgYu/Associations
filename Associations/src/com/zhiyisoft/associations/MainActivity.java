package com.zhiyisoft.associations;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.testAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.listview.testListview;
import com.zhiyisoft.associations.model.base.Model;

public class MainActivity extends BaseActivity {
	private testListview mListView;
	private BAdapter mAdapter;
	private List<Model> mlist = new ArrayList<Model>();

	@Override
	public String setCenterTitle() {
		// TODO Auto-generated method stub
		return "首页";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		mListView = (testListview) findViewById(R.id.testlv);
//		mlist.add(new ModelItem());
//		mlist.add(new ModelItem());
//		mlist.add(new ModelItem());
		Log.i("hhh", "---------------");
		mAdapter = new testAdapter(this, mlist);
		Log.i("hhh", "---------------end");
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
