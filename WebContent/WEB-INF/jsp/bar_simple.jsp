<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="height: 100%; margin: 0">
  <div id="container" style="height: 100%"></div>
</body>
<script type="text/javascript" src="../js/echarts.js"></script>
 <script type="text/javascript"  src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '柱状图'
        },
        tooltip: {},
        legend: {
            data:['部门人数']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '部门人数',
            type: 'bar',
            data: []
        }]
    };

    $(function(){
        var names=[];    //部门数组（实际用来盛放X轴坐标值）
        var nums=[];    //人数数组（实际用来盛放Y坐标值）
         $.ajax({
             type: "POST",
             url:'../classSub/goEchartsPage.do',
             dataType: 'json',
             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
             success: function (data) {
                 if(data){
                     var obj = eval(data);
                     for(var i=0;i<obj.length;i++){
                         names.push(data[i].bg_mc);
                     }
                     for(var i=0;i<obj.length;i++){
                         nums.push(data[i].bg_rs);
                     }

                 }
                 myChart.setOption({
                     xAxis: {
                         data: names
                     },
                     series: [{
                         // 根据名字对应到相应的系列
                         name: '部门人数',
                         data: nums
                     }]
                 });
             }
         })
    });

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
