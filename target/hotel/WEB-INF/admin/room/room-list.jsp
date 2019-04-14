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
<title>房间数据列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		酒店管理 <span class="c-gray en">&gt;</span> 房间列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form action="${pageContext.request.contextPath }/room/getRoomList" method="get">
		<div class="text-c">
			<span class="select-box inline">
			<select name="index"class="select" id="category">
					<option value="0" selected="selected">所属酒店</option>
			</select>
			</span> 日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" class="input-text Wdate" style="width: 120px;" name="startTime" value="${searchVo.startTime}" >
			- <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" class="input-text Wdate" style="width: 120px;" name="endTime" value="${searchVo.endTime}" >
			<input type="text"  placeholder=" 房间编号" name="text" value="${searchVo.text}"
				style="width: 250px" class="input-text">
			<button name="" class="btn btn-success" type="submit">
				<i class="Hui-iconfont">&#xe665;</i> 搜房间
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="return deleteChoice('${pageContext.request.contextPath }/room/deleteRoomByChoice')" class="btn btn-danger  radius"><i class="Hui-iconfont">&#xe6e2;</i>批量删除</a>
					<a href="javascript:;" onclick="alertShow('添加房间','${pageContext.request.contextPath }/dispatcher?view=admin/room/room-add','1000','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加房间</a>
					</span> <span class="r"><strong></strong> 
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="50">编号</th>
						<th width="120">酒店名</th>
						<th width="80">房间号</th>
						<th width="80">状态</th>
						<th width="80">面积</th>
						<th width="75">是否有独卫</th>
						<th width="60">是否带窗</th>
						<th width="100">房费</th>
						<th width="80">押金</th>
						<th width="80">创建时间</th>
						<th width="80">更新时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${roomList != null }">
						<c:forEach items="${roomList }" var="room" varStatus="status">
      					<tr class="text-c">
						<td><input type="checkbox" value="${room.id}" name="choice"></td>
						<td>${status.index+1}</td>
						<td class="td-status"><span
								class="label label-success radius">${room.hotel.name}</span></td>
						<td class="td-status"><span
							class="label label-success radius">${room.number}</span></td>
						<c:choose>
							<c:when test="${room.status == 1}">
								<td class="td-status"><span
										class="label label-success radius">正常</span></td>
							</c:when>
							<c:when test="${room.status == 2}">
								<td class="td-status"><span
										class="label label-warning radius">使用中</span></td>
							</c:when>
							<c:otherwise>
								<td class="td-status"><span
										class="label label-danger radius">已下架</span></td>
							</c:otherwise>
						</c:choose>
						<td class="td-status"><span
							class="label label-success radius">${room.area}</span></td>
						<c:choose>
							<c:when test="${room.toilet == 1}">
								<td class="td-status"><span
										class="label label-success radius">有</span></td>
							</c:when>
							<c:otherwise>
								<td class="td-status"><span
										class="label label-warning radius">无</span></td>
							</c:otherwise>
						</c:choose>
							<c:choose>
								<c:when test="${room.window == 1}">
									<td class="td-status"><span
											class="label label-success radius">有</span></td>
								</c:when>
								<c:otherwise>
									<td class="td-status"><span
											class="label label-warning radius">无</span></td>
								</c:otherwise>
							</c:choose>
						<td class="td-status"><span
									class="label label-success radius">${room.money}</span></td>
						<td class="td-status"><span
									class="label label-success radius">${room.deposit}</span></td>
						<td class="td-status"><span
							class="label label-success radius"><fmt:formatDate value="${room.createTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td class="td-status"><span
							class="label label-success radius"><fmt:formatDate value="${room.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td class="f-14 td-manage">
						<a style="text-decoration: none"
							onClick="alertShow('详细信息','${pageContext.request.contextPath }/room/getRoomById/${room.id}?view=admin/room/room-info','1000','900')" href="javascript:;"
							title="详细信息"><i class="Hui-iconfont">&#xe111;</i></a>
							 <a style="text-decoration: none" class="ml-5"
							onClick="edit('修改信息','${pageContext.request.contextPath }/room/getRoomById/${room.id}?view=admin/room/room-edit','10001')"
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a style="text-decoration: none" class="ml-5"
							onClick="deleteOne(this,'${pageContext.request.contextPath }/room/delete/${room.id}')" href="javascript:;"
							title="删除信息"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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

		//获取酒店列表
        $.ajax({
            type: 'get',
            url: '${pageContext.request.contextPath }/hotel/getHotelName',
            dataType: 'json',
            success: function(data){
                if (data.result) {
                    var menusParents = data.data;
                    if (menusParents != null){
                        for (var i = 0; i < menusParents.length; i++ ){
                            var it = '<option value='+menusParents[i].id+'>'+menusParents[i].name+'</option>';
                            $('#category').append(it);
                        }
                        // 设置所搜返回后的选中值
                        $("#category").val("${searchVo.index}");
                    } else {
                        layer.msg(data.msg, {icon : 5,time : 1000});
                    }
                } else {
                    layer.msg(data.msg,{icon:5,time:1000});
                }
            },
            error:function(data) {
                console.log(data.msg);
            }
        });
	</script>
</body>
</html>