package cn.liyu.user.mapstruct;

import cn.liyu.common.core.user.User;
import cn.liyu.common.core.user.UserExtend;
import cn.liyu.user.dao.entity.SysUser;
import cn.liyu.user.dao.entity.SysUserExtend;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author liyu
 * date 2023/7/4 14:19
 * description:
 */
@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    User SysUserToUser(SysUser sysUser);

    UserExtend SysUserExtendToUserExtend(SysUserExtend sysUserExtend);

}
