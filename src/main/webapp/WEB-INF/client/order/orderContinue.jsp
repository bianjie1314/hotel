<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script src="${pageContext.request.contextPath }/clientlib/js/photo-gallery.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath }/clientlib/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath }/clientlib/js/respond.min.js"></script>
    <![endif]-->

    <script>

    </script>
</head>
<body>
<!--Top-->
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/top.jsp"/>
<!--noSearch-->
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/noSearch.jsp"/>
<!--Navigation-->
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/orderMenu.jsp"/>
<!--//////////////////////////////////////////////////-->
<!--///////////////////Product Page///////////////////-->
<!--//////////////////////////////////////////////////-->
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div id="main-content" class="col-md-12">
                <div class="product">
                    <div class="col-md-12">
                        <div class="caption">
                            <div class="name"><h3>订单: ${orderInfo.orderCode}</h3></div>
                            <div class="info">
                                <ul>
                                    <li>房间:￥${orderInfo.room.number}/天</li>
                                </ul>
                            </div>
                            <div>需支付:￥<span id="needToPay">0.00</span>
                            </div>
                            <div class="info">
                                <ul>
                                    <li><span id="payStatus" style="color: red"></span></li>
                                </ul>
                            </div>
                            <div class="rating">
                                延长时间： <input type="text"
                                             onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
                                             id="logmin" class="input-text Wdate" style="width: 120px;" name="startTime" onchange="checkChange()">
                                - <input type="text"
                                         onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}'})"
                                         id="logmax" class="input-text Wdate" style="width: 120px;" name="endTime"   onchange="checkChange()">
                            </div>
                            <div class="well"><a href="javascript:;" onclick="continueOrder()" class="btn btn-2 ">确认延长</a></div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/footer.jsp"/>

<!-- IMG-thumb -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/clientlib/js/functions.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>
<script type="text/javascript" >
    /**
     * 复选框变化监听,价格变动
     * */
    function checkChange() {
        if ($("[name='startTime']").val() == null || $("[name='startTime']").val() == ""){
            return;
        }
        if($("[name='endTime']").val() == null || $("[name='endTime']").val() == ""){
            return;
        }
        //统计需要支付的账单费用
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/order/countContinuePay",
            dataType : 'json',
            data:{
                "startTime":$("[name='startTime']").val(),
                "endTime":$("[name='endTime']").val(),
                "roomId":${orderInfo.room.id}
            },
            success : function(data) {
                if (data.result) {
                    $("#needToPay").text(data.data.totalPrice);
                    var balance = ${clientUserInfo.balance};
                    if (balance < data.data.totalPrice){
                        $("#payStatus").text("余额不足");
                    }else {
                        $("#payStatus").text("");
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

    /**
     * 添加到购物车
     * */
    function continueOrder(){
        if ($("[name='startTime']").val() == null || $("[name='startTime']").val() == ""){
            layer.msg("请选择开始时间",{icon:5,time:3000});
        } else if($("[name='endTime']").val() == null || $("[name='endTime']").val() == ""){
            layer.msg("请选择结束时间",{icon:5,time:3000});
        }else {
            layer.confirm('确认延长入住吗？',function(index) {
                $.ajax({
                    type : 'post',
                    url : "${pageContext.request.contextPath }/client/order/continueOrder",
                    dataType : 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data:JSON.stringify({
                        "startTime":$("[name='startTime']").val(),
                        "endTime":$("[name='endTime']").val(),
                        "orderId":${orderInfo.id}
                    }),
                    success : function(data) {
                        if (data.result) {
                            layer.msg(data.msg,{icon:1,time:2000});
                            setTimeout(function(){
                                location.href = '${pageContext.request.contextPath }/dispatcher?view=/client/order/inUse';
                            }, 1200);//1.2秒后返回上一页
                        } else {
                            layer.msg(data.msg, {icon : 5,time : 1000});
                        }
                    },
                    error : function(data) {
                        console.log(data.msg);
                    }
                });
            });
        }
    }

</script>

</body>
</html>
