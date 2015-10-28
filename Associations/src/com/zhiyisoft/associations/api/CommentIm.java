package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelComment;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午4:52:39 类描述：这个类是实现一级评论二级评论
 *
 */

public interface CommentIm {
	public static final String COMMENT = "Comment";
	public static final String COMMENTLIST = "commentList";
	public static final String COMMENTS = "comment";

	public static final String COMMENTAPP = "commentApp";
	public static final String TYPE = "type";
	public static final String SOURCEID = "sourceId";
	public static final String CONTENT = "content";
	public static final String REPLYCOMMENTID = "replyCommentId";

	/**
	 * comment/commentList 评论列表
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Comment&act=commentList
	 * 
	 * 输入参数： (string) commentApp 评论app必填 (string) type 评论类型 必填 (int) sourceId
	 * 资源id必填
	 * 
	 * @param event
	 * @return
	 */
	List<Model> commentList(ModelComment event);

	/**
	 * 45.【添加评论】：Comment/comment
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Comment&act=comment
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填 (int)
	 * sourceId 资源id 必填 (string) commentApp 评论app 必填 (string) type 评论类型 必填
	 * (string) content 内容 必填 (int) replyCommentId 评论id 选填 （用于二级评论）
	 * 
	 * @param event
	 * @return
	 */
	Model comment(ModelComment event);

}
