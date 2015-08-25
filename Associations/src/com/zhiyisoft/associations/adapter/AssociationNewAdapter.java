package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMoveActivity;
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

public class AssociationNewAdapter extends BAdapter {
	private ViewHolder mViewHolder;
	private View mFirstView;
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
		if (position == 0) {
			mFirstView = mInflater.inflate(
					R.layout.copyofactivity_association_single, null);
			initView();
			initListener();
			return mFirstView;
		}
		mOtherView = mInflater.inflate(R.layout.association_single_item, null);
		return mOtherView;
	}

	private void initView() {
		mViewHolder.title_iv = (RoundImageView) mFirstView
				.findViewById(R.id.title_iv);
		mViewHolder.title_tv = (TextView) mFirstView
				.findViewById(R.id.title_tv);
		mViewHolder.title_tv_member = (TextView) mFirstView
				.findViewById(R.id.title_tv_member);
		mViewHolder.title_tv_topic = (TextView) mFirstView
				.findViewById(R.id.title_tv_topic);

		mViewHolder.title_tv_school = (TextView) mFirstView
				.findViewById(R.id.title_tv_school);
		mViewHolder.title_tv_type = (TextView) mFirstView
				.findViewById(R.id.title_tv_type);
		mViewHolder.title_tv_move = (TextView) mFirstView
				.findViewById(R.id.title_tv_move);
		mViewHolder.title_rl_move = (RelativeLayout) mFirstView
				.findViewById(R.id.title_rl_move);
	}

	private void initListener() {
		mViewHolder.title_rl_move.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle data1 = new Bundle();
				mApp.startActivity(mBaseActivity,
						AssociationMoveActivity.class, data1);
			}
		});
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
