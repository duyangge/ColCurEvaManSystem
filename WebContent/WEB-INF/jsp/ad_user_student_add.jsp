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
		<title>学生添加</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../user/addStudent.do" method="post" enctype="multipart/form-data">
						<c:forEach items="${allErrors}"  var="error">
		                     <font color="red">${error.getDefaultMessage()}</font>
	                    </c:forEach>
					<div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="professionId" lay-verify="required" id="profession_id" lay-filter="profession">	
								<c:forEach items="${proList }" var="pro" varStatus="status">
									<option value="${pro.professionId }" data-id="${pro.professionId }" >${pro.professionName }</option>
								</c:forEach>							
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">班级：</label>
						<div class="layui-input-block">
							<!--循环便利所有班级  -->
							<select name="classId" lay-verify="required" id="class_id" >	
							      <option value="" data-id="">请选择所属班级</option>						
							</select>
						</div>
                        
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						<div class="layui-input-block">
							<input type="text" name="account" required lay-verify="required" value="${account }"  autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">头像：</label>
						<div class="layui-input-block">
						<c:if test="${headImage !=null}">
						    <img src="/pic/${headImage}" width="100" height="100"/>
						     <br/>
					     </c:if>
							<input type="file"  name="head_image"/> 
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" id="tel" name="telphone" required lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="telmes"></span>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text" id="mail" name="mail" required lay-verify="required" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="mailmes"></span>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
							<input type="password" id="passwd" name="password" required lay-verify="required" value="pxxy${account }" autocomplete="off" class="layui-input">
						</div>
						<span style="font:10px '微软雅黑';color: red;margin-left: 50%" id="passwdmes"></span>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认密码：</label>
						<div class="layui-input-block">
							<input type="password" id="repasswd" name="repassword" required lay-verify="required" value="pxxy${account }" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="status" value="0" title="正常" checked>
							<input type="radio" name="status" value="1" title="异常">
						</div>

					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo" id="btnSub">立即提交</button>
							<input type="reset" class="layui-btn layui-btn-primary" value="重置">
						</div>
					</div>
				</form>
		</div>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/admin.js" type="text/javascript" charset="utf-8"></script>
<script>

//提交表单
layui.use(['form'], function() {
	var form = layui.form();
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
						for(var i=0;i<data.length;i++){
							$("#class_id").append("<option value='"+data[i].classId+"' data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
						}
						form.render();
					},
					error:function(err){
			        	alert("失败");
			      	}
	           });
	});
	
	
	//监听提交
	form.on('submit(formDemo)', function(data) {
				JSON.stringify(data.field);
				var passwd = document.getElementById('passwd').value;
				var repasswd = document.getElementById('repasswd').value;
		        var tel=document.getElementById('tel').value;
		        var mail=document.getElementById('mail').value;
		        var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
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