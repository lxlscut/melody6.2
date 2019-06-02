<!DOCTYPE html>
<html lang="en">
<head>
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
<#--style="width:700px;height:250px"-->
<table class="easyui-datagrid" id="tb" title="Basic DataGrid"
       data-options="singleSelect:true,collapsible:true,url:'http://localhost:8080/user/queryall',method:'get',
                  pagination:true,pageNumber:1,pageList:[5,10,15,20,25],fitColumns:true">
    <thead>
    <tr>
        <th data-options="field:'userInfoId',width:80">用户信息编号</th>
        <th data-options="field:'userId',width:80">用户编号</th>
        <th data-options="field:'nickname',width:100">用户昵称</th>
        <th  data-options="field:'icon',width:100, formatter:showImg">用户头像</th>
        <th data-options="field:'gender',width:100">用户性别</th>
        <th data-options="field:'province',width:100">省份</th>
        <th data-options="field:'city',width:80">城市</th>
        <th data-options="field:'country',width:80,align:'right'">国家</th>
        <th data-options="field:'birthday',width:80,align:'right'">生日</th>
        <th data-options="field:'email',width:80,align:'right'">邮件</th>
        <th data-options="field:'telephone',width:80,align:'right'">电话</th>
        <th data-options="field:'introduce',width:80,align:'right'">介绍</th>
<#--        <th data-options="field:'activeCode',width:80,align:'right'">激活码</th>-->
        <th data-options="field:'state',width:80,align:'right'">状态</th>
        <th data-options="field:'role',width:80,align:'right'">角色</th>
        <th data-options="field:'createTime',width:80,align:'right'">创建时间</th>
        <th data-options="field:'updateTime',width:80,align:'right'">更新时间</th>
    </tr>
    </thead>
</table>

<script language="JavaScript" type="text/javascript">
    $(function () {
        var jq=top.jQuery;
        $("#tb").datagrid({
            onDblClickRow:function (rowIndex,rowData) {
                var has = jq('#tt').tabs("exists", "用户"+rowData.nickname+"购买信息");
                var content = '<iframe scrolling="no" frameborder="0"  src="'+'http://localhost:8080/user/userbuyinfo?userid='+rowData.userId+'" style="width:100%;height:100%;"></iframe>';
                console.info(rowData);
                if(has){
                    jq('#tt').tabs("select", "用户"+rowData.nickname+"购买信息");
                }else {
                    jq('#tt').tabs('add',{//不存在，则添加一个
                        title:"用户"+rowData.nickname+"购买信息",
                        content:content,
                        closable:true
                    });
                }
            }
        })
    })

    //图片展示

    function showImg(value, row, index){
        if(row.icon){
            console.info(value);
            var url = "<img style='width:50px;height:50px;' border='1' src='"+row.icon+"'/>";
            console.info("url"+url);
            return url;
        }else {
            return null;
        }
    }

</script>
</body>
</html>