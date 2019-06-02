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
       data-options="rownumbers:true,singleSelect:true,url:'http://localhost:8080/user/queryuser',method:'get',toolbar:'#tb',footer:'#ft',
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
            <label>userInfoId</label>
            <input id="userInfoId" name="userInfoId" class="easyui-textbox"  readonly="true" editable="false">
        </div>
        <div class="fitem">
            <label>userId</label>
            <input id="userId" name="userId" class="easyui-textbox"  readonly="true" editable="false">
        </div>
<#--        <div class="fitem">-->
<#--            <label>UserIcon</label>-->
<#--            <input id="UserIcon" name="UserIcon" class="easyui-textbox" required="true">-->
<#--        </div>-->
        <div class="fitem">
            <label>nickname</label>
            <input id="nickname" name="nickname" class="easyui-textbox"  readonly="true" editable="false">
        </div>
<#--        <div class="fitem">-->
<#--            <label>Gender</label>-->
<#--            <input id="Gender" name="Gender" class="easyui-textbox"  required="true">-->
<#--        </div>-->
        <div class="fitem">
            <label>role</label>
            <input id="role" name="role" class="easyui-textbox"  required=true>
        </div>
<#--        <div class="fitem">-->
<#--            <label>Email</label>-->
<#--            <input id="Email" name="Email" class="easyui-textbox"  required="true">-->
<#--        </div>-->
<#--        <div class="fitem">-->
<#--            <label>Telephone</label>-->
<#--            <input id="Telephone" name="Telephone" class="easyui-textbox" required="true">-->
<#--        </div>-->
        <div class="fitem">
            <label>state</label>
            <select id="state" name="state" class="easyui-combobox" style="width: 100px">
                <option>锁定</option>
                <option>活动</option>
            </select>
        </div>
        <div class="fitem">
            <label>introduce</label>
            <input id="introduce" name="introduce" class="easyui-textbox" required="true">
        </div>
<#--        <div class="fitem">-->
<#--            <label>CreateTime</label>-->
<#--            <input id="CreateTime" name="CreateTime" class="easyui-textbox"  required="true">-->
<#--        </div>-->
<#--        <div class="fitem">-->
<#--            <label>UpdateTime</label>-->
<#--            <input id="UpdateTime" name="UpdateTime" class="easyui-textbox"  required="true">-->
<#--        </div>-->
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

    $(function () {
        $('#nickname').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#userInfoId').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#userId').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
        $('#introduce').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
    })


   function saveUser(){
       $('#fm').form('submit',{
           url: url,
           success: function(result){
               var result = eval('('+result+')');
               // if (result.errorMsg){
               //     $.messager.show({
               //         title: 'Error',
               //         msg: result.errorMsg
               //     });
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
                    $.post('http://localhost:8080/user/delete',{userInfoId:row.userInfoId},function(result){
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
            $('#fm').form('clear');
            $('#fm').form('load',row);
            url = 'http://localhost:8080/user/update?updateid='+row.UserId;
        }
    }

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
