/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;

/**
 *<p> Title:  ClassSubInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月10日下午9:39:14
 * @version 版本号
 */
@Controller
@RequestMapping("/classSub")
@SuppressWarnings("all")
public class ClassSubInfoController {
	
	@Resource
	public ClassSubInfoService classSubInfoService;
	
	@Resource
	public StudentInfoService studentInfoService;
	
	@Resource
	public LessionInfoService lessionInfoService;
	
	@Resource
	public TeacherInfoService teacherInfoService;
	
	@Resource
	public ClassInfoService classInfoService;
	
	@Resource
	public ProfessionInfoService professionInfoService;
	
	/**教师查看自己教授课程的学生评价列表
	 * @param model
	 * @param session
	 * @param beanQueryVo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findStudentClassSubByTeacher.do")
	public String findStudentClassSubByTeacher(Model model,HttpSession session,BeanQueryVo beanQueryVo) throws Exception {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}

		TeacherInfo teacherInfo  = (TeacherInfo) session.getAttribute("admin");
		TeacherInfo tea = teacherInfoService.selectByPrimaryKey(teacherInfo.getTeacherId());
		List<LessionEvaTemp> lesEvaList = null;
		beanQueryVo.setTeacherId(tea.getTeacherId());
		beanQueryVo.setStatus("0");//教师只能查看可见评价
		try {
			lesEvaList = classSubInfoService.selectByteacher(beanQueryVo);
			if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
	               List<LessionEvaTemp> userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
					
					model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					
					model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		//保持该教师所教所有班级
		List<ClassInfo> claList = new ArrayList<ClassInfo>();
		List<LessionTeacherInfo>  lesTeaList = lessionInfoService.selectByTeacherList(beanQueryVo);
		for (LessionTeacherInfo lesTea : lesTeaList) {
			ClassInfo cla = classInfoService.selectByPrimaryKey(lesTea.getClassId());
			claList.add(cla);
		}
		//去重class对象
		Set<ClassInfo> userSet = new HashSet<>(claList);
	    List<ClassInfo> list = new ArrayList<>(userSet);
		
		model.addAttribute("classId", beanQueryVo.getClassId());
		model.addAttribute("claList", list);
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "te_lession_eva";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<LessionEvaTemp> getPageContentByClassSubAndTeacher(Model model, Integer currentPage, Integer pageSize ,List<LessionEvaTemp> userInfoCustomListOld) throws Exception{
		ListPageUtil<LessionEvaTemp> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**管理员查看详细评价内容
	 * @param model
	 * @param beanQueryVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/goAdminSeeLessionEva.do")
	public String goAdminSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
	try {
			LessionEvaTemp lesTemp =classSubInfoService.selectClassSubById(beanQueryVo);
			model.addAttribute("lesTemp", lesTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_lession_eva_see";
	}
	
	/**教师查看详细评价内容
	 * @param model
	 * @param beanQueryVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/goTeacherSeeLessionEva.do")
	public String goTeacherSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		try {
			LessionEvaTemp lesTemp =classSubInfoService.selectClassSubById(beanQueryVo);
			model.addAttribute("lesTemp", lesTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "te_lession_eva_see";
	}
	
	
	
	
	/**管理员：分页+分类+模糊查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findClassSub.do")
	public String findClassSub(Model model,BeanQueryVo beanQueryVo,@RequestParam(value="evaId",required=false)Integer id) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		List<LessionEvaTemp>  lesEvaList = null;
		if(id != null) {//修改评价是否可见(默认都可见)
			lesEvaList = new ArrayList<LessionEvaTemp>();
			BeanQueryVo bean  = new BeanQueryVo();
			bean.setId(id);
			LessionEvaTemp lesEva = classSubInfoService.selectLessionEvaById(bean);
			lesEvaList.add(lesEva);
		}else {
			  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		}
		
		if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
            List<LessionEvaTemp> userInfoCustomList;
			try {
				userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
				model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//数据回显
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());
		model.addAttribute("startTime", beanQueryVo.getStartTime());
		model.addAttribute("endTime", beanQueryVo.getEndTime());
		//可见
		model.addAttribute("status",beanQueryVo.getStatus() );
		//类别
		model.addAttribute("category", beanQueryVo.getCategory());
		return "ad_lession_eva";
	}
	
	
	/**学生查看分页+分类+模糊查询:自查+添加评价成功后自动查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findStudentClassSub.do")
	public String findStudentClassSub(Model model,BeanQueryVo beanQueryVo,HttpSession session, @RequestParam(value="claSubId",required=false)Integer id) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}

		List<LessionEvaTemp> lesEvaList = null;
		if(id != null) {//添加评价后，自动查询
			ClassSubInfo claSub = classSubInfoService.selectByPrimaryKey(id);//得到该条记录
			beanQueryVo.setClassSubInfo(claSub);
			lesEvaList = new ArrayList<LessionEvaTemp>();
			LessionEvaTemp les = classSubInfoService.selectLessionEvaById(beanQueryVo);
			lesEvaList.add(les);
		}else {//默认查询所有，分页查询+模糊查询
			  StudentInfo stu = (StudentInfo) session.getAttribute("student");
			  beanQueryVo.setStudentId(stu.getStudentId());
			  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		}
		if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
            List<LessionEvaTemp> userInfoCustomList = null;
			try {
				userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "st_lession_eva";
	}
	
	
	/**调整评价课程页面
	 * 两种情况：
	 * 第一种知道该课程id直接评价：（班级id，学生ID，课程id）
	 * 第二中:在所有课程中选择要评价的（已知班级id，学生ID，+选择的课程id+）
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goSubLessionEva.do")
	public String goSubLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		beanQueryVo.setClassId(stu.getClassId());
		beanQueryVo.setStudentId(stu.getStudentId());
			try {
				LessionInfoTemp lesTemp = lessionInfoService.selectByIdLessionTemp(beanQueryVo);
				model.addAttribute("lesTemp", lesTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "st_lession_eva_sub";
	}
	
	/**学生查看自己详细评价内容
	 * 两种情况：
	 * 第一种知道该课程id直接评价：（班级id，学生ID，课程id）
	 * 第二中:在所有课程中选择要评价的（已知班级id，学生ID，+选择的课程id+）
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goSeeLessionEva.do")
	public String goSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		beanQueryVo.setClassId(stu.getClassId());
		beanQueryVo.setStudentId(stu.getStudentId());
			try {
				LessionEvaTemp lesTemp =classSubInfoService.selectLessionEvaById(beanQueryVo);
				model.addAttribute("lesEva", lesTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "st_lession_eva_see";
	}	
	
	
	
	
	/**学生提交评价后+直接查询该条记录
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/subLessionEva.do")
	public String subLessionEva(Model model, ClassSubInfo claSub) {
		try {
			claSub.setSubStatus(0);
			classSubInfoService.insertSelective(claSub);
			Integer id = claSub.getSubEvaId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../lession/findLessionByClass.do";
	}
	
	/**
	 * @param model
	 * @param claSub
	 * @return
	 */
	@RequestMapping("/updateLessionEva.do")
	public String updateLessionEva(Model model,ClassSubInfo claSub) {
		classSubInfoService.updateByPrimaryKeySelective(claSub);
		return "forward:findClassSub.do?evaId="+claSub.getSubEvaId();
	}
	
