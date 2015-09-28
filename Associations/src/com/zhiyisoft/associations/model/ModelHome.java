package com.zhiyisoft.associations.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

public class ModelHome extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Model> banner;
	private List<Model> joinedGroup;
	private List<Model> groups;
	private List<Model> events;
	private List<Model> works;
	private List<Model> topics;

	public ModelHome() {

	}

	public ModelHome(JSONObject jsonObject) {
		try {
			if (jsonObject.has("banner")) {
				JSONArray array = jsonObject.getJSONArray("banner");
				this.setBanner(JsonUtils.parseJsonArray(array,
						new ModelBanner()));
			}
			if (jsonObject.has("joinedGroup")) {
				JSONArray array = jsonObject.getJSONArray("joinedGroup");
				this.setJoinedGroup(JsonUtils.parseJsonArray(array,
						new ModelLeague()));
			}
			if (jsonObject.has("groups")) {
				JSONArray array = jsonObject.getJSONArray("groups");
				this.setGroups(JsonUtils.parseJsonArray(array,
						new ModelLeague()));
			}
			if (jsonObject.has("events")) {
				JSONArray array = jsonObject.getJSONArray("events");
				this.setEvents(JsonUtils
						.parseJsonArray(array, new ModelEvent()));
			}
			if (jsonObject.has("works")) {
				JSONArray array = jsonObject.getJSONArray("works");
				this.setWorks(JsonUtils.parseJsonArray(array,
						new ModelEventWorks()));
			}
			if (jsonObject.has("topics")) {
				JSONArray array = jsonObject.getJSONArray("topics");
				this.setTopics(JsonUtils.parseJsonArray(array,
						new ModelLeagueTopic()));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Model> getBanner() {
		return banner;
	}

	public void setBanner(List<Model> banner) {
		this.banner = banner;
	}

	public List<Model> getJoinedGroup() {
		return joinedGroup;
	}

	public void setJoinedGroup(List<Model> joinedGroup) {
		this.joinedGroup = joinedGroup;
	}

	public List<Model> getGroups() {
		return groups;
	}

	public void setGroups(List<Model> groups) {
		this.groups = groups;
	}

	public List<Model> getEvents() {
		return events;
	}

	public void setEvents(List<Model> events) {
		this.events = events;
	}

	public List<Model> getWorks() {
		return works;
	}

	public void setWorks(List<Model> works) {
		this.works = works;
	}

	public List<Model> getTopics() {
		return topics;
	}

	public void setTopics(List<Model> topics) {
		this.topics = topics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}