package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationVedioAdapter extends BAdapter {
	private View mVedioView; // 视频view

	public AssociationVedioAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);

	}

	public AssociationVedioAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mVedioView = mInflater
					.inflate(R.layout.move_works_vedio_item, null);
			initVedioWorks();
			convertView = mVedioView;
		} else {
			mHolder = (ViewHolder) convertView.getTag();
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

	/**
	 * 初始化视频作品控件
	 */
	private void initVedioWorks() {
		if (mVedioView != null) {
			mHolder.iv_vedio_user_icon = (RoundImageView) mVedioView
					.findViewById(R.id.iv_vedio_user_icon);
			mHolder.tv_user_name = (TextView) mVedioView
					.findViewById(R.id.tv_user_name);
			mHolder.tv_user_send = (TextView) mVedioView
					.findViewById(R.id.tv_user_send);
			mHolder.tv_vedio_title = (TextView) mVedioView
					.findViewById(R.id.tv_music_name);
			mHolder.iv_vedio = (SmartImageView) mVedioView
					.findViewById(R.id.iv_vedio);
			mHolder.iv_vedio_click = (ImageView) mVedioView
					.findViewById(R.id.iv_vedio_click);
			mHolder.tv_vedio_date = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_date);
			mHolder.tv_vedio_commit = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_commit);

		}
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
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
