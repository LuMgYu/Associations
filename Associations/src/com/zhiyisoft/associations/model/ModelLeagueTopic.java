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

public class ModelLeagueTopic extends Model {
	// (int) gid社团id 必填
	// (string) content内容 必填
	// // (string) title 标题 必填
	private String gid;
	private String content;
	private String title;
	// "tid":"650912",
	// "uid":"1",
	// "faceurl":"http://daxs.zhiyicx.com/attachment/uploads/2015/0919/17/55fd2457d0147.jpg",
	// "uname":"呵呵哒",
	// "content":"雅蠛蝶雅蠛蝶雅蠛蝶雅蠛蝶雅蠛蝶雅蠛蝶",
	// "ctime":"1442994449",
	// "replyCount":"0",
	/**************** 获取话题需要的内容 *********************/
	private String tid;
	private String uid;
	private String faceurl;
	private String uname;
	// private String content;
	private String ctime;
	private String replyCount;
	List<Model> attachs;

	private String replyContent; // 回复的内容
	private String pid; // 回复的内容id

	public ModelLeagueTopic() {

	}

	public ModelLeagueTopic(JSONObject jsonObject) {
		try {
			if (jsonObject.has("tid")) {
				this.setTid(jsonObject.getString("tid"));
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
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}
			if (jsonObject.has("ctime")) {
				this.setCtime(jsonObject.getString("ctime"));
			}
			if (jsonObject.has("replyCount")) {
				this.setReplyCount(jsonObject.getString("replyCount"));
			}
			if (jsonObject.has("attachs")) {
				JSONArray array = jsonObject.getJSONArray("attachs");
				this.setAttachs(JsonUtils.parseJsonArray(array,
						new ModelLeagueTopicPhoto()));
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	public List<Model> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Model> attachs) {
		this.attachs = attachs;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "ModelLeagueTopic [gid=" + gid + ", content=" + content
				+ ", title=" + title + ", tid=" + tid + ", uid=" + uid
				+ ", faceurl=" + faceurl + ", uname=" + uname + ", ctime="
				+ ctime + ", replyCount=" + replyCount + ", attachs=" + attachs
				+ "]";
	}

}
