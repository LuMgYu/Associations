package com.zhiyisoft.associations.util.swipelistview;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

public class SimpleActivity extends BaseActivity {

	private List<ApplicationInfo> mAppList;
	private AppAdapter mAdapter;
	private SwipeMenuListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAppList = getPackageManager().getInstalledApplications(0);
		mListView = (SwipeMenuListView) findViewById(R.id.listView);
		mAdapter = new AppAdapter(this, null);
		mListView.setAdapter(mAdapter);
		// step 2. listener item click event
		mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,
					int index) {
				ApplicationInfo item = mAppList.get(position);
				switch (index) {
				case 0:
					// open
					open(item);
					break;
				case 1:
					// delete
					// delete(item);
					mAdapter.mList.remove(position);
					Log.i("position----", position + "");
					mAdapter.notifyDataSetChanged();
					break;
				}
				return false;
			}
		});

	}

	private void open(ApplicationInfo item) {
		// open app
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(item.packageName);
		List<ResolveInfo> resolveInfoList = getPackageManager()
				.queryIntentActivities(resolveIntent, 0);
		if (resolveInfoList != null && resolveInfoList.size() > 0) {
			ResolveInfo resolveInfo = resolveInfoList.get(0);
			String activityPackageName = resolveInfo.activityInfo.packageName;
			String className = resolveInfo.activityInfo.name;

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			ComponentName componentName = new ComponentName(
					activityPackageName, className);

			intent.setComponent(componentName);
			startActivity(intent);
		}
	}

	class AppAdapter extends BAdapter {

		public AppAdapter(BaseActivity activity, List<Model> list) {
			super(activity, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.item_list_app, null);
				new ViewHolder(convertView);
			}
			ViewHolder holder = (ViewHolder) convertView.getTag();
			ModelUser user = (ModelUser) mList.get(position);
			mApp.displayImage(user.getFaceurl(), holder.iv_icon);
			holder.tv_name.setText(user.getUname() + "");
			return convertView;
		}

		class ViewHolder {
			ImageView iv_icon;
			TextView tv_name;

			public ViewHolder(View view) {
				iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
				tv_name = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(this);
			}
		}

		@Override
		public List<Model> refreshNew() {
			List<Model> list = new ArrayList<Model>();
			list.add(mApp.getUser());
			list.add(mApp.getUser());
			list.add(mApp.getUser());
			return list;
		}

		@Override
		public List<Model> refreshHeader(Model item, int count) {
			Log.i("hhh", "refreshHeader");
			List<Model> list = new ArrayList<Model>();
			list.add(mApp.getUser());
			list.add(mApp.getUser());
			list.add(mApp.getUser());
			return list;
		}

		@Override
		public List<Model> refreshFooter(Model item, int count) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addHeadList(List<Model> list) {
			addHeadListWay2(list);
		}

		@Override
		public int getTheCacheType() {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	// class AppAdapter extends BaseAdapter {
	//
	// @Override
	// public int getCount() {
	// return mAppList.size();
	// }
	//
	// @Override
	// public ApplicationInfo getItem(int position) {
	// return mAppList.get(position);
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// if (convertView == null) {
	// convertView = View.inflate(getApplicationContext(),
	// R.layout.item_list_app, null);
	// new ViewHolder(convertView);
	// }
	// ViewHolder holder = (ViewHolder) convertView.getTag();
	// ApplicationInfo item = getItem(position);
	// holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
	// holder.tv_name.setText(item.loadLabel(getPackageManager()));
	// return convertView;
	// }

	//
	// class ViewHolder {
	// ImageView iv_icon;
	// TextView tv_name;
	//
	// public ViewHolder(View view) {
	// iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
	// tv_name = (TextView) view.findViewById(R.id.tv_name);
	// view.setTag(this);
	// }
	// }
	// }

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setCenterTitle() {
		// TODO Auto-generated method stub
		return "刷新的dem";
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_list;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
