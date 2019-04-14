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
	
    <title>酒店预订登陆</title>
	
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
						<li>登陆</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="heading"><h2 style="color: #ed9c28">登陆</h2></div>
					<form name="form1" id="ff1" action="">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="手机号 :" name="loginMobile" id="loginMobile" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密码 :" name="loginPassword" id="loginPassword" required>
						</div>
						<div class="form-group">
								<input class="input-text size-L" type="text" placeholder="验证码" name="vcode" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
								<img src="${pageContext.request.contextPath }/verify-code" id="vcode_img"> <a id="changeCode" href="javascript:;">看不清，换一张</a>
						</div>
						<button type="button" id='loginBtn' class="btn btn-1" name="login">登陆</button>
						<a href="${pageContext.request.contextPath }/dispatcher?view=client/forgetPwd">忘记密码 ?</a>
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

    //确认登陆
    $('#loginBtn').click(function(){
        if ($("[name='loginMobile']").val() == null || $("[name='loginMobile']").val() == ""){
            layer.msg("请输入账号",{icon:5,time:3000});
            return;
        } else if($("[name='loginPassword']").val() == null || $("[name='loginPassword']").val() == ""){
            layer.msg("请输入密码",{icon:5,time:3000});
            return;
        }else if($("[name='vcode']").val() == null || $("[name='vcode']").val() == ""){
            layer.msg("请输入验证码",{icon:5,time:3000});
            return;
        }
        $.ajax({
            type: 'get',
            url: '${pageContext.request.contextPath }/login',
            dataType: 'json',
            data:{
                "mobile":$("[name='loginMobile']").val(),
                "password":$("[name='loginPassword']").val(),
                "vcode":$("[name='vcode']").val(),
				"type":"common"
            },
            success: function(data){
                if(data.result){
                    layer.msg(data.msg,{icon:1,time:1000});
                    setTimeout(function(){
                        location.href='${pageContext.request.contextPath }/dispatcher?view=/client/index';
                    }, 1000);//1秒后刷新界面
                }else {
                    layer.msg(data.msg,{icon:5,time:1000});
                    //修改验证码
                    $('#vcode_img').attr("src","${pageContext.request.contextPath }/verify-code?"+Math.random());
                }
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });

</script>
</html>