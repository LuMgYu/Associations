package com.zhiyisoft.associations.util.localMusic;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:56:47 类描述：这个类是实现
 *
 */

public class LocalMusicListActivity extends BaseActivity {
	private ListView lv_music;
	MusicAdapter mMusicAdapter;
	private List<LocalMusic> mListMusic = new ArrayList<LocalMusic>();

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {
		return "本地音乐";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.local_music_activity;
	}

	@Override
	public void initView() {
		lv_music = (ListView) findViewById(R.id.lv_music);
		MusicProvider provider = new MusicProvider(this);
		mListMusic = provider.getListMusic();
		mMusicAdapter = new MusicAdapter(this, mListMusic);
		lv_music.setAdapter(mMusicAdapter);
		lv_music.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Model music = mListMusic.get(position);
				onReturnResult(music, Config.LOCALMUSIC);
				onBackPressed();
			}
		});
	}

	@Override
	public void initListener() {

	}

}
