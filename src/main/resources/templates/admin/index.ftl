<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>

<body class="easyui-layout">

<script language="JavaScript" type="text/javascript">
    $(function () {
            $("#user").tree({
                onClick: function(node){
                    var content = '<iframe scrolling="no" frameborder="0"  src="'+node.attributes.url+'" style="width:100%;height:100%;"></iframe>';
                    var has = $('#tt').tabs("exists", node.text);
                    console.info(node.text);
                    if (has)
                    {
                        console.info(has);
                        $('#tt').tabs("select", node.text);
                    }else{
                        console.info(has);
                        $('#tt').tabs('add',{
                            title:node.text,
                            content:content,
                            closable:true,
                            tools:[{
                                iconCls:'icon-mini-refresh',
                                handler:function(){
                                    alert('refresh');
                                }
                            }]
                        });
                    }
                }
            });
        }
    )
    $(function () {
            $("#book").tree({
                onClick: function(node){
                    var content = '<iframe scrolling="no" frameborder="0"  src="'+node.attributes.url+'" style="width:100%;height:100%;"></iframe>';
                    var has = $('#tt').tabs("exists", node.text);
                    console.info(node.text);
                    if (has)
                    {
                        console.info(has);
                        $('#tt').tabs("select", node.text);
                    }else{
                        console.info(has);
                        $('#tt').tabs('add',{
                            title:node.text,
                            content:content,
                            closable:true,
                            tools:[{
                                iconCls:'icon-mini-refresh',
                                handler:function(){
                                    alert('refresh');
                                }
                            }]
                        });
                    }
                }
            });
        }
    )
    $(function(){
        $('#btn').bind('click', function(){
            alert("执行登出操作");
            $.ajax({
                type:"POST",
                contentType:"application/x-www-form-urlencoded",
                url:"http://localhost:8080/visit/logout",
                success:function (data) {if (data=='1')
                    window.location.href="http://localhost:8080/login";
                else {
                    alert("登录状态异常");
                }
                },
                fail:function (data) {
                    alert("消息发送失败");
                }
            })
        });
    });
    //点击更新密码，打开此框
    $(function(){
        $('#cp').bind('click', function(){
            $('#dlg').dialog('open').dialog('setTitle','Edit User');
            $('#fm').form('clear');
        });
    });
    /**
     * 更新密码确定
     */
    function saveUser(){
        var a = $("#new").passwordbox("getValue");
        var b = $("#new1").passwordbox("getValue");
        console.info(a);
         console.info(b);
         if(a==b)
         {
             $('#fm').form('submit',{
                 url: "http://localhost:8080/update",
                 success: function(result){
                     var result = eval('('+result+')');
                     if(result == '1')
                     {
                         alert("更新成功");
                         $('#dlg').dialog('close');		// close the dialog
                         $('#query').datagrid('reload');	// reload the user data
                     } else {
                         alert("更新失败");
                         $('#dlg').dialog('close');		// close the dialog
                         $('#query').datagrid('reload');	// reload the user data
                     }
                 }
             });
         }else {
             alert("请两次输入相同的密码");
             return false;
         }
    }


</script>


<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
    <table width="100%">
        <tr>
            <th width ="60%" align="left"> <h3 style="font-family:verdana">后台管理系统</h3></th>
            <th width="20%" align="center"><a id="cp" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >更改密码</a></th>
            <th width="20%" align="center"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" >退出</a></th>
        </tr>
    </table>
</div>

<div data-options="region:'west',split:true,title:'菜单栏'" style="width:150px;">
    <#--    west content-->
    <div class="easyui-accordion" data-options="fit:true" style="width:500px;height:300px;fit:true;">
        <div title="用户管理" data-options="iconCls:'icon-ok'," style="overflow:auto;padding:10px;">
            <ul class="easyui-tree" id="user">
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/alluser'}">全部用户</li>
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/queryuser'}">用户查询</li>
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/wxuser'}">微信授权用户信息</li>
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/localuser'}">本地账号</li>
            </ul>
        </div>
        <div title="订单管理" data-options="iconCls:'icon-help'" style="padding:10px;">
            <ul class="easyui-tree" id="book">
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/paybill'}">全部订单</li>
                <li data-options="attributes:{'url':'http://localhost:8080/jump/admin/ordermaster'}">订单查询</li>
                <li data-options="attributes:{'url':'http://www.baidu.com'}">全部账单</li>
            </ul>
        </div>

    </div>
</div>

<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>

<div data-options="region:'center',title:'Center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true," ">
    <div title="Tab1" style="padding:20px;display:none;">
        tab1
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>旧密码</label>
            <input id="old" name="old" class="easyui-passwordbox" required="true">
        </div>
        <div class="fitem">
            <label>新密码</label>
            <input id="new" name="new" class="easyui-passwordbox" required="true">
        </div>
        <div class="fitem">
            <label>确认密码</label>
            <input id="new1" name="new1" class="easyui-passwordbox"  required="true">
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<style type="text/css">
    #fm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }
</style>
</body>

</html>