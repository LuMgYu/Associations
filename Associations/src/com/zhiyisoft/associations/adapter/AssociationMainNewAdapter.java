package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMoveActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationMainNewAdapter extends BAdapter {
	private ViewHolder mViewHolder;
	private View mFirstView;
	private View mOtherView; // 真正的item

	public AssociationMainNewAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
		mViewHolder = new ViewHolder();
	}

	public AssociationMainNewAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
		mViewHolder = new ViewHolder();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == 0) {
			mFirstView = mInflater.inflate(
					R.layout.copyofactivity_association_single, null);
			initFirstView();
			initListener();
			return mFirstView;
		}
		mOtherView = mInflater.inflate(R.layout.association_single_item, null);
		initOtherView();
		return mOtherView;
	}

	/**
	 * 初始化第一种item的 view
	 */
	private void initFirstView() {
		if (mFirstView != null) {
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
	}

	/**
	 * 初始化其它共同item的 view
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void initOtherView() {
		if (mOtherView != null) {
			mViewHolder.new_item_rl_head = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_head);
			mViewHolder.new_item_iv = (RoundImageView) mOtherView
					.findViewById(R.id.new_item_iv);
			mViewHolder.title_tv_member = (TextView) mOtherView
					.findViewById(R.id.title_tv_member);
			mViewHolder.new_item_tv_nick = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_nick);
			mViewHolder.new_item_rl_content = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_content);
			mViewHolder.new_item_tv_title = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_title);
			mViewHolder.new_item_tv_content = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_content);
			mViewHolder.new_item_ll = (LinearLayout) mOtherView
					.findViewById(R.id.new_item_ll);
			mViewHolder.imageView1 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView1);
			mViewHolder.imageView2 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView2);
			mViewHolder.imageView3 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView3);
			mViewHolder.new_item_rl_footer = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_footer);
			mViewHolder.new_item_tv_date = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_date);
			mViewHolder.new_item_tv_number = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_number);
			initPhotoWidth();

		}
	}

	/**
	 * 初始化图片的宽度和高度
	 */
	private void initPhotoWidth() {
		int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
		LinearLayout.LayoutParams work1, work2;
		work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work1.leftMargin = 20;
		mViewHolder.imageView1.setLayoutParams(work1);
		work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work2.leftMargin = 10;
		mViewHolder.imageView2.setLayoutParams(work2);
		mViewHolder.imageView3.setLayoutParams(work2);
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
