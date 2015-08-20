package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeagueMember extends Model {
	/*
	 * ""id":"291940",
	 * 
	 * "gid":"15260",
	 * 
	 * "uid":"6309284",
	 * 
	 * "maskId":"176593",
	 * 
	 * "maskName":"Skill",
	 * 
	 * "status":"1",
	 * 
	 * "level":"1",
	 * 
	 * "cTime":"1438765245", "mTime":"1438765245", "isOut":"0",
	 * 
	 * "maskFace":"http:\/\/daxs.zhiyicx.com\/public\/images\/user_pic.gif",
	 * 
	 * "credit_score":0, "isTrueName":"1"
	 */

	private String id;
	private String gid;
	private String uid;
	private String maskId;
	private String maskName;
	private String status;
	private String level;
	private String mTime;
	private String isOut;
	private String maskFace;
	private String credit_score;
	private String isTrueName;

	public ModelLeagueMember() {

	}

	public ModelLeagueMember(JSONObject jsonObject) {
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

		} catch (JSONException e) {
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

	@Override
	public String toString() {
		return "ModelLeagueMember [id=" + id + ", gid=" + gid + ", uid=" + uid
				+ ", maskId=" + maskId + ", maskName=" + maskName + ", status="
				+ status + ", level=" + level + ", mTime=" + mTime + ", isOut="
				+ isOut + ", maskFace=" + maskFace + ", credit_score="
				+ credit_score + ", isTrueName=" + isTrueName + "]";
	}

}
