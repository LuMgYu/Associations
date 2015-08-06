package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 社团列表
 *
 */

public class ModelLeagueList extends Model {

	/***
	 * 
	 * "gid":"15260",
	 * 
	 * "uid":"6309282",
	 * 
	 * "maskId":"176591",
	 * 
	 * "maskName":"fengyell",
	 * 
	 * "name":"\u6d4b\u8bd5004",
	 * 
	 * "cTime":"1438079498", "categoryId":"1", "affiche":null, "logo":"251109",
	 * "status":"1", "description":"123", "reason":"", "spaceSize":"0",
	 * "isDel":"0", "joinType":"0", "privacy":null, "commendTime":null,
	 * "commend":"0", "theme":"1", "openTime":"1438079498", "openerName":null,
	 * "commendOrder":"0",
	 * "school":"\u4e2d\u56fd\u79d1\u5b66\u6280\u672f\u5927\u5b66",
	 * "schoolId":"2", "departId":"0", "contact":false, "private":"0",
	 * "tags":"\u9ed8\u8ba4", "freeze":"0", "logoSmall":
	 * "http:\/\/daxs.zhiyicx.com\/attachment\/uploads\/2015\/0728\/10\/0f3ca7e30f41989ab526bd4a6bb9966161b6
	 * 0 3 3 2 . j p g " , "logoMiddle":
	 * "http:\/\/daxs.zhiyicx.com\/attachment\/uploads\/2015\/0728\/10\/0f3ca7e30f41989ab526bd4a6bb9966161b6
	 * 0 3 3 2 . j p g " , "logoBig":
	 * "http:\/\/daxs.zhiyicx.com\/attachment\/uploads\/2015\/0728\/10\/0f3ca7e30f41989ab526bd4a6bb9966161b6
	 * 0 3 3 2 . j p g " , "medal":null, "verified":null, "verifiedUrl":
	 * "\/apps\/group\/Tpl\/default\/Public\/images\/verified.gif", "count":{
	 * "members":"3", "follow":"2", "visitors":"16", "topic":0, "post":0,
	 * "files":0 }, "isFollow":null },
	 * 
	 * */

	private String gid;
	private String uid;
	private String maskId;
	private String maskName;
	private String name;
	private String cTime;
	private String categoryId;
	private String affiche;
	private String logo;
	private String status;
	private String description;
	private String reason;
	private String spaceSize;
	private String isDel;
	private String joinType;
	private String privacy;
	private String commendTime;
	private String commend;
	private String theme;
	private String openTime;
	private String openerName;
	private String commendOrder;
	private String school;
	private String schoolId;
	private String departId;
	private String contact;
	private String Private;
	private String tags;
	private String freeze;
	private String logoSmall;
	private String logoMiddle;
	private String logoBig;
	private String medal;
	private String verified;
	private String verifiedUrl;
	private String count;
	private String isFollow;

	public ModelLeagueList() {

	}

