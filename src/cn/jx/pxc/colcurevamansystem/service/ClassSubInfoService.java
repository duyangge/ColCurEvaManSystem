/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;

/**
 *<p> Title:  ClassSubInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月10日下午9:39:29
 * @version 版本号
 */
public interface ClassSubInfoService {
	
	/**通过班级id和课程id查询该门课程评价分数
	 * @param beanQueryVo
	 * @return
	 */
	List<ClassSubInfoCustom> findAvgScoreByClassIdAndLessionId(BeanQueryVo beanQueryVo);
	
	
	/**教师查看评价详细信息
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	LessionEvaTemp  selectClassSubById(BeanQueryVo beanQueryVo) throws Exception;
	
	
	
	
	/**通过教师id查询所有课程评价记录
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionEvaTemp> selectByteacher(BeanQueryVo beanQueryVo) throws Exception;
	
	/**查询某条记录并转换为临时类输出
	 * @param beanQueryVo
	 * @return
	 */
	LessionEvaTemp selectLessionEvaById(BeanQueryVo beanQueryVo);	
	
	/**查询所有+查询某个特定学生
	 * @param beanQueryVo
	 * @return
	 */
	List<LessionEvaTemp> selectLessionEva(BeanQueryVo beanQueryVo);
	
	
	
	
	/**通过课程查询所有评价
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassSubInfo>  selectByLessionList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**通过课程id和学生id共同确定评价id
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	ClassSubInfo  selectByLessionAndStudentList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过学生id查询所有评价
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassSubInfo>  selectByStudentList(BeanQueryVo beanQueryVo) throws Exception;
	
	
    int deleteByPrimaryKey(Integer subEvaId);

    int insert(ClassSubInfo record);

    int insertSelective(ClassSubInfo record) throws Exception;

    ClassSubInfo selectByPrimaryKey(Integer subEvaId);

    int updateByPrimaryKeySelective(ClassSubInfo record);

    int updateByPrimaryKey(ClassSubInfo record);
}
