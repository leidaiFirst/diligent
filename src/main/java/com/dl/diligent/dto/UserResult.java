package com.dl.diligent.dto;

import lombok.Data;

/**
 * 表 lab_user
 * 
 * @author dail
 * @date 2018-11-22
 */
@Data
public class UserResult extends BaseParam
{

	private Long id;

	/**
	 *open_id
	 */
	private String openId;
	
	/**
	 *用户昵称
	 */
	private String nickName;
	
	/**
	 *性别
	 */
	private Integer sex;
	
	/**
	 *头像
	 */
	private String avatar;
	
	/**
	 *省份
	 */
	private String province;
	
	/**
	 *城市
	 */
	private String city;

	
}
