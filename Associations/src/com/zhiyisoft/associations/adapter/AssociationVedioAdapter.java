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
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationVedioAdapter extends BAdapter {
	private View mEssayView; // 文章view
	private View mMusicView; // 音乐view
	private View mPhotoView; // 照片view
	private View mVedioView; // 视频view
	private ViewHolder mViewHolder = new ViewHolder();

	public AssociationVedioAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);

	}

	public AssociationVedioAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		mVedioView = mInflater.inflate(R.layout.move_works_vedio_item, null);
		initVedioWorks();
		return mVedioView;
	}

	/**
	 * 初始化视频作品控件
	 */
	private void initVedioWorks() {
		if (mVedioView != null) {
			mViewHolder.iv_vedio_user_icon = (RoundImageView) mVedioView
					.findViewById(R.id.iv_vedio_user_icon);
			mViewHolder.tv_user_name = (TextView) mVedioView
					.findViewById(R.id.tv_user_name);
			mViewHolder.tv_user_send = (TextView) mVedioView
					.findViewById(R.id.tv_user_send);
			mViewHolder.tv_vedio_title = (TextView) mVedioView
					.findViewById(R.id.tv_music_name);
			mViewHolder.iv_vedio = (SmartImageView) mVedioView
					.findViewById(R.id.iv_vedio);
			mViewHolder.iv_vedio_click = (ImageView) mVedioView
					.findViewById(R.id.iv_vedio_click);
			mViewHolder.tv_vedio_date = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_date);
			mViewHolder.tv_vedio_commit = (TextView) mVedioView
					.findViewById(R.id.tv_vedio_commit);

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
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		items.add(new Model());
		items.add(new Model());
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
		items.add(new Model());
		items.add(new Model());
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
