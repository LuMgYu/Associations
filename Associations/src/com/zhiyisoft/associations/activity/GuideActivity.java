package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.GuideAdapter;

/**
 * author：qiuchunjia time：下午5:29:32 类描述：这个类是实现
 *
 */

public class GuideActivity extends BaseActivity {
	private ViewPager mViewpager;
	private GuideAdapter mAdapter;
	int[] ImageRes = new int[] { R.drawable.guide_01, R.drawable.guide_02,
			R.drawable.guide_03 };
	List<View> mViews = new ArrayList<View>();

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return null;
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_guide;
	}

	@Override
	public void initView() {
		mViewpager = (ViewPager) findViewById(R.id.guideViewpager);
		for (int i = 0; i < ImageRes.length; i++) {
			ImageView view = new ImageView(this);
			view.setLayoutParams(new ViewGroup.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			view.setImageResource(ImageRes[i]);
			if (i == 2) {
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						mApp.startActivity(GuideActivity.this,
								MainActivity.class, null,
								Intent.FLAG_ACTIVITY_CLEAR_TOP);
						GuideActivity.this.finish();

					}
				});
			}
			mViews.add(view);
		}
		mAdapter = new GuideAdapter(mViews);
		mViewpager.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

}
