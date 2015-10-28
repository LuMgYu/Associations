package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.NotifyMsgUserInforActivity;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.NotifyImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：下午5:36:20 类描述：这个类是实现
 *
 */

public class NotifyMsgDetailAdapter extends BAdapter {
	private ModelMsg mMsg;
	private ModelUser mUser; // 用于判断聊天显示左边还是右边

	public NotifyMsgDetailAdapter(BaseActivity activity, ModelMsg data) {
		super(activity, null);
		this.mMsg = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = initView(holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bundleDataToView(holder, position);
		return convertView;
	}

	private View initView(ViewHolder holder) {
		View view = mInflater.inflate(R.layout.msg_chat_item, null);
		if (holder != null) {
			// 左边聊天界面
			holder.rl_left_chat = (RelativeLayout) view
					.findViewById(R.id.rl_left_chat);
			holder.iv_left_icon = (RoundImageView) view
					.findViewById(R.id.iv_left_icon);
			holder.tv_left_name = (TextView) view
					.findViewById(R.id.tv_left_name);
			holder.tv_left_time = (TextView) view
					.findViewById(R.id.tv_left_time);
			holder.tv_left_content = (TextView) view
					.findViewById(R.id.tv_left_content);

			// 右边边聊天界面
			holder.rl_right_chat = (RelativeLayout) view
					.findViewById(R.id.rl_right_chat);
			holder.iv_right_icon = (RoundImageView) view
					.findViewById(R.id.iv_right_icon);
			holder.tv_right_time = (TextView) view
					.findViewById(R.id.tv_right_time);
			holder.tv_right_name = (TextView) view
					.findViewById(R.id.tv_right_name);
			holder.tv_right_content = (TextView) view
					.findViewById(R.id.tv_right_content);
		}
		return view;
	}

	private void bundleDataToView(ViewHolder holder, int pos) {
		// 判断是自己发的还是他人发的
		if (mUser == null) {
			mUser = mApp.getUser();
		}
		if (mUser != null) {
			ModelMsg msg = (ModelMsg) mList.get(pos);
			ModelUser user = msg.getmUser();
			if (user != null) {
				// 显示在右边
				if (mUser.getUserid().equals(user.getUserid())) {
					holder.rl_left_chat.setVisibility(View.GONE);
					holder.rl_right_chat.setVisibility(View.VISIBLE);
					mApp.displayImage(user.getFaceurl(), holder.iv_right_icon);
					holder.tv_right_name.setText(user.getUname());
					holder.tv_right_time.setText(DateUtil.stamp2humanDate(msg
							.getcTime()));
					holder.tv_right_content.setText(msg.getContent());
				} else {
					// 显示在左边
					holder.rl_left_chat.setVisibility(View.VISIBLE);
					holder.rl_right_chat.setVisibility(View.GONE);
					mApp.displayImage(user.getFaceurl(), holder.iv_left_icon);
					holder.tv_left_name.setText(user.getUname());
					holder.tv_left_time.setText(DateUtil.stamp2humanDate(msg
							.getcTime()));
					holder.tv_left_content.setText(msg.getContent());
					holder.iv_left_icon.setTag(user);
					holder.iv_left_icon
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									ModelUser user = (ModelUser) v.getTag();
									Bundle data = new Bundle();
									data.putSerializable(
											Config.SEND_ACTIVITY_DATA, user);
									mApp.startActivity(mBaseActivity,
											NotifyMsgUserInforActivity.class,
											data);
								}
							});
				}
			}
		}

	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = getMsg(mMsg, null);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getMsg(mMsg, (ModelMsg) item);
		isgetMore = true;
		return items;

	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		return null;
	}

	private boolean isgetMore = false;

	@Override
	public void addHeadList(List<Model> list) {
		super.addHeadList(list);
		if (!isgetMore) {
			mListView.setSelection(getCount() - 1); // 显示在底部
		} else {
			if (list != null) {
				mListView.setSelection(list.size()); // 上拉成功后，显示到当前文字
			}
		}
	}

	/**
	 * 获取消息
	 * 
	 * @param msg
	 * @return
	 */
	private List<Model> getMsg(ModelMsg msg, ModelMsg data) {
		if (msg != null) {
			if (data != null) {
				mMsg.setNotifyId(data.getNotifyId());
			}
			NotifyImpl impl = mApp.getNotifyIm();
			List<Model> items = impl.notifyList(msg);
			return items;
		}
		return null;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
