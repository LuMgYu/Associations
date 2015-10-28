package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelTiding;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationTindingsAdapter extends BAdapter {
	private View mView;
	private ModelLeague mLeague;

	public AssociationTindingsAdapter(BaseActivity activity, ModelLeague league) {
		super(activity, null);
		this.mLeague = league;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mView = mInflater.inflate(R.layout.association_tidings_item, null);
			initView(holder);
			convertView = mView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bundledataToView(position, holder);
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param holder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		ModelTiding tinding = (ModelTiding) mList.get(position);
		// TODO 把数据绑定到界面
		if (tinding != null && holder != null) {
			mApp.displayImage(tinding.getUser().getFaceurl(), holder.riv_icon);
			holder.tv_name.setText(tinding.getUser().getUname());
			holder.tv_time
					.setText(DateUtil.stamp2humanDate(tinding.getCtime()));
			holder.tv_title.setText(tinding.getTitle());
		}

	}

	private void initView(ViewHolder holder) {
		if (mView != null) {
			holder.riv_icon = (RoundImageView) mView
					.findViewById(R.id.riv_icon);
			holder.tv_name = (TextView) mView.findViewById(R.id.tv_name);
			holder.tv_time = (TextView) mView.findViewById(R.id.tv_time);
			holder.tv_title = (TextView) mView.findViewById(R.id.tv_title);

		}
	}

	// ---------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = getTindings(mLeague, 1);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getTindings(mLeague, 1);
		return items;
	}

	/**
	 * 获取社团新闻
	 * 
	 * @return
	 */
	private List<Model> getTindings(ModelLeague league, int index) {
		if (league != null) {
			LeagueImpl leagueImpl = mApp.getLeagueIm();
			ModelTiding tiding = new ModelTiding();
			tiding.setGid(league.getGid());
			tiding.setP(index);
			List<Model> items = leagueImpl.groupNewsList(tiding);
			return items;
		}
		return null;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		return null;
	}

	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay2(list);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
