package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.AssociationAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMove  extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	private Context mContext;
	private int[] mImageArray;
	private String[] mStringName;
	private LayoutInflater mLayoutInflater;
	private LinearLayout move_ll;

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_move;
	}

	@Override
	public void initView() {
		mContext = getActivity();
		move_ll = (LinearLayout) findViewById(R.id.move_ll);
		mLayoutInflater = LayoutInflater.from(mContext);
		mListView = (MoveListview) findViewById(R.id.move_lv);
		mAdapter = new AssociationAdapter(this, mlist);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void initData() {
		addHotSorting();
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting() {
		mImageArray = new int[] { R.drawable.ss, R.drawable.hz,
				R.drawable.yc, R.drawable.jh, R.drawable.jl,
				R.drawable.ty, R.drawable.lx, R.drawable.gy, R.drawable.qt };
		mStringName = new String[] { "全部", "赛事", "演出", "聚会", "交流",
				"体育", "旅行", "公益", "其它" };
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
