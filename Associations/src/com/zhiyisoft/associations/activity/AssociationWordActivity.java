package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationWordAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.AssociationWordListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationWordActivity extends BaseActivity {
	private BaseListView word_lv;
	private TextView next;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelLeague mLeague;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "文件");
	}

	@Override
	public String setCenterTitle() {
		return "社团文件";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
			if (mLeague != null) {
				setAlltitle(mLeague.getName(), null, null);
			}
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_word;
	}

	// private BaseListView word_lv;
	// private TextView next;
	@Override
	public void initView() {
		word_lv = (AssociationWordListview) findViewById(R.id.word_lv);
		next = (TextView) findViewById(R.id.next);
		mAdapter = new AssociationWordAdapter(this, mLeague);
		word_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_nick:
			Bundle data = new Bundle();
			mApp.startActivity(this, MeSettingNickActivity.class, data);
			break;
		case R.id.rl_gender:
			break;
		case R.id.tv_title_right:
			mApp.startActivity(this, LocalFileActivity.class, null);
			break;
		}

	}
}
