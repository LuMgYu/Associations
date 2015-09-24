package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationMainNewAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationSingleActivity extends BaseActivity {
	private BaseListView single_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	// ------------PopupWindow里面的控件--------------------------
	private PopupWindow mPopupWindow;
	private RoundImageView association_icon;
	private LinearLayout association_ll_data;
	private TextView mexmber;
	private TextView album;
	private TextView file;
	private TextView share;
	private TextView vedio;
	private Button btn_quit;

	private static final int SUCCESS_QUIT = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS_QUIT:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					ToastUtils.showToast("您已成功退出了社团！");
					mPopupWindow.dismiss();
					onBackPressed();
				} else {
					ToastUtils.showToast("退出了社团失败");
				}
				break;
			}

		}

	};
	private ModelLeague mLeague; // 从activity传过来的社团核心资料

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("北京社团轮滑社", null, null);
		setAllImagetitle(0, R.drawable.write, R.drawable.three_, 0);
	}

	@Override
	public String setCenterTitle() {
		return "";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_single;
	}

	@Override
	public void initView() {
		single_lv = (BaseListView) findViewById(R.id.single_lv);
		single_lv.setPullRefreshEnable(false);
		mAdapter = new AssociationMainNewAdapter(this, mlist, mLeague);
		single_lv.setAdapter(mAdapter);
		initPopWindow();
	}

	@Override
	public void initListener() {
		iv_title_right1.setOnClickListener(this);
		iv_title_right2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_title_right1:
			Bundle topicdata = new Bundle();
			topicdata.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationSendTopicActivity.class,
					topicdata);
			break;
		case R.id.iv_title_right2:
			showPop(iv_title_right2, 0, 0);
			break;
		// ------------popwindow------------------------------------------
		case R.id.association_icon:
			mApp.startActivity(this, MeSettingNickActivity.class, null);
			break;
		case R.id.association_ll_data:
			mApp.startActivity(this, AssociationInformationActivity.class, null);
			break;
		case R.id.mexmber:
			Bundle memberdata = new Bundle();
			memberdata.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationMemberActivity.class,
					memberdata);
			break;
		case R.id.album:
			Bundle albumdata = new Bundle();
			albumdata.putSerializable(Config.SEND_ACTIVITY_DATA, mLeague);
			mApp.startActivity(this, AssociationAlbumActivity.class, albumdata);
			break;
		case R.id.file:
			mApp.startActivity(this, AssociationWordActivity.class, null);
			break;
		case R.id.share:
			// mApp.startActivity(this, AssociationWordActivity.class, null);
			preformShare();
			break;
		case R.id.vedio:
			mApp.startActivity(this, AssociationVedioDisplayActivity.class,
					null);
			break;
		case R.id.btn_quit:
			Toast.makeText(this, "点击了退出哦", Toast.LENGTH_SHORT).show();
			applyQuitAssociation(mLeague);
			break;
		}

	}

	/**
	 * 申请退出社团
	 * 
	 * @param league
	 */
	private void applyQuitAssociation(final ModelLeague league) {
		final LeagueImpl leagueImpl = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				boolean isSuccess = leagueImpl.leave(league);
				Message message = Message.obtain();
				message.what = SUCCESS_QUIT;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
	}

	// ----------------------------------------------------------------------------
	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		View popView = mInflater.inflate(R.layout.association_slidemenu, null);
		mPopupWindow = new PopupWindow(popView,
				UIUtils.getWindowWidth(getApplicationContext()) / 2,
				LayoutParams.MATCH_PARENT);
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

	/**
	 * 设置popWindow监听器
	 */
	private void setPopListener() {
		association_icon.setOnClickListener(this);
		association_ll_data.setOnClickListener(this);
		mexmber.setOnClickListener(this);
		album.setOnClickListener(this);
		file.setOnClickListener(this);
		share.setOnClickListener(this);
		vedio.setOnClickListener(this);
		btn_quit.setOnClickListener(this);
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */
	private void initPopWidge(View popView) {
		association_icon = (RoundImageView) popView
				.findViewById(R.id.association_icon);
		association_ll_data = (LinearLayout) popView
				.findViewById(R.id.association_ll_data);
		mexmber = (TextView) popView.findViewById(R.id.mexmber);
		album = (TextView) popView.findViewById(R.id.album);
		file = (TextView) popView.findViewById(R.id.file);
		share = (TextView) popView.findViewById(R.id.share);
		vedio = (TextView) popView.findViewById(R.id.vedio);
		btn_quit = (Button) popView.findViewById(R.id.btn_quit);
	}

	/**
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAtLocation(parent, Gravity.RIGHT, x, y);
		setWindowAlpha(0.7f);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
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
}
