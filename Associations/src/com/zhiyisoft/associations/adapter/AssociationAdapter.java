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

public class AssociationAdapter extends BAdapter {
	private View mView;

	public AssociationAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public AssociationAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mView = mInflater.inflate(R.layout.listview_me_association_item,
					null);
			convertView = mView;
			convertView.setTag(mHolder);
			initView();
		} else {
			mHolder = (ViewHolder) convertView.getTag();
			// TODO 获取list里面的数据，然后添加数据
		}
		bundledataToView(position, mHolder);
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param mHolder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		Model model = mList.get(position);
		// TODO 把数据绑定到界面

	}

	private void initView() {
		if (mView != null) {
			mHolder.association_iv_icon = (RoundImageView) mView
					.findViewById(R.id.association_iv_icon);
			mHolder.association_tv_title = (TextView) mView
					.findViewById(R.id.association_tv_title);
			mHolder.association_tv_member = (TextView) mView
					.findViewById(R.id.association_tv_member);
			mHolder.association_tv_content = (TextView) mView
					.findViewById(R.id.association_tv_content);
		}
	}

	// -----------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
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
