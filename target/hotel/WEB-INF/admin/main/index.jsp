﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="${pageContext.request.contextPath }/pageResources/other/favicon.ico" >
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/pageResources/other/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/html5shiv.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>酒店管理系统后台管理</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">酒店管理系统后台管理</a>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<%--<li>${userInfo.username }</li>--%>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${userInfo.username } <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="alertShow('个人信息','${pageContext.request.contextPath }/user/getUserById/${userInfo.id }?view=admin/user/user-edit','1000','500')">个人信息</a></li>
						<li><a href="javascript:;" onClick="alertShow('修改密码','${pageContext.request.contextPath }/user/getUserById/${userInfo.id }?view=admin/common/change-pwd','1000','500')">修改密码</a></li>
						<li><a href="javascript:;" onclick="loginOut()">退出</a></li>
				</ul>
			</li>
				<li id="Hui-msg"> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="${pageContext.request.contextPath }/dispatcher?view=/admin/common/welcome">我的桌面</span>
					<em></em>
				</li>
		</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" name="right_iframe" frameborder="0" src="${pageContext.request.contextPath }/dispatcher?view=/admin/common/welcome"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>

<script type="text/javascript">
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*退出登陆*/
function loginOut(){
    layer.confirm('确认要退出登录吗？', function(index) {
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath }/exit",
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					location.href = "${pageContext.request.contextPath }/dispatcher?view=admin/common/login";
				}
			}
		})
	})
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

<!--此乃百度统计代码，请自行删除-->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!--/此乃百度统计代码，请自行删除-->
<!-- 获取菜单-->
<script>

	//上下显示
    function menuDisplay(dlid){
        //权限
        $("#"+dlid.id).slideToggle();
    }
    $(document).ready(function(){
        //先清空
       // $('.Hui-aside div').empty();


    $.ajax({
        type : 'get',
        url : "${pageContext.request.contextPath }/menu/menuLevelList",
        dataType : 'json',
        success : function(data) {
            if (data.result) {
                var menusParents = data.data;
                if (menusParents != null){

                    for (var i = 0; i < menusParents.length; i++ ){
                        var parent = menusParents[i];
                        var uid = "uid"+parent.parentId;
                        var dlid = "dlid"+i;
                        var ddid = "ddid"+i;
                        var liHtml = '<dl id='+dlid+' >' +
                            '<dt><i class="Hui-iconfont">'+parent.parentIcon+'</i> '+ parent.parentName +'<i class="Hui-iconfont menu_dropdown-arrow" onClick="menuDisplay('+ddid+')"  >&#xe6d5;</i></dt>' +
                            '<dd id='+ddid+'><ul id='+uid+' ></ul></dd></dl>';
                        $('.Hui-aside div').append(liHtml);
                        if ( parent.items != null){
                            for(var j = 0; j < parent.items.length; j++){
                                var item = parent.items[j];
                                var it = '<li><a data-href="'${pageContext.request.contextPath }+item.url+'" data-title="'+item.name+'" href="javascript:void(0)">'+item.name+'</a></li>';
                                $('#uid'+item.parentId).append(it);
                            }
						}
                    }
				}
            } else {
                layer.msg(data.msg, {icon : 5,time : 1000});
            }
        },
        error : function(data) {
            console.log(data.msg);
        }
    });
	});

</script>

<!-- 获取菜单-->
</body>
</html>