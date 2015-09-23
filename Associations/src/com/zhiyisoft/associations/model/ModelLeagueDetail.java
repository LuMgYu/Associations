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

public class ModelLeagueDetail extends Model {
	private ModelLeagueList leagueList; // 社团信息
	private List<Model> members; // 成员列表
	private List<Model> news; // 新鲜事儿列表
	private List<Model> albums;// 活动列表
	private List<Model> moveEvents;// 相册列表
	private List<Model> attachs;// 附件列表
	private List<Model> relations;// 用户与社团关系（枚举待定）

	public ModelLeagueDetail() {

	}

	public ModelLeagueDetail(JSONObject jsonObject) {
		try {
			this.leagueList = (ModelLeagueList) JsonUtils.parseJsonObject(
					jsonObject, new ModelLeagueList());

			if (jsonObject.has("memberList")) {
				JSONArray jsonArray = jsonObject.getJSONArray("memberList");
				this.members = JsonUtils.parseJsonArray(jsonArray,
						new ModelLeagueMember());

			}
			if (jsonObject.has("news")) {
				JSONArray jsonArray = jsonObject.getJSONArray("news");
				this.news = JsonUtils.parseJsonArray(jsonArray,
						new ModelLeagueTopic());

			}
			if (jsonObject.has("event")) {
				JSONArray jsonArray = jsonObject.getJSONArray("event");
				this.moveEvents = JsonUtils.parseJsonArray(jsonArray,
						new ModelMoveEvent());

			}
			if (jsonObject.has("album")) {
				JSONArray jsonArray = jsonObject.getJSONArray("album");
				this.albums = JsonUtils.parseJsonArray(jsonArray,
						new ModelLeagueAlbum());

			}
			if (jsonObject.has("attch")) {
				JSONArray jsonArray = jsonObject.getJSONArray("attch");
				this.attachs = JsonUtils.parseJsonArray(jsonArray,
						new ModelLeagueAttach());

			}

			if (jsonObject.has("relation")) {
				JSONArray jsonArray = jsonObject.getJSONArray("relation");
				this.relations = JsonUtils.parseJsonArray(jsonArray,
						new ModelLeagueRelation());

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ModelLeagueList getLeagueList() {
		return leagueList;
	}

	public void setLeagueList(ModelLeagueList leagueList) {
		this.leagueList = leagueList;
	}

	public List<Model> getMembers() {
		return members;
	}

	public void setMembers(List<Model> members) {
		this.members = members;
	}

	public List<Model> getNews() {
		return news;
	}

	public void setNews(List<Model> news) {
		this.news = news;
	}

	public List<Model> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Model> albums) {
		this.albums = albums;
	}

	public List<Model> getMoveEvents() {
		return moveEvents;
	}

	public void setMoveEvents(List<Model> moveEvents) {
		this.moveEvents = moveEvents;
	}

	public List<Model> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Model> attachs) {
		this.attachs = attachs;
	}

	public List<Model> getRelations() {
		return relations;
	}

	public void setRelations(List<Model> relations) {
		this.relations = relations;
	}

	@Override
	public String toString() {
		return "ModelLeagueDetail [leagueList=" + leagueList + ", members="
				+ members + ", news=" + news + ", albums=" + albums
				+ ", moveEvents=" + moveEvents + ", attachs=" + attachs
				+ ", relations=" + relations + "]";
	}

}
