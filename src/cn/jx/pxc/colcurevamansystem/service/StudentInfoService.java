/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfoCustom;

/**
 *<p> Title:  StudentInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月8日上午9:58:30
 * @version 版本号
 */
public interface StudentInfoService {
	
	
	/**查询某班级的总人数
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	int selectNumByClass(Integer classId) throws Exception;
	
	

	/**批量删除学生
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	int deleteStudentList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<StudentInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**通过班级id查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<StudentInfoCustom> selectByClassList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	  /**主键查询
     * @param studentId
     * @return
     */
    StudentInfo selectByPrimaryKey(Integer studentId);
    
    
    
	  /**主键查询
     * @param studentId
     * @return
     */
    StudentInfoCustom selectCustomByKey(Integer studentId);
    
    /**选择性修改
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelective(StudentInfo record) throws Exception;
    
    
    /**选择性插入
     * @param record
     * @return
     * @throws Exception
     */
    int insertSelective(StudentInfo record) throws Exception;
    
    
	/**通过账号查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	StudentInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**显示基本信息：学生信息扩展类，查看其他详细信息
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<StudentInfoCustom> findStudentByName(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**删除学生
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteStudent(Integer id) throws Exception;
	
}
