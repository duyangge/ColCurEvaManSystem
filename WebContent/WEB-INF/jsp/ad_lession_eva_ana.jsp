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
		<title>课程评价管理</title>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
				<!-- form表单，将搜索按钮变为提交按按钮 -->
					<form class="layui-form" action="../classSub/lookEvaAnaResult.do" method="post">
						<div class="layui-form-item">
							<div class="layui-inline" style="width:110px;">
							    <select name="showWays" lay-filter="status" id="showWays">
									<option value="">显示方式</option>
									<option value="0" 
									<c:if test="${showWays eq '0'}">selected="selected"</c:if>
									>列表显示 </option>
									<option value="1" 
									<c:if test="${showWays eq '1'}">selected="selected"</c:if>
									>图像显示</option>
								</select>
							</div>
							<div class="layui-inline">
						        <input type="text" name="startTime" style="width:100px;" value="${startTime }" class="layui-input" placeholder="开始日期" id="startTime" onclick="layui.laydate({elem:this})"/>
							</div>
							<div class="layui-inline">
								<input type="text" name="endTime" style="width:100px;" value="${endTime }" class="layui-input" placeholder="截至日期" id="endTime" onclick="layui.laydate({elem:this})"/>
							</div>
							
							<div class="layui-inline">
								<input type="text" id="seacher" name="keyWords"  value="${keyWords }"  placeholder="请输入关键字" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
							<input type="submit" class="layui-btn layui-btn-normal" value="搜索" lay-filter="formDemo">
							</div>
							<div class="layui-inline" style="width:185px;">
								<!--循环便利所有学院  -->
							<select name="professionId" lay-verify="required" id="profession_id" lay-filter="profession">	
							    <option value="" data-id="">学院</option>
								<c:forEach items="${proList }" var="pro" varStatus="status">
									<option value="${pro.professionId }" data-id="${pro.professionId }" <c:if test="${pro.professionId eq professionId}">selected="selected"</c:if>>${pro.professionName }</option>
								</c:forEach>							
							</select>
							
							</div>
							<div class="layui-inline" style="width:130px;">
								<!--循环便利所有班级  -->
								<select name="classId" lay-verify="required" id="class_id" >	
								      <option value="" data-id="">班级</option>						
								</select>
							</div>
						
						   
							<div class="layui-inline" style="width:90px;">
						          <select name="pageSize"  id="pageSize" lay-filter="status" >
						            <c:forEach begin="5" end="20" varStatus="status" step="5">
						                   <option value="${status.current}" <c:if test="${status.current eq pageSize }"> selected="selected"</c:if> >${status.current}条/页</option>
						            </c:forEach>
						          </select>
							</div>
							
							<div class="layui-inline" style="width:90px;">
						        <button class="layui-btn layui-btn-mini layui-btn-normal" onclick="ToExcel();">导出excel</button>
							</div>
						</div>
					</form>					
					 <c:if test="${ not empty lesEvaList  }">
					     <c:if test="${showWays ne  '1' }">
					     		<div class="layui-form" id="table-list">
										<table class="layui-table" lay-even lay-skin="nob">
											<colgroup>
												<col width="50">
												<col class="hidden-xs" width="50">
												<col >
												<col >
												<col >
												<col >
												<col >
												<col >
												<col width="150">
											</colgroup>
											<thead>
												<tr>
													<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
													<th class="hidden-xs">ID</th>
													<th >班级名称</th>
													<th>课程名称</th>
													<th >授课教师</th>
													<th >平均分</th>
													<th >评价人数</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${lesEvaList}" var="lesEva" varStatus="status">
														<tr>
															<td><input type="checkbox" name="" lay-skin="primary" data-id="1" value=""></td>
															<td class="hidden-xs">${status.count }</td>
															<td  >${lesEva.className }</td>
															<td >${lesEva.lessionName}</td>
															<td  >${lesEva.username }</td>
															<td >${lesEva.avgScore }</td>
															<td  >${lesEva.evaNum }</td>
															<td>
																<div class="layui-inline">
																	<button class="layui-btn layui-btn-mini layui-btn-normal tea_see_eva_edit-btn" data-id="" data-url=""><i class="layui-icon">&#xe642;</i></button>
																	<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="" data-url=""><i class="layui-icon">&#xe640;</i></button>
																</div>
															</td>
													    </tr>
												</c:forEach>
														<tr>
													        <td colspan="8">
														        <!--上一页，下一页，首页，尾页，跳转  -->
														        <div class="pagelist" align="center"> 
														            <a href="javaScript:firstPage()" class="layui-btn layui-btn-mini layui-btn-normal">首页</a>
															        <a href="javaScript:lastPage()"  class="layui-btn layui-btn-mini layui-btn-normal">上一页</a> 
															         <input type="text" name="currentPage" id="currentPage" class="layui-input" style="width:50px; line-height:10px;display:inline-block;height:30px;"/>
															         <a href="javaScript:goDis()" class="layui-btn layui-btn-mini layui-btn-normal">跳转</a>
																     <font>第${currentPage }页/共${totalPage}页</font>
															        <a href="javaScript:nextPage()" class="layui-btn layui-btn-mini layui-btn-normal">下一页</a>
															        <a href="javaScript:finalPage()" class="layui-btn layui-btn-mini layui-btn-normal">尾页</a>
															    </div>
													         </td>
					      							    </tr>
											  </tbody>
								    </table>
					         </div>
					     </c:if>
					     <c:if test="${showWays eq  '1' }">	
					     		<div id="container"  style="height: 500px;width:800px;;margin: 0;text-align: center;"></div>					     
					     </c:if>					
					 </c:if>
 					<c:if test="${  empty lesEvaList  }">
					 		<h1 align="center">无该条记录，请重新查询.....</h1>
					 </c:if>
				</div>
		</div>
