package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationDisplayActivity;
import com.zhiyisoft.associations.activity.MeSettingProvinceActivity;
import com.zhiyisoft.associations.activity.MoveDisplayActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveMainAdapter extends BAdapter {
	private ViewHolder mViewHolder;
	private View mFirstView;
	private View mOtherView; // 真正的item
	private int[] mImageArray;
	private String[] mStringName;

	private int mItemWidth = 0;

	public MoveMainAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
		mViewHolder = new ViewHolder();
	}

	public MoveMainAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
		mViewHolder = new ViewHolder();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == 0) {
			mFirstView = mInflater.inflate(R.layout.copyoffragment_move, null);
			initView();
			addHotSorting();
			initListener();
			return mFirstView;
		}
		return mInflater.inflate(R.layout.move_item, null);
	}

	private void initView() {
		mViewHolder.move_ll = (LinearLayout) mFirstView
				.findViewById(R.id.move_ll);
		mViewHolder.move_iv_zoom = (ImageView) mFirstView
				.findViewById(R.id.move_iv_zoom);
		mViewHolder.move_et_zoom = (EditText) mFirstView
				.findViewById(R.id.move_et_zoom);
		mViewHolder.move_arround_tv = (TextView) mFirstView
				.findViewById(R.id.move_arround_tv);
		mViewHolder.move_my_tv = (TextView) mFirstView
				.findViewById(R.id.move_my_tv);
		mViewHolder.tv_bottom_line = (TextView) mFirstView
				.findViewById(R.id.tv_bottom_line);
		mItemWidth = UIUtils.getWindowWidth(mBaseActivity) / 2;
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting() {
		mViewHolder.move_ll.removeAllViews();
		mImageArray = new int[] { R.drawable.qb, R.drawable.ss, R.drawable.hz,
				R.drawable.yc, R.drawable.jh, R.drawable.jl, R.drawable.ty,
				R.drawable.lx, R.drawable.gy, R.drawable.qt };
		mStringName = new String[] { "全部", "赛事", "会展", "演出", "聚会", "交流", "体育",
				"旅行", "公益", "其它" };
		View itemView = null;
		ImageView imageView = null;
		TextView textView;
		final List<TextView> views = new ArrayList<TextView>();
		for (int i = 0; i < mImageArray.length; i++) {
			itemView = mInflater.inflate(R.layout.association_hsv_item, null);
			imageView = (ImageView) itemView.findViewById(R.id.school_scv_iv);
			textView = (TextView) itemView.findViewById(R.id.school_scv_tv);
			imageView.setImageResource(mImageArray[i]);
			textView.setText(mStringName[i] + "");
			itemView.setTag(mStringName[i]);
			itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String data = (String) v.getTag();
					resetBackground();
					// textView.setBackgroundResource(R.drawable.tv_gray);
					Bundle bundle = new Bundle();
					bundle.putString(Config.HOTCATEGORY, data);
					mApp.startActivity(mBaseActivity,
							MoveDisplayActivity.class, bundle);
				}

				/**
				 * 重置背景
				 */
				private void resetBackground() {
					for (TextView tv : views) {
						tv.setBackgroundResource(R.color.main_white_pure_color);
					}
				}
			});
			mViewHolder.move_ll.addView(itemView);
		}
	}

	private void initListener() {
		mViewHolder.move_my_tv.setOnClickListener(new MyOnClickListener());
		mViewHolder.move_arround_tv.setOnClickListener(new MyOnClickListener());
	}

	private int mCurrentDes = 0;

	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Animation animation = null;
			switch (v.getId()) {
			case R.id.move_arround_tv:
				animation = new TranslateAnimation(mCurrentDes, 0, 0, 0);
				mCurrentDes = 0 * mItemWidth;
				break;

			case R.id.move_my_tv:
				animation = new TranslateAnimation(mCurrentDes, mItemWidth, 0,
						0);
				mCurrentDes = 1 * mItemWidth;
				break;
			}
			animation.setFillAfter(true);
			animation.setDuration(300);
			mViewHolder.tv_bottom_line.startAnimation(animation);
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
