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
	系统设置<span class="c-gray en">&gt;</span> 权限分配 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="sureDistribute()" class="btn btn-primary  radius"><i class="Hui-iconfont">&#xe604;</i>确认分配</a>
					</span> <span class="r"><strong></strong>
			</span>
	</div>
	<div class="page-container">
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover  table-responsive">
				<thead>
					<tr class="text-c">
						<th width=""><input type="checkbox" name="" value=""></th>
						<th width="">序号</th>
						<th width="">角色名称</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${authorityInfo != null }">
						<c:forEach items="${authorityInfo }" var="authority" varStatus="status">
      					<tr class="text-c">
							<c:choose>
								<c:when test="${authority.check}">
									<td><input type="checkbox" checked value="${authority.id}" name="choice"></td>
								</c:when>
								<c:otherwise>
									<td><input type="checkbox" value="${authority.id}" name="choice"></td>
								</c:otherwise>
							</c:choose>
						<td>${status.index+1}</td>
						<td>${authority.name}</td>
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
        function sureDistribute() {
            var checkbox = document.getElementsByName("choice");
            var menuIds = "";
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    //取第一个项
                    menuIds += checkbox[i].value+",";
                }
            }
            var url = "${pageContext.request.contextPath }/roleMenu/update/";
            url = url + "/"+${roleId};
            url = url + "/"+menuIds;

            layer.confirm('确认分配吗？',function(index){
                $.ajax({
                    type: 'post',
                    url: url,
                    dataType: 'json',
                    success: function(data){
                        if (data.result) {
                            layer.msg(data.msg,{icon:1,time:1000});
                            setTimeout(function(){
                                parent.location.reload();
                            }, 1200);//1.2秒后返回上一页
                        } else {
                            layer.msg(data.msg,{icon:5,time:1000});
                        }
                    },
                    error:function(data) {
                        console.log(data.msg);
                    }
                });

            });
        }


	</script>
</body>
</html>