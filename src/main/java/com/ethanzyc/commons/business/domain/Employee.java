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
 * 员工信息表
 * </p>
 *
 * @author ethan
 * @since 2019-09-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 门店id，使用位图
     */
    @TableField("shop_ids")
    private Integer shopIds;
    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer departmentId;
    /**
     * 用户名唯一
     */
    @TableField("user_name")
    private String userName;
    /**
     * 工号
     */
    @TableField("job_code")
    private String jobCode;
    /**
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 微信
     */
    @TableField("wx")
    private String wx;
    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;
    /**
     * 用户头像Url
     */
    @TableField("user_image_url")
    private String userImageUrl;
    /**
     * 生日
     */
    @TableField("birthday")
    private Date birthday;
    /**
     * 性别（0：女；1：男；2：未设定）
     */
    @TableField("gender")
    private Integer gender;
    /**
     * 员工邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 身份证号
     */
    @TableField("identity_card")
    private String identityCard;
    /**
     * 是否启用（0：未启用；1：已启用；2：待审核）
     */
    @TableField("is_active")
    private Integer isActive;
    /**
     * 是否删除（0：未删除；1：已删除）
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建者
     */
    @TableField("create_operator")
    private Integer createOperator;
    /**
     * 添加时间
     */
    @TableField("create_datetime")
    private Date createDatetime;
    /**
     * 更新者
     */
    @TableField("update_operator")
    private Integer updateOperator;
    /**
     * 更新时间
     */
    @TableField("update_datetime")
    private Date updateDatetime;


    public static final String ID = "id";

    public static final String SHOP_IDS = "shop_ids";

    public static final String DEPARTMENT_ID = "department_id";

    public static final String USER_NAME = "user_name";

    public static final String JOB_CODE = "job_code";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String WX = "wx";

    public static final String PASS_WORD = "pass_word";

    public static final String USER_IMAGE_URL = "user_image_url";

    public static final String BIRTHDAY = "birthday";

    public static final String GENDER = "gender";

    public static final String EMAIL = "email";

    public static final String IDENTITY_CARD = "identity_card";

    public static final String IS_ACTIVE = "is_active";

    public static final String IS_DELETE = "is_delete";

    public static final String CREATE_OPERATOR = "create_operator";

    public static final String CREATE_DATETIME = "create_datetime";

    public static final String UPDATE_OPERATOR = "update_operator";

    public static final String UPDATE_DATETIME = "update_datetime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
