package com.ethanzyc.commons.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务配置
 * </p>
 *
 * @author ethan
 * @since 2019-09-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScheduleJob extends Model<ScheduleJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 任务名称
     */
    @TableField("job_name")
    private String jobName;
    /**
     * cron表达式
     */
    @TableField("cron_expression")
    private String cronExpression;
    /**
     * 服务名称
     */
    @TableField("bean_name")
    private String beanName;
    /**
     * 方法名称
     */
    @TableField("method_name")
    private String methodName;
    /**
     * 状态 1.启动 2.暂停
     */
    @TableField("status")
    private Integer status;
    /**
     * 是否删除 0.否 1.是
     */
    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleteFlag;
    /**
     * 创建人id
     */
    @TableField(value = "creator_id",fill = FieldFill.INSERT)
    private Integer creatorId;
    /**
     * 创建人
     */
    @TableField(value = "creator_name",fill = FieldFill.INSERT)
    private String creatorName;
    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;


    public static final String ID = "id";

    public static final String JOB_NAME = "job_name";

    public static final String CRON_EXPRESSION = "cron_expression";

    public static final String BEAN_NAME = "bean_name";

    public static final String METHOD_NAME = "method_name";

    public static final String STATUS = "status";

    public static final String DELETE_FLAG = "delete_flag";

    public static final String CREATOR_ID = "creator_id";

    public static final String CREATOR_NAME = "creator_name";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
