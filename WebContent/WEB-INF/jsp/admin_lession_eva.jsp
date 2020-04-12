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
		<title>课程评价管理</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../classSub/findClassSub.do" method="post">
						<div class="layui-form-item">
							<div class="layui-inline tool-btn">
								<button class="layui-btn layui-btn-small layui-btn-normal tea_go-btn hidden-xs" data-url=""><i class="layui-icon">&#xe654;</i></button>
								<button class="layui-btn layui-btn-small layui-btn-danger delBtn"  data-url=""><i class="layui-icon">&#xe640;</i></button>
						        <button class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs" data-url=""><i class="iconfont">&#xe656;</i></button>
							</div>
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords"  placeholder="请输入关键字" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<select name="status" lay-filter="status" id="status">
									<option value="" selected="selected">请选择一个状态</option>
									<option value="1">班级</option>
									<option value="2">教师</option>
									<option value="3">课程</option>
								</select>
							</div>
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
								<col class="hidden-xs" width="130">
								<col class="hidden-xs" width="130">
								<col class="hidden-xs" width="130">
								<col class="hidden-xs" width="80">
								<col>
								<col class="hidden-xs" width="100">
								<col class="hidden-xs" width="150">
								<col width="150">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
									<th class="hidden-xs">ID</th>
									<th class="hidden-xs">课程名称</th>
									<th class="hidden-xs">班级名称</th>
									<th class="hidden-xs">授课教师</th>
									<th class="hidden-xs">评分</th>
									<th>评价</th>
									<th class="hidden-xs">评价者</th>
									<th class="hidden-xs">创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${lesEvaList}" var="lesEva" varStatus="status">
									<tr>
									<td><input type="checkbox" name="subEvaId" lay-skin="primary" data-id="1" value="${lesEva.id }"></td>
									<td class="hidden-xs">${lesEva.id }</td>
									<td class="hidden-xs">${lesEva.lessionName}</td>
									<td  class="hidden-xs">${lesEva.className }</td>
									<td  class="hidden-xs">${lesEva.teacherName }</td>
									<td  class="hidden-xs">${lesEva.score }</td>
									<td ><textarea readonly="readonly" style="background:transparent;border:none; resize:none;">${lesEva.subInfo }</textarea></td>
									<td class="hidden-xs">${lesEva.subUserName }</td>
									<td class="hidden-xs" ><fmt:formatDate value="${lesEva.createdTime }" pattern="yyyy:MM:dd:HH:mm:ss" /></td>
									<td>
										<div class="layui-inline">
										<button class="layui-btn layui-btn-small layui-btn-normal tea_edit-btn" data-id="${lesEva.id }" data-url=""><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="${lesEva.id }" data-url=""><i class="layui-icon">&#xe640;</i></button></div>
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