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
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationAlbumAdapter extends BAdapter {
	private View mView;

	public AssociationAlbumAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public AssociationAlbumAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mView = mInflater.inflate(R.layout.association_album_item, null);
			convertView = mView;
			convertView.setTag(mHolder);
			initView();
		} else {
			mHolder = (ViewHolder) convertView.getTag();
			// TODO 获取list里面的数据，然后添加数据
		}
		return convertView;
	}

	private void initView() {
		if (mView != null) {
			mHolder.album_iv = (SmartImageView) mView
					.findViewById(R.id.album_iv);
			mHolder.album_tv_name = (TextView) mView
					.findViewById(R.id.album_tv_name);
			mHolder.album_tv_count = (TextView) mView
					.findViewById(R.id.album_tv_count);
			mHolder.album_tv_date = (TextView) mView
					.findViewById(R.id.album_tv_date);
		}
	}

	// -------------------------------------------------
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
