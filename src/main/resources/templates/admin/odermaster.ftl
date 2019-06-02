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
       data-options="rownumbers:true,singleSelect:true,url:'http://localhost:8080/ordermaster/queryall',method:'get',toolbar:'#tb',footer:'#ft',
       pagination:true,pageNumber:1,pageList:[5,10,15,20,25],fitColumns:true">
    <thead>
    <tr>
        <th data-options="field:'orderId',width:80">订单号</th>
        <th data-options="field:'buyerName',width:100">用户</th>
        <th data-options="field:'buyerUserid',width:100">用户openid</th>
        <th data-options="field:'orderAmount',width:80,align:'right'">用户padid</th>
        <th data-options="field:'orderStatus',width:80,align:'right'">订单总额</th>
        <th data-options="field:'payStatus',width:80">订单状态</th>
        <th data-options="field:'createTime',width:100">支付状态</th>
        <th data-options="field:'updateTime',width:100">交易类型</th>
        <th data-options="field:'evaluate',width:100">估价</th>
        <th data-options="field:'orderAmount',width:80,align:'right'">创建时间</th>
        <th data-options="field:'orderStatus',width:80,align:'right'">更新时间</th>
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
            <label>orderId</label>
            <input id="orderId" name="orderId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>buyerName</label>
            <input id="buyerName" name="buyerName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>orderAmount</label>
            <input id="orderAmount" name="orderAmount" class="easyui-textbox"  required="true">
        </div>

        <div class="fitem">
            <label>evaluate</label>
            <input id="evaluate" name="evaluate" class="easyui-textbox"  required="true">
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

    $(function () {
        $('#orderId').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#buyerName').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
     //   $('#userId').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
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
                    $.post('http://localhost:8080/ordermaster/delete',{orderId:row.orderId},function(result){
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
            url = 'http://localhost:8080/ordermaster/update?Id='+row.orderId;
        }
    }

    $(function () {
        var jq=top.jQuery;
        $("#query").datagrid({
            onDblClickRow:function (rowIndex,rowData) {
                console.info(rowData);
                var has = jq('#tt').tabs("exists", "订单详细"+rowData.orderId+"购买信息");
                var content = '<iframe scrolling="no" frameborder="0"  src="'+'http://localhost:8080/detail/order?orderid='+rowData.orderId+'" style="width:100%;height:100%;"></iframe>';
                console.info(rowData);
                if(has){
                    jq('#tt').tabs("select", "订单详细"+rowData.orderId+"购买信息");
                }else {
                    jq('#tt').tabs('add',{//不存在，则添加一个
                        title:"订单详细"+rowData.orderId+"购买信息",
                        content:content,
                        closable:true
                    });
                }
            }
        })
    })










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
        width:100px;
    }
    .fitem input{
        width:160px;
    }
</style>

</body>
</html>