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
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationMainAdapter extends BAdapter {
	private final int TYPE_COUNT = 2;
	private final int TYPE_FIRSTVIEW = 0;
	private final int TYPE_OTHERVIEW = 1;

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
		int type = judgeTheViewType(position);
		if (convertView == null) {
			convertView = initConvertView(convertView, type);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		if (position > 0) {
			bundledataToView(position, mViewHolder);
		}
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param mHolder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		ModelLeague league = (ModelLeague) mList.get(position);
		resetView(holder);
		// TODO 把数据绑定到界面
		if (holder != null) {
			holder.association_iv_icon.setImageUrl(league.getLogourl() + "");
			holder.association_tv_title.setText(league.getName() + "");
			holder.association_tv_member.setText(league.getMembers() + "");
			holder.association_tv_content.setText(league.getDescription() + "");
		}
	}

	/**
	 * 把view设置为默认状态，特别是图片，这样有利于内存的回收，房子内存泄露，导致崩溃
	 * 
	 * @param holder
	 */
	private void resetView(ViewHolder holder) {
		holder.association_iv_icon
				.setImageResource(R.drawable.default_image_small);
		holder.association_tv_title.setText("unknow");
		holder.association_tv_member.setText("unknow");
		holder.association_tv_content.setText("unknow");
	}

	/**
	 * 加载缓存view
	 * 
	 * @param view
	 * @param type
	 * @return
	 */
	public View initConvertView(View view, int type) {
		if (type == TYPE_FIRSTVIEW) {
			mFirstView = mInflater.inflate(R.layout.copyoffragment_association,
					null);
			initmFirstView();
			addHotSorting();
			initListener();
			view = mFirstView;
		} else if (type == TYPE_OTHERVIEW) {
			mOtherView = mInflater.inflate(
					R.layout.listview_me_association_item, null);
			initOtherView();
			view = mOtherView;
		}
		view.setTag(mViewHolder);
		return view;
	}

	private void initmFirstView() {
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

	private void initOtherView() {
		mViewHolder.association_iv_icon = (RoundImageView) mOtherView
				.findViewById(R.id.association_iv_icon);
		mViewHolder.association_tv_title = (TextView) mOtherView
				.findViewById(R.id.association_tv_title);
		mViewHolder.association_tv_member = (TextView) mOtherView
				.findViewById(R.id.association_tv_member);
		mViewHolder.association_tv_content = (TextView) mOtherView
				.findViewById(R.id.association_tv_content);
	}

	/**
	 * 根据返回的数据类型类判断到底要加载哪一个item layout
	 * 
	 * @param pos
	 * @return
	 */
	private int judgeTheViewType(int pos) {
		if (pos == 0) {
			return 0;
		}
		return 1;
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
		int[] categorys = { 0, 30588, 30589, 30590, 30591, 30592, 30593, 30594 };
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
			itemView.setTag(R.id.album, categorys[i]);
			itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String data = (String) v.getTag();
					int category = (Integer) v.getTag(R.id.album);
					ModelLeague league = new ModelLeague();
					league.setCategoryName(data);
					league.setCategoryId(category);
					// resetBackground();
					// textView.setBackgroundResource(R.drawable.tv_gray);
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, league);
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
	 * 获取当前的学校
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

	// ----------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		ModelLeague league = new ModelLeague();
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		List<Model> list = leagueImpl.groupIndex(league);
		return list;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		return items;
	}

	@Override
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		return judgeTheViewType(position);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
