package com.zhiyisoft.associations.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.zhiyisoft.associations.model.Model;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.request.Get;
import com.zhiyisoft.associations.request.Post;
import com.zhiyisoft.associations.request.base.Request;

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
		public Object Login(ModelUser user) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addBodyParam(MOD, LoginIm.LOGIN);
			post.addBodyParam(ACT, LoginIm.INDEX);
			post.addBodyParam("mobile", "13688449697");
			post.addBodyParam("password", "13445555");
			Object object = post.run();
			Log.i("request", "object=" + object);
			return null;
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
			get.addBodyParam("name", "四川");
			Object object = get.run();
			if (isCodeOk(object.toString())) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(object.toString());
					if (jsonObject.has("data")) {
						List<Model> items = new ArrayList<Model>();
						JSONArray array = jsonObject.getJSONArray("data");
						int num = array.length();
						for (int i = 0; i < num; i++) {
							ModelSchool school = new ModelSchool(
									array.getJSONObject(i));
							items.add(school);
						}
						return items;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return null;
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
		public Object getGroupCommonList(Model mItem) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, LeagueIm.GROUP);
			get.addBodyParam(ACT, LeagueIm.GETGROUPCOMMONLIST);
			get.run();
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
			get.addBodyParam("mask", "俺是小腿");
			get.run();
			return null;
		}

		@Override
		public Object getUserActiveMaskInfo(Model item) {
			Request get = new Get();
			get.addHeaderParam("client_id", "1");
			get.addHeaderParam("uid", "6309289");
			get.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			get.addBodyParam(MOD, BaseSettingIm.USER);
			get.addBodyParam(ACT, BaseSettingIm.GETUSERACTIVITEMASKINFO);
			get.run();
			return null;
		}

		@Override
		public Object setFaceImg(Model item) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addHeaderParam("uid", "6309289");
			post.addHeaderParam("oauth_token",
					"34975aeea94b0e71311c21215daf117a");
			post.addBodyParam(MOD, BaseSettingIm.USER);
			post.addBodyParam(ACT, BaseSettingIm.SETFACEIMG);
			post.addBodyParam("url", "https://www.baidu.com/img/bd_logo1.png");
			post.addBodyParam("domain", "https://www.baidu.com");
			post.run();
			return null;
		}
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 把jsonarray解析成list<ModelItem>
	 * 
	 * @param array
	 *            需要解析的jsonarray
	 * @param typeItem
	 *            需要解析为model的類型
	 * @return
	 */
	private List<Model> parseJsonArray(JSONArray array, Model typeItem) {
		List<Model> list = new ArrayList<Model>();
		if (array != null && array.length() > 0) {
			int num = array.length();
			for (int i = 0; i < num; i++) {
				// ModelItem item = typeItem.getClass().newInstance();
			}
		}

		return null;
	}
}
