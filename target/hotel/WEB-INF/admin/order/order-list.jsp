<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/html5shiv.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/pageResources/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]--> 
<title>订单列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form action="${pageContext.request.contextPath }/order/getOrderList" method="get">
		<div class="text-c">
			<span class="select-box inline">
			<select name="category"class="select">
					<option value="" selected="selected">全部分类</option>
					<option value="1">待支付</option>
					<option value="2">预定中</option>
					<option value="3">已入住</option>
					<option value="4">取消订单</option>
					<option value="5">延长入住时间</option>
					<option value="6">退房成功</option>
					<option value="7">已评价</option>
			</select>
			</span> 日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" class="input-text Wdate" style="width: 120px;" name="startTime" value="${searchVo.startTime}" >
			- <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" class="input-text Wdate" style="width: 120px;" name="endTime" value="${searchVo.endTime}" >
			<input type="text"  placeholder=" 单号" name="text" value="${searchVo.text}"
				style="width: 250px" class="input-text">
			<button name="" class="btn btn-success" type="submit">
				<i class="Hui-iconfont">&#xe665;</i> 搜订单
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<c:if test="${userInfo.roleId == 1}">
					<a href="javascript:;" onclick="return deleteChoice('${pageContext.request.contextPath }/order/deleteShopByChoice')" class="btn btn-danger  radius"><i class="Hui-iconfont">&#xe6e2;</i>批量删除</a>
					<a href="javascript:;" onclick="return returnOrder('${pageContext.request.contextPath }/order/returnOrder',10)" class="btn btn-warning radius"><i class="Hui-iconfont">&#xe6e1;</i> 订单作废</a>
				</c:if>
					<a href="javascript:;" onclick="return deliveryOrder('${pageContext.request.contextPath }/order/delieveryOrder')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe669;</i> 用户入住</a>
					<a href="javascript:;" onclick="return deliveryOrder('${pageContext.request.contextPath }/order/delieveryOrder')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe669;</i> 延长入住时间</a>
					<a href="javascript:;" onclick="return returnOrder('${pageContext.request.contextPath }/order/returnOrder',9)" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe6e1;</i> 同意取消订单</a>
					<a href="javascript:;" onclick="return returnOrder('${pageContext.request.contextPath }/order/returnOrder',10)" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe6dd;</i> 拒绝取消</a>
					<a href="javascript:;" onclick="return returnOrder('${pageContext.request.contextPath }/order/returnOrder',10)" class="btn btn-warning radius"><i class="Hui-iconfont">&#xe66b;</i> 取消订单</a>
					<a href="javascript:;" onclick="return returnOrder('${pageContext.request.contextPath }/order/returnOrder',10)" class="btn btn-info radius"><i class="Hui-iconfont">&#xe6e1;</i> 退房完成</a>
				</span> <span class="r"><strong></strong>
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="25">序号</th>
						<th width="100">订单编号</th>
						<th width="80">用户</th>
						<th width="80">酒店名称</th>
						<th width="50">房间编号</th>
						<th width="80">支付金额</th>
						<th width="100">订单状态</th>
						<th width="100">创建时间</th>
						<th width="100">更新时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${orderList != null }">
						<c:forEach items="${orderList }" var="order" varStatus="status">
      					<tr class="text-c">
						<td><input type="checkbox" value="${order.id}" name="choice"></td>
						<td>${status.index+1}</td>
						<td>${order.orderCode}</td>
						<td>${order.user.username}</td>
						<td>${order.room.hotel.name}</td>
						<td>${order.room.number}</td>
						<td>${order.pay}</td>
						<td>
							<c:choose>
								<c:when test="${order.status == 1}">
									<span class="label label-danger radius">预定中 </span>
								</c:when>
								<c:when test="${order.status == 2}">
									<span class="label label-danger radius">预定中 </span>
								</c:when>
								<c:when test="${order.status == 3}">
								<span class="label label-warning radius">已入住 </span>
								</c:when>
								<c:when test="${order.status == 4}">
									<span class="label label-warning radius">取消订单 </span>
								</c:when>
								<c:when test="${order.status == 5}">
									<span class="label label-success radius">延长入住时间 </span>
								</c:when>
								<c:when test="${order.status == 6}">
									<span class="label label-success radius">退房成功 </span>
								</c:when>
								<c:when test="${order.status == 7}">
									<span class="label label-primary radius">已评价 </span>
								</c:when>
								<c:otherwise>
									<span class="label label-default radius">无效订单</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td><span
							class="label label-success radius"><fmt:formatDate value="${order.createTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td><span
							class="label label-success radius"><fmt:formatDate value="${order.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td class="f-14 td-manage">
							<a style="text-decoration: none" class="ml-5"
							   onClick="edit('更多信息','${pageContext.request.contextPath }/order/getShopById/${order.id}?view=admin/shop/shop-add','10001')"
							   href="javascript:;" title="更多信息"><i class="Hui-iconfont">&#xe717;</i></a>
							<c:if test="${userInfo.roleId == 1}">
							 <a style="text-decoration: none" class="ml-5"
							onClick="edit('修改信息','${pageContext.request.contextPath }/order/getShopById/${order.id}?view=admin/shop/shop-add','10001')"
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a style="text-decoration: none" class="ml-5"
							onClick="deleteOne(this,'${pageContext.request.contextPath }/order/delete/${order.id}')" href="javascript:;"
							title="删除信息"><i class="Hui-iconfont">&#xe6e2;</i></a>
							</c:if>
						</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/pageResources/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/pageResources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/newJs/curd.js"></script>
	<script type="text/javascript">
		// 设置所搜返回后的选中值
		$(".select").val("${searchVo.category}");



        /** 发货* */
        function deliveryOrder(targetUrl) {
            var checkbox = document.getElementsByName("choice");
            var isSelect;
            var dataStr = "";
            var checkObj = new Array();
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    isSelect = true;
                    dataStr += "," + checkbox[i].value;
                    checkObj.push(checkbox[i]);
                }
            }
            if (!isSelect) {
                layer.alert("请选择要操作的数据");
                return false;
            }

            layer.confirm('确认发货吗？', function(index) {
                $.ajax({
                    type : 'get',
                    url : targetUrl,
                    dataType : 'json',
                    data : {
                        "choiceId" : dataStr
                    },
                    success : function(data) {
                        if (data.result) {
                            //TODO 刷新
                            window.location.replace( window.location.href);
                        } else {
                            layer.msg(data.msg, {
                                icon : 5,
                                time : 2000
                            });
                        }

                    },
                    error : function(data) {
                        console.log(data.msg);
                    }
                });

            });
        }

        /** 退货* */
        function returnOrder(targetUrl,status) {
            var checkbox = document.getElementsByName("choice");
            var isSelect;
            var dataStr = "";
            var checkObj = new Array();
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    isSelect = true;
                    dataStr += "," + checkbox[i].value;
                    checkObj.push(checkbox[i]);
                }
            }
            if (!isSelect) {
                layer.alert("请选择要操作的数据");
                return false;
            }

            layer.confirm('确认处理退货请求吗？', function(index) {
                $.ajax({
                    type : 'get',
                    url : targetUrl,
                    dataType : 'json',
                    data : {
                        "choiceId" : dataStr,
                        "status" : status
                    },
                    success : function(data) {
                        if (data.result) {
                            //TODO 刷新
                            window.location.replace( window.location.href);
                        } else {
                            layer.msg(data.msg, {
                                icon : 5,
                                time : 2000
                            });
                        }

                    },
                    error : function(data) {
                        console.log(data.msg);
                    }
                });

            });
        }

	</script>
</body>
</html>