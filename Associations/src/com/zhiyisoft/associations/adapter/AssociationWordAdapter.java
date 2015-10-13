package com.zhiyisoft.associations.adapter;

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
import com.zhiyisoft.associations.model.ModelCommonAttach;
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

public class AssociationWordAdapter extends BAdapter {
	private View mView;
	private ModelLeague mLeague;

	public AssociationWordAdapter(BaseActivity activity, ModelLeague league) {
		super(activity, null);
		this.mLeague = league;
	}

	public AssociationWordAdapter(BaseFragment fragment, ModelLeague league) {
		super(fragment, null);
		this.mLeague = mLeague;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			mView = mInflater.inflate(R.layout.association_file_item, null);
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
		ModelEventWorks works = (ModelEventWorks) mList.get(position);
		// TODO 把数据绑定到界面
		if (works != null && holder != null) {
			mApp.displayImage(works.getFaceurl(), holder.iv_file_user_icon);
			holder.tv_user_name.setText(works.getUname());
			holder.tv_file_title.setText(works.getTitle());
			if (works.getAttachs() != null) {
				ModelCommonAttach attach = (ModelCommonAttach) works
						.getAttachs().get(0);
				String fileName = attach.getName();
				holder.tv_file_name.setText(fileName);
				if (fileName.contains("doc")) {
					holder.iv_file.setImageResource(R.drawable.doc);
				} else if (fileName.contains("pdf")) {
					holder.iv_file.setImageResource(R.drawable.pdf);
				} else if (fileName.contains("txt")) {
					holder.iv_file.setImageResource(R.drawable.text);
				}

			}
			holder.tv_file_date.setText(DateUtil.strTodate(works.getCtime()));
			holder.tv_file_commit.setText(works.getCommentCount());
		}

	}

	private void initView(ViewHolder holder) {
		if (mView != null) {
			holder.iv_file_user_icon = (RoundImageView) mView
					.findViewById(R.id.iv_file_user_icon);
			holder.tv_user_name = (TextView) mView
					.findViewById(R.id.tv_user_name);
			holder.tv_user_send = (TextView) mView
					.findViewById(R.id.tv_user_send);
			holder.tv_file_title = (TextView) mView
					.findViewById(R.id.tv_file_title);
			holder.iv_file = (ImageView) mView.findViewById(R.id.iv_file);
			holder.tv_file_name = (TextView) mView
					.findViewById(R.id.tv_file_name);
			holder.tv_file_date = (TextView) mView
					.findViewById(R.id.tv_file_date);
			holder.tv_file_commit = (TextView) mView
					.findViewById(R.id.tv_file_commit);

		}
	}

	// ---------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = getFiles();
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getFiles();
		return items;
	}

	/**
	 * 获取视频
	 * 
	 * @return
	 */
	private List<Model> getFiles() {
		if (mLeague != null) {
			LeagueImpl leagueImpl = mApp.getLeagueIm();
			mLeague.setType(1);
			List<Model> items = leagueImpl.groupWorks(mLeague);
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
