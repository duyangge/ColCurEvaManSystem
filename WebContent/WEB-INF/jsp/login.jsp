<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>后台登录</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="../css/login.css" />
<title>高校课程评价管理系统</title>
</head>
<script type="text/javascript">
var message = "${message}";
if(message != null && message != ""){	//密码修改后，退出登录
	alert(message);
 }

</script>

<body>
		<div class="m-login-bg">
			<div class="m-login">
				<h3>高校课程评价管理系统</h3>
				<div class="m-login-warp">
					<form class="layui-form"  method="post"  id="login_form" action="${pageContext.request.contextPath}/user/login.do">
						<div class="layui-form-item">
							<input type="text" name="account" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<input type="text" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
						</div>
					<!-- 	<div class="layui-form-item">
							<div class="layui-inline">
								<input type="text" name="verity" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="../images/login/yzm.jpg" />
							</div>
						</div> -->
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<!-- <button class="layui-btn layui-btn-normal" lay-submit lay-filter="login" onclick="login()">登录</button> -->
								<input type="submit"  class="layui-btn layui-btn-normal" lay-submit lay-filter="login" value="登录">
							</div>
							<div class="layui-inline">
								<!-- <button type="reset" class="layui-btn layui-btn-primary">取消</button> -->
								<input type="reset" class="layui-btn layui-btn-primary" value="取消">
							</div>
						</div>
					</form>
				</div>
				<p class="copyright">Copyright 2019-2020 by CTWFC</p>
			</div>
		</div>
		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
			  function login(){
				  document.login_form.action="${pageContext.request.contextPath}/user/login.do";
				  document.login_form.submit();
			  }
		</script>
</body>
</html>