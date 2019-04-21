<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/css/bootstrap.min.css" type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/css/style.css">
	
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/clientlib/font-awesome/css/font-awesome.min.css" type="text/css">
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
<!--noSearch-->
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/noSearch.jsp"/>
<!--Navigation-->
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/orderMenu.jsp"/>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Cart Page//////////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/dispatcher?view=/client/order/cart">我的订单</a></li>
						<li><a href="${pageContext.request.contextPath }/dispatcher?view=/client/order/orderCancer">已取消订单</a></li>
					</ul>
				</div>
			</div>
			<div  class="row" id="cartPhone">
			</div>
			<div class="row">
				<div class="pricedetails">

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

    //完成
    $(document).ready(function(){
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath }/client/order/getOrderList",
			dataType : 'json',
			data:{
				"index":"4",
			},
			success : function(data) {
				if (data.result) {
					var pageViewVo = data.data;//PageViewVo
					var totalPrice = 0;
					if (pageViewVo.size > 0 && pageViewVo.data != null){
						for (var i = 0; i < pageViewVo.data.length; i++ ){
							var order  = pageViewVo.data[i];
                            totalPrice += order.pay;
                            var numName = "num"+i;

                            var pc = '${pageContext.request.contextPath }/clientlib/images/r'+((i+1)%8+1)+'.jpg';
                            if (order.room.pictures != null){
                                pc = "${pageContext.request.contextPath }"+order.phone.pictures[0].pathUrl;
                            }

                            var statusLabel = statusLabel = "<div style='color: red'>订单取消</div>";
                            <!-- 单个手机 -->
							var orderView =
								'<div class="row">'+
									'<div class="product well">'+
										'<div class="col-md-3">'+
											'<div class="image">'+
                                				'<a href="${pageContext.request.contextPath }/client/room/getRoomById/'+order.room.id+'?view=/client/roomInfo">'+
													'<img src="'+pc+'"   style="width:280px;height:220px" />'+
												'</a>'+
											'</div>'+
										'</div>'+
										'<div class="col-md-9">'+
											'<div class="caption">'+
												'<div class="name"><h3><a href="${pageContext.request.contextPath }/client/phone/getPhoneById/'+order.room.id+'?view=/client/phoneInfo">单号: '+order.orderCode+'</a></h3></div>'+
												'<div class="info">'+
													'<ul>'+
														'<li>下单时间: '+timeStamp2String(order.createTime)+'</li>'+
														'<li>房号: '+order.room.number+'</li>'+
														'<li>酒店: '+order.room.hotel.name+'</li>'+
													'</ul>'+
												'</div>'+
												'<div class="price">售价:￥'+order.room.money;
                                                orderView+=
												'</div>'+
												'<div class="price">应付金额:￥'+order.pay+'</div>'+
											'</div>'+statusLabel+
										'</div>'+
										'<div class="clear"></div>'+
									'</div>'+
								'</div>';
							$("#cartPhone").append(orderView);
						}
					}else {
                        $("#cartPhone").append("订单空");
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
