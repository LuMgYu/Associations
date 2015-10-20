package com.zhiyisoft.associations.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现 创建活动之类的
 *
 */

public class ModelEvent extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	private int op;// op 操作类型 选填 (1为我参与的，2我创建的，3我关注的，4我的) 不传就代表获取所有
	private int stub;// (int) sub 0关注，1取消关注

	/**************** 获取活动需要的数据 *************************/
	private String id;
	// private String title;
	// private String explain;
	// private String online;
	// private String sTime;
	// private String eTime;
	private String joinCount;
	private String typeName;
	private String logourl;
	private int isover;

	// "id":"78",
	// "online":"1",
	// "logo":"http://daxs.zhiyicx.com/attachment/uploads/2015/0926/10/5606025d4deae.jpg",
	// "title":"过家家",
	// "cTime":"1443234422",
	// "eTime":"2147483647",
	// "joinCount":"0",
	// "explain":"时时刻刻上课了",
	// "host":"这不是你刷卡",
	// "gid":"15293",
	// "address":"班那么",
	// "typeName":null,
	// "gname":"Dodd",
	// "glogo":"http://daxs.zhiyicx.com/attachment/uploads/2015/0921/16/55ffc384412b6.png",
	// "isin":1,
	// "issub":1

	private String gname;
	private String glogo;
	private int isin;
	private int issub;
	private List<Model> members;
	private List<Model> works;

	private double latitude; // 2015-10-19
	private double longtitude;

	private String city; // 2015-10-20
	private String province;

	/**************** 获取活动需要的数据end *************************/

	public ModelEvent() {

	}

	public ModelEvent(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {

				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}

			if (jsonObject.has("explain")) {

				this.setExplain(jsonObject.getString("explain"));
			}
			if (jsonObject.has("online")) {
				this.setOnline(jsonObject.getString("online"));
			}
			if (jsonObject.has("cTime")) {

				this.setsTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("sTime")) {

				this.setsTime(jsonObject.getString("sTime"));
			}
			if (jsonObject.has("eTime")) {

				this.seteTime(jsonObject.getString("eTime"));
			}
			//
			if (jsonObject.has("workStime")) {

				this.setWorkStime(jsonObject.getString("workStime"));
			}
			if (jsonObject.has("workEtime")) {

				this.setWorkEtime(jsonObject.getString("workEtime"));
			}

			if (jsonObject.has("joinCount")) {

				this.setJoinCount(jsonObject.getString("joinCount"));
			}
			if (jsonObject.has("typeName")) {

				this.setTypeName(jsonObject.getString("typeName"));
			}
			if (jsonObject.has("logo")) {

				this.setLogourl(jsonObject.getString("logo"));
			}
			if (jsonObject.has("isover")) {

				this.setIsover(jsonObject.getInt("isover"));
			}
			if (jsonObject.has("op")) {

				this.setOp(jsonObject.getInt("op"));
			}
			if (jsonObject.has("gname")) {

				this.setGname(jsonObject.getString("gname"));
			}
			if (jsonObject.has("glogo")) {

				this.setGlogo(jsonObject.getString("glogo"));
			}
			if (jsonObject.has("isin")) {

				this.setIsin(jsonObject.getInt("isin"));
			}
			if (jsonObject.has("issub")) {

				this.setIssub(jsonObject.getInt("issub"));
			}
			if (jsonObject.has("gid")) {

				this.setGid(jsonObject.getString("gid"));
			}
			/**************** 经纬度 *********************/
			if (jsonObject.has("latitude")) {

				this.setLatitude((Double.valueOf(jsonObject
						.getString("latitude"))));
			}
			if (jsonObject.has("longitude")) {

				this.setLongtitude((Double.valueOf(jsonObject
						.getString("longitude"))));
			}
			/**************** 经纬度 *********************/
			if (jsonObject.has("host")) {

				this.setHost(jsonObject.getString("host"));
			}
			if (jsonObject.has("members")) {
				JSONArray array = jsonObject.getJSONArray("members");
				this.setMembers(JsonUtils.parseJsonArray(array,
						new ModelLeagueMember()));
			}
			if (jsonObject.has("works")) {
				JSONArray array = jsonObject.getJSONArray("works");
				this.setWorks(JsonUtils.parseJsonArray(array,
						new ModelEventWorks()));
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(String joinCount) {
		this.joinCount = joinCount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public int getIsover() {
		return isover;
	}

	public void setIsover(int isover) {
		this.isover = isover;
	}

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public int getStub() {
		return stub;
	}

	public void setStub(int stub) {
		this.stub = stub;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGlogo() {
		return glogo;
	}

	public void setGlogo(String glogo) {
		this.glogo = glogo;
	}

	public int getIsin() {
		return isin;
	}

	public void setIsin(int isin) {
		this.isin = isin;
	}

	public int getIssub() {
		return issub;
	}

	public void setIssub(int issub) {
		this.issub = issub;
	}

	public List<Model> getMembers() {
		return members;
	}

	public void setMembers(List<Model> members) {
		this.members = members;
	}

	public List<Model> getWorks() {
		return works;
	}

	public void setWorks(List<Model> works) {
		this.works = works;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "ModelEvent [online=" + online + ", logo=" + logo + ", gid="
				+ gid + ", title=" + title + ", address=" + address + ", type="
				+ type + ", host=" + host + ", explain=" + explain + ", sTime="
				+ sTime + ", eTime=" + eTime + ", joinAudit=" + joinAudit
				+ ", joinStime=" + joinStime + ", joinEtime=" + joinEtime
				+ ", workAudit=" + workAudit + ", worksPurview=" + worksPurview
				+ ", workStime=" + workStime + ", workEtime=" + workEtime
				+ ", explainType=" + explainType + ", rangeDes=" + rangeDes
				+ ", op=" + op + ", stub=" + stub + ", id=" + id
				+ ", joinCount=" + joinCount + ", typeName=" + typeName
				+ ", logourl=" + logourl + ", isover=" + isover + ", gname="
				+ gname + ", glogo=" + glogo + ", isin=" + isin + ", issub="
				+ issub + ", members=" + members + ", works=" + works
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ ", city=" + city + ", province=" + province + "]";
	}

}
