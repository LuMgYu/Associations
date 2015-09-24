package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMoveActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelLeagueTopicPhoto;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationMainNewAdapter extends BAdapter {

	private final int TYPE_COUNT = 2;
	private final int TYPE_FIRSTVIEW = 0;
	private final int TYPE_OTHERVIEW = 1;
	private ModelLeague mLeague;

	private View mFirstView;
	private View mOtherView; // 真正的item

	public AssociationMainNewAdapter(BaseActivity activity, List<Model> list,
			ModelLeague data) {
		super(activity, list);
		this.mLeague = data;
	}

	public AssociationMainNewAdapter(BaseFragment fragment, List<Model> list,
			ModelLeague data) {
		super(fragment, list);
		this.mLeague = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = judgeTheViewType(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initConvertView(convertView, type, holder);
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
	 * @param mHolder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		// TODO 把数据绑定到界面
		if (position == 0) {
			ModelLeague mainLeague = (ModelLeague) mList.get(position);
			holder.title_iv.setImageUrl(mainLeague.getLogourl());
			holder.title_tv.setText(mainLeague.getName());
			holder.title_tv_member_count.setText(mainLeague.getMembers_count());
			holder.title_tv_topic_count.setText(mainLeague.getTopic_count());
			holder.title_tv_school.setText(mainLeague.getSchoolName());
			holder.title_tv_type.setText(mainLeague.getCategoryName());
			holder.title_tv_move.setText("社团活动(" + mainLeague.getEvent_count()
					+ ")");
		} else {
			ModelLeagueTopic topic = (ModelLeagueTopic) mList.get(position);
			System.out.println(topic.toString());
			holder.new_item_iv.setImageUrl(topic.getFaceurl());
			holder.new_item_tv_nick.setText(topic.getUname() + "发表了话题");
			holder.new_item_tv_title.setText(topic.getTitle());
			holder.new_item_tv_content.setText(topic.getContent());
			holder.new_item_tv_date.setText(topic.getCtime());
			holder.new_item_tv_number.setText(topic.getReplyCount());
			List<Model> photos = topic.getAttachs();
			holder.imageView1.setVisibility(View.GONE);
			holder.imageView2.setVisibility(View.GONE);
			holder.imageView3.setVisibility(View.GONE);
			if (photos != null) {
				for (int i = 0; i < photos.size(); i++) {
					ModelLeagueTopicPhoto photo = (ModelLeagueTopicPhoto) photos
							.get(i);
					if (i == 0) {
						holder.imageView1.setVisibility(View.VISIBLE);
						holder.imageView1.setImageUrl(photo.getUrl());
					}
					if (i == 1) {
						holder.imageView2.setVisibility(View.VISIBLE);
						holder.imageView2.setImageUrl(photo.getUrl());
					}
					if (i == 2) {
						holder.imageView3.setVisibility(View.VISIBLE);
						holder.imageView3.setImageUrl(photo.getUrl());
					}
				}

			}

		}

	}

	/**
	 * 加载缓存view
	 * 
	 * @param view
	 * @param type
	 * @return
	 */
	public View initConvertView(View view, int type, ViewHolder holder) {
		if (type == TYPE_FIRSTVIEW) {
			mFirstView = mInflater.inflate(
					R.layout.copyofactivity_association_single, null);
			initFirstView(holder);
			initListener(holder);
			view = mFirstView;
		} else if (type == TYPE_OTHERVIEW) {
			mOtherView = mInflater.inflate(R.layout.association_single_item,
					null);
			initOtherView(holder);
			view = mOtherView;
		}
		view.setTag(holder);
		return view;
	}

	/**
	 * 根据返回的数据类型类判断到底要加载哪一个item layout
	 * 
	 * @param pos
	 * @return
	 */
	private int judgeTheViewType(int pos) {
		if (pos == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 初始化第一种item的 view
	 */
	private void initFirstView(ViewHolder holder) {
		if (mFirstView != null) {
			holder.title_iv = (RoundImageView) mFirstView
					.findViewById(R.id.title_iv);
			holder.title_tv = (TextView) mFirstView.findViewById(R.id.title_tv);
			holder.title_tv_member = (TextView) mFirstView
					.findViewById(R.id.title_tv_member);
			holder.title_tv_member_count = (TextView) mFirstView
					.findViewById(R.id.title_tv_member_count);
			holder.title_tv_topic = (TextView) mFirstView
					.findViewById(R.id.title_tv_topic);
			holder.title_tv_topic_count = (TextView) mFirstView
					.findViewById(R.id.title_tv_topic_count);
			holder.title_tv_school = (TextView) mFirstView
					.findViewById(R.id.title_tv_school);
			holder.title_tv_type = (TextView) mFirstView
					.findViewById(R.id.title_tv_type);
			holder.title_tv_move = (TextView) mFirstView
					.findViewById(R.id.title_tv_move);
			holder.title_rl_move = (RelativeLayout) mFirstView
					.findViewById(R.id.title_rl_move);
		}
	}

	/**
	 * 初始化其它共同item的 view
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void initOtherView(ViewHolder holder) {
		if (mOtherView != null) {
			holder.new_item_rl_head = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_head);
			holder.new_item_iv = (RoundImageView) mOtherView
					.findViewById(R.id.new_item_iv);
			holder.title_tv_member = (TextView) mOtherView
					.findViewById(R.id.title_tv_member);
			holder.new_item_tv_nick = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_nick);
			holder.new_item_rl_content = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_content);
			holder.new_item_tv_title = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_title);
			holder.new_item_tv_content = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_content);
			holder.new_item_ll = (LinearLayout) mOtherView
					.findViewById(R.id.new_item_ll);
			holder.imageView1 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView1);
			holder.imageView2 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView2);
			holder.imageView3 = (SmartImageView) mOtherView
					.findViewById(R.id.imageView3);
			holder.new_item_rl_footer = (RelativeLayout) mOtherView
					.findViewById(R.id.new_item_rl_footer);
			holder.new_item_tv_date = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_date);
			holder.new_item_tv_number = (TextView) mOtherView
					.findViewById(R.id.new_item_tv_number);
			initPhotoWidth(holder);

		}
	}

	/**
	 * 初始化图片的宽度和高度
	 */
	private void initPhotoWidth(ViewHolder holder) {
		int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
		LinearLayout.LayoutParams work1, work2;
		work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work1.leftMargin = 20;
		holder.imageView1.setLayoutParams(work1);
		holder.imageView1.setScaleType(ScaleType.CENTER_CROP);
		work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work2.leftMargin = 10;
		holder.imageView2.setLayoutParams(work2);
		holder.imageView2.setScaleType(ScaleType.CENTER_CROP);
		holder.imageView3.setLayoutParams(work2);
		holder.imageView3.setScaleType(ScaleType.CENTER_CROP);
	}

	private void initListener(ViewHolder holder) {
		holder.title_rl_move.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle data1 = new Bundle();
				mApp.startActivity(mBaseActivity,
						AssociationMoveActivity.class, data1);
			}
		});
	}

	// -------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		Model model = leagueImpl.viewIn(mLeague);
		List<Model> topicModels = leagueImpl.topicList(mLeague);
		items.add(model);
		if (topicModels != null) {
			items.addAll(topicModels);
		}
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
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
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		return judgeTheViewType(position);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
