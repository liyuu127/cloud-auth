package cn.liyu.user.dao.mapper;

import cn.liyu.user.dao.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * @author liyu
 * date 2023/7/4 11:35
 * description:
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserMapper> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    Optional<SysUser> selectUserByLoginName(@Param("username") String username);
}