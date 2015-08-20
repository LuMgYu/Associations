package com.zhiyisoft.associations.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelAssociation;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueDetail;
import com.zhiyisoft.associations.model.ModelLeagueList;
import com.zhiyisoft.associations.model.ModelMask;
import com.zhiyisoft.associations.model.ModelRegister;
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

	public static final class RegisterImpl implements RegisterIm {

		@Override
		public boolean appSendSMSCode(ModelRegister model) {
			Request get = new Get();
			get.setHostUrlFooter(Config.appSendSMSCode);
			get.addBodyParam(MOBILE, model.getMobile());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model appUserReg(ModelRegister model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUserReg);
			post.addBodyParam(MOBILE, model.getMobile());
			post.addBodyParam(PWD, model.getPwd());
			post.addBodyParam(SMSCODE, model.getSmscode());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelRegister());
		}

		@Override
		public Model validMobile(ModelRegister model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.validMobile);
			post.addBodyParam(MOBILE, model.getMobile());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelRegister());
		}
	}

	/**
	 * @author qcj 登录类
	 *
	 */
	public static final class LoginImpl implements LoginIm {

		@Override
		public Model appUserMobileLogin(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUserMobileLogin);
			post.addBodyParam(MOBILE, model.getMobile());
			post.addBodyParam(PWD, model.getPwd());
			post.addBodyParam(TOURL, model.getToUrl());
			post.addBodyParam(PUSHCHNLID, model.getPushchnlid());
			post.addBodyParam(PUSHUSERID, model.getPushuserid());
			post.addBodyParam(DEVICETYPE, model.getDevicetype());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model appUserAccountLogin(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUserAccountLogin);
			post.addBodyParam(ACCOUNT, model.getAccount());
			post.addBodyParam(PWD, model.getPwd());
			post.addBodyParam(TOURL, model.getToUrl());
			post.addBodyParam(PUSHCHNLID, model.getPushchnlid());
			post.addBodyParam(PUSHUSERID, model.getPushuserid());
			post.addBodyParam(DEVICETYPE, model.getDevicetype());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model boundedUserLogin(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.boundedUserLogin);
			post.addBodyParam(OPENID, model.getOpenid());
			post.addBodyParam(TOURL, model.getToUrl());
			post.addBodyParam(PUSHCHNLID, model.getPushchnlid());
			post.addBodyParam(PUSHUSERID, model.getPushuserid());
			post.addBodyParam(DEVICETYPE, model.getDevicetype());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model boundMobileUser(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.boundMobileUser);
			post.addBodyParam(MOBILE, model.getMobile());
			post.addBodyParam(PWD, model.getPwd());
			post.addBodyParam(OPENID, model.getOpenid());
			post.addBodyParam(TOURL, model.getToUrl());
			post.addBodyParam(PUSHCHNLID, model.getPushchnlid());
			post.addBodyParam(PUSHUSERID, model.getPushuserid());
			post.addBodyParam(DEVICETYPE, model.getDevicetype());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model appValidateUserAuth(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appValidateUserAuth);
			post.addBodyParam(USERAUTH, model.getUserauth());
			post.addBodyParam(TOURL, model.getToUrl());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model appValidateUserPwd(ModelUser model) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appValidateUserPwd);
			post.addBodyParam(USERAUTH, model.getUserauth());
			post.addBodyParam(PWD, model.getPwd());
			Object object = post.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

	}

	/**
	 * @author qcj 查詢學校的實現類
	 *
	 */
	public static final class SchoolImpl implements SchoolIm {

		@Override
		public List<Model> appMetaData(ModelSchool school) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appMetaData);
			post.addBodyParam(METANAME, school.getMetaName());
			post.addBodyParam(METAID, school.getMetaID());
			post.addBodyParam(OWNERID, school.getOwnerID());
			post.addBodyParam(USERAUTH, school.getUserauth());
			Object object = post.run();
			return parseOriginalJsonArray(object.toString(), new ModelSchool());
		}

		@Override
		public boolean appUpdateUserArea(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserArea);
			post.addBodyParam(PROVINCEID, user.getProvince());
			post.addBodyParam(CITYID, user.getCityid());
			post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserSchool(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserSchool);
			post.addBodyParam(SCHOOLID, user.getSchoolId());
			post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserDept(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserDept);
			post.addBodyParam(DEPTID, user.getDeptid());
			post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserClass(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserClass);
			post.addBodyParam(CLASSID, user.getClassid());
			post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateGradeYear(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateGradeYear);
			post.addBodyParam(GRADEYEAR, user.getGradeyear());
			post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> getSchools(ModelSchool model) {
			if (model != null) {
				Request get = new Get();
				Log.i("getSchools", "getSchools(ModelSchool model)---");
				get.setHostUrl("http://daxs.zhiyicx.com/api");
				get.addBodyParam(MOD, SchoolIm.TOOL);
				get.addBodyParam(ACT, SchoolIm.SCHOOLBYPROVINCE);
				get.addBodyParam("name", model.getArea());
				Object object = get.run(); // 利用网络请求数据，然后返回的数据
				return parseOriginalJsonArray(object, new ModelSchool());
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
			post.setHostUrl("http://daxs.zhiyicx.com/api");
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
			return parseOriginalJsonObject(object, new ModelLeagueDetail());
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
	public static boolean isCodeOk(Object json) {
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json.toString());
				if (jsonObject.has("state")) {
					boolean state = false;
					state = jsonObject.getBoolean("state");
					return state;
				}
				if (jsonObject.has("code")) {
					int code = 0;
					code = jsonObject.getInt("code");
					if (code == 1) {
						return true;
					}
					return false;
				}
			} catch (JSONException e) {
				e.printStackTrace();

			}
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
	public static Model parseOriginalJsonObject(Object jsonObject,
			Model ModelType) {
		Model model = null;
		if (jsonObject != null) {
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
	public static List<Model> parseOriginalJsonArray(Object jsonObject,
			Model ModelType) {
		List<Model> list;
		if (jsonObject != null) {
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
		}
		return null;

	}
}
