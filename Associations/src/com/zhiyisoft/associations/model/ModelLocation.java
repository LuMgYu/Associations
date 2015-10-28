package com.zhiyisoft.associations.model;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:05:37 类描述：这个类是实现
 *
 */

public class ModelLocation extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private double latitude;
	private double longtitude;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ModelLocation [address=" + address + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + "]";
	}

	
	
}