	public ModelLeagueList(JSONObject jsonObject) {
		try {
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
			if (jsonObject.has("cTime")) {

				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("categoryId")) {

				this.setCategoryId(jsonObject.getString("categoryId"));
			}
			if (jsonObject.has("logo")) {
				this.setLogo(jsonObject.getString("logo"));
			}
			if (jsonObject.has("status")) {

				this.setStatus(jsonObject.getString("status"));
			}
			if (jsonObject.has("description")) {
				this.setDescription(jsonObject.getString("description"));
			}
			if (jsonObject.has("reason")) {

				this.setReason(jsonObject.getString("reason"));
			}
			if (jsonObject.has("spaceSize")) {
				this.setSpaceSize(jsonObject.getString("spaceSize"));
			}
			if (jsonObject.has("isDel")) {

				this.setIsDel(jsonObject.getString("isDel"));
			}
			if (jsonObject.has("joinType")) {
				this.setJoinType(jsonObject.getString("joinType"));
			}
			if (jsonObject.has("privacy")) {

				this.setPrivacy(jsonObject.getString("privacy"));
			}
			if (jsonObject.has("commendTime")) {
				this.setCommendTime(jsonObject.getString("commendTime"));
			}
			if (jsonObject.has("commend")) {

				this.setCommend(jsonObject.getString("commend"));
			}
			if (jsonObject.has("theme")) {
				this.setTheme(jsonObject.getString("theme"));
			}
			if (jsonObject.has("openTime")) {

				this.setOpenTime(jsonObject.getString("openTime"));
			}
			if (jsonObject.has("openerName")) {
				this.setOpenerName(jsonObject.getString("openerName"));
			}
			if (jsonObject.has("commendOrder")) {

				this.setCommendOrder(jsonObject.getString("commendOrder"));
			}
			if (jsonObject.has("school")) {
				this.setSchool(jsonObject.getString("school"));
			}
			if (jsonObject.has("schoolId")) {

				this.setSchoolId(jsonObject.getString("schoolId"));
			}
			if (jsonObject.has("departId")) {
				this.setDepartId(jsonObject.getString("departId"));
			}
			if (jsonObject.has("contact")) {

				this.setContact(jsonObject.getString("contact"));
			}
			if (jsonObject.has("Private")) {
				this.setPrivate(jsonObject.getString("Private"));
			}
			if (jsonObject.has("tags")) {

				this.setTags(jsonObject.getString("tags"));
			}
			if (jsonObject.has("freeze")) {
				this.setFreeze(jsonObject.getString("freeze"));
			}
			if (jsonObject.has("logoSmall")) {

				this.setLogoSmall(jsonObject.getString("logoSmall"));
			}
			if (jsonObject.has("logoMiddle")) {
				this.setLogoMiddle(jsonObject.getString("logoMiddle"));
			}
			if (jsonObject.has("logoBig")) {

				this.setLogoBig(jsonObject.getString("logoBig"));
			}
			if (jsonObject.has("medal")) {
				this.setMedal(jsonObject.getString("medal"));
			}
			if (jsonObject.has("verified")) {

				this.setVerified(jsonObject.getString("verified"));
			}
			if (jsonObject.has("verifiedUrl")) {
				this.setVerified(jsonObject.getString("verifiedUrl"));
			}
			if (jsonObject.has("count")) {

				this.setCount(jsonObject.getString("count"));
			}
			if (jsonObject.has("isFollow")) {
				this.setIsFollow(jsonObject.getString("isFollow"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getAffiche() {
		return affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getCommendTime() {
		return commendTime;
	}

	public void setCommendTime(String commendTime) {
		this.commendTime = commendTime;
	}

	public String getCommend() {
		return commend;
	}

	public void setCommend(String commend) {
		this.commend = commend;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getOpenerName() {
		return openerName;
	}

	public void setOpenerName(String openerName) {
		this.openerName = openerName;
	}

	public String getCommendOrder() {
		return commendOrder;
	}

	public void setCommendOrder(String commendOrder) {
		this.commendOrder = commendOrder;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPrivate() {
		return Private;
	}

	public void setPrivate(String private1) {
		Private = private1;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}

	public String getLogoSmall() {
		return logoSmall;
	}

	public void setLogoSmall(String logoSmall) {
		this.logoSmall = logoSmall;
	}

	public String getLogoMiddle() {
		return logoMiddle;
	}

	public void setLogoMiddle(String logoMiddle) {
		this.logoMiddle = logoMiddle;
	}

	public String getLogoBig() {
		return logoBig;
	}

	public void setLogoBig(String logoBig) {
		this.logoBig = logoBig;
	}

	public String getMedal() {
		return medal;
	}

	public void setMedal(String medal) {
		this.medal = medal;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getVerifiedUrl() {
		return verifiedUrl;
	}

	public void setVerifiedUrl(String verifiedUrl) {
		this.verifiedUrl = verifiedUrl;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

	@Override
	public String toString() {
		return "ModelLeagueList [gid=" + gid + ", uid=" + uid + ", maskId="
				+ maskId + ", maskName=" + maskName + ", name=" + name
				+ ", cTime=" + cTime + ", categoryId=" + categoryId
				+ ", affiche=" + affiche + ", logo=" + logo + ", status="
				+ status + ", description=" + description + ", reason="
				+ reason + ", spaceSize=" + spaceSize + ", isDel=" + isDel
				+ ", joinType=" + joinType + ", privacy=" + privacy
				+ ", commendTime=" + commendTime + ", commend=" + commend
				+ ", theme=" + theme + ", openTime=" + openTime
				+ ", openerName=" + openerName + ", commendOrder="
				+ commendOrder + ", school=" + school + ", schoolId="
				+ schoolId + ", departId=" + departId + ", contact=" + contact
				+ ", Private=" + Private + ", tags=" + tags + ", freeze="
				+ freeze + ", logoSmall=" + logoSmall + ", logoMiddle="
				+ logoMiddle + ", logoBig=" + logoBig + ", medal=" + medal
				+ ", verified=" + verified + ", verifiedUrl=" + verifiedUrl
				+ ", count=" + count + ", isFollow=" + isFollow + "]";
	}
	

}
