package com.zhiyisoft.associations.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

public class ModelEventWorks extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * 
		 */
	private String title;
	private String intro;
	private String id;
	private int explainType;

	// "id":"00000000143",
	// "uid":"1",
	// "faceurl":"http://daxs.zhiyicx.com/attachment/uploads/2015/0925/15/5604fdf3ebe0e.jpg",
	// "uname":"呵呵哒",
	// "content":null,
	// "title":"并不是说",
	// "ctime":"1443424679",
	// "type":"2",
	// "commentCount":"0",
	// "attachs":[
	/************ 需要解析的json数据 ******************/
	// private String id;
	private String uid;
	private String faceurl;
	private String uname;
	// private String intro;
	private String ctime;
	private String type;
	private String commentCount;
	private List<Model> attachs;
	private String video_image;

	public ModelEventWorks() {

	}

	public ModelEventWorks(JSONObject jsonObject) {
		try {
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("id")) {
				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("faceurl")) {
				this.setFaceurl(jsonObject.getString("faceurl"));
			}
			// 这个用于首页的需要
			if (jsonObject.has("url")) {
				this.setFaceurl(jsonObject.getString("url"));
			}
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}
			if (jsonObject.has("uname")) {
				this.setUname(jsonObject.getString("uname"));
			}
			if (jsonObject.has("intro")) {
				this.setIntro(jsonObject.getString("intro"));
			}

			if (jsonObject.has("ctime")) {
				this.setCtime(jsonObject.getString("ctime"));
			}

			if (jsonObject.has("type")) {
				this.setType(jsonObject.getString("type"));
			}

			if (jsonObject.has("commentCount")) {
				this.setCommentCount(jsonObject.getString("commentCount"));
			}
			if (jsonObject.has("attachs")) {
				JSONArray array = jsonObject.getJSONArray("attachs");
				this.setAttachs(JsonUtils.parseJsonArray(array,
						new ModelCommonAttach()));
			}
			if (jsonObject.has("video_image")) {
				this.setVideo_image(jsonObject.getString("video_image"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExplainType() {
		return explainType;
	}

	public void setExplainType(int explainType) {
		this.explainType = explainType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public List<Model> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Model> attachs) {
		this.attachs = attachs;
	}

	public String getVideo_image() {
		return video_image;
	}

	public void setVideo_image(String video_image) {
		this.video_image = video_image;
	}

}