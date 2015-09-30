package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelUser;
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
	private ModelEvent mEvent;

	public MoveMemberAdapter(BaseActivity activity, ModelEvent data) {
		super(activity, null);
		this.mEvent = data;
	}

	public MoveMemberAdapter(BaseFragment fragment, ModelEvent data) {
		super(fragment, null);
		this.mEvent = data;
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
		// TODO 把数据绑定到界面
		ModelUser modelUser = (ModelUser) mList.get(position);
		if (holder != null && modelUser != null) {
			mApp.displayImage(modelUser.getFaceurl(), holder.member_iv);
			holder.member_tv_name.setText(modelUser.getUname());
			holder.member_tv_school.setText(modelUser.getSchool_name());
		}

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
		EventImpl eventImpl = mApp.getEventFIm();
		items = eventImpl.memberList(mEvent);
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
