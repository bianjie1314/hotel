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
	
    <title>个人信息</title>
	
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
						<li>个人信息</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form name="form2" id="ff2">
						<div class="form-group">
							用户名:<input type="text" class="form-control" value="${clientUserInfo.username}" name="username" id="username" required>
						</div>
						<div class="form-group">
							手机号:<input type="text" class="form-control" disabled value="${clientUserInfo.mobile}" name="mobile" id="mobile" required>
						</div>
						<div class="form-group">
							余额:<input type="text" class="form-control" disabled  value="${clientUserInfo.balance}" name="balance" id="balance" required>
						</div>
						<button type="button" id="regBtn" onclick="formSubmit()" class="btn btn-1">确认</button>
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

    //确认添加
    function formSubmit(){
        if ($("[name='username']").val() == null || $("[name='username']").val() == ""){
            layer.msg("请输入旧密码",{icon:5,time:3000});
            return;
        } else {
            layer.confirm('确认修改吗？',function(index){
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath }/client/user/update',
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data:JSON.stringify({
                        "username":$("[name='username']").val(),
                        "id":${clientUserInfo.id},
                    }),
                    success: function(data){
                        if (data.result) {
                            layer.msg(data.msg,{icon:1,time:2000});
                            setTimeout(function(){
                                parent.location.reload();
                            }, 1200);//1.2秒后返回上一页
                        } else {
                            layer.msg(data.msg,{icon:5,time:3000});
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
</html>