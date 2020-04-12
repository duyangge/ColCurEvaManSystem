package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassLessionInfo;

public interface ClassLessionInfoMapper {
	
	
	/**一个班级有多少门课程
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ClassLessionInfo> selectByClassIdList(BeanQueryVo beanQueryVo) throws Exception;
	
    int deleteByPrimaryKey(Integer classLessionId);

    int insert(ClassLessionInfo record);

    int insertSelective(ClassLessionInfo record);

    ClassLessionInfo selectByPrimaryKey(Integer classLessionId);

    int updateByPrimaryKeySelective(ClassLessionInfo record);

    int updateByPrimaryKey(ClassLessionInfo record);
}