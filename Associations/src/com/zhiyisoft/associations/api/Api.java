package com.zhiyisoft.associations.api;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.zhiyisoft.associations.model.ModelAssociation;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueDetail;
import com.zhiyisoft.associations.model.ModelLeagueList;
import com.zhiyisoft.associations.model.ModelMask;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.request.Get;
import com.zhiyisoft.associations.request.Post;
import com.zhiyisoft.associations.request.base.Request;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：下午2:32:28 类描述：这个类是实现调用api的接口
 *
 */

public class Api {

	public static final String MOD = "mod";
	public static final String ACT = "act";

	/**
	 * @author qcj 登录类
	 *
	 */
	public static final class LoginImpl implements LoginIm {
		@Override
		public Object registerMem(ModelUser user) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addBodyParam(MOD, LoginIm.LOGIN);
			post.addBodyParam(ACT, LoginIm.REG);
			post.addBodyParam("mobile", "13688449697");
			post.addBodyParam("password", "13445555");
			Object object = post.run();
			Log.i("request", "object=" + object);
			return null;
		}

		@Override
		public Model Login(ModelUser user) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addBodyParam(MOD, LoginIm.LOGIN);
			post.addBodyParam(ACT, LoginIm.INDEX);
			post.addBodyParam("mobile", "13688449697");
			post.addBodyParam("password", "13445555");
			Object object = post.run();
			Log.i("request", "object=" + object);
			return parseOriginalJsonObject(object.toString(), new ModelUser());
		}

	}

	/**
	 * @author qcj 查詢學校的實現類
	 *
	 */
	public static final class SchoolImpl implements SchoolIm {

		@Override
		public List<Model> getSchools(String province) {
			Request get = new Get();
			get.addBodyParam(MOD, SchoolIm.TOOL);
			get.addBodyParam(ACT, SchoolIm.SCHOOLBYPROVINCE);
			get.addBodyParam("name", "上海");
			Object object = get.run(); // 利用网络请求数据，然后返回的数据
			return parseOriginalJsonArray(object.toString(), new ModelSchool());
		}
	}

	public static final class LeagueImpl implements LeagueIm {
		/**
		 * 
		 * { "code": 1, "msg": "登陆成功", "data": { "uid": "6309289", "client_id":
		 * "6309289", "token": "34975aeea94b0e71311c21215daf117a", "pic":
		 * "http://dxs.demo.local/public/images/user_pic.gif", "name": null,
		 * "uname": "13688449697", "email": null, "sex": null } }
		 * 
		 * */
		@Override
		public Object createLeague(Model modelItem) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addHeaderParam("uid", "6309289");
			post.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			post.addBodyParam(MOD, LeagueIm.GROUP);
			post.addBodyParam(ACT, LeagueIm.CREATEGROUP);
			post.addBodyParam("name", "睡你麻痹起来嗨");
			post.run();
			return null;
		}

		@Override
		public List<Model> getGroupCommonList(Model mItem) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, LeagueIm.GROUP);
			get.addBodyParam(ACT, LeagueIm.GETGROUPCOMMONLIST);
			Object object = get.run();
			return parseOriginalJsonArray(object.toString(), new ModelLeague());
		}

		@Override
		public boolean addLeague(Model model) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, JOIN);
			get.addBodyParam("gid", "15222");
			Object object = get.run();
			return isCodeOk(object.toString());
		}

		@Override
		public List<Model> getLeagueMember(Model model) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, MEMBERLIST);
			get.addBodyParam("gid", "15222");
			Object object = get.run();
			return parseOriginalJsonArray(object.toString(),
					new ModelAssociation());
		}

		@Override
		public boolean quitLeague(Model model) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, LEAVE);
			get.addBodyParam("gid", "15222");
			Object object = get.run();
			return isCodeOk(object.toString());
		}

		@Override
		public List<Model> getLeagueList(Model model) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, INDEX);
			get.addBodyParam("schoolid", "2");
			Object object = get.run();
			return parseOriginalJsonArray(object.toString(),
					new ModelLeagueList());
		}

		@Override
		public Model getLeagueDetail(Model model) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, VIEW);
			get.addBodyParam("gid", "15225");
			Object object = get.run();
			return parseOriginalJsonObject(object.toString(),
					new ModelLeagueDetail());
		}

		@Override
		public Model getAlbumByLeagueID(Model model) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Model getPhotoListByAlbumId(Model model) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Model createAlbum(Model model) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static final class BaseSettingImpl implements BaseSettingIm {

		@Override
		public Object updateMask(Model item) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, BaseSettingIm.USER);
			get.addBodyParam(ACT, BaseSettingIm.SETMASK);
			get.addBodyParam("mask", "俺是da腿");
			get.run();
			return null;
		}

		@Override
		public Model getUserActiveMaskInfo(Model item) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, BaseSettingIm.USER);
			get.addBodyParam(ACT, BaseSettingIm.GETUSERACTIVITEMASKINFO);
			Object object = get.run();
			return parseOriginalJsonObject(object.toString(), new ModelMask());
		}

		@Override
		public Model setFaceImg(Model item) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addHeaderParam("uid", "6309289");
			post.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			post.addBodyParam(MOD, BaseSettingIm.USER);
			post.addBodyParam(ACT, BaseSettingIm.SETFACEIMG);
			post.addBodyParam("url", "https://www.baidu.com/img/bd_logo1.png");
			post.addBodyParam("domain", "https://www.baidu.com");
			Object object = post.run();
			return parseOriginalJsonObject(object.toString(), new ModelMask());
		}
	}

	// -------------------------------------------------------------------------------------
	/**
	 * 判断返回的json是否有效，当code=1时表面有效，其它的都看着无效
	 * 
	 * @param json
	 *            需要传入的json字符串
	 * @return
	 */
	public static boolean isCodeOk(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			if (jsonObject.has("code")) {
				int code = jsonObject.getInt("code");
				if (code == 1) {
					return true;
				}
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析网络最原始的json数据
	 * 
	 * @param jsonObject
	 *            网络上最原始的json字符串，必须经过判断是否存在实际的数据后再解析
	 * @param ModelType
	 *            需要解析成model类型，一般是model的子類
	 * @return
	 */
	public static Model parseOriginalJsonObject(String jsonObject,
			Model ModelType) {
		Model model = null;
		try {
			if (isCodeOk(jsonObject.toString())) {
				JSONObject json = new JSONObject(jsonObject.toString());
				if (json.has("data")) {
					JSONObject modelData = json.getJSONObject("data");
					model = JsonUtils.parseJsonObject(modelData, ModelType);
					Log.i("model", "model=" + model.toString());
					return model;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return model;

	}

	/**
	 * 返回解析后的model對象list
	 * 
	 * @param jsonObject
	 *            网络上最原始的json字符串，必须经过判断是否存在实际的数据后再解析
	 * @param ModelType
	 *            需啊解析成model 类型
	 * @return
	 */
	public static List<Model> parseOriginalJsonArray(String jsonObject,
			Model ModelType) {
		List<Model> list = null;
		if (isCodeOk(jsonObject.toString())) {
			JSONObject json;
			try {
				json = new JSONObject(jsonObject.toString());
				if (json.has("data")) {
					JSONArray array = json.getJSONArray("data");
					list = JsonUtils.parseJsonArray(array, ModelType);
					return list;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
