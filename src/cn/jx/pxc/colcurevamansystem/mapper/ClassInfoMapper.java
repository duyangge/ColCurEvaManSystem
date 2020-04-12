package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;

/**
 *<p> Title:  ClassInfoMapper.java</p>
 *<p> Description:  班级代理</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月5日下午1:00:28
 * @version 版本号
 */
public interface ClassInfoMapper {
	
	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**通过学院id查询所有班级
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassInfo> selectByProfessionList(BeanQueryVo beanQueryVo) throws Exception;
	
	
	
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
}