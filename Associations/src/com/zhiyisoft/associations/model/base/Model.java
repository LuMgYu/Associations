package com.zhiyisoft.associations.model.base;

import java.io.Serializable;

/**
 * author：qiuchunjia time：下午2:58:40 类描述：这个类是实现
 *
 */

public class Model implements Serializable {
	private int p; // 这个p用于数据分页

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
