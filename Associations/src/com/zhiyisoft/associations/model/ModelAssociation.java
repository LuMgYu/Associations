package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:52:43
 * 
 * 类描述：这个类是实现 社团的 javabean
 *
 */
/**
 * "id":"291936", "gid":"15222", "uid":"6309289", "maskId":"176592",
 * "maskName":"我是小腿", "status":"1", "level":"1", "cTime":"1438327847",
 * "mTime":"1438327847", "isOut":"0",
 * "maskFace":"http://daxs.zhiyicx.com/public/images/user_pic.gif",
 * "credit_score":0, "isTrueName":"1", "img_src":null
 * 
 * */
public class ModelAssociation extends Model {
	private String id;
	private String gid;
	private String uid;
	private String maskId;
	private String maskName;
	private String status;
	private String level;
	private String cTime;
	private String mTime;
	private String isOut;
	private String maskFace;
	private String credit_score;
	private String isTrueName;
	private String img_src;

	public ModelAssociation() {

	}

	public ModelAssociation(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {

				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("gid")) {
				this.setGid(jsonObject.getString("gid"));
			}
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("maskId")) {
				this.setMaskId(jsonObject.getString("maskId"));
			}
			if (jsonObject.has("maskName")) {
				this.setMaskName(jsonObject.getString("maskName"));
			}
			if (jsonObject.has("status")) {
				this.setStatus(jsonObject.getString("status"));
			}
			if (jsonObject.has("level")) {
				this.setLevel(jsonObject.getString("level"));
			}
			if (jsonObject.has("cTime")) {
				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("mTime")) {
				this.setmTime(jsonObject.getString("mTime"));
			}
			if (jsonObject.has("isOut")) {
				this.setIsOut(jsonObject.getString("isOut"));
			}
			if (jsonObject.has("maskFace")) {
				this.setMaskFace(jsonObject.getString("maskFace"));
			}
			if (jsonObject.has("credit_score")) {
				this.setCredit_score(jsonObject.getString("credit_score"));
			}
			if (jsonObject.has("isTrueName")) {
				this.setIsTrueName(jsonObject.getString("isTrueName"));
			}
			if (jsonObject.has("img_src")) {
				this.setImg_src(jsonObject.getString("img_src"));
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

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMaskId() {
		return maskId;
	}

	public void setMaskId(String maskId) {
		this.maskId = maskId;
	}

	public String getMaskName() {
		return maskName;
	}

	public void setMaskName(String maskName) {
		this.maskName = maskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public String getIsOut() {
		return isOut;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

	public String getMaskFace() {
		return maskFace;
	}

	public void setMaskFace(String maskFace) {
		this.maskFace = maskFace;
	}

	public String getCredit_score() {
		return credit_score;
	}

	public void setCredit_score(String credit_score) {
		this.credit_score = credit_score;
	}

	public String getIsTrueName() {
		return isTrueName;
	}

	public void setIsTrueName(String isTrueName) {
		this.isTrueName = isTrueName;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	@Override
	public String toString() {
		return "ModelAssociation [id=" + id + ", gid=" + gid + ", uid=" + uid
				+ ", maskId=" + maskId + ", maskName=" + maskName + ", status="
				+ status + ", level=" + level + ", cTime=" + cTime + ", mTime="
				+ mTime + ", isOut=" + isOut + ", maskFace=" + maskFace
				+ ", credit_score=" + credit_score + ", isTrueName="
				+ isTrueName + ", img_src=" + img_src + "]";
	}

}
