<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>学生课程评价</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../classSub/findStudentClassSub.do" method="post">
						
						<div class="layui-form-item">
						   <div class="layui-inline">
					       	 <input type="text" name="startTime" style="width:100px;" value="${startTime }" class="layui-input" placeholder="开始日期" id="startTime" onclick="layui.laydate({elem:this})"/>
							</div>
							<div class="layui-inline">
								<input type="text" name="endTime" style="width:100px;" value="${endTime }" class="layui-input" placeholder="截至日期" id="endTime" onclick="layui.laydate({elem:this})"/>
							</div>
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords"  placeholder="请输入关键字" value="${keyWords }" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
							<input type="submit" class="layui-btn layui-btn-normal" value="搜索">
							</div>
								<div class="layui-inline" style="width:90px;">
						          <select name="pageSize"  id="pageSize" lay-filter="status">
						            <c:forEach begin="5" end="20" varStatus="status" step="5">
						                   <option value="${status.current}" <c:if test="${status.current eq pageSize }"> selected="selected"</c:if> >${status.current}条/页</option>
						            </c:forEach>
						          </select>
							</div>
						</div>
					</form>
					 <c:if test="${ not empty lesEvaList  }">
					<div class="layui-form" id="table-list">
					<table class="layui-table" lay-even lay-skin="nob">
							<colgroup>
								<col width="50">
								<col class="hidden-xs" width="50">
								<col class="hidden-xs" width="130">
								<col>
								<col>
								<col>
								<col>
								<col>
								<col class="hidden-xs" width="130">
								<col width="100">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
									<th class="hidden-xs">ID</th>
									<th class="hidden-xs">课程名称</th>
									<th>班级名称</th>
									<th>授课教师</th>
									<th>评分</th>
									<th>评价内容</th>
									<th>评价者</th>
									<th class="hidden-xs">评价时间</th>
									<th>查看</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${lesEvaList}" var="lesEva" varStatus="status">
									<tr>
									<td><input type="checkbox" name="subEvaId" lay-skin="primary" data-id="1" value="${lesEva.id }"></td>
									<td class="hidden-xs">${lesEva.id }</td>
									<td class="hidden-xs">${lesEva.lessionName}</td>
									<td >${lesEva.className }</td>
									<td >${lesEva.teacherName }</td>
									<td>${lesEva.score }</td>
									<td ><textarea readonly="readonly" style="background:transparent;border:none; resize:none;">${lesEva.subInfo }</textarea></td>
									<td>${lesEva.subUserName }</td>
									<td class="hidden-xs" ><fmt:formatDate value="${lesEva.createdTime }" pattern="yyyy-MM-dd" /></td>
									<td >
										<div class="layui-inline">
											<button class="layui-btn layui-btn-mini layui-btn-normal stu_see_eva_edit-btn" data-id="${lesEva.id }" data-url="../classSub/goSeeLessionEva.do"><i class="layui-icon">&#xe642;</i></button>
										</div>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
						        <td colspan="8">
							        <!--上一页，下一页，首页，尾页，跳转  -->
							        <div class="pagelist" align="center"> 
							            <a href="javaScript:firstPage()" class="layui-btn layui-btn-mini layui-btn-normal">首页</a>
								        <a href="javaScript:lastPage()"  class="layui-btn layui-btn-mini layui-btn-normal">上一页</a> 
								         <input type="text" name="currentPage" id="currentPage" class="layui-input" style="width:50px; line-height:10px;display:inline-block;height:30px;"/>
								         <a href="javaScript:goDis()" class="layui-btn layui-btn-mini layui-btn-normal">跳转</a>
									     <font>第${currentPage }页/共${totalPage}页</font>
								        <a href="javaScript:nextPage()" class="layui-btn layui-btn-mini layui-btn-normal">下一页</a>
								        <a href="javaScript:finalPage()" class="layui-btn layui-btn-mini layui-btn-normal">尾页</a>
								    </div>
						         </td>
      							</tr>
						 </tbody>
						</table>
						
					</div>
					  </c:if>
 					<c:if test="${  empty lesEvaList  }">
					 		<h1 align="center">无该条记录，请重新查询.....</h1>
					 </c:if>
				</div>
		</div>
			</body>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var obj = document.getElementById("pageSize");
	var index = obj.selectedIndex;
	var pageSize = obj.options[index].value;
	function firstPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/findStudentClassSub.do?currentPage=1&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime;
	}
	function finalPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/findStudentClassSub.do?currentPage="+${totalPage }+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime;
	}
	function nextPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/findStudentClassSub.do?currentPage="+${currentPage + 1 }+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime;
	}

	function lastPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/findStudentClassSub.do?currentPage="+${currentPage - 1 }+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime;
	}
	function goDis(){
		var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
		var nubmer = document.getElementById("currentPage").value;
		if (!re.test(nubmer)) {
		alert("温馨提示：跳转页请输入数字");
	   	document.getElementById("currentPage").value = "";
		return false;
		}
		var name = document.getElementById("seacher").value;
		var currentPage = document.getElementById("currentPage").value;
		window.location.href="../classSub/findStudentClassSub.do?&keyWords="+name+"&pageSize="+pageSize+"&currentPage="+currentPage+"&startTime="+startTime+"&endTime="+endTime;
	}
</script>		
</html>