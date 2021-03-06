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
<title>学生编辑</title>
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
			<form enctype="multipart/form-data"  class="layui-form" style="width: 90%;padding-top: 20px;" action="../user/updateStudent.do" method="post">
					<input type="hidden" value="${stu.studentId}" name="studentId">
					<div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="professionId" lay-verify="required"  id="profession_id" lay-filter="profession">	
								<c:forEach items="${proList }" var="pro" varStatus="status">
									<option value="${pro.professionId }" data-id="${pro.professionId }" 
									<c:if test="${pro.professionId eq stu.professionId}">selected="selected"</c:if> 
									>${pro.professionName }</option>
								</c:forEach>							
							</select>
						</div>

						<label class="layui-form-label">班级：</label>
						<div class="layui-input-block">
							<!--循环便利所有班级  -->
							<select name="classId" lay-verify="required" id="class_id">	
							     <option value="" data-id="">-请选择所属班级-</option>	
							</select>
						</div>
                        
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						<div class="layui-input-block">
							<input type="text" name="account" value="${stu.account }" required lay-verify="required" readonly="readonly"  autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" value="${stu.username }"  required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
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
							<input type="text" id="tel" name="telphone" value="${stu.telphone }" required lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="telmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text" id="mail" name="mail" value="${stu.mail }" required lay-verify="required" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="mailmes"></span>

					</div>					
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="status" value="0" title="正常" <c:if test="${stu.status eq '0' }">checked="checked"</c:if>/>
							<input type="radio" name="status" value="1" title="异常" <c:if test="${stu.status eq '1' }">checked="checked"</c:if>/>
						</div>
					</div>
					<c:if test="${stu.classId eq classId }">selected="selected" </c:if>
					<div class="layui-form-item">
						<div class="layui-input-block">
							 <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button> 
							 <button type="reset" class="layui-btn layui-btn-primary">重置</button> 
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
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/admin.js" type="text/javascript" charset="utf-8"></script>
<script>


//选中一级产品类别后，获取并刷新二级产品类别列表	    
//提交表单
layui.use(['form','element'], function(){
	var form = layui.form();
	var element = layui.element();
	form.render();
	
	//跳转页面的同时就加载
	$(document).ready(function() {
		 var options=$("#profession_id option:selected");
	     var value=options.data("id");   //得到学院id
		 $.ajax({
				async:false,
				type:"post",
				url:"../user/changeClass.do",
				dataType: "json",
				data:{id:value}, //二级产品类别的父ID
				success:function(data){
					$("#class_id").empty();
					$("#class_id").append("<option value= '' data-id=''>请选择所属班级</option>");
					var id = "${stu.classId}";
					for(var i=0;i<data.length;i++){
						if(id == data[i].classId ){
							$("#class_id").append("<option value='"+data[i].classId+"'  selected='selected' data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
						}else{
							$("#class_id").append("<option value='"+data[i].classId+"'  data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
						}
					}
					form.render();
				},
				error:function(err){
		        	alert("失败");
		      	}
	    });//ajax
		
		
	});
	
	
	
	//监听下拉列表提交
	form.on('select(profession)', function(data) {
		     var options=$("#profession_id option:selected");
	         var value=options.data("id");   //得到学院id
	        	 $.ajax({
						async:false,
						type:"post",
						url:"../user/changeClass.do",
						dataType: "json",
						data:{id:value}, //二级产品类别的父ID
						success:function(data){
							$("#class_id").empty();
							$("#class_id").append("<option value= '' data-id=''>请选择所属班级</option>");
							var id = "${stu.classId}";
							for(var i=0;i<data.length;i++){
								if(id == data[i].classId ){
									$("#class_id").append("<option value='"+data[i].classId+"'  selected='selected' data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
								}else{
									$("#class_id").append("<option value='"+data[i].classId+"'  data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
								}
							}
							form.render();
						},
						error:function(err){
				        	alert("失败");
				      	}
		           });//ajax
        
	});//select
	
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
	    } else{
	    	return false;
	    } 
	    	
	  });
	
	
});
		
</script>	
</html>