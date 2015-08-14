package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:43:07
 * 
 * 
 * 接口描述：根据省名获取学校列表
 *
 */

public interface SchoolIm {
	/*
	 * 元数据需要的字段 metaName metaID ownerID userauth
	 */
	public static final String METANAME = "metaName";
	public static final String METAID = "metaID";
	public static final String OWNERID = "ownerID";

	public static final String PROVINCEID = "provinceId";
	public static final String CITYID = "cityId";
	public static final String USERAUTH = "userauth";
	public static final String SCHOOLID = "schoolId";
	public static final String DEPTID = "deptId";
	public static final String CLASSID = "classId";
	public static final String GRADEYEAR = "gradeYear";

	public static final String TOOL = "tool";
	public static final String SCHOOLBYPROVINCE = "schoolbyprovince";

	/**
	 * 根据省名返回该省的学校的集合
	 * 
	 * @param province
	 *            省名
	 * @return 返回学校的集合
	 */
	List<Model> getSchools(ModelSchool model);

	/**
	 * 元数据接口
	 * 
	 * @param school
	 * 
	 * @return 返回学校的集合
	 */
	List<Model> appMetaData(ModelSchool school);

	/**
	 * 修改所在地区 需要传递的字段provinceId cityId userauth
	 * 
	 * 返回的值 eg："state": true, "message": "操作成功", "data": {}
	 */
	boolean appUpdateUserArea(ModelUser user);

	/**
	 * 修改所在学校 需要传递的字段schoolId userauth
	 * 
	 * 返回的值 eg："state": true, "message": "操作成功", "data": {}
	 */
	boolean appUpdateUserSchool(ModelUser user);

	/**
	 * 修改所在院系 需要传递的字段deptId userauth
	 * 
	 * 返回的值 eg："state": true, "message": "操作成功", "data": {}
	 */
	boolean appUpdateUserDept(ModelUser user);

	/**
	 * 修改所在班级 需要传递的字段classId userauth
	 * 
	 * 返回的值 eg："state": true, "message": "操作成功", "data": {}
	 */
	boolean appUpdateUserClass(ModelUser user);

	/**
	 * 修改入学年份 需要传递的字段gradeYear userauth
	 * 
	 * 返回的值 eg："state": true, "message": "操作成功", "data": {}
	 */
	boolean appUpdateGradeYear(ModelUser user);
}
