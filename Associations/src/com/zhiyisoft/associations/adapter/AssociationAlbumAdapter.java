package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.LeagueImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/**
 * author：qiuchunjia time：上午10:47:11
 * 
 * 类描述：这个类是实现
 *
 */

public class AssociationAlbumAdapter extends BAdapter {
	private View mView;
	private ModelLeague mLeague;

	public AssociationAlbumAdapter(BaseActivity activity, List<Model> list,
			ModelLeague league) {
		super(activity, list);
		this.mLeague = league;
	}

	public AssociationAlbumAdapter(BaseFragment fragment, List<Model> list,
			ModelLeague league) {
		super(fragment, list);
		this.mLeague = league;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mView = mInflater.inflate(R.layout.association_album_item, null);
			convertView = mView;
			convertView.setTag(mHolder);
			initView();
		} else {
			mHolder = (ViewHolder) convertView.getTag();
			// TODO 获取list里面的数据，然后添加数据
		}
		bundledataToView(position, mHolder);
		return convertView;
	}

	/**
	 * 绑定数据到item
	 * 
	 * @param position
	 * @param mHolder
	 */
	private void bundledataToView(int position, ViewHolder holder) {
		ModelLeagueAlbum album = (ModelLeagueAlbum) mList.get(position);
		// TODO 把数据绑定到界面
		holder.album_iv.setImageUrl(album.getImgsrcL());
		holder.album_tv_name.setText(album.getName());
		holder.album_tv_count.setText(album.getPhotoCount());
		String originTime = album.getcTime();
//		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
//		Date date = new Date(originTime);
//		String time = format.format(date);
		holder.album_tv_date.setText(originTime);

	}

	private void initView() {
		if (mView != null) {
			mHolder.album_iv = (SmartImageView) mView
					.findViewById(R.id.album_iv);
			mHolder.album_tv_name = (TextView) mView
					.findViewById(R.id.album_tv_name);
			mHolder.album_tv_count = (TextView) mView
					.findViewById(R.id.album_tv_count);
			mHolder.album_tv_date = (TextView) mView
					.findViewById(R.id.album_tv_date);
		}
	}

	// -------------------------------------------------
	@Override
	public List<Model> refreshNew() {
		LeagueImpl leagueImpl = mApp.getLeagueIm();
		List<Model> items = leagueImpl.albumList(mLeague);
		return items;
	}

	@Override
	public List<Model> refreshHeader(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		// items.add(new Model());
		// items.add(new Model());
		// items.add(new Model());
		return items;
	}

	@Override
	public List<Model> refreshFooter(Model item, int count) {
		List<Model> items = new ArrayList<Model>();
		// items.add(new Model());
		// items.add(new Model());
		// items.add(new Model());
		return items;
	}

	@Override
	public int getTheCacheType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
