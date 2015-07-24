package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：qiuchunjia time：下午4:21:46 类描述：这个类是实现
 * 
 * maskId：昵称ID； uid：用户ID； name：昵称； status：昵称状态； faceid：昵称头像ID； isTrueName:是否实名；
 * sort：排序； isdel：是否删除； cTime：创件时间； credit_exp：昵称经验； credit_score：昵称积分；
 * spaceSize：空间大小；
 *
 */

public class ModelMask extends Model {
	/*
	 * "maskId": "176592", "uid": "6309289", "name": "俺是小腿", "status": "1",
	 * "faceid": "0", "isTrueName": "1", "sort": "0", "isdel": "0", "cTime":
	 * "1437637829", "credit_exp": "0", "credit_score": "0", "credit_ext1": "0",
	 * "credit_ext2": "0", "credit_ext3": "0", "credit_ext4": "0",
	 * "credit_ext5": "0", "spaceSize": "0"
	 */
	private String mask; // 设置昵称的时候用到的字段，其實它等于 name字段的值
	// -----------------------------------------
	/*
	 * url:图片路径； domain：域名（本站传0，其他站，传域名）
	 */

	private String url; // 当设置图片途径的时候用到
	private String domain;
	// ------------------------------------------------------
	// 返回的图片路径
	private String face_img;

	// -----------------------------------------
	// 返回的值段
	private String maskId;
	private String uid;
	private String name;
	private String status;
	private String faceid;
	private String isTrueName;
	private String sort;
	private String isdel;
	private String cTime;
	private String credit_exp;
	private String credit_score;
	private String spaceSize;

	// private String credit_ext3;
	// private String credit_ext3;
	// private String credit_ext3;
	// private String credit_ext3;

	public ModelMask() {

	}

	public ModelMask(JSONObject jsonObject) {
		try {

			if (jsonObject.has("maskId")) {
				this.setMaskId(jsonObject.getString("maskId"));
			}
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("status")) {
				this.setStatus(jsonObject.getString("status"));
			}
			if (jsonObject.has("faceid")) {
				this.setFaceid(jsonObject.getString("faceid"));
			}
			if (jsonObject.has("isTrueName")) {
				this.setIsTrueName(jsonObject.getString("isTrueName"));
			}
			if (jsonObject.has("sort")) {
				this.setSort(jsonObject.getString("sort"));
			}
			if (jsonObject.has("isdel")) {
				this.setIsdel(jsonObject.getString("isdel"));
			}
			if (jsonObject.has("cTime")) {
				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("credit_exp")) {
				this.setCredit_exp(jsonObject.getString("credit_exp"));
			}
			if (jsonObject.has("credit_score")) {
				this.setCredit_score(jsonObject.getString("credit_score"));
			}
			if (jsonObject.has("spaceSize")) {
				this.setSpaceSize(jsonObject.getString("spaceSize"));
			}
			if (jsonObject.has("face_img")) {
				this.setFace_img(jsonObject.getString("face_img"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getMaskId() {
		return maskId;
	}

	public void setMaskId(String maskId) {
		this.maskId = maskId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFaceid() {
		return faceid;
	}

	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}

	public String getIsTrueName() {
		return isTrueName;
	}

	public void setIsTrueName(String isTrueName) {
		this.isTrueName = isTrueName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getCredit_exp() {
		return credit_exp;
	}

	public void setCredit_exp(String credit_exp) {
		this.credit_exp = credit_exp;
	}

	public String getCredit_score() {
		return credit_score;
	}

	public void setCredit_score(String credit_score) {
		this.credit_score = credit_score;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFace_img() {
		return face_img;
	}

	public void setFace_img(String face_img) {
		this.face_img = face_img;
	}

	
	@Override
	public String toString() {
		return "ModelMask [mask=" + mask + ", url=" + url + ", domain="
				+ domain + ", face_img=" + face_img + ", maskId=" + maskId
				+ ", uid=" + uid + ", name=" + name + ", status=" + status
				+ ", faceid=" + faceid + ", isTrueName=" + isTrueName
				+ ", sort=" + sort + ", isdel=" + isdel + ", cTime=" + cTime
				+ ", credit_exp=" + credit_exp + ", credit_score="
				+ credit_score + ", spaceSize=" + spaceSize + ", getMaskId()="
				+ getMaskId() + ", getUid()=" + getUid() + ", getName()="
				+ getName() + ", getStatus()=" + getStatus() + ", getFaceid()="
				+ getFaceid() + ", getIsTrueName()=" + getIsTrueName()
				+ ", getSort()=" + getSort() + ", getIsdel()=" + getIsdel()
				+ ", getcTime()=" + getcTime() + ", getCredit_exp()="
				+ getCredit_exp() + ", getCredit_score()=" + getCredit_score()
				+ ", getSpaceSize()=" + getSpaceSize() + ", getMask()="
				+ getMask() + ", getUrl()=" + getUrl() + ", getDomain()="
				+ getDomain() + ", getFace_img()=" + getFace_img()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
