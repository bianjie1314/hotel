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
	
    <title>Mobile Shop</title>
	
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
<jsp:include page="${pageContext.request.contextPath }/WEB-INF/client/common/menu.jsp"/>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Product Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="product">
						<div class="col-md-6">
							<div class="image">
								<img src="${pageContext.request.contextPath }/clientlib/images/r5.jpg" />
								<div class="image-more">
									<ul class="row">
										<c:if test="${roomInfo.pictures != null}">
											<c:forEach items="${roomInfo.pictures }" var="pic" varStatus="status">
												<li class="col-lg-3 col-sm-3 col-xs-4">
													<a href="#"><img class="img-responsive" src="${pageContext.request.contextPath }${pic.pathUrl}"></a>
												</li>
											</c:forEach>
										</c:if>
										<c:if test="${roomInfo.pictures == null }">
											 <li class="col-lg-3 col-sm-3 col-xs-4">
												<a href="#"><img class="img-responsive" src="${pageContext.request.contextPath }/clientlib/images/r1.jpg"></a>
											</li>
											 <li class="col-lg-3 col-sm-3 col-xs-4">
												<a href="#"><img class="img-responsive" src="${pageContext.request.contextPath }/clientlib/images/r2.jpg"></a>
											</li>
											 <li class="col-lg-3 col-sm-3 col-xs-4">
												<a href="#"><img class="img-responsive" src="${pageContext.request.contextPath }/clientlib/images/r3.jpg"></a>
											</li>
											 <li class="col-lg-3 col-sm-3 col-xs-4">
												<a href="#"><img class="img-responsive" src="${pageContext.request.contextPath }/clientlib/images/r4.jpg"></a>
											</li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="caption">
								<div class="name"><h3>${roomInfo.number}房</h3></div>
								<div class="info">
									<ul>
										<li>售价:￥${roomInfo.money}</li>
										<li>押金:￥${roomInfo.deposit}(入住付)</li>
									</ul>
								</div>
								<div class="price">售价:￥${roomInfo.money}</div>
								<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
								<div class="well"><a href="javascript:;" onclick="addToCar()" class="btn btn-2 ">加入购物车</a></div>
								<div class="share well">
									<strong style="margin-right: 13px;">店铺 :</strong>
									<a href="#" class="share-btn" target="_blank">
										${roomInfo.hotel.name}
									</a>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>	
					<div class="product-desc">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#evalate">评论</a></li>
						</ul>
						<div class="tab-content">
							<div id="evalate" class="tab-pane fade in active">
							</div>
						</div>
					</div>
					<div class="product-related">
						<div class="heading"><h2>相似房间</h2></div>
						<div class="products" id="similarProducts">
							<!-- 商品项 -->
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div id="sidebar" class="col-md-4">
					<div class="widget wid-product">
						<div class="heading"><h4>该酒店房间推荐</h4></div>
						<div class="content" id="shopProduct">
							<!-- 店铺商品推荐 -->
						</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/clientlib/js/functions.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>
