<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui/css/H-ui.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/css/H-ui.admin.css" />
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>修改密码</title>
</head>
<body>
<div class="pd-20">
  <form action="">
    <table class="table table-border table-bordered table-bg">
      <thead>
        <tr>
          <th colspan="2">修改密码</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th class="text-r" width="30%">旧密码：</th>
          <td><input name="oldpassword" id="oldpassword" class="input-text" type="password" autocomplete="off" placeholder="旧密码" >
          </td>
        </tr>
        <tr>
          <th class="text-r">新密码：</th>
          <td><input name="newpassword" id="newpassword" class="input-text" type="password" autocomplete="off" placeholder="新密码">
          </td>
        </tr>
        <tr>
          <th class="text-r">再次输入新密码：</th>
          <td><input name="newpassword2" id="newpassword2" class="input-text" type="password" autocomplete="off" placeholder="确认新密码">
          </td>
        </tr>
        <tr>
          <th></th>
          <td>
            <button type="button"  onClick="formSubmit();" class="btn btn-success radius" id="admin-password-save" name="admin-password-save"><i class="icon-ok"></i> 确定</button>
          </td>
        </tr>
      </tbody>
    </table>
  </form>
</div>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript">

    //确认添加
    function formSubmit(){
        if ($("[name='oldpassword']").val() == null || $("[name='oldpassword']").val() == ""){
            layer.msg("请输入旧密码",{icon:5,time:3000});
            return;
        } else if($("[name='newpassword']").val() == null || $("[name='newpassword']").val() == ""){
            layer.msg("请输入您的新密码",{icon:5,time:3000});
            return;
        }else if($("[name='newpassword2']").val() == null || $("[name='newpassword2']").val() == ""){
            layer.msg("请再输入一次新密码",{icon:5,time:3000});
            return;
        }else if($("[name='newpassword']").val() !=  $("[name='newpassword2']").val()){
            layer.msg("两次密码不一致",{icon:5,time:3000});
            return;
        }else {
            layer.confirm('确认修改吗？',function(index){
                $.ajax({
                    type: 'GET',
                    url: '${pageContext.request.contextPath }/changePwd',
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data:{
                        "oldPassword":$("[name='oldpassword']").val(),
                        "newPassword":$("[name='newpassword']").val(),
                        "id":${userInfo.id}
                    },
                    success: function(data){
                        if (data.result) {
                             layer.msg("修改成功",{icon:1,time:2000});
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