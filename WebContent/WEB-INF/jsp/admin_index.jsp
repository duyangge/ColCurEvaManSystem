<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<div class="m-logo">
				</div>
				
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				
                  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont" data-id='5'>&#xe606;</i>用户管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../user/teacherAdmin.do" data-id='1' data-text="教师管理">
				      	<span class="l-line"></span>教师管理</a></dd>
				      <dd><a href="javascript:;" data-url="../user/studentAdmin.do" data-id='2' data-text="学生管理">
				      	<span class="l-line"></span>学生管理</a></dd>
				    </dl>
				  </li>

                  <li class="layui-nav-item">
				    <a href="javascript:;"data-url="../lession/lessionAdmin.do" data-id='3' data-text="课程管理">
				    	<i class="iconfont">&#xe608;</i>课程管理</a>
				  </li> 
                  
                   <li class="layui-nav-item">
				    <a href="javascript:;"data-url="../classSub/findClassSub.do" data-id='4' data-text="课程评价管理">
				    	<i class="iconfont">&#xe60c;</i>评价管理</a>
				  </li> 
                   

				   <li class="layui-nav-item">
				    <a href="javascript:;"data-url="../class/classAdmin.do" data-id='5' data-text="班级管理">
				    	<i class="iconfont">&#xe600;</i>班级管理</a>
				  </li> 
				  
                  
                  <li class="layui-nav-item">
				    <a href="javascript:;"data-url="../profession/professionAdmin.do" data-id='6' data-text="学院管理">
				    	<i class="iconfont">&#xe60d;</i>学院管理</a>
				  </li> 

                 <!--   <li class="layui-nav-item">
				    <a href="javascript:;"data-url="admin_role.html" data-id='10' data-text="角色管理">
				    	<i class="iconfont">&#xe60d;</i>角色管理</a>
				  </li>  -->

				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="../user/goUpdateAdminInfo.do" data-id='8' data-text="个人信息">
				    	<i class="iconfont">&#xe606;</i>个人信息</a>
				  </li>


				  <li class="layui-nav-item">
				  	<a href="javascript:;" data-url="system.html" data-id='9' data-text="系统设置">
				  		<i class="iconfont">&#xe60b;</i>系统设置</a>
				  </li>


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
					    <a href="javascript:;" data-url="../user/goUpdateAdminInfo.do" data-id='5' data-text="个人信息">${tea.username }</a>
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