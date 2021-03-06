package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;

/**
 *<p> Title:  ClassSubInfoMapper.java</p>
 *<p> Description:  课程评价</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月5日下午3:52:11
 * @version 版本号
 */
public interface ClassSubInfoMapper {
	
	/**通过班级id和课程id查询该门课程评价分数
	 * @param beanQueryVo
	 * @return
	 */
	List<ClassSubInfoCustom> findAvgScoreByClassIdAndLessionId(BeanQueryVo beanQueryVo);
	
	/**通过模糊查询多表
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassSubInfo> selectByName(BeanQueryVo beanQueryVo) throws Exception;
	
	
	/**通过教师id查询所有课程评价记录
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassSubInfo> selectByteacher(BeanQueryVo beanQueryVo) throws Exception;
	
	/**通过课程,教师,班级,学生查询所有评价
	 * @return
	 * @throws Exception
	 */
	List<ClassSubInfo>  selectByAllList(BeanQueryVo beanQueryVo) throws Exception;
	
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

    /**
     * @param subEvaId
     * @return
     */
    ClassSubInfo selectByPrimaryKey(Integer subEvaId);

    int updateByPrimaryKeySelective(ClassSubInfo record);

    int updateByPrimaryKey(ClassSubInfo record);
}