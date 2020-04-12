package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;

/**
 *<p> Title:  ProfessionInfoMapper.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.mapper
 * @author    23801
 * @date      2020年4月9日下午2:13:15
 * @version 版本号
 */
public interface ProfessionInfoMapper {
	
	
	
	/**模糊查询
	 * @param beanQueryVo
	 * @return
	 */
	List<ProfessionInfo> selectByName(BeanQueryVo beanQueryVo);
	
	
    int deleteByPrimaryKey(Integer professionId);

    int insert(ProfessionInfo record);

    int insertSelective(ProfessionInfo record);

    ProfessionInfo selectByPrimaryKey(Integer professionId);

    int updateByPrimaryKeySelective(ProfessionInfo record);

    int updateByPrimaryKey(ProfessionInfo record);
}