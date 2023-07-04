package cn.liyu.user.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liyu
 * date 2023/7/4 11:35
 * description:
 */
@Data
@TableName(value = "sys_user_oauth")
public class SysUserOauth implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    /**
     * 第三方登录类型 1qq、2wechat
     */
    @TableField(value = "oauth_type")
    private Integer oauthType;

    /**
     * 第三放id
     */
    @TableField(value = "oauth_id")
    private String oauthId;

    /**
     * 多方联合id
     */
    @TableField(value = "unionid")
    private String unionid;

    /**
     * 登录凭证
     */
    @TableField(value = "credential")
    private String credential;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 逻辑删除标识 0未删除 1已删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}