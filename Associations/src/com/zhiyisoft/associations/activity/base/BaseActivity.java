package com.zhiyisoft.associations.activity.base;

/***********************************************************************
 * Module:  BaseActivity.java
 * Author: qcj qq:260964739
 * Purpose: Defines the Class BaseActivity
 ***********************************************************************/

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.LoginActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelUser;
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

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mApp = (Association) getApplication();
		mInflater = LayoutInflater.from(getApplicationContext());
		mApp.setActivity(this);
		if (checkTheUser()) {
			initTheCommonLayout();
			// 把内容和title结合
			setContentView(combineTheLayout());
			initIntent();
			initView();
			initListener();
			doRefreshNew();
		} else {
			mApp.startActivity(this, LoginActivity.class, null);
		}
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
			setViewVisable(mTitlell);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			ContentResolver resolver = getContentResolver();
			if (resultCode != RESULT_OK) {
				return;
			} else if (requestCode == IMAGE_CODE) {
				Uri originalUri = data.getData();
				if (originalUri != null) {
					Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					compressPhotoAndDisplay(bitmap);
				}
			}
			// else if (requestCode == CAPTURE_CODE && resultCode == RESULT_OK)
			// {
			// Bundle bundle = data.getExtras();
			// if (bundle != null) {
			// mBitmap = (Bitmap) bundle.get("data");
			// association_icon.setImageBitmap(mBitmap);
			// }
		} catch (Exception e) {
			Log.i("tag", e.toString() + "");
			ToastUtils.showToast("获取图片抛出了异常！！");
		}
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

	/**
	 * 打开相册
	 */
	public void openTheGalley() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, IMAGE_CODE);
	}
	// ----------------------------------我是本区域邪恶的分界线------------------------------------------------------
}