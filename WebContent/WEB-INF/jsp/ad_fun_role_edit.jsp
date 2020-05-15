<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>编辑角色权限</title>
<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
      <!--隐藏  -->
      <input type="hidden" name="roleId" value="${roleId }" id="roleId"/>
      <!--角色权限  -->
      <div  id="div_all">
    	<div id="div_all_choose">
    		<input type="checkbox" id="checkall_all" onclick="setAllNo()" />全选
    		<input type="checkbox" id="checkall_opposite" />反选  
    	</div>
    	
    	<!--遍历所有权限功能  -->
     <c:if test="${not empty parentFunInfoList }">
     
     		<!--一级菜单  --> 
	    	<c:forEach items="${parentFunInfoList}" var="parentFunInfo" varStatus="status">
	    	   <c:if test="${parentFunInfo.funParentStatus eq '0'}">
	    	   
	    	   		<div id="div_all_oneFun${status.count }" class="div_all_oneFun">
	    	   		
		    	     <input type="checkbox" name="funIds" checked="checked" value="${parentFunInfo.funParentId}" />${parentFunInfo.funParentName }
		    	     
		    	     <!--遍历一级下所有二级菜单  --> 
		    	     <c:forEach items="${parentFunInfo.subFunInfoList}" var="subFunInfo" varStatus="status">
		    	     
		       	  		<div id="div_all_twoFun${status.count }" class="div_all_twoFun">
			       	  		<c:if test="${not empty subFunInfo.funName}">
			       	  			<!--input中可以嵌套多个c标签  -->
			       	  			<input type="checkbox" name="funId"   value="${subFunInfo.funId}" <c:if test="${subFunInfo.funStatus eq '0' }">checked="checked"</c:if> />${subFunInfo.funName}  
			       	  		</c:if>
		       	  		</div>
		       	  		
		       	     </c:forEach>
		       	  </div>
	    	   </c:if>
	    	   
	    	   <c:if test="${parentFunInfo.funParentStatus eq '1'}">
	    	   
	    	   		<div id="div_all_oneFun${status.count }" class="div_all_oneFun">
	    	   		
		    	     <input type="checkbox" name="funIds" value="${parentFunInfo.funParentId}"/>${parentFunInfo.funParentName }
		    	     
		    	     <!--遍历一级下所有二级菜单  --> 
		    	     <c:forEach items="${parentFunInfo.subFunInfoList}" var="subFunInfo" varStatus="status">
		    	     
		       	  		<div id="div_all_twoFun${status.count }" class="div_all_twoFun">
			       	  		<c:if test="${not empty subFunInfo.funName}">
			       	  			<!--input中可以嵌套多个c标签  -->
			       	  			<input type="checkbox" name="funId"   value="${subFunInfo.funId}" />${subFunInfo.funName}  
			       	  		</c:if>
		       	  		</div>
		       	  		
		       	     </c:forEach>
		       	  </div>
	    	   </c:if>
	    	   
	   		 </c:forEach>
     </c:if>
     
    </div> 
     <div class="form-group" style="margin-left:0.5%;margin-top:1%;">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
           <button class="layui-btn layui-btn-normal" onclick="updateRoleFun()" >保存</button>
          &nbsp; &nbsp; &nbsp; &nbsp;
           <button class="layui-btn layui-btn-normal" onclick="self.location=document.referrer;">返回</button>
        </div>
      </div>
	
  </div>
</form>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function updateRoleFun(){//一级，二级菜单都需要考虑
	var parentIds = "";//权限id字符串
	var subIds = "";//权限id字符串
	var funSubIds = document.getElementsByName("funId");//二级菜单
	var funParentIds = document.getElementsByName("funIds");//一级菜单
	var roleId = document.getElementById("roleId").value; //角色id
	//alert("roleId:"+roleId);
	//遍历所有二级菜单的选中的id
	   for (var i = 0; i < funSubIds.length; i++) {
		  if(funSubIds[i].checked ==  true){
			  var temp = funSubIds[i].value;
			  subIds = subIds+","+temp;
		  }
	   }
	//遍历所有选中的一级菜单的id
	   for (var i = 0; i < funParentIds.length; i++) {
		  if(funParentIds[i].checked ==  true){
			  var temp = funParentIds[i].value;
			  parentIds = parentIds+","+temp;
		  }
	   }
  window.location.href = "../role/editRoleFun.do?subIds="+subIds+"&parentIds="+parentIds+"&roleId="+roleId; 
  window.event.returnValue=false;   
}

$(function () {
	if($('#div_all_oneFun1 div input:checked').length != 0){
		$('#div_all_oneFun1>input').prop('checked','checked');
	}
	if($('#div_all_oneFun2 div input:checked').length != 0){
		$('#div_all_oneFun2>input').prop('checked','checked');
	}
	if($('#div_all_oneFun4 div input:checked').length != 0){
		$('#div_all_oneFun4>input').prop('checked','checked');
	}
	if($('#div_all_oneFun5 div input:checked').length != 0){
		$('#div_all_oneFun5>input').prop('checked','checked');
	}
 
	$('#checkall_all').click(function (){
		$('form input').prop('checked','checked');
		$('#checkall_opposite').prop('checked','');
	});
	$('#checkall_opposite').click(function (){
		$('form input').prop('checked','');
	});
	$('#div_all_oneFun1>input').click(function (){
		$('#div_all_oneFun1 div input').prop('checked',$(this).prop('checked'));
	});
	$('#div_all_oneFun2>input').click(function (){
		$('#div_all_oneFun2 div input').prop('checked',$(this).prop('checked'));
	});
	$('#div_all_oneFun4>input').click(function (){
		$('#div_all_oneFun4 div input').prop('checked',$(this).prop('checked'));
	});
	$('#div_all_oneFun5>input').click(function (){
		$('#div_all_oneFun5 div input').prop('checked',$(this).prop('checked'));
	});
	
	$('#div_all_oneFun1 div input').click(function (){
		if($('#div_all_oneFun1 div input:checked').length != 0){
			$('#div_all_oneFun1>input').prop('checked',true);
		}else if($('#div_all_oneFun1 div input:checked').length == 0){
			$('#div_all_oneFun1>input').prop('checked',false);
		}
	});
	
	$('#div_all_oneFun2 div input').click(function (){
		if($('#div_all_oneFun2 div input:checked').length != 0){
			$('#div_all_oneFun2>input').prop('checked',true);
		}else if($('#div_all_oneFun2 div input:checked').length == 0){
			$('#div_all_oneFun2>input').prop('checked',false);
		}
	});
	$('#div_all_oneFun4 div input').click(function (){
		if($('#div_all_oneFun4 div input:checked').length != 0){
			$('#div_all_oneFun4>input').prop('checked',true);
		}else if($('#div_all_oneFun4 div input:checked').length == 0){
			$('#div_all_oneFun4>input').prop('checked',false);
		}
	});
	$('#div_all_oneFun5 div input').click(function (){
		if($('#div_all_oneFun5 div input:checked').length != 0){
			$('#div_all_oneFun5>input').prop('checked',true);
		 }else if($('#div_all_oneFun5 div input:checked').length == 0){
			$('#div_all_oneFun5>input').prop('checked',false);
		}
	});
	
})
</script>
<style type="text/css">
#div_all{
 font-size:16px;
}
.div_all_oneFun{
		margin-left:80px;

}
.div_all_twoFun{
		margin-left:90px;

}
#div_all_choose{
		margin-left:15px;
}
</style>
</body>
</html>