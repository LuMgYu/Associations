/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhiyisoft.associations.activity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.config.Config;

public class VideoViewDemo extends Activity {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media
	 * file path.
	 */
	private String path = "http://daxs.zhiyicx.com/attachment/uploads/2015/1014/13/561de34cb1731.mp4";
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.videoview);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			path = bundle.getString(Config.SEND_ACTIVITY_DATA);
		}
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		if (path == "") {
			Toast.makeText(
					VideoViewDemo.this,
					"Please edit VideoViewDemo Activity, and set path"
							+ " variable to your media file URL/path",
					Toast.LENGTH_LONG).show();
			return;
		} else {
			mVideoView.setVideoPath(path);
			mVideoView.setMediaController(new MediaController(this));
			mVideoView.requestFocus();

			mVideoView
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							mediaPlayer.setPlaybackSpeed(1.0f);
						}
					});
		}

	}

}
