package com.zhiyisoft.associations;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.fragment.FragmentAssociation;
import com.zhiyisoft.associations.fragment.FragmentHome;
import com.zhiyisoft.associations.fragment.FragmentMe;
import com.zhiyisoft.associations.fragment.FragmentMove;
import com.zhiyisoft.associations.fragment.FragmentNotify;
import com.zhiyisoft.associations.fragment.base.BaseFragment;

public class MainActivity extends BaseActivity implements OnClickListener {
	// private testListview mListView;
	// private BAdapter mAdapter;
	// private List<Model> mlist = new ArrayList<Model>();
	private LinearLayout ll_home;
	private LinearLayout ll_move;
	private LinearLayout ll_association;
	private LinearLayout ll_notify;
	private LinearLayout ll_me;
	// -----------------------------------
	private ImageView iv_home;
	private ImageView iv_move;
	private ImageView iv_association;
	private ImageView iv_notify;
	private ImageView iv_me;
	// --------------------------------------
	private TextView tv_home;
	private TextView tv_move;
	private TextView tv_association;
	private TextView tv_notify;
	private TextView tv_me;

	private FragmentHome mHomeFragment;
	private FragmentMove mMoveFragment;
	private FragmentAssociation mAssociationFragment;
	private FragmentNotify mNotifyFragment;
	private FragmentMe mMeFragment;

	@Override
	public String setCenterTitle() {
		return "首页";
	}

	@Override
	public int getBottomLayoutId() {
		return R.layout.comom_layout;
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.comom_layout;
	}

	@Override
	public void initView() {
		// mListView = (testListview) findViewById(R.id.testlv);
		// mAdapter = new testAdapter(this, mlist);
		// mListView.setAdapter(mAdapter);

		ll_home = (LinearLayout) findViewById(R.id.ll_home);
		ll_move = (LinearLayout) findViewById(R.id.ll_move);
		ll_association = (LinearLayout) findViewById(R.id.ll_association);
		ll_notify = (LinearLayout) findViewById(R.id.ll_notify);
		ll_me = (LinearLayout) findViewById(R.id.ll_me);

		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_move = (ImageView) findViewById(R.id.iv_move);
		iv_association = (ImageView) findViewById(R.id.iv_association);
		iv_notify = (ImageView) findViewById(R.id.iv_notify);
		iv_me = (ImageView) findViewById(R.id.iv_me);

		tv_home = (TextView) findViewById(R.id.tv_home);
		tv_move = (TextView) findViewById(R.id.tv_move);
		tv_association = (TextView) findViewById(R.id.tv_association);
		tv_notify = (TextView) findViewById(R.id.tv_notify);
		tv_me = (TextView) findViewById(R.id.tv_me);

	}

	@Override
	public void initListener() {
		ll_home.setOnClickListener(this);
		ll_move.setOnClickListener(this);
		ll_association.setOnClickListener(this);
		ll_notify.setOnClickListener(this);
		ll_me.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_home:
			resetTheColor();
			changeTheColor(iv_home, tv_home);
			initFragmentHome();
			break;

		case R.id.ll_move:
			resetTheColor();
			changeTheColor(iv_move, tv_move);
			initFragmentMove();
			break;
		case R.id.ll_association:
			resetTheColor();
			changeTheColor(iv_association, tv_association);
			initFragmentAssociation();
			break;
		case R.id.ll_notify:
			resetTheColor();
			changeTheColor(iv_notify, tv_notify);
			initFragmentNotify();
			break;
		case R.id.ll_me:
			resetTheColor();
			changeTheColor(iv_me, tv_me);
			initFragmentMe();
			break;
		}
	}

	/**
	 * 初始化FragmentMe()
	 */
	private void initFragmentMe() {
		if (mMeFragment == null) {
			mMeFragment = new FragmentMe();
		}
		replaceFragment(mMeFragment);
		changeTheTitle("个人");
	}

	/**
	 * 初始化FragmentNotify()
	 */

	private void initFragmentNotify() {
		if (mNotifyFragment == null) {
			mNotifyFragment = new FragmentNotify();
		}
		replaceFragment(mNotifyFragment);
		changeTheTitle("通知");
	}

	/**
	 * 初始化FragmentAssociation()
	 */
	private void initFragmentAssociation() {
		if (mAssociationFragment == null) {
			mAssociationFragment = new FragmentAssociation();
		}
		replaceFragment(mAssociationFragment);
		changeTheTitle("社团");
	}

	/**
	 * 初始化FragmentMove()
	 */
	private void initFragmentMove() {
		if (mMoveFragment == null) {
			mMoveFragment = new FragmentMove();
		}
		replaceFragment(mMoveFragment);
		changeTheTitle("运动");
	}

	/**
	 * 初始化FragmentHome()
	 */
	private void initFragmentHome() {
		if (mHomeFragment == null) {
			mHomeFragment = new FragmentHome();
		}
		replaceFragment(mHomeFragment);
		changeTheTitle("主页");
	}

	private void replaceFragment(BaseFragment fragment) {
		FragmentTransaction mFTransaction = mFManager.beginTransaction();
		mFTransaction.replace(R.id.ll_content, fragment).commit();
	}

	/**
	 * 改变这两个控件颜色
	 * 
	 * @param imageView
	 *            需要改变的imageview控件
	 * @param textView
	 *            需要改變的textview控件
	 */
	private void changeTheColor(ImageView imageView, TextView textView) {
		if (imageView != null && textView != null) {
			imageView.setImageDrawable(getResources().getDrawable(
					R.drawable.arrow_down));
			textView.setTextColor(getResources().getColor(
					R.color.bottom_tv_color_change));
		}
	}

	/**
	 * 把这两个控件设置颜色重置
	 */
	private void resetTheColor() {
		iv_home.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		iv_move.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		iv_association.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		iv_notify.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		iv_me.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		tv_home.setTextColor(getResources().getColor(R.color.bottom_tv_color));
		tv_move.setTextColor(getResources().getColor(R.color.bottom_tv_color));
		tv_association.setTextColor(getResources().getColor(
				R.color.bottom_tv_color));
		tv_notify
				.setTextColor(getResources().getColor(R.color.bottom_tv_color));
		tv_me.setTextColor(getResources().getColor(R.color.bottom_tv_color));
	}
}
