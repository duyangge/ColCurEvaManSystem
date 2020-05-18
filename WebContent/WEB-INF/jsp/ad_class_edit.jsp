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
	<title>班级编辑</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../class/updateClass.do" method="post">
					
					<div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="professionId" lay-verify="required" onchange="get_class()" id="profession">	
								<c:forEach items="${proList }" var="pro" varStatus="status">
									<option value="${pro.professionId }" data-id="${pro.professionId }" 
									<c:if test="${pro.professionId eq cla.professionId}">selected="selected"</c:if> 
									
									>${pro.professionName }</option>
								</c:forEach>							
							</select>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">班级名称：</label>
						<div class="layui-input-block">
						   <input type="hidden" name="classId" value="${cla.classId }" class="layui-input">
							<input type="text" name="className" value="${cla.className }" required lay-verify="required" placeholder="请输入班级名称" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注:</label>
						<div class="layui-input-block">
							<textarea name="classInfo" placeholder="请输入50字以内的内容" class="layui-textarea">${cla.classInfo }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							 <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button> 
							<button type="reset" class="layui-btn layui-btn-primary">重置</button> 
						</div>
					</div>
				</form>
		</div>

	</body>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
//提交表单
layui.use(['form'], function() {
	var form = layui.form();
	form.render();
	//监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		 var index = parent.layer.getFrameIndex(window.name);
         parent.layer.close(index);
		return true;
	});
});
		
</script>
</html>