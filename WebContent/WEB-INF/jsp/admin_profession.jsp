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
		<title>学院管理</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script>
	//Demo
	layui.use(['form'], function() {
		var form = layui.form();
		form.render();
		//监听提交
		form.on('submit(formDemo)', function(data) {
			layer.msg(JSON.stringify(data.field));
			return true;
		});
	});
</script>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../profession/professionAdmin.do" method="post">
						<div class="layui-form-item">
							<div class="layui-inline tool-btn">
								<button class="layui-btn layui-btn-small layui-btn-normal pro_go-btn hidden-xs" data-url="../profession/goAddProfession.do"><i class="layui-icon">&#xe654;</i></button>
						        <button class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs" data-url=""><i class="iconfont">&#xe656;</i></button>
							</div>
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords"  placeholder="请输入关键字" autocomplete="off" class="layui-input">
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
								<col class="hidden-xs" width="150">
								<col class="hidden-xs" width="200">
								<col>
								<col>
								<col class="hidden-xs" width="150">
								<col class="hidden-xs" width="150">
								<col width="150">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
									<th class="hidden-xs">ID</th>
									<th class="hidden-xs">学院名称</th>
									<th>备注</th>
									<th>创建者</th>
									<th>修改者</th>
									<th class="hidden-xs">创建时间</th>
									<th class="hidden-xs">修改时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="pro" items="${proList }">
								<tr>
									<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
									<td class="hidden-xs">${pro.professionId }</td>
									<td class="hidden-xs">${pro.professionName }</td>
									<td><textarea>${pro.professionInfo }</textarea></td>
									<td>${pro.createdUser }</td>
									<th>${pro.modifiedUser }</th>
									<td class="hidden-xs"><fmt:formatDate value="${pro.createdTime }" pattern="yyyy:MM:dd:HH:mm:ss" /></td>
									<td class="hidden-xs"><fmt:formatDate value="${pro.modifiedTime }" pattern="yyyy:MM:dd:HH:mm:ss" /></td>
									<td>
										<div class="layui-inline">
											<button class="layui-btn layui-btn-small layui-btn-normal pro_edit-btn" data-id="${pro.professionId }" data-url="../profession/goUpdateProfession.do"><i class="layui-icon">&#xe642;</i></button>
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