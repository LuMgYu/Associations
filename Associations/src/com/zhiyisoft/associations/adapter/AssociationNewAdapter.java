package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationNewAdapter extends BAdapter {
	private ViewHolder mViewHolder;
	private View mOtherView; // 真正的item

	public AssociationNewAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
		mViewHolder = new ViewHolder();
	}

	public AssociationNewAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
		mViewHolder = new ViewHolder();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mOtherView = mInflater.inflate(R.layout.association_single_item, null);
		return mOtherView;
	}

	private void initView() {
	}

	private void initListener() {
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
