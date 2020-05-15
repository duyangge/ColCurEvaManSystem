package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;

/**
 *<p> Title:  ParentFunInfoMapper.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月22日下午5:53:24
 * @version 版本号
 */
public interface ParentFunInfoMapper {
	
	
	/**通过角色id查询所有的一级菜单
	 * @param roleId
	 * @return
	 */
	List<ParentFunInfo> selelctByRoleId(Integer roleId);
	
	
	/**
	 * @param beanQueryVo
	 * @return
	 */
	List<ParentFunInfo> selectByName(BeanQueryVo beanQueryVo);
	
	/**
	 * @param beanQueryVo
	 * @return
	 */
	List<ParentFunInfo> loadMenuByRoleAndStatus(BeanQueryVo beanQueryVo);
	
    /**
     * @param funParentId
     * @return
     */
    int deleteByPrimaryKey(Integer funParentId);

    /**
     * @param record
     * @return
     */
    int insert(ParentFunInfo record);

    /**
     * @param record
     * @return
     */
    int insertSelective(ParentFunInfo record);

    /**
     * @param funParentId
     * @return
     */
    ParentFunInfo selectByPrimaryKey(Integer funParentId);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ParentFunInfo record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(ParentFunInfo record);
}