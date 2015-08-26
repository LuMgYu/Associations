package com.zhiyisoft.associations.util;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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
	/******************************** 社团一级页面的控件 ********************************************/
	public LinearLayout school_ll;
	public RelativeLayout school_rl_change;
	public ImageView school_iv_change;
	public TextView school_tv;
	/******************************** 社团一级页面的控件end ********************************************/
	/******************************** 活动一级页面的控件 ********************************************/
	public LinearLayout move_ll;
	public ImageView move_iv_zoom;
	public EditText move_et_zoom;
	public TextView move_arround_tv;
	public TextView move_my_tv;
	public TextView tv_bottom_line;
	/******************************** 活动一级页面的控件end ********************************************/
	/******************************** 单个社团控件 ********************************************/
	public RoundImageView title_iv;
	public TextView title_tv;
	public TextView title_tv_member;
	public TextView title_tv_topic;
	public TextView title_tv_school;
	public TextView title_tv_type;
	public TextView title_tv_move;
	public RelativeLayout title_rl_move;

	/******************************** 单个社团end ********************************************/
	/******************************** 单个社团item单个控件 ********************************************/
	public RelativeLayout new_item_rl_head;
	public RoundImageView new_item_iv;
	public TextView new_item_tv_nick;
	public RelativeLayout new_item_rl_content;
	public TextView new_item_tv_title;
	public TextView new_item_tv_content;
	public LinearLayout new_item_ll;
	public SmartImageView imageView1;
	public SmartImageView imageView2;
	public SmartImageView imageView3;
	public RelativeLayout new_item_rl_footer;
	public TextView new_item_tv_date;
	public TextView new_item_tv_number;

	/******************************** 单个社团item单个控件end ********************************************/
	/******************************** 活动作品展示 ********************************************/
	// 文章部分
	public RoundImageView iv_essay_user_icon;
	// public TextView tv_user_name; 因为命名重复了就注释掉这部分
	// public TextView tv_user_send;
	public TextView tv_essay_title;
	public TextView tv_essay_content;
	public TextView tv_essay_date;
	public TextView tv_essay_commit;
	// 音频部分
	public RoundImageView iv_music_user_icon;
	// public TextView tv_user_name;
	// public TextView tv_user_send; 因为命名重复了就注释掉这部分
	public TextView tv_music_name;
	public TextView tv_music_date;
	public TextView tv_music_commit;
	// 图片部分
	public RoundImageView iv_photo_user_icon;
	public TextView tv_user_name;
	public TextView tv_user_send;
	public TextView tv_photo_title;
	public SmartImageView iv_photo1;
	public SmartImageView iv_photo2;
	public SmartImageView iv_photo3;
	public TextView tv_photo_date;
	public TextView tv_photo_commit;
	// 视频部分
	public RoundImageView iv_vedio_user_icon;
	// public TextView tv_user_name; 因为命名重复了就注释掉这部分
	// public TextView tv_user_send;
	public TextView tv_vedio_title;
	public SmartImageView iv_vedio;
	public ImageView iv_vedio_click;
	public TextView tv_vedio_date;
	public TextView tv_vedio_commit;

	/******************************** 活动作品展示end ********************************************/
}
