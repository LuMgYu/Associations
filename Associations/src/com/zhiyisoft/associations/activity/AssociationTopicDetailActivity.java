package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MyPhotoGridViewAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.Comment;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelLeagueTopicPhoto;
import com.zhiyisoft.associations.model.ModelLeagueTopicReply;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;
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

	private Model mModel; // 当前上一个actitivy传过来的信息，更加不同的需求判断解析成相应的子类
	private ModelLeagueTopic mModelTopic;
	/************ 回复的内容时两个重要参数 ************/
	private String replayContent; // 回复的内容
	private String manId; // 二级回帖的时候 这个标示
	/************ api标示 ************/
	private static final int TOPICVIEW = 1;
	private static final int REPLYTOPIC = 2;
	private static final int REPLYPOST = 3;
	private static final int GETTOPICPOSTS = 4;
	@SuppressWarnings("unused")
	private Handler mHandle = new Handler() {
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case TOPICVIEW:
				ModelLeagueTopic topic = (ModelLeagueTopic) msg.obj;
				if (topic != null) {
					ToastUtils.showToast("获取详情成功");
					List<Model> photos = topic.getAttachs();
					List<String> photoUrls = new ArrayList<String>();
					if (photos != null) {
						for (int i = 0; i < photos.size(); i++) {
							ModelLeagueTopicPhoto photo = (ModelLeagueTopicPhoto) photos
									.get(i);
							photoUrls.add(photo.getUrl());
						}
					}
					bindDataToView(topic.getTitle(), topic.getFaceurl(),
							topic.getUname(), topic.getCtime(),
							topic.getContent(), photoUrls, null, null, null);
				} else {
					ToastUtils.showToast("获取详情失败");
				}
				break;
			case REPLYTOPIC:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					ToastUtils.showToast("评论成功");
					getTopicPosts(mModelTopic);
				} else {
					ToastUtils.showToast("评论成功");
				}
				break;
			case REPLYPOST:
				// ModelUser user = (ModelUser) msg.obj;
				// if (user != null) {
				// ToastUtils.showToast("完善资料成功");
				// onBackPressed();
				// } else {
				// ToastUtils.showToast("完善资料失败");
				// }
				break;
			case GETTOPICPOSTS:
				List<ModelLeagueTopicReply> replays = (List<ModelLeagueTopicReply>) msg.obj;
				if (replays != null) {
					initReplayView(replays);
					ToastUtils.showToast("获取评论成功");
				} else {
					ToastUtils.showToast("获取评论失败");
				}
				break;
			}

		};

	};

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
			mModel = (Model) bundle.get(Config.SEND_ACTIVITY_DATA);
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
		handTheModel(mModel);
		// initContentViewData(mCurrentFlag);
		// initReplayView();
	}

	/**
	 * 初始化 评论的条数
	 */
	public void initReplayView(List<ModelLeagueTopicReply> list) {
		// TODO 这里只是显示数据而已，并没有什么卵用
		if (list != null) {
			// replay_other_ll.removeAllViews();
			for (int i = 0; i < list.size(); i++) {
				int otherReplay = 2;
				View view = mInflater
						.inflate(R.layout.detail_replay_item, null);
				// TODO 这里以后需要初始化控件，然后把数据添加上去
				RoundImageView item_user_icon = (RoundImageView) view
						.findViewById(R.id.item_user_icon);
				TextView item_user_tv = (TextView) view
						.findViewById(R.id.item_user_tv);
				TextView item_user_tv_a = (TextView) view
						.findViewById(R.id.item_user_tv_a);
				TextView item_user_tv_date = (TextView) view
						.findViewById(R.id.item_user_tv_date);
				Button replay_btn = (Button) view.findViewById(R.id.replay_btn);
				TextView replay_content_tv = (TextView) view
						.findViewById(R.id.replay_content_tv);
				TextView other_more = (TextView) view
						.findViewById(R.id.other_more);
				/********************** 添加回复的内容 ***********************************/
				ModelLeagueTopicReply reply = list.get(i);
				item_user_icon.setImageUrl(reply.getFaceurl());
				item_user_tv.setText(reply.getUname());
				// item_user_tv_a.sette
				item_user_tv_date.setText(reply.getCtime());
				replay_content_tv.setText(reply.getContent());
				// other_more

				/************************* 添加回复的内容end ********************************/
				LinearLayout replay_other_ll = (LinearLayout) view
						.findViewById(R.id.replay_other_ll);
				addOtherReplayLayout(replay_other_ll, reply.getCommentlist());
				// -----------------------------------------------------
				content_ll_main.addView(view);
				replay_btn.setTag(reply);
				replay_btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						ModelLeagueTopicReply data = (ModelLeagueTopicReply) v
								.getTag();
						manId = data.getPid();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.toggleSoftInput(0,
								InputMethodManager.HIDE_NOT_ALWAYS);
						imm.showSoftInput(fill_content,
								InputMethodManager.HIDE_NOT_ALWAYS);
					}
				});
			}
		}
	}

	/**
	 * @param parent
	 *            父布局
	 * @param otherReplay
	 *            其它回复的数量
	 */
	private void addOtherReplayLayout(LinearLayout parent, List<Model> list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Comment comment = (Comment) list.get(i);
				View view = mInflater.inflate(
						R.layout.detail_other_replay_item, null);
				// TODO 以后这些数据就是从网上获取
				String username = comment.getMaskName() + ":";
				String content = comment.getContent() + "";
				String time = "  20小时前";
				TextView textView = (TextView) view
						.findViewById(R.id.other_tv_content);
				SpannableString ssName = new SpannableString(username);
				ForegroundColorSpan colorSpan = new ForegroundColorSpan(
						0xff2f7dff);
				ssName.setSpan(colorSpan, 0, ssName.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				textView.setText(ssName);
				textView.append(content);
				SpannableString ssTime = new SpannableString(time);
				AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(10, true);
				ssTime.setSpan(sizeSpan, 0, ssTime.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				ForegroundColorSpan timeSpan = new ForegroundColorSpan(
						0xff999999);
				ssTime.setSpan(timeSpan, 0, ssTime.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				textView.append(ssTime);
				parent.addView(view);
			}
		}
	}

	// /**
	// * 这里先要判断content_ll_main需要加载什么内容
	// *
	// * eg： 图片（一张图，或者多图），视频，文字，音乐
	// */
	// /**
	// *
	// */
	// public void initContentViewData(int flag) {
	// switch (flag) {
	// case FLAG_SINGLEPHOTO:
	// initSinglePhoto();
	// break;
	// case FLAG_MANYPHOTO:
	// initManyPhoto();
	// break;
	// case FLAG_FILE:
	// initDownledFile();
	// break;
	// case FLAG_VIDEO:
	// initVedio();
	// break;
	// case FLAG_MUSIC:
	// initMusic();
	// break;
	// case FLAG_ESSAY:
	// content_ll_main.setVisibility(View.GONE);
	// break;
	// }
	// }

	/******************************* 判断需要加载的布局 **************************************/
	/**
	 * 初始化单个照片的布局
	 */
	private void initSinglePhoto(String photoUrl) {
		SmartImageView imageView = new SmartImageView(this);
		LinearLayout.LayoutParams params = new LayoutParams(
				UIUtils.getWindowWidth(mApp), 300);
		params.setMargins(10, 0, 10, 0);
		imageView.setLayoutParams(params);
		imageView.setImageUrl(photoUrl);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		content_ll_main.addView(imageView);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mApp.startActivity(AssociationTopicDetailActivity.this,
						AssociationTopicDetailPhotoActivity.class, null);
			}
		});
	}

	/**
	 * 初始化多个照片的布局
	 */
	private void initManyPhoto(List<String> photourls) {
		mNeedView = addViewToContent(content_ll_main,
				R.layout.detail_many_photo_item);
		MyGridView gridView = (MyGridView) mNeedView
				.findViewById(R.id.detail_gv_many_photo);
		MyPhotoGridViewAdapter adapter = new MyPhotoGridViewAdapter(this,
				photourls);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mApp.startActivity(AssociationTopicDetailActivity.this,
						AssociationTopicDetailPhotoActivity.class, null);
			}

		});

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

	/******************************* 判断需要加载的布局 end **************************************/
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
		btn_return.setOnClickListener(this);
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
		case R.id.btn_return:
			if (mModelTopic != null) {
				String content = fill_content.getText().toString();
				mModelTopic.setReplyContent(content);
				if (manId == null || manId.length() < 1) {
					replyTopic(mModelTopic);
				} else {
					mModelTopic.setPid(manId);
					replyPost(mModelTopic);

				}
			}
			break;
		}

	}

	/**
	 * 更加activity传过来的model来解析成相应的model子类，然后判断需要调用的 接口
	 * 
	 * @param model
	 */
	public void handTheModel(Model model) {
		if (model instanceof ModelLeagueTopic) {
			// 传入的是话题model
			mModelTopic = (ModelLeagueTopic) model;
			topicView(mModelTopic);
			getTopicPosts(mModelTopic);
		}
	}

	private void bindDataToView(String title, String faceUrl, String username,
			String date, String content, List<String> photoUrls, Model file,
			Model music, Model vedio) {
		if (title != null) {
			content_tv_title.setText(title);
		}
		if (faceUrl != null) {
			user_icon.setImageUrl(faceUrl);
		}
		if (username != null) {
			content_tv_user.setText(username + "发表于");
		}
		if (date != null) {
			content_tv_date.setText(date);
		}
		if (content != null) {
			content_tv_content.setText(content);
		}
		if (photoUrls != null) {
			if (photoUrls.size() == 1) {
				initSinglePhoto(photoUrls.get(0));
			} else {
				initManyPhoto(photoUrls);
			}

		}
		if (file != null) {

		}
		if (music != null) {

		}
		if (vedio != null) {

		}

	}

	/********************************* 下面是根据某些要求调用相关的接口 *********************************************/
	/**
	 * 获取话题详情
	 * 
	 * @param topic
	 */
	private void topicView(final ModelLeagueTopic topic) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = leagueImpl.topicView(topic);
				Message message = Message.obtain();
				message.what = TOPICVIEW;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 话题回帖
	 * 
	 * @param topic
	 */
	private void replyTopic(final ModelLeagueTopic topic) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				boolean isSuccess = leagueImpl.replyTopic(topic);
				Message message = Message.obtain();
				message.what = REPLYTOPIC;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 28.【回复帖子】：Group/replyPost
	 * 
	 * @param topic
	 */
	private void replyPost(final ModelLeagueTopic topic) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				boolean isSuccess = leagueImpl.replyPost(topic);
				Message message = Message.obtain();
				message.what = REPLYPOST;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
	}

	/**
	 * 【获取某个话题下的帖子】：
	 * 
	 * @param topic
	 */
	private void getTopicPosts(final ModelLeagueTopic topic) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				List<Model> list = leagueImpl.getTopicPosts(topic);
				Message message = Message.obtain();
				message.what = GETTOPICPOSTS;
				message.obj = list;
				mHandle.sendMessage(message);
			}
		});
	}

}
