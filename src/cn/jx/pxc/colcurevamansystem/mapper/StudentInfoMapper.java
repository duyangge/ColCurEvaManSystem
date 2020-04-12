package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;

/**
 *<p> Title:  StudentInfoMapper.java</p>
 *<p> Description:  学生代理</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月5日上午11:17:40
 * @version 版本号
 */
public interface StudentInfoMapper {
	
	
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
	List<StudentInfo> selectByClassList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过账号查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	StudentInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	
	
    int deleteByPrimaryKey(Integer studentId) throws Exception;

    int insert(StudentInfo record) throws Exception;

    int insertSelective(StudentInfo record) throws Exception;

 
    /**主键查询
     * @param studentId
     * @return
     */
    StudentInfo selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(StudentInfo record) throws Exception;

    int updateByPrimaryKey(StudentInfo record) throws Exception;
}