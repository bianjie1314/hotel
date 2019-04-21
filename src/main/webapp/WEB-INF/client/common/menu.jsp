<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
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
</head>

<body>
    <nav id="menu" class="navbar">
		<div class="container">
			<div class="navbar-header"><span id="heading" class="visible-xs">Categories</span>
			  <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath }/dispatcher?view=/client/index">首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">酒店信息</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner" id="menuContainer">

							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>

<script>
    $(document).ready(function() {
        //获取酒店列表
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/hotel/getHotelPage",
            dataType : 'json',
            data:{
                "start":0,	//起始页
                "offset":30//数量
            },
            success : function(data) {
                if (data.result) {
                    var pageViewVo = data.data;//PageViewVo
                    $("#menuContainer").empty();
                    if (pageViewVo != null && pageViewVo.length > 0){
                        for (var i = 0; i < pageViewVo.length; i++ ){
                            var row =  Math.floor(i / 4) ;
                            var col = i % 4 ;
                            var rowId = "menus"+row;
                            if (col == 0){
                                var ulStr = '<ul class="list-unstyled" id='+rowId+'></ul>';
                                $("#menuContainer").append(ulStr);
                            }
                            var liStr ='<li><a href="javascript:;" onclick="searchHotel(\''+pageViewVo[i].name+'\')">'+pageViewVo[i].name+'</a></li>';
                            $("#"+rowId).append(liStr);
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

</html>
