/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfoCustom;

/**
 *<p> Title:  ClassInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月9日下午3:35:54
 * @version 版本号
 */
public interface ClassInfoService {
	
	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassInfoCustom> selectClaCusByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过学院id查询所有班级
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassInfo> selectByProfessionList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	
    int deleteByPrimaryKey(Integer classId) throws Exception;

    int insert(ClassInfo record) throws Exception;

    int insertSelective(ClassInfo record) throws Exception;

    ClassInfo selectByPrimaryKey(Integer classId);
    
    ClassInfoCustom selectById(Integer classId);
    

    int updateByPrimaryKeySelective(ClassInfo record) throws Exception;

    int updateByPrimaryKey(ClassInfo record);
}
