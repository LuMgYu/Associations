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

public class ModelComment extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pid;
	private String uid;
	private String faceurl;
	private String uname;
	private String content;
	private String ctime;
	private List<Model> commentlist;

	/*********** 活动需要添加的字段 *******************/
	private String id;

	public ModelComment() {

	}

	public ModelComment(JSONObject jsonObject) {
		try {
			if (jsonObject.has("pid")) {
				this.setPid(jsonObject.getString("pid"));
			}
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("faceurl")) {
				this.setFaceurl(jsonObject.getString("faceurl"));
			}
			if (jsonObject.has("uname")) {
				this.setUname(jsonObject.getString("uname"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}
			if (jsonObject.has("ctime")) {
				this.setCtime(jsonObject.getString("ctime"));
			}
			if (jsonObject.has("comment")) {
				JSONArray array = jsonObject.getJSONArray("comment");
				this.setCommentlist(JsonUtils.parseJsonArray(array,
						new ModelChildComment()));
			}
			if (jsonObject.has("childComment")) {
				JSONArray array = jsonObject.getJSONArray("childComment");
				this.setCommentlist(JsonUtils.parseJsonArray(array,
						new ModelChildComment()));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public List<Model> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<Model> commentlist) {
		this.commentlist = commentlist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
