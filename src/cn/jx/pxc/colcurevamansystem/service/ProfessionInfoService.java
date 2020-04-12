/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service;

import java.util.List;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;

/**
 *<p> Title:  ProfessionInfoService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service
 * @author    23801
 * @date      2020年4月9日下午2:13:05
 * @version 版本号
 */
public interface ProfessionInfoService {
	
	
	
	
	/**模糊查询
	 * @param beanQueryVo
	 * @return
	 */
	List<ProfessionInfo> selectByName(BeanQueryVo beanQueryVo);
	
	
    /**
     * @param professionId
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(Integer professionId) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insert(ProfessionInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int insertSelective(ProfessionInfo record) throws Exception;

    /**
     * @param professionId
     * @return
     */
    ProfessionInfo selectByPrimaryKey(Integer professionId);

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelective(ProfessionInfo record) throws Exception;

    /**
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(ProfessionInfo record) throws Exception;
	

}
