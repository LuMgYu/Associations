package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLocation;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：
 * 
 * 上午11:02:06
 * 
 * 类描述：这个类是实现
 *
 */

public class BaiDuLocateActivity extends BaseActivity {
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	private RelativeLayout rl_mapview;
	private EditText et_find;
	private ImageView iv_find;
	private RelativeLayout rl_location_choose;
	private ListView mListView;

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	boolean isFirstLoc = true;// 是否首次定位
	private String mCity = "";
	private String mProvince;
	private GeoCoder mSearch; // 搜索功能
	private LatLng mResultLatlng;
	private String mResultAddress;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, "百度定位", "确认");
	}

	@Override
	public String setCenterTitle() {
		return "百度定位";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_baidu_locate;
	}

	@Override
	public void initView() {
		rl_mapview = (RelativeLayout) findViewById(R.id.rl_mapview);
		et_find = (EditText) findViewById(R.id.et_find);
		iv_find = (ImageView) findViewById(R.id.iv_find);
		rl_location_choose = (RelativeLayout) findViewById(R.id.rl_location_choose);
		mListView = (ListView) findViewById(R.id.mListView);

		mMapView = new MapView(this);
		rl_mapview.addView(mMapView);
		mBaiduMap = mMapView.getMap();
		initSiteDisplay(mBaiduMap);
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = initOption();
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}

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

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		iv_find.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_find:
			String findStr = et_find.getText().toString();
			if (findStr != null && findStr.length() > 0) {
				mSearch = GeoCoder.newInstance();
				mSearch.setOnGetGeoCodeResultListener(listener);
				mSearch.geocode(new GeoCodeOption().city(mCity)
						.address(findStr));
			}
			break;

		case R.id.tv_title_right:
			setReturnData(mResultLatlng, mResultAddress);
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
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

	public class MyLocationListener implements BDLocationListener {
		private boolean isFirst = true;

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (isFirst) {
				displayCurrentPos(mBaiduMap, location);
				mCity = location.getCity();
				mProvince = location.getProvince();
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
			baiduMap.addOverlay(option);
		}
	}

	OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
		public void onGetGeoCodeResult(GeoCodeResult result) {
			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				// 没有检索到结果
				ToastUtils.showToast("没有找到你想要的地址信息");
			}
			Log.i("result", result.toString());
			markPos(mBaiduMap, result.getLocation());
			mResultLatlng = result.getLocation();
			mResultAddress = result.getAddress();
			// 获取地理编码结果
		}

		@Override
		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				// 没有找到检索结果
			}
			// 获取反向地理编码结果
		}
	};

	private void setReturnData(LatLng mResultLatlng, String mResultAddress) {
		if (mResultLatlng != null && mResultAddress != null) {
			mResultAddress = mProvince + mCity + mResultAddress;
			ModelLocation location = new ModelLocation();
			location.setAddress(mResultAddress);
			location.setLatitude(mResultLatlng.latitude);
			location.setLongtitude(mResultLatlng.longitude);
			onReturnResult(location, Config.LOCATION);
			onBackPressed();
		}
	}
}
