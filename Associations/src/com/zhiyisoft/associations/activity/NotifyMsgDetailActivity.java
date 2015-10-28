package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.NotifyMsgDetailAdapter;
import com.zhiyisoft.associations.api.Api.NotifyImpl;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.NotifyMsgListview;
import com.zhiyisoft.associations.model.ModelError;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DateUtil;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：下午5:32:12 类描述：这个类是实现私信类的详情，即聊天内容
 *
 */

public class NotifyMsgDetailActivity extends BaseActivity {

	private NotifyMsgListview lv_msg_detail;
	private RelativeLayout rl_message;
	private Button btn_send;
	private EditText et_message;
	private NotifyMsgDetailAdapter mAdapter;
	private ModelMsg mMsg;
	private MyTask mTask;

	private static final int SEND_MESSAGE = 1;
	private static final int GETOTHERMESSAGE = 2;
	private Handler mHandle = new Handler() {

		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case SEND_MESSAGE:
				ModelError data = (ModelError) msg.obj;
				if (data != null) {
					if (data.getStatus() == 1) {
						ModelMsg modelMsg = new ModelMsg();
						modelMsg.setmUser(mApp.getUser());
						modelMsg.setcTime(DateUtil.DateToStamp(new Date()));
						modelMsg.setContent(mMsg.getContent());
						mAdapter.mList.add(modelMsg);
						mAdapter.notifyDataSetChanged();
						lv_msg_detail.setSelection(lv_msg_detail.getAdapter()
								.getCount() - 1);
						et_message.setText("");
					} else {
						ToastUtils.showToast(data.getMsg());
					}
				} else {
					ToastUtils.showToast("发送失败");
				}
				break;
			case GETOTHERMESSAGE:
				List<ModelMsg> list = (List<ModelMsg>) msg.obj;
				if (list != null) {
					if (mAdapter.getCount() > 0) {
						List<ModelMsg> msgs = getNewMessage(list,
								(ModelMsg) mAdapter.mList.get(mAdapter.mList
										.size() - 1));
						if (msgs != null && msgs.size() > 0) {
							Log.i("OtherUidfd", "-----------------");
							mAdapter.mList.addAll(msgs);
							mAdapter.notifyDataSetChanged();
							lv_msg_detail.setSelection(lv_msg_detail
									.getAdapter().getCount() - 1);
						}
					}
				}

				break;
			}

		}

		/**
		 * 把第一页的信息筛选出来
		 * 
		 * @param list
		 * @param model
		 */
		private List<ModelMsg> getNewMessage(List<ModelMsg> list, ModelMsg msg) {
			List<ModelMsg> NewMessage = new ArrayList<ModelMsg>();
			if (list != null && msg != null) {
				String stamp = msg.getcTime();
				String MyUid = mApp.getUser().getUserid();
				for (int i = 0; i < list.size(); i++) {
					ModelMsg modelMsg = list.get(i);
					// 时间戳在大于最后一条的就视为新消息 切不为自己发送的，因为自己的发送的有专门的方法调用更新
					if (modelMsg.getcTime().compareTo(stamp) > 0
							&& !modelMsg.getUid().equals(MyUid)) {
						Log.i("OtherUid", modelMsg.getUid());
						NewMessage.add(modelMsg);
					}
				}
			}
			return NewMessage;
		}
	};

	@Override
	public String setCenterTitle() {
		return "私信";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mMsg = (ModelMsg) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_notify_msg_detail;
	}

	@Override
	public void initView() {
		lv_msg_detail = (NotifyMsgListview) findViewById(R.id.lv_msg_detail);
		rl_message = (RelativeLayout) findViewById(R.id.rl_message);
		btn_send = (Button) findViewById(R.id.btn_send);
		et_message = (EditText) findViewById(R.id.et_message);
		if (mMsg != null) {
			mAdapter = new NotifyMsgDetailAdapter(this, mMsg);
			lv_msg_detail.setAdapter(mAdapter);
		}
		mTask = new MyTask();
		mTask.start();
	}

	@Override
	public void initListener() {
		btn_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:
			String content = et_message.getText().toString();
			if (content == null || content.equals("")) {
				ToastUtils.showToast("发送信息不能为空");
				return;
			}
			if (mMsg != null) {
				mMsg.setContent(content);
				sendMessageToOther(mMsg);
			}
			break;

		}
	}

	/**
	 * 发送消息到对方
	 * 
	 * @param msg
	 */
	private void sendMessageToOther(final ModelMsg msg) {
		mApp.getExecutor().execute(new Runnable() {
			@Override
			public void run() {
				NotifyImpl notifyImpl = mApp.getNotifyIm();
				Model model = notifyImpl.sendMsg(mMsg);
				Message message = Message.obtain();
				message.what = SEND_MESSAGE;
				message.obj = model;
				mHandle.sendMessage(message);
			}
		});
	}

	private class MyTask extends Thread {
		private boolean isStop = false;

		@Override
		public void run() {
			NotifyImpl notifyImpl = mApp.getNotifyIm();
			ModelMsg msg = new ModelMsg();
			msg.setUid(mMsg.getUid());
			msg.setP(1);
			while (!isStop) {
				try {
					Thread.sleep(3 * 1000);
					List<Model> list = notifyImpl.notifyList(mMsg);
					Message message = Message.obtain();
					message.what = GETOTHERMESSAGE;
					message.obj = list;
					mHandle.sendMessage(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void setStop(boolean stop) {
			this.isStop = stop;
		}

	}

	@Override
	protected void onDestroy() {
		if (mTask != null) {
			mTask.setStop(true);
		}
		super.onDestroy();
	}
}
