package com.dl.diligent.mapper;

import com.dl.diligent.dto.DiUserResult;
import com.dl.diligent.dto.UserParam;
import com.dl.diligent.entity.DiUser;

import java.util.List;

public interface DiUserMapper {

    /**
     * 更新一个用户，如果字段为null，则不更新数据库字段
     *
     * @param record 用户信息
     * @return 更新条数
     */
    int updateByPrimaryKeySelective(DiUser record);


    /**
     * 插入一个用户，如果字段为null，则使用数据库默认值
     *
     * @param record 用户信息
     * @return 插入成功条数
     */
    int insertSelective(DiUser record);

    /**
     * 根据查询条件查询用户列表，使用前请看mapper.xml 文件中condition 节点是否已经添加所需要的条件
     *
     * @param example 查询条件
     * @return 用户列表
     */
    List<DiUserResult> selectByExample(UserParam example);


}
