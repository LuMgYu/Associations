package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.EmotionGridViewAdapter;
import com.zhiyisoft.associations.adapter.ViewpagerCommonAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.localImageHelper.LocalImageManager;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationSendTopicActivity extends BaseActivity {
	private EditText topic_title;
	private EditText topic_content;
	private HorizontalScrollView hsvScrollView;
	private LinearLayout ll_ScrollView;
	private ImageView topic_image;
	private ImageView topic_expression;

	private Bitmap mBitmap; // 获取本地的bitmap

	private LocalImageManager mImageManager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("发表话题", null, "发表");
	}

	@Override
	public String setCenterTitle() {
		return "";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_send_topic;
	}

	@Override
	public void initView() {
		topic_title = (EditText) findViewById(R.id.topic_title);
		topic_content = (EditText) findViewById(R.id.topic_content);
		hsvScrollView = (HorizontalScrollView) findViewById(R.id.hsvScrollView);
		ll_ScrollView = (LinearLayout) findViewById(R.id.ll_ScrollView);
		topic_image = (ImageView) findViewById(R.id.topic_image);
		topic_expression = (ImageView) findViewById(R.id.topic_expression);
		mImageManager = LocalImageManager.from(mApp);
		addImageToHsv(null, ADDPHOTO);
	}

	/**
	 * 添加图片到下面布局中
	 */
	private final int ADDPHOTO = 0;
	private final int PHOTO = 1;

	private void addImageToHsv(String path, int type) {
		View itemView = mInflater.inflate(R.layout.hsv_img_item, null);
		ImageView big_image = (ImageView) itemView.findViewById(R.id.big_image);
		ImageView delete_image = (ImageView) itemView
				.findViewById(R.id.delete_image);
		if (type == PHOTO) {
			if (path != null) {
				mImageManager.displayImage(big_image, path,
						R.drawable.default_image_small, 100, 100);
				delete_image.setTag(itemView);
				ll_ScrollView.addView(itemView);
				changeThePosition(ll_ScrollView, itemView);
				delete_image.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						View view = (View) v.getTag();
						ll_ScrollView.removeView(view);
					}
				});
			}
		} else if (type == ADDPHOTO) {
			big_image.setBackgroundResource(R.drawable.add);
			itemView.setTag("tag");
			delete_image.setVisibility(View.GONE);
			ll_ScrollView.addView(itemView);
			big_image.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mApp.startActivityForResult(
							AssociationSendTopicActivity.this,
							LocalImagListActivity.class, null);
				}
			});
		}
	}

	/**
	 * 交换位置
	 * 
	 * @param parent
	 *            父布局
	 * @param itemView
	 * 
	 */
	private void changeThePosition(LinearLayout parent, View itemView) {
		int sum = parent.getChildCount();
		for (int i = 0; i < sum; i++) {
			View view = parent.getChildAt(i);
			String str = (String) view.getTag();
			if (str != null && str == "tag") {
				parent.removeView(view);
				parent.addView(view);
			}
		}
	}

	@Override
	public void initListener() {
		topic_image.setOnClickListener(this);
		topic_expression.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topic_image:
			Bundle data2 = new Bundle();
			mApp.startActivityForResult(this, LocalImagListActivity.class,
					data2);
			break;
		case R.id.topic_expression:
			initPopWindow();
			showPop(topic_expression, 0, 0);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ArrayList<String> list = (ArrayList<String>) bundle
					.get(Config.GET_ACTIVITY_DATA);
			for (String str : list) {
				if (ll_ScrollView.getChildCount() > 6) {
					ToastUtils.showToast("最多只能选六张！");
					return;
				}
				addImageToHsv(str, PHOTO);
				// TODO 这里还需要把bitmap获取出来
			}
		}
	}

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private ViewPager emotionViewpager;
	private LinearLayout emotionflag;
	private ViewpagerCommonAdapter adapter;
	private List<View> views = new ArrayList<View>();
	private EmotionGridViewAdapter gridViewAdapter;

	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.emotion_framework, null);
			mPopupWindow = new PopupWindow(popView,
					android.widget.AbsListView.LayoutParams.MATCH_PARENT, 300);
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			mPopupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {

				}
			});
			// 设置popwindow出现和消失动画
			initPopWidge(popView);
		}
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		emotionViewpager = (ViewPager) popView
				.findViewById(R.id.emotionViewpager);
		emotionflag = (LinearLayout) popView.findViewById(R.id.emotionflag);
		gridViewAdapter = new EmotionGridViewAdapter(this, 21);
		for (int i = 0; i < 7; i++) {
			View view = mInflater.inflate(
					R.layout.emotion_framework_gridview_item, null);
			GridView gridView = (GridView) view
					.findViewById(R.id.emotion_gridView);
			gridView.setAdapter(gridViewAdapter);

			views.add(gridView);
		}
		adapter = new ViewpagerCommonAdapter(views);
		emotionViewpager.setAdapter(adapter);
	}

	/**
	 * 显示popWindow
	 * */
	@SuppressLint("NewApi")
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, x, y);
		mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
	}
}
