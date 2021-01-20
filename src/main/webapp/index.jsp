<%--
  Created by IntelliJ IDEA.
  User: dicte
  Date: 2020/12/8
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">
    <title>Login Index</title>
</head>
<body onload="clear()">
<div class="top" id="top"
     style="z-index: 100;">
    <img class="topLogo" src="images/logo.png"  alt="logo">
</div>
<div class="container">
    <ul class="slideshow">
        <li><span></span></li>
        <li><span></span></li>
        <li><span></span></li>
        <li><span></span></li>
        <li><span></span></li>
        <li><span></span></li>
   </ul>
    <div class="login-wrapper">
        <div class="header">Admin登录</div>
            <div class="form-wrapper">
                <span style="color:red;display: block;height: 15px" id="alerting"></span>
                <input type="text" name="username" placeholder="账号" class="input-item" id="accountInput">
                <input type="password" name="password" placeholder="密码" class="input-item" id="passwordInput">

                <div class="btn">
                    <input id="loginByPasswordButton" type="submit" onclick="loginByPassword()" value="登录">
                </div>
            </div>
    </div>
</div>
</body>
<script>

    function loginByPassword(){
        var username=document.getElementById("accountInput").value;
        var password=document.getElementById("passwordInput").value;
        if(username === "" || password === ""){
            document.getElementById("alerting").innerHTML="输入不得为空";
            return;
        }
        var xhr=new XMLHttpRequest();
        var requestInfor="http://localhost:8080/admin?request=adminLogin&account="+username+"&pssword="+password;
        xhr.open('POST',requestInfor,true);
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200){
                var jsonObj=JSON.parse(xhr.responseText);
                var message=jsonObj.message;
                if(message==="SUCCESS"){
                    if(username==="root"){
                        window.location.href="/admin.jsp";
                    }
                }else if(message==="FAILED"){
                    document.getElementById("alerting").innerHTML="登陆失败";
                }
            }
        }
        xhr.send();
    }


</script>
</html>
