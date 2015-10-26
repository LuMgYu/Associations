package com.zhiyisoft.associations.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MoveLocationDisplayActivity.LocationResultListener;
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
	private ModelEvent mEvent;
	LocationResultListener mListener;

	public MoveAdapter(BaseActivity activity, ModelEvent event) {
		super(activity, null);
		this.mEvent = event;
	}

	public MoveAdapter(BaseFragment fragment, ModelEvent event) {
		super(fragment, null);
		this.mEvent = event;
	}

	public MoveAdapter(BaseActivity activity, ModelEvent event,
			LocationResultListener listener) {
		super(activity, null);
		this.mEvent = event;
		this.mListener = listener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initView(holder, convertView);
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
			mApp.displayImage(event.getLogourl(), holder.move_smiv_icon);
			// holder.move_smiv_icon.setImageUrl(event.getLogourl());
			int isover = event.getIsover();
			if (isover == 0) {
				holder.move_tv_end.setVisibility(View.GONE);
			} else {
				holder.move_tv_end.setVisibility(View.VISIBLE);
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
			if (event.getLatitude() > 0 && event.getLongtitude() > 0) {
				LatLng latLng = new LatLng(event.getLatitude(),
						event.getLongtitude());
				LatLng latLng2 = new LatLng(mEvent.getLatitude(),
						mEvent.getLongtitude());
				double distance = DistanceUtil.getDistance(latLng2, latLng);
				double hh = distance / 1000;
				DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
				String dis = df.format(hh);// 返回的是String类型的
				holder.move_tv_distance.setVisibility(View.VISIBLE);
				holder.move_tv_distance.setText(dis + "km");
			}
		}
		// TODO 把数据绑定到界面

	}

	private View initView(ViewHolder holder, View parent) {
		parent = mInflater.inflate(R.layout.move_item, null);
		holder.move_smiv_icon = (SmartImageView) parent
				.findViewById(R.id.move_smiv_icon);
		holder.move_tv_end = (TextView) parent.findViewById(R.id.move_tv_end);
		holder.move_tv_title = (TextView) parent
				.findViewById(R.id.move_tv_title);
		holder.move_btn_online = (Button) parent
				.findViewById(R.id.move_btn_online);
		holder.move_btn_event = (Button) parent
				.findViewById(R.id.move_btn_event);
		holder.move_tv_deadline = (TextView) parent
				.findViewById(R.id.move_tv_deadline);
		holder.move_tv_allmove = (TextView) parent
				.findViewById(R.id.move_tv_allmove);
		holder.move_tv_content = (TextView) parent
				.findViewById(R.id.move_tv_content);
		holder.move_tv_distance = (TextView) parent
				.findViewById(R.id.move_tv_distance);
		return parent;
	}

	// ------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = getMove(1);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getMove(1);
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		p++;
		List<Model> items = getMove(p);
		return items;
	}

	@Override
	public void addHeadList(List<Model> list) {
		if (mListener != null) {
			mListener.result(list);
		}
		addHeadListWay2(list);
	}

	/**
	 * 获取社团
	 * 
	 * @return
	 */
	private List<Model> getMove(int index) {
		if (mEvent != null) {
			mEvent.setP(index);
			EventImpl eventImpl = mApp.getEventFIm();
			List<Model> items = null;
			if (mEvent.getTypeName() != null) {
				if (mEvent.getTypeName().equals("周边活动")) {
					items = eventImpl.getNearbyEvents(mEvent);
				} else {
					items = eventImpl.eventList(mEvent);
				}
			} else {
				items = eventImpl.eventList(mEvent);
			}
			return items;
		}
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}