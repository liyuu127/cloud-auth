package cn.liyu.user.dao.mapper;

import cn.liyu.user.dao.entity.SysUserOauth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyu
 * date 2023/7/4 11:35
 * description:
 */
@Mapper
public interface SysUserOauthMapper extends BaseMapper<SysUserOauthMapper> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserOauth record);

    int insertSelective(SysUserOauth record);

    SysUserOauth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserOauth record);

    int updateByPrimaryKey(SysUserOauth record);
}