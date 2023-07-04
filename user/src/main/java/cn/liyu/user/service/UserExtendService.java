package cn.liyu.user.service;

import cn.liyu.common.core.user.UserExtend;
import cn.liyu.user.dao.entity.SysUserExtend;
import cn.liyu.user.dao.mapper.SysUserExtendMapper;
import cn.liyu.user.mapstruct.SysUserConvert;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author liyu
 * date 2023/7/4 14:05
 * description:
 */

@Service
public class UserExtendService {

    private final SysUserExtendMapper userExtendMapper;

    public UserExtendService(SysUserExtendMapper userExtendMapper) {
        this.userExtendMapper = userExtendMapper;
    }


    /**
     * 根据用户id查询用户拓展信息
     *
     * @param userId 用户id
     * @return UserExtend
     */
    public UserExtend selectOneByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("userId param can be blank");
        }

        SysUserExtend userExtend = userExtendMapper.selectOneByUserId(userId);
        return SysUserConvert.INSTANCE.SysUserExtendToUserExtend(userExtend);
    }


}
