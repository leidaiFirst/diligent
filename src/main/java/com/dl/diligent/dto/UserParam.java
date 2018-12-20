package com.dl.diligent.dto;

import lombok.Data;

/**
 * 表 lab_user
 * 
 * @author dail
 * @date 2018-11-22
 */
@Data
public class UserParam
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
}
