package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.SchoolIm;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class NotifyMsgAdapter extends BAdapter {

	public NotifyMsgAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public NotifyMsgAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mInflater.inflate(R.layout.notify_msg_iem, null);
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
		SchoolIm school = new Api.SchoolImpl();
		school.getSchools("四川");
		// LoginIm loginIm = new Api.LoginImpl();
		// loginIm.Login(null);
		// LeagueIm leagueIm = new Api.LeagueImpl();
		// leagueIm.createLeague(new Model());
		// leagueIm.getGroupCommonList(new Model());
		// BaseSettingIm settingIm = new Api.BaseSettingImpl();
		// settingIm.updateMask(new Model());
		// settingIm.getUserActiveMaskInfo(new Model());
		// settingIm.setFaceImg(new Model());
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
