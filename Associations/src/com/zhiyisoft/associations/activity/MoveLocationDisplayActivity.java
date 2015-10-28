package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MoveAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现展示所点选项的社团
 *
 */

public class MoveLocationDisplayActivity extends BaseActivity {
	private RelativeLayout rl_location;
	private BaseListView move_lv;
	private Button btn_arround;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelEvent mEvent;
	private boolean mSwitch = false;

	List<ModelEvent> mResultList;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("", mEvent.getTypeName(), "");
	}

	@Override
	public void initIntent() {
		mBundle = getIntent().getExtras();
		if (mBundle != null) {
			mEvent = (ModelEvent) mBundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move__location_display;
	}

	@Override
	public void initView() {
		btn_arround = (Button) findViewById(R.id.btn_arround);
		rl_location = (RelativeLayout) findViewById(R.id.rl_location);
		rl_location.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, UIUtils
						.getWindowHeight(getApplicationContext()) / 3 * 2));
		/******************* 地图初始化部分 *******************************/
		mMapView = new MapView(this);
		mBaiduMap = mMapView.getMap();
		initSiteDisplay(mBaiduMap);
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = initOption();
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		/********************************************************/
		rl_location.addView(mMapView);
		move_lv = (MoveListview) findViewById(R.id.move_lv);
		if (mEvent == null) {
			mEvent = new ModelEvent(); // 基本不会出现这个情况
		}
	}

	@Override
	public void initListener() {
		btn_arround.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_arround:
			if (mSwitch) {
				mSwitch = false;
				rl_location
						.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.MATCH_PARENT,
								UIUtils.getWindowHeight(getApplicationContext()) / 3 * 2));
			} else {
				mSwitch = true;
				rl_location
						.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.MATCH_PARENT,
								UIUtils.getWindowHeight(getApplicationContext()) / 3 * 1));
				// slideview(rl_location, 800, 200);
				// rl_location.setAnimation(animation);
			}
			break;
		}

	}

	private void slideview(final View view, final float p1, final float p2) {
		TranslateAnimation animation = new TranslateAnimation(0, 0, p1, p2);
		animation.setInterpolator(new OvershootInterpolator());
		animation.setDuration(2000);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.clearAnimation();
				view.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT,
						UIUtils.getWindowHeight(getApplicationContext()) / 3 * 1));
			}
		});
		view.startAnimation(animation);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		if (mMapView != null) {
			mMapView.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		if (mMapView != null) {
			mMapView.onPause();
		}
	}

	@Override
	protected void onDestroy() {
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
		if (mMapView != null) {
			mMapView.onDestroy();
		}
		super.onDestroy();
	}

	@Override
	public String setCenterTitle() {
		return "";
	}

	/******************************* 地图部分 ********************************************/
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private String mCity;
	private String mProvince;

	private List<Marker> markers = new ArrayList<Marker>();

	private void initSiteDisplay(BaiduMap map) {
		if (map != null) {
			map.setMyLocationEnabled(true);
			MyLocationConfiguration config = new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.NORMAL, true, null);
			map.setMyLocationConfigeration(config);
		}
	}

	/**
	 * 初始化定位的option
	 * 
	 * @return
	 */
	private LocationClientOption initOption() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		int span = 2000;
		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		return option;
	}

	public class MyLocationListener implements BDLocationListener {

		private boolean isFirst = true;

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (isFirst) {
				displayCurrentPos(mBaiduMap, location);
				mCity = location.getCity();
				mProvince = location.getProvince();
				mEvent.setProvince(mProvince);
				mEvent.setCity(mCity);
				mEvent.setLatitude(location.getLatitude());
				mEvent.setLongtitude(location.getLongitude());
				mAdapter = new MoveAdapter(MoveLocationDisplayActivity.this,
						mEvent, new MyLocationResultListener());
				move_lv.setAdapter(mAdapter);
				mAdapter.doRefreshHeader();
				isFirst = false;
			}
		}
	}

	private void displayCurrentPos(BaiduMap baiduMap, BDLocation location) {
		if (baiduMap != null && location != null) {
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(0).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData);
			LatLng ll = new LatLng(location.getLatitude(),
					location.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			baiduMap.animateMapStatus(u);
		}
	}

	/**
	 * 做地标
	 * 
	 * @param baiduMap
	 * @param location
	 */
	private void markPos(BaiduMap baiduMap, LatLng latLng) {
		if (baiduMap != null && latLng != null) {
			// 构建Marker图标
			BitmapDescriptor bitmap = BitmapDescriptorFactory
					.fromResource(R.drawable.location);
			// 构建MarkerOption，用于在地图上添加Marker
			OverlayOptions option = new MarkerOptions().position(latLng).icon(
					bitmap);
			// 在地图上添加Marker，并显示
			Marker marker = (Marker) baiduMap.addOverlay(option);
			markers.add(marker);
			baiduMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
		}
	}

	/**************************************************/
	public interface LocationResultListener {
		public void result(Object result);
	}

	private class MyLocationResultListener implements LocationResultListener {

		@Override
		public void result(Object result) {
			mResultList = (List<ModelEvent>) result;
			if (mResultList != null) {
				clearAllOverLay();
				markers.removeAll(markers);
				btn_arround.setText("附近共有" + mResultList.size() + "个社团");
				for (int i = 0; i < mResultList.size(); i++) {
					ModelEvent event = mResultList.get(i);
					LatLng latLng = new LatLng(event.getLatitude(),
							event.getLongtitude());
					markPos(mBaiduMap, latLng);
				}
			}
		}
	}

	private class MyOnMarkerClickListener implements OnMarkerClickListener {

		@Override
		public boolean onMarkerClick(Marker arg0) {
			if (markers != null) {
				for (int i = 0; i < markers.size(); i++) {
					Marker marker = markers.get(i);

					if (marker.equals(arg0)) {
						ModelEvent event = mResultList.get(i);
						showPop(mBaiduMap, arg0, event);
					}
				}
			}
			return true;
		}
	}

	private void showPop(BaiduMap baiduMap, Marker marker,
			final ModelEvent event) {
		if (baiduMap != null && marker != null && event != null) {
			LatLng latLng = marker.getPosition();
			// 创建InfoWindow展示的view
			View popup = View.inflate(this, R.layout.baidu_pop_view, null);
			ImageView iv_bd_pop = (ImageView) popup
					.findViewById(R.id.iv_bd_pop);
			TextView tv_name = (TextView) popup.findViewById(R.id.tv_name);
			TextView tv_des = (TextView) popup.findViewById(R.id.tv_des);
			mApp.displayImage(event.getLogourl(), iv_bd_pop);
			tv_name.setText(event.getTitle());
			tv_des.setText(event.getExplain());
			// 创建InfoWindow的点击事件监听者
			OnInfoWindowClickListener listener = new OnInfoWindowClickListener() {
				public void onInfoWindowClick() {
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, event);
					mApp.startActivity(MoveLocationDisplayActivity.this,
							MoveMainActivity.class, bundle);
				}
			};
			// 创建InfoWindow
			BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(popup);
			InfoWindow mInfoWindow = new InfoWindow(bitmap, latLng, -47,
					listener);
			// 显示InfoWindow
			baiduMap.showInfoWindow(mInfoWindow);
		}
	}

	public void clearAllOverLay() {
		if (mBaiduMap != null) {
			mBaiduMap.clear();
		}
	}

}
