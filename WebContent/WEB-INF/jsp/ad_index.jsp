<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>后台管理员首页</title>
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="../css/admin.css"/>
</head>
<body>
<div class="main-layout" id='main-layout'>


			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo"></div>
				
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				<c:forEach items="${funList }" var="fun" varStatus="status">
				      <li class="layui-nav-item">
					    <a href="javascript:;" data-id='${fun.funParentId }'   data-text="${fun.funParentName}" data-url="${fun.funParentUrl }"><i class="iconfont" >&#xe${fun.funParentImg };</i>${fun.funParentName }</a>
					    <c:if test="${  not empty fun.subFunInfoList[0].funId   }"><!-- fn:length(fun.subFunInfoList) -->
					   		 <dl class="layui-nav-child">
					    		<c:forEach items="${fun.subFunInfoList }" var="subFun" varStatus="status">
						    		 <dd>
						    		    <a href="javascript:;" data-url="${subFun.funUrl }" data-id='${subFun.funId }${status.count}' data-text="${subFun.funName }">
						      			<span class="l-line"></span>${subFun.funName }</a>
						      	     </dd>
					    		</c:forEach>
					    	 </dl>
					    </c:if> 
					  </li>
				</c:forEach>
				</ul>
			</div>

			


			<!--右侧内容-->
			<div class="main-layout-container">
			
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a href="javascript:;" data-url="" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="../user/goUpdateAdminInfo.do" data-id='5' data-text="个人信息">${admin.username }</a>
					  </li>
					  <li class="layui-nav-item"><a href="javascript:if(confirm('您是否要退出系统?')){location='../user/loginOut.do'};">退出</a></li>
					</ul>
				</div>
				
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					
					<!--主体标题  -->
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">后台主页</li>
					  </ul>
					  
					  <!--主体页面  -->
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="../user/indexAdmin.do" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
					    	<!--1end-->
					    </div>
					  </div>
					  
					  
					</div>
				</div>
			</div>
			<!--遮罩-->
			<div class="main-mask"></div>
		</div>
		<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/main.js" type="text/javascript" charset="utf-8"></script>  
		
</body>
</html>