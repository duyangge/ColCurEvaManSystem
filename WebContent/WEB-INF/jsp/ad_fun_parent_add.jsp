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
		<title>添加一级菜单</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../fun/addParentFun.do" method="post">
					
				 <div class="layui-form-item">
						<label class="layui-form-label">角色名称：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
							<select name="roleId" lay-verify="required" lay-filter="role" id="role_id">	
								<c:forEach items="${roleList }" var="role" varStatus="status">
									<option value="${role.roleId}" data-id="${role.roleId }" >${role.roleName}</option>
								</c:forEach>							
							</select>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">功能名称：</label>
						<div class="layui-input-block">
							<input type="text" name="funParentName" required lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">功能地址：</label>
						<div class="layui-input-block">
							<input type="text" name="funParentUrl" required lay-verify="required" value="../fun/maintainPage.do" placeholder="请输入功能地址" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">图标代码：</label>
						<div class="layui-input-block">
							<input type="text" name="funParentImg" required lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="funParentStatus" value="0" title="正常" checked>
							<input type="radio" name="funParentStatus" value="1" title="异常">
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
		<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
		var roadParent = "${roadParent}";
		if(roadParent != null && roadParent != ""){	
		   		parent.location.reload();//刷新父页面
		 }
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