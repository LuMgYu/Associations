package com.zhiyisoft.associations.impl;

/**
 * author：qiuchunjia time：下午3:01:30 接口描述：这个接口是用来表示下拉刷新，
 *
 * 上拉加载更多，隱藏headView，footview等 主要是用在listview里面的
 */

public interface RefreshListener {
	void headerShow();

	void headerHiden();

	void headerRefresh();

	void footerShow();

	void footerHiden();

}
