package com.zhiyisoft.associations.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelComment;
import com.zhiyisoft.associations.model.ModelError;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelEventWorks;
import com.zhiyisoft.associations.model.ModelHome;
import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelMask;
import com.zhiyisoft.associations.model.ModelMember;
import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.model.ModelRegister;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelTiding;
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
	public static final String APP = "app";
	public static final String MOD = "mod";
	public static final String ACT = "act";
	// 由于这个基本上都用到，就写到这里吧
	public static final String API = "api";
	public static final String oauth_token = "oauth_token";
	public static final String oauth_token_secret = "oauth_token_secret";
	public static final String P = "p"; // 用于数据分页
	public static ModelUser mUser;

	public static final class RegisterImpl implements RegisterIm {

		@Override
		public boolean appSendSMSCode(ModelRegister model) {
			Request get = new Post();
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
		public Model authorize(ModelUser model) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, AUTHORIZE);
			get.addBodyParam(LOGIN1, model.getMobile());
			get.addBodyParam(PWD, model.getPwd());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model logout(ModelUser model) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, LOGOUT);
			get.addBodyParam(UID, model.getUserid());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public Model getOtherLoginInfo(ModelUser model) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, GET_OTHER_LOGIN_INFO);
			get.addBodyParam(TYPE, model.getType());
			get.addBodyParam(TYPE_UID, model.getType_uid());
			get.addBodyParam(ACCESS_TOKEN, model.getAccess_token());
			get.addBodyParam(REFRESH_TOKEN, model.getRefresh_token());
			get.addBodyParam(EXPIRE_IN, model.getExpire_in());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public boolean bindNewUser(ModelUser model) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, BIND_NEW_USER);
			get.addBodyParam(UID, model.getUserid());
			get.addBodyParam(TYPE, model.getType());
			get.addBodyParam(TYPE_UID, model.getType_uid());
			get.addBodyParam(ACCESS_TOKEN, model.getAccess_token());
			get.addBodyParam(REFRESH_TOKEN, model.getRefresh_token());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model sendRegisterCode(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, SEND_REGISTER_CODE);
			get.addBodyParam(MOBILE, user.getMobile());
			Object object = get.run();
			getModelError(object);
			return getModelError(object);
		}

		@Override
		public Model register(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, LOGIN);
			get.addBodyParam(ACT, REGISTER);
			get.addBodyParam(MOBILE, user.getMobile());
			get.addBodyParam(REGCODE, user.getRegCode());
			get.addBodyParam(PWD, user.getPwd());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public boolean sendCodeByPhone(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, USER);
			get.addBodyParam(ACT, SENDCODEBYPHONE);
			get.addBodyParam(MOBILE, user.getMobile());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public boolean saveUserPasswordByPhone(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, USER);
			get.addBodyParam(ACT, SAVEUSERPASSWORDBYPHONE);
			get.addBodyParam(MOBILE, user.getMobile());
			get.addBodyParam(PWD, user.getPwd());
			get.addBodyParam(CODE, user.getRegCode());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model updateProfile(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, USER);
			get.addBodyParam(ACT, UPDATEPROFILE);
			if (user.getOauth_token() != null
					&& !user.getOauth_token().equals("")) {
				get.addBodyParam(oauth_token, user.getOauth_token());
				get.addBodyParam(oauth_token_secret,
						user.getOauth_token_secret());
			} else {
				judgeTheUser(get);
			}
			get.addBodyParam(UNAME, user.getUname());
			get.addBodyParam(FACEID, user.getFaceId());
			get.addBodyParam(SEX, user.getSex());
			get.addBodyParam(SCHOOL_ID, user.getschool_id());
			get.addBodyParam(AUTOGRAPH, user.getAutograph());
			get.addBodyParam(EMAIL, user.getEmail());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelUser());
		}

		@Override
		public boolean saveUserPasswordByPassword(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, USER);
			get.addBodyParam(ACT, SAVEUSERPASSWORDBYPASSWORD);
			get.addBodyParam(oauth_token, user.getOauth_token());
			get.addBodyParam(oauth_token_secret, user.getOauth_token_secret());
			get.addBodyParam(PWD, user.getPwd());
			get.addBodyParam(OLDPASSWORD, user.getOldPwd());
			Object object = get.run();
			return isCodeOk(object);
		}

		// @Override
		// public Model appValidateUserAuth(ModelUser model) {
		// Request post = new Post();
		// post.setHostUrlFooter(Config.appValidateUserAuth);
		// post.addBodyParam(USERAUTH, model.getUserauth());
		// post.addBodyParam(TOURL, model.getToUrl());
		// Object object = post.run();
		// return parseOriginalJsonObject(object, new ModelUser());
		// }
		//
		// @Override
		// public Model appValidateUserPwd(ModelUser model) {
		// Request post = new Post();
		// post.setHostUrlFooter(Config.appValidateUserPwd);
		// post.addBodyParam(USERAUTH, model.getUserauth());
		// post.addBodyParam(PWD, model.getPwd());
		// Object object = post.run();
		// return parseOriginalJsonObject(object, new ModelUser());
		// }

	}

	public static final class HomeImpl implements HomeIm {

		@Override
		public Model index() {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, HOME);
			get.addBodyParam(ACT, INDEX);
			judgeTheUser(get);
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelHome());
		}
	}

	public static final class PhotoImpl implements PhotoIm {

		@Override
		public Model Attach(ModelUser user) {
			RequestParams params = new RequestParams();
			// params.put(APP, API);
			// params.put(MOD, ATTACH);
			// params.put(ACT, FACEPIC);
			params.put(oauth_token, user.getOauth_token());
			params.put(oauth_token_secret, user.getOauth_token_secret());
			File file = user.getUploadFile();
			if (file != null) {
				try {
					params.put("file", user.getUploadFile());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			AsyncHttpClient client = new AsyncHttpClient();
			return null;
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
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserSchool(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserSchool);
			// post.addBodyParam(SCHOOLID, user.getschool_id());
			// post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserDept(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserDept);
			// post.addBodyParam(DEPTID, user.getDeptid());
			// post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateUserClass(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateUserClass);
			// post.addBodyParam(CLASSID, user.getClassid());
			// post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public boolean appUpdateGradeYear(ModelUser user) {
			Request post = new Post();
			post.setHostUrlFooter(Config.appUpdateGradeYear);
			// post.addBodyParam(GRADEYEAR, user.getGradeyear());
			// post.addBodyParam(USERAUTH, user.getUserauth());
			Object object = post.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> getSchools(ModelSchool model) {
			if (model != null) {
				Request get = new Get();
				get.addBodyParam(APP, API);
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

		@Override
		public boolean createGroup(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, CREATEGROUP);
			judgeTheUser(get);
			get.addBodyParam(NAME, league.getName());
			get.addBodyParam(CATEGORYID, league.getCategoryId());
			get.addBodyParam(LOGO, league.getLogo());
			get.addBodyParam(DESCRIPTION, league.getDescription());
			get.addBodyParam(SCHOOLID, league.getSchoolId());
			get.addBodyParam(PRIVATE, league.getPrivate());
			get.addBodyParam(OPENERNAME, league.getOpenerName());
			get.addBodyParam(CONTACT, league.getContact());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> groupIndex(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, INDEX);
			judgeTheUser(get);
			if (league.getSchoolId() > 0)
				get.addBodyParam(SCHOOLID, league.getSchoolId());
			if (league.getCategoryId() > 0)
				get.addBodyParam(CATEGORYID, league.getCategoryId());
			if (league.getName() != null && league.getName().length() > 1)
				get.addBodyParam(NAME, league.getName());
			get.addBodyParam(P, league.getP());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelLeague());
		}

		@Override
		public boolean join(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, JOIN);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public boolean leave(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, LEAVE);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model view(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, VIEW);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelLeague());
		}

		@Override
		public Model viewIn(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, VIEWIN);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelLeague());
		}

		@Override
		public List<Model> memberList(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, MEMBERLIST);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelMember());
		}

		@Override
		public List<Model> albumList(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, ALBUMLIST);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelLeagueAlbum());
		}

		@Override
		public boolean createAlbum(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, CREATEALBUM);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			get.addBodyParam(ALBUMNAME, league.getAlbumName());
			get.addBodyParam(ALBUMINFO, league.getAlbumInfo());
			get.addBodyParam(ALBUMHIDE, league.getHide());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> photoList(ModelLeagueAlbum league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, PHOTOLIST);
			judgeTheUser(get);
			get.addBodyParam(ALBUMID, league.getId());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelLeagueAlbum());
		}

		@Override
		public List<Model> topicList(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, TOPICLIST);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			get.addBodyParam(P, league.getP());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelLeagueTopic());
		}

		@Override
		public Model topicView(ModelLeagueTopic topic) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, TOPICVIEW);
			judgeTheUser(get);
			get.addBodyParam(TID, topic.getTid());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelLeagueTopic());
		}

		@Override
		public boolean replyTopic(ModelLeagueTopic topic) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, REPLYTOPIC);
			judgeTheUser(get);
			get.addBodyParam(TID, topic.getTid());
			get.addBodyParam(CONTENT, topic.getReplyContent());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public boolean replyPost(ModelLeagueTopic topic) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, REPLYPOST);
			judgeTheUser(get);
			get.addBodyParam(PID, topic.getPid());
			get.addBodyParam(CONTENT, topic.getReplyContent());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> getTopicPosts(ModelLeagueTopic topic) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, GETTOPICPOSTS);
			judgeTheUser(get);
			get.addBodyParam(TID, topic.getTid());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelComment());
		}

		@Override
		public List<Model> joinedGroup(ModelUser user) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, JOINEDGROUP);
			judgeTheUser(get);
			if (user != null) {
				get.addBodyParam(P, user.getP());
			}
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelLeague());
		}

		@Override
		public Model getGroupBaseInfo(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, GETGROUPBASEINFO);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelLeague());
		}

		@Override
		public List<Model> groupWorks(ModelLeague league) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, GROUPWORKS);
			judgeTheUser(get);
			get.addBodyParam(GID, league.getGid());
			get.addBodyParam(TYPE, league.getType());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelEventWorks());
		}

		@Override
		public Model groupPhotoView(ModelLeagueAlbum photoModel) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, GROUPPHOTOVIEW);
			judgeTheUser(get);
			get.addBodyParam(ID, photoModel.getId());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelEventWorks());
		}

		@Override
		public List<Model> groupNewsList(ModelTiding tiding) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, GROUP);
			get.addBodyParam(ACT, GROUPNEWSLIST);
			judgeTheUser(get);
			get.addBodyParam(GIDID, tiding.getGid());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelTiding());
		}

		@Override
		public Model groupNewsDetail(ModelTiding tiding) {
			// TODO Auto-generated method stub
			// 突然发现不需要这个接口 卧槽
			return null;
		}
	}

	public static final class EventImpl implements EventIm {
		@Override
		public List<Model> eventList(ModelEvent event) {
			if (event != null) {
				Request get = new Get();
				get.addBodyParam(APP, API);
				get.addBodyParam(MOD, EVENT);
				get.addBodyParam(ACT, EVENTLIST);
				judgeTheUser(get);
				get.addBodyParam(TYPE, event.getType());
				get.addBodyParam(OP, event.getOp());
				get.addBodyParam(GID, event.getGid());
				get.addBodyParam(P, event.getP());
				Object object = get.run();
				return parseOriginalJsonArray(object, new ModelEvent());
			}
			return null;
		}

		@Override
		public boolean createEvent(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, CREATEEVENT);
			judgeTheUser(get);
			get.addBodyParam(ONLINE, event.getOnline());
			get.addBodyParam(LOGO, event.getLogo());
			get.addBodyParam(GID, event.getGid());
			get.addBodyParam(TITLE, event.getTitle());
			get.addBodyParam(ADDRESS, event.getAddress());
			get.addBodyParam(TYPE, event.getType());
			get.addBodyParam(HOST, event.getHost());
			get.addBodyParam(EXPLAIN, event.getExplain());
			get.addBodyParam(STIME, event.getsTime());
			get.addBodyParam(ETIME, event.geteTime());
			get.addBodyParam(JOINAUDIT, event.getJoinAudit());
			get.addBodyParam(JOINSTIME, event.getsTime());
			get.addBodyParam(JOINETIME, event.getJoinEtime());
			get.addBodyParam(WORKAUDIT, event.getWorkAudit());
			get.addBodyParam(WORKSPURVIEW, event.getWorksPurview());
			get.addBodyParam(WORKSTIME, event.getWorkStime());
			get.addBodyParam(WORKETIME, event.getWorkEtime());
			get.addBodyParam(RANGEDES, event.getRangeDes());
			get.addBodyParam(EXPLAINTYPE, event.getExplainType());
			get.addBodyParam(LONGTITUDE, event.getLongtitude());
			get.addBodyParam(LATITUDE, event.getLatitude());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model eventView(ModelEvent event) {
			if (event != null) {
				Request get = new Get();
				get.addBodyParam(APP, API);
				get.addBodyParam(MOD, EVENT);
				get.addBodyParam(ACT, EVENTVIEW);
				judgeTheUser(get);
				get.addBodyParam(ID, event.getId());
				Object object = get.run();
				return parseOriginalJsonObject(object, new ModelEvent());
			}
			return null;
		}

		@Override
		public Model join(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, JOIN);
			judgeTheUser(get);
			get.addBodyParam(ID, event.getId());
			Object object = get.run();
			Model model = getModelError(object);
			return model;
		}

		@Override
		public boolean sub(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, SUB);
			judgeTheUser(get);
			get.addBodyParam(ID, event.getId());
			get.addBodyParam(SUB, event.getStub());
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public List<Model> memberList(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, MEMBERLIST);
			judgeTheUser(get);
			get.addBodyParam(ID, event.getId());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelMember());
		}

		@Override
		public List<Model> workList(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, WORKLIST);
			judgeTheUser(get);
			get.addBodyParam(ID, event.getId());
			get.addBodyParam(P, event.getP());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelEventWorks());
		}

		@Override
		public Model workView(ModelEventWorks event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, WORKVIEW);
			judgeTheUser(get);
			get.addBodyParam(ID, event.getId());
			Object object = get.run();
			return parseOriginalJsonObject(object, new ModelEventWorks());
		}

		@Override
		public boolean comment(ModelEventWorks event) {
			return false;
		}

		@Override
		public List<Model> getNearbyEvents(ModelEvent event) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, EVENT);
			get.addBodyParam(ACT, GETNEARBYEVENTS);
			judgeTheUser(get);
			get.addBodyParam(CITY, event.getCity());
			get.addBodyParam(PROVINCE, event.getProvince());
			get.addBodyParam(P, event.getP());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelEvent());
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

	public static final class CommentImpl implements CommentIm {

		@Override
		public List<Model> commentList(ModelComment comment) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, COMMENT);
			get.addBodyParam(ACT, COMMENTLIST);
			judgeTheUser(get);
			get.addBodyParam(COMMENTAPP, comment.getCommentApp());
			get.addBodyParam(TYPE, comment.getType());
			get.addBodyParam(SOURCEID, comment.getSourceId());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelComment());
		}

		@Override
		public boolean comment(ModelComment comment) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, COMMENT);
			get.addBodyParam(ACT, COMMENTS);
			judgeTheUser(get);
			get.addBodyParam(COMMENTAPP, comment.getCommentApp());
			get.addBodyParam(TYPE, comment.getType());
			get.addBodyParam(SOURCEID, comment.getSourceId());
			get.addBodyParam(CONTENT, comment.getContent());
			get.addBodyParam(REPLYCOMMENTID, comment.getReplyCommentId());
			Object object = get.run();
			return isCodeOk(object);
		}

	}

	public static final class NotifyImpl implements NotifyIm {

		@Override
		public List<Model> notifyList(ModelNotify notify) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, NOTIFY);
			get.addBodyParam(ACT, NOTIFYLIST);
			judgeTheUser(get);
			if (notify != null) {
				get.addBodyParam(P, notify.getP());
			}
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelNotify());
		}

		@Override
		public boolean setRead(ModelNotify notify) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, NOTIFY);
			get.addBodyParam(ACT, SETREAD);
			judgeTheUser(get);
			if (notify != null) {
				get.addBodyParam(ID, notify);
			}
			Object object = get.run();
			return isCodeOk(object);
		}

		@Override
		public Model sendMsg(ModelMsg msg) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, WALL);
			get.addBodyParam(ACT, PUBLISH);
			judgeTheUser(get);
			get.addBodyParam(TUID, msg.getUid());
			get.addBodyParam(CONTENT, msg.getContent());
			Object object = get.run();
			return getModelError(object);
		}

		@Override
		public List<Model> wallList(ModelMsg msg) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, WALL);
			get.addBodyParam(ACT, WALLLIST);
			judgeTheUser(get);
			get.addBodyParam(P, msg.getP());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelMsg());
		}

		@Override
		public List<Model> notifyList(ModelMsg msg) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, WALL);
			get.addBodyParam(ACT, NOTIFYLISTMSG);
			judgeTheUser(get);
			get.addBodyParam(UID, msg.getUid());
			get.addBodyParam(LAST_ID, msg.getNotifyId());
			Object object = get.run();
			return parseOriginalJsonArray(object, new ModelMsg());
		}

		@Override
		public Model delNotify(ModelMsg msg) {
			Request get = new Get();
			get.addBodyParam(APP, API);
			get.addBodyParam(MOD, WALL);
			get.addBodyParam(ACT, DELNOTIFY);
			judgeTheUser(get);
			get.addBodyParam(UID, msg.getUid());
			Object object = get.run();
			return getModelError(object);
		}

	}

	/**
	 * 添加验证信息
	 * 
	 * @param get
	 */
	public static void judgeTheUser(Request get) {
		mUser = Association.getUser();
		if (mUser != null) {
			get.addBodyParam(oauth_token, mUser.getOauth_token());
			get.addBodyParam(oauth_token_secret, mUser.getOauth_token_secret());
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
				if (jsonObject.has("status")) {
					int code = 0;
					code = jsonObject.getInt("status");
					if (code == 1) {
						return true;
					}
					return false;
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

	public static Model getModelError(Object json) {
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json.toString());
				Model model = JsonUtils.parseJsonObject(jsonObject,
						new ModelError());
				return model;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
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
						// Log.i("model", "model=" + model.toString());
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
