<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
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
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../user/addStudent.do" method="post">

					<div class="layui-form-item">
						<label class="layui-form-label">学院：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="studentInfo.professionId" lay-verify="required" id="profession_id">	
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
							<select name="studentInfo.classId" lay-verify="required" id="class_id" >	
							<option value= "0" data-id="0">请选择所属班级</option>						
							<!--<option value="1" selected="selected">1601软工班</option>
								<option value="2">1602软工班</option>
								<option value="3">1603软件班</option>
								<option value="4">1604软件班</option>-->
							</select>
						</div>
                        
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">学号：</label>
						<div class="layui-input-block">
							<input type="text" name="studentInfo.account" required lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名：</label>
						<div class="layui-input-block">
							<input type="text" name="studentInfo.username" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" name="studentInfo.telphone" required lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">email：</label>
						<div class="layui-input-block">
							<input type="text" name="studentInfo.mail" required lay-verify="required" placeholder="请输入email" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
							<input type="text" name="studentInfo.password" required lay-verify="required" value="00000000" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认密码：</label>
						<div class="layui-input-block">
							<input type="text" name="repassword" required lay-verify="required" value="00000000" autocomplete="off" class="layui-input">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="studentInfo.status" value="0" title="正常" checked>
							<input type="radio" name="studentInfo.status" value="1" title="异常">
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
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script>


//$(document).ready(function(){
//选中一级产品类别后，获取并刷新二级产品类别列表
//$("#profession_id").click(function(){
	 var options=$("#profession_id option:selected");
     var value=options.data("id");   
            $.ajax({
				async:false,
				type:"post",
				url:"../user/changeClass.do",
				dataType: "json",
				data:{id:value}, //二级产品类别的父ID
				success:function(data){
					$("#class_id").empty();
					$("#class_id").append("<option value= '' data-id='0'>请选择所属班级</option>");
					for(var i=0;i<data.length;i++){
						$("#class_id").append("<option value='"+data[i].classId+"' data-id='"+data[i].classId+"'>"+data[i].className+"</option>");
					}
				},
				error:function(err){
		        	alert("失败");
		      	}
   });
	
//});

//});     
    
		
//提交表单
layui.use(['form'], function() {
	var form = layui.form();
	form.render();
	//监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		alert("json:"+JSON.stringify(data.field));
		return true;
	});
});

//首次刷新页面获取一级产品类别列表
/* function cheng(){
	        	$.ajax({
	        		   async:false,
	        		   type:"post",
	        		   url:"../user/changeProfession.do",
	        		   //data:{id:1},  //一级产品类别的父ID
	        		   success:function(data){
	        			   alert("prfo1");
	        			   $("#profession").empty();
	      					$("#profession").append("<option value= '' data-id='0'>请选择所属学院</option>");
	      					for(var i=0;i<data.length;i++){
	      						alert("prfo2");
	      						$("#profession").append("<option value='"+data[i].professionId+"' data-id='"+data[i].professionId+"'>"+data[i].professionName+"</option>");
	      					} 
	        		   }
	        	   });
} */
			
</script>
	</body>
</html>