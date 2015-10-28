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

import com.umeng.socialize.utils.Log;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMainActivity;
import com.zhiyisoft.associations.activity.AssociationSingleActivity;
import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.activity.MoveDisplayActivity;
import com.zhiyisoft.associations.activity.MoveLocationDisplayActivity;
import com.zhiyisoft.associations.activity.MoveMainActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.HomeImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelCommonAttach;
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
import com.zhiyisoft.associations.widget.ads.MyADView;
import com.zhiyisoft.associations.widget.ads.MyADViewModel;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现homeAdapter
 *
 */

public class HomeAdapter extends BAdapter {
	public static final String TAG = "HOME";
	public final static int HOTMOVECOUNT = 3; // 热门活动的数量
	public final static int NEWSCOUNT = 5; // 新鲜事的的数量

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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.copyoffragment_home, null);
			initView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		setAds(holder);
		// if (isFirstInit || isRefresh) {
		initData(position, holder);
		// }
		return convertView;
	}

	/**
	 * 初始化数据
	 */
	private void initData(int pos, ViewHolder holder) {
		ModelHome home = (ModelHome) mList.get(pos);
		if (home != null) {
			initWorksIv(home.getWorks(), holder);
			addAdviceAssocition(home.getGroups(), holder);
			addMyAssociation(home.getJoinedGroup(), holder);
			adddataToWork(home.getWorks(), holder);
			initHotView(home.getEvents(), holder);
			initNewsView(home.getTopics(), holder);
			setListener();
		}
	}

	/**
	 * 添加作品数据到界面
	 * 
	 * @param works
	 */
	private void adddataToWork(List<Model> works, ViewHolder holder) {
		if (works != null && holder != null) {
			holder.ll_works.setVisibility(View.VISIBLE);
			for (int i = 0; i < works.size(); i++) {
				ModelEventWorks work = (ModelEventWorks) works.get(i);
				if (i == 0) {
					holder.iv_work1.setTag(work);
					mApp.displayImage(work.getFaceurl(), holder.iv_work1);
					// holder.iv_work1.setImageUrl(work.getFaceurl());
				} else if (i == 1) {
					holder.iv_work2.setTag(work);
					mApp.displayImage(work.getFaceurl(), holder.iv_work2);
					// holder.iv_work2.setImageUrl(work.getFaceurl());
				} else if (i == 2) {
					mApp.displayImage(work.getFaceurl(), holder.iv_work3);
					holder.iv_work3.setTag(work);
					// holder.iv_work3.setImageUrl(work.getFaceurl());
				}
			}

		}
	}

	/**
	 * 添加社团推荐
	 * 
	 * @param groups
	 */
	private void addAdviceAssocition(List<Model> groups, ViewHolder holder) {
		if (groups != null && holder != null) {
			for (int i = 0; i < groups.size(); i++) {
				ModelLeague league = (ModelLeague) groups.get(i);
				if (i == 0) {
					if (league.getName() != null) {
						holder.ll_allAssociations.setVisibility(View.VISIBLE);
						holder.ll_association1.setVisibility(View.VISIBLE);
					}
					holder.iv_association1.setTag(league);
					// holder.iv_association1
					// .setImageUrl(league.getLogourl());
					mApp.displayImage(league.getLogourl(),
							holder.iv_association1);
					holder.tv_association1.setText(league.getName());
				} else if (i == 1) {
					if (league.getName() != null) {
						holder.ll_association2.setVisibility(View.VISIBLE);
					}
					holder.iv_association2.setTag(league);
					// holder.iv_association2
					// .setImageUrl(league.getLogourl());
					mApp.displayImage(league.getLogourl(),
							holder.iv_association2);
					holder.tv_association2.setText(league.getName());
				} else if (i == 2) {
					if (league.getName() != null) {
						holder.ll_association3.setVisibility(View.VISIBLE);
					}
					holder.iv_association3.setTag(league);
					mApp.displayImage(league.getLogourl(),
							holder.iv_association3);
					holder.tv_association3.setText(league.getName());
				} else if (i == 3) {
					if (league.getName() != null) {
						holder.ll_association4.setVisibility(View.VISIBLE);
					}
					holder.iv_association4.setTag(league);
					mApp.displayImage(league.getLogourl(),
							holder.iv_association4);
					// holder.iv_association4
					// .setImageUrl(league.getLogourl());
					holder.tv_association4.setText(league.getName());
				}
			}
		}
	}

	/**
	 * 初始化新鲜事的数量
	 */
	private void initNewsView(List<Model> list, ViewHolder holder) {
		if (list != null) {
			mNewsItemViewArray = new View[NEWSCOUNT];
			if (holder.ll_news.getChildCount() > 0) {
				holder.ll_news.removeAllViews();
			}
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
				List<Model> photos = topic.getAttachs();
				if (photos != null) {
					if (photos.size() >= 1) {
						ModelCommonAttach attach = (ModelCommonAttach) photos
								.get(0);
						mApp.displayImage(attach.getUrl(), move_iv);
					}
				}
				// move_iv.setImageUrl();
				move_tv_title.setText(topic.getTitle());
				move_tv_content.setText(topic.getContent());
				/******************* 初始化新鲜事以及添加 ****************************/
				holder.ll_news.addView(mNewsItemViewArray[i]);

			}
		}
	}

	/**
	 * 
	 * 初始化热门活动的数量
	 */
	private void initHotView(List<Model> list, ViewHolder holder) {
		Log.i(TAG, "initHotView");
		if (list != null && holder != null) {
			Log.i(TAG, "list != null && holder != null");
			mHotItemViewArray = new View[HOTMOVECOUNT];
			if (holder.ll_hotMove.getChildCount() > 0) {
				holder.ll_hotMove.removeAllViews();
			}
			Log.i(TAG, "list != null && holder != null" + list.size() + "");
			for (int i = 0; i < list.size(); i++) {
				mHotItemViewArray[i] = mInflater.inflate(R.layout.move_item,
						null);
				ViewHolder viewHolder = new ViewHolder();
				/**************** 绑定活动的数据先初始化再绑定数据 *****************/
				viewHolder.move_smiv_icon = (SmartImageView) mHotItemViewArray[i]
						.findViewById(R.id.move_smiv_icon);
				viewHolder.move_tv_end = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_end);
				viewHolder.move_tv_title = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_title);
				viewHolder.move_btn_online = (Button) mHotItemViewArray[i]
						.findViewById(R.id.move_btn_online);
				viewHolder.move_btn_event = (Button) mHotItemViewArray[i]
						.findViewById(R.id.move_btn_event);

				viewHolder.move_tv_deadline = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_deadline);
				viewHolder.move_tv_allmove = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_allmove);
				viewHolder.move_tv_content = (TextView) mHotItemViewArray[i]
						.findViewById(R.id.move_tv_content);
				// 绑定数据
				Log.i(TAG, "(ModelEvent) list.get(i);");
				ModelEvent event = (ModelEvent) list.get(i);
				if (event != null) {
					Log.i(TAG, "event != null");
					mHotItemViewArray[i].setTag(event);
					mApp.displayImage(event.getLogourl(),
							viewHolder.move_smiv_icon);
					// holder.move_smiv_icon.setImageUrl(event.getLogourl());
					int isover = event.getIsover();
					if (isover == 0) {
						viewHolder.move_tv_end.setVisibility(View.GONE);
					} else {
						viewHolder.move_tv_end.setVisibility(View.GONE);
					}
					viewHolder.move_tv_title.setText(event.getTitle());
					String isonline = event.getOnline();
					if (isonline.equals("0")) {
						viewHolder.move_btn_online.setText("线上");
					} else {
						viewHolder.move_btn_online.setText("线下");
					}
					viewHolder.move_btn_event.setText(event.getTypeName());

					viewHolder.move_tv_deadline.setText(DateUtil
							.stamp2humanDate(event.geteTime()));
					viewHolder.move_tv_allmove.setText(event.getJoinCount());
					viewHolder.move_tv_content.setText(event.getExplain());

					/**************** 绑定活动的数量 化再绑定数据 *****************/
					holder.ll_hotMove.addView(mHotItemViewArray[i]);
					Log.i(TAG,
							"holder.ll_hotMove.addView(mHotItemViewArray[i]);");
				}
			}
		}
	}

	/**
	 * 初始化控件
	 */
	private void initView(ViewHolder holder, View view) {
		if (view != null && holder != null) {
			holder.home_rl_ads = (RelativeLayout) view
					.findViewById(R.id.home_rl_ads);
			holder.home_rl_my_association = (RelativeLayout) view
					.findViewById(R.id.home_rl_my_association);
			holder.tv_my_association = (TextView) view
					.findViewById(R.id.tv_my_association);
			holder.hsv_association = (HorizontalScrollView) view
					.findViewById(R.id.hsv_association);
			holder.ll_association = (LinearLayout) view
					.findViewById(R.id.ll_association);
			holder.home_ll_move = (LinearLayout) view
					.findViewById(R.id.home_ll_move);
			holder.btn_all_move = (Button) view.findViewById(R.id.btn_all_move);
			holder.btn_around_move = (Button) view
					.findViewById(R.id.btn_around_move);
			holder.ll_allAssociations = (LinearLayout) view
					.findViewById(R.id.ll_allAssociations);
			holder.ll_association1 = (LinearLayout) view
					.findViewById(R.id.ll_association1);
			holder.iv_association1 = (RoundImageView) view
					.findViewById(R.id.iv_association1);
			holder.tv_association1 = (TextView) view
					.findViewById(R.id.tv_association1);
			holder.ll_association2 = (LinearLayout) view
					.findViewById(R.id.ll_association2);
			holder.iv_association2 = (RoundImageView) view
					.findViewById(R.id.iv_association2);
			holder.tv_association2 = (TextView) view
					.findViewById(R.id.tv_association2);
			holder.ll_association3 = (LinearLayout) view
					.findViewById(R.id.ll_association3);
			holder.iv_association3 = (RoundImageView) view
					.findViewById(R.id.iv_association3);
			holder.tv_association3 = (TextView) view
					.findViewById(R.id.tv_association3);
			holder.ll_association4 = (LinearLayout) view
					.findViewById(R.id.ll_association4);
			holder.iv_association4 = (RoundImageView) view
					.findViewById(R.id.iv_association4);
			holder.tv_association4 = (TextView) view
					.findViewById(R.id.tv_association4);
			holder.ll_hotMove = (LinearLayout) view
					.findViewById(R.id.ll_hotMove);
			holder.ll_works = (LinearLayout) view.findViewById(R.id.ll_works);
			holder.iv_work1 = (SmartImageView) view.findViewById(R.id.iv_work1);
			holder.iv_work2 = (SmartImageView) view.findViewById(R.id.iv_work2);
			holder.iv_work3 = (SmartImageView) view.findViewById(R.id.iv_work3);
			holder.ll_news = (LinearLayout) view.findViewById(R.id.ll_news);
			holder.adView = (MyADView) view.findViewById(R.id.adView);
			mTwoButton = new View[] { holder.btn_all_move,
					holder.btn_around_move };
			mRefreAssos = new View[] { holder.iv_association1,
					holder.iv_association2, holder.iv_association3,
					holder.iv_association4 };
			mWorksViews = new View[] { holder.iv_work1, holder.iv_work2,
					holder.iv_work3 };
		}
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		for (int i = 0; i < mTwoButton.length; i++) {
			ModelEvent event = new ModelEvent();
			if (i == 0) {
				event.setTypeName("全国活动");
			} else if (i == 1) {
				event.setTypeName("周边活动");
			}
			mTwoButton[i].setTag(event);
			mTwoButton[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO 这里以后需要添加 周边活动之类的
					ModelEvent event = (ModelEvent) v.getTag();
					Bundle bundle = new Bundle();
					bundle.putSerializable(Config.SEND_ACTIVITY_DATA, event);
					if (event.getTypeName().equals("周边活动")) {
						mApp.startActivity(mBaseActivity,
								MoveLocationDisplayActivity.class, bundle);
					} else {
						mApp.startActivity(mBaseActivity,
								MoveDisplayActivity.class, bundle);
					}

				}
			});
		}
		if (mRefreAssos != null) {
			for (int i = 0; i < mRefreAssos.length; i++) {
				if (mRefreAssos[i] != null) {
					mRefreAssos[i].setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							ModelLeague league = (ModelLeague) v.getTag();
							Bundle bundle = new Bundle();
							bundle.putSerializable(Config.SEND_ACTIVITY_DATA,
									league);
							mApp.startActivity(mBaseActivity,
									AssociationMainActivity.class, bundle);

						}
					});
				}
			}
		}
		if (mWorksViews != null) {
			for (int i = 0; i < mWorksViews.length; i++) {
				if (mWorksViews[i] != null) {
					mWorksViews[i].setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							ModelEventWorks work = (ModelEventWorks) v.getTag();
							Bundle workdata = new Bundle();
							workdata.putSerializable(Config.SEND_ACTIVITY_DATA,
									work);
							mApp.startActivity(mBaseActivity,
									AssociationTopicDetailActivity.class,
									workdata);

						}
					});
				}
			}
		}
		if (mHotItemViewArray != null) {
			// 热门活动
			for (int i = 0; i < mHotItemViewArray.length; i++) {
				if (mHotItemViewArray[i] != null) {
					mHotItemViewArray[i]
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									ModelEvent event = (ModelEvent) v.getTag();
									Bundle eventdata = new Bundle();
									eventdata.putSerializable(
											Config.SEND_ACTIVITY_DATA, event);
									mApp.startActivity(mBaseActivity,
											MoveMainActivity.class, eventdata);

								}
							});
				}
			}
		}
		if (mNewsItemViewArray != null) {
			// 新鲜事
			for (int i = 0; i < mNewsItemViewArray.length; i++) {
				if (mNewsItemViewArray[i] != null) {
					mNewsItemViewArray[i]
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									ModelLeagueTopic topic = (ModelLeagueTopic) v
											.getTag();
									Bundle topicdata = new Bundle();
									topicdata.putSerializable(
											Config.SEND_ACTIVITY_DATA, topic);
									mApp.startActivity(
											mBaseActivity,
											AssociationTopicDetailActivity.class,
											topicdata);

								}
							});
				}
			}
		}
	}

	private boolean isFirst = true;

	// 设置广告自动浮动
	private void setAds(ViewHolder holder) {
		if (holder != null) {

			List<MyADViewModel> data = new ArrayList<MyADViewModel>();
			data.add(new MyADViewModel(R.drawable.banner1));
			data.add(new MyADViewModel(R.drawable.banner2));
			data.add(new MyADViewModel(R.drawable.banner3));
			data.add(new MyADViewModel(R.drawable.banner4));
			if (isFirst) {
				try {
					holder.adView.setData(data);
					holder.adView.setTransformDuration((int) (0.8 * 1000));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isFirst = false;
			}
		}
	}

	/**
	 * 初始化作品的照片的每一个view
	 */
	private void initWorksIv(List<Model> list, ViewHolder holder) {
		if (list != null && holder != null) {
			int photoWidth = (UIUtils.getWindowWidth(mBaseActivity) - 60) / 3;
			LinearLayout.LayoutParams work1, work2;
			work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
			work1.leftMargin = 20;
			holder.iv_work1.setLayoutParams(work1);
			work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
			work2.leftMargin = 10;
			holder.iv_work2.setLayoutParams(work2);
			holder.iv_work3.setLayoutParams(work2);
		}

	}

	public boolean IsLogin() {
		ModelUser user = mApp.getUser();
		if (user != null && user.getOauth_token() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 添加我的社团
	 */
	private void addMyAssociation(List<Model> list, ViewHolder holder) {
		if (!IsLogin()) {
			holder.home_rl_my_association.setVisibility(View.GONE);
			return;
		}
		View itemView;
		TextView textView;
		if (list != null) {
			if (holder.ll_association.getChildCount() > 0) {
				holder.ll_association.removeAllViews();
			}
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
				if (itemView != null && holder.ll_association != null) {
					holder.ll_association.addView(itemView);
					Log.i("ll_association",
							"holder.ll_association.getChildCount()="
									+ holder.ll_association.getChildCount());
				}
			}
		}
	}

	// ------------------------------------------更新数据时把数据设置到相应的控件上------------------------------------------------

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
		Model model;
		HomeImpl homeImpl = mApp.getHomeIm();
		model = homeImpl.index();
		items.add(model);
		return items;
	}

	/**
	 * 重写这个方法，因为这个方法时调用时在主线程当中，所以可以用这个来更新ui
	 * 
	 * @param list
	 */
	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay2(list);
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
