package cn.liyu.user.dao.mapper;

import cn.liyu.user.dao.entity.SysUserExtend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liyu
 * date 2023/7/4 11:35
 * description:
 */
@Mapper
public interface SysUserExtendMapper extends BaseMapper<SysUserExtendMapper> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserExtend record);

    int insertSelective(SysUserExtend record);

    SysUserExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserExtend record);

    int updateByPrimaryKey(SysUserExtend record);

    SysUserExtend selectOneByUserId(@Param("userId") Long userId);
}