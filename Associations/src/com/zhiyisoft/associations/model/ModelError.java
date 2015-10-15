package com.zhiyisoft.associations.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：上午11:19:30 类描述：这个类是实现
 *
 */

public class ModelError extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;

	public ModelError() {

	}

	public ModelError(JSONObject jsonObject) {
		try {
			if (jsonObject.has("status")) {
				this.setStatus(jsonObject.getInt("status"));
			}
			if (jsonObject.has("msg")) {
				this.setMsg(jsonObject.getString("msg"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
