package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveSendEssayActivity extends BaseActivity {
	private EditText essay_title;
	private EditText essay_content;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("发表文章", null, "发表");
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
		return R.layout.activity_move_send_essay;
	}

	@Override
	public void initView() {
		essay_title = (EditText) findViewById(R.id.essay_title);
		essay_content = (EditText) findViewById(R.id.essay_content);

	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_association:
			Bundle data = new Bundle();
			mApp.startActivity(this, AssociationMemberActivity.class, data);
			break;
		case R.id.rl_works_display:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, MoveWorksDisplayActivity.class, data1);
			break;
		case R.id.rl_move_status:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		}

	}
}
