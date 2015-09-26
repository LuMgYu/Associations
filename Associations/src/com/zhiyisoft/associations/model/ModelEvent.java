package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现 创建活动之类的
 *
 */

public class ModelEvent extends Model {
	private String online; // 0线上活动，1线下活动 必填
	private String logo;// 接口37中返回的id必填
	private String gid;// 所属社团 必填
	private String title;// 活动标题 必填
	private String address;// 活动地点 选填
	private String type;// 活动类别 必填
	private String host;// 主办方 必填
	private String explain;// 活动内容 必填
	private String sTime;// 活动开始时间 必填
	private String eTime;// 活动结束时间 必填
	private String joinAudit;// 报名是否审核，0否，1是 必填
	private String joinStime;// 报名开始时间 必填
	private String joinEtime;// 报名结束时间 必填
	private String workAudit;// 作品是否审核，0否，1是 必填
	private String worksPurview;// 作品上传权限 1发起人，2所有人
	private String workStime;// 品提交开始时间 必填
	private String workEtime;// 作品提交结束时间 必填
	private String explainType;// 作品提交结束时间 必填
	private String rangeDes;// 指定学校id 选填

	public ModelEvent() {

	}

	public ModelEvent(JSONObject jsonObject) {
		try {
			if (jsonObject.has("name")) {

				this.setOnline(jsonObject.getString("name"));
			}
			if (jsonObject.has("level")) {
				// this.setLevel(jsonObject.getString("level"));
			}
			if (jsonObject.has("id")) {

				// this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("schoolName")) {
				// this.setSchoolName(jsonObject.getString("schoolName"));
			}
			if (jsonObject.has("faceurl")) {

				// this.setFaceurl(jsonObject.getString("faceurl"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public String getJoinAudit() {
		return joinAudit;
	}

	public void setJoinAudit(String joinAudit) {
		this.joinAudit = joinAudit;
	}

	public String getJoinStime() {
		return joinStime;
	}

	public void setJoinStime(String joinStime) {
		this.joinStime = joinStime;
	}

	public String getJoinEtime() {
		return joinEtime;
	}

	public void setJoinEtime(String joinEtime) {
		this.joinEtime = joinEtime;
	}

	public String getWorkAudit() {
		return workAudit;
	}

	public void setWorkAudit(String workAudit) {
		this.workAudit = workAudit;
	}

	public String getWorksPurview() {
		return worksPurview;
	}

	public void setWorksPurview(String worksPurview) {
		this.worksPurview = worksPurview;
	}

	public String getWorkStime() {
		return workStime;
	}

	public void setWorkStime(String workStime) {
		this.workStime = workStime;
	}

	public String getWorkEtime() {
		return workEtime;
	}

	public void setWorkEtime(String workEtime) {
		this.workEtime = workEtime;
	}

	public String getRangeDes() {
		return rangeDes;
	}

	public void setRangeDes(String rangeDes) {
		this.rangeDes = rangeDes;
	}

	public String getExplainType() {
		return explainType;
	}

	public void setExplainType(String explainType) {
		this.explainType = explainType;
	}

}