<script type="text/javascript" >


    /**
     * 添加到购物车
     * */
    function addToCar(){
        $.ajax({
            type : 'post',
            url : "${pageContext.request.contextPath }/client/order/addCar",
            dataType : 'json',
            contentType: 'application/json;charset=UTF-8',
            success : function(data) {
                if (data.result) {
                    layer.msg(data.msg,{icon:1,time:2000});
                    setTimeout(function(){
                        parent.location.href = '${pageContext.request.contextPath }/dispatcher?view=/client/cart';
                    }, 1200);//1.2秒后返回上一页
                } else {
                    layer.msg(data.msg, {icon : 5,time : 1000});
                }
            },
            error : function(data) {
                console.log(data.msg);
            }
        });
    }
    $(document).ready(function(){
        $(".nav-tabs a").click(function(){
            $(this).tab('show');
        });
        $('.nav-tabs a').on('shown.bs.tab', function(event){
            var x = $(event.target).text();         // active tab
            var y = $(event.relatedTarget).text();  // previous tab
            $(".act span").text(x);
            $(".prev span").text(y);
        });

        //获取相似商品
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/room/getRoomPage",
            dataType : 'json',
            data:{
                "text":"${phoneInfo.name}",	//名称
                "category":"${phoneInfo.type}",	//类型
                "start":0,	//起始页
                "offset":6	//数量
            },
            success : function(data) {
                if (data.result) {
                    var pageViewVo = data.data;//PageViewVo
                    if (pageViewVo.size > 0 && pageViewVo.data != null){
                        for (var i = 0; i < pageViewVo.data.length; i++ ){
                            var room  = pageViewVo.data[i];
                            var pc = ""${pageContext.request.contextPath };
                            if (room.pictures != null){
                                pc =  '${pageContext.request.contextPath }'+room.pictures[0].pathUrl;
                            }else {
                                pc = '${pageContext.request.contextPath }/clientlib/images/r'+((i+1)%5+1)+'.jpg';
                            }
                            <!-- 单个手机 -->
                            var productView = ''+
                                '<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">'+
                                '<div class="product">'+
                                '<div class="image"><a href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/roomInfo"><img  style="width:270px;height:180px" src="'+pc+'" /></a></div>'+
                                '<div  style="margin-top:10px">'+
                                '<a href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/phoneInfo">'+room.hotel.name+'</a>'+
                                '<span class="buttons"  style="margin:10px">'+
                                '<a class="btn cart" href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/roomInfo"><span class="glyphicon glyphicon-shopping-cart"></span></a>'+
                                '</span>'+
                                '</div>'+
                                '<div class="caption">'+
                                '<div class="name"><h3><a href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/roomInfo">'+room.number+'房￥'+room.money+'</a></h3></div>'+
                                '<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>'+
                                '</div>'+
                                '</div>'+
                                '</div>';
                            $("#similarProducts").append(productView);
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

        //店铺推荐
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/room/getRoomPage",
            dataType : 'json',
            data:{
                "index":"${roomInfo.hotelId}",	//店铺
                "start":0,	//起始页
                "offset":6	//数量
            },
            success : function(data) {
                if (data.result) {
                    var pageViewVo = data.data;//PageViewVo
                    if (pageViewVo.size > 0 && pageViewVo.data != null){
                        for (var i = 0; i < pageViewVo.data.length; i++ ){
                            var room  = pageViewVo.data[i];
                            var pc = ""${pageContext.request.contextPath };
                            if (room.pictures != null){
                                pc =  '${pageContext.request.contextPath }'+room.pictures[0].pathUrl;
                            }else {
                                pc = '${pageContext.request.contextPath }/clientlib/images/r'+((i+1)%5+1)+'.jpg';
                            }
                            <!-- 单个手机 -->
                            var productView = ''+
                                '<div class="product">'+
                                '<a href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/roomInfo"><img src="'+pc+'" /></a>'+
                                '<div class="wrapper">'+
                                '<h5><a href="${pageContext.request.contextPath }/client/room/getRoomById/'+room.id+'?view=/client/roomInfo">'+room.hotel.name+'</a></h5>'+
                                '<div class="price">￥'+room.money+ '</div>' +
                                '<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>'+
                                '</div>'+
                                '</div>'
                            $("#shopProduct").append(productView);
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

        //评论
        $.ajax({
            type : 'get',
            url : "${pageContext.request.contextPath }/client/evelate/getEvelatePage",
            dataType : 'json',
            data:{
                "index":"${roomInfo.id}",	//商品id
                "status":1,
                "start":0,	//起始页
                "offset":10	//数量
            },
            success : function(data) {
                if (data.result) {
                    var pageViewVo = data.data;//PageViewVo
                    if (pageViewVo.size > 0 && pageViewVo.data != null){
                        for (var i = 0; i < pageViewVo.data.length; i++ ){
                            var evelate  = pageViewVo.data[i];
                            var timeStr = timeStamp2String(evelate.createTime);
                            var evelateView = ''+
                                '<div class="product">'+
                                '<div class="wrapper">'+
                                '<div class="info">'+evelate.user.username+ '  '+timeStr+'</div>'+
                                '<div class="content">'+evelate.content+'</span></div>'+
                                '<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>'+
                                '</div>'+
                                '</div>'+
                                '<hr/>'
                            $("#evalate").append(evelateView);
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

</body>
</html>
