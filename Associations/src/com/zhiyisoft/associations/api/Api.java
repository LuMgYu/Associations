package com.zhiyisoft.associations.api;

import android.util.Log;

import com.zhiyisoft.associations.impl.LoginImpl;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.request.Post;
import com.zhiyisoft.associations.request.base.Request;

/**
 * author：qiuchunjia time：下午2:32:28 类描述：这个类是实现调用api的接口
 *
 */

public class Api {
	/**
	 * @author qcj 登陆类
	 *
	 */
	public static final class Login implements LoginImpl {
		@Override
		public Object registerMem(ModelUser user) {
			Request post = new Post();
			post.addHeaderParam("client_id", "1");
			post.addBodyParam("mod", LoginImpl.MOD);
			post.addBodyParam("act", LoginImpl.ACT);
			post.addBodyParam("mobile", 1234567888);
			post.addBodyParam("password", 1234567888);
			Object object = post.run();
			Log.i("request", "object=" + object);
			return null;
		}

		@Override
		public Object Login(ModelUser user) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
