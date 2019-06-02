<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    <title>Title</title>
</head>
<body>
<div style="margin:20px 0;"></div>

<#--http://localhost:8080/detail/show?order_id='+${order}-->

<table class="easyui-datagrid" id="query"  title="DataGrid Complex Toolbar"
       data-options="rownumbers:true,singleSelect:true,url:'http://localhost:8080/detail/show?order_id='+${order},method:'get',toolbar:'#tb',footer:'#ft',
       pagination:true,pageNumber:1,pageList:[5,10,15,20,25],fitColumns:true">
    <thead>
    <tr>
        <th data-options="field:'detailId',width:80">详情编号</th>
        <th data-options="field:'orderId',width:100">订单编号</th>
        <th data-options="field:'productAddress',width:100">订单地址</th>
        <th data-options="field:'productName',width:80,align:'right'">商品名称</th>
        <th data-options="field:'productPrice',width:80,align:'right'">商品价格</th>
<#--        <th data-options="field:'buyerPadId',width:80">支付状态</th>-->
        <th data-options="field:'createTime',width:100">创建时间</th>
        <th data-options="field:'updateTime',width:100">更新时间</th>
    </tr>
    </thead>
</table>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>orderId</label>
            <input id="orderId" name="orderId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>productName</label>
            <input id="productName" name="productName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>productAddress</label>
            <input id="productAddress" name="productAddress" class="easyui-textbox" required="true">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="DOWNLOAD()" style="width:90px">下载</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>


<script language="JavaScript" type="text/javascript">

        var downloadurl = "http://localhost:8080/file/download?url=";
    $('#query').datagrid({
        onDblClickCell: function(index,field,value){
            if(field.value = "订单地址")
            {
                console.info("成功");
                console.info(value);
                var row = $('#query').datagrid('getSelected');
                console.info(row);
                if (row){
                    $('#dlg').dialog('open').dialog('setTitle','Edit User');
                    $('#fm').form('clear');
                    $('#fm').form('load',row);
                    downloadurl = downloadurl+value;
                    console.info(downloadurl);
                }
            }else {
                console.info("失败");
            }
        }
    });

    $(function () {
        $('#orderId').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#productName').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#productAddress').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
    })

    function DOWNLOAD(){
            var url = "";
            var $eleForm = $("<form method='post'></form>");
            console.info(downloadurl);
            $eleForm.attr("action",downloadurl);
            console.info(downloadurl);
            $(document.body).append($eleForm);
            //提交表单，实现下载
            $eleForm.submit();
            $('#dlg').dialog('close');
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
        width:110px;
    }
    .fitem input{
        width:160px;
    }
</style>
</body>

</html>