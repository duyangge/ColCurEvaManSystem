package cn.jx.pxc.colcurevamansystem.mapper;

import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}