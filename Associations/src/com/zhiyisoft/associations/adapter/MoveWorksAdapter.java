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
	// 多个item加载时做缓存的方法
	private final int VIEW_TYPE = 4;
	private final int TYPE_ESSAY = 0;
	private final int TYPE_MUSIC = 1;
	private final int TYPE_PHOTO = 2;
	private final int TYPE_VEDIO = 3;

	private ViewHolder mViewHolder = new ViewHolder();

	public MoveWorksAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);

	}

	public MoveWorksAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = judgeTheViewType(position);
		if (convertView == null) {
			convertView = initConvertView(convertView, type);
		} else {
			// convertView不为空的时候就
			mViewHolder = (ViewHolder) convertView.getTag();

		}
		bundleDataToView(position, mViewHolder, type);
		return convertView;
	}

	/**
	 * 把数据绑定到View里面
	 *
	 * @param position
	 *            位置
	 * @param mViewHolder2
	 * @param type
	 *            类型
	 */
	private void bundleDataToView(int position, ViewHolder holder, int type) {
		// TODO 获取数据源，然后加载到上面
		Model model = mList.get(position);
		switch (type) {
		case TYPE_ESSAY:

			break;

		case TYPE_MUSIC:

			break;
		case TYPE_PHOTO:

			break;
		case TYPE_VEDIO:

			break;
		}
	}

	/**
	 * 初始化缓存converView
	 * 
	 * @param convertView
	 * @param type
	 * @return
	 */
	private View initConvertView(View convertView, int type) {
		if (type == TYPE_ESSAY) {
			mEssayView = mInflater
					.inflate(R.layout.move_works_essay_item, null);
			convertView = mEssayView;
			initEssayWorks();
			setstartActivity(mEssayView,
					AssociationTopicDetailActivity.FLAG_ESSAY);
		} else if (type == TYPE_MUSIC) {
			mMusicView = mInflater
					.inflate(R.layout.move_works_music_item, null);
			initMusicWorks();
			convertView = mMusicView;
			setstartActivity(mMusicView,
					AssociationTopicDetailActivity.FLAG_MUSIC);
		} else if (type == TYPE_PHOTO) {
			mPhotoView = mInflater
					.inflate(R.layout.move_works_photo_item, null);
			initPhotoWorks();
			convertView = mPhotoView;
			setstartActivity(mPhotoView,
					AssociationTopicDetailActivity.FLAG_MANYPHOTO);
		} else if (type == TYPE_VEDIO) {
			mVedioView = mInflater
					.inflate(R.layout.move_works_vedio_item, null);
			initVedioWorks();
			convertView = mVedioView;
			setstartActivity(mVedioView,
					AssociationTopicDetailActivity.FLAG_VIDEO);
		}
		convertView.setTag(mViewHolder);
		return convertView;
	}

	private int judgeTheViewType(int pos) {
		// 这里以后需要判断呢，更加判断来返回哪一种数据
		return pos % 4;
	}

	/**
	 * @param view
	 *            点击的view
	 * @param flag
	 *            跳转的到后需要显示的画面
	 */
	private void setstartActivity(View view, int flag) {
		// R.id.album只是一个静态数据而已，作为一个取值的凭证
		view.setTag(R.id.album, flag);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int flags = (Integer) v.getTag(R.id.album);
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

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE;
	}

	@Override
	public int getItemViewType(int position) {
		return judgeTheViewType(position);
	}

}
