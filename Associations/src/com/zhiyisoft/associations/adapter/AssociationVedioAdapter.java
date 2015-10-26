package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationVedioAdapter extends BAdapter {
	private View mVedioView; // 视频view
	private ModelLeague mLeague;

	public AssociationVedioAdapter(BaseActivity activity, ModelLeague league) {
		super(activity, null);
		this.mLeague = league;
	}

	public AssociationVedioAdapter(BaseFragment fragment, ModelLeague league) {
		super(fragment, null);
		this.mLeague = league;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mVedioView = mInflater
					.inflate(R.layout.move_works_vedio_item, null);
			initVedioWorks(holder);
			convertView = mVedioView;
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
		ModelEventWorks works = (ModelEventWorks) mList.get(position);
		// TODO 把数据绑定到界面
		if (holder != null && works != null) {
			mApp.displayImage(works.getFaceurl(), holder.iv_vedio_user_icon);
			mApp.displayImage(works.getVideo_image(), holder.iv_vedio);
			holder.tv_user_name.setText(works.getUname());
			holder.tv_vedio_title.setText(works.getTitle());
			holder.tv_vedio_commit.setText(works.getCommentCount());
			holder.tv_vedio_date.setText(DateUtil.stamp2humanDate(works.getCtime()));
		}

	}

	/**
	 * 初始化视频作品控件
	 */
	private void initVedioWorks(ViewHolder holder) {
		if (mVedioView != null) {
			holder.iv_vedio_user_icon = (RoundImageView) mVedioView
					.findViewById(R.id.iv_vedio_user_icon);
			holder.tv_user_name = (TextView) mVedioView
					.findViewById(R.id.tv_user_name);
			holder.tv_user_send = (TextView) mVedioView
					.findViewById(R.id.tv_user_send);
			holder.tv_vedio_title = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_title);
			holder.iv_vedio = (SmartImageView) mVedioView
					.findViewById(R.id.iv_vedio);
			holder.iv_vedio_click = (ImageView) mVedioView
					.findViewById(R.id.iv_vedio_click);
			holder.tv_vedio_date = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_date);
			holder.tv_vedio_commit = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_commit);

		}
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = getVideo();
		return items;
	}

	/**
	 * 获取视频
	 * 
	 * @return
	 */
	private List<Model> getVideo() {
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		mLeague.setType(3);
		List<Model> items = leagueImpl.groupWorks(mLeague);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getVideo();
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		return items;
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
