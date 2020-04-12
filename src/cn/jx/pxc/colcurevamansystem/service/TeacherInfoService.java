/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;

/**
 *<p> Title:  TeacherInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月8日上午9:58:50
 * @version 版本号
 */
public interface TeacherInfoService {
	
	/**批量删除学生
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	int deleteList(BeanQueryVo beanQueryVo) throws Exception;

	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<TeacherInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**添加教师
	 * @param record
	 * @return
	 */
	int insertSelective(TeacherInfo record)  throws Exception;
	
	
	/**通过教师id查询
	 * @param teacherId
	 * @return
	 */
	TeacherInfo selectByPrimaryKey(Integer teacherId);

    /**更新教师信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TeacherInfo record) throws Exception;
	
	
	
	/**通过账号查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	TeacherInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	   /**删除教师
	 * @param teacherId
	 * @return
	 */
	int deleteByPrimaryKey(Integer teacherId) throws Exception;
	
	
	
}
