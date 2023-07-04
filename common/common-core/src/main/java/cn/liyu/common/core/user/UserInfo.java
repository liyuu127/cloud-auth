package cn.liyu.common.core.user;

import lombok.Data;

import java.util.List;

/**
 * @author liyu
 * date 2023/7/4 14:16
 * description:
 */
@Data
public class UserInfo {
    private User user;
    private UserExtend userExtend;

    private List<String> roleScope;

    private List<String> permissionScope;
}
