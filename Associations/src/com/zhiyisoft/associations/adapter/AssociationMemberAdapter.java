package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
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
		if (position == 0) {
			holder = new ViewHolder();
			mMasterTypeView = mInflater.inflate(
					R.layout.association_member_master_item, null);
			initMasterTypeView(holder);
			return mMasterTypeView;
		}
		if (position == 3) {
			mMasterTypeView = mInflater.inflate(
					R.layout.association_member_master_item, null);
			initMasterTypeView(holder);
			return mMasterTypeView;
		}
		mMemberView = mInflater.inflate(R.layout.association_member_item, null);
		initMemberView(holder);
		return mMemberView;
	}

	private void initMasterTypeView(ViewHolder holder) {
		if (mMasterTypeView != null) {
			holder.member_type = (TextView) mMasterTypeView
					.findViewById(R.id.member_type);
		}
	}

	private void initMemberView(ViewHolder holder) {
		if (mMemberView != null) {
			holder.member_iv = (RoundImageView) mMasterTypeView
					.findViewById(R.id.member_iv);
			holder.member_tv_name = (TextView) mMasterTypeView
					.findViewById(R.id.member_tv_name);
			holder.member_tv_school = (TextView) mMasterTypeView
					.findViewById(R.id.member_tv_school);
		}
	}

	// -------------------------------------------------------------------------------------
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
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
		items.add(new Model());
		return items;
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
