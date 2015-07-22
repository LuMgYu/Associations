package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.model.ModelItem;

/**
 * author：qiuchunjia time：上午10:47:11 类描述：这个类是实现
 *
 */

public class testAdapter extends BAdapter {

	public testAdapter(BaseActivity activity, List<ModelItem> list) {
		super(activity, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return mInflater.inflate(R.layout.title, null);
	}

	@Override
	public List<ModelItem> refreshNew() {
		List<ModelItem> items = new ArrayList<ModelItem>();
		items.add(new ModelItem());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<ModelItem> refreshHeader(ModelItem item, int count) {
	    List<ModelItem> items = new ArrayList<ModelItem>();
		items.add(new ModelItem());
		items.add(new ModelItem());
		items.add(new ModelItem());
		return items;
	}

	@Override
	public List<ModelItem> refreshFooter(ModelItem item, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
