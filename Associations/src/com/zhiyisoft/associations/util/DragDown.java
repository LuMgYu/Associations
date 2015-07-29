package com.zhiyisoft.associations.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.impl.RefreshListener;

public class DragDown implements OnTouchListener, OnGestureListener,
		RefreshListener {
	private GestureDetector mGestureDetector;
	private static final int CONTENT_TEXT_ID = 0;
	private static final int CONTENT_TIME_ID = 1;
	private static final int CONTENT_LAYOUT_ID = 2;
	private static final int CONTENT_IMAGE_ID = 3;
	private static final int FOOTER_IMAGE_ID = 3;
	private static final int OFFSET = -180;
	private static final int IMAGE_LEFT_MARGIN = 40;
	private static final int IMAGE_RIGHT_MARGIN = 40;
	private static final int BOTTON_MARIGN = 20;

	private static final int MIN_OFFSET = 0;

	private static final String TAG = "DragDown";

	private static boolean hasHeader = false;
	private long lastRefresh;

	private boolean refreshing = false;
	/**
	 * 用于判断是否下拉之后往回松，如果往回松滑动到指定的范围内时候则返回
	 */
	private static boolean hasReverse = false;
	private static boolean hasTouch = false;

	private LinearLayout header;
	private LinearLayout footer;
	private LinearLayout headerContent;
	private LinearLayout footerContent;

	private static Animation anim;
	private static Animation anim_down;
	private BaseActivity activityObj;
	private Context context;

	private ListView view;
	private static boolean viewHasHeader = false;
	private static boolean viewHasFooter = false;

	/**
	 * ListView的头部信息
	 * 
	 * @param context
	 * @param v
	 */
	public DragDown(Context context, ListView v) {
		mGestureDetector = new GestureDetector(context, this);
		setContext(context);
		setView(v);
		try {
			setActivityObj((BaseActivity) context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		footer = (LinearLayout) View.inflate(getContext(), R.layout.more_item,
				null);
		setFooter(footer);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public BaseActivity getActivityObj() {
		return activityObj;
	}

	public void setActivityObj(BaseActivity activityObj) {
		this.activityObj = activityObj;
	}

	public ListView getView() {
		return view;
	}

	public void setView(ListView view) {
		this.view = view;
	}

	/**
	 * @return 获取头部的view
	 */
	public View getHeaderView() {
		anim = AnimationUtils.loadAnimation(getContext(), R.anim.reverse_up);
		anim_down = AnimationUtils.loadAnimation(getContext(),
				R.anim.reverse_down);
		anim.setFillEnabled(true);
		anim.setFillAfter(true);
		anim_down.setFillEnabled(true);
		anim_down.setFillAfter(true);

		LinearLayout.LayoutParams lpCenter = this.getLinearLayout();
		header = new LinearLayout(getContext());
		header.setOrientation(LinearLayout.HORIZONTAL);
		header.setGravity(Gravity.CENTER_HORIZONTAL);

		headerContent = new LinearLayout(getContext());
		headerContent.setId(CONTENT_LAYOUT_ID);
		headerContent.setOrientation(LinearLayout.VERTICAL);

		LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lpText.gravity = Gravity.CENTER;

		TextView contentText = new TextView(getContext());
		contentText.setId(CONTENT_TEXT_ID);
		contentText.setText("下拉刷新");
		TextView contentTime = new TextView(getContext());
		contentTime.setId(CONTENT_TIME_ID);
		headerContent.addView(contentText, lpText);
		headerContent.addView(contentTime, lpText);

		ImageView arrow = new ImageView(getContext());
		arrow.setId(CONTENT_IMAGE_ID);
		arrow.setBackgroundResource(R.drawable.arrow_down);
		LinearLayout.LayoutParams lpImage = this.getLinearLayout();
		// lpImage.leftMargin = IMAGE_LEFT_MARGIN;
		lpImage.rightMargin = IMAGE_RIGHT_MARGIN;
		lpImage.bottomMargin = BOTTON_MARIGN;
		header.addView(arrow, lpImage);
		header.addView(headerContent, lpCenter);
		this.setTime();
		viewHasHeader = true;
		header.setClickable(false);
		return header;
	}

	/**
	 * 添加list底部view
	 */
	public View getFooterView() {
		// TODO list底部view
		// if(footer != null)
		viewHasFooter = true;
		return getFooter();
	}

	/**
	 * @return 获取线性布局的参数
	 */
	private LinearLayout.LayoutParams getLinearLayout() {
		LinearLayout.LayoutParams result = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		result.gravity = Gravity.BOTTOM;
		result.topMargin = OFFSET;
		result.bottomMargin = BOTTON_MARIGN;
		return result;
	}

	/**
	 * @return 获取activity
	 */
	public BaseActivity getActivity() {
		return (BaseActivity) this.context;
	}

	/**
	 * 判断是否可以点击
	 * 
	 * @return true 不可以点击，false 可以点击
	 */
	public boolean isUnClickable() {
		return refreshing || hasHeader;
	}

	/**
	 * 设置刷新的时间
	 */
	private void setTime() {
		if (header == null) {
			return;
		}
		TextView contentTime = (TextView) header.findViewById(CONTENT_TIME_ID);
		String time = new SimpleDateFormat("MM-dd HH:mm").format(new Date(
				this.lastRefresh));
		// String text = this.getContext().getString("最近刷新") + time;
		String text = "最近刷新" + time;

		contentTime.setText(text);
		contentTime.setGravity(Gravity.CENTER);
	}

	/**
	 * @return 获取上下文
	 */
	public Context getContext() {
		return this.context;
	}

	/*
	 * (non-Javadoc) 下拉刷新的核心方法
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (null != headerContent) {// 头部不空
			LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) headerContent
					.getLayoutParams();
			Log.v(TAG, "onTouch1-->refreshing=" + refreshing
					+ "  event.getAction"
					+ (event.getAction() == MotionEvent.ACTION_UP));
			if (!refreshing && event.getAction() == MotionEvent.ACTION_UP) {
				BaseActivity activity = getActivityObj();
				// View right = null;
				// if (activity.getCustomTitle() != null)
				// right = activity.getCustomTitle().getRight();
				// boolean canRefresh = false;
				// if (right == null) {
				// canRefresh = true;
				// } else {
				// canRefresh = right.isClickable();
				// }
				// Log.v(TAG,
				// "onTouch2-->right=null:"+(right==null)+" canRefresh="+canRefresh+" lpCenter.topMargin "
				// +lpCenter.topMargin);
				if (lpCenter.topMargin >= MIN_OFFSET) {
					// 设置最后刷新时间
					activity.refreshHeader();
				} else {
					lpCenter.topMargin = OFFSET;
					headerContent.setLayoutParams(lpCenter);
				}
				hasTouch = false;
			}
		}
		if (refreshing) {
			return true;
		}
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		hasHeader = true;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		hasHeader = false;
		return false;
	}

	/*
	 * (non-Javadoc) 刷新的核心方法
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onScroll(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// 看能否滑动
		if (this.checkCanScroll())
			return false;
		// header是否为空
		if (header == null) {
			return false;
		}
		TextView contentText = (TextView) header.findViewById(CONTENT_TEXT_ID);
		ImageView contentImage = (ImageView) header
				.findViewById(CONTENT_IMAGE_ID);
		if (headerContent == null) {
			return false;
		}
		LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) headerContent
				.getLayoutParams();
		try {
			if (distanceY < 0 && e1.getY() >= e2.getY()) {
				int height = (int) Math.ceil(Math.abs((int) (e1.getY() - e2
						.getY())) * 0.5);

				lpCenter.topMargin = height - Math.abs(OFFSET);
				headerContent.setLayoutParams(lpCenter);
				Log.v(TAG, "onScrol--> lpCenter.topMargin1 "
						+ lpCenter.topMargin);
				hasTouch = true;
			} else {
				if (e2.getY() >= e1.getY()) {
					int height = (int) Math.ceil(Math.abs((int) (e2.getY() - e1
							.getY())) * 0.5);
					lpCenter.topMargin = height + OFFSET;
					headerContent.setLayoutParams(lpCenter);
					// Log.v(TAG, "onScrol--> lpCenter.topMargin2 "
					// +lpCenter.topMargin);
					hasTouch = true;
				}
			}
		} catch (Exception ex) {
			Log.e(TAG, "onScroll exception");
			return false;
		}
		if (lpCenter.topMargin < 0) {
			if (hasReverse) {
				contentImage.startAnimation(anim_down);
				hasReverse = false;
			}
			contentText.setText("下拉可以刷新");
			contentText.setPadding(0, 7, 0, 0);
		} else {
			if (!hasReverse) {
				contentImage.startAnimation(anim);
				hasReverse = true;
			}
			contentText.setText("松开可以刷新");
			contentText.setPadding(0, 7, 0, 0);
		}
		if (refreshing) {
			return true;
		}
		return hasTouch;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	/**
	 * 顯示頭部
	 */
	@Override
	public void headerShow() {
		if (headerContent == null) {
			return;
		}
		LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) headerContent
				.getLayoutParams();
		lpCenter.topMargin = 5;
		headerContent.setLayoutParams(lpCenter);
		refreshing = true;
	}

	/**
	 * 隐藏头部
	 */
	@Override
	public void headerHiden() {
		try {
			Log.i("refresh", "dragdown headerHiden()  {");
			LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) headerContent
					.getLayoutParams();

			Animation anima = AnimationUtils.loadAnimation(getContext(),
					R.anim.activity_upoword_out);
			// header.startAnimation(anima);
			Log.i("refresh", "// header.startAnimation(anima);");
			lpCenter.topMargin = OFFSET;
			headerContent.setLayoutParams(lpCenter);
			refreshing = false;
			ImageView contentImage = (ImageView) header
					.findViewById(CONTENT_IMAGE_ID);
			Log.i("refresh", "// header.startAnimation(anima);");
			LinearLayout.LayoutParams lpImage = (android.widget.LinearLayout.LayoutParams) contentImage
					.getLayoutParams();
			lpImage.bottomMargin = BOTTON_MARIGN;
			contentImage.setBackgroundResource(R.drawable.arrow_down);
			Log.i("refresh", "setTime();-----");
			setTime();
		} catch (Exception ex) {
			Log.i("refresh", "dragdown headerHiden() catch  {");
			return;
		}
	}

	/**
	 * 头部刷新
	 */
	@Override
	public void headerRefresh() {
		this.setTime();
		if (header == null) {
			return;
		}

		Log.i("headerRefresh()", "调用了这个方法headerRefresh()");
		TextView contentText = (TextView) header.findViewById(CONTENT_TEXT_ID);
		ImageView contentImage = (ImageView) header
				.findViewById(CONTENT_IMAGE_ID);
		contentText.setText("正在刷新");
		contentText.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams lpImage = (android.widget.LinearLayout.LayoutParams) contentImage
				.getLayoutParams();
		lpImage.bottomMargin += 10;
		Anim.refresh(getContext(), contentImage);
		this.lastRefresh = System.currentTimeMillis();
	}

	/**
	 * @return 获取最后刷新的时间
	 */
	public long getLastRefresh() {
		return lastRefresh;
	}

	/**
	 * @param lastRefresh
	 *            最后刷新的时间
	 */
	public void setLastRefresh(long lastRefresh) {
		this.lastRefresh = lastRefresh;
	}

	/**
	 * @return 返回是否是空的list
	 */
	private boolean emptyList() {
		if (viewHasHeader && viewHasFooter)
			return view.getCount() == 2;
		return view.getCount() == 1;
	}

	/**
	 * 显示底部
	 */
	@Override
	public void footerShow() {
		if (footerContent != null) {
			LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) footerContent
					.getLayoutParams();
			lpCenter.topMargin = 5;
			footerContent.setLayoutParams(lpCenter);
			ImageView contentImage = (ImageView) footer
					.findViewById(FOOTER_IMAGE_ID);
			Anim.refreshMiddle(getContext(), contentImage);
		}
	}

	/**
	 * 底部设置为不可见
	 */
	@Override
	public void footerHiden() {
		try {
			LinearLayout.LayoutParams lpCenter = (android.widget.LinearLayout.LayoutParams) footer
					.getLayoutParams();
			lpCenter.bottomMargin = OFFSET;
			footer.setLayoutParams(lpCenter);
			footer.setVisibility(View.GONE);
			refreshing = false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}

	/**
	 * 看dragview是否可以滑动
	 * 
	 * @return true 当前在刷新中，或者当前展示的不是listview的第一个item；
	 */
	private boolean checkCanScroll() {
		int firstVisiblePosition = this.getView().getFirstVisiblePosition();
		return refreshing || firstVisiblePosition != 0;
	}

	public LinearLayout getFooter() {
		footer.setVisibility(View.VISIBLE);
		return footer;
	}

	public void setFooter(LinearLayout footer) {
		this.footer = footer;
	}
}
