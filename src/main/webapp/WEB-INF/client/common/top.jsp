<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>酒店预订</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/css/bootstrap.min.css"  type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/css/style.css">
	
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/fonts/font-slider.css" type="text/css">
	
	<!-- jQuery and Modernizr-->
	<script src="${pageContext.request.contextPath }/clientlib/js/jquery-2.1.1.js"></script>
	
	<!-- Core JavaScript Files -->  	 
    <script src="${pageContext.request.contextPath }/clientlib/js/bootstrap.min.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath }/clientlib/js/html5shiv.js"></script>
        <script src="${pageContext.request.contextPath }/clientlib/js/respond.min.js"></script>
    <![endif]-->
	<style>
		.dropdown-inner {display: table;}
		.dropdown-inner ul {display: table-cell;}
		.dropdown-inner a {min-width: 160px;display: block;padding: 3px 20px;clear: both;line-height: 20px;color: #333333;font-size: 12px;}
	</style>
</head>

<body>
	<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">

				</div>
				<div class="col-xs-6">
					<ul class="top-link">
						<li style="color: #ed9c28">${clientUserInfo.username}</li>
						<c:if test="${clientUserInfo == null}">
							<li><a href="${pageContext.request.contextPath }/dispatcher?view=client/login" style="color: #ed9c28"><span class="glyphicon glyphicon-ok"></span> 登陆</a></li>
							<li><a href="${pageContext.request.contextPath }/dispatcher?view=client/register" style="color: #ed9c28"><span class="glyphicon glyphicon-sort-by-order"></span> 注册</a></li>
						</c:if>
						<c:if test="${clientUserInfo != null}">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: #ed9c28"><span class="glyphicon glyphicon-user"></span> 我的</a>
								<div class="dropdown-menu">
									<div class="dropdown-inner">
										<ul class="list-unstyled">
											<a href="${pageContext.request.contextPath }/dispatcher?view=client/add_wallet" style="color: #ed9c28"><span class="glyphicon glyphicon-plane"></span> 充值</a>
											<a href="${pageContext.request.contextPath }/dispatcher?view=client/userInfo" style="color: #ed9c28"><span class="glyphicon glyphicon-user"></span> 个人信息</a>
											<a href="${pageContext.request.contextPath }/dispatcher?view=client/changePwd" style="color: #ed9c28"><span class="glyphicon glyphicon-edit"></span> 修改密码</a>
										</ul>
									</div>
								</div>
							</li>
							<li><a href="${pageContext.request.contextPath }/dispatcher?view=client/order/cart" style="color: #ed9c28"><span class="glyphicon glyphicon-th-list"></span> 订单管理</a></li>
							<li><a href="javascript:;" onclick="loginOut()" style="color: #ed9c28"><span class="glyphicon glyphicon-off"></span> 退出登陆</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</nav>
</body>

<script src="${pageContext.request.contextPath }/clientlib/js/functions.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>
<script>

    /*退出登陆*/
    function loginOut(){
        layer.confirm('确认要退出登录吗？', function(index) {
            $.ajax({
                type : 'get',
                url : "${pageContext.request.contextPath }/clientExit",
                dataType : 'json',
                success : function(data) {
                    if (data.result) {
                        location.href = "${pageContext.request.contextPath }/dispatcher?view=client/login";
                    }else {
                        layer.msg(data.msg, {icon : 5,time : 1000});
                    }
                }
            })
        })
    }

</script>

</html>
