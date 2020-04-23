<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>角色管理</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../role/getRoleAdBySelect.do" method="post">
						<div class="layui-form-item">
							<div class="layui-inline tool-btn">
								<button class="layui-btn layui-btn-small layui-btn-normal cla_go-btn hidden-xs" data-url=""><i class="layui-icon">&#xe654;</i></button>
							</div>
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords" value="${keyWords }"  placeholder="请输入关键字" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<select name="status" lay-filter="status" id="status">
									<option value="" selected="selected">请选择状态</option>
									<option value="0">正常</option>
									<option value="1">异常</option>
								</select>
							</div>
							<div class="layui-inline">
							<input type="submit" class="layui-btn layui-btn-normal" value="搜索">
							</div>
							<div class="layui-inline">
						          <select name="pageSize"  id="pageSize" lay-filter="status">
						            <c:forEach begin="5" end="20" varStatus="status" step="5">
						                   <option value="${status.current}" <%-- <c:if test="${status.current eq pageSize }"> selected="selected"</c:if> --%> >${status.current}条/页</option>
						            </c:forEach>
						          </select>
							</div>
						</div>
					</form>
					<c:if test="${ not empty roleList  }"> 
					<div class="layui-form" id="table-list">
					<table class="layui-table" lay-even lay-skin="nob">
						<colgroup>
								<col width="50">
								<col class="hidden-xs" width="50">
								<col >
								<col >
								<col >	
								<col class="hidden-xs" width="150">
								<col class="hidden-xs" width="150">
								<col width="150">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
									<th class="hidden-xs">ID</th>
									<th >角色名称</th>
									<th >角色说明</th>
									<th >状态</th>
									<th class="hidden-xs">创建时间</th>
									<th class="hidden-xs">修改时间</th>
									<th>权限</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${roleList}" var="role" varStatus="status">
									<tr>
									<td><input type="checkbox" name="roleId" lay-skin="primary" data-id="1" value="${role.roleId }"></td>
									<td class="hidden-xs">${role.roleId}</td>
									<td >${ role.roleName}</td>
									<td ><textarea  readonly="readonly" style="background:transparent;border:none; resize:none;">${role.roleInfo}</textarea></td>
									<td >
									<c:if test="${role.roleStatus eq '0' }">
											<button class="layui-btn layui-btn-mini layui-btn-normal">
												正常
											</button>
										</c:if>
										<c:if test="${role.roleStatus eq '1' }">
											<button class="layui-btn layui-btn-mini layui-btn-normal" style="background:red;">
												异常
											</button>
										</c:if>
									</td>
									<td class="hidden-xs" ><fmt:formatDate value="${role.createdTime }" pattern="yyyy:MM:dd:HH:mm:ss"/></td>
									<td class="hidden-xs"><fmt:formatDate value="${role.modifiedTime }" pattern="yyyy:MM:dd:HH:mm:ss" /></td>
									<td>
										<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="${role.roleId }" data-url=""><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="${role.roleId }" data-url=""><i class="layui-icon">权限</i></button>
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
 					<c:if test="${  empty roleList  }">
					 		<h1 align="center">无该条记录，请重新查询.....</h1>
					 </c:if>
				</div>
		</div>
			</body>
<script type="text/javascript">
	var obj = document.getElementById("pageSize");
	var index = obj.selectedIndex;
	var pageSize = obj.options[index].value;
	function firstPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../role/getRoleAdBySelect.do?currentPage=1&keyWords="+name+"&pageSize="+pageSize;
	}
	function finalPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../role/getRoleAdBySelect.do?currentPage="+${totalPage }+"&keyWords="+name+"&pageSize="+pageSize;
	}
	function nextPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../role/getRoleAdBySelect.do?currentPage="+${currentPage + 1 }+"&keyWords="+name+"&pageSize="+pageSize;
	}

	function lastPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../role/getRoleAdBySelect.do?currentPage="+${currentPage - 1 }+"&keyWords="+name+"&pageSize="+pageSize;
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
		window.location.href="../role/getRoleAdBySelect.do?&keyWords="+name+"&pageSize="+pageSize+"&currentPage="+currentPage;
	}
</script>		
</html>