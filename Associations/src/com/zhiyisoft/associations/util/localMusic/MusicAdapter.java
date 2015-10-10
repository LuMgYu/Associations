package com.zhiyisoft.associations.util.localMusic;

import java.util.List;

import com.zhiyisoft.associations.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * author：qiuchunjia time：上午11:18:40 类描述：这个类是实现
 *
 */

public class MusicAdapter extends BaseAdapter {
	private Context mContext;
	private List<LocalMusic> mMusiclist;
	private LayoutInflater mInflater;

	public MusicAdapter(Context context, List<LocalMusic> musics) {
		this.mContext = context;
		this.mMusiclist = musics;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mMusiclist.size();
	}

	@Override
	public Object getItem(int position) {
		return mMusiclist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.local_music_listview_item,
					null);
			holder.tv_music_name = (TextView) convertView
					.findViewById(R.id.tv_music_name);
			holder.tv_music_author = (TextView) convertView
					.findViewById(R.id.tv_music_author);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		bindDataToView(position, holder);
		return convertView;
	}

	/**
	 * 把数据绑定到界面上
	 * 
	 * @param position
	 * @param holder
	 */
	private void bindDataToView(int position, ViewHolder holder) {
		if (holder != null && mMusiclist != null) {
			LocalMusic music = mMusiclist.get(position);
			holder.tv_music_author.setText(music.getAuthor());
			holder.tv_music_name.setText(music.getName());
		}
	}

	private class ViewHolder {
		public TextView tv_music_name;
		public TextView tv_music_author;
	}

}
