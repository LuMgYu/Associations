package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Video.Thumbnails;
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
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.zhiyisoft.associations.model.LocalFile;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.localImageHelper.LocalImageManager;
import com.zhiyisoft.associations.util.localMusic.LocalMusic;
import com.zhiyisoft.associations.util.localMusic.LocalMusicListActivity;
import com.zhiyisoft.associations.util.localVedio.LocalVideo;
import com.zhiyisoft.associations.util.localVedio.LocalVideoActivity;

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
	/************ 上传视频需要的控件 ****************/
	int mWorkType = 0;
	private ImageView vedio_iv_big_image;
	private FrameLayout fl_upload_video;
	private ImageView vedio_iv_start;
	private FrameLayout fl_progress;
	private ProgressBar progressBar1;
	private TextView tv_progress;
	// 文档支持dox,txt,pdf;图片支持jpg,png,gif;视频支持mp4;音频支持mp3;
	/************ 上传视频需要的控件 ****************/
	/************ 上传音乐需要的控件 ****************/
	private FrameLayout fl_upload_music;
	private TextView tv_music_name;
	/************ 上传音乐需要的控件 ****************/

	private Bitmap mBitmap; // 获取本地的bitmap

	private LocalImageManager mImageManager;
	/******** activity传过来的model类型 ************/
	private ModelLeague mLeague;
	private ModelEventWorks mWorks;
	/******** activity传过来的model类型end ************/

	private LocalVideo mVideo;
	private LocalMusic mMusic;
	private LocalFile mFile;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		if (mLeague != null) {
			setAlltitle("发表话题", null, "发表");
			hsvScrollView.setVisibility(View.VISIBLE);
			return;
		}
		if (mWorks != null) {
			mWorkType = mWorks.getExplainType();
			if (mWorkType == 1) {
				setAlltitle("发表文档", null, "发表");
				fl_upload_music.setVisibility(View.VISIBLE);
			} else if (mWorkType == 2) {
				setAlltitle("发表图片", null, "发表");
				hsvScrollView.setVisibility(View.VISIBLE);
			} else if (mWorkType == 3) {
				setAlltitle("发表视频", null, "发表");
				fl_upload_video.setVisibility(View.VISIBLE);
			} else if (mWorkType == 4) {
				setAlltitle("发表音频", null, "发表");
				fl_upload_music.setVisibility(View.VISIBLE);
			}
		}
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
			} else if (model instanceof ModelEventWorks) {
				mWorks = (ModelEventWorks) model;
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
		/************ 上传音乐需要的初始化控件 ****************/
		fl_upload_music = (FrameLayout) findViewById(R.id.fl_upload_music);
		tv_music_name = (TextView) findViewById(R.id.tv_music_name);
		fl_progress = (FrameLayout) findViewById(R.id.fl_progress);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_progress = (TextView) findViewById(R.id.tv_progress);
		/************ 上传音乐需要的初始化控件 ****************/
		/************ 上传视频需要的初始化控件 ****************/
		fl_upload_video = (FrameLayout) findViewById(R.id.fl_upload_video);
		vedio_iv_big_image = (ImageView) findViewById(R.id.vedio_iv_big_image);
		vedio_iv_start = (ImageView) findViewById(R.id.vedio_iv_start);
		/************ 上传视频需要的初始化控件 ****************/
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
		if (fl_upload_video != null) {
			fl_upload_video.setOnClickListener(this);
		}
		if (fl_upload_music != null) {
			fl_upload_music.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fl_upload_music:
			if (mWorkType == 4) {
				mApp.startActivityForResult(this, LocalMusicListActivity.class,
						null);
			} else if (mWorkType == 1) {
				getLocalFile();
			}
			break;
		case R.id.fl_upload_video:
			mApp.startActivityForResult(this, LocalVideoActivity.class, null);
			break;
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
				} else if (mWorks != null) {
					mWorks.setTitle(str_title);
					mWorks.setIntro(str_content);
					sendWorksToNet(mWorks);
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
		fl_progress.setVisibility(View.VISIBLE);
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, mUser.getOauth_token());
		params.put(Api.oauth_token_secret, mUser.getOauth_token_secret());
		params.put("gid", topic.getGid());
		params.put("title", topic.getTitle());
		params.put("content", topic.getContent());
		Log.i("param", params.toString());
		if (mPhotoList != null) {
			for (int i = 0; i < mPhotoList.size(); i++) {
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
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {

					}

					@Override
					public void onProgress(long bytesWritten, long totalSize) {
						super.onProgress(bytesWritten, totalSize);
						culcuteProgress(bytesWritten, totalSize);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						try {
							JSONObject jsonObject = new JSONObject(result);
							if (jsonObject.has("status")) {
								int status = jsonObject.getInt("status");
								if (status == 1) {
									ToastUtils.showToast("发表话题成功");
									onReturnResult(new Model());
									onBackPressed();
								} else {
									if (jsonObject.has("msg")) {
										ToastUtils.showToast(jsonObject
												.getString("msg"));
									} else {
										ToastUtils.showToast("发表话题失败！");
									}
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
		fl_progress.setVisibility(View.VISIBLE);
		if (vedio_iv_start != null) {
			vedio_iv_start.setVisibility(View.GONE);
		}
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, mUser.getOauth_token());
		params.put(Api.oauth_token_secret, mUser.getOauth_token_secret());
		params.put("id", work.getId());
		params.put("title", work.getTitle());
		params.put("intro", work.getIntro());
		params.put("explainType", String.valueOf(work.getExplainType()));
		Log.i("param", params.toString());
		// 上传图片
		if (mPhotoList != null) {
			for (int i = 0; i < mPhotoList.size(); i++) {
				try {
					File file = new File(mPhotoList.get(i));
					params.put("file" + i, file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/************ 上传视频 ***************/
		if (mVideo != null) {
			File file = new File(mVideo.getPath());
			try {
				params.put(mVideo.getTitle(), file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		/************ 上传视频 ***************/
		/************ 上传音乐 ***************/
		if (mMusic != null) {
			File file = new File(mMusic.getMusicpath());
			try {
				params.put("name", file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		/************ 上传音乐 ***************/
		/************ 上传doc pdf txt文件 ***************/
		if (mFile != null) {
			File file = new File(mFile.getFilepath());
			try {
				params.put(mFile.getFileName(), file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		/************ 上传doc pdf txt文件 ***************/

		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Event&act=uploadWork",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						String result = new String(arg2);
						Log.i("progress", "arg0=" + arg0 + ",  result="
								+ result);
					}

					@Override
					public void onProgress(long bytesWritten, long totalSize) {
						super.onProgress(bytesWritten, totalSize);
						culcuteProgress(bytesWritten, totalSize);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						Log.i("works", "result=" + result);
						try {
							JSONObject jsonObject = new JSONObject(result);
							if (jsonObject.has("status")) {
								int status = jsonObject.getInt("status");
								if (status == 1) {
									ToastUtils.showToast("上传作品成功");
									onReturnResult(new Model());
									onBackPressed();
								} else {
									if (jsonObject.has("msg")) {
										ToastUtils.showToast(jsonObject
												.getString("msg"));
									} else {
										ToastUtils.showToast("上传作品失败！");
									}
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

	private void culcuteProgress(long bytesWritten, long totalSize) {
		float progress = ((float) bytesWritten / (float) totalSize) * 100;
		DecimalFormat df = new DecimalFormat("0");// 格式化小数
		String s = df.format(progress);// 返回的是String类型
		tv_progress.setText(s + "%");
		if (s.equals("100")) {
			fl_progress.setVisibility(View.GONE);
		}
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
			if (mPhotoList != null) {
				for (String str : mPhotoList) {
					if (ll_ScrollView.getChildCount() > 6) {
						ToastUtils.showToast("最多只能选六张！");
						return;
					}
					addImageToHsv(str, PHOTO);
					// TODO 这里还需要把bitmap获取出来
				}
			}
			// video的获取
			mVideo = (LocalVideo) bundle.get(Config.LOCALVIDEO);
			if (mVideo != null) {
				Bitmap bitmap = getVideoThumbnail(mVideo.getPath(), 120, 120,
						Thumbnails.MINI_KIND);
				vedio_iv_big_image.setImageBitmap(bitmap);
			}
			mMusic = (LocalMusic) bundle.get(Config.LOCALMUSIC);
			if (mMusic != null) {
				tv_music_name.setText(mMusic.getName() + ".mp3");
			}
		}
		if (requestCode == FILE_CODE) {
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					String filepath = uri.getPath();
					mFile = filterThepath(filepath);
					if (mFile != null) {
						tv_music_name.setText(mFile.getFileName() + "");
					}
					ToastUtils.showToast(filepath);
				}
			}
		}
	}

	/**
	 * 判断是否选择文件正确
	 * 
	 * 目前只支持doc,txt,pdf;
	 * 
	 * @param filepath
	 * @return
	 */
	private LocalFile filterThepath(String filepath) {
		if (filepath != null) {
			if (filepath.contains("doc") || filepath.contains("txt")
					|| filepath.contains("pdf")) {
				int index = filepath.lastIndexOf("/");
				String fileName = filepath.substring(index + 1);
				LocalFile localFile = new LocalFile();
				localFile.setFileName(fileName);
				localFile.setFilepath(filepath);
				return localFile;
			}
			ToastUtils.showToast("请选择doc,txt,pdf文件");
			return null;
		}
		return null;
	}

	/**
	 * 获取视频缩略图
	 * 
	 * @param videoPath
	 * @param width
	 * @param height
	 * @param kind
	 * @return
	 */
	private Bitmap getVideoThumbnail(String videoPath, int width, int height,
			int kind) {
		Bitmap bitmap = null;
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
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
