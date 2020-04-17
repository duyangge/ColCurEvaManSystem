/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfoTemp;

/**
 *<p> Title:  LessionInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月9日下午3:47:32
 * @version 版本号
 */
public interface LessionInfoService {
	
	
	/**详细查看课程信息
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	LessionInfoTemp selectByLession(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过教师id查询所有教授课程信息
	 * @param beanQueyVo
	 * @return
	 * @throws Exception
	 */
	List<LessionInfoTemp> selectByTeacher(BeanQueryVo beanQueyVo) throws Exception;
	

	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过班级id查询所有课程
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	List<LessionInfo> selectByClassList(BeanQueryVo  beanQueryVo) throws Exception;
	
	
	/**通过班级id查询所有课程,存放到临时类中
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionInfoTemp> selectLessionInfoTempByClassList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**跳转学生评价页面所需信息
	 * @param beanQueryVo:班级id，学生ID，课程id--》教师id
	 * @return
	 * @throws Exception
	 */
	LessionInfoTemp selectByIdLessionTemp(BeanQueryVo beanQueryVo) throws Exception;
	
	/**批量删除
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	int deleteByIdList(BeanQueryVo beanQueryVo) throws Exception;
	
    int deleteByPrimaryKey(Integer lessionId) throws Exception;

    int insert(LessionInfo record) throws Exception;

    int insertSelective(LessionInfo record) throws Exception;

    LessionInfo selectByPrimaryKey(Integer lessionId) ;

    int updateByPrimaryKeySelective(LessionInfo record) throws Exception;

    int updateByPrimaryKey(LessionInfo record) throws Exception;
}
