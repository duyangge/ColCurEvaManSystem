/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
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
	
	
	/**查询所有
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

    int insertSelective(ClassSubInfo record);

    ClassSubInfo selectByPrimaryKey(Integer subEvaId);

    int updateByPrimaryKeySelective(ClassSubInfo record);

    int updateByPrimaryKey(ClassSubInfo record);
}
