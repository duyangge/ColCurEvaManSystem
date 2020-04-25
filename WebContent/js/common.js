layui.config({
	base: '../js/module/'
}).extend({
	dialog: 'dialog',
});

layui.use(['form', 'jquery', 'laydate', 'layer', 'laypage', 'dialog',   'element'], function() {
	var form = layui.form(),
		layer = layui.layer,
		$ = layui.jquery,
		dialog = layui.dialog;
	//获取当前iframe的name值
	var iframeObj = $(window.frameElement).attr('name');
	
	//var check
	
	//全选
	form.on('checkbox(allChoose)', function(data) {
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
		child.each(function(index, item) {
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});
	
	//渲染表单
	form.render();	
	
	//列表跳转
	
	//添加学生\、教师、学院、班级、课程，(可公用)
/*	 $('#table-list,.tool-btn').on('click', '.add-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})*/
	
	//添加学生
	$('#table-list,.tool-btn').on('click', '.stu_go-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})

	//添加教师
	$('#table-list,.tool-btn').on('click', '.tea_go-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})
	
	//添加学院
	$('#table-list,.tool-btn').on('click', '.pro_go-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})
	
	//添加班级
	$('#table-list,.tool-btn').on('click', '.cla_go-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})
	
	//添加课程
	$('#table-list,.tool-btn').on('click', '.les_go-btn', function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	})

	//管理员编辑学生
	 $('#table-list,.tool-btn').on('click', '.stu_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?studentId="+id;
		return false;
	})	
	
	//管理员编辑教师
	$('#table-list,.tool-btn').on('click', '.tea_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	//管理员编辑学院
	$('#table-list,.tool-btn').on('click', '.pro_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})
	
	//管理员编辑班级
	$('#table-list,.tool-btn').on('click', '.cla_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	//教师具体查看课程评价
	$('#table-list,.tool-btn').on('click', '.tea_see_eva_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	//学生具体查看课程评价
	$('#table-list,.tool-btn').on('click', '.stu_see_eva_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	//管理员编辑角色信息
	$('#table-list,.tool-btn').on('click', '.role_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	//管理员编辑角色权限
	$('#table-list,.tool-btn').on('click', '.role_fun_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	
	
/*	//编辑信息(学院,教师,班级,学生具体查看课程评价,教师具体查看课程评价)，（可通用）
	$('#table-list,.tool-btn').on('click', 'edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return true;
	})	
	*/
	
	//管理员编辑课程
	$('#table-list,.tool-btn').on('click', '.les_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})	
	
	//管理员编辑菜单
		$('#table-list,.tool-btn').on('click', '.edit-fun-btn', function() {
			var tableName=$(this).attr('data-table');
			var id = $(this).attr('data-id');
			if(tableName == "parentFun"){
				window.location.href="../fun/goEditParentFunPage.do?id="+id;
			}else{
				window.location.href="../fun/goEditSubFunPage.do?id="+id;
			}
			return false;
		})	
	
	
	//学生添加课程评价
	$('#table-list,.tool-btn').on('click', '.eva_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?lessionId="+id;
		return true;
	})	
	
	//管理员具体查看课程评价
	$('#table-list,.tool-btn').on('click', '.see_eva_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?lessionId="+id;
		return true;
	})	
	
	
	
	//教师查看课程
	$('#table-list,.tool-btn').on('click', '.see_les_edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?lessionId="+id;
		return true;
	})	
	
	
	//列表删除（可公用）
		$('#table-list').on('click', '.del-btn', function() {
			var url=$(this).attr('data-url');
			var id = $(this).attr('data-id');
			dialog.confirm({
				message:'您确定要进行删除吗？',
				success:function(){
					layer.msg('删除成功');
					window.location.href=url+"?id="+id;
				},
				cancel:function(){
					layer.msg('取消成功');
				}
			})
			return true;
		})	
		
		//顶部批量删除
		$('.delBtn').click(function() {
			var url=$(this).attr('data-url');
			dialog.confirm({
				message:'您确定要删除选中项',
				success:function(){
					layer.msg('删除成功');
					var id_str = $('input[type=checkbox]:checked').map(function(){return this.value}).get().join(',');
					window.location.href=url+"?idStr="+id_str;
				},
				cancel:function(){
					layer.msg('取消成功');
				}
			})
			return false;

		}).mouseenter(function() {
			dialog.tips('批量删除', '.delBtn');
		})		
	
		//菜单删除
		$('#table-list').on('click', '.del-fun-btn', function() {
			var tableName=$(this).attr('data-table');
			var id = $(this).attr('data-id');
			dialog.confirm({
				message:'您确定要进行删除吗？',
				success:function(){
					layer.msg('删除成功');
					if(tableName == "parentFun"){
						window.location.href="../fun/delParentFun.do?id="+id;
					}else{
						window.location.href="../fun/delSubFun.do?id="+id;
					}
				},
				cancel:function(){
					layer.msg('取消成功');
				}
			})
			return true;
		})
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//顶部添加
/*	$('.addBtn').click(function() {
		var url=$(this).attr('data-url');
		//将iframeObj传递给父级窗口,执行操作完成刷新
		parent.page("添加学生", url, iframeObj, w = "700px", h = "620px");
		//window.location.href=url;
		return true;

	}).mouseenter(function() {

		dialog.tips('添加', '.addBtn');

	})
	*/
	//顶部排序
/*	$('.listOrderBtn').click(function() {
		var url=$(this).attr('data-url');
		dialog.confirm({
			message:'您确定要进行排序吗？',
			success:function(){
				layer.msg('确定了')
			},
			cancel:function(){
				layer.msg('取消了')
			}
		})
		return false;

	}).mouseenter(function() {

		dialog.tips('批量排序', '.listOrderBtn');

	})	*/
	
	//列表添加
/*	$('#table-list').on('click', '.add-btn', function() {
		var url=$(this).attr('data-url');
		//将iframeObj传递给父级窗口
		parent.page("菜单添加", url, iframeObj, w = "700px", h = "620px");
		//window.location.href=url;
		return false;
	})*/
	
	
	//列表删除
/*	$('#table-list').on('click', '.del-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		dialog.confirm({
			message:'您确定要进行删除吗？',
			success:function(){
				layer.msg('删除成功');
				window.location.href=url+"?studentId="+id;
			},
			cancel:function(){
				layer.msg('取消成功');
			}
		})
		return true;
	})*/
	
	
	//列表跳转
/*	$('#table-list,.tool-btn').on('click', '.go-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?studentId="+id;
		return true;
	})*/
	
	
	//编辑栏目
/*	$('#table-list').on('click', '.edit-btn', function() {
		var That = $(this);
		var id = That.attr('data-id');
		var url=That.attr('data-url');
		//将iframeObj传递给父级窗口
		parent.page("学生信息编辑", url + "?studentId=" + id, iframeObj, w = "700px", h = "620px");
		refresh();
		return true;
	})*/

	
	
	
	
	
	
	
	
	
	
	
	
	
});






/**
 * 控制iframe窗口的刷新操作
 */
var iframeObjName ;

//父级弹出页面
function page(title, url, obj, w, h) {
	if(title == null || title == '') {
		title = false;
	};
	if(url == null || url == '') {
		url = "404.html";
	};
	if(w == null || w == '') {
		w = '700px';
	};
	if(h == null || h == '') {
		h = '350px';
	};
	iframeObjName = obj;
	//如果手机端，全屏显示
	if(window.innerWidth <= 768) {
		var index = layer.open({
			type: 2,
			title: title,
			area: [320, h],
			fixed: false, //不固定
			content: url
		});
		layer.full(index);
	} else {
		var index = layer.open({
			type: 2,
			title: title,
			area: [w, h],
			fixed: false, //不固定
			content: url
		});
	}
}

/**
 * 刷新子页,关闭弹窗
 */
function refresh() {
	//根据传递的name值，获取子iframe窗口，执行刷新
	if(window.frames[iframeObjName]) {
		window.frames[iframeObjName].location.reload();
	} else {
		window.location.reload();
	}

	layer.closeAll();
}