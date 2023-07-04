package cn.liyu.user.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liyu
 * date 2023/7/4 11:35
 * description: 
 */
@Data
@TableName(value = "sys_user_extend")
public class SysUserExtend implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_id")
    private Long userId;

    /**
    * 用户住址
    */
    @TableField(value = "address")
    private String address;

    /**
    * 出生日期
    */
    @TableField(value = "birthday")
    private LocalDate birthday;
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

    private static final long serialVersionUID = 1L;
}