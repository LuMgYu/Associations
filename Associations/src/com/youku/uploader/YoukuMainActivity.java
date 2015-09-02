package com.youku.uploader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyisoft.associations.R;

public class YoukuMainActivity extends Activity implements OnClickListener {

	private ProgressBar progressBar;

	private TextView percent;
	private VideoProvider videoProvider;
	private YoukuUploader uploader;
	private String TAG = "YoukuMainActivity";
	private String code;

	private String redirect_uri;
	private String CLIENT_ID = "25a61d30d14c2216";
	private String CLIENT_SECRET = "3baaef6605adf1d98373596c9a1e41a1";
	private String access_token;
	private String userName = "13688449697";
	private String userPwd = "qiuchunjia";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youku_main);
		Intent in = getIntent();
		code = in.getStringExtra("code");
		access_token = in.getStringExtra("access_token");
		Log.d(TAG, "token =" + access_token);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		percent = (TextView) findViewById(R.id.percent);
		uploader = YoukuUploader.getInstance(CLIENT_ID, CLIENT_SECRET,
				getApplicationContext());
		bindEvents();
		videoProvider = new VideoProvider(this);

	}

	private void bindEvents() {
		findViewById(R.id.upload).setOnClickListener(this);
		findViewById(R.id.cancel).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.upload:

			Thread tt = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("username", userName);
					params.put("password", userPwd);
					// 这里需要将access_token设置到参数中
					params.put("access_token", access_token);
					Log.d(TAG, "access_token=" + access_token);
					HashMap<String, String> uploadInfo = new HashMap<String, String>();
					uploadInfo.put("title", "小视频");
					uploadInfo.put("tags", "原创");
					// uploadInfo.put("file_name",
					// "/mnt/sdcard2/download/dota2.mp4");
					List<Video> list = videoProvider.getList();
					if (list != null) {
						uploadInfo.put("file_name", list.get(0).getPath());
						Log.d(TAG, list.get(0).getPath());
					} else {
						Log.d(TAG, "list为空");
					}

					Log.d(TAG, "params=" + params + "uploadInfo " + uploadInfo
							+ " file_name "
							+ uploadInfo.get("file_name").toString());
					uploader.upload(params, uploadInfo,
							new IUploadResponseHandler() {

								@Override
								public void onStart() {
									Log.v(TAG, "onStart");
									progressBar.setProgress(0);
									percent.setText("等待中");
								}

								@Override
								public void onSuccess(JSONObject response) {
									Log.v(TAG,
											"onSuccess " + response.toString());
									String respStr = response.toString()
											.substring(
													13,
													response.toString()
															.length() - 2);
									String str = String
											.format("http://v.youku.com/v_show/id_%s.html?from=y1.7-1.2",
													respStr);

									Log.v(TAG, "onSuccess " + " the uri is :"
											+ str);
									Toast.makeText(getApplicationContext(),
											"response.toString()", 0).show();
								}

								@Override
								public void onProgressUpdate(int counter) {
									Log.v(TAG, "onProgressUpdate" + counter
											+ "");
									progressBar.setProgress(counter);
									percent.setText(counter + "%");
								}

								@Override
								public void onFailure(JSONObject errorResponse) {
									Log.v(TAG,
											"onFailure"
													+ errorResponse.toString());
								}

								@Override
								public void onFinished() {
									Log.v(TAG, "onFinished");
									percent.setText("完成");
								}
							});
				}
			});
			tt.start();
			break;

		case R.id.cancel:
			if (uploader.cancel()) {
				progressBar.setProgress(0);
				percent.setText("");
			}
			break;
		}
	}
}