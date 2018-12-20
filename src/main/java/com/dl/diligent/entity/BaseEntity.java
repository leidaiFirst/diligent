package com.dl.diligent.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dail
 * @since 2018/11/20 16:35
 */

@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 创建者id
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改者id
     */
    private Long modifier;

    /**
     * 修改时间
     */
    private Date modifyDate;


    /**
     * 是否删除 0否 1是
     */
    private int isDelete;
}
