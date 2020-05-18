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
  <div class="layui-tab page-content-wrap">
		  <ul class="layui-tab-title">
		    <li class="layui-this">修改资料</li>
		    <li>修改密码</li>
		  </ul>
		  <div class="layui-tab-content">
		    <div class="layui-tab-item layui-show">
		    	<form enctype="multipart/form-data" class="layui-form"  style="width: 90%;padding-top: 20px;" action="../user/updateAdminInfo.do" method="post">
		    	  <input type="hidden" name="id" value="${tea.teacherId }">
				  <div class="layui-form-item">
				    <label class="layui-form-label">ID：</label>
				    <div class="layui-input-block">
				      <input type="text" name="teacherId" disabled autocomplete="off" class="layui-input layui-disabled" value="${tea.teacherId }">
				    </div>
				  </div>
				  
				 	<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						<div class="layui-input-block">
							<input type="text" name="account" required lay-verify="required" value="${tea.account }" readonly="readonly" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" required lay-verify="required" value="${tea.username}" placeholder="请输入姓名" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">头像：</label>
						<div class="layui-input-block">
							<c:if test="${tea.headImage !=null}">
						    <img src="/pic/${tea.headImage}" width="100" height="100"/>
						     <br/>
					     </c:if>
							<input type="file"  name="head_image"/> 
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" id="tel" name="telphone" required lay-verify="required"  value="${tea.telphone }" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="telmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text" id="mail" name="mail" required lay-verify="required" value="${tea.mail }" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="mailmes"></span>

					</div>
			
					 <div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="status" value="0" title="正常"  readonly="readonly" checked="checked"/>
						</div>

					</div> 
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminInfo">立即提交</button>
				    </div>
				  </div>
				</form>
		    </div>
		    
		    <!--密码修改  -->
		    <div class="layui-tab-item">
		    	<form class="layui-form" v style="width: 90%;padding-top: 20px;" action="../user/updateAdminPassWord.do" method="post">
		    	<input type="hidden" name="teacherId" value="${tea.teacherId }">
				  <div class="layui-form-item">
				    <label class="layui-form-label">用户名：</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" disabled autocomplete="off" class="layui-input layui-disabled" value="${tea.username }">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">旧密码：</label>
				    <div class="layui-input-block">
				      <input type="password" name="oldpassword" value="${tea.password}" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">新密码：</label>
				    <div class="layui-input-block">
				      <input type="password" id="passwd" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				     <span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="passwdmes"></span>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">重复密码：</label>
				    <div class="layui-input-block">
				      <input type="password" id="repasswd" name="repassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminPassword">立即提交</button>
				    </div>
				  </div>
				</form>
		    </div>
		  </div>
		</div>
</body>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/admin.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//Demo
layui.use(['form','element'], function(){
  var form = layui.form();
  var element = layui.element();
  form.render();
//监听提交
form.on('submit(formDemo)', function(data) {
	   JSON.stringify(data.field);
       var tel=document.getElementById('tel').value;
       var mail=document.getElementById('mail').value;
       if( isPoneAvailable(tel) &&  isEmailAvailable(mail)){
    	   var index = parent.layer.getFrameIndex(window.name);
	         parent.layer.close(index);
    	   return true;
       }else {
        	return false;
        }
});
	
  //监听密码提交
   form.on('submit(adminPassword)', function(data){
    JSON.stringify(data.field);
    var passwd = document.getElementById('passwd').value;
	var repasswd = document.getElementById('repasswd').value;
    if(checkpasswd(passwd,repasswd)){
    	var index = parent.layer.getFrameIndex(window.name);
         parent.layer.close(index);
         return true;
    }else{
    	return false;
    }
    	
  });
});
</script>
</html>