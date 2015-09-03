package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyPhotoGridViewAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.widget.MyGridView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationTopicDetailActivity extends BaseActivity {
	private LinearLayout ll_detail_all;

	private TextView content_tv_title;
	private RoundImageView user_icon;
	private TextView content_tv_user;
	private TextView content_tv_date;
	private TextView content_tv_content;
	private LinearLayout content_ll_main;
	private ImageView phiz;
	private EditText fill_content;
	private Button btn_return;
	private View mNeedView;
	public final static int FLAG_SINGLEPHOTO = 0;
	public final static int FLAG_MANYPHOTO = 1;
	public final static int FLAG_FILE = 2;
	public final static int FLAG_VIDEO = 3;
	public final static int FLAG_MUSIC = 4;
	public final static int FLAG_ESSAY = 5;
	private int mCurrentFlag = 3;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAllImagetitle(0, 0, R.drawable.shareout, 0);
	}

	@Override
	public String setCenterTitle() {
		return "详情";

	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mCurrentFlag = bundle.getInt(Config.SEND_ACTIVITY_DATA);
		}

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_topic_detail;
	}

	@Override
	public void initView() {
		ll_detail_all = (LinearLayout) findViewById(R.id.ll_detail_all);
		content_tv_title = (TextView) findViewById(R.id.content_tv_title);
		user_icon = (RoundImageView) findViewById(R.id.user_icon);
		content_tv_user = (TextView) findViewById(R.id.content_tv_user);
		content_tv_date = (TextView) findViewById(R.id.content_tv_date);
		content_tv_title = (TextView) findViewById(R.id.content_tv_title);
		content_tv_content = (TextView) findViewById(R.id.content_tv_content);
		content_ll_main = (LinearLayout) findViewById(R.id.content_ll_main);
		phiz = (ImageView) findViewById(R.id.phiz);
		fill_content = (EditText) findViewById(R.id.fill_content);
		btn_return = (Button) findViewById(R.id.btn_return);
		initContentViewData(mCurrentFlag);
		initReplayView();
	}

	/**
	 * 初始化 评论的条数
	 */
	private void initReplayView() {
		// TODO 这里只是显示数据而已，并没有什么卵用
		int totalNum = 5;
		for (int i = 0; i < totalNum; i++) {
			View view = mInflater.inflate(R.layout.detail_replay_item, null);
			// TODO 这里以后需要初始化控件，然后把数据添加上去
			ll_detail_all.addView(view);
		}
	}

	/**
	 * 这里先要判断content_ll_main需要加载什么内容
	 * 
	 * eg： 图片（一张图，或者多图），视频，文字，音乐
	 */
	/**
	 * 
	 */
	private void initContentViewData(int flag) {
		switch (flag) {
		case FLAG_SINGLEPHOTO:
			initSinglePhoto();
			break;
		case FLAG_MANYPHOTO:
			initManyPhoto();
			break;
		case FLAG_FILE:
			initDownledFile();
			break;
		case FLAG_VIDEO:
			initVedio();
			break;
		case FLAG_MUSIC:
			initMusic();
			break;
		case FLAG_ESSAY:
			content_ll_main.setVisibility(View.GONE);
			break;
		}
	}

	/**
	 * 初始化单个照片的布局
	 */
	private void initSinglePhoto() {
		ImageView imageView = new ImageView(this);
		LinearLayout.LayoutParams params = new LayoutParams(
				UIUtils.getWindowWidth(mApp), 300);
		params.setMargins(10, 0, 10, 0);
		imageView.setLayoutParams(params);
		imageView.setBackgroundResource(R.drawable.detail_single_photo);
		content_ll_main.addView(imageView);
	}

	/**
	 * 初始化多个照片的布局
	 */
	private void initManyPhoto() {
		int[] photoArray = new int[] { R.drawable.detail_single_photo,
				R.drawable.association_icon, R.drawable.detail_single_photo,
				R.drawable.association_icon, R.drawable.detail_single_photo,
				R.drawable.detail_single_photo };
		mNeedView = addViewToContent(content_ll_main,
				R.layout.detail_many_photo_item);
		MyGridView gridView = (MyGridView) mNeedView
				.findViewById(R.id.detail_gv_many_photo);
		MyPhotoGridViewAdapter adapter = new MyPhotoGridViewAdapter(photoArray,
				this);
		gridView.setAdapter(adapter);

	}

	/**
	 * 初始化下载文件的item
	 */
	private void initDownledFile() {
		mNeedView = addViewToContent(content_ll_main,
				R.layout.detail_file_download_item);
		ImageView detail_file_iv = (ImageView) mNeedView
				.findViewById(R.id.detail_file_iv);
		TextView detail_file_tv = (TextView) mNeedView
				.findViewById(R.id.detail_file_tv);
		Button detail_file_iv_issure = (Button) mNeedView
				.findViewById(R.id.detail_file_iv_issure);
	}

	private void initVedio() {
		mNeedView = addViewToContent(content_ll_main,
				R.layout.detail_vedio_item);
		SmartImageView iv_vedio = (SmartImageView) mNeedView
				.findViewById(R.id.iv_vedio);
		ImageView iv_vedio_click = (ImageView) mNeedView
				.findViewById(R.id.iv_vedio_click);
	}

	private void initMusic() {
		mNeedView = addViewToContent(content_ll_main,
				R.layout.detail_music_item);
		ImageView detail_iv_start = (ImageView) mNeedView
				.findViewById(R.id.detail_iv_start);
		TextView detail_tv_time = (TextView) mNeedView
				.findViewById(R.id.detail_tv_time);
	}

	/**
	 * 把布局加载到内容布局里面
	 * 
	 * 返回的是生成的view
	 */
	private View addViewToContent(ViewGroup parent, int xml) {
		mNeedView = mInflater.inflate(xml, null);
		parent.addView(mNeedView);
		return mNeedView;
	}

	@Override
	public void initListener() {
		iv_title_right2.setOnClickListener(this);
		// rl_gender.setOnClickListener(this);
		// rl_school.setOnClickListener(this);
		// rl_homeland.setOnClickListener(this);
		// rl_email.setOnClickListener(this);
		// rl_phone.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_title_right2:
			preformShare();
			break;
		case R.id.rl_gender:
			break;
		case R.id.rl_school:
			// Bundle data2 = new Bundle();
			// mApp.startActivity(this, MeSettingProvinceActivity.class, data2);
			break;
		case R.id.rl_homeland:
			break;
		case R.id.rl_email:
			break;
		case R.id.rl_phone:
			break;
		}

	}
}
