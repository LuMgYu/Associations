package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
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

public class AssociationAdapter extends BAdapter {
	private View mView;
	private ModelLeague mLeague;

	public AssociationAdapter(BaseActivity activity, List<Model> list,
			ModelLeague league) {
		super(activity, list);
		this.mLeague = league;
	}

	public AssociationAdapter(BaseFragment fragment, List<Model> list,
			ModelLeague league) {
		super(fragment, list);
		this.mLeague = league;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mView = mInflater.inflate(R.layout.listview_me_association_item,
					null);
			convertView = mView;
			initView(holder);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			// TODO 获取list里面的数据，然后添加数据
		}
		bundleDataToView(position, holder);
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param mHolder
	 */
	private void bundleDataToView(int position, ViewHolder holder) {
		ModelLeague league = (ModelLeague) mList.get(position);
		// TODO 把数据绑定到界面
		if (holder != null) {
			// 设置tag房子图片加载错乱
			resetHolder(holder);
			holder.association_iv_icon.setTag(league.getLogourl());
			// 设置默认图片，有助于清理缓存
			holder.association_iv_icon
					.setImageResource(R.drawable.default_image_small);
			if (holder.association_iv_icon.getTag() != null
					&& (holder.association_iv_icon.getTag()).equals(league
							.getLogourl())) {
				// holder.association_iv_icon.setImageUrl(league.getLogourl());
				mApp.displayImage(league.getLogourl(),
						holder.association_iv_icon);
			}
			holder.association_tv_title.setText(league.getName() + "");
			holder.association_tv_member.setText(league.getMembers() + "");
			holder.association_tv_content.setText(league.getDescription() + "");
		}
	}

	private void resetHolder(ViewHolder holder) {
		holder.association_iv_icon
				.setImageResource(R.drawable.default_image_small);
		holder.association_tv_title.setText("");
		holder.association_tv_member.setText("");
		holder.association_tv_content.setText("");
	}

	private void initView(ViewHolder viewHolder) {
		if (mView != null) {

			viewHolder.association_iv_icon = (RoundImageView) mView
					.findViewById(R.id.association_iv_icon);
			viewHolder.association_tv_title = (TextView) mView
					.findViewById(R.id.association_tv_title);
			viewHolder.association_tv_member = (TextView) mView
					.findViewById(R.id.association_tv_member);
			viewHolder.association_tv_content = (TextView) mView
					.findViewById(R.id.association_tv_content);
		}
	}

	// -----------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		List<Model> list = leagueImpl.groupIndex(mLeague);
		System.out.println(mLeague.toString());
		return list;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		items.add(new ModelLeague());
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
