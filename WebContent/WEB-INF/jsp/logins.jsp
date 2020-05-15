<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>高校课程评价管理系统</title>
    <meta name="description" content="登录页面">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- External CSS -->
   <link rel="stylesheet" href="../vendor/bootstrap/bootstrap.min.css">
    <!-- CSS -->
    <link rel="stylesheet" href="../css/style.min.css">
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="../css/login.css" />   
</head>
<body data-spy="scroll" data-target="#navbar" class="static-layout">
<div class="jumbotron d-flex align-items-center">
 <div class="container text-center">
      <div class="m-login-bg">
			<div class="m-login">
				<h3 style="font-weight:bold;color: white;font-size:35px; ">欢&nbsp;&nbsp;迎&nbsp;&nbsp;登&nbsp;&nbsp;录</h3>
				<div class="m-login-warp">
					<form class="layui-form"  method="post"  id="login_form" action="../user/login.do" onsubmit="return dosubmit()">
						<div class="layui-form-item">
							<input type="text" name="account" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
							<span style="font:10px '微软雅黑';color: red;" id="mes">${message}</span>
						</div>
						<div class="layui-form-item">
							<input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
						</div>
						<!--验证码  -->
						<div class="layui-form-item">
							<div class="layui-inline">
								 <input type="text" value="" name="verity" required lay-verify="required" placeholder="图形验证码" autocomplete="off" id="input-val" class="layui-input">
							</div>
							<div class="layui-inline">
								 <canvas id="canvas"  class="verifyImg"></canvas>
							</div>
						</div>
						
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<input type="submit"  class="layui-btn layui-btn-normal" lay-submit lay-filter="login" value="登录" id="btn">
							</div>
							<div class="layui-inline">
								<input type="reset" class="layui-btn layui-btn-primary" value="取消">
							</div>
						</div>
					</form>
				</div>
				<p class="copyright" font-weight="bold" style="color:white;" >@Copyright 2019-2020 by compangy.CTWFC</p>
			</div>
		</div>
 </div> 
  <div class="rectangle-1"></div>
  <div class="rectangle-2"></div>
  <div class="rectangle-transparent-1"></div>
  <div class="rectangle-transparent-2"></div>
  <div class="circle-1"></div>
  <div class="circle-2"></div>
  <div class="circle-3"></div>
  <div class="triangle triangle-1">
  	<img src="../img/obj_triangle.png" alt="">
  </div>
  <div class="triangle triangle-2">
  	<img src="../img/obj_triangle.png" alt="">
  </div>
  <div class="triangle triangle-3">
  	<img src="../img/obj_triangle.png" alt="">
  </div>
  <div class="triangle triangle-4">
  	<img src="../img/obj_triangle.png" alt="">
  </div>
</div>	<!-- Features Section-->
</body>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script>
			  $(function(){
			        var show_num = [];
			        draw(show_num);

			        $("#canvas").on('click',function(){//点击图片事件
			            draw(show_num);
			        })
			        $("#btn").on('click',function(){//登录点击事件
			            var val = $("#input-val").val().toLowerCase();
			            var num = show_num.join("");
			            if(val==''){
			                document.getElementById('mes').innerText="请输入验证码！";
			            }else if(val == num){
							document.login_form.submit();
			                $("#input-val").val('');
			                document.getElementById('mes').innerText="";
			                draw(show_num);	
			            }else{
			                document.getElementById('mes').innerText="验证码错误！请重新输入！";
			                $("#input-val").val('');
			                draw(show_num);
			            }
			           
			        })
			    })
			    
			  //数字和字母随机匹配
			    function draw(show_num) {
			        var canvas_width=$('#canvas').width();
			        var canvas_height=$('#canvas').height();
			        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
			        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
			        canvas.width = canvas_width;
			        canvas.height = canvas_height;
			        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
			        var aCode = sCode.split(",");
			        var aLength = aCode.length;//获取到数组的长度
			        
			        for (var i = 0; i <= 3; i++) {
			            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
			            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30
			            var txt = aCode[j];//得到随机的一个内容
			            show_num[i] = txt.toLowerCase();
			            var x = 10 + i * 20;//文字在canvas上的x坐标
			            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
			            context.font = "bold 23px 微软雅黑";

			            context.translate(x, y);
			            context.rotate(deg);

			            context.fillStyle = randomColor();
			            context.fillText(txt, 0, 0);

			            context.rotate(-deg);
			            context.translate(-x, -y);
			        }
			        for (var i = 0; i <= 5; i++) { //验证码上显示线条
			            context.strokeStyle = randomColor();
			            context.beginPath();
			            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
			            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
			            context.stroke();
			        }
			        for (var i = 0; i <= 30; i++) { //验证码上显示小点
			            context.strokeStyle = randomColor();
			            context.beginPath();
			            var x = Math.random() * canvas_width;
			            var y = Math.random() * canvas_height;
			            context.moveTo(x, y);
			            context.lineTo(x + 1, y + 1);
			            context.stroke();
			        }
			    }

			    //颜色
			    function randomColor() {//得到随机的颜色值
			        var r = Math.floor(Math.random() * 256);
			        var g = Math.floor(Math.random() * 256);
			        var b = Math.floor(Math.random() * 256);
			        return "rgb(" + r + "," + g + "," + b + ")";
			    }
			
			    
             var isCommitted = false;//表单是否已经提交标识，默认为false
             function dosubmit(){
                 if(isCommitted==false){
                     isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
                     return true;//返回true让表单正常提交
                 }else{
                    return false;//返回false那么表单将不提交
                }
             }
             /* 鼠标点击特效 - 7Core.CN */
             var a_idx = 0;jQuery(document).ready(function($) {$("body").click(function(e) {var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正" ,"法治", "爱国", "敬业", "诚信", "友善");
             var $i = $("<span/>").text(a[a_idx]);
             a_idx = (a_idx + 1) % a.length;var x = e.pageX,y = e.pageY;$i.css({"z-index": 100000000,"top": y - 20,"left": x,"position": "absolute","font-weight": "bold","color": "#ff6651"});
             $("body").append($i);$i.animate({"top": y - 180,"opacity": 0},1500,function() {$i.remove();});});});
	        
</script>
</html>
