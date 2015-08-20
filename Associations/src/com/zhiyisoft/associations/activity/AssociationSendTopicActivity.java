package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationSendTopicActivity extends BaseActivity {
	private EditText topic_title;
	private EditText topic_content;
	private ImageView topic_iv_big_image;
	private ImageView topic_iv_delete_image;
	private ImageView topic_image;
	private ImageView topic_expression;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle("发表话题", null, "发表");
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
		return R.layout.activity_association_send_topic;
	}

	// private RoundImageView topic_title;
	// private EditText topic_content;
	// private TextView topic_iv_big_image;
	// private RelativeLayout topic_iv_delete_image;
	// private RelativeLayout topic_image;
	// private TextView topic_expression;
	@Override
	public void initView() {
		topic_title = (EditText) findViewById(R.id.topic_title);
		topic_content = (EditText) findViewById(R.id.topic_content);
		topic_iv_big_image = (ImageView) findViewById(R.id.topic_iv_big_image);
		topic_iv_delete_image = (ImageView) findViewById(R.id.topic_iv_delete_image);
		topic_image = (ImageView) findViewById(R.id.topic_image);
		topic_expression = (ImageView) findViewById(R.id.topic_expression);

	}

	@Override
	public void initListener() {
		topic_iv_big_image.setOnClickListener(this);
		topic_iv_delete_image.setOnClickListener(this);
		topic_image.setOnClickListener(this);
		topic_expression.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topic_iv_big_image:
			Bundle data = new Bundle();
			mApp.startActivity(this, AssociationMemberActivity.class, data);
			break;
		case R.id.topic_iv_delete_image:
			Bundle data1 = new Bundle();
			mApp.startActivity(this, AssociationNewActivity.class, data1);
			break;
		case R.id.topic_image:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, AssociationMoveActivity.class, data2);
			break;
		case R.id.topic_expression:
			break;
		}

	}
}
