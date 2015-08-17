package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.SchoolIm;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MeSettingSchoolAdapter extends BAdapter {
	private ModelSchool school; // 需要刷新的学校的地址

	public MeSettingSchoolAdapter(BaseActivity activity, List<Model> list,
			ModelSchool modelSchool) {
		super(activity, list);
		this.school = modelSchool;
	}

	public MeSettingSchoolAdapter(BaseFragment fragment, List<Model> list,
			ModelSchool modelSchool) {
		super(fragment, list);
		this.school = modelSchool;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.me_setting_school_item,
					null);
			holder.tv_school = (TextView) convertView
					.findViewById(R.id.tv_school_name);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_school.setText(((ModelSchool) mList.get(position)).getName()
				+ "");
		return convertView;
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items;
		SchoolIm schoolIm = new Api.SchoolImpl();
		items = schoolIm.getSchools(school);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		return null;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

	private class Holder {
		public TextView tv_school;

	}
}
