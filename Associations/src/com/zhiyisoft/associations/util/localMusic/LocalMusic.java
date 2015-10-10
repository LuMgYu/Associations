package com.zhiyisoft.associations.util.localMusic;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:45:37 类描述：这个类是实现
 *
 */

public class LocalMusic extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String musicpath;
	private String author;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMusicpath() {
		return musicpath;
	}

	public void setMusicpath(String musicpath) {
		this.musicpath = musicpath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "LocalMusic [name=" + name + ", musicpath=" + musicpath
				+ ", author=" + author + "]";
	}

}
