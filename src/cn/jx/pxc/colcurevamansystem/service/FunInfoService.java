/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.FunInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;

/**
 *<p> Title:  FunInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月22日下午3:24:22
 * @version 版本号
 */
public interface FunInfoService {
	
	/**
	 * @param beanQueryVo
	 * @return
	 */
	List<ParentFunInfo> loadMenuByRoleAndStatus(BeanQueryVo beanQueryVo);
	/**
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(RoleFunInfo roleFunInfo);
	
	/**
	 * @param id
	 * @return
	 */
	List<SubFunInfo> selectSubFunByParentFunId(Integer id);
	
	/**
	 * @param id
	 * @return
	 */
	RoleFunInfo selectByFunId(Integer id);
	
	/**通过角色id查询所有的一级菜单
	 * @param roleId
	 * @return
	 */
	List<ParentFunInfo> selelctByRoleId(Integer roleId);
	
	/**
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ParentFunInfo> selectByName(BeanQueryVo beanQueryVo) throws Exception;
	
	/**
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<FunInfoTemp> selectByNameTemp(BeanQueryVo beanQueryVo) throws Exception;
	
    /**
     * @param funParentId
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKeyByParent(Integer funParentId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertByParent(ParentFunInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertSelectiveByParent(ParentFunInfo record) throws Exception;

    /**
     * @param funParentId
     * @return
     * @throws Exception
     */
    ParentFunInfo selectByPrimaryKeyByParent(Integer funParentId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelectiveByParent(ParentFunInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeyByParent(ParentFunInfo record) throws Exception;
    
    /**
     * @param funId
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKeyBySub(Integer funId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertBySub(SubFunInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertSelectiveBySub(SubFunInfo record) throws Exception;

    /**
     * @param funId
     * @return
     * @throws Exception
     */
    SubFunInfo selectByPrimaryKeyBySub(Integer funId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelectiveBySub(SubFunInfo record) throws Exception;

    int updateByPrimaryKeyBySub(SubFunInfo record) throws Exception;	

}
