package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationDisplayActivity;
import com.zhiyisoft.associations.activity.MeSettingProvinceActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationMainAdapter extends BAdapter {
	private ViewHolder mViewHolder;
	private View mFirstView;
	private View mOtherView; // 真正的item
	private int[] mImageArray;
	private String[] mStringName;

	public AssociationMainAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
		mViewHolder = new ViewHolder();
	}

	public AssociationMainAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
		mViewHolder = new ViewHolder();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == 0) {
			mFirstView = mInflater.inflate(R.layout.copyoffragment_association,
					null);
			initView();
			addHotSorting();
			initListener();
			return mFirstView;
		}
		return mInflater.inflate(R.layout.listview_me_association_item, null);
	}

	private void initView() {
		mViewHolder.school_ll = (LinearLayout) mFirstView
				.findViewById(R.id.school_ll);
		mViewHolder.school_rl_change = (RelativeLayout) mFirstView
				.findViewById(R.id.school_rl_change);
		mViewHolder.school_iv_change = (ImageView) mFirstView
				.findViewById(R.id.school_iv_change);
		mViewHolder.school_tv = (TextView) mFirstView
				.findViewById(R.id.school_tv);
		getCurrentSchool(mViewHolder.school_tv);
	}

	private void initListener() {
		mViewHolder.school_rl_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mApp.startActivity(mBaseActivity,
						MeSettingProvinceActivity.class, null);
			}
		});
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting() {
		mViewHolder.school_ll.removeAllViews();
		mImageArray = new int[] { R.drawable.qb, R.drawable.zygy_,
				R.drawable.shsj, R.drawable.xsxx, R.drawable.jycy,
				R.drawable.xqah, R.drawable.xlhd, R.drawable.qt };
		mStringName = new String[] { "全部", "志愿公益", "社会实践", "学术学习", "就业创业",
				"兴趣爱好", "心理活动", "其它" };
		View itemView = null;
		ImageView imageView = null;
		TextView textView;
		final List<TextView> views = new ArrayList<TextView>();
		for (int i = 0; i < mImageArray.length; i++) {
			itemView = mInflater.inflate(R.layout.association_hsv_item, null);
			imageView = (ImageView) itemView.findViewById(R.id.school_scv_iv);
			textView = (TextView) itemView.findViewById(R.id.school_scv_tv);
			views.add(textView);
			imageView.setImageResource(mImageArray[i]);
			textView.setText(mStringName[i] + "");
			itemView.setTag(mStringName[i]);
			itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String data = (String) v.getTag();
					// resetBackground();
					// textView.setBackgroundResource(R.drawable.tv_gray);
					Bundle bundle = new Bundle();
					bundle.putString(Config.HOTCATEGORY, data);
					mApp.startActivity(mBaseActivity,
							AssociationDisplayActivity.class, bundle);
				}

				// /**
				// * 重置背景
				// */
				// private void resetBackground() {
				// for (TextView tv : views) {
				// tv.setBackgroundResource(R.color.main_white_pure_color);
				// }
				// }
			});
			mViewHolder.school_ll.addView(itemView);
		}
	}

	/**
	 * 获取当前的省份
	 */
	private void getCurrentSchool(TextView tv) {
		SharedPreferences preferences = mBaseActivity.getSharedPreferences(
				Config.USER_DATA, Activity.MODE_PRIVATE);
		String province = preferences.getString(Config.CURRENT_SCHOOL, null);
		if (province != null) {
			tv.setVisibility(View.VISIBLE);
			tv.setText(province + "");
			return;
		}
		tv.setVisibility(View.GONE);
	}

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
