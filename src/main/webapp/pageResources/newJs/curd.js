$('.table-sort').dataTable({
	"aaSorting" : [ [ 1, "desc" ] ],// 默认第几个排序
	"bStateSave" : true,// 状态保存
	"pading" : false,
	"aoColumnDefs" : [
	// {"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	{
		"orderable" : false,
		"aTargets" : [ 0, 8 ]
	} // 不参与排序的列
	]
});


/** 单条-删除 */
function deleteOne(obj, targetUrl) {
	layer.confirm('确认要删除吗？', function(index) {
		$.ajax({
			type : 'get',
			url : targetUrl,
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					$(obj).parents("tr").remove();
					layer.msg(data.msg, {
						icon : 1,
						time : 1000
					});
				} else {
					layer.msg(data.msg, {
						icon : 5,
						time : 1000
					});
				}
			},
			error : function(data) {
				console.log(data.msg);
			}
		});
	});
}
/** 多条-删除* */
function deleteChoice(targetUrl) {
	var checkbox = document.getElementsByName("choice");
	var isSelect;
	var dataStr = "";
	var checkObj = new Array();
	for (var i = 0; i < checkbox.length; i++) {
		if (checkbox[i].checked) {
			isSelect = true;
			dataStr += "," + checkbox[i].value;
			checkObj.push(checkbox[i]);
		}
	}
	if (!isSelect) {
		layer.alert("请选择要删除的项");
		return false;
	}
	layer.confirm('确认要删除吗？', function(index) {
		$.ajax({
			type : 'get',
			url : targetUrl,
			dataType : 'json',
            data : {
				"choiceId" : dataStr
			},
			success : function(data) {
				if (data.result) {
					for (var i = 0; i < checkObj.length; i++) {
						$(checkObj[i]).parents("tr").remove(); // 移除删除的元素显示
					}
					layer.msg(data.msg, {
						icon : 1,
						time : 1000
					});
				} else {
					layer.msg(data.msg, {
						icon : 5,
						time : 1000
					});
				}

			},
			error : function(data) {
                console.log(data.msg);
			}
		});

	});
}

/** 编辑-弹窗 */
function alertShow(title, url, w, h) {
	layer_show(title, url, w, h);
}

/** 修改 */
function edit(title, url, id, w, h) {
	var index = layer.open({
		type : 2,
		title : title,
		content : url
	});
	layer.full(index);
}


/**停用*/
function defineStatus(obj,id,status,targetUrl){
	var str;
	if(status == 1){
		str = "确定启用吗？";
	}else{
		str = "确定停用吗？";
	}
	layer.confirm(str,function(index){
		var target = targetUrl+"/"+id;
		$.ajax({
			type : 'get',
			url : target+'/'+status,
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					if(status==2){	//原先正常，现在进行关闭
						//此处请求后台程序，下方是成功后的前台处理……
						$(obj).parents("tr").find(".td-manage").prepend("<a onClick=defineStatus(this,"+id+",1,'"+targetUrl+"') href='javascript:;' title='启用' style='text-decoration:none'><i class='Hui-iconfont'>&#xe615;</i></a>");
						$(obj).parents("tr").find(".td-status").html('<span class="label label-warning radius">禁用</span>');
					}else{	//进行开启
						//此处请求后台程序，下方是成功后的前台处理……
						$(obj).parents("tr").find(".td-manage").prepend("<a onClick=defineStatus(this,"+id+",2,'"+targetUrl+"') href='javascript:;' title='停用' style='text-decoration:none'><i class='Hui-iconfont'>&#xe631;</i></a>");
						$(obj).parents("tr").find(".td-status").html('<span class="label label-primary radius">正常</span>');
					}
					$(obj).remove();
					layer.msg(data.message,{icon: 1,time:1000});
				} else {
					layer.msg(data.message, {icon : 5,time : 1000});
				}
			},
			error : function(data) {
				console.log(data.msg);
			}
		});
		
	});
}




/**推送*/
function pushMessage(alertStr,targetUrl){
	layer.confirm(alertStr,function(index){
		$.ajax({
			type : 'get',
			url : targetUrl,
			dataType : 'json',
			success : function(data) {
				if (data.code == '1') {
					layer.msg(data.message,{icon: 1,time:1000});
				} else {
					layer.msg(data.message, {icon : 5,time : 1000});
				}
			},
			error : function(data) {
				console.log(data.msg);
			}
		});
		
	});
}