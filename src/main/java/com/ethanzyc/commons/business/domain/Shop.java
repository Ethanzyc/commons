package com.ethanzyc.commons.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 公司表
 * </p>
 *
 * @author ethan
 * @since 2019-09-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 公司编号
     */
    @TableField("company_no")
    private String companyNo;
    /**
     * 公司全称
     */
    @TableField("name")
    private String name;
    /**
     * 公司简称
     */
    @TableField("short_name")
    private String shortName;
    /**
     * 公司地址
     */
    @TableField("address")
    private String address;
    /**
     * 公司logo
     */
    @TableField("company_logo")
    private String companyLogo;
    /**
     * 公司电话
     */
    @TableField("service_phone")
    private String servicePhone;
    /**
     * 公司介绍
     */
    @TableField("introduce")
    private String introduce;
    /**
     * 状态 1 在用 0 禁用
     */
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


    public static final String ID = "id";

    public static final String COMPANY_NO = "company_no";

    public static final String NAME = "name";

    public static final String SHORT_NAME = "short_name";

    public static final String ADDRESS = "address";

    public static final String COMPANY_LOGO = "company_logo";

    public static final String SERVICE_PHONE = "service_phone";

    public static final String INTRODUCE = "introduce";

    public static final String STATE = "state";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
