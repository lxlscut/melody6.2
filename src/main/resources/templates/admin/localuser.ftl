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
       data-options="rownumbers:true,singleSelect:true,url:'http://localhost:8080/localuser/alluser',method:'get',toolbar:'#tb',footer:'#ft',
       pagination:true,pageNumber:1,pageList:[5,10,15,20,25],fitColumns:true">
    <thead>
    <tr>
        <th data-options="field:'localAuthId',width:80">本地用户编号</th>
        <th data-options="field:'userId',width:100">用户编号</th>
        <th data-options="field:'username',width:100">用户名称</th>
        <th data-options="field:'createTime',width:80,align:'right'">创建时间</th>
        <th data-options="field:'updateTime',width:80,align:'right'">更新时间</th>
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
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()" disabled="true">Edit User</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>localAuthId</label>
            <input id="localAuthId" name="localAuthId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>userId</label>
            <input id="userId" name="userId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>username</label>
            <input id="username" name="username" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>createTime</label>
            <input id="createTime" name="createTime" class="easyui-textbox"  required="true">
        </div>
        <div class="fitem">
            <label>updateTime</label>
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
    //添加按键的处理
    $(function(){
        $('#add').bind('click', function(){
            $('#dlg').dialog('open').dialog('setTitle','New User');
            $('#fm').form('clear');
            var id1 = $('#userid').textbox('getValue');
            console.info(id1);
            url = 'http://localhost:8080/add';
            // ?id='+$('#id').textbox('getValue')+'&userid='+$('#userid').val()+'&nickname='+$('#nickname').val()+'&other='+$("#other").val()
        });
    });
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
                    $.post('http://localhost:8080/localuser/delete',{localAuthId:row.localAuthId},function(result){
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
            url = 'http://localhost:8080/localuser/update?updateid='+row.localAuthId;
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