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
		<title>提交学生评价课程</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../classSub/subLessionEva.do" method="post">
				
					<div class="layui-form-item">
						<label class="layui-form-label">班级名称：</label>
						<div class="layui-input-block">
							<input type="hidden" name="subStudentId" value="${lesTemp.studentId }"/>
							<input type="hidden" name="subClassId" value="${lesTemp.classId }"/>
							<input type="text" name="className"readonly="readonly"  required lay-verify="required" value="${lesTemp.className }"  autocomplete="off" class="layui-input">
						</div>
					</div>
				
					<div class="layui-form-item">
						<label class="layui-form-label">课程名称：</label>
						<div class="layui-input-block">
						   <input type="hidden" name="subLessionId" value="${lesTemp.lessionId }"/>
							<input type="text" name="lessionName" readonly="readonly" required lay-verify="required" value="${lesTemp.lessionName }" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">授课教师：</label>
						<div class="layui-input-block">
						    <input type="hidden" name="subTeacherId" value="${lesTemp.teacherId }"/>
							<input type="text" name="teacherName" readonly="readonly" required lay-verify="required" value="${lesTemp.teacherName }" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">评分：</label>
						<div class="layui-input-block">
						<c:forEach begin="1" var="item" end="10">
								<input type="radio" name="subScore" value="${item }" title="${item }分" >
						</c:forEach>
							
						</div>

					</div>

				   	<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">评价内容:</label>
						<div class="layui-input-block">
							<textarea name="subInfo" placeholder="请输入50字以内的内容" class="layui-textarea"></textarea>
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

		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
			//Demo
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
	</body>

</html>