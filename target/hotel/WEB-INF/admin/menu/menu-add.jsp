<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE HTML>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加系统角色</title>
<link href="${pageContext.request.contextPath }/pageResources/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/pageResources/calendar/tcal.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/calendar/tcal.css" />
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" action="">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="输入菜单名称" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>链接：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="输入链接" name="url">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图标：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="输入图标" name="icon">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>级别：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select name="menuLevel"class="select" id="firstMenu">
					<option value="1">一级菜单</option>
					<option value="2">二级菜单</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">父菜单：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<select name="parentId"class="select" id="parentMenu">
			</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>状态：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<select name="status"class="select">
					<option value="1">正常</option>
					<option value="2">禁用</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>显示顺序：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" class="input-text" value="" placeholder="显示顺序" name="index">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="formSubmit()" class="btn btn-secondary radius" type="button">确认添加</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript">
//设置所搜返回后的选中值
$(".select").val("${newsInfo.category}");

//设置滑动按钮 
$('#myswitch').on('switch-change', function (e, data) {
	if(data.value==true){    
	    $('#status').val(1);    
	}else{    
	    $('#status').val(2);
	}    
});

$("#firstMenu").change(function() {
	var value = $("#firstMenu").val();
	$('#parentMenu').empty();
	if (value == 2){
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath }/menu/getNeedMenuList",
			dataType : 'json',
			data : {
				"status" : 1,
				"category" : 1,
			},
			success : function(data) {
				if (data.result) {
					var menusParents = data.data;
					if (menusParents != null){
						for (var i = 0; i < menusParents.length; i++ ){
							var it = '<option value='+menusParents[i].id+'>'+menusParents[i].name+'</option>';
							$('#parentMenu').append(it);
						}
					} else {
						layer.msg(data.msg, {icon : 5,time : 1000});
					}
				}
			},
			error : function(data) {
				console.log(data.msg);
			}
		});
	}
});


//确认添加
function formSubmit(){
    if ($("[name='name']").val() == null || $("[name='name']").val() == ""){
		layer.msg("请输入菜单名称",{icon:5,time:1000});
		return;
	} else if($("#firstMenu").val() == 2 && ($("[name='url']").val() == null || $("[name='url']").val() == "")){
		layer.msg("请输入url",{icon:5,time:1000});
		return;
	} else if($("[name='index']").val() == null || $("[name='index']").val() == ""){
		layer.msg("请输入显示顺序",{icon:5,time:1000});
		return;
	}else {

		layer.confirm('确认添加吗？',function(index){
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath }/menu/add',
				dataType: 'json',
				contentType: 'application/json;charset=UTF-8',
				data:JSON.stringify({
					"name":$("[name='name']").val(),
					"url":$("[name='url']").val(),
					"icon":$("[name='icon']").val(),
					"status":$("[name='status']").val(),
					"parentId":$("[name='parentId']").val(),
					"index":$("[name='index']").val()
			}),
			success: function(data){
				if (data.result) {
					layer.msg(data.msg,{icon:1,time:1000});
					setTimeout(function(){
						parent.location.reload();
					}, 1200);//1.2秒后返回上一页
				} else {
					layer.msg(data.msg,{icon:5,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			}
		});
	});
	}


}

</script>
</body>
</html>