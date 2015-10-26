package com.zhiyisoft.associations.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MoveDisplayActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.BaiduUtil;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveMainAdapter extends BAdapter {
	private int TYPE_COUNT = 2;
	private int TYPE_FIRST = 0;
	private int TYPE_SECOND = 1;

	private View mFirstView;
	private View mOtherView; // 真正的item
	private int[] mImageArray;
	private String[] mStringName;
	private String[] mType;
	private int mItemWidth = 0;
	/********* 活动类型 ***************/
	public static final int ARROUNTMOVE = 0; // 周边活动
	public static final int MYMOVE = 1; // 我的活动
	private int mCurrrentMoveType = 0; // 当前的选中的活动类型

	public MoveMainAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
		startLocation();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = judgeTheViewType(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initConvertView(convertView, type, holder);
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
		// TODO 把数据绑定到界面
		if (position > 0) {
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

				holder.move_tv_deadline.setText(DateUtil.strTodate(event
						.getsTime())
						+ "-"
						+ DateUtil.strTodate(event.geteTime()));
				holder.move_tv_allmove.setText(event.getJoinCount());
				holder.move_tv_content.setText(event.getExplain());
				holder.move_tv_distance.setVisibility(View.GONE);
				/************ 根据经纬度来判断距离 *****************/
				if (mCurrrentMoveType == TYPE_FIRST) {
					if (event.getLatitude() > 0 && event.getLongtitude() > 0) {
						LatLng latLng = new LatLng(event.getLatitude(),
								event.getLongtitude());
						double distance = DistanceUtil.getDistance(
								mCurrentLatlng, latLng);
						double hh = distance / 1000;
						DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
						String dis = df.format(hh);// 返回的是String类型的
						holder.move_tv_distance.setVisibility(View.VISIBLE);
						holder.move_tv_distance.setText(dis + "km");
					}
				}
			}
		}

	}

	/**
	 * 判断item view类型
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

	/**
	 * 加载缓存view
	 * 
	 * @param view
	 * @param type
	 * @return
	 */
	public View initConvertView(View view, int type, ViewHolder holder) {
		if (type == TYPE_FIRST) {
			mFirstView = mInflater.inflate(R.layout.copyoffragment_move, null);
			initFirstView(holder);
			addHotSorting(holder);
			initListener(holder);
			view = mFirstView;
		} else if (type == TYPE_SECOND) {
			mOtherView = mInflater.inflate(R.layout.move_item, null);
			initOtherView(holder);
			view = mOtherView;
		}
		view.setTag(holder);
		return view;
	}

	private void initOtherView(ViewHolder holder) {
		if (mOtherView != null) {
			holder.move_smiv_icon = (SmartImageView) mOtherView
					.findViewById(R.id.move_smiv_icon);
			holder.move_tv_end = (TextView) mOtherView
					.findViewById(R.id.move_tv_end);
			holder.move_tv_title = (TextView) mOtherView
					.findViewById(R.id.move_tv_title);
			holder.move_btn_online = (Button) mOtherView
					.findViewById(R.id.move_btn_online);
			holder.move_btn_event = (Button) mOtherView
					.findViewById(R.id.move_btn_event);

			holder.move_tv_deadline = (TextView) mOtherView
					.findViewById(R.id.move_tv_deadline);
			holder.move_tv_allmove = (TextView) mOtherView
					.findViewById(R.id.move_tv_allmove);
			holder.move_tv_content = (TextView) mOtherView
					.findViewById(R.id.move_tv_content);
			holder.move_tv_distance = (TextView) mOtherView
					.findViewById(R.id.move_tv_distance);
		}
	}

	private void initFirstView(ViewHolder holder) {
		holder.move_ll = (LinearLayout) mFirstView.findViewById(R.id.move_ll);
		holder.move_iv_zoom = (ImageView) mFirstView
				.findViewById(R.id.move_iv_zoom);
		holder.move_et_zoom = (EditText) mFirstView
				.findViewById(R.id.move_et_zoom);
		holder.move_arround_tv = (TextView) mFirstView
				.findViewById(R.id.move_arround_tv);
		holder.move_my_tv = (TextView) mFirstView.findViewById(R.id.move_my_tv);
		holder.tv_bottom_line = (TextView) mFirstView
				.findViewById(R.id.tv_bottom_line);
		mItemWidth = UIUtils.getWindowWidth(mBaseActivity) / 2;
		holder.tv_bottom_line.setWidth(mItemWidth);
	}

	/**
	 * 添加热热门分类
	 */
	private void addHotSorting(ViewHolder holder) {
		holder.move_ll.removeAllViews();
		mImageArray = new int[] { R.drawable.qb, R.drawable.ss, R.drawable.hz,
				R.drawable.yc, R.drawable.jh, R.drawable.jl, R.drawable.ty,
				R.drawable.lx, R.drawable.gy, R.drawable.qt };
		mStringName = new String[] { "全部", "赛事", "会展", "演出", "聚会", "交流", "体育",
				"旅行", "公益", "其它" };
		mType = new String[] { null, "12", "13", "14", "15", "16", "17", "18",
				"19", "20" };
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
			ModelEvent event = new ModelEvent();
			event.setType(mType[i]);
			event.setTypeName(mStringName[i]);
			itemView.setTag(event);
			itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ModelEvent event = (ModelEvent) v.getTag();
					resetBackground();
					// textView.setBackgroundResource(R.drawable.tv_gray);
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, event);
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
			holder.move_ll.addView(itemView);
		}
	}

	private void initListener(ViewHolder holder) {
		holder.move_my_tv.setOnClickListener(new MyOnClickListener(holder));
		holder.move_arround_tv
				.setOnClickListener(new MyOnClickListener(holder));
	}

	private int mCurrentDes = 0;

	private class MyOnClickListener implements OnClickListener {
		private ViewHolder holder;

		public MyOnClickListener(ViewHolder holder) {
			this.holder = holder;
		}

		@Override
		public void onClick(View v) {
			Animation animation = null;
			switch (v.getId()) {
			case R.id.move_arround_tv:
				animation = new TranslateAnimation(mCurrentDes, 0, 0, 0);
				mCurrentDes = 0 * mItemWidth;
				mCurrrentMoveType = ARROUNTMOVE;
				doRefreshNew();
				break;

			case R.id.move_my_tv:
				animation = new TranslateAnimation(mCurrentDes, mItemWidth, 0,
						0);
				mCurrentDes = 1 * mItemWidth;
				mCurrrentMoveType = MYMOVE;
				doRefreshNew();
				break;
			}
			animation.setFillAfter(true);
			animation.setDuration(300);
			holder.tv_bottom_line.startAnimation(animation);
		}
	}

	// -----------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		items = getMove(mCurrrentMoveType, 1);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items = getMove(mCurrrentMoveType, 1);
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		p++; // 记录分页
		List<Model> items = getMove(mCurrrentMoveType, p);
		return items;
	}

	public List<Model> getMove(int moveType, int index) {
		if (moveType == ARROUNTMOVE) {
			return getArroundMove(index);
		} else {
			return getMyMove(index);
		}
	}

	/**
	 * 获取活动
	 * 
	 * @return
	 */
	private List<Model> getMyMove(int index) {
		List<Model> items;
		EventImpl eventImpl = mApp.getEventFIm();
		ModelEvent event = new ModelEvent();
		event.setOp(4);
		event.setP(index);
		items = eventImpl.eventList(event);
		return items;
	}

	private List<Model> getArroundMove(int index) {
		List<Model> items;
		EventImpl eventImpl = mApp.getEventFIm();
		ModelEvent event = new ModelEvent();
		if (mCity != null) {
			event.setCity(mCity);
			event.setProvince(mProvince);
			event.setP(index);
			items = eventImpl.getNearbyEvents(event);
			return items;
		}
		return null;
	}

	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay3(list);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		return judgeTheViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}

	/****************** 地图相关部分-------地图定位 ******************************************/
	private String mCity;
	private String mProvince;
	private LocationClient mClient;
	private LatLng mCurrentLatlng;

	/**
	 * 开启定位功能
	 */
	public void startLocation() {
		if (mClient == null) {
			mClient = new LocationClient(mBaseActivity);
		}
		BaiduUtil.startLocation(mClient, 60 * 1000, new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location != null) {
					mCity = location.getCity();
					mProvince = location.getProvince();
					mCurrentLatlng = new LatLng(location.getLatitude(),
							location.getLongitude());
					doRefreshNew();
				}
			}
		});
	}
}
