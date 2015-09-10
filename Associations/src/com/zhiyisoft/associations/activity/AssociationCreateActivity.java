package com.zhiyisoft.associations.activity;

import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.LeagueIm;
import com.zhiyisoft.associations.cache.BaseCache;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.util.Anim;
import com.zhiyisoft.associations.widget.wheelview.ArrayWheelAdapter;
import com.zhiyisoft.associations.widget.wheelview.WheelView;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationCreateActivity extends BaseActivity {
	private RoundImageView association_icon;
	private EditText association_et_name;
	private TextView association_tv_school_name;
	private RelativeLayout association_rl_school;
	private RelativeLayout association_rl_welfare;
	private TextView association_tv_welfare_name;
	private ImageView association_iv_welfare;
	private EditText association_et_about;
	private ImageView association_iv_yes;
	private ImageView association_iv_no;
	private RelativeLayout association_rl_main;
	private EditText association_et_contact;
	private EditText association_et_contact_way;
	private ImageView association_iv_commit_yes;

	private TextView association_tv_commit_yes;
	private Button association_btn_commit;
	private Bitmap mBitmap;
	public static final int SUCCESS = 1;

	private Handler mHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SUCCESS:

				break;

			default:
				break;
			}
		};
	};

	@Override
	public String setCenterTitle() {
		return "申请社团";

	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_create;
	}

	@Override
	public void initView() {
		association_icon = (RoundImageView) findViewById(R.id.association_icon);
		association_et_name = (EditText) findViewById(R.id.association_et_name);
		association_tv_school_name = (TextView) findViewById(R.id.association_tv_school_name);
		association_rl_school = (RelativeLayout) findViewById(R.id.association_rl_school);
		association_rl_welfare = (RelativeLayout) findViewById(R.id.association_rl_welfare);
		association_tv_welfare_name = (TextView) findViewById(R.id.association_tv_welfare_name);

		association_iv_welfare = (ImageView) findViewById(R.id.association_iv_welfare);
		association_et_about = (EditText) findViewById(R.id.association_et_about);
		association_iv_yes = (ImageView) findViewById(R.id.association_iv_yes);
		association_iv_no = (ImageView) findViewById(R.id.association_iv_no);
		association_rl_main = (RelativeLayout) findViewById(R.id.association_rl_main);
		association_et_contact = (EditText) findViewById(R.id.association_et_contact);
		association_et_contact_way = (EditText) findViewById(R.id.association_et_contact_way);
		association_iv_commit_yes = (ImageView) findViewById(R.id.association_iv_commit_yes);
		association_tv_commit_yes = (TextView) findViewById(R.id.association_tv_commit_yes);
		association_btn_commit = (Button) findViewById(R.id.association_btn_commit);
		association_tv_commit_yes.getPaint()
				.setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
		initCameraPopWindow();
		initPopWindow();
	}

	@Override
	public void initListener() {
		association_icon.setOnClickListener(this);
		association_rl_school.setOnClickListener(this);
		association_iv_welfare.setOnClickListener(this);
		association_iv_yes.setOnClickListener(this);
		association_iv_no.setOnClickListener(this);
		association_rl_main.setOnClickListener(this);
		association_btn_commit.setOnClickListener(this);
		association_tv_commit_yes.setOnClickListener(this);
		association_rl_welfare.setOnClickListener(this);
	}

	@Override
	public Bitmap compressPhotoAndDisplay(Bitmap originBitmap) {
		mBitmap = super.compressPhotoAndDisplay(originBitmap);
		association_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public Bitmap compressOutStream2Bitmap(Bitmap bitmap, OutputStream stream) {
		// TODO Auto-generated method stub
		mBitmap = super.compressOutStream2Bitmap(bitmap, stream);
		association_icon.setImageBitmap(mBitmap);
		return mBitmap;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.association_rl_welfare:
			showPop(association_rl_welfare, 0, 0);
			break;
		case R.id.association_icon:
			showCameraPop(association_icon, 0, 0);
			break;
		case R.id.association_rl_school:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, MeSettingProvinceActivity.class, data1);
			break;
		case R.id.association_tv_commit_yes:
			Bundle dat = new Bundle();
			Intent intent = new Intent();
			intent.setClass(this, AssociationAgreementActivity.class);
			startActivity(intent);
			Anim.startActivityFromBottom(this);
			break;
		case R.id.association_iv_welfare:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.association_iv_yes:
			resetChoose();
			association_iv_yes.setImageResource(R.drawable.yes);
			break;
		case R.id.association_iv_no:
			resetChoose();
			association_iv_no.setImageResource(R.drawable.yes);
			break;
		case R.id.association_rl_main:
			Bundle data3 = new Bundle();
			mApp.startActivity(this, AssociationWordActivity.class, data3);
			break;
		case R.id.association_btn_commit:
			final ModelLeague league = new ModelLeague();
			// name:社团名(45字符，约11个字)
			// categoryid：分类
			// logo:logoID
			// description:描述
			// school:学校ID
			// private:是否够开
			// tags: 标签(选填)
			// contact:联系人电话
			league.setName(association_et_name.getText().toString() + "");
			// league.setCategoryid(categoryid);
			// league.setLogo(name);
			league.setDescription(association_et_about.getText().toString()
					+ "");
			league.setSchool(association_tv_school_name.getText().toString()
					+ "");
			// league.setmPrivate(association_et_name.getText().toString() +
			// "");
			// league.setTags(association_et_name.getText().toString() + "");
			league.setContact(association_et_contact_way.getText().toString()
					+ "");
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					LeagueIm leagueIm = mApp.getLeagueIm();
					leagueIm.createLeague(league);
				}
			});
			break;
		}

	}

	/**
	 * 重置选项
	 */
	private void resetChoose() {
		association_iv_yes.setImageResource(R.drawable.no);
		association_iv_no.setImageResource(R.drawable.no);

	}

	// --------------------------PopupWindow的界面控件-----------------------------------------
	private PopupWindow mPopupWindow;
	private TextView tv_date_cancle;
	private TextView tv_date_sure;
	private WheelView wv_category;
	private String[] mCategory = new String[] { "  科技  ", "  人文  ", "  数学  ",
			"  人文  ", "  艺术  ", "  教育  " };

	/**
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		if (mPopupWindow == null) {
			View popView = mInflater.inflate(R.layout.category_picker_item,
					null);
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

	private void initPopWidge(View popView) {
		tv_date_cancle = (TextView) popView.findViewById(R.id.tv_date_cancle);
		tv_date_sure = (TextView) popView.findViewById(R.id.tv_date_sure);
		wv_category = (WheelView) popView.findViewById(R.id.wv_category);
		wv_category.setVisibleItems(5);
		wv_category.setCyclic(true);
		wv_category.setAdapter(new ArrayWheelAdapter<String>(mCategory));
	}

	private class PopWindowItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_date_sure:
				// TODO 把日期加载到textview里面
				association_tv_welfare_name.setText(mCategory[wv_category
						.getCurrentItem()] + "");
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
