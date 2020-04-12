package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;

/**
 *<p> Title:  TeacherInfoMapper.java</p>
 *<p> Description:  教师代理</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月5日上午11:33:30
 * @version 版本号
 */
public interface TeacherInfoMapper {
	
	
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
	
	/**通过账号查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	TeacherInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception;
	
	
    int deleteByPrimaryKey(Integer teacherId);

    int insert(TeacherInfo record);

    int insertSelective(TeacherInfo record);

    TeacherInfo selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(TeacherInfo record);

    int updateByPrimaryKey(TeacherInfo record);
}