package com.ethanzyc.commons.business.domain.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工注册信息
 * </p>
 *
 * @author ethan
 * @since 2019-09-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterDto {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    private Integer id;
    /**
     * 门店id，使用位图
     */
    @NotNull(message = "门店不能为空")
    private Integer shopIds;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 用户名唯一
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空")
    private String jobCode;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String passWord;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String passWordAgain;
    /**
     * 用户头像Url
     */
    private String userImageUrl;
    /**
     * 性别（0：女；1：男；2：未设定）
     */
    @NotNull(message = "用户名不能为空")
    private Integer gender;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verityCode;

}
