package com.zhiyisoft.associations.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.widget.wheelview.ArrayWheelAdapter;
import com.zhiyisoft.associations.widget.wheelview.WheelView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveCreateActivity extends BaseActivity {
	private FrameLayout fl_icon;
	private ImageView move_icon;
	private EditText move_et_name;
	private RelativeLayout move_rl_welfare;
	private RelativeLayout move_rl_main;
	private TextView move_tv_start_time;
	private RelativeLayout move_rl_end;
	private TextView move_tv_start_end;
	private ImageView move_iv_yes;
	private ImageView move_iv_no;
	private RelativeLayout move_rl_enter;
	private TextView move_tv_enter_time;
	private RelativeLayout move_rl_enter_end;
	private TextView move_tv_enter_end_time;
	private ImageView move_iv_vetify_work_yes;
	private TextView move_tv_commmit_work_Start_time;
	private TextView move_tv_workr_end_time;
	private ImageView move_iv_vetify_no;
	private ImageView move_iv_title_yes;
	private TextView move_tv_photo;
	private ImageView move_iv_photo_yes;
	private ImageView move_iv_music_yes;
	private RelativeLayout move_rl_scope;
	private RelativeLayout move_rl_work_end;
	private RelativeLayout move_rl_commmit_work;
	// 新添加的的控件
	private ImageView move_iv_vetify_work_no;
	private ImageView move_iv_commit_yes_master;
	private ImageView move_iv_commit_all;
	private ImageView move_iv_vetify_yes;
	private ImageView move_iv_vedio_yes;

	public String[] mYear = new String[40];
	public String[] mMonth = new String[12];
	public String[] mDay = new String[31];
	// 定义六个布尔类型，分别从上到下表示这个六个时间选择器
	private boolean[] mFlag = new boolean[] { false, false, false, false,
			false, false };
	private TextView[] mTvViews;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "提交");
	}

	@Override
	public String setCenterTitle() {
		return "创建线上活动";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_create;
	}

	@Override
	public void initView() {
		fl_icon = (FrameLayout) findViewById(R.id.fl_icon);
		move_icon = (ImageView) findViewById(R.id.move_icon);
		move_et_name = (EditText) findViewById(R.id.association_tv_school_name);
		move_rl_welfare = (RelativeLayout) findViewById(R.id.move_rl_welfare);
		move_rl_main = (RelativeLayout) findViewById(R.id.move_rl_main);
		move_tv_start_time = (TextView) findViewById(R.id.move_tv_start_time);
		move_rl_end = (RelativeLayout) findViewById(R.id.move_rl_end);
		move_tv_start_end = (TextView) findViewById(R.id.move_tv_start_end);
		move_iv_yes = (ImageView) findViewById(R.id.move_iv_yes);
		move_iv_no = (ImageView) findViewById(R.id.move_iv_no);
		move_rl_enter = (RelativeLayout) findViewById(R.id.move_rl_enter);
		move_tv_enter_end_time = (TextView) findViewById(R.id.move_tv_enter_end_time);
		move_iv_vetify_work_yes = (ImageView) findViewById(R.id.move_iv_vetify_work_yes);
		move_iv_vetify_no = (ImageView) findViewById(R.id.move_iv_vetify_no);
		move_iv_title_yes = (ImageView) findViewById(R.id.move_iv_title_yes);
		move_iv_photo_yes = (ImageView) findViewById(R.id.move_iv_photo_yes);
		move_tv_photo = (TextView) findViewById(R.id.move_tv_photo);
		move_iv_music_yes = (ImageView) findViewById(R.id.move_iv_music_yes);
		move_rl_scope = (RelativeLayout) findViewById(R.id.move_rl_scope);
		move_tv_commmit_work_Start_time = (TextView) findViewById(R.id.move_tv_commmit_work_Start_time);
		move_tv_workr_end_time = (TextView) findViewById(R.id.move_tv_workr_end_time);
		move_tv_enter_time = (TextView) findViewById(R.id.move_tv_enter_time);
		move_rl_work_end = (RelativeLayout) findViewById(R.id.move_rl_work_end);
		move_rl_enter_end = (RelativeLayout) findViewById(R.id.move_rl_enter_end);
		move_rl_commmit_work = (RelativeLayout) findViewById(R.id.move_rl_commmit_work);
		// 新增控件
		move_iv_vetify_work_no = (ImageView) findViewById(R.id.move_iv_vetify_work_no);
		move_iv_commit_yes_master = (ImageView) findViewById(R.id.move_iv_commit_yes_master);
		move_iv_commit_all = (ImageView) findViewById(R.id.move_iv_commit_all);
		move_iv_vetify_yes = (ImageView) findViewById(R.id.move_iv_vetify_yes);
		move_iv_vedio_yes = (ImageView) findViewById(R.id.move_iv_vedio_yes);

		mTvViews = new TextView[] { move_tv_start_time, move_tv_start_end,
				move_tv_enter_time, move_tv_enter_end_time,
				move_tv_commmit_work_Start_time, move_tv_workr_end_time };
		genateYearMouthDay();
		initPopWindow();
	}

	@Override
	public void initListener() {
		move_rl_main.setOnClickListener(this);
		move_rl_end.setOnClickListener(this);
		move_rl_enter.setOnClickListener(this);
		move_rl_enter_end.setOnClickListener(this);
		move_rl_work_end.setOnClickListener(this);
		move_rl_commmit_work.setOnClickListener(this);
		// -------------------------
		move_icon.setOnClickListener(this);
		move_rl_welfare.setOnClickListener(this);
		move_iv_yes.setOnClickListener(this);
		move_iv_no.setOnClickListener(this);
		move_iv_vetify_work_yes.setOnClickListener(this);
		move_iv_vetify_work_no.setOnClickListener(this);
		move_iv_commit_yes_master.setOnClickListener(this);
		move_iv_commit_all.setOnClickListener(this);
		move_iv_vetify_yes.setOnClickListener(this);
		move_iv_vetify_no.setOnClickListener(this);
		move_iv_title_yes.setOnClickListener(this);
		move_iv_photo_yes.setOnClickListener(this);
		move_iv_vedio_yes.setOnClickListener(this);
		move_iv_music_yes.setOnClickListener(this);
		move_rl_scope.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ----------------------------------时间选择部分------------------------------------------
		case R.id.move_rl_main:
			showPop(move_rl_welfare, 0, 0);
			mFlag[0] = true;
			break;
		case R.id.move_rl_end:
			showPop(move_rl_welfare, 0, 0);
			mFlag[1] = true;
			break;
		case R.id.move_rl_enter:
			showPop(move_rl_welfare, 0, 0);
			mFlag[2] = true;
			break;
		case R.id.move_rl_enter_end:
			showPop(move_rl_welfare, 0, 0);
			mFlag[3] = true;
			break;
		case R.id.move_rl_commmit_work:
			showPop(move_rl_welfare, 0, 0);
			mFlag[4] = true;
			break;
		case R.id.move_rl_work_end:
			showPop(move_rl_welfare, 0, 0);
			mFlag[5] = true;
			break;
		// ----------------------------------------------------------------------------
		case R.id.move_icon:
			break;
		case R.id.move_rl_welfare:
			break;
		// 活动时间
		case R.id.move_iv_yes:
			resetmove_iv_yes();
			move_iv_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_no:
			resetmove_iv_yes();
			move_iv_no.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_work_yes:
			resetmove_iv_vetify_work_yes();
			move_iv_vetify_work_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_work_no:
			resetmove_iv_vetify_work_yes();
			move_iv_vetify_work_no.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_commit_yes_master:
			resetmove_iv_commit_yes_master();
			move_iv_commit_yes_master.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_commit_all:
			resetmove_iv_commit_yes_master();
			move_iv_commit_all.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_yes:
			resetmove_iv_vetify_yes();
			move_iv_vetify_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_no:
			resetmove_iv_vetify_yes();
			move_iv_vetify_no.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_title_yes:
			break;
		case R.id.move_iv_photo_yes:
			break;
		case R.id.move_iv_vedio_yes:
			break;
		case R.id.move_iv_music_yes:
			break;
		case R.id.move_rl_scope:
			break;
		}

	}

	private void resetmove_iv_vetify_yes() {
		move_iv_vetify_yes.setImageResource(R.drawable.no);
		move_iv_vetify_no.setImageResource(R.drawable.no);

	}

	private void resetmove_iv_commit_yes_master() {
		move_iv_commit_yes_master.setImageResource(R.drawable.no);
		move_iv_commit_all.setImageResource(R.drawable.no);

	}

	private void resetmove_iv_vetify_work_yes() {
		move_iv_vetify_work_yes.setImageResource(R.drawable.no);
		move_iv_vetify_work_no.setImageResource(R.drawable.no);
	}

	private void resetmove_iv_yes() {
		move_iv_yes.setImageResource(R.drawable.no);
		move_iv_no.setImageResource(R.drawable.no);
	}

	/**
	 * 生成年月日
	 */
	private void genateYearMouthDay() {
		for (int i = 0; i < mYear.length; i++) {
			mYear[i] = "  " + (2015 + i) + "  ";
		}
		for (int i = 0; i < mMonth.length; i++) {
			mMonth[i] = "  " + ((1 + i)) + "  ";
		}
		for (int i = 0; i < mDay.length; i++) {
			mDay[i] = "  " + ((1 + i)) + "  ";
		}
	}

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private TextView tv_date_cancle;
	private TextView tv_date_sure;
	private WheelView wv_date_year;
	private WheelView wv_date_month;
	private WheelView wv_date_day;

	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.date_picker_item, null);
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

		tv_date_cancle.setOnClickListener(listener);
		tv_date_sure.setOnClickListener(listener);
		wv_date_year.setOnClickListener(listener);
		wv_date_month.setOnClickListener(listener);
		wv_date_day.setOnClickListener(listener);

	}

	private String mCurrentYear; // 当前的年
	private String mCurrentMonth;// 当前的月
	private String mCurrentDay;// 当前的日

	private String cacluteDate() {
		mCurrentYear = mYear[wv_date_year.getCurrentItem()];
		mCurrentYear = mCurrentYear.trim();
		mCurrentMonth = mMonth[wv_date_month.getCurrentItem()];
		mCurrentMonth = mCurrentMonth.trim();
		mCurrentDay = mDay[wv_date_day.getCurrentItem()];
		mCurrentDay = mCurrentDay.trim();
		return mCurrentYear + "年" + mCurrentMonth + "月" + mCurrentDay + "号";
	}

	/**
	 * 把时间添加到textview里面去
	 */
	private void addDateToTextView() {
		String date = cacluteDate();
		date = date.trim();
		for (int i = 0; i < mFlag.length; i++) {
			if (mFlag[i]) {
				mTvViews[i].setText(date + "");
				mFlag[i] = false;
			}
		}
	}

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */

	private void initPopWidge(View popView) {
		tv_date_cancle = (TextView) popView.findViewById(R.id.tv_date_cancle);
		tv_date_sure = (TextView) popView.findViewById(R.id.tv_date_sure);
		wv_date_year = (WheelView) popView.findViewById(R.id.wv_date_year);
		wv_date_month = (WheelView) popView.findViewById(R.id.wv_date_month);
		wv_date_day = (WheelView) popView.findViewById(R.id.wv_date_day);
		wv_date_year.setVisibleItems(5);
		wv_date_year.setCyclic(true);
		wv_date_year.setAdapter(new ArrayWheelAdapter<String>(mYear));
		wv_date_month.setVisibleItems(5);
		wv_date_month.setCyclic(true);
		wv_date_month.setAdapter(new ArrayWheelAdapter<String>(mMonth));
		wv_date_day.setVisibleItems(5);
		wv_date_day.setCyclic(true);
		wv_date_day.setAdapter(new ArrayWheelAdapter<String>(mDay));
	}

	private class PopWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_date_sure:
				// TODO 把日期加载到textview里面
				addDateToTextView();
				mPopupWindow.dismiss();
				break;

			case R.id.tv_date_cancle:
				mPopupWindow.dismiss();

			}
		}

	}

	/**
	 * 显示popWindow
	 * */
	@SuppressLint("NewApi")
	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, x, y);
		// 获取popwindow焦点
		mPopupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
		setWindowAlpha(0.7f);
	}
}
