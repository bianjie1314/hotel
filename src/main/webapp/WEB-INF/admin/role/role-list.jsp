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
	系统设置<span class="c-gray en">&gt;</span> 系统角色列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form action="${pageContext.request.contextPath }/role/getRoleList" method="get">
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" class="input-text Wdate" style="width: 120px;" name="startTime" value="${searchVo.startTime}" >
			- <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" class="input-text Wdate" style="width: 120px;" name="endTime" value="${searchVo.endTime}" >
			<input type="text"  placeholder=" 角色名称" name="text" value="${searchVo.text}"
				style="width: 250px" class="input-text">
			<button name="" class="btn btn-success" type="submit">
				<i class="Hui-iconfont">&#xe665;</i> 搜角色
			</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="return deleteRole('${pageContext.request.contextPath }/role/deleteRoleByChoice')" class="btn btn-danger  radius"><i class="Hui-iconfont">&#xe6e2;</i>批量删除</a>
					<a href="javascript:;" onclick="alertShow('添加角色','${pageContext.request.contextPath }/dispatcher?view=admin/role/role-add','400','200')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe604;</i> 新建角色</a>
					<a href="javascript:;" onclick="showAudio('权限分配','${pageContext.request.contextPath }/roleMenu/getAuthority','800','400')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe604;</i> 权限分配</a>
					</span> <span class="r"><strong></strong>
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover  table-responsive">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="25">序号</th>
						<th width="80">角色名称</th>
						<th width="100">创建时间</th>
						<th width="100">更新时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${roleList != null }">
						<c:forEach items="${roleList }" var="role" varStatus="status">
      					<tr class="text-c">
						<td>
								<input type="checkbox" value="${role.id}" name="choice">
						</td>
						<td>${status.index+1}</td>
						<td>${role.name}</td>
						<td><span
							class="label label-success radius"><fmt:formatDate value="${role.createTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td><span
							class="label label-success radius"><fmt:formatDate value="${role.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></span></td>
						<td class="f-14 td-manage">
							<c:choose>
								<c:when test="${role.id != 1 && role.id != 2 && role.id != 3 }">
									<a style="text-decoration: none" class="ml-5"
									   onClick="deleteOne(this,'${pageContext.request.contextPath }/role/delete/${role.id}')" href="javascript:;"
									   title="删除信息"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</c:when>
								<c:otherwise>
									系统固定角色
								</c:otherwise>
							</c:choose>
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

        /** 权限分配* */
        function showAudio(title, url, w, h) {
            var checkbox = document.getElementsByName("choice");
            var checkObj = -1;
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    //取第一个项
                    checkObj = checkbox[i].value;
                    break;
                }
            }
            if (checkObj == -1) {
                layer.alert("请选择要操作的项");
                return;
            }
            url = url + ("/"+checkObj+"?view=admin/role/role-distribute");
            alertShow(title, url, w, h);
        }

        /** 多条-删除* */
        function deleteRole(targetUrl) {
            var checkbox = document.getElementsByName("choice");
            var isSelect;
            var dataStr = "";
            var checkObj = new Array();
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    if (checkbox[i].value == 1 || checkbox[i].value == 2 || checkbox[i].value == 3){
                        layer.msg("无法删除系统固定角色", {
                            icon : 5,
                            time : 1000
                        });
                        return;
					}
                    isSelect = true;
                    dataStr += "," + checkbox[i].value;
                    checkObj.push(checkbox[i]);
                }
            }
            if (!isSelect) {
                layer.alert("请选择要删除的项");
                return false;
            }
            layer.confirm('确认要删除吗？', function(index) {
                $.ajax({
                    type : 'get',
                    url : targetUrl,
                    dataType : 'json',
                    data : {
                        "choiceId" : dataStr
                    },
                    success : function(data) {
                        if (data.result) {
                            for (var i = 0; i < checkObj.length; i++) {
                                $(checkObj[i]).parents("tr").remove(); // 移除删除的元素显示
                            }
                            layer.msg(data.msg, {
                                icon : 1,
                                time : 1000
                            });
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
	</script>
</body>
</html>