package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveWorksAdapter extends BAdapter {

	public MoveWorksAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public MoveWorksAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position % 4 == 0) {
			return mInflater.inflate(R.layout.move_works_essay_item, null);
		}
		if (position % 4 == 1) {
			return mInflater.inflate(R.layout.move_works_music_item, null);
		}
		if (position % 4 == 2) {
			return mInflater.inflate(R.layout.move_works_photo_item, null);
		}
		if (position % 4 == 3) {
			return mInflater.inflate(R.layout.move_works_vedio_item, null);
		}
		return null;
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
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
