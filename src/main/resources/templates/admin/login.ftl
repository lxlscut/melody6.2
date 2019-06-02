<#--<!DOCTYPE html>-->
<#--<html>-->
<#--<head>-->
<#--    <meta charset="UTF-8"/>-->
<#--    <meta charset="UTF-8">-->
<#--    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">-->
<#--    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">-->
<#--    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">-->
<#--    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">-->
<#--    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>-->
<#--    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>-->
<#--</head>-->
<#--<body>-->
<#--<div style="margin:20px 0;"></div>-->
<#--<div class="easyui-panel" style="width:400px;padding:50px 60px">-->

<#--    <h3>登录界面</h3>-->

<#--    <div style="margin-bottom:20px">-->
<#--        <input class="easyui-textbox" id="Username" prompt="Username" iconWidth="28" style="width:100%;height:34px;padding:10px;">-->
<#--    </div>-->

<#--    <div style="margin-bottom:20px">-->
<#--        <input class="easyui-passwordbox" id="password" prompt="Password" iconWidth="28" style="width:100%;height:34px;padding:10px">-->
<#--    </div>-->

<#--    <div id="tb" style="padding:2px 8px;" >-->
<#--        <a href="#" id="search" class="easyui-linkbutton" iconCls="icon-ok" >登录</a>-->
<#--        <a href="#" id="clear" class="easyui-linkbutton" iconCls="icon-clear">取消</a>-->
<#--    </div>-->

<#--</div>-->
<#--<script language="JavaScript" type="text/javascript">-->
<#--    $(function(){-->
<#--        $('#search').bind('click', function(){-->
<#--            var name=$("#Username").textbox('getValue');-->
<#--            var password = $("#password").passwordbox('getValue');-->
<#--            $.ajax({-->
<#--                type:"POST",-->
<#--                contentType:"application/x-www-form-urlencoded",-->
<#--                url:"http://localhost:8080/visit/login",-->
<#--                data:{"username":name,"password":password},-->
<#--                success:function (data) {-->
<#--                    console.info(data);-->
<#--                    console.info("成功");-->
<#--                    window.location.href="http://localhost:8080/jump/admin/index";-->
<#--                },-->
<#--                fail:function (data) {-->
<#--                    alert("消息发送失败："+data);-->
<#--                }-->
<#--            })-->
<#--        });-->
<#--    });-->
<#--</script>-->
<#--</body>-->
<#--</html>-->



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
                data:{"u":$("#u").val(),"p":$("#p").val()},
                success:function (data) {
                    if(data.status=="success"){
                        alert("登陆成功");
                        window.location.href="http://localhost:8080/jump/admin/index";
                    }
                    else{
                        alert("登陆失败"+data);
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