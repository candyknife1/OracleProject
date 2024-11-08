<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>欢迎注册</title>
  <link href="/css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
  <div class="reg-content">
    <h1>欢迎注册</h1>
    <span>已有帐号？</span> <a href="login.jsp">登录</a>
  </div>

  <form id="reg-form" action="user/register" method="post"  onsubmit="return validateForm()">

    <table>

      <tr>
        <td>用户名</td>
        <td class="inputs">
          <input name="username" type="text" id="username">
          <br>
          <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
        </td>

      </tr>

      <tr>
        <td>密码</td>
        <td class="inputs">
          <input name="password" type="password" id="password">
          <br>
          <span id="password_err" class="err_msg" style="display: none">密码至少六位</span>
        </td>
      </tr>


      <tr>
        <td>验证码</td>
        <td class="inputs">
          <input name="checkCode" type="text" id="checkCode">
          <img id="checkCodeimg" src="user/checkCode">
          <a href="#" id="changeImg">看不清？</a>
        </td>
      </tr>
      <div class="err_msg">${register_msg}</div>

    </table>

    <div class="buttons">
      <input value="注 册" type="submit" id="reg_btn">
    </div>
    <br class="clear">
  </form>

</div>
<script>
  document.getElementById("changeImg").onclick = function(){
    document.getElementById("checkCodeimg").src = "user/checkCode?" + new Date().getTime();
  }
  //密码输入框失去焦点事件
  document.getElementById("password").onblur = function() {

    var password = document.getElementById("password").value;
    // 密码验证正则表达式，要求密码至少6位且包含字母和数字
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;

    if (!passwordRegex.test(password)) {

      document.getElementById("password_err").style.display = "block";
    } else {
      document.getElementById("password_err").style.display = "none";
    }
  };

  //用户名输入框绑定失去焦点事件
  document.getElementById("username").onblur=function (){
    //获取用户名的值
    var username = document.getElementById("username").value;

    //ajax
    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "user/selectUser?username="+username);
    xhttp.send();
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        if(xhttp.responseText=="false"){
          //用户名存在,显示提示信息
          /*document.getElementById("username_err").innerHTML="用户名已存在";*/
          document.getElementById("username_err").style.display='';
        }else{
          /*document.getElementById("username_err").innerHTML="";*/
          document.getElementById("username_err").style.display='none';
        }
      }
    }

  }

  function validateForm() {
    var password = document.getElementById("password").value;
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
    if (!passwordRegex.test(password)) {
      // 如果密码不符合要求，显示错误消息
      document.getElementById("password_err").style.display = "block";
      return false; // 阻止表单提交
    }
    return true; // 允许表单提交
  }

</script>

</body>
</html>
