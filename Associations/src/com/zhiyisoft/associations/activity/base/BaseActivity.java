package com.zhiyisoft.associations.activity.base;

/***********************************************************************
 * Module:  BaseActivity.java
 * Author: qcj qq:260964739
 * Purpose: Defines the Class BaseActivity
 ***********************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.Anim;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * activity的基类，任何activity都要继承它，并且实现里面的方法 一般不要轻易修改它
 * 
 * @pdOid
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	/**
	 * activity的总布局，加入mTitleLayout和mBodyLayout
	 * 
	 * 
	 */
	private RelativeLayout mLayout;
	/** title 容器 */
	private LinearLayout mTitlell;
	/** 内容容器 */
	public FrameLayout mContentll;
	/** 底部容器 */
	public LinearLayout mBottomll;
	/** bundle数据 */
	public Bundle mBundle;
	/** title左边的图片id */
	private int mTitleLeftImageId;
	/** title的右边id */
	private int mTitleRightImageId;
	/** title的布局 */
	private View mTitleLayout;
	/** 內容的布局 */
	private View mBodyLayout;
	/** 底部的布局 */
	private View mBottomLayout;

	public LayoutInflater mInflater;
	// 全局应用
	public Association mApp;

	// adapter的基类
	private BAdapter mAdapter;
	private BaseListView mListView;
	/** 常用的title布局的控件，注意 这只是常用的title哈，不常用的 就隐藏这个title，然后就直接画到这个该布局xml里面 */
	public TextView tv_title_left;
	public TextView tv_title;
	public TextView tv_title_right;
	public ImageView iv_title_left;
	public ImageView iv_title_right2;
	public ImageView iv_title_right1;
	public ImageView iv_title_right3;
	/**
	 * 方便子类替换content部分
	 */
	public FragmentManager mFManager = getSupportFragmentManager();

	/**
	 * 使用友盟来分享就是爽爽哒
	 */
	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mApp = (Association) getApplication();
		mInflater = LayoutInflater.from(getApplicationContext());
		mApp.setActivity(this);
		initTheCommonLayout();
		// 把内容和title结合
		setContentView(combineTheLayout());
		initIntent();
		initView();
		initListener();
		doRefreshNew();
		initShareContent();
	}

	/**
	 * 判断这个用户是否登录过
	 * 
	 * 如果个别fragement对用户开发的话，就重新这个方法，然后return true就可以了
	 * 
	 * @return
	 */
	public boolean checkTheUser() {
		ModelUser user = mApp.getUser();
		user.toString();
		if (user != null && user.getMobile() != null) {
			return true;
		}
		return false;
	}

	/** 初始化公共布局 */
	private void initTheCommonLayout() {
		mLayout = (RelativeLayout) mInflater.inflate(R.layout.comom_layout,
				null);
		mTitlell = (LinearLayout) mLayout.findViewById(R.id.ll_Title);
		mContentll = (FrameLayout) mLayout.findViewById(R.id.ll_content);
		mBottomll = (LinearLayout) mLayout.findViewById(R.id.ll_bottom);
	}

	/** 设置title的布局 */
	private void setTitleLayout() {
		// 当需改变的时候就imgeid时就从重新这个方法，这样可以增加扩张性
		mTitleLeftImageId = setTitleLeftImageId();
		mTitleRightImageId = setTitleRightImageId();
		String title = setCenterTitle();
		// if (title != null && title.length() > 0) {
		if (title != null) {
			mTitleLayout = mInflater.inflate(R.layout.title, null);
			tv_title_left = (TextView) mTitleLayout
					.findViewById(R.id.tv_title_left);
			tv_title = (TextView) mTitleLayout.findViewById(R.id.tv_title);
			tv_title_right = (TextView) mTitleLayout
					.findViewById(R.id.tv_title_right);
			iv_title_left = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_left);
			iv_title_right2 = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_right2);
			iv_title_right1 = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_right1);
			iv_title_right3 = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_right3);
			if (mTitleLeftImageId != 0) {
				iv_title_left.setImageResource(mTitleLeftImageId);
			}
			if (mTitleRightImageId != 0) {
				iv_title_left.setImageResource(mTitleRightImageId);
			}
			iv_title_left.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});
			tv_title.setText(title + "");
		} else {
			// 如果没有title的话 就设置为空
			mTitlell.setVisibility(View.GONE);
		}
	}

	// @Override
	// public void onWindowFocusChanged(boolean hasFocus) {
	// super.onWindowFocusChanged(hasFocus);
	// if (hasFocus) {
	// if (mAdapter != null) {
	// if (mAdapter.getList().size() > 0) {
	// mAdapter.mListView.autoRefresh();
	// } else {
	// mAdapter.doRefreshNew();
	// }
	// }
	// }
	// }
	/**
	 * 第一次无数据的时候刷新
	 */
	private void doRefreshNew() {
		if (mAdapter != null) {
			mAdapter.doRefreshNew();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Anim.exit(this);
	}

	/**
	 * 设置底部布局
	 */
	private void setBottomlayout() {
		int id = getBottomLayoutId();
		if (id != 0) {
			mBottomll.setVisibility(View.VISIBLE);
			mBodyLayout = mInflater.inflate(R.layout.bottom_layout, mBottomll);
			Log.i("bottom", "--------------------->mBodyLayout");
		}
	}

	/**
	 * 默认不实现底部布局，如果要实现的话就重新這個方法，增加其扩张性
	 * 
	 * @return 返回底部布局id
	 */
	public int getBottomLayoutId() {
		return 0;
	}

	/** 把内容和title全部加载到这个总布局里面 */
	public View combineTheLayout() {
		setTitleLayout();
		setBottomlayout();
		if (getLayoutId() > 0) {
			mBodyLayout = mInflater.inflate(getLayoutId(), null);
			mContentll.addView(mBodyLayout);
		}
		if (mTitleLayout != null)
			mTitlell.addView(mTitleLayout);
		if (mBottomLayout != null) {
			mBottomll.addView(mBottomLayout);
			// Log.i("bottom", "--------------------->");
		}
		return mLayout;
	}

	/** 設置中間的title */
	public abstract String setCenterTitle();

	/**
	 * 设置使用的title
	 * 
	 * @param leftTitle
	 *            左边的title 不设置的可以为空
	 * @param centerTitle
	 *            中的title 不设置的可以为空
	 * @param rightTitle
	 *            右边的title 不需要就设置为空
	 */
	public void setAlltitle(String leftTitle, String centerTitle,
			String rightTitle) {
		if (leftTitle != null) {
			tv_title_left.setText(leftTitle + "");
		}
		if (centerTitle != null && centerTitle.length() > 0) {
			tv_title.setText(centerTitle + "");
		}
		if (rightTitle != null) {
			tv_title_right.setText(rightTitle + "");
		}

	}

	/**
	 * 设置title的图标
	 * 
	 * @param leftResId
	 *            左边按钮的资源文件
	 * @param rightResId1
	 *            右边1按钮的资源文件
	 * @param rightResId
	 *            右边按钮的资源文件
	 * @param rightResid3
	 */
	public void setAllImagetitle(int leftResId, int rightResId1,
			int rightResId, int rightResid3) {
		if (leftResId != 0) {
			iv_title_left.setImageResource(leftResId);
			setViewVisable(iv_title_left);
		}
		if (rightResId1 != 0) {
			iv_title_right1.setImageResource(rightResId1);
			setViewVisable(iv_title_right1);
		}
		if (rightResId != 0) {
			iv_title_right2.setImageResource(rightResId);
			setViewVisable(iv_title_right2);
		}
		if (rightResid3 != 0) {
			iv_title_right3.setImageResource(rightResid3);
			setViewVisable(iv_title_right3);
		}

	}

	/**
	 * 设置控件是否显示
	 * 
	 * @param view
	 *            需要改变的控件
	 */
	public void setViewVisable(View view) {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 改变title
	 * 
	 * @param str
	 *            需要改变的title文字
	 */
	public void changeTheTitle(String str) {
		if (tv_title != null) {
			tv_title.setText(str + "");
		}
	}

	/** 設置title左邊的圖片的id 需要改变图片就在子类中重新这个方法 */
	public int setTitleLeftImageId() {
		return 0;
	}

	/** 设置title右边的的图片id 需要改变图片就在子类中重新这个方法 */
	public int setTitleRightImageId() {
		return 0;
	}

	/** 獲取intent传来的内容 */
	public abstract void initIntent();

	/** 获取内容布局id */
	public abstract int getLayoutId();

	/** 初始化各个控件 */
	public abstract void initView();

	/** 初始化设置各个控件的监听器 */
	public abstract void initListener();

	public void refreshHeader() {
		if (mAdapter != null) {
			Log.i("refresh", "if (mAdapter != null)");
			mAdapter.doRefreshHeader();
		}
	}

	/**
	 * @param adapter
	 *            需要设置的adapter
	 */
	public void setAdapter(BAdapter adapter) {
		this.mAdapter = adapter;
	}

	/**
	 * @param listView
	 *            需要设置的listView
	 */
	public void setListView(BaseListView listView) {
		this.mListView = listView;
	}

	/**
	 * 设置底部可见
	 */
	public void setBottomVisible() {
		mBottomll.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置底部不可见
	 */
	public void setBottomGone() {
		mBottomll.setVisibility(View.GONE);
	}

	// ----------------------------------调用本地的图片，摄像机，文件之类的操作------------------------------------------------------
	public static final int IMAGE_CODE = 1; // 取照片的时做的标记
	public static final int CAPTURE_CODE = 2; // 取照片的时做的标记
	public static final int GET_DATA_FROM_ACTIVITY = 2;

	/**
	 * 打开相册
	 */
	public void openTheGalley() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, IMAGE_CODE);
	}

	/**
	 * 打开照相机
	 */
	public void openTheCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAPTURE_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			ContentResolver resolver = getContentResolver();
			if (resultCode != RESULT_OK) {
				return;
			} else if (requestCode == IMAGE_CODE) {
				Uri originalUri = data.getData();
				ToastUtils.showToast(originalUri.toString() + "");
				if (originalUri != null) {
					Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					compressPhotoAndDisplay(bitmap);
				}
			} else if (requestCode == CAPTURE_CODE) {
				String sdStatus = Environment.getExternalStorageState();
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
					Log.i("TestFile",
							"SD card is not avaiable/writeable right now.");
					return;
				}
				String name = new DateFormat().format("yyyyMMdd_hhmmss",
						Calendar.getInstance(Locale.CHINA)) + ".jpg";

				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

				FileOutputStream b = null;
				// ???????????????????????????????为什么不能直接保存在系统相册位置呢？？？？？？？？？？？？
				File file = new File("/sdcard/myImage/");
				file.mkdirs();// 创建文件夹
				String fileName = "/sdcard/myImage/" + name;

				try {
					b = new FileOutputStream(fileName);
					compressOutStream2Bitmap(bitmap, b);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						b.flush();
						b.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			Log.i("tag", e.toString() + "");
			ToastUtils.showToast("获取图片抛出了异常！！");
		}
	}

	/**
	 * 设置返回的结果
	 */
	public void onReturnResult(Model model) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putSerializable(Config.GET_ACTIVITY_DATA, model);
		intent.putExtras(bundle);
		this.setResult(this.GET_DATA_FROM_ACTIVITY, intent);
		// Intent intent = new Intent();
		// Bundle bundle = new Bundle();
		// bundle.putStringArrayList(Config.GET_ACTIVITY_DATA,
		// selectedDataList);
		// intent.putExtras(bundle
	}

	/**
	 * 设置返回的结果
	 */
	public void onReturnResult(ArrayList<String> list) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putStringArrayList(Config.GET_ACTIVITY_DATA, list);
		intent.putExtras(bundle);
		this.setResult(this.GET_DATA_FROM_ACTIVITY, intent);
	}

	/**
	 * 获取文件
	 * 
	 * @param path
	 * @return
	 */
	public File getFile(String path) {
		if (path != null) {
			return new File(path);
		}
		return null;
	}

	/**
	 * 同比例压缩图片，并且显示图片如果需要显示这个图片的话就 显示就交给子类重新这个方法，并实现
	 * 
	 * @param photo
	 */
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		// TODO 统统同比例压缩一倍， 这压缩太粗糙， 留在迭代开发做，现在如果做了，迭代开发干什么？
		float scale = 0.5f;
		if (scale <= 0)
			scale = 1;
		int width = originBitmap.getWidth();
		int heigth = originBitmap.getHeight();
		originBitmap = Bitmap.createScaledBitmap(originBitmap,
				(int) (scale * width), (int) (scale * heigth), true);
		return originBitmap;
	}

	public Bitmap compressOutStream2Bitmap(Bitmap bitmap, OutputStream stream) {
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		return bitmap;
	}

	// ----------------------------------我是本区域邪恶的分界线------------------------------------------------------

	// ------------------------------------友盟初始化qq，微信，微博，人人等-----------------------
	/**
	 * 初始化需要分享的内容
	 */
	private void initShareContent() {
		// // 设置分享内容
		// mController
		// .setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
		// 设置分享内容
		mController.setShareContent("快点来看哥哥做的app，大学生在线社团，就是这么屌！");
		// 设置分享图片, 参数2为图片的url地址
		mController
				.setShareMedia(new UMImage(
						this,
						"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E8%90%8C%E5%9B%BE%E7%89%87&step_word=&pn=3&spn=0&di=182312698480&pi=&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3883907600%2C1209210391&os=1389723797%2C702479891&adpicid=0&ln=1000&fr=&fmq=1440664042957_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&objurl=http%3A%2F%2F5.66825.com%2Fdownload%2Fpic%2F000%2F328%2F2d9c7a0a343c880e632ac1c4db0339af.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fowr_z%26e3Bncnn_z%26e3Bv54AzdH3FrtvAzdH3Fndbc8d_z%26e3Brir%3F45ktsj%3D8d00d&gsm=0"));
		initQQShare();
		initQQZoneShare();
		initWeiXinShare();
		// 设置分享面板上显示的平台
		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
				SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
				SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT, SHARE_MEDIA.RENREN);
	}

	/**
	 * 初始化qq分享的内容
	 */
	private void initQQShare() {
		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "1104831952",
				"ioLElezMMAJ94NHm");
		qqSsoHandler.addToSocialSDK();
	}

	/**
	 * 初始化qq空间分享的内容
	 */
	private void initQQZoneShare() {
		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this,
				"1104831952", "ioLElezMMAJ94NHm");
		qZoneSsoHandler.addToSocialSDK();
	}

	/**
	 * 初始化微信分享
	 */
	private void initWeiXinShare() {
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appID = "wxf3ff5a169747d2f5";
		String appSecret = "14eeda3b22f601ae91bfd34b8d65574d";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, appID, appSecret);
		wxHandler.addToSocialSDK();
		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this, appID, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * 执行分享
	 */
	public void preformShare() {
		mController.openShare(this, false);
	}

	// ------------------------------------友盟初始化qq微信，微博，人人end------------------z
	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private TextView btn_openTheCamera;
	private TextView btn_openTheGallery;
	private TextView btn_cancle;

	/**
	 * 初始化popWindow
	 * */
	public void initCameraPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.upload_icon_item, null);
			mPopupWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			mPopupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					setWindowAlpha(1.0f);

				}
			});
			// 设置popwindow出现和消失动画
			initPopWidge(popView);
			setPopListener();
		}
	}

	/**
	 * 设置屏幕的透明度
	 * 
	 * @param alpha
	 *            需要设置透明度
	 */
	private void setWindowAlpha(float alpha) {
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.alpha = alpha;
		getWindow().setAttributes(params);
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		PopWindowItemListener listener = new PopWindowItemListener();
		btn_openTheCamera.setOnClickListener(listener);
		btn_openTheGallery.setOnClickListener(listener);
		btn_cancle.setOnClickListener(listener);
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		btn_openTheCamera = (TextView) popView
				.findViewById(R.id.btn_openTheCamera);
		btn_openTheGallery = (TextView) popView
				.findViewById(R.id.btn_openTheGallery);
		btn_cancle = (TextView) popView.findViewById(R.id.btn_cancle);

	}

	private class PopWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_openTheCamera:
				openTheCamera();
				mPopupWindow.dismiss();
				break;

			case R.id.btn_openTheGallery:
				mPopupWindow.dismiss();
				openTheGalley();
				break;
			case R.id.btn_cancle:
				mPopupWindow.dismiss();
				break;

			}
		}

	}

	/**
	 * 显示popWindow
	 * */
	@SuppressLint("NewApi")
	public void showCameraPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
		mPopupWindow.update();
		setWindowAlpha(0.7f);
	}
}