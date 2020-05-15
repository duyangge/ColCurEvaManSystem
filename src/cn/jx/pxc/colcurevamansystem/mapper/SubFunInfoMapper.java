package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;

public interface SubFunInfoMapper {
	
	/**
	 * @param beanQueryVo
	 * @return
	 */
	List<SubFunInfo> selectSubFunByName(BeanQueryVo beanQueryVo);
	
	/**
	 * @param id
	 * @return
	 */
	List<SubFunInfo> selectSubFunByParentFunId(Integer id);
	
	
	
    int deleteByPrimaryKey(Integer funId);

    int insert(SubFunInfo record);

    int insertSelective(SubFunInfo record);

    SubFunInfo selectByPrimaryKey(Integer funId);

    int updateByPrimaryKeySelective(SubFunInfo record);

    int updateByPrimaryKey(SubFunInfo record);
}