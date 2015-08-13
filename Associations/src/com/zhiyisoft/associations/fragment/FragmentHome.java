package com.zhiyisoft.associations.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentHome extends BaseFragment {
	private RelativeLayout home_rl_ads;
	private RelativeLayout home_rl_my_association;
	private TextView tv_my_association;
	private HorizontalScrollView hsv_association;
	private LinearLayout ll_association;
	private LinearLayout home_ll_move;
	private Button btn_all_move;
	private Button btn_around_move;
	private LinearLayout ll_association1;
	private RoundImageView iv_association1;
	private TextView tv_association1;
	private LinearLayout ll_association2;
	private RoundImageView iv_association2;
	private TextView tv_association2;
	private LinearLayout ll_association3;
	private RoundImageView iv_association3;
	private TextView tv_association3;
	private LinearLayout ll_association4;
	private RoundImageView iv_association4;
	private TextView tv_association4;
	private LinearLayout ll_hotMove;
	private LinearLayout ll_works;
	private SmartImageView iv_work1;
	private SmartImageView iv_work2;
	private SmartImageView iv_work3;
	private LinearLayout ll_news;
	// --------------------------------------------------------
	// 热门活动的item
	private View mHotViewItem;
	// 新鲜事的item
	private View mNewsViewItem;

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	public void initView() {

		home_rl_ads = (RelativeLayout) findViewById(R.id.home_rl_ads);
		home_rl_my_association = (RelativeLayout) findViewById(R.id.home_rl_my_association);
		tv_my_association = (TextView) findViewById(R.id.tv_my_association);
		hsv_association = (HorizontalScrollView) findViewById(R.id.hsv_association);
		ll_association = (LinearLayout) findViewById(R.id.ll_association);
		home_ll_move = (LinearLayout) findViewById(R.id.home_ll_move);
		btn_all_move = (Button) findViewById(R.id.btn_all_move);
		btn_around_move = (Button) findViewById(R.id.btn_around_move);
		ll_association1 = (LinearLayout) findViewById(R.id.ll_association1);
		iv_association1 = (RoundImageView) findViewById(R.id.iv_association1);
		tv_association1 = (TextView) findViewById(R.id.tv_association1);
		ll_association2 = (LinearLayout) findViewById(R.id.ll_association2);
		iv_association2 = (RoundImageView) findViewById(R.id.iv_association2);
		tv_association2 = (TextView) findViewById(R.id.tv_association2);
		ll_association3 = (LinearLayout) findViewById(R.id.ll_association3);
		iv_association3 = (RoundImageView) findViewById(R.id.iv_association3);
		tv_association3 = (TextView) findViewById(R.id.tv_association3);
		ll_association4 = (LinearLayout) findViewById(R.id.ll_association4);
		iv_association4 = (RoundImageView) findViewById(R.id.iv_association4);
		tv_association4 = (TextView) findViewById(R.id.tv_association4);
		ll_hotMove = (LinearLayout) findViewById(R.id.ll_hotMove);
		ll_works = (LinearLayout) findViewById(R.id.ll_works);
		iv_work1 = (SmartImageView) findViewById(R.id.iv_work1);
		iv_work2 = (SmartImageView) findViewById(R.id.iv_work2);
		iv_work3 = (SmartImageView) findViewById(R.id.iv_work3);
		ll_news = (LinearLayout) findViewById(R.id.ll_news);

	}

	@Override
	public void initListener() {

	}

	@Override
	public void initData() {
		initWorksIv();
		if (mHotViewItem == null) {
			mHotViewItem = mInflater.inflate(R.layout.move_item, null);
			ll_hotMove.addView(mHotViewItem);
			ll_hotMove.addView(mInflater.inflate(R.layout.move_item, null));
			ll_hotMove.addView(mInflater.inflate(R.layout.move_item, null));
		}

		if (mNewsViewItem == null) {
			mNewsViewItem = mInflater.inflate(R.layout.association_news_item,
					null);
			ll_news.addView(mNewsViewItem);
			ll_news.addView(mInflater.inflate(R.layout.association_news_item,
					null));
			ll_news.addView(mInflater.inflate(R.layout.association_news_item,
					null));
		}
		addMyAssociation();
	}

	/**
	 * 初始化作品的照片的每一个view
	 */
	private void initWorksIv() {
		int photoWidth = (UIUtils.getWindowWidth(getActivity()) - 60) / 3;
		LinearLayout.LayoutParams work1, work2;
		work1 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work1.leftMargin = 20;
		iv_work1.setLayoutParams(work1);
		work2 = new LinearLayout.LayoutParams(photoWidth, photoWidth);
		work2.leftMargin = 10;
		iv_work2.setLayoutParams(work2);
		iv_work3.setLayoutParams(work2);

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
			ll_association.addView(itemView);
		}
	}

	@Override
	public void onClick(View v) {

	}

}
