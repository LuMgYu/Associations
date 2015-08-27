package com.zhiyisoft.associations.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.UiThreadTest;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.util.UIUtils;

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

	// private RoundImageView topic_title;
	// private EditText topic_content;
	// private TextView topic_iv_big_image;
	// private RelativeLayout topic_iv_delete_image;
	// private RelativeLayout topic_image;
	// private TextView topic_expression;
	@Override
	public void initView() {
		topic_title = (EditText) findViewById(R.id.topic_title);
		topic_content = (EditText) findViewById(R.id.topic_content);
		hsvScrollView = (HorizontalScrollView) findViewById(R.id.hsvScrollView);
		ll_ScrollView = (LinearLayout) findViewById(R.id.ll_ScrollView);
		topic_image = (ImageView) findViewById(R.id.topic_image);
		topic_expression = (ImageView) findViewById(R.id.topic_expression);
		addImageToHsv(null, ADDPHOTO);
	}

	/**
	 * 添加图片到下面布局中
	 */
	private final int ADDPHOTO = 0;
	private final int PHOTO = 1;

	private void addImageToHsv(Bitmap bitmap, int type) {
		View itemView = mInflater.inflate(R.layout.hsv_img_item, null);
		ImageView big_image = (ImageView) itemView.findViewById(R.id.big_image);
		ImageView delete_image = (ImageView) itemView
				.findViewById(R.id.delete_image);
		if (type == PHOTO) {
			if (bitmap != null) {
				big_image.setImageBitmap(bitmap);
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
			big_image.setImageResource(R.drawable.add);
			itemView.setTag("tag");
			delete_image.setVisibility(View.GONE);
			ll_ScrollView.addView(itemView);
			big_image.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mApp.startActivity(AssociationSendTopicActivity.this,
							AssociationPhoneAlbumActivity.class, null);
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
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		mBitmap = Bitmap.createScaledBitmap(mBitmap,
				UIUtils.getWindowWidth(getApplicationContext()) / 4,
				UIUtils.getWindowWidth(getApplicationContext()) / 4, true);
		addImageToHsv(mBitmap, PHOTO);
		return mBitmap;
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
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.topic_expression:
			break;
		}

	}
}
