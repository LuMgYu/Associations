package com.zhiyisoft.associations.util.localMusic;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

/**
 * author：qiuchunjia time：上午10:43:47 类描述：这个类是实现
 *
 */

public class MusicProvider {
	private Context context;

	public MusicProvider(Context context) {
		this.context = context;
	}

	public List<LocalMusic> getListMusic() {
		List<LocalMusic> list = new ArrayList<LocalMusic>();
		ContentResolver musicResolver = context.getContentResolver();
		Cursor musicCursor = musicResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		int musicColumnIndex;
		if (null != musicCursor && musicCursor.getCount() > 0) {
			for (musicCursor.moveToFirst(); !musicCursor.isAfterLast(); musicCursor
					.moveToNext()) {
				LocalMusic music = new LocalMusic();
				// 取得音乐播放路径
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
				String musicPath = musicCursor.getString(musicColumnIndex);
				// 取得音乐的名字
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
				String musicName = musicCursor.getString(musicColumnIndex);
				// 取得音乐的作者
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
				String musicArtist = musicCursor.getString(musicColumnIndex);
				music.setName(musicName);
				music.setMusicpath(musicPath);
				music.setAuthor(musicArtist);
				Log.i("musiclist", "music=" + music.toString());
				list.add(music);
			}
		}
		return list;

	}
}
