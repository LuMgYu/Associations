package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveAdapter extends BAdapter {
	private View mView;

	public MoveAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public MoveAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mView = mInflater.inflate(R.layout.move_item, null);
			initView();
			convertView = mView;
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		bundledataToView(position, mHolder);
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

	private void initView() {
		mHolder.move_smiv_icon = (SmartImageView) mView
				.findViewById(R.id.move_smiv_icon);
		mHolder.move_tv_end = (TextView) mView.findViewById(R.id.move_tv_end);
		mHolder.move_tv_title = (TextView) mView
				.findViewById(R.id.move_tv_title);
		mHolder.move_btn_online = (Button) mView
				.findViewById(R.id.move_btn_online);
		mHolder.move_btn_event = (Button) mView
				.findViewById(R.id.move_btn_event);

		mHolder.move_tv_deadline = (TextView) mView
				.findViewById(R.id.move_tv_deadline);
		mHolder.move_tv_allmove = (TextView) mView
				.findViewById(R.id.move_tv_allmove);
		mHolder.move_tv_content = (TextView) mView
				.findViewById(R.id.move_tv_content);
	}

	// ------------------------------------------------------
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
