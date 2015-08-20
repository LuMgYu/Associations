package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationNewActivity;
import com.zhiyisoft.associations.activity.MoveMainActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.base.Model;
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
			initData();
		}
		return mView;
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		setAds();
		initWorksIv();
		addMyAssociation();
		initHotView();
		initNewsView();
		setListener();
	}

	/**
	 * 初始化新鲜事的数量
	 */
	private void initNewsView() {
		mNewsItemViewArray = new View[NEWSCOUNT];
		for (int i = 0; i < mNewsItemViewArray.length; i++) {
			mNewsItemViewArray[i] = mInflater.inflate(
					R.layout.association_news_item, null);
			mViewHolder.ll_news.addView(mNewsItemViewArray[i]);
		}
	}

	/**
	 * 
	 * 初始化热门活动的数量
	 */
	private void initHotView() {
		mHotItemViewArray = new View[HOTMOVECOUNT];
		for (int i = 0; i < mHotItemViewArray.length; i++) {
			mHotItemViewArray[i] = mInflater.inflate(R.layout.move_item, null);
			mViewHolder.ll_hotMove.addView(mHotItemViewArray[i]);
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
		}
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		for (int i = 0; i < mHotItemViewArray.length; i++) {
			mHotItemViewArray[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mApp.startActivity(mBaseActivity, MoveMainActivity.class,
							null);

				}
			});
		}
		for (int i = 0; i < mNewsItemViewArray.length; i++) {
			mNewsItemViewArray[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mApp.startActivity(mBaseActivity,
							AssociationNewActivity.class, null);

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
	private void initWorksIv() {
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

	/**
	 * 添加我的社团
	 */
	private void addMyAssociation() {
		String[] StringName = new String[] { "羽毛球社团", "乒乓球社团", "花花球社团",
				"泡妹子社团", "交友社团" };
		View itemView = null;
		TextView textView;
		for (int i = 0; i < StringName.length; i++) {
			itemView = mInflater.inflate(R.layout.my_association_tv_item, null);
			textView = (TextView) itemView.findViewById(R.id.association_text);
			textView.setText(StringName[i] + "");
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
		items.add(new Model());
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
