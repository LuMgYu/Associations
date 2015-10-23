package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

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
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class NotifyNfyAdapter extends BAdapter {
	private View mView;
	float x, ux;
	private TextView mCurdelTv;

	public NotifyNfyAdapter(BaseActivity activity, List<Model> list) {
		super(activity, list);
	}

	public NotifyNfyAdapter(BaseFragment fragment, List<Model> list) {
		super(fragment, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.notify_notify_iem, null);
			mView = convertView;
			initView(mView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bindDataToView(position, holder);
		return convertView;
	}

	private void bindDataToView(int position, ViewHolder holder) {
		if (holder != null) {
			ModelNotify notify = (ModelNotify) mList.get(position);
			if (notify != null) {
				holder.tv_msg.setText(notify.getContent());
			}

		}
	}

	private void initView(View view, ViewHolder holder) {
		if (view != null) {
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
	}

	@Override
	public List<Model> refreshNew() {
		List<Model> items = getNotify(1);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = getNotify(1);
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		p++;
		List<Model> items = getNotify(p);
		return items;
	}

	private List<Model> getNotify(int index) {
		ModelNotify notify = new ModelNotify();
		notify.setP(index);
		List<Model> items = new ArrayList<Model>();
		NotifyImpl notifyImpl = mApp.getNotifyIm();
		items = notifyImpl.notifyList(notify);
		return items;
	}

	@Override
	public void addHeadList(List<Model> list) {
		addHeadListWay2(list);
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
