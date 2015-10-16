package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.widget.wheelview.ArrayWheelAdapter;
import com.zhiyisoft.associations.widget.wheelview.WheelView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveCreateActivity extends BaseActivity {
	public final static int ONLINE = 0;
	public final static int NOT_ONLINE = 1;
	private boolean isOnline = true;
	private FrameLayout fl_icon;
	private ImageView move_icon;
	private EditText move_et_name;
	private RelativeLayout move_rl_welfare;
	private RelativeLayout move_rl_main;
	private RelativeLayout move_rl_location;
	private EditText move_et_location;

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
	private TextView move_tv_welfare_name;
	private TextView move_tv_photo;
	private ImageView move_iv_photo_yes;
	private ImageView move_iv_music_yes;
	private RelativeLayout move_rl_scope;
	private RelativeLayout move_rl_work_end;
	private RelativeLayout move_rl_commmit_work;
	// 新添加的的控件
	private RelativeLayout move_rl_main_master;
	private EditText move_et_main_master;
	private RelativeLayout move_rl_association;
	private TextView move_tv_association_name;

	private ImageView move_iv_vetify_work_no;
	private ImageView move_iv_commit_yes_master;
	private ImageView move_iv_commit_all;
	private ImageView move_iv_vetify_yes;
	private ImageView move_iv_vedio_yes;
	private TextView move_tv_scope_name;
	private EditText move_et_about;

	private TextView move_tv_enter;
	private TextView move_tv_work;
	private RelativeLayout move_rl_public;
	private RelativeLayout move_rl_work;
	private RelativeLayout move_rl_commit;
	private RelativeLayout move_rl_vetify;
	private Button btn_move_commit;

	public String[] mYear = new String[40];
	public String[] mMonth = new String[12];
	public String[] mDay = new String[31];

	// 定义六个布尔类型，分别从上到下表示这个六个时间选择器
	private boolean[] mFlag = new boolean[] { false, false, false, false,
			false, false };
	private TextView[] mTvViews;

	// 多选标志
	private boolean mIsChoose1 = false;
	private boolean mIsChoose2 = false;
	private boolean mIsChoose3 = false;
	private boolean mIsChoose4 = false;
	private Bitmap mBitmap;
	private static final int SUCCESS_ONLINE = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {

			case SUCCESS_ONLINE:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					ToastUtils.showToast("创建活动成功！等待审核");
				} else {
					ToastUtils.showToast("创建活动失败！");
				}
				break;
			}

		};

	};

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
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			int i = bundle.getInt(Config.SEND_ACTIVITY_DATA);
			isOnline = (i == 0) ? true : false;
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_create;
	}

	@Override
	public void initView() {
		fl_icon = (FrameLayout) findViewById(R.id.fl_icon);
		move_icon = (ImageView) findViewById(R.id.move_icon);
		move_et_name = (EditText) findViewById(R.id.move_et_name);
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
		move_rl_location = (RelativeLayout) findViewById(R.id.move_rl_location);
		move_et_location = (EditText) findViewById(R.id.move_et_location);
		// 新增控件

		move_rl_main_master = (RelativeLayout) findViewById(R.id.move_rl_main_master);
		move_et_main_master = (EditText) findViewById(R.id.move_et_main_master);
		move_tv_association_name = (TextView) findViewById(R.id.move_tv_association_name);
		move_rl_association = (RelativeLayout) findViewById(R.id.move_rl_association);

		move_et_about = (EditText) findViewById(R.id.move_et_about);
		move_iv_vetify_work_no = (ImageView) findViewById(R.id.move_iv_vetify_work_no);
		move_iv_commit_yes_master = (ImageView) findViewById(R.id.move_iv_commit_yes_master);
		move_iv_commit_all = (ImageView) findViewById(R.id.move_iv_commit_all);
		move_iv_vedio_yes = (ImageView) findViewById(R.id.move_iv_vedio_yes);
		move_tv_scope_name = (TextView) findViewById(R.id.move_tv_scope_name);
		move_tv_welfare_name = (TextView) findViewById(R.id.move_tv_welfare_name);
		move_tv_enter = (TextView) findViewById(R.id.move_tv_enter);
		move_tv_work = (TextView) findViewById(R.id.move_tv_work);
		move_rl_public = (RelativeLayout) findViewById(R.id.move_rl_public);
		move_rl_work = (RelativeLayout) findViewById(R.id.move_rl_work);
		move_rl_commit = (RelativeLayout) findViewById(R.id.move_rl_commit);
		btn_move_commit = (Button) findViewById(R.id.btn_move_commit);
		judgeIsOnline();
		mTvViews = new TextView[] { move_tv_start_time, move_tv_start_end,
				move_tv_enter_time, move_tv_enter_end_time,
				move_tv_commmit_work_Start_time, move_tv_workr_end_time };
		genateYearMouthDay();
		initPopWindow();
		initCameraPopWindow();
		initCategoryPopWindow();
	}

	/**
	 * 判断是否是创建线上内容，否则就是线下内容
	 */
	private void judgeIsOnline() {
		// TODO 隐藏部分控件
		if (!isOnline) {
			move_tv_enter.setVisibility(View.GONE);
			move_rl_public.setVisibility(View.GONE);
			move_rl_enter.setVisibility(View.GONE);
			move_rl_enter_end.setVisibility(View.GONE);
			move_tv_work.setVisibility(View.GONE);
			move_rl_work.setVisibility(View.GONE);
			move_rl_commit.setVisibility(View.GONE);
			move_rl_commmit_work.setVisibility(View.GONE);
			move_rl_work_end.setVisibility(View.GONE);
			tv_title_right.setVisibility(View.GONE);
			btn_move_commit.setVisibility(View.VISIBLE);
			btn_move_commit.setOnClickListener(this);
			setAlltitle(null, "创建线下活动", null);
		} else {
			move_rl_location.setVisibility(View.GONE);
		}
	}

	@Override
	public void initListener() {
		move_rl_main.setOnClickListener(this);
		move_rl_end.setOnClickListener(this);
		move_rl_enter.setOnClickListener(this);
		move_rl_enter_end.setOnClickListener(this);
		move_rl_work_end.setOnClickListener(this);
		move_rl_commmit_work.setOnClickListener(this);
		move_rl_welfare.setOnClickListener(this);
		// -------------------------
		move_icon.setOnClickListener(this);
		move_rl_welfare.setOnClickListener(this);
		move_iv_yes.setOnClickListener(this);
		move_iv_no.setOnClickListener(this);
		move_iv_vetify_work_yes.setOnClickListener(this);
		move_iv_vetify_work_no.setOnClickListener(this);
		move_iv_commit_yes_master.setOnClickListener(this);
		move_iv_commit_all.setOnClickListener(this);
		move_iv_title_yes.setOnClickListener(this);
		move_iv_photo_yes.setOnClickListener(this);
		move_iv_vedio_yes.setOnClickListener(this);
		move_iv_music_yes.setOnClickListener(this);
		move_rl_scope.setOnClickListener(this);
		tv_title_right.setOnClickListener(this);
		move_rl_association.setOnClickListener(this);

	}

	@Override
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		move_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public Bitmap compressOutStream2Bitmap(Bitmap bitmap, OutputStream stream) {
		// TODO Auto-generated method stub
		mBitmap = super.compressOutStream2Bitmap(bitmap, stream);
		move_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public File getFile(String path) {
		Log.i("upload", path);
		File file = super.getFile(path);
		uploadEventIcon(file);
		return file;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ----------------------------------时间选择部分------------------------------------------
		case R.id.move_rl_association:
			mApp.startActivityForResult(this, AssociationCheckActivity.class,
					null);
			break;
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
		case R.id.move_rl_welfare:
			showCategoryPop(move_rl_welfare, 0, 0);
			break;
		// ----------------------------------------------------------------------------
		case R.id.move_icon:
			showCameraPop(move_icon, 0, 0);
			break;
		// 活动时间
		case R.id.move_iv_yes:
			resetmove_iv_yes();
			joinAudit = "1";
			move_iv_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_no:
			resetmove_iv_yes();
			joinAudit = "0";
			move_iv_no.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_work_yes:
			resetmove_iv_vetify_work_yes();
			workAudit = "1";
			move_iv_vetify_work_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_vetify_work_no:
			resetmove_iv_vetify_work_yes();
			workAudit = "0";
			move_iv_vetify_work_no.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_commit_yes_master:
			resetmove_iv_commit_yes_master();
			worksPurview = "1";
			move_iv_commit_yes_master.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_commit_all:
			resetmove_iv_commit_yes_master();
			worksPurview = "2";
			move_iv_commit_all.setImageResource(R.drawable.yes);
			break;
		case R.id.move_iv_title_yes:
			changeTheViewImage(move_iv_title_yes, mIsChoose1);
			mIsChoose1 = (mIsChoose1 == true) ? false : true;
			count1++;
			break;
		case R.id.move_iv_photo_yes:
			changeTheViewImage(move_iv_photo_yes, mIsChoose2);
			mIsChoose2 = (mIsChoose2 == true) ? false : true;
			count2++;
			break;
		case R.id.move_iv_vedio_yes:
			changeTheViewImage(move_iv_vedio_yes, mIsChoose3);
			mIsChoose3 = (mIsChoose3 == true) ? false : true;
			count3++;
			break;
		case R.id.move_iv_music_yes:
			changeTheViewImage(move_iv_music_yes, mIsChoose4);
			mIsChoose4 = (mIsChoose4 == true) ? false : true;
			count4++;
			break;
		case R.id.move_rl_scope:
			mApp.startActivityForResult(this, MeSettingProvinceActivity.class,
					null);

			break;
		case R.id.tv_title_right:
			// TODO
			view2data();
			if (judgeTheOnlineData()) {
				ModelEvent event = bindDataToModel();
				createEvent(event);
			}
			break;
		case R.id.btn_move_commit:
			view2data();
			if (judgeTheNotOnlineData()) {
				ModelEvent event = bindDataToModel();
				createEvent(event);
			}
			break;
		}

	}

	/**
	 * 改变图片的状态
	 * 
	 * @param view
	 * @param mIsChoose12
	 */
	private void changeTheViewImage(ImageView view, boolean mIsChoose12) {
		if (mIsChoose12) {
			view.setImageResource(R.drawable.xz02);
		} else {
			view.setImageResource(R.drawable.xz01);
		}
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ModelSchool model = (ModelSchool) bundle
					.get(Config.GET_ACTIVITY_DATA);
			if (model != null) {
				move_tv_scope_name.setText(model.getName() + "");
			}
			ModelLeague league = (ModelLeague) bundle
					.get(Config.CHECKED_ASSOCITION);
			if (league != null) {
				gid = league.getGid();
				move_tv_association_name.setText(league.getName());
			}
		}
	}

	/************************************** 创建活动需要的变量 **************************************************/
	private String online = "0"; // 0线上活动，1线下活动 必填
	private String logo;// 接口37中返回的id必填
	private String gid = "15293";// 所属社团 必填
	private String title;// 活动标题 必填
	private String address;// 活动地点 选填
	private String type = "12";// 活动类别 必填
	private String host = "成都大学";// 主办方 必填
	private String explain;// 活动内容 必填
	private String sTime;// 活动开始时间 必填
	private String eTime;// 活动结束时间 必填
	private String joinAudit = "1";// 报名是否审核，0否，1是 必填
	private String joinStime;// 报名开始时间 必填
	private String joinEtime;// 报名结束时间 必填
	private String workAudit = "1";// 作品是否审核，0否，1是 必填
	private String worksPurview = "1";// 作品上传权限 1发起人，2所有人
	private String workStime;// 品提交开始时间 必填
	private String workEtime;// 作品提交结束时间 必填
	private String explainType; // 作品类型
	private String rangeDes;// 指定学校id 选填explainType

	private int count1 = 0; // 来记录选择作品次数，然后用于拼接
	private int count2 = 0;
	private int count3 = 0;
	private int count4 = 0;

	private void calcuteCountChoose() {
		explainType = "";
		if (count1 % 2 != 0) {
			explainType = explainType + "1,";
		}
		if (count2 % 2 != 0) {
			explainType = explainType + "2,";
		}
		if (count3 % 2 != 0) {
			explainType = explainType + "3,";
		}
		if (count4 % 2 != 0) {
			explainType = explainType + "4";
		}
	}

	// TODO
	/**
	 * 把界面的数据映射到这些变量里面
	 */
	private void view2data() {
		if (isOnline) {
			online = "0"; // 0线上活动，1线下活动 必填
		} else {
			online = "1";
		}
		// logo;// 接口37中返回的id必填
		// gid = "15293";// 所属社团 必填
		title = move_et_name.getText().toString();// 活动标题 必填
		address = move_et_location.getText().toString();// 活动地点 选填
		// type = "12"; 在选择的时候就已经赋值了
		host = move_et_main_master.getText().toString();
		explain = move_et_about.getText().toString();// 活动内容 必填
		sTime = move_tv_start_time.getText().toString();// 活动开始时间 必填
		sTime = dateToStr(sTime);
		eTime = move_tv_start_end.getText().toString();// 活动结束时间 必填
		eTime = dateToStr(eTime);
		// joinAudit = ;// 报名是否审核，0否，1是 必填
		joinStime = move_tv_enter_time.getText().toString();// 报名开始时间 必填
		joinStime = dateToStr(joinStime);
		joinEtime = move_tv_enter_end_time.getText().toString();// 报名结束时间 必填
		joinEtime = dateToStr(joinEtime);
		// workAudit;// 作品是否审核，0否，1是 必填
		// worksPurview;// 作品上传权限 1发起人，2所有人
		workStime = move_tv_commmit_work_Start_time.getText().toString();// 品提交开始时间
		workStime = dateToStr(workStime); // 必填
		workEtime = move_tv_workr_end_time.getText().toString();// 作品提交结束时间 必填
		workEtime = dateToStr(workEtime);
		rangeDes = move_tv_scope_name.getText().toString();// 指定学校id 选填
		calcuteCountChoose();
	}

	/**
	 * 把日期转为时间戳
	 * 
	 * @param year_month_date
	 * @return
	 */
	private String dateToStr(String year_month_date) {
		if (year_month_date == null || year_month_date.equals("")) {
			return null;
		}
		return DateUtil.dateToStr(year_month_date);
	}

	/**
	 * 判断线上活动是否填写完整
	 * 
	 * @return
	 */
	private boolean judgeTheOnlineData() {
		if (online == null || online.equals("")) {
			ToastUtils.showToast("选择线上还是线下");
			return false;
		}
		if (logo == null || logo.equals("")) {
			ToastUtils.showToast("请上传活动封面");
			return false;
		}
		if (gid == null || gid.equals("")) {
			ToastUtils.showToast("请选择所属社团");
			return false;
		}
		if (title == null || title.equals("")) {
			ToastUtils.showToast("请填写标题");
			return false;
		}
		if (type == null || type.equals("")) {
			ToastUtils.showToast("请选择类型");
			return false;
		}
		if (host == null || host.equals("")) {
			ToastUtils.showToast("请输入主办方");
			return false;
		}
		if (explain == null || explain.equals("")) {
			ToastUtils.showToast("请输入活动详情");
			return false;
		}

		if (sTime == null || sTime.equals("")) {
			ToastUtils.showToast("请选择时间");
			return false;
		}
		if (eTime == null || eTime.equals("")) {
			ToastUtils.showToast("请选择时间");
			return false;
		}
		if (joinStime == null || joinStime.equals("")) {
			ToastUtils.showToast("请选择加入时间");
			return false;
		}
		if (joinEtime == null || joinEtime.equals("")) {
			ToastUtils.showToast("请选择加入时间");
			return false;
		}
		if (workStime == null || workStime.equals("")) {
			ToastUtils.showToast("请选择作品时间");
			return false;
		}
		if (workEtime == null || workEtime.equals("")) {
			ToastUtils.showToast("请选择作品时间");
			return false;
		}
		if (explainType == null || explainType.equals("")) {
			ToastUtils.showToast("请选择作品类型");
			return false;
		}
		if (rangeDes == null || rangeDes.equals("")) {
			ToastUtils.showToast("请选择学校");
			return false;
		}
		return true;
	}

	/**
	 * 判断非上线活动的数据
	 * 
	 * @return
	 */
	private boolean judgeTheNotOnlineData() {
		if (online == null || online.equals("")) {
			ToastUtils.showToast("选择线上还是线下");
			return false;
		}
		if (logo == null || logo.equals("")) {
			ToastUtils.showToast("请上传活动封面");
			return false;
		}
		if (gid == null || gid.equals("")) {
			ToastUtils.showToast("请选择所属社团");
			return false;
		}
		if (title == null || title.equals("")) {
			ToastUtils.showToast("请填写标题");
			return false;
		}
		if (address == null || address.equals("")) {
			ToastUtils.showToast("请填写地址");
			return false;
		}
		if (type == null || type.equals("")) {
			ToastUtils.showToast("请选择类型");
			return false;
		}
		if (host == null || host.equals("")) {
			ToastUtils.showToast("请输入主办方");
			return false;
		}
		if (explain == null || explain.equals("")) {
			ToastUtils.showToast("请输入活动详情");
			return false;
		}

		if (sTime == null || sTime.equals("")) {
			ToastUtils.showToast("请选择时间");
			return false;
		}
		if (eTime == null || eTime.equals("")) {
			ToastUtils.showToast("请选择时间");
			return false;
		}
		if (explainType == null || explainType.equals("")) {
			ToastUtils.showToast("请选择作品类型");
			return false;
		}
		if (rangeDes == null || rangeDes.equals("")) {
			ToastUtils.showToast("请选择学校");
			return false;
		}
		return true;
	}

	/**
	 * 把数据封装到model里面去
	 * 
	 * @return
	 */
	private ModelEvent bindDataToModel() {
		ModelEvent event = new ModelEvent();
		event.setOnline(online);
		event.setLogo(logo);
		event.setGid(gid);
		event.setTitle(title);
		event.setAddress(address);
		event.setType(type);
		event.setHost(host);
		event.setExplain(explain);
		event.setsTime(sTime);
		event.seteTime(eTime);
		event.setJoinAudit(joinAudit);
		event.setJoinStime(joinStime);
		event.setJoinEtime(joinEtime);
		event.setWorkAudit(workAudit);
		event.setWorksPurview(worksPurview);
		event.setWorkStime(workStime);
		event.setWorkEtime(workEtime);
		event.setRangeDes(rangeDes);
		event.setExplainType(explainType);
		return event;
	}

	private void createEvent(final ModelEvent event) {
		final EventImpl eventImpl = mApp.getEventFIm();
		mApp.getExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Boolean isSuccess = eventImpl.createEvent(event);
				Message message = Message.obtain();
				message.what = SUCCESS_ONLINE;
				message.obj = isSuccess;
				mHandle.sendMessage(message);
			}
		});
	}

	/************************* 创建活动需要的变量 end ****************************************/

	/**
	 * 上传活动icon
	 * 
	 * @param user
	 */
	private void uploadEventIcon(File file) {
		ModelUser user = mApp.getUser();
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, user.getOauth_token());
		params.put(Api.oauth_token_secret, user.getOauth_token_secret());
		if (file != null) {
			try {
				params.put("file", file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=eventlogo",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						try {
							JSONObject jsonObject = new JSONObject(result);
							if (jsonObject.has("data")) {
								JSONObject data = jsonObject
										.getJSONObject("data");
								if (data.has("url")) {
									ToastUtils.showToast("上传活动封面成功");
								}
								if (data.has("id")) {
									logo = String.valueOf(data.getInt("id"));
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
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
	private void addDataToTextView() {
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
				addDataToTextView();
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

	// --------------------------类别选择PopupWindow的界面控件-----------------------------------------
	private PopupWindow mCategoryPW;
	private WheelView wv_category;
	private String[] mCategory = new String[] { "  赛事  ", "  会展  ", "  演出  ",
			"  聚会  ", "  体育  ", "  旅行  ", "  公益  ", "  其他  " };
	private int[] mCategoryId = new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	/**
	 * 初始化popWindow
	 * */
	private void initCategoryPopWindow() {
		if (mCategoryPW == null) {
			View popView = mInflater.inflate(R.layout.category_picker_item,
					null);
			mCategoryPW = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			mCategoryPW.setBackgroundDrawable(new ColorDrawable(0));
			mCategoryPW.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					setWindowAlpha(1.0f);

				}
			});
			// 设置popwindow出现和消失动画
			initCategoryPopWidge(popView);
			setCategoryPopListener();
		}
	}

	/**
	 * 设置popWindow监听器
	 */
	private void setCategoryPopListener() {
		PopCategoryWindowItemListener listener = new PopCategoryWindowItemListener();
		tv_date_cancle.setOnClickListener(listener);
		tv_date_sure.setOnClickListener(listener);
		wv_category.setOnClickListener(listener);

	}

	/**
	 * 把时间添加到textview里面去
	 */

	/**
	 * 初始化popwindow里面的控件
	 * 
	 * @param popView
	 */

	private void initCategoryPopWidge(View popView) {
		tv_date_cancle = (TextView) popView.findViewById(R.id.tv_date_cancle);
		tv_date_sure = (TextView) popView.findViewById(R.id.tv_date_sure);
		wv_category = (WheelView) popView.findViewById(R.id.wv_category);
		wv_category.setVisibleItems(5);
		wv_category.setCyclic(true);
		wv_category.setAdapter(new ArrayWheelAdapter<String>(mCategory));
	}

	private class PopCategoryWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_date_sure:
				// TODO 把日期加载到textview里面
				move_tv_welfare_name.setText(mCategory[wv_category
						.getCurrentItem()] + "");
				type = String
						.valueOf(mCategoryId[wv_category.getCurrentItem()]);
				mCategoryPW.dismiss();
				break;

			case R.id.tv_date_cancle:
				mCategoryPW.dismiss();

			}
		}
	}

	/**
	 * 显示popWindow
	 * */
	@SuppressLint("NewApi")
	public void showCategoryPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		mCategoryPW.showAtLocation(parent, Gravity.BOTTOM, x, y);
		// 获取popwindow焦点
		mCategoryPW.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		mCategoryPW.setOutsideTouchable(true);
		mCategoryPW.update();
		setWindowAlpha(0.7f);
	}

}
