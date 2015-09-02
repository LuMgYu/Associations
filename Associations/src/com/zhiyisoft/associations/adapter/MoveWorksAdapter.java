package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.Anim;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class MoveWorksAdapter extends BAdapter {
	private View mEssayView; // 文章view
	private View mMusicView; // 音乐view
	private View mPhotoView; // 照片view
	private View mVedioView; // 视频view
	private ViewHolder mViewHolder = new ViewHolder();

	public MoveWorksAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);

	}

	public MoveWorksAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position % 4 == 0) {
			mEssayView = mInflater
					.inflate(R.layout.move_works_essay_item, null);
			initEssayWorks();
			setstartActivity(mEssayView,
					AssociationTopicDetailActivity.FLAG_ESSAY);
			return mEssayView;
		}
		if (position % 4 == 1) {
			mMusicView = mInflater
					.inflate(R.layout.move_works_music_item, null);
			initMusicWorks();
			setstartActivity(mMusicView,
					AssociationTopicDetailActivity.FLAG_MUSIC);
			return mMusicView;
		}
		if (position % 4 == 2) {
			mPhotoView = mInflater
					.inflate(R.layout.move_works_photo_item, null);
			initPhotoWorks();
			setstartActivity(mPhotoView,
					AssociationTopicDetailActivity.FLAG_MANYPHOTO);
			return mPhotoView;
		}
		if (position % 4 == 3) {
			mVedioView = mInflater
					.inflate(R.layout.move_works_vedio_item, null);
			initVedioWorks();
			setstartActivity(mVedioView,
					AssociationTopicDetailActivity.FLAG_VIDEO);
			return mVedioView;
		}
		return null;
	}

	/**
	 * @param view
	 *            点击的view
	 * @param flag
	 *            跳转的到后需要显示的画面
	 */
	private void setstartActivity(View view, int flag) {
		view.setTag(flag);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int flags = (Integer) v.getTag();
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt(Config.SEND_ACTIVITY_DATA, flags);
				intent.putExtras(bundle);
				intent.setClass(mBaseActivity,
						AssociationTopicDetailActivity.class);
				mBaseActivity.startActivity(intent);
				Anim.in(mBaseActivity);
			}
		});
	}

	/**
	 * 初始化文章的控件
	 */
	private void initEssayWorks() {
		if (mEssayView != null) {
			mViewHolder.iv_essay_user_icon = (RoundImageView) mEssayView
					.findViewById(R.id.iv_essay_user_icon);
			mViewHolder.tv_user_name = (TextView) mEssayView
					.findViewById(R.id.tv_user_name);
			mViewHolder.tv_user_send = (TextView) mEssayView
					.findViewById(R.id.tv_user_send);
			mViewHolder.tv_essay_title = (TextView) mEssayView
					.findViewById(R.id.tv_essay_title);
			mViewHolder.tv_essay_content = (TextView) mEssayView
					.findViewById(R.id.tv_essay_content);
			mViewHolder.tv_essay_date = (TextView) mEssayView
					.findViewById(R.id.tv_essay_date);
			mViewHolder.tv_essay_commit = (TextView) mEssayView
					.findViewById(R.id.tv_essay_commit);
		}
	}

	/**
	 * 初始化音乐作品的控件
	 */
	private void initMusicWorks() {
		if (mMusicView != null) {
			mViewHolder.iv_music_user_icon = (RoundImageView) mMusicView
					.findViewById(R.id.iv_music_user_icon);
			mViewHolder.tv_user_name = (TextView) mMusicView
					.findViewById(R.id.tv_user_name);
			mViewHolder.tv_user_send = (TextView) mMusicView
					.findViewById(R.id.tv_user_send);
			mViewHolder.tv_music_name = (TextView) mMusicView
					.findViewById(R.id.tv_music_name);
			mViewHolder.tv_music_date = (TextView) mMusicView
					.findViewById(R.id.tv_music_date);
			mViewHolder.tv_music_commit = (TextView) mMusicView
					.findViewById(R.id.tv_music_commit);
		}
	}

	/**
	 * 初始化照片作品控件
	 */
	private void initPhotoWorks() {
		if (mPhotoView != null) {
			mViewHolder.iv_photo_user_icon = (RoundImageView) mPhotoView
					.findViewById(R.id.iv_photo_user_icon);
			mViewHolder.tv_user_name = (TextView) mPhotoView
					.findViewById(R.id.tv_user_name);
			mViewHolder.tv_user_send = (TextView) mPhotoView
					.findViewById(R.id.tv_user_send);
			mViewHolder.tv_photo_title = (TextView) mPhotoView
					.findViewById(R.id.tv_photo_title);
			mViewHolder.iv_photo1 = (SmartImageView) mPhotoView
					.findViewById(R.id.iv_photo1);
			mViewHolder.iv_photo2 = (SmartImageView) mPhotoView
					.findViewById(R.id.iv_photo2);
			mViewHolder.iv_photo3 = (SmartImageView) mPhotoView
					.findViewById(R.id.iv_photo3);
			mViewHolder.tv_photo_date = (TextView) mPhotoView
					.findViewById(R.id.tv_photo_date);
			mViewHolder.tv_photo_commit = (TextView) mPhotoView
					.findViewById(R.id.tv_photo_commit);
			initPhotoWidth();
		}
	}

	/**
	 * 初始化照片的宽高
	 */
	private void initPhotoWidth() {
		int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
		LinearLayout.LayoutParams work1, work2;
		work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work1.leftMargin = 20;
		mViewHolder.iv_photo1.setLayoutParams(work1);
		work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work2.leftMargin = 10;
		mViewHolder.iv_photo2.setLayoutParams(work2);
		mViewHolder.iv_photo3.setLayoutParams(work2);
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
