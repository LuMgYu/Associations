package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationMemberAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.LeagueIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelError;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelMember;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenu;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenuListView;
import com.zhiyisoft.associations.util.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationMemberActivity extends BaseActivity {
	private SwipeMenuListView member_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelLeague mLeague;
	private int deletePosion = 0;

	private final int DELETE_SUCCESS = 1;
	private Handler mHandle = new Handler() {

		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case DELETE_SUCCESS:
				ModelError error = (ModelError) msg.obj;
				if (error != null) {
					if (error.getStatus() == 1) {
						mAdapter.mList.remove(deletePosion);
						mAdapter.notifyDataSetChanged();
						return;
					}
					ToastUtils.showToast(error.getMsg());

				} else {
					ToastUtils.showToast("删除失败");
				}
				break;
			}

		};

	};

	@Override
	public String setCenterTitle() {
		return "社团成员";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_member;
	}

	@Override
	public void initView() {
		member_lv = (SwipeMenuListView) findViewById(R.id.member_lv);
		mAdapter = new AssociationMemberAdapter(this, mlist, mLeague);
		member_lv.setAdapter(mAdapter);
		member_lv.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,
					int index) {
				switch (index) {
				case 0:
					deletePosion = position;
					ModelMember member = (ModelMember) mAdapter.mList
							.get(position);
					member.setGid(mLeague.getGid());
					MemberOut(member);
					break;
				}
				return false;
			}
		});
	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {

	}

	/**
	 * 删除私信
	 * 
	 * @param msg
	 */
	private void MemberOut(final ModelMember member) {
		final LeagueIm leagueIm = mApp.getLeagueIm();
		mApp.getExecutor().execute(new Runnable() {

			@Override
			public void run() {
				Model model = leagueIm.memberOut(member);
				Message message = Message.obtain();
				message.obj = model;
				message.what = DELETE_SUCCESS;
				mHandle.sendMessage(message);
			}
		});
	}
}
