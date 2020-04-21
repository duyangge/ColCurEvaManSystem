<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>班级管理</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../class/classAdmin.do" method="post">
						<div class="layui-form-item">
							<div class="layui-inline tool-btn">
								<button class="layui-btn layui-btn-small layui-btn-normal cla_go-btn hidden-xs" data-url="../class/goAddClass.do"><i class="layui-icon">&#xe654;</i></button>
						        <button class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs" data-url=""><i class="iconfont">&#xe656;</i></button>
							</div>
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords"  placeholder="请输入关键字" autocomplete="off" class="layui-input">
							</div>
						<!-- 	<div class="layui-inline">
								<select name="status" lay-filter="status" id="status">
									<option value="" selected="selected">请选择学院</option>
									<option value="0">正常</option>
									<option value="1">异常</option>
								</select>
							</div> -->
							<div class="layui-inline">
							<input type="submit" class="layui-btn layui-btn-normal" value="搜索">
							</div>
						</div>
					</form>
					<div class="layui-form" id="table-list">
					<table class="layui-table" lay-even lay-skin="nob">
						<colgroup>
								<col width="50">
								<col class="hidden-xs" width="50">
								<col >
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
									<th >班级名称</th>
									<th >所属学院</th>
									<th >备注信息</th>
									<th >创建者</th>
									<th class="hidden-xs">创建时间</th>
									<th class="hidden-xs">修改时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${claList}" var="cla" varStatus="status">
									<tr>
									<td><input type="checkbox" name="classId" lay-skin="primary" data-id="1" value="${cla.classId }"></td>
									<td class="hidden-xs">${cla.classId }</td>
									<td >${cla.className }</td>
									<td >${cla.professionName }</td>
									<td ><textarea  readonly="readonly" style="background:transparent;border:none; resize:none;">${cla.classInfo }</textarea></td>
									<td >${cla.createdUser }</td>
									<td class="hidden-xs" ><fmt:formatDate value="${cla.createdTime }" pattern="yyyy:MM:dd:HH:mm:ss"/></td>
									<td class="hidden-xs"><fmt:formatDate value="${cla.modifiedTime }" pattern="yyyy:MM:dd:HH:mm:ss" /></td>
									<td>
										<div class="layui-inline">
										<button class="layui-btn layui-btn-small layui-btn-normal cla_edit-btn" data-id="${cla.classId }" data-url="../class/goUpdateClass.do"><i class="layui-icon">&#xe642;</i></button>
										</div>
									</td>
								</tr>
							</c:forEach>
						 </tbody>
						</table>
						
						<!-- 分页 -->
					<!-- 	<div class="page-wrap">
							<ul class="pagination">
								<li class="disabled"><span>«</span></li>
								<li class="active"><span>1</span></li>
								<li>
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">»</a>
								</li>
							</ul>
						</div> -->
					</div>
				</div>
		</div>
			</body>

</html>