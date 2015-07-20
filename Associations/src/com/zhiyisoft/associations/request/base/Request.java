package com.zhiyisoft.associations.request.base;

/***********************************************************************
 * Module:  Request.java
 * Author:  Administrator
 * Purpose: Defines the Class Request
 ***********************************************************************/

/** 定制網絡請求 */
public abstract class Request {
	/** host的基本地址 */
	private String mHostUrl;

	/**
	 * @param name
	 *            鍵值對
	 * @param value
	 * @pdOid 添加键值对，并且返回request对象
	 */
	public abstract Request appendParam(String name, Object value);

	/** 把mhosturl与键值对相结合返回的完成url */
	public abstract String combineTheCompleteUrl();

	/** 獲取網絡數據，并返回object類型，方便具體用時，具體解析 */
	public Object run() {
		String url = combineTheCompleteUrl();
		// TODO 通过网络请求数据
		
		
		return null;
	}

	/** 过滤一下请求地址 */
	public void filterTheUrl(String url) {
		// TODO: implement
	}

	/** 獲取主機地址，注意：主機地址放在配置文件里面，方便修改 */
	public String getTheHostUrl() {
		// TODO: 通过application获取host地址
		return null;
	}

}