<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>酒店预注册</title>
	
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
</head>

<body>
	<!--Top-->
	<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/top.jsp"/>
	<!--search-->
	<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/noSearch.jsp"/>
	<!--Navigation-->
	<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/loginRegMenu.jsp"/>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Account Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li>注册</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="heading"><h2 style="color: #ed9c28">新用户 ? 创建账户.</h2></div>
					<form name="form2" id="ff2">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="用户名 :" name="username" id="username" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="手机号 :" name="regMobile" id="regMobile" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密码 :" name="regPassword" id="regPassword" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="确认密码 :" name="repassword" id="repassword" required>
						</div>
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" > 同意协议.
						</div>
						<button type="button" id="regBtn" class="btn btn-1">确认</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/footer.jsp"/>
</body>
<script src="${pageContext.request.contextPath }/clientlib/js/functions.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>
<script>

    //刷新验证码,需要加上标识,防止缓存不刷新
    $('#changeCode').click(function(){
        $('#vcode_img').attr("src","${pageContext.request.contextPath }/verify-code?"+Math.random());
    });
    //注册
    $('#regBtn').click(function(){
        if ($("[name='username']").val() == null || $("[name='username']").val() == ""){
            layer.msg("请输入旧密码",{icon:5,time:3000});
            return;
        } else if($("[name='regMobile']").val() == null || $("[name='regMobile']").val() == ""){
            layer.msg("请输入手机号",{icon:5,time:3000});
            return;
        }else if($("[name='regMobile']").val().length < 11){
            layer.msg("手机号格式错误",{icon:5,time:3000});
            return;
        }else if($("[name='regPassword']").val() == null || $("[name='regPassword']").val() == ""){
            layer.msg("请输入您的密码",{icon:5,time:3000});
            return;
        }else if($("[name='repassword']").val() == null || $("[name='repassword']").val() == ""){
            layer.msg("请再输入一次密码",{icon:5,time:3000});
            return;
        }else if($("[name='regPassword']").val() !=  $("[name='repassword']").val()) {
            layer.msg("两次密码不一致", {icon: 5, time: 3000});
            return;
        }
        $.ajax({
            type: 'get',
            url: '${pageContext.request.contextPath }/reg',
            dataType: 'json',
            data:{
                "username":$("[name='username']").val(),
                "mobile":$("[name='regMobile']").val(),
                "password":$("[name='regPassword']").val(),
                "type":3
            },
            success: function(data){
                if(data.result){
                    layer.msg("注册成功",{icon:1,time:1000});
                    setTimeout(function(){
                        location.href='${pageContext.request.contextPath }/dispatcher?view=/client/index';
                    }, 1000);//1秒后刷新界面
                }else {
                    layer.msg(data.msg,{icon:5,time:1000});
                }
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });

</script>
</html>