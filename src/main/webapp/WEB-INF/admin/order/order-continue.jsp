<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="${pageContext.request.contextPath }/pageResources/favicon.ico" >
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/pageResources/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/respond.min.js"></script>

    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/lib/Hui-iconfont/1.0.8/iconfont.css" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <link href="${pageContext.request.contextPath }/pageResources/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
        <div class="row cl">
            <div class="text-c">
               单号: ${orderInfo.orderCode}
            </div>
        </div>
        <div class="row cl">
              <div class="text-c">
			 日期范围： <input type="text"
                                 onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
                                 id="logmin" class="input-text Wdate" style="width: 120px;" name="startTime">
                    - <input type="text"
                             onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}'})"
                             id="logmax" class="input-text Wdate" style="width: 120px;" name="endTime" >
              </div>
        </div>
        <div class="row cl">
            <div class="text-c">
                <button onClick="formSubmit();"  class="btn btn-success" type="submit"> <i class="Hui-iconfont"></i>确认延长</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">

    //确认添加
    function formSubmit(){
        if ($("[name='startTime']").val() == null || $("[name='startTime']").val() == ""){
            layer.msg("请选择开始时间",{icon:5,time:3000});
            return;
        } else if ($("[name='endTime']").val() == null || $("[name='endTime']").val() == ""){
            layer.msg("请选择结束时间",{icon:5,time:3000});
            return;
        }else {
            layer.confirm('确认延长入住吗？',function(index){
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath }/order/continueOrder',
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data:JSON.stringify({
                        "startTime":$("[name='startTime']").val(),
                        "endTime":$("[name='endTime']").val(),
                        "orderId":${orderInfo.id}
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
</body>
</html>