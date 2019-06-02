  <!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>

            <form class="form">
                <input type="text" id="username"  placeholder="Username">
                <input type="password" id="password"  placeholder="Password">
                <button type="submit" id="button">Login</button>
            </form>
        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>


<script>
    jQuery(document).ready(function () {
        //    登陆请求
        $("#button").on("click",function () {
            var u = $("#username").val();
            var p = $("#password").val();
            if(u== null || u=="")
            {
                alert("用户名不能为空");
                return false;
            }
            if(p== null || p=="")
            {
                alert("密码名不能为空");
                return false;
            }
            $.ajax({
                type:"POST",
                contentType:"application/x-www-form-urlencoded",
                url:"http://localhost:8080/visit/login",
                data:{"username":$("#username").val(),"password":$("#password").val()},
                success:function (data) {
                    if(data=="1"){
                        alert("登陆成功");
                        window.location.href="http://localhost:8080/jump/admin/index";
                    }
                    else{
                        alert("登陆失败");
                        window.location.href="http://localhost:8080/login";
                    }
                },
                fail:function (data) {
                    alert("消息发送失败："+data.responseText);
                }
            })
            return false;
        })
    });
</script>





<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>用户及数据管理系统</h1>
</div>
</body>
</html>