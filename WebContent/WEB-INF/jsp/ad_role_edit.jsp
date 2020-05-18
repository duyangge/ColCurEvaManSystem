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
	<title>角色信息编辑</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>

	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../role/editRole.do" method="post">
				    <input type="hidden" name="roleId" value="${role.roleId }">
					<div class="layui-form-item">
						<label class="layui-form-label">角色名称：</label>
						<div class="layui-input-block">
							<input type="text" name="roleName" required lay-verify="required" value="${role.roleName }"  placeholder="请输入信息" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="roleStatus" value="1" title="异常" <c:if test="${role.roleStatus eq '1' }">checked="checked"</c:if>>
							<input type="radio" name="roleStatus" value="0" title="正常"<c:if test="${role.roleStatus eq '0' }">checked="checked"</c:if> >
						</div>
					</div>

					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-block">
							<textarea name="roleInfo" placeholder="请输入50字以内的内容" class="layui-textarea">${role.roleInfo }</textarea>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
							<input type="reset" class="layui-btn layui-btn-primary" value="重置">
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