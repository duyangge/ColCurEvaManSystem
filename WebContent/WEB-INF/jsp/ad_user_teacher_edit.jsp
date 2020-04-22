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
		<title>教师信息编辑</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../user/updateTeacher.do" method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="professionId" lay-verify="required" id="profession_id">	
								<c:forEach items="${proList }" var="pro" varStatus="status">
									<option value="${pro.professionId }" data-id="${pro.professionId }" 
									<%-- <c:if test="${pro.professionId eq tea.professionId}">selected="selected"</c:if> --%> 
									>${pro.professionName }</option>
								</c:forEach>							
							</select>
						</div>
			
					<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						 <input type="hidden" name="teacherId" value="${tea.teacherId }" class="layui-input">
						<div class="layui-input-block">
							<input type="text" name="account" required lay-verify="required" value="${tea.account }" placeholder="请输入帐号" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" required lay-verify="required" value="${tea.username}" placeholder="请输入姓名" autocomplete="off" class="layui-input">
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" id="tel"  name="telphone" required lay-verify="required"  value="${tea.telphone }" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="telmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text"  id="mail"  name="mail" required lay-verify="required" value="${tea.mail }" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="mailmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
							<input type="password" id="passwd" name="passwrod" required lay-verify="required" value="${tea.password }"   autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="passwdmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认密码：</label>
						<div class="layui-input-block">
							<input type="password" id="repasswd"  name="repassword" required lay-verify="required"  value="${tea.password }" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="status" value="0" title="正常" <c:if test="${tea.status eq '0' }">checked="checked"</c:if>/>
							<input type="radio" name="status" value="1" title="异常" <c:if test="${tea.status eq '1' }">checked="checked"</c:if>/>
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

		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/admin.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/admin.js" type="text/javascript" charset="utf-8"></script>
		<script>
			//Demo
			layui.use(['form'], function() {
				var form = layui.form();
				form.render();
				//监听提交
				form.on('submit(formDemo)', function(data) {
					   JSON.stringify(data.field);
						var passwd = document.getElementById('passwd').value;
						var repasswd = document.getElementById('repasswd').value;
				       var tel=document.getElementById('tel').value;
				       var mail=document.getElementById('mail').value;
				       if( isPoneAvailable(tel) &&  isEmailAvailable(mail)&&checkpasswd(passwd,repasswd)){
				    	   return true;
				       }else {
				        	return false;
				        }
				});
			});
		</script>
	</body>

</html>