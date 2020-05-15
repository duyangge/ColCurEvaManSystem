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
		<title>查看课程信息</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
	</head>
	<body>
		<div class="wrap-container">
			<form class="layui-form" style="width: 90%;padding-top: 20px;" action="" method="post">
				
			        <input type="hidden" name="lessionId" value="${les.lessionId }">
					<div class="layui-form-item">
						<label class="layui-form-label">课程名称：</label>
						<div class="layui-input-block">
							<input type="text" name="lessionName" required lay-verify="required"   value="${les.lessionName }"  readonly="readonly" autocomplete="off" class="layui-input">
						</div>
					</div>

				   	<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注:</label>
						<div class="layui-input-block">
							<textarea name="lessionInfo"   readonly="readonly" class="layui-textarea">${les.lessionInfo }</textarea>
						</div>
					</div>
					
					
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" onclick="javaScirpt:window.history.go(-2);">返回上一页</button>
						</div>
					</div>
				</form>
		</div>
	</body>
  <script type="text/javascript">
      function back() {
      //window.frames['iframe'].history.back();
        //alert("13");
  		//window.location.href="../lession/findLessionByTeacher.do";
  		//window.history.back();
  		//window.location.href=document.referrer;
  		window.history.back();
  	    //alert("1");
      }
  </script>
  <script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
</html>