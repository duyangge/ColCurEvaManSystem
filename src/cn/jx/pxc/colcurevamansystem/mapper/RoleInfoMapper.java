package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;

/**
 *<p> Title:  RoleInfoMapper.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月22日下午3:25:54
 * @version 版本号
 */
public interface RoleInfoMapper {
	
	/**模糊查询
	 * @param beanQueryVo
	 * @return
	 */
	List<RoleInfo> selectByName(BeanQueryVo beanQueryVo);
	
    /**
     * @param roleId
     * @return
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * @param record
     * @return
     */
    int insert(RoleInfo record);

    /**
     * @param record
     * @return
     */
    int insertSelective(RoleInfo record);

    /**
     * @param roleId
     * @return
     */
    RoleInfo selectByPrimaryKey(Integer roleId);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RoleInfo record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(RoleInfo record);
}