package cn.jx.pxc.colcurevamansystem.mapper;

import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;

public interface RoleFunInfoMapper {
	
	
	 /**
	 * @param id
	 * @return
	 */
	RoleFunInfo selectByFunId(Integer id);
	
	/**
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	int deleteByFunId(Integer roleId) throws Exception;
	
    int deleteByPrimaryKey(Integer roleFunId);

    int insert(RoleFunInfo record);

    int insertSelective(RoleFunInfo record);

    RoleFunInfo selectByPrimaryKey(Integer roleFunId);

    int updateByPrimaryKeySelective(RoleFunInfo record);

    int updateByPrimaryKey(RoleFunInfo record);
}