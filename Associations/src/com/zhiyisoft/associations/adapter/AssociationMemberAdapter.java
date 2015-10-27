package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.NotifyMsgDetailActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelMember;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationMemberAdapter extends BAdapter {
	private final static int VIEWTYPE = 2;
	private final static int VIEWTYPE1 = 0;
	private final static int VIEWTYPE2 = 1;

	private final static String TITLETYPE = "4"; // 作为一个标志用来判断是否添加标题栏
	private ModelLeague league;

	public AssociationMemberAdapter(BaseActivity activity, List<Model> list,
			ModelLeague request) {
		super(activity, list);
		this.league = request;
	}

	public AssociationMemberAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 现在只是做一个效果，以后需要修改
		ViewHolder holder = null;
		int type = judgeTheViewType(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initConverView(position, holder, type);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bindDataToView(position, holder, type);
		return convertView;
	}

	private int judgeTheViewType(int pos) {
		ModelMember member = (ModelMember) mList.get(pos);
		if (member != null) {
			if (member.getLevel().equals(TITLETYPE)) {
				return VIEWTYPE1;
			}
		}
		return VIEWTYPE2;
	}

	/**
	 * 绑定数据到view；
	 * 
	 * @param position
	 * @param holder
	 */
	private void bindDataToView(int position, ViewHolder holder, int type) {
		if (holder != null) {
			ModelMember member = (ModelMember) mList.get(position);
			if (type == VIEWTYPE1) {
				holder.member_type.setText(member.getTitleName());
			} else {
				mApp.displayImage(member.getFaceurl(), holder.member_iv);
				holder.member_tv_name.setText(member.getName());
				holder.member_tv_school.setText(member.getSchoolName());
				holder.member_tv_sendmessage.setTag(member);
				holder.member_tv_sendmessage
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								ModelMember mem = (ModelMember) v.getTag();
								if (mem != null) {
									ModelMsg msg = new ModelMsg();
									Bundle bundle = new Bundle();
									bundle.putSerializable(
											Config.SEND_ACTIVITY_DATA, msg);
									msg.setUid(mem.getUid());
									mApp.startActivity(mBaseActivity,
											NotifyMsgDetailActivity.class,
											bundle);
								}
							}
						});
			}
		}
	}

	private View initConverView(int position, ViewHolder holder, int type) {
		View view = null;
		if (type == 0) {
			view = mInflater.inflate(R.layout.association_member_master_item,
					null);
			initTitleTypeView(holder, view);
		} else {
			view = mInflater.inflate(R.layout.association_member_item, null);
			initMemberView(holder, view);
		}
		return view;
	}

	/**
	 * 初始化标题view
	 * 
	 * @param holder
	 * @param view
	 */
	private void initTitleTypeView(ViewHolder holder, View view) {
		if (holder != null && view != null) {
			holder.member_type = (TextView) view.findViewById(R.id.member_type);
		}
	}

	/**
	 * 初始化成员view
	 * 
	 * @param holder
	 * @param view
	 */
	private void initMemberView(ViewHolder holder, View view) {
		if (view != null && view != null) {
			holder.member_iv = (RoundImageView) view
					.findViewById(R.id.member_iv);
			holder.member_tv_name = (TextView) view
					.findViewById(R.id.member_tv_name);
			holder.member_tv_school = (TextView) view
					.findViewById(R.id.member_tv_school);
			holder.member_tv_sendmessage = (TextView) view
					.findViewById(R.id.member_tv_sendmessage);
		}
	}

	// -------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = getMember();
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getMember();
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		// items.add(new Model());
		// items.add(new Model());
		// items.add(new Model());
		return items;
	}

	private List<Model> getMember() {
		List<Model> items = new ArrayList<Model>();
		LeagueImpl leagueImpl = new LeagueImpl();
		List<Model> models = leagueImpl.memberList(league);
		items = addTitleView(models);
		return items;
	}

	/**
	 * 根据相应的条件添加title
	 * 
	 * @param models
	 * @return
	 */
	private List<Model> addTitleView(List<Model> models) {
		List<Model> list = new ArrayList<Model>();
		List<Model> masters = new ArrayList<Model>();
		List<Model> members = new ArrayList<Model>();
		if (models != null) {
			for (int i = 0; i < models.size(); i++) {
				ModelMember member = (ModelMember) models.get(i);
				if (member.getLevel().equals("1")) {
					members.add(member);
				} else {
					masters.add(member);
				}
			}
			if (masters.size() > 0) {
				ModelMember title1 = new ModelMember();
				title1.setLevel(TITLETYPE);
				title1.setTitleName("社团管理员");
				list.add(title1);
				list.addAll(masters);
			}
			ModelMember title2 = new ModelMember();
			title2.setLevel(TITLETYPE);
			title2.setTitleName("社团成员");
			list.add(title2);
			list.addAll(members);
			return list;
		}
		return null;
	}

	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay2(list);
	}

	@Override
	public int getItemViewType(int position) {
		return judgeTheViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return VIEWTYPE;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
