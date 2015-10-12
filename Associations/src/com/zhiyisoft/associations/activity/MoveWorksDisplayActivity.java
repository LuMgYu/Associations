package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MoveWorksAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.MoveWorksListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveWorksDisplayActivity extends BaseActivity {
	private BaseListView works_display_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelEvent mEvent;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "上传作品");
	}

	@Override
	public String setCenterTitle() {
		return "作品展示";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mEvent = (ModelEvent) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_works_display;
	}

	@Override
	public void initView() {
		works_display_lv = (MoveWorksListview) findViewById(R.id.works_display_lv);
		mAdapter = new MoveWorksAdapter(this, mlist, mEvent);
		works_display_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_right:
			initPopWindow();
			showPop(tv_title_right, 0, 10);
			break;
		// --------------------------PopupWindow的界面控件监听器------------------
		case R.id.ll_essay:
			mPopupWindow.dismiss();
			Bundle data = bindDataToModel(1);
			mApp.startActivityForResult(this, AssociationSendTopicActivity.class, data);
			break;
		case R.id.ll_pic:
			mPopupWindow.dismiss();
			Bundle picdata = bindDataToModel(2);
			mApp.startActivityForResult(this, AssociationSendTopicActivity.class,
					picdata);
			break;
		case R.id.ll_music:
			mPopupWindow.dismiss();
			Bundle micdata = bindDataToModel(4);
			mApp.startActivityForResult(this, AssociationSendTopicActivity.class,
					micdata);
			break;
		case R.id.ll_vedio:
			mPopupWindow.dismiss();
			Bundle veddata = bindDataToModel(3);
			mApp.startActivityForResult(this, AssociationSendTopicActivity.class,
					veddata);
			break;
		}

	}

	private Bundle bindDataToModel(int type) {
		Bundle data = new Bundle();
		ModelEventWorks works = new ModelEventWorks();
		if (mEvent != null) {
			works.setId(mEvent.getId());
			works.setExplainType(type);
			Log.i("works", works.toString() + "");
		}
		data.putSerializable(Config.SEND_ACTIVITY_DATA, works);
		return data;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data != null) {
				Bundle bundle = data.getExtras();
				Object object=bundle.get(Config.GET_ACTIVITY_DATA);
				if(object!=null){
					mAdapter.doRefreshHeader();
				}
			}
		}
	}

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private LinearLayout ll_essay;
	private LinearLayout ll_pic;
	private LinearLayout ll_music;
	private LinearLayout ll_vedio;

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
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.move_works_send_style,
					null);
			mPopupWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
					200);// 设置需要显示宽度
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(
					R.color.main_gray_color));
			mPopupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					mPopupWindow = null;
					setWindowAlpha(1);
				}
			});
			setWindowAlpha(0.7f);
			// 设置popwindow出现和消失动画
			initPopWidge(popView);
			setPopListener();
		}
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		ll_essay = (LinearLayout) popView.findViewById(R.id.ll_essay);
		ll_pic = (LinearLayout) popView.findViewById(R.id.ll_pic);
		ll_music = (LinearLayout) popView.findViewById(R.id.ll_music);
		ll_vedio = (LinearLayout) popView.findViewById(R.id.ll_vedio);
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		ll_essay.setOnClickListener(this);
		ll_pic.setOnClickListener(this);
		ll_music.setOnClickListener(this);
		ll_vedio.setOnClickListener(this);
	}

	/**
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAsDropDown(parent, x, y);
		// mPopupWindow.showAtLocation(parent, gravity, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
	}

}
