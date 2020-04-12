package cn.jx.pxc.colcurevamansystem.mapper;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;

public interface LessionInfoMapper {
	
	
	/**批量删除
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	int deleteByIdList(BeanQueryVo beanQueryVo) throws Exception;
	
	/**模糊查询：全局查询
	 * @param beanQueryVo
	 * @return
	 * @throws Exception
	 */
	List<LessionInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception;

	
    int deleteByPrimaryKey(Integer lessionId) throws Exception;

    int insert(LessionInfo record) throws Exception;

    int insertSelective(LessionInfo record) throws Exception;

    LessionInfo selectByPrimaryKey(Integer lessionId) ;

    int updateByPrimaryKeySelective(LessionInfo record) throws Exception;

    int updateByPrimaryKey(LessionInfo record) throws Exception;
}