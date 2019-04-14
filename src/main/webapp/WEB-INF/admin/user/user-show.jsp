<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/html5shiv.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/style.css" />
<link href="${pageContext.request.contextPath }/pageResources/lib/lightbox2/2.8.1/css/lightbox.css" rel="stylesheet" type="text/css" >

<!--[if IE 6]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
	<div class="formControls">
		<div class="portfoliobox">
		 	<div class="picbox">
		  	<a href="${userPojo.portrait}" data-lightbox="gallery" data-title="头像">
				<img class="avatar size-XL l" src="${userPojo.portrait}">
			</a>
			</div>
		</div>
	</div>
	<dl style="margin-left:80px; color:#fff">
		<dt>
			<span class="f-18">${userPojo.username} </span>
			<span class="pl-10 f-12"></span>
		</dt>
		<dd class="pt-10 f-12" style="margin-left:0">${userPojo.account}</dd>
	</dl>
</div>
<div class="pd-20">
	<table class="table">
		<tbody>
			<tr>
				<th class="text-r" width="100">账号类型:</th>
				<c:choose>
					<c:when test="${userPojo.type==0}">
						<td><span class="label label-warning radius">平台</span></td>
					</c:when>
					<c:when test="${userPojo.type==1}">
						<td><span class="label label-success radius">微信</span></td>
					</c:when>
					<c:otherwise>
						<td><span class="label label-primary radius">QQ</span></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th class="text-r">注册时间:</th>
				<td>${userPojo.createTime}</td>
			</tr>
			<tr>
				<th class="text-r">最近登陆时间：</th>
				<td>${userPojo.lastLoginTime}</td>
			</tr>
			<tr>
				<th class="text-r">状态：</th>
				<td>
				<c:choose>
					<c:when test="${userPojo.status==1}">正常</c:when>
					<c:otherwise>不可用</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/lightbox2/2.8.1/js/lightbox.min.js"></script> 

<!--请在下方写此页面业务相关的脚本-->
</body>
</html>