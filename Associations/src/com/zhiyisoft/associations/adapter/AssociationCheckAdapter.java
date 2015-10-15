package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationCheckAdapter extends BAdapter {

	public AssociationCheckAdapter(BaseActivity activity) {
		super(activity, null);
	}

	public AssociationCheckAdapter(BaseFragment fragment) {
		super(fragment, null);

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
		holder.tv_school.setText(((ModelLeague) mList.get(position)).getName()
				+ "");
		return convertView;
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> list = getAssociation(1);
		return list;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> list = getAssociation(1);
		return list;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		p++;
		List<Model> list = getAssociation(p);
		return list;
	}

	private List<Model> getAssociation(int index) {
		ModelLeague league = new ModelLeague();
		league.setP(index);
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		List<Model> list = leagueImpl.groupIndex(league);
		return list;
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
