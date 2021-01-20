<%--
  Created by IntelliJ IDEA.
  User: dicte
  Date: 2020/12/10
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>医生管理界面</title>
    <link rel="stylesheet" href="css/admin.css">
</head>
<body onload="updateUserInfor()">
    <div class="titleBar">医生管理面板</div>

    <div class="functionBar">
        <button id="append" onclick="appendUser()">增加医生</button>
        <button id="check" onclick="appendDate()">新增预约</button>
    </div>
    <div id="userDisplay">
        <table id="userTable">
            <tr>
                <th>医生姓名</th>
                <th>医生科室</th>
                <th>排班日期</th>
                <th>剩余量</th>
            </tr>
        </table>
    </div>

    <div class="append-wrapper" id="appendPanel">
        <div class="header" id="headers">新增医生</div>
        <div class="form-wrapper">
            <span style="color:red;display: block;height: 15px" id="alerting"></span>
            <input type="text" name="username" placeholder="医生姓名" class="input-item" id="accountInput">
            <input type="text" name="password" placeholder="科室名称" class="input-item" id="passwordInput">
            <input type="text" name="username" placeholder="医生职称" class="input-item" id="phoneInput">
            <div class="btn">
                <input id="confirmButton" type="submit" onclick="funcSend()" value="确认">
                <input id="cancelButton" type="submit" onclick="cancelFunc()" value="取消">
            </div>
        </div>
    </div>
</body>

<script>

    var cancelFunc=function cancelSend(){
        updateUserInfor();
        document.getElementById("userDisplay").style.display="inline";
        var appendUserForm=document.getElementById("appendPanel");
        appendUserForm.style.display="none";
        document.getElementById("headers").innerText="新增医生";
    }

    function appendUser(){
        document.getElementById("userDisplay").style.display="none";
        document.getElementById("accountInput").style.display="inline";
        var appendUserForm=document.getElementById("appendPanel");
        appendUserForm.style.display="inline-block";
        document.getElementById("accountInput").setAttribute("placeholder","医生姓名");
        document.getElementById("passwordInput").setAttribute("placeholder","科室名称");
        document.getElementById("phoneInput").setAttribute("placeholder","医生职称");
        document.getElementById("confirmButton").onclick=funcSend;
        document.getElementById("cancelButton").onclick=cancelFunc;
    }

    function appendDate(){
        document.getElementById("userDisplay").style.display="none";
        document.getElementById("accountInput").style.display="inline";
        var appendUserForm=document.getElementById("appendPanel");
        appendUserForm.style.display="inline-block";
        document.getElementById("accountInput").setAttribute("placeholder","医生编号");
        document.getElementById("passwordInput").setAttribute("placeholder","预约日期");
        document.getElementById("phoneInput").setAttribute("placeholder","预约数量");
        document.getElementById("confirmButton").onclick=funcAppend;
        document.getElementById("cancelButton").onclick=cancelFunc;
    }

    var funcAppend=function(){
        var name=document.getElementById("accountInput").value;
        var date=document.getElementById("passwordInput").value;
        var surplus=document.getElementById("phoneInput").value;
        if(name === "" || date === ""||surplus ===""){
            document.getElementById("alerting").innerHTML="输入不得为空";
            return;
        }
        var xhr=new XMLHttpRequest();
        var xhrInfor="http://localhost:8080/admin?request=appendReserve&doctorID="+name
            +"&date="+date+"&surplus="+surplus;
        xhr.open("POST",xhrInfor,true);
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200) {
                jsonObj=JSON.parse(xhr.responseText);
                var message=jsonObj.message;
                if(message==="PHONE ALREADY EXISTED"){
                    document.getElementById("alerting").innerHTML="手机号已存在";
                }else if(message==="USERNAME ALREADY EXISTED"){
                    document.getElementById("alerting").innerHTML="用户名已存在";
                }else if(message==="APPEND SUCCESS"){
                    alert("预约新增成功！");
                    updateUserInfor();
                    document.getElementById("userDisplay").style.display="inline";
                    var appendUserForm=document.getElementById("appendPanel");
                    appendUserForm.style.display="none";
                    document.getElementById("accountInput").value="";
                    document.getElementById("phoneInput").value="";
                    document.getElementById("passwordInput").value="";
                }
            }
        }
        xhr.send();
    }

    var EditFunc=function edit(){
        var modifyUsername=this.parentElement.parentElement.firstChild.textContent;
        document.getElementById("userDisplay").style.display="none";
        var appendUserForm=document.getElementById("appendPanel");
        appendUserForm.style.display="inline-block";
        document.getElementById("headers").innerHTML="修改"+modifyUsername;
        document.getElementById("accountInput").style.display="none";
        document.getElementById("confirmButton").onclick=modifyFunc;
        document.getElementById("cancelButton").onclick=cancelFunc;
        username=modifyUsername;
    }

    var funcSend=function onSendUsers(){
        var name=document.getElementById("accountInput").value;
        var department=document.getElementById("passwordInput").value;
        var degree=document.getElementById("phoneInput").value;
        if(name === "" || department === ""||degree ===""){
            document.getElementById("alerting").innerHTML="输入不得为空";
            return;
        }
        var xhr=new XMLHttpRequest();
        var xhrInfor="http://localhost:8080/admin?request=appendDoctor&doctorName="+name
            +"&doctorDepartment="+department+"&doctorDegree="+degree;
        xhr.open("POST",xhrInfor,true);
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200) {
                jsonObj=JSON.parse(xhr.responseText);
                var message=jsonObj.message;
                if(message==="PHONE ALREADY EXISTED"){
                    document.getElementById("alerting").innerHTML="手机号已存在";
                }else if(message==="USERNAME ALREADY EXISTED"){
                    document.getElementById("alerting").innerHTML="用户名已存在";
                }else if(message==="APPEND SUCCESS"){
                    alert("医生新增成功！");
                    updateUserInfor();
                    document.getElementById("userDisplay").style.display="inline";
                    var appendUserForm=document.getElementById("appendPanel");
                    appendUserForm.style.display="none";
                    document.getElementById("accountInput").value="";
                    document.getElementById("phoneInput").value="";
                    document.getElementById("passwordInput").value="";
                }
            }
        }
        xhr.send();
    }


    function updateUserInfor(){
        document.getElementById("userDisplay").innerHTML="<table id=\"userTable\">\n" +
            "            <tr>\n" +
            "                <th>医生姓名</th>\n" +
            "                <th>科室名称</th>\n" +
            "                <th>排班日期</th>\n" +
            "                <th>剩余量</th>\n" +
            "            </tr>\n" +
            "        </table>"
        var xhr=new XMLHttpRequest();
        var jsonObj
        var requestInfor="http://localhost:8080/admin?request=check";
        xhr.open('GET',requestInfor,true);
        xhr.setRequestHeader('ACCEPT_CHARSET','UTF-8');
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200) {
                jsonObj=JSON.parse(xhr.responseText);
                var jsonLenth=0;
                var key;
                for(key in jsonObj){
                    if (jsonObj.hasOwnProperty(key))
                        jsonLenth++;
                }
                for (var i=0;i<jsonLenth;i++){
                    var tr=document.createElement("tr");
                    var td = document.createElement("td");
                    var modi,del;
                    td.innerText = jsonObj[i].doctorName;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerText = jsonObj[i].doctorDepartment;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerText = jsonObj[i].date;
                    tr.appendChild(td);
                    td = document.createElement("td");
                    td.innerText = jsonObj[i].surplus;
                    tr.appendChild(td);
                    document.getElementById("userTable").appendChild(tr);
                }
            }
        }
        xhr.send();
    }


</script>
</html>
