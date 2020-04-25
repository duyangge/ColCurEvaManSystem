package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;

/**
 *<p> Title:  LessionTeacherInfoMapper.java</p>
 *<p> Description:  教师所教课程代理</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月5日下午3:21:43
 * @version 版本号
 */
public interface LessionTeacherInfoMapper {
	
	
	
	

	
	/**通过查询课程得到班级和教师
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionTeacherInfo> selectByLessionList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**通过查询教师id得到班级和课程
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionTeacherInfo> selectByTeacherList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**班级id+课程id确定教师id
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	LessionTeacherInfo selectByLessionAndClass(BeanQueryVo beanQueryVo) throws Exception;
	
    int deleteByPrimaryKey(Integer lessionTeacherId);

    int insert(LessionTeacherInfo record);

    int insertSelective(LessionTeacherInfo record);

    LessionTeacherInfo selectByPrimaryKey(Integer lessionTeacherId);

    int updateByPrimaryKeySelective(LessionTeacherInfo record);

    int updateByPrimaryKey(LessionTeacherInfo record);
}