</body>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/echarts.min.js" type="text/javascript"  charset="utf-8"  ></script>
<script type="text/javascript">
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
				$("#class_id").append("<option value= '' data-id=''>班级</option>");
				var id = "${classId}";
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
     //echart图像处理      
   var way = "${showWays}";
   if( way == 1){
   	var names=[];    //部门数组（实际用来盛放X轴坐标值）
       var nums=[];    //人数数组（实际用来盛放Y坐标值）
   	 // 基于准备好的dom，初始化echarts实例
       var myChart = echarts.init(document.getElementById('container'));
       var app = {};
       // 指定图表的配置项和数据
         option = null;
         option = {
       	color: ['#3398DB'],
           title: {
               text: '柱形图'
           },
           tooltip: {
               trigger: 'axis',
               axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                   type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
               }
           },
           grid: {
               left: '10%',
               right: '4%',
               containLabel: true
           },

           legend: {
             data: this.legend,
             top:10,
             left:'center',
             textStyle:{
               color:'#000',
             }
           },
           xAxis: {
           	type: 'category',
               data: []
           },
           yAxis: {
           	type: 'value'
           },
           series: [{
               name: '课程平均分',
               type: 'bar',
               barWidth: '60%',
               data: []
           }]
       };
    //将后台返回json数据串处理成对象
    var data= '${lesEvaList}';
	 data = eval(data);
	if(data != null){
		for(var i=0;i<data.length;i++){
			 names.push(data[i].lessionName);
              }
              for(var i=0;i<data.length;i++){
              	 nums.push(data[i].avgScore);
              }
          	// 使用刚指定的配置项和数据显示图表。	
              myChart.setOption(option);
		myChart.setOption({
            xAxis: {
                data: names
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '课程平均分',
                data: nums
            }]
        });
		
	}//list != null
 } //way == 1
   
 
	    

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
						$("#class_id").append("<option value= '' data-id=''>班级</option>");
						var id = "${classId}";
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
	           });
	});
	
});
    

    
    //分页处理
    
	var obj = document.getElementById("pageSize");
	var index = obj.selectedIndex;
	var pageSize = obj.options[index].value;
	
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var professionId = document.getElementById("profession_id").value;
	var classId = document.getElementById("class_id").value;
	
	function firstPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/lookEvaAnaResult.do?currentPage=1&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime+"&professionId="+professionId+"&classId="+classId;
	}
	function finalPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/lookEvaAnaResult.do?currentPage="+${totalPage}+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime+"&professionId="+professionId+"&classId="+classId;
	}
	function nextPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/lookEvaAnaResult.do?currentPage="+${currentPage + 1 }+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime+"&professionId="+professionId+"&classId="+classId;
	}

	function lastPage(){
		var name = document.getElementById("seacher").value;
		window.location.href="../classSub/lookEvaAnaResult.do?currentPage="+${currentPage - 1 }+"&keyWords="+name+"&pageSize="+pageSize+"&startTime="+startTime+"&endTime="+endTime+"&professionId="+professionId+"&classId="+classId;
	}
	function goDis(){
		var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
		var nubmer = document.getElementById("currentPage").value;
		if (!re.test(nubmer)) {
		alert("温馨提示：跳转页请输入数字");
	   	document.getElementById("currentPage").value = "";
		return false;
		}
		var name = document.getElementById("seacher").value;
		var currentPage = document.getElementById("currentPage").value;
		window.location.href="../classSub/lookEvaAnaResult.do?&keyWords="+name+"&pageSize="+pageSize+"&currentPage="+currentPage+"&startTime="+startTime+"&endTime="+endTime+"&professionId="+professionId+"&classId="+classId;
	}
	//点击导出excel
   function ToExcel(){
       var  exportLink ="../classSub/exportReportStaticsData.do?startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&professionId="+$("#profession_id").val()+"&classId="+$("#class_id").val()+"&keyWords="+$("#seacher").val();//拼接controller访问地址
       window.open(exportLink ,'_self');//进行访问	 'scrollbars=no,resizable=no,width=641,height=480,top=50,left=50' 
       alert("下载成功！");
   }
</script>
</html>