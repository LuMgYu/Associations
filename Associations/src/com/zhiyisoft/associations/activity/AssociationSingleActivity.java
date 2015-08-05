package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationNewAdapter;
import com.zhiyisoft.associations.adapter.NotifyNfyAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.listview.AssociationNewListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationSingleActivity extends BaseActivity {
	private RoundImageView title_iv;
	private TextView title_tv;
	private TextView title_tv_member;
	private TextView title_tv_topic;
	private TextView title_tv_school;
	private TextView title_tv_type;
	private TextView title_tv_move;
	private RelativeLayout title_rl_move;
	private BaseListView single_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("北京社团轮滑社", null, null);
	}

	@Override
	public String setCenterTitle() {
		return "";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_single;
	}

	@Override
	public void initView() {
		title_iv = (RoundImageView) findViewById(R.id.title_iv);
		title_tv = (TextView) findViewById(R.id.title_tv);
		title_tv_member = (TextView) findViewById(R.id.title_tv_member);
		title_tv_topic = (TextView) findViewById(R.id.title_tv_topic);
		title_tv_school = (TextView) findViewById(R.id.title_tv_school);
		title_tv_move = (TextView) findViewById(R.id.title_tv_move);
		title_rl_move = (RelativeLayout) findViewById(R.id.title_rl_move);
		single_lv = (AssociationNewListview) findViewById(R.id.single_lv);
		mAdapter = new AssociationNewAdapter(this, mlist);
		single_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		title_iv.setOnClickListener(this);
		title_rl_move.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_iv:
			Bundle data = new Bundle();
			mApp.startActivity(this, MeSettingNickActivity.class, data);
			break;
		case R.id.title_rl_move:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data1);
			break;
		}

	}
}
