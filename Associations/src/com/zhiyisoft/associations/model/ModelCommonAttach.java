package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelCommonAttach extends Model {
	// "name":"560259119198d.jpg",
	// "savepath":"2015/0923/15/",
	// "url":"http://daxs.zhiyicx.com/attachment/uploads/2015/0923/15/560259119198d.jpg",
	// "hash":"24540161d82f4fa0e5438007ca7d6e0c"
	private String name;
	private String savepath;
	private String url;
	private String hash;

	public ModelCommonAttach() {

	}

	public ModelCommonAttach(JSONObject jsonObject) {
		try {
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("savepath")) {
				this.setSavepath(jsonObject.getString("savepath"));
			}
			if (jsonObject.has("url")) {
				this.setUrl(jsonObject.getString("url"));
			}
			if (jsonObject.has("hash")) {
				this.setHash(jsonObject.getString("hash"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
