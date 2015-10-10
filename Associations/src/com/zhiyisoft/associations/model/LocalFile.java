package com.zhiyisoft.associations.model;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:29:58 类描述：这个类是实现
 *
 */

public class LocalFile extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String filepath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
