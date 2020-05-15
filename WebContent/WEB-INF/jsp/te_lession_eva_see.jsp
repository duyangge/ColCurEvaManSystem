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
		<title>教师详细查看学生评价课程</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="" method="post">
				
					<div class="layui-form-item">
						<label class="layui-form-label">班级名称：</label>
						<div class="layui-input-block">
							<input type="text" name="className"readonly="readonly"  required lay-verify="required" value="${lesTemp.className }"  autocomplete="off" class="layui-input">
						</div>
					</div>
				
					<div class="layui-form-item">
						<label class="layui-form-label">课程名称：</label>
						<div class="layui-input-block">
							<input type="text" name="lessionName" readonly="readonly" required lay-verify="required" value="${lesTemp.lessionName }" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">授课教师：</label>
						<div class="layui-input-block">
							<input type="text" name="teacherName" readonly="readonly" required lay-verify="required" value="${lesTemp.teacherName }" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">评价学生：</label>
						<div class="layui-input-block">
							<input type="text" name="studentName" readonly="readonly"  required lay-verify="required" value="${lesTemp.subUserName }" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">评分：</label>
						<div class="layui-input-block">
						<c:forEach begin="1" var="item" end="10">
						         <c:if test="${item eq lesTemp.score }">
						         	<input type="radio" name="subScore"  value="${item }" title="${item }分"  checked="checked">
						         </c:if>
								  <c:if test="${item ne lesTemp.score }">
						         	<input type="radio" name="subScore"  value="${item }" title="${item }分"  disabled="disabled">
						         </c:if>
						</c:forEach>
							
						</div>

					</div>

				   	<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">评价内容:</label>
						<div class="layui-input-block">
							<textarea name="subInfo" placeholder="请输入50字以内的内容" readonly="readonly" class="layui-textarea">${lesTemp.subInfo }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" onclick="javaScript:history.go(-1);" >返回上一页</button>
						</div>
					</div>
				</form>
		</div>

		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
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
	</body>

</html>