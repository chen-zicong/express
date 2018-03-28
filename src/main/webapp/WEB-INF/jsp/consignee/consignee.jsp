<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String orgId = session.getAttribute("loginTreeId").toString();
//Object orgId = session.getAttribute("loginTreeId");

%>

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
		<a class="easyui-linkbutton" onclick="admin.deleteWin()" iconCls="icon-table-row-delete" plain='true'>删除</a>
		<span style="margin-left:20px">名字 ：&nbsp<span><input id="sConsigneeName" type="text" style="width:70px;"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>	
	</div>
	<div id="deletewin" class="easyui-window" title="删除会议" modal="true" data-options="iconCls:'icon-save'" style="top:100px;width:300px;height:200px;padding:5px;" closed="true">
		<div style="margin-bottom:50px;margin-top:30px"><p style="margin:0 auto;width:160px;font-size:16px;">请问要删除此会议？</p></div>
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
			url:"${proPath }" + '/express/consignee/getConsigneeList',
			title:'收件人列表',
			columns:[[
				{field:'consigneeId',title:'收件人id',width:20},
				{field:'consigneeName',title:'收件人姓名',width:25},
				{field:'consigneePhone',title:'收件人手机',width:35},
				{field:'reservepersonName',title:'备用人姓名',width:35},
				{field:'reservepersonPhone',title:'备用人手机',width:35},
				{field:'consigneeAddress',title:'收货地址',width:35},
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	
				}, 
		});
	});
	
	var admin = {

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
		var consigneeName  = $("#sConsigneeName").val();
		$('#meeting').datagrid('load',{
			consigneeName:consigneeName
		});
	})
  	</script>
</body>
</html>