package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
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

public class AssociationNewAdapter extends BAdapter {
	private View mOtherView; // 真正的item

	public AssociationNewAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public AssociationNewAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mOtherView = mInflater.inflate(R.layout.association_single_item,
					null);
			convertView = mOtherView;
			initView(holder);
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
	 * @param mHolder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		Model model = mList.get(position);
		// TODO 把数据绑定到界面

	}

	/**
	 * 初始化控件
	 */
	private void initView(ViewHolder holder) {
		if (mOtherView != null) {
			holder.new_item_rl_head = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_head);
			holder.new_item_iv = (RoundImageView) mOtherView
					.findViewById(R.id.new_item_iv);
			holder.title_tv_member = (TextView) mOtherView
					.findViewById(R.id.title_tv_member);
			holder.new_item_tv_nick = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_nick);
			holder.new_item_rl_content = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_content);
			holder.new_item_tv_title = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_title);
			holder.new_item_tv_content = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_content);
			holder.new_item_ll = (LinearLayout) mOtherView
					.findViewById(R.id.new_item_ll);
			holder.imageView1 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView1);
			holder.imageView2 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView2);
			holder.imageView3 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView3);
			holder.new_item_rl_footer = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_footer);
			holder.new_item_tv_date = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_date);
			holder.new_item_tv_number = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_number);
			initPhotoWidth(holder);

		}
	}

	/**
	 * 初始化图片的宽度和高度
	 */
	private void initPhotoWidth(ViewHolder holder) {
		int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
		LinearLayout.LayoutParams work1, work2;
		work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work1.leftMargin = 20;
		holder.imageView1.setLayoutParams(work1);
		work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work2.leftMargin = 10;
		holder.imageView2.setLayoutParams(work2);
		holder.imageView3.setLayoutParams(work2);
	}

	private void initListener() {

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
