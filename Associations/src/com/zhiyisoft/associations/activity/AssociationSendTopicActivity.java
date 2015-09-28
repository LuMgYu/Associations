package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.umeng.socialize.utils.Log;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.EmotionGridViewAdapter;
import com.zhiyisoft.associations.adapter.ViewpagerCommonAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
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
	/******** activity传过来的model类型 ************/
	private ModelLeague mLeague;
	private ModelEvent mEvent;

	/******** activity传过来的model类型 ************/

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
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			Model model = (Model) bundle.get(Config.SEND_ACTIVITY_DATA);
			if (model instanceof ModelLeague) {
				mLeague = (ModelLeague) model;
			} else if (model instanceof ModelEvent) {
				mEvent = (ModelEvent) model;
			}
		}
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
		mUser = mApp.getUser();
		mImageManager = LocalImageManager.from(mApp);
		addImageToHsv(null, ADDPHOTO);
	}

	/**
	 * 添加图片到下面布局中
	 */
	private final int ADDPHOTO = 0;
	private final int PHOTO = 1;
	private ModelUser mUser;
	private ArrayList<String> mPhotoList;

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
		tv_title_right.setOnClickListener(this);
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
		case R.id.tv_title_right:
			String str_title = topic_title.getText().toString();
			String str_content = topic_content.getText().toString();
			checkThedata(str_title, str_content);
			if (checkThedata(str_content, str_content)) {
				if (mLeague != null) {
					ModelLeagueTopic topic = new ModelLeagueTopic();
					topic.setGid(mLeague.getGid());
					topic.setTitle(str_title);
					topic.setContent(str_content);
					sendTopicToNet(topic);
				} else if (mEvent != null) {
					ModelEventWorks works = new ModelEventWorks();
					works.setId(mEvent.getId());
					works.setTitle(str_title);
					works.setIntro(str_content);
					sendWorksToNet(works);
				}
			}
			break;
		}

	}

	/**
	 * 判断标题和内容是否为空
	 * 
	 * @param str_title
	 * @param str_content
	 */
	private boolean checkThedata(String str_title, String str_content) {
		if (str_title == null || str_title.length() < 1) {
			ToastUtils.showToast("标题不能为空");
			return false;
		}
		if (str_content == null || str_content.length() < 1) {
			ToastUtils.showToast("内容不能为空");
			return false;
		}
		return true;

	}

	/**
	 * 上传多张照片
	 * 
	 * @param user
	 */
	private void sendTopicToNet(ModelLeagueTopic topic) {
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, mUser.getOauth_token());
		params.put(Api.oauth_token_secret, mUser.getOauth_token_secret());
		params.put("gid", topic.getGid());
		params.put("title", topic.getTitle());
		params.put("content", topic.getContent());
		Log.i("param", params.toString());
		if (mPhotoList != null) {
			for (int i = 0; i < mPhotoList.size() - 1; i++) {
				try {
					File file = new File(mPhotoList.get(i));
					params.put("file" + i, file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Group&act=createTopic",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						Log.i("uploadpath", "==========" + arg1 + "");
						try {
							JSONObject jsonObject = new JSONObject(arg1);
							if (jsonObject.has("status")) {
								int status = jsonObject.getInt("status");
								if (status == 1) {
									ToastUtils.showToast("发表话题成功");
								} else {
									ToastUtils.showToast("发表话题失败！");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
	}

	/**
	 * 
	 * 上传作品
	 * 
	 * @param work
	 */
	private void sendWorksToNet(ModelEventWorks work) {
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, mUser.getOauth_token());
		params.put(Api.oauth_token_secret, mUser.getOauth_token_secret());
		params.put("id ", work.getId());
		params.put("title", work.getTitle());
		params.put("intro", work.getIntro());
		Log.i("param", params.toString());
		// 上传图片
		if (mPhotoList != null) {
			for (int i = 0; i < mPhotoList.size() - 1; i++) {
				try {
					File file = new File(mPhotoList.get(i));
					params.put("file" + i, file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Event&act=uploadWork",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						Log.i("uploadpath", "==========" + arg1 + "");
						try {
							JSONObject jsonObject = new JSONObject(arg1);
							if (jsonObject.has("status")) {
								int status = jsonObject.getInt("status");
								if (status == 1) {
									ToastUtils.showToast("上传作品成功");
								} else {
									ToastUtils.showToast("上传作品失败！");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
	}

	// -------------------------------------------------------------------------
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			mPhotoList = (ArrayList<String>) bundle
					.get(Config.GET_ACTIVITY_DATA);
			for (String str : mPhotoList) {
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
