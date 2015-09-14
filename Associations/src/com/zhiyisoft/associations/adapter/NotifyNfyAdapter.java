package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class NotifyNfyAdapter extends BAdapter {
	private View mView;
	float x, ux;
	private TextView mCurdelTv;

	public NotifyNfyAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public NotifyNfyAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.notify_notify_iem, null);
			convertView.setTag(mHolder);
			mView = convertView;
			initView(mView);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		// convertView.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// mHolder = (ViewHolder) v.getTag();
		// if (event.getAction() == MotionEvent.ACTION_DOWN) {
		// x = event.getX();
		// // 判断之前是否出现了删除按钮如果存在就隐藏
		// if (mCurdelTv != null) {
		// if (mCurdelTv.getVisibility() == View.VISIBLE) {
		// mCurdelTv.setVisibility(View.GONE);
		// return true;
		// }
		// }
		// return true;
		// } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
		// return true;
		// } else if (event.getAction() == MotionEvent.ACTION_UP) {
		// ux = event.getX();
		// if (Math.abs(x - ux) > 50) {
		// mHolder.tv_del.setVisibility(View.VISIBLE);
		// }
		// }
		//
		// return false;
		// }
		// });
		return convertView;
	}

	private void initView(View view) {
		if (view != null) {
			mHolder.rl_notify = (RelativeLayout) view
					.findViewById(R.id.rl_notify);
			mHolder.fl_icon = (FrameLayout) view.findViewById(R.id.fl_icon);
			mHolder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			mHolder.iv_remind = (ImageView) view.findViewById(R.id.iv_remind);
			mHolder.tv_nick = (TextView) view.findViewById(R.id.tv_nick);
			mHolder.tv_msg = (TextView) view.findViewById(R.id.tv_msg);
			mHolder.tv_date = (TextView) view.findViewById(R.id.tv_date);
			mHolder.tv_del = (TextView) view.findViewById(R.id.tv_del);
		}
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
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
		// model.setDevicetype("android"); 6343485
		// model.setPwd("123456");
		// model.setMobile("13688449697");
		model.setUserid("6343485");
		loginIm.logout(model);
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
		// LoginIm loginIm = new Api.LoginImpl();
		// ModelUser model = new ModelUser();
		// // model.setMobile("13688449697");
		// // model.setPwd("123456");
		// // model.setDevicetype("android");
		// model.setPwd("123456");
		// model.setUserauth("d61937499890ce90b0a61c22e8762e6e");
		// loginIm.appValidateUserPwd(model);
		// loginIm.appUserMobileLogin(model);
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
