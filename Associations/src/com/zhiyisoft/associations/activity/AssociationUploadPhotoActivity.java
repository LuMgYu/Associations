package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.umeng.socialize.utils.Log;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.UploadPhotoGridViewAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:49:20 类描述：这个类是实现照片上传
 *
 */

public class AssociationUploadPhotoActivity extends BaseActivity {
	private EditText photo_detail;
	private RelativeLayout rl_album_location;
	private TextView tv_album_location;
	private GridView gv_fill_photo;
	public final static String ADDPHOTO = "addphoto";
	private UploadPhotoGridViewAdapter mAdapter;
	private ProgressBar progressBar1;
	private TextView tv_progress;
	private FrameLayout fl_progress;

	private List<String> mPhotos = new ArrayList<String>();
	private ModelLeagueAlbum mAlbum;
	private ModelUser mUser;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "上传");
	}

	@Override
	public String setCenterTitle() {
		// TODO Auto-generated method stub
		return "上传照片";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mAlbum = (ModelLeagueAlbum) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_uploadphoto;
	}

	@Override
	public void initView() {
		mPhotos.add(ADDPHOTO);
		photo_detail = (EditText) findViewById(R.id.photo_detail);
		rl_album_location = (RelativeLayout) findViewById(R.id.rl_album_location);
		tv_album_location = (TextView) findViewById(R.id.tv_album_location);
		gv_fill_photo = (GridView) findViewById(R.id.gv_fill_photo);
		mAdapter = new UploadPhotoGridViewAdapter(mPhotos, this);
		gv_fill_photo.setAdapter(mAdapter);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_progress = (TextView) findViewById(R.id.tv_progress);
		fl_progress = (FrameLayout) findViewById(R.id.fl_progress);
		mUser = mApp.getUser();
	}

	@Override
	public void initListener() {
		tv_title_right.setOnClickListener(this);
		gv_fill_photo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = (ViewHolder) view.getTag();
				ImageView imageView = holder.iv_upload_photo;
				String str = (String) imageView.getTag();
				if (str.equals(ADDPHOTO)) {
					mApp.startActivityForResult(
							AssociationUploadPhotoActivity.this,
							LocalImagListActivity.class, null);
				}

			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_right:
			String infro = photo_detail.getText().toString();
			if (infro != null && infro.length() > 0) {
				mAlbum.setInfo(infro);
				uploadPhotos(mAlbum);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 上传多张照片
	 * 
	 * @param user
	 */
	private void uploadPhotos(ModelLeagueAlbum album) {
		fl_progress.setVisibility(View.VISIBLE);
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, mUser.getOauth_token());
		params.put(Api.oauth_token_secret, mUser.getOauth_token_secret());
		params.put("gid", album.getGid());
		params.put("albumId", album.getId());
		params.put("info", album.getInfo());
		Log.i("param", params.toString() + "");
		for (int i = 0; i < mPhotos.size() - 1; i++) {
			Log.i("uploadpath", mPhotos.get(i) + "");
			File file = new File(mPhotos.get(i));
			try {
				params.put("file" + i, file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=groupphoto",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgress(long bytesWritten, long totalSize) {
						super.onProgress(bytesWritten, totalSize);
						culcuteProgress(bytesWritten, totalSize);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						try {
							JSONObject jsonObject = new JSONObject(result);
							Log.i("zhedu", jsonObject.toString());
							if (jsonObject.has("status")) {
								int status = jsonObject.getInt("status");
								if (status == 1) {
									ToastUtils.showToast("上传成功！");
									onBackPressed();
								} else {
									ToastUtils.showToast("上传失败！请稍后再试");
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

				});
	}

	private void culcuteProgress(long bytesWritten, long totalSize) {
		float progress = ((float) bytesWritten / (float) totalSize) * 100;
		DecimalFormat df = new DecimalFormat("0");// 格式化小数
		String s = df.format(progress);// 返回的是String类型
		tv_progress.setText(s + "%");
		if (s.equals("100")) {
			fl_progress.setVisibility(View.GONE);
		}
	}

	// -------------------------------------------------------
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ArrayList<String> list = (ArrayList<String>) bundle
					.get(Config.GET_ACTIVITY_DATA);
			if (list != null) {
				// TODO 这里的list路径，以后还要靠这个来上传图片呢，接口写出来后就调用这里
				mPhotos.addAll(list);
				replaceLocation(ADDPHOTO, mPhotos);
			}
		}
	}

	/**
	 * 把添加图片的按钮设置放置到最后
	 * 
	 * @param path
	 * @param list
	 */
	private void replaceLocation(String path, List<String> list) {
		String addphoto = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(ADDPHOTO)) {
				addphoto = list.remove(i);
			}
		}
		list.add(addphoto);
		mAdapter = new UploadPhotoGridViewAdapter(list, this);
		gv_fill_photo.setAdapter(mAdapter);
	}
}
