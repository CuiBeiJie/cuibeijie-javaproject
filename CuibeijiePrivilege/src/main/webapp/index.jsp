<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery表单验证代码仿百度注册页面表单验证</title>

<style type="text/css">
.u_logo{margin: 12px 0 24px;}
.u_logo a{}
.u_logo a img{ border:0;}
.mod-bread {background: url("<%=basePath%>/login/img/breadbg.gif") repeat-x scroll 0 0 transparent;height: 46px;}
.mod-bread .bread-left {float: left;font-size: 14px;font-weight: bold;height: 46px;line-height: 46px;text-align: right;width: 137px;}
.mod-bread .bread-right {float: right;height: 46px;line-height: 46px;padding-right: 20px;}
</style>
<link href="<%=basePath%>/login/main.css" rel="stylesheet" type="text/css" />

<!--必要样式-->
<link href="<%=basePath%>/login/user_form.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>/login/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/login/formValidator-4.1.1.js" type="text/javascript"></script>
<script src="<%=basePath%>/login/formValidatorRegex.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({theme:"baidu",mode:"AutoTip",submitOnce:true,formID:"user_form_0",
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
	$("#username").formValidator({onEmpty:"请输入用户名",onFocus:"6~12个字符，包括字母、数字、下划线，以字母开头，字母或数字结尾",onCorrect:"该用户名可以注册",tipCss:{height:36,width:460}}).inputValidator({min:6,max:12,onError:"你输入的用户长度不正确,请确认"}).regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	    .ajaxValidator({
	    dataType : "json",
		async : true,
		url : "/CuibeijiePrivilege/validateUserName.json",
		type: 'POST',
		success : function(result){
            if(result.ret){
               return true;
            }else{
              return false;
            }
            return false;
		},
		buttons: $("#user_submit"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该用户名不可用，请更换用户名",
		onWait : "正在进行合法性校验，请稍候..."
	});
	$("#mail").formValidator({onEmpty:"请输入邮箱",onFocus:"请输入常用邮箱，通过验证后可用于登陆和找回密码",defaultValue:"@",tipCss:{height:36,width:320}}).inputValidator({min:6,max:60,onError:"请输入邮箱"}).regexValidator({regExp:"email",dataType:"enum",onError:"邮箱格式不正确"});
	$("#pwd").formValidator({onEmpty:"请输入密码",onFocus:"6~16个字符，包括字母、数字、特殊符号，区分大小写",tipCss:{height:68,width:320}}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"}).passwordValidator({compareID:"username"});
	$("#pwd2").formValidator({onEmpty:"请输入确认密码",onFocus:"请输入确认密码",tipCss:{height:36,width:320}}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"pwd",operateor:"=",onError:"2次密码不一致,请确认"});

    $("#verifycode").formValidator({onShowText:"请输入验证码",onFocus:"请输入图片中的字符，不分区大小写",onEmpty:"请输入验证码",tipCss:{left:194,height:36,width:320}}).inputValidator({min:1,onError:"请输入验证码"});
	
	$.formValidator.reloadAutoTip();
});
</script>
</head>

<body>
<div class="div">
	<div class="u_logo">
    	<a href="http://www.baidu.com"><img src="<%=basePath%>/login/img/logo.gif" alt="baidu"></a>
    </div>
</div>

<div class="mod-bread clearfix">
	<div class="div">
		<div class="bread-left"> 填写注册信息 </div>
    	<div class="bread-right"> 已有帐号？<a id="login" href="javascript:;">马上登录</a></div>
	</div>
</div>

<div class="div" style="padding-top:20px;">
<form id="user_form_0" class="user_form" method="post" target="pass_reg_iframe_0" action="#">
<p>
<label id="label_username" for="username">用户名</label>
<input id="username" class="user_input" type="text" name="username" tabindex="1">
</p>

<p>
<label id="label_password_0" for="password_0">密码</label>
<input id="pwd" class="user_input" type="password" name="pwd" tabindex="2">
</p>

<p>
<label id="label_password_1" for="password_1">确认密码</label>
<input id="pwd2" class="user_input" type="password" name="pwd2" tabindex="3">
</p>

<p>
<label id="label_mail" for="mail">邮箱</label>
<input id="mail" class="user_input" type="text" name="mail" tabindex="4">
</p>

<p class="user_p_verifycode">
<label id="label_verifycode" for="verifycode">验证码</label>
<input id="verifycode" class="user_input" style="width:80px;" type="text" name="verifycode" tabindex="5">
</p>

<p class="user_p_img_verifycode"><img src="" class="user_verifycode" id="pass_reg_img_verifycode_0" alt="验证码图片" title="验证码图片"><span class="user_change_verifycode" id="user_change_verifycode_0">看不清？</span></p>

<div class="clear"></div>
<p>
<input name="提交" type="submit" class="user_submit" id="user_submit" style="margin-left:152px;" tabindex="6" value="注册">
</p>
</form>

</div>
<iframe id="pass_reg_iframe_0" style="display:none;" src="javascript:''" name="pass_reg_iframe_0"></iframe>
</body>
</html>
