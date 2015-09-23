package com.zhiyisoft.associations.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeagueAlbum extends Model {
	// "id":"972",
	// "uid":"0",
	// "maskId":"176594",
	// "maskName":"",
	// "gid":"15311",
	// "name":"fdfaf",
	// "info":"fdsfadgagadgad",
	// "cTime":"1442923341",
	// "imgsrcL":"http://daxs.zhiyicx.com/public/themes/default/images/user_gruop.gif",
	private String id;
	private String maskId;
	private String gid;
	private String name;
	private String info;
	private String cTime;
	private String imgsrcL;
	private String photoCount;

	public ModelLeagueAlbum() {

	}

	public ModelLeagueAlbum(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {
				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("maskId")) {
				this.setMaskId(jsonObject.getString("maskId"));
			}
			if (jsonObject.has("gid")) {
				this.setGid(jsonObject.getString("gid"));
			}
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("info")) {
				this.setInfo(jsonObject.getString("info"));
			}
			if (jsonObject.has("cTime")) {
				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("imgsrcL")) {
				this.setImgsrcL(jsonObject.getString("imgsrcL"));
			}
			if (jsonObject.has("photoCount")) {
				this.setPhotoCount(jsonObject.getString("photoCount"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaskId() {
		return maskId;
	}

	public void setMaskId(String maskId) {
		this.maskId = maskId;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getImgsrcL() {
		return imgsrcL;
	}

	public void setImgsrcL(String imgsrcL) {
		this.imgsrcL = imgsrcL;
	}

	public String getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(String photoCount) {
		this.photoCount = photoCount;
	}

}
