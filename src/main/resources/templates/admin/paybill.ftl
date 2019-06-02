<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>


<#--<div style="margin:20px 0;"></div>-->
<table class="easyui-datagrid" id="query"  title="DataGrid Complex Toolbar"
       data-options="rownumbers:true,singleSelect:true,url:'http://localhost:8080/bill/queryall',method:'get',toolbar:'#tb',footer:'#ft',
       pagination:true,pageNumber:1,pageList:[5,10,15,20,25],fitColumns:true">
    <thead>
    <tr>
        <th data-options="field:'payId',width:80">payId</th>
        <th data-options="field:'orderId',width:100">orderId</th>
        <th data-options="field:'openId',width:100">openId</th>
        <th data-options="field:'tradeType',width:80,align:'right'">tradeType</th>
        <th data-options="field:'payStatus',width:80,align:'right'">payStatus</th>
        <th data-options="field:'payFee',width:80">payFee</th>
        <th data-options="field:'createTime',width:100">createTime</th>
        <th data-options="field:'updateTime',width:100">updateTime</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;" >
    ID From:<input class="easyui-numberbox" style="width:29%;height:32px" id="start">
    TO:<input class="easyui-numberbox" style="width:29%;height:32px" id="end">
    <a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
    <a href="#" id="clear" class="easyui-linkbutton" iconCls="icon-clear">Clear</a>
</div>
<div id="ft" style="padding:2px 5px;">
    <#--    <a href="#" id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">Add User</a>-->
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>payId</label>
            <input id="payId" name="payId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>orderId</label>
            <input id="orderId" name="orderId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>openId</label>
            <input id="openId" name="openId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>tradeType</label>
            <input id="tradeType" name="tradeType" class="easyui-textbox"  required="true">
        </div>
        <div class="fitem">
            <label>payStatus</label>
            <input id="payStatus" name="payStatus" class="easyui-textbox"  required="true">
        </div>
        <div class="fitem">
            <label>payFee</label>
            <input id="payFee" name="payFee" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>createTime</label>
            <input id="createTime" name="createTime" class="easyui-textbox"  required="true">
        </div>
        <div class="fitem">
            <label>update_time</label>
            <input id="updateTime" name="updateTime" class="easyui-textbox"  required="true">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<script language="JavaScript" type="text/javascript">
    var url;
    <#--搜索按键的处理-->
    $(function(){
        $('#search').bind('click', function(){
            var start = $("#start").val();
            var end = $("#end").val();
            if(start>end){
                alert("输入错误");
                return false;
            }
            $("#query").datagrid({
                queryParams: {
                    start: start,
                    end: end
                }
            });
            console.info(start);
            console.info(end);
        });
    });
    //    清除按键的处理
    $(function () {
        $("#clear").bind('click',function () {
            $("#start").numberbox("clear");
            $("#end").numberbox("clear");
            console.info("clear");
        })
    })
    function saveUser(){
        $('#fm').form('submit',{
            url: url,
            success: function(result){
                var result = eval('('+result+')');
                if(result == '1')
                {
                    alert("添加更新成功");
                    $('#dlg').dialog('close');		// close the dialog
                    $('#query').datagrid('reload');	// reload the user data
                } else {
                    alert("添加更新失败");
                    $('#dlg').dialog('close');		// close the dialog
                    $('#query').datagrid('reload');	// reload the user data
                }
            }
        });
    }
    function destroyUser(){
        var row = $('#query').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                if (r){
                    $.post('http://localhost:8080/bill/delete',{payId:row.payId},function(result){
                        if (result=='1'){
                            alert("删除成功");
                            $('#query').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "删除失败"
                            });
                        }
                    },'json');
                }
            });
        }
    }
    function editUser(){
        var row = $('#query').datagrid('getSelected');
        console.info(row);
        if (row){
            $('#dlg').dialog('open').dialog('setTitle','Edit User');
            $('#fm').form('load',row);
            url = 'http://localhost:8080/bill/update?Id='+row.payId;
        }
    }
</script>

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