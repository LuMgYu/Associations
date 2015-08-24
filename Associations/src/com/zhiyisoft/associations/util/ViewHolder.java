package com.zhiyisoft.associations.util;

import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.widget.CircleFlowIndicator;
import com.zhiyisoft.associations.widget.ViewFlow;

/**
 * author：qiuchunjia time：下午2:49:17 类描述：这个类是实现
 *
 */

public class ViewHolder {
	/******************************** home item的各种控件 ********************************************/
	public RelativeLayout home_rl_ads;
	public RelativeLayout home_rl_my_association;
	public TextView tv_my_association;
	public HorizontalScrollView hsv_association;
	public LinearLayout ll_association;
	public LinearLayout home_ll_move;
	public Button btn_all_move;
	public Button btn_around_move;
	public LinearLayout ll_association1;
	public RoundImageView iv_association1;
	public TextView tv_association1;
	public LinearLayout ll_association2;
	public RoundImageView iv_association2;
	public TextView tv_association2;
	public LinearLayout ll_association3;
	public RoundImageView iv_association3;
	public TextView tv_association3;
	public LinearLayout ll_association4;
	public RoundImageView iv_association4;
	public TextView tv_association4;
	public LinearLayout ll_hotMove;
	public LinearLayout ll_works;
	public SmartImageView iv_work1;
	public SmartImageView iv_work2;
	public SmartImageView iv_work3;
	public LinearLayout ll_news;
	// 热门活动的item
	public View mHotViewItem;
	// 新鲜事的item
	public View mNewsViewItem;
	// 广告栏
	public ViewFlow mhome_viewflow;
	public CircleFlowIndicator mhome_viewflowindicator;
	/******************************** home item的各种控件 ********************************************/
	/******************************** 作品的照片的各种控件 ********************************************/
	public RoundImageView iv_photo_user_icon;
	public TextView tv_user_name;
	public TextView tv_user_send;
	public TextView tv_photo_title;
	public RoundImageView iv_photo1;
	public TextView iv_photo2;
	public LinearLayout iv_photo3;
	public LinearLayout tv_photo_date;
	public SmartImageView tv_photo_commit;
	/******************************** 作品的照片的各种控件end ********************************************/

}
