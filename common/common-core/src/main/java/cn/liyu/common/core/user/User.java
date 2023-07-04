package cn.liyu.common.core.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liyu
 * date 2023/7/4 14:16
 * description:
 */
@Data
public class User {
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录口令
     */
    private String password;

    /**
     * 状态:1未激活 2正常 3锁定 4冻结
     */
    private Integer state;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;
}
