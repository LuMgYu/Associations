package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationTindingsAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.AssociationListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现展示所点选项的社团
 *
 */

public class AssociationTidingsDisplayActivity extends BaseActivity {
	private BaseListView association_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelLeague league;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("", league.getCategoryName(), "");
	}

	@Override
	public void initIntent() {
		mBundle = getIntent().getExtras();
		if (mBundle != null) {
			league = (ModelLeague) mBundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_tiding_display;
	}

	@Override
	public void initView() {
		association_lv = (AssociationListview) findViewById(R.id.association_lv);
		mAdapter = new AssociationTindingsAdapter(this, league);
		association_lv.setAdapter(mAdapter);
		association_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Model model = (Model) parent.getItemAtPosition(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable(Config.SEND_ACTIVITY_DATA, model);
				mApp.startActivity(AssociationTidingsDisplayActivity.this,
						AssociationTidingsDetailActivity.class, bundle);
			}
		});
	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "";
	}
}
