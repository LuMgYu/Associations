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
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveAdapter extends BAdapter {
	private View mView;
	private ModelEvent mEvent;

	public MoveAdapter(BaseActivity activity, ModelEvent event) {
		super(activity, null);
		this.mEvent = event;
	}

	public MoveAdapter(BaseFragment fragment, ModelEvent event) {
		super(fragment, null);
		this.mEvent = event;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mView = mInflater.inflate(R.layout.move_item, null);
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
		ModelEvent event = (ModelEvent) mList.get(position);
		if (event != null) {
			holder.move_smiv_icon.setImageUrl(event.getLogourl());
			int isover = event.getIsover();
			if (isover == 0) {
				holder.move_tv_end.setVisibility(View.GONE);
			} else {
				holder.move_tv_end.setVisibility(View.GONE);
			}
			holder.move_tv_title.setText(event.getTitle());
			String isonline = event.getOnline();
			if (isonline.equals("0")) {
				holder.move_btn_online.setText("线上");
			} else {
				holder.move_btn_online.setText("线下");
			}
			holder.move_btn_event.setText(event.getTypeName());

			holder.move_tv_deadline
					.setText(DateUtil.strTodate(event.getsTime()) + "-"
							+ DateUtil.strTodate(event.geteTime()));
			holder.move_tv_allmove.setText(event.getJoinCount());
			holder.move_tv_content.setText(event.getExplain());
		}
		// TODO 把数据绑定到界面

	}

	private void initView(ViewHolder holder) {
		holder.move_smiv_icon = (SmartImageView) mView
				.findViewById(R.id.move_smiv_icon);
		holder.move_tv_end = (TextView) mView.findViewById(R.id.move_tv_end);
		holder.move_tv_title = (TextView) mView
				.findViewById(R.id.move_tv_title);
		holder.move_btn_online = (Button) mView
				.findViewById(R.id.move_btn_online);
		holder.move_btn_event = (Button) mView
				.findViewById(R.id.move_btn_event);

		holder.move_tv_deadline = (TextView) mView
				.findViewById(R.id.move_tv_deadline);
		holder.move_tv_allmove = (TextView) mView
				.findViewById(R.id.move_tv_allmove);
		holder.move_tv_content = (TextView) mView
				.findViewById(R.id.move_tv_content);
	}

	// ------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		EventImpl eventImpl = mApp.getEventFIm();
		List<Model> items = eventImpl.eventList(mEvent);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		EventImpl eventImpl = mApp.getEventFIm();
		List<Model> items = eventImpl.eventList(mEvent);
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
