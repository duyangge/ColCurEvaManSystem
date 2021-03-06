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
<title>学生个人中心</title>
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
		    	<form class="layui-form"  style="width: 90%;padding-top: 20px;" action="../user/updateStudentInfo.do" method="post" enctype="multipart/form-data">
		    	 <input type="hidden" name="studentId" value="${stu.studentId  }">
				  <div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block">
							<input type="text" name="profession" readonly="readonly" required lay-verify="required" value="${stu.professionName }" autocomplete="off" class="layui-input">
						</div>
					</div>
				  
				  <div class="layui-form-item">
						<label class="layui-form-label">班级：</label>
						<div class="layui-input-block">
							<input type="text" name="class" readonly="readonly"  required lay-verify="required" value="${stu.className }" autocomplete="off" class="layui-input">
						</div>
					</div>
				  
				 	<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						<div class="layui-input-block">
							<input type="text" name="account" readonly="readonly"  required lay-verify="required" value="${stu.account }" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" required lay-verify="required" value="${stu.username}" placeholder="请输入姓名" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">头像：</label>
						<div class="layui-input-block">
							<c:if test="${stu.headImage !=null}">
						    <img src="/pic/${stu.headImage}" width="100" height="100"/>
						     <br/>
					     </c:if>
							<input type="file"  name="head_image"/> 
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" id="tel" name="telphone" required lay-verify="required"  value="${stu.telphone }" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="telmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text" id="mail" name="mail" required lay-verify="required" value="${stu.mail }" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="mailmes"></span>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="status" value="0" title="正常"checked="checked" readonly="readonly"/>
						</div>
					</div>
			
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminInfo">立即提交</button>
				    </div>
				  </div>
				  
				</form>
		    </div>
		    
		    <div class="layui-tab-item">
		    	<form class="layui-form" v  style="width: 90%;padding-top: 20px;" action="../user/updateStudentPassWord.do" method="post">
		    	 <input type="hidden" name="studentId" value="${stu.studentId  }">
				  <div class="layui-form-item">
				    <label class="layui-form-label">用户名：</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" disabled autocomplete="off" class="layui-input layui-disabled" value="${stu.username }">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">旧密码：</label>
				    <div class="layui-input-block">
				      <input type="password" name="oldpassword" value="${stu.password}" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
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
var message = "${message}";
if(message != null && message != ""){	//密码修改后，退出登录
	top.location.href="../user/loginOut.do";
 }
var roadParent = "${roadParent}";
if(roadParent != null && roadParent != ""){	
   		parent.location.reload();//刷新父页面
 }
//Demo
layui.use(['form','element'], function(){
  var form = layui.form();
  var element = layui.element();
  form.render();
  //监听信息提交
  form.on('submit(adminInfo)', function(data){
    JSON.stringify(data.field);
    var tel=document.getElementById('tel').value;
    var mail=document.getElementById('mail').value;
	
    if( isPoneAvailable(tel) &&  isEmailAvailable(mail)){
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
    	return true;
    } else{
    	return false;
    } 
    	
  });
});

</script>
</html>