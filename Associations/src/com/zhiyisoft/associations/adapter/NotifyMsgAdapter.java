package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.NotifyImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class NotifyMsgAdapter extends BAdapter {

	public NotifyMsgAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public NotifyMsgAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
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
		bindDataToView(holder, position);
		return convertView;
	}

	private View initView(ViewHolder holder) {
		View view = mInflater.inflate(R.layout.notify_notify_iem, null);
		if (holder != null) {
			holder.rl_notify = (RelativeLayout) view
					.findViewById(R.id.rl_notify);
			holder.fl_icon = (FrameLayout) view.findViewById(R.id.fl_icon);
			holder.iv_icon = (RoundImageView) view.findViewById(R.id.iv_icon);
			holder.iv_remind = (ImageView) view.findViewById(R.id.iv_remind);
			holder.tv_nick = (TextView) view.findViewById(R.id.tv_nick);
			holder.tv_msg = (TextView) view.findViewById(R.id.tv_msg);
			holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
			holder.tv_del = (TextView) view.findViewById(R.id.tv_del);
		}
		return view;
	}

	private void bindDataToView(ViewHolder holder, int position) {
		if (holder != null) {
			ModelMsg msg = (ModelMsg) mList.get(position);
			if (msg != null) {
				Log.i("msg", msg.toString());
				mApp.displayImage(msg.getmUser().getFaceurl(), holder.iv_icon);
				// String type = msg.getIsRead();
				// if (type.equals("0")) {
				// // holder.iv_remind
				// } else {
				// // holder.iv_remind
				// }
				holder.tv_nick.setText(msg.getmUser().getUname());
				holder.tv_msg.setText(msg.getContent());
				holder.tv_date.setText(DateUtil.strTodate(msg.getcTime()));
			}
		}
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = getWallList(1);
		return items;
	}

	private List<Model> getWallList(int index) {
		ModelMsg msg = new ModelMsg();
		msg.setP(index);
		List<Model> items = new ArrayList<Model>();
		NotifyImpl notifyImpl = mApp.getNotifyIm();
		items = notifyImpl.wallList(msg);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getWallList(1);
		return items;
	}

	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay2(list);
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		return null;
	}

	@Override
	public int getTheCacheType() {
		return 0;
	}

}
