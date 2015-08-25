package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationDisplayActivity;
import com.zhiyisoft.associations.activity.MeSettingProvinceActivity;
import com.zhiyisoft.associations.adapter.AssociationAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.AssociationListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class CopyOfFragmentAssociation extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	private Context mContext;
	private int[] mImageArray;
	private String[] mStringName;
	private LayoutInflater mLayoutInflater;
	private LinearLayout school_ll;

	private RelativeLayout school_rl_change;
	private ImageView school_iv_change;
	private TextView school_tv;

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_association;
	}

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public void initView() {
		mContext = getActivity();
		school_ll = (LinearLayout) findViewById(R.id.school_ll);
		mLayoutInflater = LayoutInflater.from(mContext);
		mListView = (AssociationListview) findViewById(R.id.school_lv);
		mListView.setPullRefreshEnable(false);
		mAdapter = new AssociationAdapter(this, mlist);
		mListView.setAdapter(mAdapter);

		school_rl_change = (RelativeLayout) findViewById(R.id.school_rl_change);
		school_iv_change = (ImageView) findViewById(R.id.school_iv_change);
		school_tv = (TextView) findViewById(R.id.school_tv);
		getCurrentSchool(school_tv);
	}

	/**
	 * 获取当前的省份
	 */
	private void getCurrentSchool(TextView tv) {
		SharedPreferences preferences = mActivity.getSharedPreferences(
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
	public void initListener() {
		school_rl_change.setOnClickListener(this);

	}

	@Override
	public void initData() {
		addHotSorting();
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting() {
		school_ll.removeAllViews();
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
			itemView = mLayoutInflater.inflate(R.layout.association_hsv_item,
					null);
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
					mApp.startActivity(getActivity(),
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
			school_ll.addView(itemView);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.school_rl_change:
			mApp.startActivity(getActivity(), MeSettingProvinceActivity.class,
					null);
			break;

		default:
			break;
		}

	}

}
