package cn.liyu.common.core.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liyu
 * date 2023/7/4 14:16
 * description:
 */
@Data
public class UserExtend {
    private Long id;
    private Long userId;

    /**
     * 用户住址
     */
    private String address;

    /**
     * 出生日期
     */
    private LocalDate birthday;
    private LocalDateTime lastLoginTime;
}
