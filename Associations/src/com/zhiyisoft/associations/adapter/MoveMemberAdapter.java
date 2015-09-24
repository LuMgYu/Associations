package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveMemberAdapter extends BAdapter {
	private View mView;

	public MoveMemberAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public MoveMemberAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 现在只是做一个效果，以后需要修改
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mView = mInflater.inflate(R.layout.association_member_item, null);
			initView(holder);
			convertView = mView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bundledataToView(position, holder);
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param holder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		Model model = mList.get(position);
		// TODO 把数据绑定到界面

	}

	private void initView(ViewHolder holder) {
		if (mView != null) {
			holder.member_iv = (RoundImageView) mView
					.findViewById(R.id.member_iv);
			holder.member_tv_name = (TextView) mView
					.findViewById(R.id.member_tv_name);
			holder.member_tv_school = (TextView) mView
					.findViewById(R.id.member_tv_school);
		}
	}

	// ----------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
