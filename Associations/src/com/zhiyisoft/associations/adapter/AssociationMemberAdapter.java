package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youku.uploader.Api;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelUser;
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

	private View mMasterTypeView; // 管理员 对应item
									// R.layout.association_member_master_item
	private View mMemberView; // R.layout.association_member_item

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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initConverView(position, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bindDataToView(position, holder);
		return convertView;
	}

	/**
	 * 绑定数据到view；
	 * 
	 * @param position
	 * @param holder
	 */
	private void bindDataToView(int position, ViewHolder holder) {
		if (position > 0) {
			ModelUser modelUser = (ModelUser) mList.get(position);
			System.out.println(modelUser.toString());
			if (holder != null && modelUser != null) {
				mApp.displayImage(modelUser.getFaceurl(), holder.member_iv);
				// holder.member_iv.setImageUrl(modelUser.getFaceurl());
				holder.member_tv_name.setText(modelUser.getUname());
				holder.member_tv_school.setText(modelUser.getSchool_name());
			}
		}
	}

	private View initConverView(int position, ViewHolder holder) {
		View view = null;
		int type = judgeTheViewType(position);
		if (type == 0) {
			holder = new ViewHolder();
			mMasterTypeView = mInflater.inflate(
					R.layout.association_member_master_item, null);
			initMasterTypeView(holder);
			view = mMasterTypeView;
		} else {
			mMemberView = mInflater.inflate(R.layout.association_member_item,
					null);
			initMemberView(holder);
			view = mMemberView;
		}
		return view;
	}

	private int judgeTheViewType(int pos) {
		if (pos == 0) {
			return VIEWTYPE1;
		}
		return VIEWTYPE2;
	}

	private void initMasterTypeView(ViewHolder holder) {
		if (mMasterTypeView != null) {
			holder.member_type = (TextView) mMasterTypeView
					.findViewById(R.id.member_type);
		}
	}

	private void initMemberView(ViewHolder holder) {
		if (mMemberView != null) {
			holder.member_iv = (RoundImageView) mMemberView
					.findViewById(R.id.member_iv);
			holder.member_tv_name = (TextView) mMemberView
					.findViewById(R.id.member_tv_name);
			holder.member_tv_school = (TextView) mMemberView
					.findViewById(R.id.member_tv_school);
		}
	}

	// -------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		LeagueImpl leagueImpl = new LeagueImpl();
		List<Model> models = leagueImpl.memberList(league);
		if (models != null) {
			items.addAll(models);
		}
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		// items.add(new Model());
		// items.add(new Model());
		// items.add(new Model());
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
