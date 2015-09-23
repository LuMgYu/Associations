package com.zhiyisoft.associations.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.UploadPhotoGridViewAdapter;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelUser;
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

	private List<String> mPhotos = new ArrayList<String>();
	private ModelLeague mLeague;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setAlltitle(null, null, "上传");
	}

	@Override
	public void onClick(View v) {

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
			mLeague = (ModelLeague) bundle.get(Config.SEND_ACTIVITY_DATA);
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
	}

	@Override
	public void initListener() {
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

	private void uploadPhoto(ModelUser user) {
		RequestParams params = new RequestParams();
		params.put(Api.oauth_token, user.getOauth_token());
		params.put(Api.oauth_token_secret, user.getOauth_token_secret());
		File file = user.getUploadFile();
		if (file != null) {
			try {
				params.put("file", user.getUploadFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=facepic",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						try {
							JSONObject jsonObject = new JSONObject(arg1);
							if (jsonObject.has("data")) {
								JSONObject data = jsonObject
										.getJSONObject("data");
								if (data.has("id")) {
//									mPhotoid = data.getString("id");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
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
