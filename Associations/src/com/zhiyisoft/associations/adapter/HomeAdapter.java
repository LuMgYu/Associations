package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMainActivity;
import com.zhiyisoft.associations.activity.AssociationSingleActivity;
import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.activity.MoveDisplayActivity;
import com.zhiyisoft.associations.activity.MoveMainActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.HomeImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelHome;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;
import com.zhiyisoft.associations.widget.CircleFlowIndicator;
import com.zhiyisoft.associations.widget.ViewFlow;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现homeAdapter
 *
 */

public class HomeAdapter extends BAdapter {
	public final static int HOTMOVECOUNT = 3; // 热门活动的数量
	public final static int NEWSCOUNT = 5; // 新鲜事的的数量

	private ViewHolder mViewHolder;
	private View mView;

	private View[] mHotItemViewArray;
	private View[] mNewsItemViewArray;

	// 更新的数据的数据集合；
	private List<Model> mAssociationList;
	private List<Model> mMoveList;
	private List<Model> mWorksList;
	private List<Model> mNewsList;

	// 各个按钮的数组；
	private View[] mTwoButton;
	private View[] mRefreAssos;
	private View[] mWorksViews;

	public HomeAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public HomeAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (mView == null) {
			mViewHolder = new ViewHolder();
			mView = mInflater.inflate(R.layout.copyoffragment_home, null);
			initView();
			initData(position);
		}
		return mView;
	}

	/**
	 * 初始化数据
	 */
	private void initData(int pos) {
		ModelHome home = (ModelHome) mList.get(pos);
		if (home != null) {
			setAds();
			initWorksIv(home.getWorks());
			addAdviceAssocition(home.getGroups());
			addMyAssociation(home.getJoinedGroup());
			adddataToWork(home.getWorks());
			initHotView(home.getEvents());
			initNewsView(home.getTopics());
			setListener();
		}
	}

	/**
	 * 添加作品数据到界面
	 * 
	 * @param works
	 */
	private void adddataToWork(List<Model> works) {
		if (works != null && mViewHolder != null) {
			for (int i = 0; i < works.size(); i++) {
				ModelEventWorks work = (ModelEventWorks) works.get(i);
				if (i == 0) {
					mViewHolder.iv_work1.setTag(work);
					mApp.displayImage(work.getFaceurl(), mViewHolder.iv_work1);
					// mViewHolder.iv_work1.setImageUrl(work.getFaceurl());
				} else if (i == 1) {
					mViewHolder.iv_work2.setTag(work);
					mApp.displayImage(work.getFaceurl(), mViewHolder.iv_work2);
					// mViewHolder.iv_work2.setImageUrl(work.getFaceurl());
				} else if (i == 2) {
					mApp.displayImage(work.getFaceurl(), mViewHolder.iv_work3);
					mViewHolder.iv_work3.setTag(work);
					// mViewHolder.iv_work3.setImageUrl(work.getFaceurl());
				}
			}

		}
	}

	/**
	 * 添加社团推荐
	 * 
	 * @param groups
	 */
	private void addAdviceAssocition(List<Model> groups) {
		if (groups != null && mViewHolder != null) {
			for (int i = 0; i < groups.size(); i++) {
				ModelLeague league = (ModelLeague) groups.get(i);
				if (i == 0) {
					mViewHolder.iv_association1.setTag(league);
					// mViewHolder.iv_association1
					// .setImageUrl(league.getLogourl());
					mApp.displayImage(league.getLogourl(),
							mViewHolder.iv_association1);
					mViewHolder.tv_association1.setText(league.getName());
				} else if (i == 1) {
					mViewHolder.iv_association2.setTag(league);
					// mViewHolder.iv_association2
					// .setImageUrl(league.getLogourl());
					mApp.displayImage(league.getLogourl(),
							mViewHolder.iv_association2);
					mViewHolder.tv_association2.setText(league.getName());
				} else if (i == 2) {
					mViewHolder.iv_association3.setTag(league);
					// mViewHolder.iv_association3
					// .setImageUrl(league.getLogourl());
					mApp.displayImage(league.getLogourl(),
							mViewHolder.iv_association3);
					mViewHolder.tv_association3.setText(league.getName());
				} else if (i == 3) {
					mViewHolder.iv_association4.setTag(league);
					mApp.displayImage(league.getLogourl(),
							mViewHolder.iv_association4);
					// mViewHolder.iv_association4
					// .setImageUrl(league.getLogourl());
					mViewHolder.tv_association4.setText(league.getName());
				}
			}
		}
	}

	/**
	 * 初始化新鲜事的数量
	 */
	private void initNewsView(List<Model> list) {
		if (list != null) {
			mNewsItemViewArray = new View[NEWSCOUNT];
			for (int i = 0; i < list.size(); i++) {
				ModelLeagueTopic topic = (ModelLeagueTopic) list.get(i);
				mNewsItemViewArray[i] = mInflater.inflate(
						R.layout.association_news_item, null);
				mNewsItemViewArray[i].setTag(topic);
				/******************* 初始化新鲜事以及添加 ****************************/
				SmartImageView move_iv = (SmartImageView) mNewsItemViewArray[i]
						.findViewById(R.id.move_iv);
				TextView move_tv_title = (TextView) mNewsItemViewArray[i]
						.findViewById(R.id.move_tv_title);
				TextView move_tv_content = (TextView) mNewsItemViewArray[i]
						.findViewById(R.id.move_tv_content);
				mApp.displayImage(topic.getFaceurl(), move_iv);
				// move_iv.setImageUrl();
				move_tv_title.setText(topic.getTitle());
				move_tv_content.setText(topic.getContent());
				/******************* 初始化新鲜事以及添加 ****************************/
				mViewHolder.ll_news.addView(mNewsItemViewArray[i]);

			}
		}
	}

	/**
	 * 
	 * 初始化热门活动的数量
	 */
	private void initHotView(List<Model> list) {
		if (list != null) {
			mHotItemViewArray = new View[HOTMOVECOUNT];
			for (int i = 0; i < list.size(); i++) {
				mHotItemViewArray[i] = mInflater.inflate(R.layout.move_item,
						null);
				/**************** 绑定活动的数据先初始化再绑定数据 *****************/
				ViewHolder holder = new ViewHolder();
				holder.move_smiv_icon = (SmartImageView) mHotItemViewArray[i]
						.findViewById(R.id.move_smiv_icon);
				holder.move_tv_end = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_end);
				holder.move_tv_title = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_title);
				holder.move_btn_online = (Button) mHotItemViewArray[i]
						.findViewById(R.id.move_btn_online);
				holder.move_btn_event = (Button) mHotItemViewArray[i]
						.findViewById(R.id.move_btn_event);

				holder.move_tv_deadline = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_deadline);
				holder.move_tv_allmove = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_allmove);
				holder.move_tv_content = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_content);
				// 判定数据
				ModelEvent event = (ModelEvent) list.get(i);
				if (event != null) {
					mHotItemViewArray[i].setTag(event);
					mApp.displayImage(event.getLogourl(), holder.move_smiv_icon);
					// holder.move_smiv_icon.setImageUrl(event.getLogourl());
					int isover = event.getIsover();
					if (isover == 0) {
						holder.move_tv_end.setVisibility(View.GONE);
					} else {
						holder.move_tv_end.setVisibility(View.GONE);
					}
					holder.move_tv_title.setText(event.getTitle());
					String isonline = event.getOnline();
					if (isonline.equals("0")) {
						holder.move_btn_online.setText("线上");
					} else {
						holder.move_btn_online.setText("线下");
					}
					holder.move_btn_event.setText(event.getTypeName());

					holder.move_tv_deadline.setText(DateUtil.strTodate(event
							.geteTime()));
					holder.move_tv_allmove.setText(event.getJoinCount());
					holder.move_tv_content.setText(event.getExplain());

					/**************** 绑定活动的数量 化再绑定数据 *****************/
					mViewHolder.ll_hotMove.addView(mHotItemViewArray[i]);
				}
			}
		}
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		if (mView != null && mViewHolder != null) {
			mViewHolder.home_rl_ads = (RelativeLayout) mView
					.findViewById(R.id.home_rl_ads);
			mViewHolder.home_rl_my_association = (RelativeLayout) mView
					.findViewById(R.id.home_rl_my_association);
			mViewHolder.tv_my_association = (TextView) mView
					.findViewById(R.id.tv_my_association);
			mViewHolder.hsv_association = (HorizontalScrollView) mView
					.findViewById(R.id.hsv_association);
			mViewHolder.ll_association = (LinearLayout) mView
					.findViewById(R.id.ll_association);
			mViewHolder.home_ll_move = (LinearLayout) mView
					.findViewById(R.id.home_ll_move);
			mViewHolder.btn_all_move = (Button) mView
					.findViewById(R.id.btn_all_move);
			mViewHolder.btn_around_move = (Button) mView
					.findViewById(R.id.btn_around_move);
			mViewHolder.ll_association1 = (LinearLayout) mView
					.findViewById(R.id.ll_association1);
			mViewHolder.iv_association1 = (RoundImageView) mView
					.findViewById(R.id.iv_association1);
			mViewHolder.tv_association1 = (TextView) mView
					.findViewById(R.id.tv_association1);
			mViewHolder.ll_association2 = (LinearLayout) mView
					.findViewById(R.id.ll_association2);
			mViewHolder.iv_association2 = (RoundImageView) mView
					.findViewById(R.id.iv_association2);
			mViewHolder.tv_association2 = (TextView) mView
					.findViewById(R.id.tv_association2);
			mViewHolder.ll_association3 = (LinearLayout) mView
					.findViewById(R.id.ll_association3);
			mViewHolder.iv_association3 = (RoundImageView) mView
					.findViewById(R.id.iv_association3);
			mViewHolder.tv_association3 = (TextView) mView
					.findViewById(R.id.tv_association3);
			mViewHolder.ll_association4 = (LinearLayout) mView
					.findViewById(R.id.ll_association4);
			mViewHolder.iv_association4 = (RoundImageView) mView
					.findViewById(R.id.iv_association4);
			mViewHolder.tv_association4 = (TextView) mView
					.findViewById(R.id.tv_association4);
			mViewHolder.ll_hotMove = (LinearLayout) mView
					.findViewById(R.id.ll_hotMove);
			mViewHolder.ll_works = (LinearLayout) mView
					.findViewById(R.id.ll_works);
			mViewHolder.iv_work1 = (SmartImageView) mView
					.findViewById(R.id.iv_work1);
			mViewHolder.iv_work2 = (SmartImageView) mView
					.findViewById(R.id.iv_work2);
			mViewHolder.iv_work3 = (SmartImageView) mView
					.findViewById(R.id.iv_work3);
			mViewHolder.ll_news = (LinearLayout) mView
					.findViewById(R.id.ll_news);
			mViewHolder.mhome_viewflow = (ViewFlow) mView
					.findViewById(R.id.home_viewflow);
			mViewHolder.mhome_viewflowindicator = (CircleFlowIndicator) mView
					.findViewById(R.id.home_viewflowindicator);

			mTwoButton = new View[] { mViewHolder.btn_all_move,
					mViewHolder.btn_around_move };
			mRefreAssos = new View[] { mViewHolder.iv_association1,
					mViewHolder.iv_association2, mViewHolder.iv_association3,
					mViewHolder.iv_association4 };
			mWorksViews = new View[] { mViewHolder.iv_work1,
					mViewHolder.iv_work2, mViewHolder.iv_work3 };
		}
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		String[] strArray = new String[] { "全国活动", "周边活动" };
		for (int i = 0; i < mTwoButton.length; i++) {
			mTwoButton[i].setTag(strArray[i]);
			mTwoButton[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(Config.HOTCATEGORY, (String) v.getTag());
					mApp.startActivity(mBaseActivity,
							MoveDisplayActivity.class, bundle);

				}
			});
		}
		for (int i = 0; i < mRefreAssos.length; i++) {
			mRefreAssos[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ModelLeague league = (ModelLeague) v.getTag();
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, league);
					mApp.startActivity(mBaseActivity,
							AssociationMainActivity.class, bundle);

				}
			});
		}
		for (int i = 0; i < mWorksViews.length; i++) {
			mWorksViews[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ModelEventWorks work = (ModelEventWorks) v.getTag();
					Bundle workdata = new Bundle();
					workdata.putSerializable(Config.SEND_ACTIVITY_DATA, work);
					mApp.startActivity(mBaseActivity,
							AssociationTopicDetailActivity.class, workdata);

				}
			});
		}
		// 热门活动
		for (int i = 0; i < mHotItemViewArray.length; i++) {
			mHotItemViewArray[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ModelEvent event = (ModelEvent) v.getTag();
					Bundle eventdata = new Bundle();
					eventdata.putSerializable(Config.SEND_ACTIVITY_DATA, event);
					mApp.startActivity(mBaseActivity, MoveMainActivity.class,
							eventdata);

				}
			});
		}
		// 新鲜事
		for (int i = 0; i < mNewsItemViewArray.length; i++) {
			mNewsItemViewArray[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ModelLeagueTopic topic = (ModelLeagueTopic) v.getTag();
					Bundle topicdata = new Bundle();
					topicdata.putSerializable(Config.SEND_ACTIVITY_DATA, topic);
					mApp.startActivity(mBaseActivity,
							AssociationTopicDetailActivity.class, topicdata);

				}
			});
		}
	}

	// 设置广告自动浮动
	private void setAds() {
		if (mViewHolder != null) {
			mViewHolder.mhome_viewflow.setAdapter(new ImageAdapter(
					mBaseActivity));
			mViewHolder.mhome_viewflow
					.setFlowIndicator(mViewHolder.mhome_viewflowindicator);
			mViewHolder.mhome_viewflow.setTimeSpan(1000 * 3);
			mViewHolder.mhome_viewflow.startAutoFlowTimer();
		}
	}

	/**
	 * 初始化作品的照片的每一个view
	 */
	private void initWorksIv(List<Model> list) {
		if (list != null) {
			int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
			LinearLayout.LayoutParams work1, work2;
			work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
			work1.leftMargin = 20;
			mViewHolder.iv_work1.setLayoutParams(work1);
			work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
			work2.leftMargin = 10;
			mViewHolder.iv_work2.setLayoutParams(work2);
			mViewHolder.iv_work3.setLayoutParams(work2);
		}

	}

	public boolean IsLogin() {
		ModelUser user = mApp.getUser();
		if (user != null && user.getMobile() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 添加我的社团
	 */
	private void addMyAssociation(List<Model> list) {
		if (!IsLogin()) {
			mViewHolder.home_rl_my_association.setVisibility(View.GONE);
			return;
		}
		View itemView = null;
		TextView textView;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ModelLeague league = (ModelLeague) list.get(i);
				itemView = mInflater.inflate(R.layout.my_association_tv_item,
						null);
				textView = (TextView) itemView
						.findViewById(R.id.association_text);
				textView.setText(league.getName() + "");
				itemView.setTag(league);
				itemView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Bundle data = new Bundle();
						Model model = (Model) v.getTag();
						data.putSerializable(Config.SEND_ACTIVITY_DATA, model);
						mApp.startActivity(mBaseActivity,
								AssociationSingleActivity.class, data);

					}
				});
			}
			mViewHolder.ll_association.addView(itemView);
		}
	}

	// ------------------------------------------更新数据时把数据设置到相应的控件上------------------------------------------------
	/**
	 * 更新社团推荐
	 * 
	 * @param list
	 *            更新的数据集
	 */
	private void updateAssociation(List<Model> list) {
		// TODO
	}

	/**
	 * 更新热门活动
	 * 
	 * @param list
	 *            数据集
	 * @param views
	 *            热门活动控件集
	 */
	private void updateMove(List<Model> list, View[] views) {
		// TODO
	}

	/**
	 * 更新作品
	 * 
	 * @param list
	 */
	private void updateWorks(List<Model> list) {
		// TODO
	}

	/**
	 * 更新新鲜事
	 * 
	 * @param list
	 *            数据集
	 * @param views
	 *            新鲜事控件
	 */
	private void updateNews(List<Model> list, View[] views) {
		// TODO
	}

	// ------------------------------------------------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		List<Model> items = new ArrayList<Model>();
		Model model;
		HomeImpl homeImpl = mApp.getHomeIm();
		model = homeImpl.index();
		items.add(model);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		// TODO 这里用来访问后续写好的的接口，一个一个的添加到这里就ok了
		// mAssociationList=调用接口
		// mMoveList=调用接口
		// mWorksList=调用接口
		// mNewsList=调用接口
		return items;
	}

	/**
	 * 重写这个方法，因为这个方法时调用时在主线程当中，所以可以用这个来更新ui
	 * 
	 * @param list
	 */
	@Override
	public void addHeadList(List<Model> list) {
		// TODO 更新ui线程
		// updateAssociation(list);
		// updateMove(list, views);
		// updateNews(list, views);
		// updateWorks(list);
		if (mList.size() > 1) {
			dismissTheProgress();
			mListView.setFooterGone();
			return;
		}
		super.addHeadList(list);
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {

		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
