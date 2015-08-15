package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.LeagueIm;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class NotifyNfyAdapter extends BAdapter {

	public NotifyNfyAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public NotifyNfyAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mInflater.inflate(R.layout.notify_notify_iem, null);
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
		// AssociationImpl association = new Api.AssociationImpl();
		// association.addAssociation(new Model());
		LoginIm loginIm = new Api.LoginImpl();
		ModelUser model = new ModelUser();
		// model.setMobile("13688449697");
		// model.setPwd("123456");
		// model.setDevicetype("android");
		model.setPwd("123456");
		model.setUserauth("d61937499890ce90b0a61c22e8762e6e");
		loginIm.appValidateUserPwd(model);
		loginIm.appUserMobileLogin(model);
		// loginIm.Login(null);
		// LeagueIm leagueIm = new Api.LeagueImpl();
		// leagueIm.getLeagueDetail(new Model());
		// RegisterIm registerIm = new Api.RegisterImpl();
		// ModelRegister model = new ModelRegister();
		// model.setSmscode("614737");
		// model.setPwd("123456");
		// model.setMobile("13688449697");
		// // registerIm.appUserReg(model);
		// // registerIm.appSendSMSCode(model);
		// registerIm.validMobile(model);
		// leagueIm.getLeagueList(new Model());
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
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		Log.i("refreshFooter",
				"---------------------调用了这个refreshFooter(Model item, int count) ");
		// AssociationImpl association = new Api.AssociationImpl();
		// association.addAssociation(new Model());
		// LoginIm loginIm = new Api.LoginImpl();
		// loginIm.Login(null);
		// LeagueIm leagueIm = new Api.LeagueImpl();
		// leagueIm.getLeagueDetail(new Model());
		LoginIm loginIm = new Api.LoginImpl();
		ModelUser model = new ModelUser();
		// model.setMobile("13688449697");
		// model.setPwd("123456");
		// model.setDevicetype("android");
		model.setPwd("123456");
		model.setUserauth("d61937499890ce90b0a61c22e8762e6e");
		loginIm.appValidateUserPwd(model);
		loginIm.appUserMobileLogin(model);
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
