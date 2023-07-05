package cn.liyu.user.service;

import cn.liyu.common.core.exception.ApplicationException;
import cn.liyu.common.core.global.SysStubInfo;
import cn.liyu.common.core.user.User;
import cn.liyu.common.core.user.UserInfo;
import cn.liyu.user.dao.entity.SysUser;
import cn.liyu.user.dao.mapper.SysUserMapper;
import cn.liyu.user.mapstruct.SysUserConvert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author liyu
 * date 2023/7/4 14:05
 * description:
 */

@Service
public class UserService {

    private final SysUserMapper sysUserMapper;
    private final UserExtendService userExtendService;

    public UserService(SysUserMapper sysUserMapper, UserExtendService userExtendService) {
        this.sysUserMapper = sysUserMapper;
        this.userExtendService = userExtendService;
    }


    /**
     * 根据用户名查询用户
     *
     * @param username 登录名
     * @return User
     */
    public User findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username param can be blank");
        }

        SysUser sysUser = sysUserMapper.selectUserByLoginName(username).orElse(null);

        return SysUserConvert.INSTANCE.SysUserToUser(sysUser);
    }

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    public User findByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("userId param can be blank");
        }

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        return SysUserConvert.INSTANCE.SysUserToUser(sysUser);
    }


    /**
     * 查询单个用户
     *
     * @param userId    用户id
     * @param loginName 登录名
     * @return User
     */

    public @Nullable UserInfo queryOneUser(Long userId, String loginName) {
        UserInfo userInfo = new UserInfo();

        //基础信息
        User user = null;
        if (Objects.nonNull(userId)) {
            user = findByUserId(userId);
        } else if (StringUtils.isNotBlank(loginName)) {
            user = findByUsername(loginName);
        }
        if (Objects.isNull(user)) {
            throw new ApplicationException(SysStubInfo.NOT_FOUND);
        }
        userInfo.setUser(user);

        userId = user.getId();
        //拓展信息
        userInfo.setUserExtend(userExtendService.selectOneByUserId(userId));

        //角色code mock
        List<String> roleScope = Arrays.asList("admin", "normal", "USER");
        userInfo.setRoleScope(roleScope);

        //权限code mock
        List<String> permissionScope = Arrays.asList("messages:add", "messages:delete");
        userInfo.setPermissionScope(permissionScope);

        return userInfo;

    }
}
