package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.AssociationAdapter;
import com.zhiyisoft.associations.adapter.MoveAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMove extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	private Context mContext;
	private int[] mImageArray;
	private String[] mStringName;
	private LayoutInflater mLayoutInflater;
	private LinearLayout move_ll;
	// 获取控件
	private ImageView move_iv_zoom;
	private EditText move_et_zoom;
	private TextView move_arround_tv;
	private TextView move_my_tv;
	private TextView tv_bottom_line;
	private int mItemWidth = 0;

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_move;
	}

	@Override
	public void initView() {
		move_iv_zoom = (ImageView) findViewById(R.id.move_iv_zoom);
		move_et_zoom = (EditText) findViewById(R.id.move_et_zoom);
		move_arround_tv = (TextView) findViewById(R.id.move_arround_tv);
		move_my_tv = (TextView) findViewById(R.id.move_my_tv);
		tv_bottom_line = (TextView) findViewById(R.id.tv_bottom_line);

		mContext = getActivity();
		move_ll = (LinearLayout) findViewById(R.id.move_ll);
		mLayoutInflater = LayoutInflater.from(mContext);
		mListView = (MoveListview) findViewById(R.id.move_lv);
		mAdapter = new MoveAdapter(this, mlist);
		mListView.setAdapter(mAdapter);
		mItemWidth = UIUtils.getWindowWidth(mContext) / 2;
	}

	@Override
	public void initListener() {
		move_my_tv.setOnClickListener(this);
		move_arround_tv.setOnClickListener(this);
	}

	@Override
	public void initData() {
		tv_bottom_line.setWidth(mItemWidth);
		addHotSorting();
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting() {
		mImageArray = new int[] { R.drawable.ss, R.drawable.hz, R.drawable.yc,
				R.drawable.jh, R.drawable.jl, R.drawable.ty, R.drawable.lx,
				R.drawable.gy, R.drawable.qt };
		mStringName = new String[] { "全部", "赛事", "演出", "聚会", "交流", "体育", "旅行",
				"公益", "其它" };
		View itemView = null;
		ImageView imageView = null;
		TextView textView;
		for (int i = 0; i < mImageArray.length; i++) {
			itemView = mLayoutInflater.inflate(R.layout.association_hsv_item,
					null);
			imageView = (ImageView) itemView.findViewById(R.id.school_scv_iv);
			textView = (TextView) itemView.findViewById(R.id.school_scv_tv);
			imageView.setImageResource(mImageArray[i]);
			textView.setText(mStringName[i] + "");
			move_ll.addView(itemView);
		}
	}

	private int mCurrentDes = 0;

	@Override
	public void onClick(View v) {
		Animation animation = null;
		switch (v.getId()) {
		case R.id.move_arround_tv:
			animation = new TranslateAnimation(mCurrentDes, 0, 0, 0);
			mCurrentDes = 0 * mItemWidth;
			break;

		case R.id.move_my_tv:
			animation = new TranslateAnimation(mCurrentDes, mItemWidth, 0, 0);
			mCurrentDes = 1 * mItemWidth;
			break;
		}
		animation.setFillAfter(true);
		animation.setDuration(300);
		tv_bottom_line.startAnimation(animation);
	}

}