	/**查看课程评价分
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/lookEvaAnaResult.do")
	public String lookEvaAnaResult(BeanQueryVo beanQueryVo, Model model) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		List<ClassSubInfoCustom>  lesEvaList = classSubInfoService.findAvgScoreByClassIdAndLessionId(beanQueryVo);
		String way = beanQueryVo.getShowWays();
		if(way == null || !way.equals("1")) {//判断显示方式
			if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
	            List<ClassSubInfoCustom> userInfoCustomList = null;
				try {
					userInfoCustomList = this.getPageContent(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
					model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			//return "forward:goEchartsPage.do?beanQueryVo="+beanQueryVo;
			model.addAttribute("lesEvaList",JSON.toJSONString(lesEvaList));
		}
		    //数据回显
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());
			List<ProfessionInfo>  proList = professionInfoService.selectByName(null);
			model.addAttribute("proList", proList);//保存每个学院
			model.addAttribute("professionId", beanQueryVo.getProfessionId());
			model.addAttribute("classId", beanQueryVo.getClassId());
			model.addAttribute("startTime", beanQueryVo.getStartTime());
			model.addAttribute("endTime", beanQueryVo.getEndTime());
			model.addAttribute("showWays", beanQueryVo.getShowWays());
			model.addAttribute("totalPage", 1);
		    return "ad_lession_eva_ana";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<ClassSubInfoCustom> getPageContent(Model model, Integer currentPage, Integer pageSize ,List<ClassSubInfoCustom> userInfoCustomListOld) throws Exception{
		ListPageUtil<ClassSubInfoCustom> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**利用echart插件查看班级课程平均分
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/goEchartsPage.do", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public List<ClassSubInfoCustom>  goEchartsPage(Model model, BeanQueryVo beanQueryVo){
		List<ClassSubInfoCustom>  lesEvaList = classSubInfoService.findAvgScoreByClassIdAndLessionId(beanQueryVo);
		List<ProfessionInfo>  proList = professionInfoService.selectByName(null);
		//数据回显
		model.addAttribute("proList", proList);//保存每个学院
		model.addAttribute("professionId", beanQueryVo.getProfessionId());
		model.addAttribute("classId", beanQueryVo.getClassId());
		model.addAttribute("startTime", beanQueryVo.getStartTime());
		model.addAttribute("endTime", beanQueryVo.getEndTime());
		model.addAttribute("showWays", beanQueryVo.getShowWays());
		return lesEvaList;
	}
	
}
