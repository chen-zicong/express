<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
	<title>马赛物流</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/icon.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui_animation.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui_plus.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wu.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.insdep-extend.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#deleteWin p{
		margin-top:20px;
		text-align:center;
	}
	#deleteWin a{
		margin-left:20px;
		margin-top:40px;
	}
</style>
</head>
<body class="easyui-layout">
	<div id="content" region="center"style="width:1095px;height:800px">
		<table id="meeting" singleSelect="true"></table>
	</div>
	<div id="meetingTb" style="padding-top:0px">
		<shiro:hasAnyRoles name="2,3">
		<a class="easyui-linkbutton" onclick="admin.addAdminWin()" iconCls="icon-add1" plain='true'>新增</a>	
		<a class="easyui-linkbutton" onclick="admin.deleteWin()" iconCls="icon-table-row-delete" plain='true'>删除</a>	
		</shiro:hasAnyRoles>
		<span style="margin-left:20px">姓名 ：&nbsp<span><input id="sadminName" type="text" style="width:70px;"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	
	<div id="addWin" class="easyui-window" title="新增管理员" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:500px;height:400px;padding-top:20px;" closed="true">

		 <table align="center" width="500" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">管理员姓名:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="adminName" id="adminName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">管理员手机:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="adminPhone" id="adminPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">管理员密码:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="adminPassword" id="adminPassword" /></td>
		 	</tr>
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="admin.addAdmin()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addWin').window('close')">取消</a>
		</div> 
	</div>  
	<div id="deletewin" class="easyui-window" title="删除" modal="true" data-options="iconCls:'icon-save'" style="top:100px;width:300px;height:200px;padding:5px;" closed="true">
		<div style="margin-bottom:50px;margin-top:30px"><p style="margin:0 auto;width:160px;font-size:16px;">请问是否要删除？</p></div>
		<div style="margin:0 auto;width:215px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="admin.deleteAdmin()">确认删除</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deletewin').window('close')">取消删除</a>
		</div>
	</div>  
    <script type="text/javascript">
		var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1195,
			url:"${proPath }" + '/express/admin/getAdminList',
			title:'货物接件人列表',
			columns:[[
				{field:'adminId',title:'接件人id',width:20},
				{field:'adminName',title:'接件人姓名',width:25},
				{field:'adminPhone',title:'接件人手机',width:35},
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	 roleId:'1',
			    	 adminName:""
				}, 
		});
	});
	
	var admin = {
		//新增管理员窗口
		addAdminWin:function(){
			$("#addWin").window('open');
		},
		
		//新增管理员
		addAdmin:function(){
			if($("#adminName").val()==''|| $("#adminPhone").val()==''|| $("#adminPassword").val()==''){
				$.messager.alert('警告','请输入完整数据');
				return;
			}
			//获取表单数据
			var adminName = $("#adminName").val();
			var adminPhone = $("#adminPhone").val();
			var adminPassword = $("#adminPassword").val();
			$.ajax({
				   type: "POST",
				   url: "/express/admin/add",
				   data: {
					   adminName:adminName,
					   adminPhone:adminPhone,
					   adminPassword:adminPassword,
					   adminRoleId:'1'
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						//window.location.reload();
				   	}
			   }
			});	
			
		},
		
		//打开删除窗口
		deleteWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行删除');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行删除');
				return;
			}
			$("#deletewin").window('open');
		}
	}
	
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var adminName  = $("#sadminName").val();
		$('#meeting').datagrid('load',{
			adminName:adminName,
			roleId:1
		});
	})
  	</script>
</body>
</html>