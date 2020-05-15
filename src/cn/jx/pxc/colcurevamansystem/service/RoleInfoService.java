/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;

/**
 *<p> Title:  RoleInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月22日下午3:24:08
 * @version 版本号
 */
public interface RoleInfoService {
	
	
   /**添加
	 * @param roleFunInfo
	 * @return
	 */
   int addRoleFun(RoleFunInfo roleFunInfo);
	
	/**模糊查询
	 * @param beanQueryVo
	 * @return
	 */
	List<RoleInfo> selectByName(BeanQueryVo beanQueryVo);
	
	
	/**
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	int deleteByPrimaryKey(Integer roleId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insert(RoleInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertSelective(RoleInfo record) throws Exception;

    /**
     * @param roleId
     * @return
     * @throws Exception
     */
    RoleInfo selectByPrimaryKey(Integer roleId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelective(RoleInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(RoleInfo record) throws Exception;
}
