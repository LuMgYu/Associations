package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationCheckAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.AssociationListview;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午1:58:30 类描述：这个类是实现
 *
 */

public class AssociationCheckActivity extends BaseActivity {
	private AssociationListview Association_lv;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {

		return "社团选择";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_check;
	}

	@Override
	public void initView() {
		Association_lv = (AssociationListview) findViewById(R.id.Association_lv);
		AssociationCheckAdapter checkAdapter = new AssociationCheckAdapter(this);
		Association_lv.setAdapter(checkAdapter);
		Association_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Model model = (Model) parent.getItemAtPosition(position);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable(Config.CHECKED_ASSOCITION, model);
				intent.putExtras(bundle);
				setResult(GET_DATA_FROM_ACTIVITY, intent);
				onBackPressed();
			}
		});
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
