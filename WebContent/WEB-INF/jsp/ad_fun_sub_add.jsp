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
		<title>添加二级菜单</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="../fun/addSubFun.do" method="post">
			
				  <div class="layui-form-item">
						<label class="layui-form-label">角色名称：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="roleId" lay-verify="required" lay-filter="role" id="role_id">	
								<c:forEach items="${roleList }" var="role" varStatus="status">
									<option value="${role.roleId}" data-id="${role.roleId }" >${role.roleName}</option>
								</c:forEach>							
							</select>
						</div>
					</div>
					
				  <div class="layui-form-item">
						<label class="layui-form-label">父功能名：</label>
						<div class="layui-input-block" style="margin-bottom: 20px;">
						   <!--循环便利所有学院  -->
							<select name="funParentId" lay-verify="required" id="parentFun_id">	
									<option value="" data-id="" >----请选择----</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">子功能名：</label>
						<div class="layui-input-block">
							<input type="text" name="funName" required lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">功能地址：</label>
						<div class="layui-input-block">
							<input type="text" name="funUrl" required lay-verify="required" value="../fun/maintainPage.do" placeholder="请输入功能地址" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<input type="radio" name="funStatus" value="0" title="正常" checked>
							<input type="radio" name="funStatus" value="1" title="异常">
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
	</body>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//跳转页面的同时就加载
$(document).ready(function() {
    var options=$("#role_id option:selected");
    var value=options.data("id");   //得到学院id
       $.ajax({
			async:false,
			type:"post",
			url:"../fun/changeRole.do",
			dataType: "json",
			data:{id:value}, //二级产品类别的父ID
			success:function(data){
				$("#parentFun_id").empty();
				$("#parentFun_id").append("<option value= '' data-id=''>----请选择---</option>");
				for(var i=0;i<data.length;i++){
					$("#parentFun_id").append("<option value='"+data[i].funParentId+"' data-id='"+data[i].funParentId+"'>"+data[i].funParentName+"</option>");
				}
				form.render();
			},
			error:function(err){
	        	alert("失败");
	      	}
      });//ajax
});
var roadParent = "${roadParent}";
if(roadParent != null && roadParent != ""){	
   		parent.location.reload();//刷新父页面
 }
//Demo
layui.use(['form'], function() {
	var form = layui.form();
	//监听下拉列表提交
	form.on('select(role)', function(data) {
		     var options=$("#role_id option:selected");
	         var value=options.data("id");   //得到学院id
	            $.ajax({
					async:false,
					type:"post",
					url:"../fun/changeRole.do",
					dataType: "json",
					data:{id:value}, //二级产品类别的父ID
					success:function(data){
						$("#parentFun_id").empty();
						$("#parentFun_id").append("<option value= '' data-id=''>----请选择---</option>");
						for(var i=0;i<data.length;i++){
							$("#parentFun_id").append("<option value='"+data[i].funParentId+"' data-id='"+data[i].funParentId+"'>"+data[i].funParentName+"</option>");
						}
						form.render();
					},
					error:function(err){
			        	alert("失败");
			      	}
	           });//ajax
	});//form
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