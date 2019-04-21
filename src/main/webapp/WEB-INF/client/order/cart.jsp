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
						<li><a href="${pageContext.request.contextPath }/dispatcher?view=/client/order/cart">待支付订单</a></li>
					</ul>
				</div>
			</div>
			<div  class="row" id="cartPhone">
			</div>
			<div class="row">
				<div class="pricedetails">
					<div class="col-md-4 col-md-offset-8">
						<table>
							<h6>价格明细</h6>
							<tr>
								<td>订单数量</td>
								<td><span id="phoneNum">0</span></td>
							</tr>
							<tr>
								<td>房屋总价</td>
								<td><span id="phonePrice">0.00</span></td>
							</tr>
							<tr>
								<td>折扣</td>
								<td>-----</td>
							</tr>
							<tr style="border-top: 1px solid #333">
								<td><h5>总价</h5></td>
								<td><span id="totalPrice">0.00</span></td>
							</tr>
							<tr style="border-top: 1px solid #333">
								<td></td>
								<td><span style="color: red" id="remark"></span></td>
							</tr>
						</table>
						<center><a href="#" class="btn btn-3">继续预约</a>&nbsp;
							<a  href="javascript:;" onclick="payOrder()" class="btn btn-2">确认支付</a></center>
					</div>
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

	/**
	 * 复选框变化监听,价格变动
	 * */
	function checkChange() {
        var checkbox = document.getElementsByName("checkPay");
        var dataStr = "";
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                dataStr += checkbox[i].value+",";
            }
        }
		//统计需要支付的账单费用
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/order/countPayFree",
            dataType : 'json',
            data:{
                "orderIds":dataStr
            },
            success : function(data) {
                if (data.result) {
                    $("#phonePrice").text(data.data.phonePrice);
                    $("#totalPrice").text(data.data.totalPrice);
                    $("#phoneNum").text(data.data.size);
                    var balance = ${clientUserInfo.balance};
                    if (balance < data.data.totalPrice){
                        $("#remark").text("余额不足");
                    }

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

    }

    /** 单条-支付订单 */
    function payOrder() {
        var checkbox = document.getElementsByName("checkPay");
        var isSelect;
        var dataStr = "";
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                isSelect = true;
                dataStr += checkbox[i].value+",";
            }
        }
        if (!isSelect) {
            layer.alert("请选择要支付的订单");
            return false;
        }
        layer.confirm('确认要支付吗？', function(index) {
             $.ajax({
                type : 'get',
                url : "${pageContext.request.contextPath }/client/order/payOrders",
                dataType : 'json',
                data:{
                    "orderIds":dataStr
                },
                success : function(data) {
                    if (data.result) {
                        layer.msg(data.msg, {
                            icon : 1,
                            time : 1000
                        });
                        location.reload();
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

    /** 单条-取消订单 */
    function cancerOrder(id) {
        layer.confirm('确认要取消订单吗？', function(index) {
            $.ajax({
                type : 'get',
                url : "${pageContext.request.contextPath }/client/order/cancerOrder/"+id,
                dataType : 'json',
                success : function(data) {
                    if (data.result) {
                        layer.msg(data.msg, {
                            icon : 1,
                            time : 1000
                        });
                        location.reload();
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

    //未支付订单
    $(document).ready(function(){
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath }/client/order/getOrderList",
			dataType : 'json',
			data:{
				"index":"1",	//未支付订单
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
                            var pc = '${pageContext.request.contextPath }/clientlib/images/r'+((i+1)%5+1)+'.jpg';
                            if (order.room.pictures != null){
                                pc =  '${pageContext.request.contextPath }'+order.room.pictures[0].pathUrl;
                            }

							var orderView =
								'<div class="row">'+
									'<div class="product well">'+
										'<div class="col-md-5">'+
											'<div class="image" style="width:400px;height:200px" >'+
                                				'<a href="${pageContext.request.contextPath }/client/room/getRoomById/'+order.room.id+'?view=/client/roomInfo">'+
													'<img src="'+pc+'" style="width:280px;height:220px"/>'+
												'</a>'+
											'</div>'+
										'</div>'+
										'<div class="col-md-7">'+
											'<div class="caption">'+
												'<div class="name"><h3><a href="${pageContext.request.contextPath }/client/getRoomById/'+order.room.id+'?view=/client/roomInfo">单号: '+order.orderCode+'</a></h3></div>'+
												'<div class="info">'+
													'<ul>'+
														'<li>时间: '+timeStamp2String(order.createTime)+'</li>'+
														'<li>酒店: '+order.room.hotel.name+'</li>'+
														'<li>房号: '+order.room.number+'</li>'+
													'</ul>'+
												'</div>'+
												'<div class="price">应付金额:￥'+order.pay+'</div>'+
                                                 '<a href="javascript:;" onclick="cancerOrder('+order.id+')" class="btn btn-warning">取消订单</a>'+
												'<div><label class="checkbox"><input type="checkbox" checked onchange="checkChange()" value="'+order.id+'" name="checkPay">点我咯</label></div>'+
											'</div>'+
										'</div>'+
										'<div class="clear"></div>'+
									'</div>'+
								'</div>';
							$("#cartPhone").append(orderView);
						}
					}else {
                        $("#cartPhone").append("无待支付订单");
                    }
                    $("#phonePrice").text(totalPrice);
                    $("#totalPrice").text(totalPrice);
                    $("#phoneNum").text(pageViewVo.size);
                    var balance = ${clientUserInfo.balance};
                    if (balance < totalPrice){
                        $("#remark").text("余额不足");
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
